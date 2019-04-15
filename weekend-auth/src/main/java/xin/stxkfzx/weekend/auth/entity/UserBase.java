package xin.stxkfzx.weekend.auth.entity;

import java.io.Serializable;

/**
 * 本类只用于封装与 Token 部分相关的实体，故只包含了四个基本属性
 *
 * @author VicterTian
 * @version V1.0
 * @date 2019/4/13
 */
public class UserBase implements Serializable {
    private String id;
    private String nickName;
    private String password;
    private Integer status;

    public UserBase() {
    }

    public UserBase(String id, String nickName) {
        this.id = id;
        this.nickName = nickName;
    }

    public UserBase(String id, String nickName, Integer status) {
        this.id = id;
        this.nickName = nickName;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    @Override
    public String toString() {
        return "UserBase{" +
                "id='" + id + '\'' +
                ", nickName='" + nickName + '\'' +
                ", password='" + password + '\'' +
                ", status=" + status +
                '}';
    }
}
