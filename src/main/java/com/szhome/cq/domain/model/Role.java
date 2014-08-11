package com.szhome.cq.domain.model;

import java.util.Date;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.springjdbc.annotation.BaseDomain;
import com.springjdbc.annotation.Entity;
import com.springjdbc.annotation.Id;
import com.springjdbc.annotation.Table;
import com.springjdbc.annotation.Transient;
import com.szhome.cq.utils.DateUtils;

@Scope("prototype")
@Component
@Entity
@Table(name = "T_ROLE")
public class Role extends BaseDomain<Role> {
	@Id
	private String roleid; //角色ID
	private String rolename; //角色名
	private String parentid; //上一级角色(注意只允许进一层继承)
	private String effectflag; //有效标志 1有效 0无效
	private String operatorcode; //创建人
	private Date createdate; //创建日期
	@Transient
	private String createdatestr; //创建日期str	
	private String attribute; //角色属性	 0正常,1临时角色(转授权)，注意临时角色不显示在角色维护中
	private Date begintime; //生效时间
	@Transient
	private String begintimestr; //生效时间
	private Date endtime; //终止时间
	@Transient
	private String endtimestr; //终止时间
	private String keepflag; //固定角色标志 1固定(用于管理员，不可变更) ，0可变(正常角色)
	private String remark; //备注	

	public Role(){
		super();
		this.t = Role.class;
	}

	public Role(String roleid){
		super();
		this.roleid = roleid;
	}

	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public void setParentid(String parentid) {
		this.parentid = parentid;
	}

	public void setEffectflag(String effectflag) {
		this.effectflag = effectflag;
	}

	public void setOperatorcode(String operatorcode) {
		this.operatorcode = operatorcode;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}

	public void setBegintime(Date begintime) {
		this.begintime = begintime;
	}

	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}

	public void setKeepflag(String keepflag) {
		this.keepflag = keepflag;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRoleid() {
		return roleid;
	}

	public String getRolename() {
		return rolename;
	}

	public String getParentid() {
		return parentid;
	}

	public String getEffectflag() {
		return effectflag;
	}

	public String getOperatorcode() {
		return operatorcode;
	}

	public Date getCreatedate() {
		return createdate;
	}

	public String getAttribute() {
		return attribute;
	}

	public Date getBegintime() {
		return begintime;
	}

	public Date getEndtime() {
		return endtime;
	}

	public String getKeepflag() {
		return keepflag;
	}

	public String getRemark() {
		return remark;
	}

	public String getCreatedatestr() {
		return createdatestr;
	}

	public void setCreatedatestr(String createdatestr) {
		this.createdatestr = createdatestr;
	}

	public String getBegintimestr() {
		return begintimestr;
	}

	public void setBegintimestr(String begintimestr) {
		this.begintimestr = begintimestr;
	}

	public String getEndtimestr() {
		return endtimestr;
	}

	public void setEndtimestr(String endtimestr) {
		this.endtimestr = endtimestr;
	}

}
