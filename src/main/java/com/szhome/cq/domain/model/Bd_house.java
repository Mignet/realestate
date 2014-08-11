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
@Table(name = "bd_house")
public class Bd_house extends BaseDomain<Bd_house> {
	private String data_from;
	@Id
	private String ho_id;
	private String house_code_sz;
	private String house_code_gb;
	private String house_code_tmp;
	private String parcel_no_subno;
	private String unit_no;
	private double house_no_num;
	private String seat_no;
	private String house_style;
	private String floor_no;
	private String layer_no;
	private double layer_no_num;
	private double layer_used;
	private String house_attr;
	private String house_type;
	private String house_usage;
	private String house_structure;
	private double lu_term;
	private Date start_date;
	private Date end_date;
	private double built_in_area;
	private double house_in_area;
	private double house_comm_area;
	private double floor_area;
	private double lu_area;
	private double shared_com_area;
	private double shared_base_area;
	private double other_area;
	private String inputer_no;
	private String inputer;
	private Date input_date;
	private String modifier_no;
	private String modifier;
	private Date modify_date;
	private String rise_id;
	private String data_state;
	private String approver_no;
	private Date appr_date;
	private String appr_opinion;
	private String appr_result;
	private String hxt_id;
	private String fcch_flatlet_id;

	public Bd_house(){
		super();
		this.t = Bd_house.class;
	}

	public Bd_house(String ho_id){
		super();
		this.ho_id = ho_id;
	}

	public void setData_from(String data_from) {
		this.data_from = data_from;
	}

	public void setHo_id(String ho_id) {
		this.ho_id = ho_id;
	}

	public void setHouse_code_sz(String house_code_sz) {
		this.house_code_sz = house_code_sz;
	}

	public void setHouse_code_gb(String house_code_gb) {
		this.house_code_gb = house_code_gb;
	}

	public void setHouse_code_tmp(String house_code_tmp) {
		this.house_code_tmp = house_code_tmp;
	}

	public void setParcel_no_subno(String parcel_no_subno) {
		this.parcel_no_subno = parcel_no_subno;
	}

	public void setUnit_no(String unit_no) {
		this.unit_no = unit_no;
	}

	public void setHouse_no_num(double house_no_num) {
		this.house_no_num = house_no_num;
	}

	public void setSeat_no(String seat_no) {
		this.seat_no = seat_no;
	}

	public void setHouse_style(String house_style) {
		this.house_style = house_style;
	}

	public void setFloor_no(String floor_no) {
		this.floor_no = floor_no;
	}

	public void setLayer_no(String layer_no) {
		this.layer_no = layer_no;
	}

	public void setLayer_no_num(double layer_no_num) {
		this.layer_no_num = layer_no_num;
	}

	public void setLayer_used(double layer_used) {
		this.layer_used = layer_used;
	}

	public void setHouse_attr(String house_attr) {
		this.house_attr = house_attr;
	}

	public void setHouse_type(String house_type) {
		this.house_type = house_type;
	}

	public void setHouse_usage(String house_usage) {
		this.house_usage = house_usage;
	}

	public void setHouse_structure(String house_structure) {
		this.house_structure = house_structure;
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

	public void setBuilt_in_area(double built_in_area) {
		this.built_in_area = built_in_area;
	}

	public void setHouse_in_area(double house_in_area) {
		this.house_in_area = house_in_area;
	}

	public void setHouse_comm_area(double house_comm_area) {
		this.house_comm_area = house_comm_area;
	}

	public void setFloor_area(double floor_area) {
		this.floor_area = floor_area;
	}

	public void setLu_area(double lu_area) {
		this.lu_area = lu_area;
	}

	public void setShared_com_area(double shared_com_area) {
		this.shared_com_area = shared_com_area;
	}

	public void setShared_base_area(double shared_base_area) {
		this.shared_base_area = shared_base_area;
	}

	public void setOther_area(double other_area) {
		this.other_area = other_area;
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

	public void setModifier_no(String modifier_no) {
		this.modifier_no = modifier_no;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	public void setModify_date(Date modify_date) {
		this.modify_date = modify_date;
	}

	public void setRise_id(String rise_id) {
		this.rise_id = rise_id;
	}

	public void setData_state(String data_state) {
		this.data_state = data_state;
	}

	public void setApprover_no(String approver_no) {
		this.approver_no = approver_no;
	}

	public void setAppr_date(Date appr_date) {
		this.appr_date = appr_date;
	}

	public void setAppr_opinion(String appr_opinion) {
		this.appr_opinion = appr_opinion;
	}

	public void setAppr_result(String appr_result) {
		this.appr_result = appr_result;
	}

	public void setHxt_id(String hxt_id) {
		this.hxt_id = hxt_id;
	}

	public void setFcch_flatlet_id(String fcch_flatlet_id) {
		this.fcch_flatlet_id = fcch_flatlet_id;
	}

	public String getData_from() {
		return data_from;
	}

	public String getHo_id() {
		return ho_id;
	}

	public String getHouse_code_sz() {
		return house_code_sz;
	}

	public String getHouse_code_gb() {
		return house_code_gb;
	}

	public String getHouse_code_tmp() {
		return house_code_tmp;
	}

	public String getParcel_no_subno() {
		return parcel_no_subno;
	}

	public String getUnit_no() {
		return unit_no;
	}

	public double getHouse_no_num() {
		return house_no_num;
	}

	public String getSeat_no() {
		return seat_no;
	}

	public String getHouse_style() {
		return house_style;
	}

	public String getFloor_no() {
		return floor_no;
	}

	public String getLayer_no() {
		return layer_no;
	}

	public double getLayer_no_num() {
		return layer_no_num;
	}

	public double getLayer_used() {
		return layer_used;
	}

	public String getHouse_attr() {
		return house_attr;
	}

	public String getHouse_type() {
		return house_type;
	}

	public String getHouse_usage() {
		return house_usage;
	}

	public String getHouse_structure() {
		return house_structure;
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

	public double getBuilt_in_area() {
		return built_in_area;
	}

	public double getHouse_in_area() {
		return house_in_area;
	}

	public double getHouse_comm_area() {
		return house_comm_area;
	}

	public double getFloor_area() {
		return floor_area;
	}

	public double getLu_area() {
		return lu_area;
	}

	public double getShared_com_area() {
		return shared_com_area;
	}

	public double getShared_base_area() {
		return shared_base_area;
	}

	public double getOther_area() {
		return other_area;
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

	public String getModifier_no() {
		return modifier_no;
	}

	public String getModifier() {
		return modifier;
	}

	public Date getModify_date() {
		return modify_date;
	}

	public String getRise_id() {
		return rise_id;
	}

	public String getData_state() {
		return data_state;
	}

	public String getApprover_no() {
		return approver_no;
	}

	public Date getAppr_date() {
		return appr_date;
	}

	public String getAppr_opinion() {
		return appr_opinion;
	}

	public String getAppr_result() {
		return appr_result;
	}

	public String getHxt_id() {
		return hxt_id;
	}

	public String getFcch_flatlet_id() {
		return fcch_flatlet_id;
	}

}