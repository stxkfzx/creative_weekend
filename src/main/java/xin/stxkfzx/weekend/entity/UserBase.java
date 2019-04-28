package xin.stxkfzx.weekend.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 本类只用于封装与 Token 部分相关的实体，故只包含了四个基本属性
 *
 * @author VicterTian
 * @version V1.0
 * @date 2019/4/13
 */
@SuppressWarnings("all")
public class UserBase implements Serializable {
    private Integer id;
    private String nickName;
    private String password;
    private Date createTime;
    private Integer status;

    public UserBase() {
    }

    public UserBase(Integer id, String nickName) {
        this.id = id;
        this.nickName = nickName;
    }

    public UserBase(Integer id, String nickName, Integer status) {
        this.id = id;
        this.nickName = nickName;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
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

    @Override
    public String toString() {
        return "UserBase{" +
                "id='" + id + '\'' +
                ", nickName='" + nickName + '\'' +
                ", password='" + password + '\'' +
                ", createTime='" + createTime + '\'' +
                ", status=" + status +
                '}';
    }
}
