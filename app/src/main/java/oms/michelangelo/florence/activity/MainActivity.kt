package oms.michelangelo.florence.activity

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import oms.michelangelo.florence.ImageItemInfo
import oms.michelangelo.florence.MediaManager
import oms.michelangelo.florence.R
import oms.michelangelo.florence.adapter.SelectPhotoAdapter
import oms.michelangelo.florence.utils.StatusBarUtil
import java.io.File

private val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)

class MainActivity : AppCompatActivity() {

    private var list: MutableList<String> = ArrayList()
    private val REQUEST_PERMISSION_CODE = 100
    private lateinit var mAdapter: SelectPhotoAdapter
    private lateinit var mLists: ArrayList<ImageItemInfo>
    private var hasPermissionDenied = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        StatusBarUtil.setColorNoTranslucent(this, Color.WHITE)
        StatusBarUtil.StatusBarLightMode(this)

        initPermission()
        getAllImageList()
        initRecyclerView()
    }

    private fun getAllImageList() {
        mLists = ArrayList()
        mLists = MediaManager.getAllImageList(this, mLists)
    }

    private fun initRecyclerView() {
        mAdapter = SelectPhotoAdapter(this)
        val layoutManager = GridLayoutManager(this, 4)
        rcyPhoto.layoutManager = layoutManager
        rcyPhoto.adapter = mAdapter
        mAdapter.setData(mLists)
    }

    private fun initPermission() {
        for (permission in REQUIRED_PERMISSIONS) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                list.add(permission)
            }
        }

        if (list.size > 0) {
            ActivityCompat.requestPermissions(this, list.toTypedArray(), REQUEST_PERMISSION_CODE)
        } else {
            //获取媒体文件
//            getAllImageList()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_PERMISSION_CODE) {
            for (i in grantResults.indices) {
                if (grantResults[i] == -1) {
                    hasPermissionDenied = false
                }
            }
        }

        if (hasPermissionDenied) { //跳转到权限设置页面

        } else { //获取媒体文件
//            getAllImageList()
        }
    }
}
