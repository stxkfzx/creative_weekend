package xin.stxkfzx.weekend.vo.activity;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author fmy
 * @date 2019-04-29 19:32
 */
public class ActivityTagVO {
    private String content;
    @JsonProperty("tagId")
    private Integer tbId;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getTbId() {
        return tbId;
    }

    public void setTbId(Integer tbId) {
        this.tbId = tbId;
    }
}
