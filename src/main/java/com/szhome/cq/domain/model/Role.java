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
	private String roleid; //��ɫID
	private String rolename; //��ɫ��
	private String parentid; //��һ����ɫ(ע��ֻ�����һ��̳�)
	private String effectflag; //��Ч��־ 1��Ч 0��Ч
	private String operatorcode; //������
	private Date createdate; //��������
	@Transient
	private String createdatestr; //��������str	
	private String attribute; //��ɫ����	 0����,1��ʱ��ɫ(ת��Ȩ)��ע����ʱ��ɫ����ʾ�ڽ�ɫά����
	private Date begintime; //��Чʱ��
	@Transient
	private String begintimestr; //��Чʱ��
	private Date endtime; //��ֹʱ��
	@Transient
	private String endtimestr; //��ֹʱ��
	private String keepflag; //�̶���ɫ��־ 1�̶�(���ڹ���Ա�����ɱ��) ��0�ɱ�(������ɫ)
	private String remark; //��ע	

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
