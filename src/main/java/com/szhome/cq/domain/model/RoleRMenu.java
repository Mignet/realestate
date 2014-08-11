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
@Table(name = "T_ROLE_R_MENU")
public class RoleRMenu extends BaseDomain<RoleRMenu> {
	@Id
	private String roleid;
	@Id
	private String menuid;
	private String creator;
	private Date createdate;

	public RoleRMenu(){
		super();
		this.t = RoleRMenu.class;
	}

	public RoleRMenu(String roleid){
		super();
		this.roleid = roleid;
	}

	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}

	public void setMenuid(String menuid) {
		this.menuid = menuid;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public String getRoleid() {
		return roleid;
	}

	public String getMenuid() {
		return menuid;
	}

	public String getCreator() {
		return creator;
	}

	public Date getCreatedate() {
		return createdate;
	}

}
