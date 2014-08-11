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
@Table(name = "CFIG_CODE")
public class Identifier extends BaseDomain<Identifier> {
	private String parent_id;
	@Id
	private String code_cfig_id;
	private String b_deleteflag;
	private String code_general_type;
	private String rule_exper;
	private String is_valid;
	private Date rul_edit_date;
	private String rul_edit_person;
	private Date rul_effect_date;
	private String seq_name;
	private String rule_name;

	public Identifier(){
		super();
		this.t = Identifier.class;
	}

	public Identifier(String code_cfig_id){
		super();
		this.code_cfig_id = code_cfig_id;
	}

	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
	}

	public void setCode_cfig_id(String code_cfig_id) {
		this.code_cfig_id = code_cfig_id;
	}

	public void setB_deleteflag(String b_deleteflag) {
		this.b_deleteflag = b_deleteflag;
	}

	public void setCode_general_type(String code_general_type) {
		this.code_general_type = code_general_type;
	}

	public void setRule_exper(String rule_exper) {
		this.rule_exper = rule_exper;
	}

	public void setIs_valid(String is_valid) {
		this.is_valid = is_valid;
	}

	public void setRul_edit_date(Date rul_edit_date) {
		this.rul_edit_date = rul_edit_date;
	}

	public void setRul_edit_person(String rul_edit_person) {
		this.rul_edit_person = rul_edit_person;
	}

	public void setRul_effect_date(Date rul_effect_date) {
		this.rul_effect_date = rul_effect_date;
	}

	public void setSeq_name(String seq_name) {
		this.seq_name = seq_name;
	}

	public void setRule_name(String rule_name) {
		this.rule_name = rule_name;
	}

	public String getParent_id() {
		return parent_id;
	}

	public String getCode_cfig_id() {
		return code_cfig_id;
	}

	public String getB_deleteflag() {
		return b_deleteflag;
	}

	public String getCode_general_type() {
		return code_general_type;
	}

	public String getRule_exper() {
		return rule_exper;
	}

	public String getIs_valid() {
		return is_valid;
	}

	public Date getRul_edit_date() {
		return rul_edit_date;
	}

	public String getRul_edit_person() {
		return rul_edit_person;
	}

	public Date getRul_effect_date() {
		return rul_effect_date;
	}

	public String getSeq_name() {
		return seq_name;
	}

	public String getRule_name() {
		return rule_name;
	}

}

