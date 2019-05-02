package xin.stxkfzx.weekend.vo.activity;

import com.fasterxml.jackson.annotation.*;

import java.util.Date;

/**
 * 加入活动记录
 *
 * @author fmy
 * @date 2019-05-01 23:35
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JoinActivityRecordVO {
    @JsonProperty("recordId")
    private Integer tbId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date joinTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date exitTime;
    private Integer activityId;

    public Integer getTbId() {
        return tbId;
    }

    public void setTbId(Integer tbId) {
        this.tbId = tbId;
    }

    public Date getJoinTime() {
        return joinTime;
    }

    public void setJoinTime(Date joinTime) {
        this.joinTime = joinTime;
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public Date getExitTime() {
        return exitTime;
    }

    public void setExitTime(Date exitTime) {
        this.exitTime = exitTime;
    }
}
