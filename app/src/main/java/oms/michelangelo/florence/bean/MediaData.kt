package oms.michelangelo.florence.bean

import java.util.*

/**
 * Created by Sherry on 2020/5/5
 * 获取到的图片实体类
 */

class MediaData {

    var id: Int = 0
    var thumbPath: String? = null
    var path: String? = null
    var createTime: String? = null

    constructor(id: Int,  path: String, createTime: String) {
        this.id = id
        this.path = path
        this.createTime = createTime
    }

    constructor(id: Int, thumbPath: String, path: String, createTime: String) {
        this.id = id
        this.thumbPath = thumbPath
        this.path = path
        this.createTime = createTime
    }

    constructor()

}