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
@Table(name = "BK_HOLDER")
public class Holder extends BaseDomain<Holder> {
	
	
	@Id
	private String holder_id;
	private String right_rel_id;
	private String b_deleteflag;
	private String hol_type;
	private String hol_name;
	private String hol_cer_type;
	private String hol_cer_no;
	private String hol_address;
	private String depart_type;
	private String legal_name;
	private String legal_cer;
	private String agent_name;
	private String agent_cer;
	private String agent_tel;
	private String portion;
	private String his_holder_id;
	private String hol_rel;
	private String agent_cer_type;

	public Holder(){
		super();
		this.t = Holder.class;
	}

	public Holder(String holder_id){
		super();
		this.holder_id = holder_id;
	}

	public void setHolder_id(String holder_id) {
		this.holder_id = holder_id;
	}

	public void setRight_rel_id(String right_rel_id) {
		this.right_rel_id = right_rel_id;
	}

	public void setB_deleteflag(String b_deleteflag) {
		this.b_deleteflag = b_deleteflag;
	}

	public void setHol_type(String hol_type) {
		this.hol_type = hol_type;
	}

	public void setHol_name(String hol_name) {
		this.hol_name = hol_name;
	}

	public void setHol_cer_type(String hol_cer_type) {
		this.hol_cer_type = hol_cer_type;
	}

	public void setHol_cer_no(String hol_cer_no) {
		this.hol_cer_no = hol_cer_no;
	}

	public void setHol_address(String hol_address) {
		this.hol_address = hol_address;
	}

	public void setDepart_type(String depart_type) {
		this.depart_type = depart_type;
	}

	public void setLegal_name(String legal_name) {
		this.legal_name = legal_name;
	}

	public void setLegal_cer(String legal_cer) {
		this.legal_cer = legal_cer;
	}

	public void setAgent_name(String agent_name) {
		this.agent_name = agent_name;
	}

	public void setAgent_cer(String agent_cer) {
		this.agent_cer = agent_cer;
	}

	public void setAgent_tel(String agent_tel) {
		this.agent_tel = agent_tel;
	}

	public void setPortion(String portion) {
		this.portion = portion;
	}

	public void setHis_holder_id(String his_holder_id) {
		this.his_holder_id = his_holder_id;
	}

	public String getHolder_id() {
		return holder_id;
	}

	public String getRight_rel_id() {
		return right_rel_id;
	}

	public String getB_deleteflag() {
		return b_deleteflag;
	}

	public String getHol_type() {
		return hol_type;
	}

	public String getHol_name() {
		return hol_name;
	}

	public String getHol_cer_type() {
		return hol_cer_type;
	}

	public String getHol_cer_no() {
		return hol_cer_no;
	}

	public String getHol_address() {
		return hol_address;
	}

	public String getDepart_type() {
		return depart_type;
	}

	public String getLegal_name() {
		return legal_name;
	}

	public String getLegal_cer() {
		return legal_cer;
	}

	public String getAgent_name() {
		return agent_name;
	}

	public String getAgent_cer() {
		return agent_cer;
	}

	public String getAgent_tel() {
		return agent_tel;
	}

	public String getPortion() {
		return portion;
	}

	public String getHis_holder_id() {
		return his_holder_id;
	}

	public String getHol_rel() {
		return hol_rel;
	}

	public void setHol_rel(String hol_rel) {
		this.hol_rel = hol_rel;
	}

	public String getAgent_cer_type() {
		return agent_cer_type;
	}

	public void setAgent_cer_type(String agent_cer_type) {
		this.agent_cer_type = agent_cer_type;
	}

}
