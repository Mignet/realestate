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
@Table(name = "bd_house_ext")
public class Bd_house_ext extends BaseDomain<Bd_house_ext> {
	@Id
	private String ho_id;
	private String loc_canton;
	private String house_decoration;
	private Date const_enddate;
	private String estate_state;
	private String forwsale_owner;
	private String owner_name;
	private String stat_type;
	private String sale_flag;
	private String update_flag;
	private Date update_date;
	private String zzf_flag;
	private String zzf_ratio;
	private String cert_handle_comment;
	private String site_key;

	public Bd_house_ext(){
		super();
		this.t = Bd_house_ext.class;
	}

	public Bd_house_ext(String ho_id){
		super();
		this.ho_id = ho_id;
	}

	public void setHo_id(String ho_id) {
		this.ho_id = ho_id;
	}

	public void setLoc_canton(String loc_canton) {
		this.loc_canton = loc_canton;
	}

	public void setHouse_decoration(String house_decoration) {
		this.house_decoration = house_decoration;
	}

	public void setConst_enddate(Date const_enddate) {
		this.const_enddate = const_enddate;
	}

	public void setEstate_state(String estate_state) {
		this.estate_state = estate_state;
	}

	public void setForwsale_owner(String forwsale_owner) {
		this.forwsale_owner = forwsale_owner;
	}

	public void setOwner_name(String owner_name) {
		this.owner_name = owner_name;
	}

	public void setStat_type(String stat_type) {
		this.stat_type = stat_type;
	}

	public void setSale_flag(String sale_flag) {
		this.sale_flag = sale_flag;
	}

	public void setUpdate_flag(String update_flag) {
		this.update_flag = update_flag;
	}

	public void setUpdate_date(Date update_date) {
		this.update_date = update_date;
	}

	public void setZzf_flag(String zzf_flag) {
		this.zzf_flag = zzf_flag;
	}

	public void setZzf_ratio(String zzf_ratio) {
		this.zzf_ratio = zzf_ratio;
	}

	public void setCert_handle_comment(String cert_handle_comment) {
		this.cert_handle_comment = cert_handle_comment;
	}

	public void setSite_key(String site_key) {
		this.site_key = site_key;
	}

	public String getHo_id() {
		return ho_id;
	}

	public String getLoc_canton() {
		return loc_canton;
	}

	public String getHouse_decoration() {
		return house_decoration;
	}

	public Date getConst_enddate() {
		return const_enddate;
	}

	public String getEstate_state() {
		return estate_state;
	}

	public String getForwsale_owner() {
		return forwsale_owner;
	}

	public String getOwner_name() {
		return owner_name;
	}

	public String getStat_type() {
		return stat_type;
	}

	public String getSale_flag() {
		return sale_flag;
	}

	public String getUpdate_flag() {
		return update_flag;
	}

	public Date getUpdate_date() {
		return update_date;
	}

	public String getZzf_flag() {
		return zzf_flag;
	}

	public String getZzf_ratio() {
		return zzf_ratio;
	}

	public String getCert_handle_comment() {
		return cert_handle_comment;
	}

	public String getSite_key() {
		return site_key;
	}

}