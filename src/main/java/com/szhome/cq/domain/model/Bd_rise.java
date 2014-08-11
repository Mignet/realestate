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
@Table(name = "bd_rise")
public class Bd_rise extends BaseDomain<Bd_rise> {
	@Id
	private String rise_id;
	private String bldg_id;
	private String bldg_code_sz;
	private String bldg_code_gb;
	private String bldg_code_tmp;
	private String par_id;
	private String project_name;
	private String rise_no;
	private String bldg_name_no;
	private String street;
	private String par_no_subno;
	private String bldg_attr;
	private String bldg_type;
	private String bldg_usage;
	private String bldg_structure;
	private double bldg_floors;
	private double unit_suits;
	private Date rptdate;
	private String rptid;
	private Date const_startdate;
	private Date const_enddate;
	private double lu_term;
	private Date start_date;
	private Date end_date;
	private double base_area;
	private double built_up_area;
	private double other_area;
	private double shared_lu_area;
	private String inputer_no;
	private String inputer;
	private Date input_date;
	private double modifier_no;
	private String modifier;
	private Date modify_date;
	private String approver_no;
	private String approver;
	private Date appr_date;
	private String appr_opinion;
	private String publish_status;
	private String par_no;
	private String bldg_status;
	private String data_state;
	private String data_from;
	private String lmt_id;
	private String gdt_id;
	private String fcch_rise_id;

	public Bd_rise(){
		super();
		this.t = Bd_rise.class;
	}

	public Bd_rise(String rise_id){
		super();
		this.rise_id = rise_id;
	}

	public void setRise_id(String rise_id) {
		this.rise_id = rise_id;
	}

	public void setBldg_id(String bldg_id) {
		this.bldg_id = bldg_id;
	}

	public void setBldg_code_sz(String bldg_code_sz) {
		this.bldg_code_sz = bldg_code_sz;
	}

	public void setBldg_code_gb(String bldg_code_gb) {
		this.bldg_code_gb = bldg_code_gb;
	}

	public void setBldg_code_tmp(String bldg_code_tmp) {
		this.bldg_code_tmp = bldg_code_tmp;
	}

