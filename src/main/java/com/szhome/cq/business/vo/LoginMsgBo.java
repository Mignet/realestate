package com.szhome.cq.business.vo;

import java.util.Date;
import java.util.List;

/**
 * 登录信息类
 * 保存登录信息规则信息
 * @author Mignet
 */
public class LoginMsgBo {
	private String operatorcode;//操作员代码
	private String operatorname;//操作名称
	private String channelid;//操作员选择的频道
	private String ipAddress;//登录的IP地址
	private Date loginTime;//登录时间
//	private Serviceprovider serviceprovider;//运营商对象
//	private List<Menu> listMenuAll;//操作员有权访问的菜单列表
	private String notePadMessage; //预约提醒信息
//	private Organise organise; //组织信息
	private String businesstype;//业务类型
	private String developmanid; //该操作员对应的发展人代码
	private String workcode; //工号
	private boolean vipflag =false; //vip操作类型
	private String developmanname;//发展人姓名
	private String developmanorganiseid;//发展人部门
	private String developmanorganisename;//发展人部门名字
	private String oldoperatorcode;//暂存呼叫中心传入操作员,因为原operatorcode被替换成呼叫中心工号
	private boolean leader = false;
	public boolean isVipflag() {
		return vipflag;
	}

	public void setVipflag(boolean vipflag) {
		this.vipflag = vipflag;
	}

	public String getDevelopmanid() {
		return developmanid;
	}

	public void setDevelopmanid(String developmanid) {
		this.developmanid = developmanid;
	}

	public String getWorkcode() {
		return workcode;
	}

	public void setWorkcode(String workcode) {
		this.workcode = workcode;
	}

	public String getOperatorname() {
		return this.operatorname;
	}

	public void setOperatorname(String operatorname) {
		this.operatorname = operatorname;
	}

	public String getNotePadMessage() {
		return this.notePadMessage;
	}

	public void setNotePadMessage(String notePadMessage) {
		this.notePadMessage = notePadMessage;
	}

	public String getOperatorcode() {
		return this.operatorcode;
	}

	public void setOperatorcode(String operatorcode) {
		this.operatorcode = operatorcode;
	}

	public String getIpAddress() {
		return this.ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public Date getLoginTime() {
		return this.loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	public String getBusinesstype() {
		return this.businesstype;
	}

	public void setBusinesstype(String businesstype) {
		this.businesstype = businesstype;
	}

	public String getDevelopmanname() {
		return developmanname;
	}

	public String getDevelopmanorganiseid() {
		return developmanorganiseid;
	}

	public String getDevelopmanorganisename() {
		return developmanorganisename;
	}

	public void setDevelopmanname(String developmanname) {
		this.developmanname = developmanname;
	}

	public void setDevelopmanorganiseid(String developmanorganiseid) {
		this.developmanorganiseid = developmanorganiseid;
	}

	public void setDevelopmanorganisename(String developmanorganisename) {
		this.developmanorganisename = developmanorganisename;
	}

	public String getOldoperatorcode() {
		return oldoperatorcode;
	}

	public void setOldoperatorcode(String oldoperatorcode) {
		this.oldoperatorcode = oldoperatorcode;
	}

	public boolean isLeader() {
		return leader;
	}

	public void setLeader(boolean leader) {
		this.leader = leader;
	}

	public String getChannelid() {
		return channelid;
	}

	public void setChannelid(String channelid) {
		this.channelid = channelid;
	}

}

