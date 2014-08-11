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
@Table(name = "CFIG_BUS_TYPE")
public class BusType extends BaseDomain<BusType> {
	@Id
	private String bus_type_id;
	private String b_deleteflag;
	private String bus_name;
	private String proc_id;
	private String parent_id;

	public BusType(){
		super();
		this.t = BusType.class;
	}

	public BusType(String bus_type_id){
		super();
		this.bus_type_id = bus_type_id;
	}

	public void setBus_type_id(String bus_type_id) {
		this.bus_type_id = bus_type_id;
	}

	public void setB_deleteflag(String b_deleteflag) {
		this.b_deleteflag = b_deleteflag;
	}

	public void setBus_name(String bus_name) {
		this.bus_name = bus_name;
	}

	public void setProc_id(String proc_id) {
		this.proc_id = proc_id;
	}

	public String getBus_type_id() {
		return bus_type_id;
	}

	public String getB_deleteflag() {
		return b_deleteflag;
	}

	public String getBus_name() {
		return bus_name;
	}

	public String getProc_id() {
		return proc_id;
	}

	public String getParent_id() {
		return parent_id;
	}

	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
	}

}
