package com.szhome.cq.domain.model;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.springjdbc.annotation.BaseDomain;
import com.springjdbc.annotation.Entity;
import com.springjdbc.annotation.Id;
import com.springjdbc.annotation.Table;

@Scope("prototype")
@Component
@Entity
@Table(name = "bd_prj_bldg_rel")
public class Bd_prj_bldg_rel extends BaseDomain<Bd_prj_bldg_rel> {
	@Id
	private String prj_bldg_id;
	private String prj_id;
	private String bldg_id;

	public Bd_prj_bldg_rel(){
		super();
		this.t = Bd_prj_bldg_rel.class;
	}

	public Bd_prj_bldg_rel(String prj_bldg_id){
		super();
		this.prj_bldg_id = prj_bldg_id;
	}

	public void setPrj_bldg_id(String prj_bldg_id) {
		this.prj_bldg_id = prj_bldg_id;
	}

	public void setPrj_id(String prj_id) {
		this.prj_id = prj_id;
	}

	public void setBldg_id(String bldg_id) {
		this.bldg_id = bldg_id;
	}

	public String getPrj_bldg_id() {
		return prj_bldg_id;
	}

	public String getPrj_id() {
		return prj_id;
	}

	public String getBldg_id() {
		return bldg_id;
	}

}