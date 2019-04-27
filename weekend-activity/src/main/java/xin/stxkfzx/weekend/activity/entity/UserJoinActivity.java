package xin.stxkfzx.weekend.activity.entity;

import xin.stxkfzx.weekend.common.enums.StatusEnum;

import java.util.Date;

/**
 * 
 * 
 * @author fmy
 * @date 2019-04-10 22:09 
 */
public class UserJoinActivity {
    public static final Short JOIN = 1;
    public static final Short EXIT = 0;

    public static final Short PAY_SUCCESS = 2;
    public static final Short PAY_FAIL = -1;

    private Integer tbId;

    private Date createTime;

    private Date updateTime;

    private Date joinTime;

    private Short status;

    private Integer activityId;

    private Integer userId;

    private Short paymentStatus;

    public Integer getTbId() {
        return tbId;
    }

    public void setTbId(Integer tbId) {
        this.tbId = tbId;
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

    public Date getJoinTime() {
        return joinTime;
    }

    public void setJoinTime(Date joinTime) {
        this.joinTime = joinTime;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status.getCode().shortValue();
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Short getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(Short paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", tbId=").append(tbId);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", joinTime=").append(joinTime);
        sb.append(", status=").append(status);
        sb.append(", activityId=").append(activityId);
        sb.append(", userId=").append(userId);
        sb.append(", paymentStatus=").append(paymentStatus);
        sb.append("]");
        return sb.toString();
    }
}