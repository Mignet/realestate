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
@Table(name = "BUS_REVOKEAPPROVAL")
public class BusRevokeapproval extends BaseDomain<BusRevokeapproval> {
	private String bus_id;
	@Id
	private String id;
	private String can_reason;
	private String effective;
	private String reg_code;
	private String pre_reg_code;
	private String can_type;

	public String getCan_type() {
		return can_type;
	}

	public void setCan_type(String can_type) {
		this.can_type = can_type;
	}

	public BusRevokeapproval(){
		super();
		this.t = BusRevokeapproval.class;
	}

	public BusRevokeapproval(String id){
		super();
		this.id = id;
	}

	public void setBus_id(String bus_id) {
		this.bus_id = bus_id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setCan_reason(String can_reason) {
		this.can_reason = can_reason;
	}

	public void setEffective(String effective) {
		this.effective = effective;
	}

	public void setReg_code(String reg_code) {
		this.reg_code = reg_code;
	}

	public void setPre_reg_code(String pre_reg_code) {
		this.pre_reg_code = pre_reg_code;
	}

	public String getBus_id() {
		return bus_id;
	}

	public String getId() {
		return id;
	}

	public String getCan_reason() {
		return can_reason;
	}

	public String getEffective() {
		return effective;
	}

	public String getReg_code() {
		return reg_code;
	}

	public String getPre_reg_code() {
		return pre_reg_code;
	}

}
