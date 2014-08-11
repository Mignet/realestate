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
@Table(name = "SPE_REPLENISH")
public class MaterialReplenish extends BaseDomain<MaterialReplenish> {
	@Id
	private String id;
	private String reg_code;
	private String repl_reson;
	private Date repl_date;

	public MaterialReplenish(){
		super();
		this.t = MaterialReplenish.class;
	}

	public MaterialReplenish(String id){
		super();
		this.id = id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setReg_code(String reg_code) {
		this.reg_code = reg_code;
	}

	public void setRepl_reson(String repl_reson) {
		this.repl_reson = repl_reson;
	}

	public void setRepl_date(Date repl_date) {
		this.repl_date = repl_date;
	}

	public String getId() {
		return id;
	}

	public String getReg_code() {
		return reg_code;
	}

	public String getRepl_reson() {
		return repl_reson;
	}

	public Date getRepl_date() {
		return repl_date;
	}

}

