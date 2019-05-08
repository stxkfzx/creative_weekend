package xin.stxkfzx.weekend.vo.activity;

import java.util.StringJoiner;

/**
 * 获取活动列表的条件
 *
 * @author fmy
 * @date 2019-04-30 11:30
 */
public class ActivityConditionParam {
    private Short range;
    private Boolean myActivity = false;
    private Integer page = 0;
    private Integer pageSize = 10;


    @Override
    public String toString() {
        return new StringJoiner(", ", ActivityConditionParam.class.getSimpleName() + "[", "]")
                .add("range=" + range)
                .add("myActivity=" + myActivity)
                .add("page=" + page)
                .add("pageSize=" + pageSize)
                .toString();
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Boolean getMyActivity() {
        return myActivity;
    }

    public void setMyActivity(Boolean myActivity) {
        this.myActivity = myActivity;
    }

    public Short getRange() {
        return range;
    }

    public void setRange(Short range) {
        this.range = range;
    }

}
