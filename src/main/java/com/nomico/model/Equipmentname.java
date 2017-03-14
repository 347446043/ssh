package com.nomico.model;

import java.io.Serializable;
import java.util.Date;

public class Equipmentname  implements Serializable{
	
	
    /**
	 * 
	 */
	private static final long serialVersionUID = -196278116945570803L;

	private String id;

    private String name;

    private Date createtime;

    private String createip;

    private Date overtime;

    private String overip;

    private Integer state;

    private String userid;
    private String  remark;
    
    public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getCreateip() {
        return createip;
    }

    public void setCreateip(String createip) {
        this.createip = createip == null ? null : createip.trim();
    }

    public Date getOvertime() {
        return overtime;
    }

    public void setOvertime(Date overtime) {
        this.overtime = overtime;
    }

    public String getOverip() {
        return overip;
    }

    public void setOverip(String overip) {
        this.overip = overip == null ? null : overip.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }
}