package com.techadhoc.techadhocutils.features.utils;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.webkit.MimeTypeMap;

import androidx.exifinterface.media.ExifInterface;
import androidx.loader.content.CursorLoader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class ImageCompressor {
    private Context context;

    public ImageCompressor(Context context) {
        this.context = context;
    }

    private static final String TAG = LogUtil.makeLogTag(ImageCompressor.class);

    /**
     * return image File
     */
    public File compressImage(Uri imageUri) throws IOException {
        // new FileUtils(context).getPath(imageUri);
        //String filePath = getRealPathFromURI( imageUri);

        //  com.android.support:exifinterface:25.1.0
        Bitmap scaledBitmap = null;
        Bitmap bmp = null;
        ExifInterface exif;
        BitmapFactory.Options options = new BitmapFactory.Options();
//      by setting this field as true, the actual bitmap pixels are not loaded in the memory. Just the bounds are loaded. If
//      you try the use the bitmap here, you will get null.
        options.inJustDecodeBounds = true;
        InputStream inputStreamImage = context.getContentResolver().openInputStream(imageUri);
        bmp = BitmapFactory.decodeStream(inputStreamImage, null, options);

        int actualHeight = options.outHeight;
        int actualWidth = options.outWidth;

//      max Height and width values of the compressed image is taken as 816x612
        float maxHeight = 700.0f;
        float maxWidth = 500.0f;
        float imgRatio = actualWidth / actualHeight;
        float maxRatio = maxWidth / maxHeight;

//      width and height values are set maintaining the aspect ratio of the image
        if (actualHeight > maxHeight || actualWidth > maxWidth) {
            if (imgRatio < maxRatio) {
                imgRatio = maxHeight / actualHeight;
                actualWidth = (int) (imgRatio * actualWidth);
                actualHeight = (int) maxHeight;
            } else if (imgRatio > maxRatio) {
                imgRatio = maxWidth / actualWidth;
                actualHeight = (int) (imgRatio * actualHeight);
                actualWidth = (int) maxWidth;
            } else {
                actualHeight = (int) maxHeight;
                actualWidth = (int) maxWidth;
            }
        }

//      setting inSampleSize value allows to load a scaled down version of the original image
        options.inSampleSize = calculateInSampleSize(options, actualWidth, actualHeight);

//      inJustDecodeBounds set to false to load the actual bitmap
        options.inJustDecodeBounds = false;

//      this options allow android to claim the bitmap memory if it runs low on memory
        options.inPurgeable = true;
        options.inInputShareable = true;
        options.inTempStorage = new byte[16 * 1024];

        try {
//          load the bitmap from its path
            inputStreamImage = context.getContentResolver().openInputStream(imageUri);
            bmp = BitmapFactory.decodeStream(inputStreamImage, null, options);
        } catch (OutOfMemoryError exception) {
            exception.printStackTrace();
        } finally {
            exif = new ExifInterface(inputStreamImage);
            inputStreamImage.close();
        }
        try {
            scaledBitmap = Bitmap.createBitmap(actualWidth, actualHeight, Bitmap.Config.ARGB_8888);
        } catch (OutOfMemoryError exception) {
            exception.printStackTrace();
        }

        float ratioX = actualWidth / (float) options.outWidth;
        float ratioY = actualHeight / (float) options.outHeight;
        float middleX = actualWidth / 2.0f;
        float middleY = actualHeight / 2.0f;

        Matrix scaleMatrix = new Matrix();
        scaleMatrix.setScale(ratioX, ratioY, middleX, middleY);

        Canvas canvas = new Canvas(scaledBitmap);
        canvas.setMatrix(scaleMatrix);
        canvas.drawBitmap(bmp, middleX - bmp.getWidth() / 2, middleY - bmp.getHeight() / 2, new Paint(Paint.FILTER_BITMAP_FLAG));

//      check the rotation of the image and display it properly
        try {
            int orientation = exif.getAttributeInt(
                    ExifInterface.TAG_ORIENTATION, 0);
            LogUtil.LOGD(TAG, "Exif: " + orientation);
            Matrix matrix = new Matrix();
            if (orientation == 6) {
                matrix.postRotate(90);
                LogUtil.LOGD(TAG, "Exif: " + orientation);
            } else if (orientation == 3) {
                matrix.postRotate(180);
                LogUtil.LOGD(TAG, "Exif: " + orientation);
            } else if (orientation == 8) {
                matrix.postRotate(270);
                LogUtil.LOGD(TAG, "Exif: " + orientation);
            }
            scaledBitmap = Bitmap.createBitmap(scaledBitmap, 0, 0,
                    scaledBitmap.getWidth(), scaledBitmap.getHeight(), matrix,
                    true);
        } catch (Exception e) {
            LogUtil.LOGE(TAG, e.getMessage());
        }
        FileOutputStream out = null;
        File imgFile = null;
        try {
            imgFile = getImageFile();
            out = new FileOutputStream(imgFile);
//          write the compressed bitmap at the destination specified by filename.
            scaledBitmap.compress(Bitmap.CompressFormat.JPEG, 75, out);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            out.close();
        }
        return imgFile;
    }

    public String getFilename() {
        File file = new File(Environment.getExternalStorageDirectory().getPath(), "Sunflower/Images");
        if (!file.exists()) {
            file.mkdirs();
        }
        String uriString = (file.getAbsolutePath() + "/" + System.currentTimeMillis() + ".jpg");
        return uriString;
    }

    public File getImageFile() {
        //File file = new File(Environment.getExternalStorageDirectory().getPath(), "Sunflower/Images");
        File dir = context.getExternalFilesDir(Environment.getExternalStorageDirectory().getPath() + "Sunflower/Images");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File file = new File(dir, System.currentTimeMillis() + ".jpg");
        //String uriString = (file.getAbsolutePath() + "/" + System.currentTimeMillis() + ".jpg");
        return file;
    }

    private String getRealPathFromURI(String contentURI) {
        Uri contentUri = Uri.parse(contentURI);
        Cursor cursor = context.getContentResolver().query(contentUri, null,
                null, null, null);
        if (cursor == null) {
            return contentUri.getPath();
        } else {
            cursor.moveToFirst();
            int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            return cursor.getString(index);
        }
    }

    private String getRealPathFromURI(Uri contentUri) {
        String[] proj = {MediaStore.Images.Media.DATA};
        CursorLoader loader = new CursorLoader(context, contentUri, proj, null, null, null);
        Cursor cursor = loader.loadInBackground();
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result = cursor.getString(column_index);
        cursor.close();
        return result;
    }

    public int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            final int heightRatio = Math.round((float) height / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }
        final float totalPixels = width * height;
        final float totalReqPixelsCap = reqWidth * reqHeight * 2;
        while (totalPixels / (inSampleSize * inSampleSize) > totalReqPixelsCap) {
            inSampleSize++;
        }

        return inSampleSize;
    }

    public static String getMimeType(String url) {
        String type = null;
        String extension = MimeTypeMap.getFileExtensionFromUrl(url);
        if (extension != null) {
            type = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension);
        }
        return type;
    }
}

