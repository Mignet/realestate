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
@Table(name = "BUS_OPINION")
public class ExamSecond extends BaseDomain<ExamSecond> {
	@Id
	private String opinion_id;
	private String bus_id;
	private String b_deleteflag;
	private String checker_no;
	private String opinion_type;
	private String opinion_content;
	private Date opinion_time;

	public ExamSecond(){
		super();
		this.t = ExamSecond.class;
	}

	public ExamSecond(String opinion_id){
		super();
		this.opinion_id = opinion_id;
	}

	public void setOpinion_id(String opinion_id) {
		this.opinion_id = opinion_id;
	}

	public void setBus_id(String bus_id) {
		this.bus_id = bus_id;
	}

	public void setB_deleteflag(String b_deleteflag) {
		this.b_deleteflag = b_deleteflag;
	}

	public void setChecker_no(String checker_no) {
		this.checker_no = checker_no;
	}

	public void setOpinion_type(String opinion_type) {
		this.opinion_type = opinion_type;
	}

	public void setOpinion_content(String opinion_content) {
		this.opinion_content = opinion_content;
	}

	public void setOpinion_time(Date opinion_time) {
		this.opinion_time = opinion_time;
	}

	public String getOpinion_id() {
		return opinion_id;
	}

	public String getBus_id() {
		return bus_id;
	}

	public String getB_deleteflag() {
		return b_deleteflag;
	}

	public String getChecker_no() {
		return checker_no;
	}

	public String getOpinion_type() {
		return opinion_type;
	}

	public String getOpinion_content() {
		return opinion_content;
	}

	public Date getOpinion_time() {
		return opinion_time;
	}

}
