package com.szhome.cq.business.vo;

import java.util.Date;
import java.util.List;

/**
 * ��¼��Ϣ��
 * �����¼��Ϣ������Ϣ
 * @author Mignet
 */
public class LoginMsgBo {
	private String operatorcode;//����Ա����
	private String operatorname;//��������
	private String channelid;//����Աѡ���Ƶ��
	private String ipAddress;//��¼��IP��ַ
	private Date loginTime;//��¼ʱ��
//	private Serviceprovider serviceprovider;//��Ӫ�̶���
//	private List<Menu> listMenuAll;//����Ա��Ȩ���ʵĲ˵��б�
	private String notePadMessage; //ԤԼ������Ϣ
//	private Organise organise; //��֯��Ϣ
	private String businesstype;//ҵ������
	private String developmanid; //�ò���Ա��Ӧ�ķ�չ�˴���
	private String workcode; //����
	private boolean vipflag =false; //vip��������
	private String developmanname;//��չ������
	private String developmanorganiseid;//��չ�˲���
	private String developmanorganisename;//��չ�˲�������
	private String oldoperatorcode;//�ݴ�������Ĵ������Ա,��Ϊԭoperatorcode���滻�ɺ������Ĺ���
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

