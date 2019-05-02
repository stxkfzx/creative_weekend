package xin.stxkfzx.weekend.entity;

import java.util.Date;

/**
 * 点赞表
 *
 * @author VicterTian
 * @version V1.0
 * @date 2019/5/2
 */
public class Liked {
    private Integer id;
    /**
     * 点赞模块
     */
    private String likedModule;
    /**
     * 被点赞内容id
     */
    private String likedContentId;
    /**
     * 点赞用户id
     */
    private String likedPostId;
    private Integer status;
    private Date createTime;
    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLikedModule() {
        return likedModule;
    }

    public void setLikedModule(String likedModule) {
        this.likedModule = likedModule;
    }

    public String getLikedContentId() {
        return likedContentId;
    }

    public void setLikedContentId(String likedContentId) {
        this.likedContentId = likedContentId;
    }

    public String getLikedPostId() {
        return likedPostId;
    }

    public void setLikedPostId(String likedPostId) {
        this.likedPostId = likedPostId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
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

    public Liked() {
    }

    public Liked(Integer id, String likedModule, String likedContentId, String likedPostId, Integer status, Date createTime, Date updateTime) {
        this.id = id;
        this.likedModule = likedModule;
        this.likedContentId = likedContentId;
        this.likedPostId = likedPostId;
        this.status = status;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Liked(String likedModule,  String likedPostId, String likedContentId,Integer status) {
        this.likedModule = likedModule;
        this.likedContentId = likedContentId;
        this.likedPostId = likedPostId;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Liked{" +
                "id=" + id +
                ", likedModule='" + likedModule + '\'' +
                ", likedContentId='" + likedContentId + '\'' +
                ", likedPostId='" + likedPostId + '\'' +
                ", status=" + status +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
