package xin.stxkfzx.weekend.vo;

/**
 * @author VicterTian
 * @version V1.0
 * @date 2019/5/3
 */
public class FriendVO {
    private Integer id;

    private Integer uid;

    private Integer fid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
    }

    @Override
    public String toString() {
        return "FriendVO{" +
                "id=" + id +
                ", uid=" + uid +
                ", fid=" + fid +
                '}';
    }

    public FriendVO() {
    }

    public FriendVO(Integer id, Integer uid, Integer fid) {
        this.id = id;
        this.uid = uid;
        this.fid = fid;
    }
}
