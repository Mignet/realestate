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
@Table(name = "BUS_DISTRAIN_REG")
public class BusDistrain extends BaseDomain<BusDistrain> {
	@Id
	private String dis_reg_id;
	private String bus_id;
	private String b_deleteflag;
	private String dis_no;
	private String dis_off;
	private String lim_holder;
	private Date start_date;
	private Date end_date;
	private Date dis_date;
	private String law_doc;
	private String dis_limit;
	private String remark;
	private String service_name;
	private String dis_per_tel;
	private Date service_date;
	private String workid;
	private String pre_con_no;
	private String dis_range;
	public String getPre_con_no() {
		return pre_con_no;
	}

	public void setPre_con_no(String pre_con_no) {
		this.pre_con_no = pre_con_no;
	}

	public String getDis_range() {
		return dis_range;
	}

	public void setDis_range(String dis_range) {
		this.dis_range = dis_range;
	}

	public String getDis_type() {
		return dis_type;
	}

	public void setDis_type(String dis_type) {
		this.dis_type = dis_type;
	}

	private String dis_type;

	public BusDistrain(){
		super();
		this.t = BusDistrain.class;
	}

	public BusDistrain(String dis_reg_id){
		super();
		this.dis_reg_id = dis_reg_id;
	}

	public void setDis_reg_id(String dis_reg_id) {
		this.dis_reg_id = dis_reg_id;
	}

	public void setBus_id(String bus_id) {
		this.bus_id = bus_id;
	}

	public void setB_deleteflag(String b_deleteflag) {
		this.b_deleteflag = b_deleteflag;
	}

	public void setDis_no(String dis_no) {
		this.dis_no = dis_no;
	}

	public void setDis_off(String dis_off) {
		this.dis_off = dis_off;
	}

	public void setLim_holder(String lim_holder) {
		this.lim_holder = lim_holder;
	}

	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}

	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}

	public void setDis_date(Date dis_date) {
		this.dis_date = dis_date;
	}

	public void setLaw_doc(String law_doc) {
		this.law_doc = law_doc;
	}

	public void setDis_limit(String dis_limit) {
		this.dis_limit = dis_limit;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public void setService_name(String service_name) {
		this.service_name = service_name;
	}

	public void setDis_per_tel(String dis_per_tel) {
		this.dis_per_tel = dis_per_tel;
	}

	public void setService_date(Date service_date) {
		this.service_date = service_date;
	}

	public void setWorkid(String workid) {
		this.workid = workid;
	}

	public String getDis_reg_id() {
		return dis_reg_id;
	}

	public String getBus_id() {
		return bus_id;
	}

	public String getB_deleteflag() {
		return b_deleteflag;
	}

	public String getDis_no() {
		return dis_no;
	}

	public String getDis_off() {
		return dis_off;
	}

	public String getLim_holder() {
		return lim_holder;
	}

	public Date getStart_date() {
		return start_date;
	}

	public Date getEnd_date() {
		return end_date;
	}

	public Date getDis_date() {
		return dis_date;
	}

	public String getLaw_doc() {
		return law_doc;
	}

	public String getDis_limit() {
		return dis_limit;
	}

	public String getRemark() {
		return remark;
	}

	public String getService_name() {
		return service_name;
	}

	public String getDis_per_tel() {
		return dis_per_tel;
	}

	public Date getService_date() {
		return service_date;
	}

	public String getWorkid() {
		return workid;
	}

}
