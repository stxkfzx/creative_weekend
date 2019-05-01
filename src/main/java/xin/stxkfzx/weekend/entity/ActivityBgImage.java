package xin.stxkfzx.weekend.entity;

import xin.stxkfzx.weekend.enums.StatusEnum;

import java.util.Date;

/**
 * 
 * 
 * @author fmy
 * @date 2019-04-29 14:42 
 */
public class ActivityBgImage {
    private Integer tbId;

    private String url;

    private Short status;

    private Date createTime;

    private Date updateTime;

    private Integer activityId;

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

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", tbId=").append(tbId);
        sb.append(", url=").append(url);
        sb.append(", status=").append(status);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", activityId=").append(activityId);
        sb.append("]");
        return sb.toString();
    }

    public void setStatus(StatusEnum status) {
        this.status = status.getCode().shortValue();
    }
}