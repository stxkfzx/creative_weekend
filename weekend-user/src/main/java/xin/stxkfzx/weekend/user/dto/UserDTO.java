package xin.stxkfzx.weekend.user.dto;

import java.util.Objects;

/**
 * @author VicterTian
 * @version V1.0
 * @date 2019/4/12
 */
public class UserDTO {
    private Integer tbId;

    private String nickName;

    private String phoneNum;

    private String fullname;

    private String address;

    private Short status;

    public UserDTO() {
    }

    public UserDTO(Integer tbId, String nickName, String phoneNum, String fullname, String address, Short status) {
        this.tbId = tbId;
        this.nickName = nickName;
        this.phoneNum = phoneNum;
        this.fullname = fullname;
        this.address = address;
        this.status = status;
    }

    public Integer getTbId() {
        return tbId;
    }

    public void setTbId(Integer tbId) {
        this.tbId = tbId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "tbId=" + tbId +
                ", nickName='" + nickName + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", fullname='" + fullname + '\'' +
                ", address='" + address + '\'' +
                ", status=" + status +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (!(o instanceof UserDTO)) {
            return false;
        }
        UserDTO userDTO = (UserDTO) o;
        return Objects.equals(getTbId(), userDTO.getTbId()) &&
                Objects.equals(getNickName(), userDTO.getNickName()) &&
                Objects.equals(getPhoneNum(), userDTO.getPhoneNum()) &&
                Objects.equals(getFullname(), userDTO.getFullname()) &&
                Objects.equals(getAddress(), userDTO.getAddress()) &&
                Objects.equals(getStatus(), userDTO.getStatus());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTbId(), getNickName(), getPhoneNum(), getFullname(), getAddress(), getStatus());
    }
}
