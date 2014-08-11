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
@Table(name = "BDC_PZSJ_BHGZPX")
public class IdentifierRuleSort extends BaseDomain<IdentifierRuleSort> {
	@Id
	private String id;
	private String identifier_id;
	private double sort;
	private String rule_type;

	public IdentifierRuleSort(){
		super();
		this.t = IdentifierRuleSort.class;
	}

	public IdentifierRuleSort(String id){
		super();
		this.id = id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setIdentifier_id(String identifier_id) {
		this.identifier_id = identifier_id;
	}

	public void setSort(double sort) {
		this.sort = sort;
	}

	public void setRule_type(String rule_type) {
		this.rule_type = rule_type;
	}

	public String getId() {
		return id;
	}

	public String getIdentifier_id() {
		return identifier_id;
	}

	public double getSort() {
		return sort;
	}

	public String getRule_type() {
		return rule_type;
	}

}
