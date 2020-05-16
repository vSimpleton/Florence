package oms.michelangelo.florence

import android.content.Context
import android.provider.MediaStore
import java.io.File

/**
 * Created by Sherry on 2020/5/17
 * 获取相册相关信息manager
 */

object MediaManager {

    fun getAllImageList(context: Context, mImageLists: ArrayList<ImageItemInfo>) : ArrayList<ImageItemInfo> {
        Thread(Runnable {
            val allImageParams = HashMap<String, ArrayList<ImageItemInfo>>()
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
                    val path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA))
                    val time =
                        cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATE_ADDED))
                    val size = cursor.getInt(cursor.getColumnIndex(MediaStore.Images.Media.SIZE))
                    val name =
                        cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DISPLAY_NAME))

                    val mImageInfo = ImageItemInfo()
                    mImageInfo.path = path
                    mImageInfo.createTime = time
                    mImageInfo.size = size
                    mImageInfo.name = name
                    mImageLists.add(mImageInfo)

                    val dirPath = File(path).parentFile.absolutePath
                    if (allImageParams.containsKey(dirPath)) {
                        val lists = allImageParams[dirPath];
                        val data = ImageItemInfo()
                        data.path = path
                        data.createTime = time
                        data.size = size
                        data.name = name
                        lists?.add(data)
                        continue
                    } else {
                        val lists = ArrayList<ImageItemInfo>()
                        val data = ImageItemInfo()
                        data.path = path
                        data.createTime = time
                        data.size = size
                        data.name = name
                        lists.add(data)
                        allImageParams[dirPath] = lists
                    }
                }
                cursor.close()
            }
        }).start()

        return mImageLists
    }

}