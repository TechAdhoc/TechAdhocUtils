package com.techadhoc.techadhocutils.features.utils.imageutils;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;

import com.techadhoc.techadhocutils.features.utils.LogUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class BitmapLoader {
    private Context mContext;
    private imgLoadListener imgLoadListener;
    private static String TAG = LogUtil.makeLogTag(BitmapLoader.class);

    public BitmapLoader(Context context, imgLoadListener listner) {
        imgLoadListener = listner;
        this.mContext = context;
    }

    public void saveImages(ArrayList<Object> listGalryDetailProd) {
        if (listGalryDetailProd.size() > 0) {
            for (Object product : listGalryDetailProd) {
              /*  if (null != product.getImgBitMap()) {
                    onBitmapLoaded(product.getImgBitMap());
                } else {
                    onBitmapFailed();
                }*/
            }
        }
    }

    public void onBitmapLoaded(Bitmap bitmap) {
        File file = new File(getDataFolder(mContext).getPath() + "/" + TAG +
                System.currentTimeMillis() + ".jpeg");
        try {
            if (!(file.exists())) {
                file.createNewFile();
                FileOutputStream ostream = new FileOutputStream(file);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 75, ostream);
                ostream.close();
            }
            imgLoadListener.onImageLoaded(1);
        } catch (Exception e) {
            LogUtil.LOGE(TAG, e.getMessage());
            imgLoadListener.onImageLoaded(1);
        }
    }

    public void onBitmapFailed() {
        imgLoadListener.onImageLoaded(1);
    }

    public File getCacheFolder(Context context) {
        File cacheDir = null;
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            cacheDir = new File(Environment.getExternalStorageDirectory(), "cachesunflower");
            if (!cacheDir.isDirectory()) {
                cacheDir.mkdirs();
            }
        }

        if (!cacheDir.isDirectory()) {
            cacheDir = context.getCacheDir(); //get system cache folder
        }
        return cacheDir;
    }

    public File getDataFolder(Context context) {
        File dataDir = null;
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            dataDir = new File(Environment.getExternalStorageDirectory(), "sunflower");
            if (!dataDir.isDirectory()) {
                dataDir.mkdirs();
            }
        }
        if (!dataDir.isDirectory()) {
            dataDir = context.getFilesDir();
        }
        return dataDir;
    }


    ArrayList<String> readData() {
        ArrayList<String> FilePathStrings = null;
        try {
           /* String myData = null;
            File file = new File(getDataFolder(
                    imageView.getContext()).getPath() + "/" + name);
            Bitmap bitmap = BitmapFactory.decodeFile(
                    getDataFolder(imageView.getContext()).getPath() + "/" + name);*/
            File mFile = getDataFolder(mContext);
            if (mFile.isDirectory()) {
                File[] listFile = mFile.listFiles();
                // Create a String array for FilePathStrings
                //FilePathStrings = new String[listFile.length];
                FilePathStrings = new ArrayList<String>();
                // Create a String array for FileNameStrings
                // String[]  FileNameStrings = new String[listFile.length];

                for (int i = 0; i < listFile.length; i++) {
                    // Get the path of the image file
                    String file_path = listFile[i].getAbsolutePath();
                    FilePathStrings.add(file_path);
                    // Get the name image file
                    //   FileNameStrings[i] = listFile[i].getName();
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return FilePathStrings;
    }

    void deleteAll() {
        File dir = getDataFolder(mContext);
        if (null != dir) {
            deleteRecursive(dir);
        }
    }

    public void deleteRecursive(File fileOrDirectory) {
        if (fileOrDirectory.isDirectory()) {
            File[] files = fileOrDirectory.listFiles();
            if (null != files) {
                for (File child : files) {
                    deleteRecursive(child);
                }
            }
        }
        fileOrDirectory.delete();
    }

    public interface imgLoadListener {
        void onImageLoaded(int completeCode);
    }

}