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
@Table(name = "BK_DISTRAIN")
public class Reg_Distrain extends BaseDomain<Reg_Distrain> {
	private String effective;
	@Id
	private String distress_id;
	private String book_code;
	private String b_deleteflag;
	private String reg_code;
	private Date reg_date;
	private String cer_no;
	private String recorder;
	private String pre_reg_code;
	private String procdef_id;
	private String lim_holder;
	private String dis_off;
	private String law_doc;
	private String dis_no;
	private Date start_date;
	private Date end_date;
	private Date dis_date;
	private String dis_limit;
	private String excursus;
	private String service_name;
	private Date service_date;
	private String workid;
	private String dis_per_tel;
	private String pre_con_no;
	public String getPre_con_no() {
		return pre_con_no;
	}

	public void setPre_con_no(String pre_con_no) {
		this.pre_con_no = pre_con_no;
	}

	public String getDis_range() {
		return dis_range;
	}

	public void setDis_range(String dis_range) {
		this.dis_range = dis_range;
	}

	public String getDis_type() {
		return dis_type;
	}

	public void setDis_type(String dis_type) {
		this.dis_type = dis_type;
	}

	private String dis_range;
	private String dis_type;

	public Reg_Distrain(){
		super();
		this.t = Reg_Distrain.class;
	}

	public Reg_Distrain(String distress_id){
		super();
		this.distress_id = distress_id;
	}

	public void setEffective(String effective) {
		this.effective = effective;
	}

	public void setDistress_id(String distress_id) {
		this.distress_id = distress_id;
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

	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}

	public void setCer_no(String cer_no) {
		this.cer_no = cer_no;
	}

	public void setRecorder(String recorder) {
		this.recorder = recorder;
	}

	public void setPre_reg_code(String pre_reg_code) {
		this.pre_reg_code = pre_reg_code;
	}

	public void setProcdef_id(String procdef_id) {
		this.procdef_id = procdef_id;
	}

	public void setLim_holder(String lim_holder) {
		this.lim_holder = lim_holder;
	}

	public void setDis_off(String dis_off) {
		this.dis_off = dis_off;
	}

	public void setLaw_doc(String law_doc) {
		this.law_doc = law_doc;
	}

	public void setDis_no(String dis_no) {
		this.dis_no = dis_no;
	}

	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}

	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}

	public void setDis_date(Date dis_date) {
		this.dis_date = dis_date;
	}

	public void setDis_limit(String dis_limit) {
		this.dis_limit = dis_limit;
	}

	public void setExcursus(String excursus) {
		this.excursus = excursus;
	}

	public void setService_name(String service_name) {
		this.service_name = service_name;
	}

	public void setService_date(Date service_date) {
		this.service_date = service_date;
	}

	public void setWorkid(String workid) {
		this.workid = workid;
	}

	public void setDis_per_tel(String dis_per_tel) {
		this.dis_per_tel = dis_per_tel;
	}

	public String getEffective() {
		return effective;
	}

	public String getDistress_id() {
		return distress_id;
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

	public Date getReg_date() {
		return reg_date;
	}

	public String getCer_no() {
		return cer_no;
	}

	public String getRecorder() {
		return recorder;
	}

	public String getPre_reg_code() {
		return pre_reg_code;
	}

	public String getProcdef_id() {
		return procdef_id;
	}

	public String getLim_holder() {
		return lim_holder;
	}

	public String getDis_off() {
		return dis_off;
	}

	public String getLaw_doc() {
		return law_doc;
	}

	public String getDis_no() {
		return dis_no;
	}

	public Date getStart_date() {
		return start_date;
	}

	public Date getEnd_date() {
		return end_date;
	}

	public Date getDis_date() {
		return dis_date;
	}

	public String getDis_limit() {
		return dis_limit;
	}

	public String getExcursus() {
		return excursus;
	}

	public String getService_name() {
		return service_name;
	}

	public Date getService_date() {
		return service_date;
	}

	public String getWorkid() {
		return workid;
	}

	public String getDis_per_tel() {
		return dis_per_tel;
	}

}
