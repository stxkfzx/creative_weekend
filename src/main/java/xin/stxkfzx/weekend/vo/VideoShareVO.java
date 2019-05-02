package xin.stxkfzx.weekend.vo;

import java.util.Date;

/**
 * @author VicterTian
 * @version V1.0
 * @date 2019/5/2
 */
public class VideoShareVO {
    private Integer tbId;

    private String url;

    private String title;

    private Short status;

    private Date createTime;

    private Date updateTime;

    private Integer userId;

    private Integer categoryId;

    private Integer likeNum;

    public Integer getTbId() {
        return tbId;
    }

    public void setTbId(Integer tbId) {
        this.tbId = tbId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(Integer likeNum) {
        this.likeNum = likeNum;
    }

    @Override
    public String toString() {
        return "VideoShareVO{" +
                "tbId=" + tbId +
                ", url='" + url + '\'' +
                ", title='" + title + '\'' +
                ", status=" + status +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", userId=" + userId +
                ", categoryId=" + categoryId +
                ", likeNum=" + likeNum +
                '}';
    }
}
