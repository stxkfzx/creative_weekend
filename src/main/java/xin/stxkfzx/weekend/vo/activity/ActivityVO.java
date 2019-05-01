package xin.stxkfzx.weekend.vo.activity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.List;
import java.util.StringJoiner;

/**
 *
 * @author fmy
 * @date 2019-04-29 19:17
 */
public class ActivityVO {
    @JsonProperty("activityId")
    private Integer tbId;
    private String title;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date startTime;
    private String imgUrl;
    private List<ActivityTagVO> tagList;

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

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public List<ActivityTagVO> getTagList() {
        return tagList;
    }

    public void setTagList(List<ActivityTagVO> tagList) {
        this.tagList = tagList;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ActivityVO.class.getSimpleName() + "[", "]")
                .add("tbId=" + tbId)
                .add("title='" + title + "'")
                .add("startTime=" + startTime)
                .add("imgUrl='" + imgUrl + "'")
                .add("tagList=" + tagList)
                .toString();
    }
}
