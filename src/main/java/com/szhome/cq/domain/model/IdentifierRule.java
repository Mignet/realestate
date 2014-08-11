package com.szhome.cq.domain.model;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.springjdbc.annotation.BaseDomain;
import com.springjdbc.annotation.Entity;
import com.springjdbc.annotation.Id;
import com.springjdbc.annotation.Table;

@Scope("prototype")
@Component
@Entity
@Table(name = "BDC_PZSJ_BHGZ")
public class IdentifierRule extends BaseDomain<IdentifierRule> {
	@Id
	private String rule_id;
	private String rule_name;
	private String rule;
	private String state;
	private String identifier_id;
	private double sort;

	public IdentifierRule(){
		super();
		this.t = IdentifierRule.class;
	}

	public IdentifierRule(String rule_id){
		super();
		this.rule_id = rule_id;
	}

	public void setRule_id(String rule_id) {
		this.rule_id = rule_id;
	}

	public void setRule_name(String rule_name) {
		this.rule_name = rule_name;
	}

	public void setRule(String rule) {
		this.rule = rule;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setIdentifier_id(String identifier_id) {
		this.identifier_id = identifier_id;
	}

	public void setSort(double sort) {
		this.sort = sort;
	}

	public String getRule_id() {
		return rule_id;
	}

	public String getRule_name() {
		return rule_name;
	}

	public String getRule() {
		return rule;
	}

	public String getState() {
		return state;
	}

	public String getIdentifier_id() {
		return identifier_id;
	}

	public double getSort() {
		return sort;
	}

}
