package oms.michelangelo.florence.utils

import android.R.attr.path
import android.content.ContentResolver
import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import oms.michelangelo.florence.bean.MediaData
import java.io.File


/**
 * Created by Sherry on 2020/5/17
 * 获取相册相关信息manager
 */

object MediaManager {

    interface Callback {
        fun onSuccess(result: List<MediaData>)
    }

    fun getAllImageList(context: Context, mMediaDataLists: ArrayList<MediaData>, mCallBack: Callback) {
        Thread(Runnable {
            val allImageParams = HashMap<String, ArrayList<MediaData>>()
            val mImageUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            val cursor = context.contentResolver.query(
                    mImageUri,
                    null,
                    MediaStore.Images.Media.MIME_TYPE + "=? or " + MediaStore.Images.Media.MIME_TYPE + "=?",
                    arrayOf("image/jpeg", "image/png"),
                    MediaStore.Images.Media.DATE_MODIFIED + " desc"
            )
            if (cursor != null) {
                while (cursor.moveToNext()) {
                    val thumbPath =
                            cursor.getString(cursor.getColumnIndex(MediaStore.Images.Thumbnails.DATA))
                    val path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA))
                    val time =
                            cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATE_ADDED))
                    val id = cursor.getInt(cursor.getColumnIndex(MediaStore.Images.Media._ID))

                    val filePath: String
                    val fileThumbPath: String
                    if (Build.VERSION.SDK_INT == Build.VERSION_CODES.Q) {
                        fileThumbPath = MediaStore.Images.Media
                                .EXTERNAL_CONTENT_URI
                                .buildUpon()
                                .appendPath(id.toString()).build().toString()
                        filePath = fileThumbPath
                    } else {
                        filePath = path
                        fileThumbPath = thumbPath
                    }

                    val mediaData = MediaData(id, fileThumbPath, filePath, time)
                    mMediaDataLists.add(mediaData)

                    val dirPath = File(filePath).parentFile.absolutePath
                    if (allImageParams.containsKey(dirPath)) {
                        val lists = allImageParams[dirPath];
                        lists?.add(mediaData)
                        continue
                    } else {
                        val lists = ArrayList<MediaData>()
                        lists.add(mediaData)
                        allImageParams[dirPath] = lists
                    }
                }
                mCallBack.onSuccess(mMediaDataLists)
                cursor.close()
            }
        }).start()
    }

}