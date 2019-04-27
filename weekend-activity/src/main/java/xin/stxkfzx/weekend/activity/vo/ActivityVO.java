package xin.stxkfzx.weekend.activity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.StringJoiner;

/**
 * 活动VO
 *
 * @author fmy
 * @date 2019-04-18 14:16
 */
public class ActivityVO {

    @NotBlank
    private String title;

    @NotBlank
    private String description;

    @NotNull
    private Short range;

    /**
     * 坐标
     */
    @NotBlank
    private String coordinate;

    @NotNull
    private Integer maxCount;

    private Integer money;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date startTime;

    @Override
    public String toString() {
        return new StringJoiner(", ", ActivityVO.class.getSimpleName() + "[", "]")
                .add("title='" + title + "'")
                .add("description='" + description + "'")
                .add("range=" + range)
                .add("coordinate='" + coordinate + "'")
                .add("maxCount=" + maxCount)
                .add("money=" + money)
                .add("startTime=" + startTime)
                .toString();
    }

    public ActivityVO() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Short getRange() {
        return range;
    }

    public void setRange(Short range) {
        this.range = range;
    }

    public String getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(String coordinate) {
        this.coordinate = coordinate;
    }

    public Integer getMaxCount() {
        return maxCount;
    }

    public void setMaxCount(Integer maxCount) {
        this.maxCount = maxCount;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

}
