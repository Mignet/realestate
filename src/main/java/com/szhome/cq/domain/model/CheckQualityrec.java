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
@Table(name = "CHK_QUALITY_REC")
public class CheckQualityrec extends BaseDomain<CheckQualityrec> {
	private String proc_node;
	private String proc_node_name;
	private String activdef_id;
	@Id
	private String qua_rec_id;
	private String bus_id;
	private String b_deleteflag;
	private String qua_no;
	private String evaluate_result;
	private Date evaluate_time;

	public CheckQualityrec(){
		super();
		this.t = CheckQualityrec.class;
	}

	public CheckQualityrec(String qua_rec_id){
		super();
		this.qua_rec_id = qua_rec_id;
	}

	public void setProc_node(String proc_node) {
		this.proc_node = proc_node;
	}

	public void setProc_node_name(String proc_node_name) {
		this.proc_node_name = proc_node_name;
	}

	public void setActivdef_id(String activdef_id) {
		this.activdef_id = activdef_id;
	}

	public void setQua_rec_id(String qua_rec_id) {
		this.qua_rec_id = qua_rec_id;
	}

	public void setBus_id(String bus_id) {
		this.bus_id = bus_id;
	}

	public void setB_deleteflag(String b_deleteflag) {
		this.b_deleteflag = b_deleteflag;
	}

	public void setQua_no(String qua_no) {
		this.qua_no = qua_no;
	}

	public void setEvaluate_result(String evaluate_result) {
		this.evaluate_result = evaluate_result;
	}

	public void setEvaluate_time(Date evaluate_time) {
		this.evaluate_time = evaluate_time;
	}

	public String getProc_node() {
		return proc_node;
	}

	public String getProc_node_name() {
		return proc_node_name;
	}

	public String getActivdef_id() {
		return activdef_id;
	}

	public String getQua_rec_id() {
		return qua_rec_id;
	}

	public String getBus_id() {
		return bus_id;
	}

	public String getB_deleteflag() {
		return b_deleteflag;
	}

	public String getQua_no() {
		return qua_no;
	}

	public String getEvaluate_result() {
		return evaluate_result;
	}

	public Date getEvaluate_time() {
		return evaluate_time;
	}

}

