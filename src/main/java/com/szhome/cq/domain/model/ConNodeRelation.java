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
@Table(name = "CFIG_NODE_OFI_REL")
public class ConNodeRelation extends BaseDomain<ConNodeRelation> {
	@Id
	private String node_ofi_rel;
	private String office_id;
	private String node_id;

	public ConNodeRelation(){
		super();
		this.t = ConNodeRelation.class;
	}

	public ConNodeRelation(String node_ofi_rel){
		super();
		this.node_ofi_rel = node_ofi_rel;
	}

	public void setNode_ofi_rel(String node_ofi_rel) {
		this.node_ofi_rel = node_ofi_rel;
	}

	public void setOffice_id(String office_id) {
		this.office_id = office_id;
	}

	public void setNode_id(String node_id) {
		this.node_id = node_id;
	}

	public String getNode_ofi_rel() {
		return node_ofi_rel;
	}

	public String getOffice_id() {
		return office_id;
	}

	public String getNode_id() {
		return node_id;
	}

}
