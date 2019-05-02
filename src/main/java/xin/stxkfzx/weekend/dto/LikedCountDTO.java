package xin.stxkfzx.weekend.dto;

/**
 * @author VicterTian
 * @version V1.0
 * @date 2019/5/2
 */
public class LikedCountDTO {
    private Integer id;
    /**
     * 点赞模块
     */
    private String likedModule;
    /**
     * 被点赞内容id
     */
    private Integer count;

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

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public LikedCountDTO() {
    }

    public LikedCountDTO(String likedModule, Integer count) {
        this.likedModule = likedModule;
        this.count = count;
    }
}
