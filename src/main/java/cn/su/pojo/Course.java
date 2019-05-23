package cn.su.pojo;

import java.util.Date;

public class Course extends BaseDomain{
    private Integer id;

    private String coursename;

    private String coursetype;

    private Date coursedate;

    private Integer coursebord;

    private Integer coursecount;

    private Integer coursesy;

    private Integer coursepeople;

    private String cotein;

    private String courseteacherid;

    private Integer jgchoose;

    private String[] dellist;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCoursename() {
        return coursename;
    }

    public void setCoursename(String coursename) {
        this.coursename = coursename;
    }

    public String getCoursetype() {
        return coursetype;
    }

    public void setCoursetype(String coursetype) {
        this.coursetype = coursetype;
    }

    public Date getCoursedate() {
        return coursedate;
    }

    public void setCoursedate(Date coursedate) {
        this.coursedate = coursedate;
    }

    public Integer getCoursebord() {
        return coursebord;
    }

    public void setCoursebord(Integer coursebord) {
        this.coursebord = coursebord;
    }

    public Integer getCoursecount() {
        return coursecount;
    }

    public void setCoursecount(Integer coursecount) {
        this.coursecount = coursecount;
    }

    public Integer getCoursesy() {
        return coursesy;
    }

    public void setCoursesy(Integer coursesy) {
        this.coursesy = coursesy;
    }

    public Integer getCoursepeople() {
        return coursepeople;
    }

    public void setCoursepeople(Integer coursepeople) {
        this.coursepeople = coursepeople;
    }

    public String getCotein() {
        return cotein;
    }

    public void setCotein(String cotein) {
        this.cotein = cotein;
    }

    public String getCourseteacherid() {
        return courseteacherid;
    }

    public void setCourseteacherid(String courseteacherid) {
        this.courseteacherid = courseteacherid;
    }

    public Integer getJgchoose() {
        return jgchoose;
    }

    public void setJgchoose(Integer jgchoose) {
        this.jgchoose = jgchoose;
    }

    public String[] getDellist() {
        return dellist;
    }

    public void setDellist(String[] dellist) {
        this.dellist = dellist;
    }
}