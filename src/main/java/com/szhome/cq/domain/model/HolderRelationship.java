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
@Table(name = "BK_RIGHT_REL")
public class HolderRelationship extends BaseDomain<HolderRelationship> {
	@Id
	private String right_rel_id;
	private String book_code;
	private String b_deleteflag;
	private String reg_code;
	private String effective;

	public HolderRelationship(){
		super();
		this.t = HolderRelationship.class;
	}

	public HolderRelationship(String right_rel_id){
		super();
		this.right_rel_id = right_rel_id;
	}

	public void setRight_rel_id(String right_rel_id) {
		this.right_rel_id = right_rel_id;
	}

	public void setBook_code(String book_code) {
		this.book_code = book_code;
	}

	public void setB_deleteflag(String b_deleteflag) {
		this.b_deleteflag = b_deleteflag;
	}

	public void setReg_code(String reg_code) {
		this.reg_code = reg_code;
	}

	public void setEffective(String effective) {
		this.effective = effective;
	}

	public String getRight_rel_id() {
		return right_rel_id;
	}

	public String getBook_code() {
		return book_code;
	}

	public String getB_deleteflag() {
		return b_deleteflag;
	}

	public String getReg_code() {
		return reg_code;
	}

	public String getEffective() {
		return effective;
	}

}
