package xin.stxkfzx.weekend.user.entity;

import javax.validation.constraints.*;
import java.util.Date;
import java.util.Objects;

/**
 * @author VicterTian
 * @version V1.0
 * @date 2019/4/12
 */
@SuppressWarnings("all")
public class User {
    private Integer tbId;
    @NotBlank(message = "用户名不能为空")
    private String nickName;
    @NotNull
    @Pattern(regexp = "^((13[0-9])|(14[5,7])|(15[0-3,5-9])|(17[0,3,5-8])|(18[0-9])|166|198|199|(147))\\d{8}$", message = "手机号格式不正确")
    private String phoneNum;
    @NotNull
    @Pattern(regexp = "^[\\w_-]{6,16}$", message = "请输入长度为6~16位密码，可包含数字字母下划线")
    private String password;
    @Pattern(regexp = "^[\u4e00-\u9fa5]$", message = "姓名不符合要求")
    private String fullname;
    @Pattern(regexp = "^(\\d{6})(\\d{4})(\\d{2})(\\d{2})(\\d{3})([0-9]|X)$", message = "身份证不符合要求")
    private String idCard;
    private String address;
    @Pattern(regexp = "^[\u4e00-\u9fa5]$", message = "紧急联系人姓名不符合要求")
    private String urgentContact;
    @Pattern(regexp = "^((13[0-9])|(14[5,7])|(15[0-3,5-9])|(17[0,3,5-8])|(18[0-9])|166|198|199|(147))\\d{8}$", message = "紧急联系人手机号格式不正确")
    private String urgentPhone;
    private Date createTime;
    private Date updateTime;
    @Min(value = -1, message = "状态不合法")
    @Max(value = 3, message = "状态不合法")
    private Short status;

    public User() {
    }

    public User(Integer tbId, @NotBlank(message = "用户名不能为空") String nickName, @Pattern(regexp = "^((13[0-9])|(14[5,7])|(15[0-3,5-9])|(17[0,3,5-8])|(18[0-9])|166|198|199|(147))\\d{8}$", message = "手机号格式不正确") String phoneNum, String password, String fullname, String idCard, String address, String urgentContact, String urgentPhone, Date createTime, Date updateTime, @NotNull(message = "状态不能为空") Short status) {
        this.tbId = tbId;
        this.nickName = nickName;
        this.phoneNum = phoneNum;
        this.password = password;
        this.fullname = fullname;
        this.idCard = idCard;
        this.address = address;
        this.urgentContact = urgentContact;
        this.urgentPhone = urgentPhone;
        this.createTime = createTime;
        this.updateTime = updateTime;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUrgentContact() {
        return urgentContact;
    }

    public void setUrgentContact(String urgentContact) {
        this.urgentContact = urgentContact;
    }

    public String getUrgentPhone() {
        return urgentPhone;
    }

    public void setUrgentPhone(String urgentPhone) {
        this.urgentPhone = urgentPhone;
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

    @Override
    public String toString() {
        return "UserVO{" +
                "tbId=" + tbId +
                ", nickName='" + nickName + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", password='" + password + '\'' +
                ", fullname='" + fullname + '\'' +
                ", idCard='" + idCard + '\'' +
                ", address='" + address + '\'' +
                ", urgentContact='" + urgentContact + '\'' +
                ", urgentPhone='" + urgentPhone + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", status=" + status +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof User)) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(getTbId(), user.getTbId()) &&
                Objects.equals(getNickName(), user.getNickName()) &&
                Objects.equals(getPhoneNum(), user.getPhoneNum()) &&
                Objects.equals(getPassword(), user.getPassword()) &&
                Objects.equals(getFullname(), user.getFullname()) &&
                Objects.equals(getIdCard(), user.getIdCard()) &&
                Objects.equals(getAddress(), user.getAddress()) &&
                Objects.equals(getUrgentContact(), user.getUrgentContact()) &&
                Objects.equals(getUrgentPhone(), user.getUrgentPhone()) &&
                Objects.equals(getCreateTime(), user.getCreateTime()) &&
                Objects.equals(getUpdateTime(), user.getUpdateTime()) &&
                Objects.equals(getStatus(), user.getStatus());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTbId(), getNickName(), getPhoneNum(), getPassword(), getFullname(), getIdCard(), getAddress(), getUrgentContact(), getUrgentPhone(), getCreateTime(), getUpdateTime(), getStatus());
    }
}
