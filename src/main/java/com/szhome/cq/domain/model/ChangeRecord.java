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
@Table(name = "BUS_CHANGE_RECORD")
public class ChangeRecord extends BaseDomain<ChangeRecord> {
	@Id
	private String id;
	private String old_data;
	private String new_data;
	private String change_code;
	private String bus_id;
	private Date change_date;
	private String b_deleteflag;

	public ChangeRecord(){
		super();
		this.t = ChangeRecord.class;
	}

	public ChangeRecord(String id){
		super();
		this.id = id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setOld_data(String old_data) {
		this.old_data = old_data;
	}

	public void setNew_data(String new_data) {
		this.new_data = new_data;
	}

	public void setChange_code(String change_code) {
		this.change_code = change_code;
	}

	public void setBus_id(String bus_id) {
		this.bus_id = bus_id;
	}

	public void setChange_date(Date change_date) {
		this.change_date = change_date;
	}

	public void setB_deleteflag(String b_deleteflag) {
		this.b_deleteflag = b_deleteflag;
	}

	public String getId() {
		return id;
	}

	public String getOld_data() {
		return old_data;
	}

	public String getNew_data() {
		return new_data;
	}

	public String getChange_code() {
		return change_code;
	}

	public String getBus_id() {
		return bus_id;
	}

	public Date getChange_date() {
		return change_date;
	}

	public String getB_deleteflag() {
		return b_deleteflag;
	}

}
