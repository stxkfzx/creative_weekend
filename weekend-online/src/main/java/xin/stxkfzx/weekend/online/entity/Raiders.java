package xin.stxkfzx.weekend.online.entity;

import java.util.Date;

/**
 * @author krjaydog
 * @date 2019-04-12 08:45
 */
public class Raiders {

    private Integer tbId;

    private String title;

    private Date createTime;

    private Date updateTime;

    private Short status;

    private RaidersCategory raidersCategory;

    private RaidersContent raidersContent;

    private Integer userId;

    public Raiders() {
    }

    public Raiders(String title, Date createTime, Date updateTime, Short status, RaidersCategory raidersCategory, RaidersContent raidersContent, Integer userId) {
        this.title = title;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.status = status;
        this.raidersCategory = raidersCategory;
        this.raidersContent = raidersContent;
        this.userId = userId;
    }


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

    public void setStatus(Short status) {
        this.status = status;
    }

    public RaidersCategory getRaidersCategory() {
        return raidersCategory;
    }

    public void setRaidersCategory(RaidersCategory raidersCategory) {
        this.raidersCategory = raidersCategory;
    }

    public RaidersContent getRaidersContent() {
        return raidersContent;
    }

    public void setRaidersContent(RaidersContent raidersContent) {
        this.raidersContent = raidersContent;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Raiders{" +
                "tbId=" + tbId +
                ", title='" + title + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", status=" + status +
                ", raidersCategory=" + raidersCategory +
                ", raidersContent=" + raidersContent +
                ", userId=" + userId +
                '}';
    }
}