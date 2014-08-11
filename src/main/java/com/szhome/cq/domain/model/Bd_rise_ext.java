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
@Table(name = "bd_rise_ext")
public class Bd_rise_ext extends BaseDomain<Bd_rise_ext> {
	@Id
	private String rise_id;
	private String prj_id;
	private String project_name;
	private String loc_canton;
	private String division;
	private String location;
	private double constcost_in_rmb;
	private double constcost_in_hkd;
	private double shared_lu_cost;
	private String estate_state;
	private String owner_name;
	private String loc_canton1;
	private String loc_canton2;
	private String noncons_land;
	private String stat_type;
	private String update_flag;
	private Date update_date;
	private String no_insert_flag;
	private String site_key;

	public Bd_rise_ext(){
		super();
		this.t = Bd_rise_ext.class;
	}

	public Bd_rise_ext(String rise_id){
		super();
		this.rise_id = rise_id;
	}

	public void setRise_id(String rise_id) {
		this.rise_id = rise_id;
	}

	public void setPrj_id(String prj_id) {
		this.prj_id = prj_id;
	}

	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}

	public void setLoc_canton(String loc_canton) {
		this.loc_canton = loc_canton;
	}

	public void setDivision(String division) {
		this.division = division;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public void setConstcost_in_rmb(double constcost_in_rmb) {
		this.constcost_in_rmb = constcost_in_rmb;
	}

	public void setConstcost_in_hkd(double constcost_in_hkd) {
		this.constcost_in_hkd = constcost_in_hkd;
	}

	public void setShared_lu_cost(double shared_lu_cost) {
		this.shared_lu_cost = shared_lu_cost;
	}

	public void setEstate_state(String estate_state) {
		this.estate_state = estate_state;
	}

	public void setOwner_name(String owner_name) {
		this.owner_name = owner_name;
	}

	public void setLoc_canton1(String loc_canton1) {
		this.loc_canton1 = loc_canton1;
	}

	public void setLoc_canton2(String loc_canton2) {
		this.loc_canton2 = loc_canton2;
	}

	public void setNoncons_land(String noncons_land) {
		this.noncons_land = noncons_land;
	}

	public void setStat_type(String stat_type) {
		this.stat_type = stat_type;
	}

	public void setUpdate_flag(String update_flag) {
		this.update_flag = update_flag;
	}

	public void setUpdate_date(Date update_date) {
		this.update_date = update_date;
	}

	public void setNo_insert_flag(String no_insert_flag) {
		this.no_insert_flag = no_insert_flag;
	}

	public void setSite_key(String site_key) {
		this.site_key = site_key;
	}

	public String getRise_id() {
		return rise_id;
	}

	public String getPrj_id() {
		return prj_id;
	}

	public String getProject_name() {
		return project_name;
	}

	public String getLoc_canton() {
		return loc_canton;
	}

	public String getDivision() {
		return division;
	}

	public String getLocation() {
		return location;
	}

	public double getConstcost_in_rmb() {
		return constcost_in_rmb;
	}

	public double getConstcost_in_hkd() {
		return constcost_in_hkd;
	}

	public double getShared_lu_cost() {
		return shared_lu_cost;
	}

	public String getEstate_state() {
		return estate_state;
	}

	public String getOwner_name() {
		return owner_name;
	}

	public String getLoc_canton1() {
		return loc_canton1;
	}

	public String getLoc_canton2() {
		return loc_canton2;
	}

	public String getNoncons_land() {
		return noncons_land;
	}

	public String getStat_type() {
		return stat_type;
	}

	public String getUpdate_flag() {
		return update_flag;
	}

	public Date getUpdate_date() {
		return update_date;
	}

	public String getNo_insert_flag() {
		return no_insert_flag;
	}

	public String getSite_key() {
		return site_key;
	}

}