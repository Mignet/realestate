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
@Table(name = "bd_par_prj_rel")
public class Bd_par_prj_rel extends BaseDomain<Bd_par_prj_rel> {
	@Id
	private String par_prj_id;
	private String par_id;
	private String prj_id;

	public Bd_par_prj_rel(){
		super();
		this.t = Bd_par_prj_rel.class;
	}

	public Bd_par_prj_rel(String par_prj_id){
		super();
		this.par_prj_id = par_prj_id;
	}

	public void setPar_prj_id(String par_prj_id) {
		this.par_prj_id = par_prj_id;
	}

	public void setPar_id(String par_id) {
		this.par_id = par_id;
	}

	public void setPrj_id(String prj_id) {
		this.prj_id = prj_id;
	}

	public String getPar_prj_id() {
		return par_prj_id;
	}

	public String getPar_id() {
		return par_id;
	}

	public String getPrj_id() {
		return prj_id;
	}

}