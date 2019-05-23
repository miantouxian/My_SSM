package cn.su.pojo;

import java.util.Date;

public class Consumlog extends BaseDomain{
    private Integer id;

    private String consumname;

    private Date consumdate;

    private String csmoney;

    private String remark;

    private String consumtype;

    private String remainder;

    private String type;

    private String imghref;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getConsumname() {
        return consumname;
    }

    public void setConsumname(String consumname) {
        this.consumname = consumname;
    }

    public Date getConsumdate() {
        return consumdate;
    }

    public void setConsumdate(Date consumdate) {
        this.consumdate = consumdate;
    }

    public String getCsmoney() {
        return csmoney;
    }

    public void setCsmoney(String csmoney) {
        this.csmoney = csmoney;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getConsumtype() {
        return consumtype;
    }

    public void setConsumtype(String consumtype) {
        this.consumtype = consumtype;
    }

    public String getRemainder() {
        return remainder;
    }

    public void setRemainder(String remainder) {
        this.remainder = remainder;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImghref() {
        return imghref;
    }

    public void setImghref(String imghref) {
        this.imghref = imghref;
    }
}