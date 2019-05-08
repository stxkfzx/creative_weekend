package xin.stxkfzx.weekend.vo;

import java.util.Date;

/**
 * @author VicterTian
 * @version V1.0
 * @date 2019/5/7
 */
public class RaidersVO {
    private Integer tbId;

    private String title;

    private Date createTime;

    private Date updateTime;

    private Integer categoryId;

    private Integer contentId;

    private Integer userId;

    private String content;

    public Integer getTbId() {
        return tbId;
    }

    public void setTbId(Integer tbId) {
        this.tbId = tbId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getContentId() {
        return contentId;
    }

    public void setContentId(Integer contentId) {
        this.contentId = contentId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "RaidersVO{" +
                "tbId=" + tbId +
                ", title='" + title + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", categoryId=" + categoryId +
                ", contentId=" + contentId +
                ", userId=" + userId +
                ", content='" + content + '\'' +
                '}';
    }
}
