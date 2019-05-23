package cn.su.pojo;

import java.util.List;

public class PageBean<T> extends BaseDomain{
    private int currPage;//当前页数
    private int pageSize;//每页显示的记录数
    private int totalCount;//总记录数
    private int totalPage;//总页数
    private int previouspage;
    private int nextpage;
    private ConsumParam consumParam;//查询参数
    private List<T> lists;//每页的显示的数据

    public PageBean() {
        super();
    }

    public int getCurrPage() {
        return currPage;
    }

    public void setCurrPage(int currPage) {
        this.currPage = currPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getPreviouspage() {
        return previouspage;
    }

    public void setPreviouspage(int previouspage) {
        this.previouspage = previouspage;
    }

    public int getNextpage() {
        return nextpage;
    }

    public void setNextpage(int nextpage) {
        this.nextpage = nextpage;
    }

    public ConsumParam getConsumParam() {
        return consumParam;
    }

    public void setConsumParam(ConsumParam consumParam) {
        this.consumParam = consumParam;
    }

    public List<T> getLists() {
        return lists;
    }

    public void setLists(List<T> lists) {
        this.lists = lists;
    }

}
