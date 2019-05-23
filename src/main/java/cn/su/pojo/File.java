package cn.su.pojo;

import java.util.Date;
import java.util.List;

public class File {
    private Integer id;

    private String filename;

    private Date createtime;

    private String createman;

    private String description;

    private String filehref;

    private Integer amount;

    private List<String> download;

    private User user;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getCreateman() {
        return createman;
    }

    public void setCreateman(String createman) {
        this.createman = createman;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFilehref() {
        return filehref;
    }

    public void setFilehref(String filehref) {
        this.filehref = filehref;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public List<String> getDownload() {
        return download;
    }

    public void setDownload(List<String> download) {
        this.download = download;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}