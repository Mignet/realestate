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
@Table(name = "bd_bldg_comm_part")
public class Bd_bldg_comm_part extends BaseDomain<Bd_bldg_comm_part> {
	@Id
	private String comm_part_id;
	private String invalid;
	private String rise_id;
	private String comm_part_name;
	private String type;
	private double comm_part_area;
	private String home_num;
	private String house_num;
	private String memo;
	private Date input_date;
	private String inputer_no;
	private String inputer;

	public Bd_bldg_comm_part(){
		super();
		this.t = Bd_bldg_comm_part.class;
	}

	public Bd_bldg_comm_part(String comm_part_id){
		super();
		this.comm_part_id = comm_part_id;
	}

	public void setComm_part_id(String comm_part_id) {
		this.comm_part_id = comm_part_id;
	}

	public void setInvalid(String invalid) {
		this.invalid = invalid;
	}

	public void setRise_id(String rise_id) {
		this.rise_id = rise_id;
	}

	public void setComm_part_name(String comm_part_name) {
		this.comm_part_name = comm_part_name;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setComm_part_area(double comm_part_area) {
		this.comm_part_area = comm_part_area;
	}

	public void setHome_num(String home_num) {
		this.home_num = home_num;
	}

	public void setHouse_num(String house_num) {
		this.house_num = house_num;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public void setInput_date(Date input_date) {
		this.input_date = input_date;
	}

	public void setInputer_no(String inputer_no) {
		this.inputer_no = inputer_no;
	}

	public void setInputer(String inputer) {
		this.inputer = inputer;
	}

	public String getComm_part_id() {
		return comm_part_id;
	}

	public String getInvalid() {
		return invalid;
	}

	public String getRise_id() {
		return rise_id;
	}

	public String getComm_part_name() {
		return comm_part_name;
	}

	public String getType() {
		return type;
	}

	public double getComm_part_area() {
		return comm_part_area;
	}

	public String getHome_num() {
		return home_num;
	}

	public String getHouse_num() {
		return house_num;
	}

	public String getMemo() {
		return memo;
	}

	public Date getInput_date() {
		return input_date;
	}

	public String getInputer_no() {
		return inputer_no;
	}

	public String getInputer() {
		return inputer;
	}

}