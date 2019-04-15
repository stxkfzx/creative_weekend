package xin.stxkfzx.weekend.online.entity;

import java.util.Date;

/**
 * 
 * 
 * @author fmy
 * @date 2019-04-10 22:09 
 */
public class VideoShare {
    private Integer tbId;

    private String url;

    private String title;

    private Short status;

    private Date createTime;

    private Date updateTime;

    private Integer userId;

    private ShareCategory shareCategory;

    public VideoShare(String url, String title, Short status, Date createTime, Date updateTime, Integer userId, ShareCategory shareCategory) {
        this.url = url;
        this.title = title;
        this.status = status;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.userId = userId;
        this.shareCategory = shareCategory;
    }

    public VideoShare() {
    }

    @Override
    public String toString() {
        return "VideoShare{" +
                "tbId=" + tbId +
                ", url='" + url + '\'' +
                ", title='" + title + '\'' +
                ", status=" + status +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", userId=" + userId +
                ", shareCategory=" + shareCategory +
                '}';
    }

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

    public ShareCategory getShareCategory() {
        return shareCategory;
    }

    public void setShareCategory(ShareCategory shareCategory) {
        this.shareCategory = shareCategory;
    }
}