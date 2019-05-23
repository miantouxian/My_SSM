package cn.su.pojo;

import java.util.Date;

public class Task {

    private User user;

    private Integer id;

    private String tasktitle;

    private String taskdetail;

    private String taskimg;

    private Date createtime;

    private String offeror;

    private String offeree;

    private String status;

    private Date finishtime;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTasktitle() {
        return tasktitle;
    }

    public void setTasktitle(String tasktitle) {
        this.tasktitle = tasktitle;
    }

    public String getTaskdetail() {
        return taskdetail;
    }

    public void setTaskdetail(String taskdetail) {
        this.taskdetail = taskdetail;
    }

    public String getTaskimg() {
        return taskimg;
    }

    public void setTaskimg(String taskimg) {
        this.taskimg = taskimg;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getOfferor() {
        return offeror;
    }

    public void setOfferor(String offeror) {
        this.offeror = offeror;
    }

    public String getOfferee() {
        return offeree;
    }

    public void setOfferee(String offeree) {
        this.offeree = offeree;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getFinishtime() {
        return finishtime;
    }

    public void setFinishtime(Date finishtime) {
        this.finishtime = finishtime;
    }
}