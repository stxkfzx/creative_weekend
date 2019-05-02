package xin.stxkfzx.weekend.entity;

import xin.stxkfzx.weekend.enums.StatusEnum;

import java.util.Date;

/**
 * 
 * 
 * @author fmy
 * @date 2019-04-10 22:09 
 */
public class Raiders {
    private Integer tbId;

    private String title;

    private Date createTime;

    private Date updateTime;

    private Short status;

    private Integer categoryId;

    private Integer contentId;

    private Integer userId;

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

    public Short getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status.getCode().shortValue();
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", tbId=").append(tbId);
        sb.append(", title=").append(title);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", status=").append(status);
        sb.append(", categoryId=").append(categoryId);
        sb.append(", contentId=").append(contentId);
        sb.append(", userId=").append(userId);
        sb.append("]");
        return sb.toString();
    }
}