package com.szhome.cq.domain.model;

import java.util.Date;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.springjdbc.annotation.BaseDomain;
import com.springjdbc.annotation.Entity;
import com.springjdbc.annotation.Id;
import com.springjdbc.annotation.Table;

@Scope("prototype")
@Component
@Entity
@Table(name = "T_ROLE_R_USER")
public class RoleRUser extends BaseDomain<RoleRUser> {
	@Id
	private String userid;
	@Id
	private String roleid;
	private String creator;
	private Date createdate;

	public RoleRUser(){
		super();
		this.t = RoleRUser.class;
	}

	public RoleRUser(String userid){
		super();
		this.userid = userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public String getUserid() {
		return userid;
	}

	public String getRoleid() {
		return roleid;
	}

	public String getCreator() {
		return creator;
	}

	public Date getCreatedate() {
		return createdate;
	}

}
