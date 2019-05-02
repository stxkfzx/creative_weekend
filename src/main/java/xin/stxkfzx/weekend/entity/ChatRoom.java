package xin.stxkfzx.weekend.entity;

import java.util.Date;
import java.util.StringJoiner;

/**
 * 
 * 
 * @author fmy
 * @date 2019-04-10 22:09 
 */
public class ChatRoom {
    private Integer tbId;

    private Integer activateId;

    private Integer userId;

    private Date createTime;

    private Date updateTime;
    private Short status;

    @Override
    public String toString() {
        return new StringJoiner(", ", ChatRoom.class.getSimpleName() + "[", "]")
                .add("tbId=" + tbId)
                .add("activateId=" + activateId)
                .add("userId=" + userId)
                .add("createTime=" + createTime)
                .add("updateTime=" + updateTime)
                .add("status=" + status)
                .toString();
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public Integer getTbId() {
        return tbId;
    }

    public void setTbId(Integer tbId) {
        this.tbId = tbId;
    }

    public Integer getActivateId() {
        return activateId;
    }

    public void setActivateId(Integer activateId) {
        this.activateId = activateId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

}