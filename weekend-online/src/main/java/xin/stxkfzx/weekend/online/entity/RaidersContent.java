package xin.stxkfzx.weekend.online.entity;

/**
 * 
 * 
 * @author fmy
 * @date 2019-04-10 22:09 
 */
public class RaidersContent {
    private Integer tbId;

    private String content;

    public Integer getTbId() {
        return tbId;
    }

    public void setTbId(Integer tbId) {
        this.tbId = tbId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", tbId=").append(tbId);
        sb.append(", content=").append(content);
        sb.append("]");
        return sb.toString();
    }
}