package cn.su.pojo;


import java.util.Date;

public class ConsumParam extends Consumlog{

    private int currPage;//当前页数
    private int start;
    private int size;
    private String[] delid;
    private Date consumdate;

    public int getCurrPage() {
        return currPage;
    }

    public void setCurrPage(int currPage) {
        this.currPage = currPage;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }


    public String[] getDelid() {
        return delid;
    }

    public void setDelid(String[] delid) {
        this.delid = delid;
    }

    @Override
    public Date getConsumdate() {
        return consumdate;
    }

    @Override
    public void setConsumdate(Date consumdate) {
        this.consumdate = consumdate;
    }
}
