package com.techadhoc.techadhocutils.features.utils.imageutils

import android.content.Context
import android.graphics.Bitmap
import android.os.Environment
import com.techadhoc.techadhocutils.features.utils.LogUtil
import java.io.File
import java.io.FileOutputStream

class BitMapLoaderK(
    private val mContext: Context,
    private val imgLoadListener: imgLoaderListener
) {
    fun saveImages(listGalryDetailProd: ArrayList<Any>) {
        if (listGalryDetailProd.size > 0) {
            for (product in listGalryDetailProd) {
               /* if (null != product.getImgBitMap()) {
                    onBitmapLoaded(product.getImgBitMap())
                } else {
                    onBitmapFailed()
                }*/
            }
        }
    }

    private fun onBitmapLoaded(bitmap: Bitmap) {
        val file = File(
            getDataFolder(mContext)!!.path + "/" + TAG +
                    System.currentTimeMillis() + ".jpeg"
        )
        try {
            if (!file.exists()) {
                file.createNewFile()
                val ostream = FileOutputStream(file)
                bitmap.compress(Bitmap.CompressFormat.JPEG, 75, ostream)
                ostream.close()
            }
            imgLoadListener.onImageLoaded(1)
        } catch (e: Exception) {
            LogUtil.LOGE(
                TAG,
                e.message
            )
            imgLoadListener.onImageLoaded(1)
        }
    }

    fun onBitmapFailed() {
        imgLoadListener.onImageLoaded(1)
    }

    fun getCacheFolder(context: Context): File? {
        var cacheDir: File? = null
        if (Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED) {
            cacheDir =
                File(Environment.getExternalStorageDirectory(), "cachesunflower")
            if (!cacheDir.isDirectory) {
                cacheDir.mkdirs()
            }
        }
        if (!cacheDir!!.isDirectory) {
            cacheDir = context.cacheDir //get system cache folder
        }
        return cacheDir
    }

    private fun getDataFolder(context: Context): File? {
        var dataDir: File? = null
        if (Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED) {
            dataDir =
                File(Environment.getExternalStorageDirectory(), "sunflower")
            if (!dataDir.isDirectory) {
                dataDir.mkdirs()
            }
        }
        if (!dataDir!!.isDirectory) {
            dataDir = context.filesDir
        }
        return dataDir
    }

    fun readData(): ArrayList<String?>? {
        var FilePathStrings: ArrayList<String?>? = null
        try {
            /* String myData = null;
            File file = new File(getDataFolder(
                    imageView.getContext()).getPath() + "/" + name);
            Bitmap bitmap = BitmapFactory.decodeFile(
                    getDataFolder(imageView.getContext()).getPath() + "/" + name);*/
            val mFile = getDataFolder(mContext)
            if (mFile!!.isDirectory) {
                val listFile = mFile.listFiles()
                // Create a String array for FilePathStrings
                //FilePathStrings = new String[listFile.length];
                FilePathStrings = ArrayList()
                // Create a String array for FileNameStrings
                // String[]  FileNameStrings = new String[listFile.length];
                for (i in listFile.indices) {
                    // Get the path of the image file
                    val file_path = listFile[i].absolutePath
                    FilePathStrings.add(file_path)
                    // Get the name image file
                    //   FileNameStrings[i] = listFile[i].getName();
                }
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
        return FilePathStrings
    }

    fun deleteAll() {
        val dir = getDataFolder(mContext)
        dir?.let { deleteRecursive(it) }
    }

    private fun deleteRecursive(fileOrDirectory: File) {
        if (fileOrDirectory.isDirectory) {
            val files = fileOrDirectory.listFiles()
            if (null != files) {
                for (child in files) {
                    deleteRecursive(child)
                }
            }
        }
        fileOrDirectory.delete()
    }

    interface imgLoaderListener {
        fun onImageLoaded(completeCode: Int)
    }

    companion object {
        private val TAG =
            LogUtil.makeLogTag(
                BitmapLoader::class.java
            )
    }

}