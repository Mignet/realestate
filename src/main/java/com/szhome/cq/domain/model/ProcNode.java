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
@Table(name = "CFIG_PROC_NODE")
public class ProcNode extends BaseDomain<ProcNode> {
	private String bus_type_id;
	private String node_id;
	private String proc_node_id;
	private String node_name;
	private String comment;
	private Integer order_id;

	public Integer getOrder_id() {
		return order_id;
	}

	public void setOrder_id(Integer order_id) {
		this.order_id = order_id;
	}

	public ProcNode(){
		super();
		this.t = ProcNode.class;
	}

	public void setBus_type_id(String bus_type_id) {
		this.bus_type_id = bus_type_id;
	}

	public void setNode_id(String node_id) {
		this.node_id = node_id;
	}

	public void setProc_node_id(String proc_node_id) {
		this.proc_node_id = proc_node_id;
	}

	public void setNode_name(String node_name) {
		this.node_name = node_name;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getBus_type_id() {
		return bus_type_id;
	}

	public String getNode_id() {
		return node_id;
	}

	public String getProc_node_id() {
		return proc_node_id;
	}

	public String getNode_name() {
		return node_name;
	}

	public String getComment() {
		return comment;
	}

}
