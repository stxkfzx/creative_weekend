package xin.stxkfzx.weekend.entity;

import xin.stxkfzx.weekend.enums.StatusEnum;

import java.util.Date;

/**
 * 
 * 
 * @author fmy
 * @date 2019-04-27 0:43 
 */
public class UserJoinChatRoom {
    private Integer tbId;

    private Date createTime;

    private Date updateTime;

    private Date joinTime;

    private Short status;

    private Integer userId;

    private Integer roomId;

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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
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
        sb.append(", userId=").append(userId);
        sb.append(", roomId=").append(roomId);
        sb.append("]");
        return sb.toString();
    }

    public void setStatus(StatusEnum status) {
        this.status = status.getCode().shortValue();
    }
}