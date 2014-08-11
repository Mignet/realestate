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
@Table(name = "BK_REMARK")
public class Remark extends BaseDomain<Remark> {
	private String remark_office;
	private String remark_comment;
	private String pre_reg_code;
	@Id
	private String remark_id;
	private String book_code;
	private String b_deleteflag;
	private String reg_code;
	private String remark_type;
	private String remark_no;

	public Remark(){
		super();
		this.t = Remark.class;
	}

	public Remark(String remark_id){
		super();
		this.remark_id = remark_id;
	}

	public void setRemark_office(String remark_office) {
		this.remark_office = remark_office;
	}
	public void setRemark_comment(String remark_comment) {
		this.remark_comment = remark_comment;
	}

	public void setPre_reg_code(String pre_reg_code) {
		this.pre_reg_code = pre_reg_code;
	}

	public void setRemark_id(String remark_id) {
		this.remark_id = remark_id;
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

	public void setRemark_type(String remark_type) {
		this.remark_type = remark_type;
	}

	public void setRemark_no(String remark_no) {
		this.remark_no = remark_no;
	}

	public String getRemark_office() {
		return remark_office;
	}

	public String getRemark_comment() {
		return remark_comment;
	}

	public String getPre_reg_code() {
		return pre_reg_code;
	}

	public String getRemark_id() {
		return remark_id;
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

	public String getRemark_type() {
		return remark_type;
	}

	public String getRemark_no() {
		return remark_no;
	}

}
