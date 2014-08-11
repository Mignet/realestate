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
@Table(name = "CFIG_ACCEPT_RULE")
public class AcceptRule extends BaseDomain<AcceptRule> {
	@Id
	private String acc_rule_id;
	private String bus_type_id;
	private double b_deleteflag;
	private String acc_recorder;
	private Date acc_enter_date;
	private String rule_name;
	private String rule_content;
	private String rule_type;
	private String rule;

	public AcceptRule(){
		super();
		this.t = AcceptRule.class;
	}

	public AcceptRule(String acc_rule_id){
		super();
		this.acc_rule_id = acc_rule_id;
	}

	public void setAcc_rule_id(String acc_rule_id) {
		this.acc_rule_id = acc_rule_id;
	}

	public void setBus_type_id(String bus_type_id) {
		this.bus_type_id = bus_type_id;
	}

	public void setB_deleteflag(double b_deleteflag) {
		this.b_deleteflag = b_deleteflag;
	}

	public void setAcc_recorder(String acc_recorder) {
		this.acc_recorder = acc_recorder;
	}

	public void setAcc_enter_date(Date acc_enter_date) {
		this.acc_enter_date = acc_enter_date;
	}

	public void setRule_name(String rule_name) {
		this.rule_name = rule_name;
	}

	public void setRule_content(String rule_content) {
		this.rule_content = rule_content;
	}

	public void setRule_type(String rule_type) {
		this.rule_type = rule_type;
	}

	public void setRule(String rule) {
		this.rule = rule;
	}

	public String getAcc_rule_id() {
		return acc_rule_id;
	}

	public String getBus_type_id() {
		return bus_type_id;
	}

	public double getB_deleteflag() {
		return b_deleteflag;
	}

	public String getAcc_recorder() {
		return acc_recorder;
	}

	public Date getAcc_enter_date() {
		return acc_enter_date;
	}

	public String getRule_name() {
		return rule_name;
	}

	public String getRule_content() {
		return rule_content;
	}

	public String getRule_type() {
		return rule_type;
	}

	public String getRule() {
		return rule;
	}


}
