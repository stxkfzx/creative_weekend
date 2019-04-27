package xin.stxkfzx.weekend.entity;

import java.util.Date;

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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", tbId=").append(tbId);
        sb.append(", activateId=").append(activateId);
        sb.append(", userId=").append(userId);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }
}