	public void setPar_id(String par_id) {
		this.par_id = par_id;
	}

	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}

	public void setRise_no(String rise_no) {
		this.rise_no = rise_no;
	}

	public void setBldg_name_no(String bldg_name_no) {
		this.bldg_name_no = bldg_name_no;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public void setPar_no_subno(String par_no_subno) {
		this.par_no_subno = par_no_subno;
	}

	public void setBldg_attr(String bldg_attr) {
		this.bldg_attr = bldg_attr;
	}

	public void setBldg_type(String bldg_type) {
		this.bldg_type = bldg_type;
	}

	public void setBldg_usage(String bldg_usage) {
		this.bldg_usage = bldg_usage;
	}

	public void setBldg_structure(String bldg_structure) {
		this.bldg_structure = bldg_structure;
	}

	public void setBldg_floors(double bldg_floors) {
		this.bldg_floors = bldg_floors;
	}

	public void setUnit_suits(double unit_suits) {
		this.unit_suits = unit_suits;
	}

	public void setRptdate(Date rptdate) {
		this.rptdate = rptdate;
	}

	public void setRptid(String rptid) {
		this.rptid = rptid;
	}

	public void setConst_startdate(Date const_startdate) {
		this.const_startdate = const_startdate;
	}

	public void setConst_enddate(Date const_enddate) {
		this.const_enddate = const_enddate;
	}

	public void setLu_term(double lu_term) {
		this.lu_term = lu_term;
	}

	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}

	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}

	public void setBase_area(double base_area) {
		this.base_area = base_area;
	}

	public void setBuilt_up_area(double built_up_area) {
		this.built_up_area = built_up_area;
	}

	public void setOther_area(double other_area) {
		this.other_area = other_area;
	}

	public void setShared_lu_area(double shared_lu_area) {
		this.shared_lu_area = shared_lu_area;
	}

	public void setInputer_no(String inputer_no) {
		this.inputer_no = inputer_no;
	}

	public void setInputer(String inputer) {
		this.inputer = inputer;
	}

	public void setInput_date(Date input_date) {
		this.input_date = input_date;
	}

	public void setModifier_no(double modifier_no) {
		this.modifier_no = modifier_no;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	public void setModify_date(Date modify_date) {
		this.modify_date = modify_date;
	}

	public void setApprover_no(String approver_no) {
		this.approver_no = approver_no;
	}

	public void setApprover(String approver) {
		this.approver = approver;
	}

	public void setAppr_date(Date appr_date) {
		this.appr_date = appr_date;
	}

	public void setAppr_opinion(String appr_opinion) {
		this.appr_opinion = appr_opinion;
	}

	public void setPublish_status(String publish_status) {
		this.publish_status = publish_status;
	}

	public void setPar_no(String par_no) {
		this.par_no = par_no;
	}

	public void setBldg_status(String bldg_status) {
		this.bldg_status = bldg_status;
	}

	public void setData_state(String data_state) {
		this.data_state = data_state;
	}

	public void setData_from(String data_from) {
		this.data_from = data_from;
	}

	public void setLmt_id(String lmt_id) {
		this.lmt_id = lmt_id;
	}

	public void setGdt_id(String gdt_id) {
		this.gdt_id = gdt_id;
	}

	public void setFcch_rise_id(String fcch_rise_id) {
		this.fcch_rise_id = fcch_rise_id;
	}

	public String getRise_id() {
		return rise_id;
	}

	public String getBldg_id() {
		return bldg_id;
	}

	public String getBldg_code_sz() {
		return bldg_code_sz;
	}

	public String getBldg_code_gb() {
		return bldg_code_gb;
	}

	public String getBldg_code_tmp() {
		return bldg_code_tmp;
	}

	public String getPar_id() {
		return par_id;
	}

	public String getProject_name() {
		return project_name;
	}

	public String getRise_no() {
		return rise_no;
	}

	public String getBldg_name_no() {
		return bldg_name_no;
	}

	public String getStreet() {
		return street;
	}

	public String getPar_no_subno() {
		return par_no_subno;
	}

	public String getBldg_attr() {
		return bldg_attr;
	}

	public String getBldg_type() {
		return bldg_type;
	}

	public String getBldg_usage() {
		return bldg_usage;
	}

	public String getBldg_structure() {
		return bldg_structure;
	}

	public double getBldg_floors() {
		return bldg_floors;
	}

	public double getUnit_suits() {
		return unit_suits;
	}

	public Date getRptdate() {
		return rptdate;
	}

	public String getRptid() {
		return rptid;
	}

	public Date getConst_startdate() {
		return const_startdate;
	}

	public Date getConst_enddate() {
		return const_enddate;
	}

	public double getLu_term() {
		return lu_term;
	}

	public Date getStart_date() {
		return start_date;
	}

	public Date getEnd_date() {
		return end_date;
	}

	public double getBase_area() {
		return base_area;
	}

	public double getBuilt_up_area() {
		return built_up_area;
	}

	public double getOther_area() {
		return other_area;
	}

	public double getShared_lu_area() {
		return shared_lu_area;
	}

	public String getInputer_no() {
		return inputer_no;
	}

	public String getInputer() {
		return inputer;
	}

	public Date getInput_date() {
		return input_date;
	}

	public double getModifier_no() {
		return modifier_no;
	}

	public String getModifier() {
		return modifier;
	}

	public Date getModify_date() {
		return modify_date;
	}

	public String getApprover_no() {
		return approver_no;
	}

	public String getApprover() {
		return approver;
	}

	public Date getAppr_date() {
		return appr_date;
	}

	public String getAppr_opinion() {
		return appr_opinion;
	}

	public String getPublish_status() {
		return publish_status;
	}

	public String getPar_no() {
		return par_no;
	}

	public String getBldg_status() {
		return bldg_status;
	}

	public String getData_state() {
		return data_state;
	}

	public String getData_from() {
		return data_from;
	}

	public String getLmt_id() {
		return lmt_id;
	}

	public String getGdt_id() {
		return gdt_id;
	}

	public String getFcch_rise_id() {
		return fcch_rise_id;
	}

}