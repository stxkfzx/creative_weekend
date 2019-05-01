package xin.stxkfzx.weekend.entity;

import java.util.List;

/**
 * 分页返回结果集
 * @author VicterTian
 * @version V1.0
 * @date 2019/4/30
 */
public class PageResult<T> {
    /**
     * 总条数
     */
    private long total;
    /**
     * 总页数
     */
    private long totalPage;
    /**
     * 当前页数据
     */
    private List<T> items;

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public long getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(long totalPage) {
        this.totalPage = totalPage;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public PageResult() {
    }
    public PageResult(long total, List<T> items) {
        this.total = total;
        this.items = items;
    }

    public PageResult(long total, long totalPage, List<T> items) {
        this.total = total;
        this.totalPage = totalPage;
        this.items = items;
    }
}
