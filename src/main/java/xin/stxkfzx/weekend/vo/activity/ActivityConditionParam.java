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

    public Short getRange() {
        return range;
    }

    public void setRange(Short range) {
        this.range = range;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ActivityConditionParam.class.getSimpleName() + "[", "]")
                .add("range=" + range)
                .toString();
    }
}
