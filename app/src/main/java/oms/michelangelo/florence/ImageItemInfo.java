package oms.michelangelo.florence;

/**
 * Created by Sherry on 2020-05-05
 * 获取到的图片实体类
 */

public class ImageItemInfo {

    private String path;
    private String name;
    private long createTime;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }
}
