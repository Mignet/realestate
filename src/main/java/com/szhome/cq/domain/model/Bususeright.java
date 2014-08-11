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
@Table(name = "BUS_USERIGHT_REG")
public class Bususeright extends BaseDomain<Bususeright> {
	private Date start_date;
	@Id
	private String useright_reg_id;
	private String bus_id;
	private String b_deleteflag;
	private String land_use;
	private String useright_type;
	private String use_limit;
	private Date end_date;
	private double get_price;
	private String excursus;
	private String reason;

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
	public Bususeright(){
		super();
		this.t = Bususeright.class;
	}

	public Bususeright(String useright_reg_id){
		super();
		this.useright_reg_id = useright_reg_id;
	}

	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}

	public void setUseright_reg_id(String useright_reg_id) {
		this.useright_reg_id = useright_reg_id;
	}

	public void setBus_id(String bus_id) {
		this.bus_id = bus_id;
	}

	public void setB_deleteflag(String b_deleteflag) {
		this.b_deleteflag = b_deleteflag;
	}


	public void setLand_use(String land_use) {
		this.land_use = land_use;
	}


	public void setUseright_type(String useright_type) {
		this.useright_type = useright_type;
	}

	public void setUse_limit(String use_limit) {
		this.use_limit = use_limit;
	}

	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}

	public void setGet_price(double get_price) {
		this.get_price = get_price;
	}

	public void setExcursus(String excursus) {
		this.excursus = excursus;
	}

	public Date getStart_date() {
		return start_date;
	}

	public String getUseright_reg_id() {
		return useright_reg_id;
	}

	public String getBus_id() {
		return bus_id;
	}

	public String getB_deleteflag() {
		return b_deleteflag;
	}

	public String getLand_use() {
		return land_use;
	}


	public String getUseright_type() {
		return useright_type;
	}

	public String getUse_limit() {
		return use_limit;
	}

	public Date getEnd_date() {
		return end_date;
	}

	public double getGet_price() {
		return get_price;
	}

	public String getExcursus() {
		return excursus;
	}

}
