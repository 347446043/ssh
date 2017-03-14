package com.nomico.model;

import java.io.Serializable;
import java.util.Date;

public class Equipment implements Serializable{
	
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 7008820821421362925L;

	private String id;

    private String equipmentnameid;

    private String ppxh;

    private String ccbh;

    private Integer state;

    private String remark;

    private Date checktime;

    private Date checkavltime;

    private String jdjg;

    private String jdzsh;

    private String file1;

    private String file2;

    private Date createtime;

    private String createip;

    private Date overtime;

    private String overip;

    private Integer shuliang;

    private Equipmentname equipmentname;
    
	public Equipmentname getEquipmentname() {
		return equipmentname;
	}

	public void setEquipmentname(Equipmentname equipmentname) {
		this.equipmentname = equipmentname;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getEquipmentnameid() {
        return equipmentnameid;
    }

    public void setEquipmentnameid(String equipmentnameid) {
        this.equipmentnameid = equipmentnameid == null ? null : equipmentnameid.trim();
    }

    public String getPpxh() {
        return ppxh;
    }

    public void setPpxh(String ppxh) {
        this.ppxh = ppxh == null ? null : ppxh.trim();
    }

    public String getCcbh() {
        return ccbh;
    }

    public void setCcbh(String ccbh) {
        this.ccbh = ccbh == null ? null : ccbh.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Date getChecktime() {
        return checktime;
    }

    public void setChecktime(Date checktime) {
        this.checktime = checktime;
    }

    public Date getCheckavltime() {
        return checkavltime;
    }

    public void setCheckavltime(Date checkavltime) {
        this.checkavltime = checkavltime;
    }

    public String getJdjg() {
        return jdjg;
    }

    public void setJdjg(String jdjg) {
        this.jdjg = jdjg == null ? null : jdjg.trim();
    }

    public String getJdzsh() {
        return jdzsh;
    }

    public void setJdzsh(String jdzsh) {
        this.jdzsh = jdzsh == null ? null : jdzsh.trim();
    }

    public String getFile1() {
        return file1;
    }

    public void setFile1(String file1) {
        this.file1 = file1 == null ? null : file1.trim();
    }

    public String getFile2() {
        return file2;
    }

    public void setFile2(String file2) {
        this.file2 = file2 == null ? null : file2.trim();
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

    public Integer getShuliang() {
        return shuliang;
    }

    public void setShuliang(Integer shuliang) {
        this.shuliang = shuliang;
    }
}