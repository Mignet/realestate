/**
 * Project Name:dxtx_re
 * File Name:Audit.java
 * Package Name:com.szhome.cq.domain.model
 * Date:2013-12-23ÏÂÎç7:43:30
 * Copyright (c) 2013, chenzhou1025@126.com All Rights Reserved.
 *
*/

package com.szhome.cq.business.vo;
/**
 * ClassName:Audit <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2013-12-23 ÏÂÎç7:43:30 <br/>
 * @author   xuzz
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class AuditVo {
	private String  buildingid;
	private String houseid;
	private String parcelid;
	private String  buildname;
	private String buildnum;
	private String code;
	private String buildcode;
	private String houselocation;
	private String parcelcode;
	private String usage;
	public String getUsage() {
		return usage;
	}

	public void setUsage(String usage) {
		this.usage = usage;
	}
	private String proname;
	private String reg_type;
	private double buildarea;
	private double ftbuildarea;
	private String parcelarea;
	private String houldername;
	private String location;
	private String houldercode;
	private String reg_typename;
	public String getReg_typename() {
		return reg_typename;
	}

	public void setReg_typename(String reg_typename) {
		this.reg_typename = reg_typename;
	}

	public String getBuildname() {
		return buildname;
	}
	
	public String getReg_type() {
		return reg_type;
	}
	public void setReg_type(String reg_type) {
		this.reg_type = reg_type;
	}
	public void setBuildname(String buildname) {
		this.buildname = buildname;
	}
	public String getBuildnum() {
		return buildnum;
	}
	public void setBuildnum(String buildnum) {
		this.buildnum = buildnum;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code =code ;
	}
	public String getBuildcode() {
		return buildcode;
	}
	public void setBuildcode(String buildcode) {
		this.buildcode = buildcode;
	}
	public String getHouselocation() {
		return houselocation;
	}
	public void setHouselocation(String houselocation) {
		this.houselocation = houselocation;
	}
	public String getParcelcode() {
		return parcelcode;
	}
	public void setParcelcode(String parcelcode) {
		this.parcelcode = parcelcode;
	}
	public String getProname() {
		return proname;
	}
	public void setProname(String proname) {
		this.proname = proname;
	}
	public double getBuildarea() {
		return buildarea;
	}
	public void setBuildarea(double buildarea) {
		this.buildarea = buildarea;
	}
	public double getFtbuildarea() {
		return ftbuildarea;
	}
	public void setFtbuildarea(double ftbuildarea) {
		this.ftbuildarea = ftbuildarea;
	}
	public String getParcelarea() {
		return parcelarea;
	}
	public void setParcelarea(String parcelarea) {
		this.parcelarea = parcelarea;
	}
	public String getHouldername() {
		return houldername;
	}
	public void setHouldername(String houldername) {
		this.houldername = houldername;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getHouldercode() {
		return houldercode;
	}
	public void setHouldercode(String houldercode) {
		this.houldercode = houldercode;
	}
	public String getBuildingid() {
		return buildingid;
	}
	public void setBuildingid(String buildingid) {
		this.buildingid = buildingid;
	}
	public String getHouseid() {
		return houseid;
	}
	public void setHouseid(String houseid) {
		this.houseid = houseid;
	}
	public String getParcelid() {
		return parcelid;
	}
	public void setParcelid(String parcelid) {
		this.parcelid = parcelid;
	}
	

}


