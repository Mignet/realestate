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
@Table(name = "bk_useright")
public class Reg_Useright extends BaseDomain<Reg_Useright> {
	@Id
	private String useright_id;
	private String book_code;
	private String b_deleteflag;
	private String reg_code;
	private String share_status;
	private String cer_no;
	private String reg_pri;
	private Date reg_date;
	private String recorder;
	private String excursus;
	private String lu_term;
	private Date start_date;
	private Date end_date;
	private String pre_reg_code;
	private String procdef_id;
	private String land_use;
	private String effective;
	private String useright_type;
	private String reason;

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
	public Reg_Useright(){
		super();
		this.t = Reg_Useright.class;
	}

	public Reg_Useright(String useright_id){
		super();
		this.useright_id = useright_id;
	}

	public void setUseright_id(String useright_id) {
		this.useright_id = useright_id;
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

	public void setShare_status(String share_status) {
		this.share_status = share_status;
	}

	public void setCer_no(String cer_no) {
		this.cer_no = cer_no;
	}

	public void setReg_pri(String reg_pri) {
		this.reg_pri = reg_pri;
	}

	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}

	public void setRecorder(String recorder) {
		this.recorder = recorder;
	}

	public void setExcursus(String excursus) {
		this.excursus = excursus;
	}

	public void setLu_term(String lu_term) {
		this.lu_term = lu_term;
	}

	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}

	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}

	public void setPre_reg_code(String pre_reg_code) {
		this.pre_reg_code = pre_reg_code;
	}

	public void setProcdef_id(String procdef_id) {
		this.procdef_id = procdef_id;
	}

	public void setLand_use(String land_use) {
		this.land_use = land_use;
	}

	public void setEffective(String effective) {
		this.effective = effective;
	}

	public void setUseright_type(String useright_type) {
		this.useright_type = useright_type;
	}

	public String getUseright_id() {
		return useright_id;
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

	public String getShare_status() {
		return share_status;
	}

	public String getCer_no() {
		return cer_no;
	}

	public String getReg_pri() {
		return reg_pri;
	}

	public Date getReg_date() {
		return reg_date;
	}

	public String getRecorder() {
		return recorder;
	}

	public String getExcursus() {
		return excursus;
	}

	public String getLu_term() {
		return lu_term;
	}

	public Date getStart_date() {
		return start_date;
	}

	public Date getEnd_date() {
		return end_date;
	}

	public String getPre_reg_code() {
		return pre_reg_code;
	}

	public String getProcdef_id() {
		return procdef_id;
	}

	public String getLand_use() {
		return land_use;
	}

	public String getEffective() {
		return effective;
	}

	public String getUseright_type() {
		return useright_type;
	}

}
