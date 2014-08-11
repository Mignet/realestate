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
@Table(name = "CFIG_BUS_MATTER")
public class BusMatter extends BaseDomain<BusMatter> {
	@Id
	private String id;
	private String name;
	private String bus_type_id;
	private String parent_id;
	private String proc_id;
	private String pro_type;
	private String sort;

	public BusMatter(){
		super();
		this.t = BusMatter.class;
	}

	public BusMatter(String id){
		super();
		this.id = id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setBus_type_id(String bus_type_id) {
		this.bus_type_id = bus_type_id;
	}

	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
	}

	public void setProc_id(String proc_id) {
		this.proc_id = proc_id;
	}

	public void setPro_type(String pro_type) {
		this.pro_type = pro_type;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getBus_type_id() {
		return bus_type_id;
	}

	public String getParent_id() {
		return parent_id;
	}

	public String getProc_id() {
		return proc_id;
	}

	public String getPro_type() {
		return pro_type;
	}

	public String getSort() {
		return sort;
	}

}