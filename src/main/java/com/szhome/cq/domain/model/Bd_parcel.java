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
@Table(name = "bd_parcel")
public class Bd_parcel extends BaseDomain<Bd_parcel> {
	@Id
	private String par_id;
	private String parcel_no;
	private String contract_no;
	private String lu_function;
	private String lu_code;
	private double lu_area;
	private String land_grade;
	private double adj_street_coef;
	private String land_type;
	private String estate_state;
	private String loc_canton;
	private String pre_lot_no;
	private String pro_lot_no;
	private double base_area;
	private double lu_term;
	private Date start_date;
	private Date end_date;
	private String lu_location;
	private String no_modify_flag;
	private String back_flag;
	private String item;
	private String rel_lot_no;
	private String chg_type;
	private String doc_doc_no;
	private String lp_lu_proj_no;
	private String rl_redline_no;
	private String create_from;
	private String contract_type;
	private String own_type;
	private String commit_flag;
	private String special_error;
	private String lot_status;
	private String lam_flag;
	private String lot_no;
	private String lu_term_adjust;
	private String redline_no;
	private double floor_area;
	private String branch_name;
	private String valid_flag;
	private String remark;
	private String land_source;
	private String solution;
	private String lu_funnew;
	private String lu_codenew;
	private String assem_flag;
	private String lam_no;
	private String hist_flag;
	private Date birth_date;
	private Date death_date;
	private String lam_stage;
	private String bu_noinsert_flag;
	private String lu_function_gb;
	private String lu_code_gb;
	private String site_key;
	private String parcel_code;

	public Bd_parcel(){
		super();
		this.t = Bd_parcel.class;
	}

	public Bd_parcel(String par_id){
		super();
		this.par_id = par_id;
	}

	public void setPar_id(String par_id) {
		this.par_id = par_id;
	}

	public void setParcel_no(String parcel_no) {
		this.parcel_no = parcel_no;
	}

	public void setContract_no(String contract_no) {
		this.contract_no = contract_no;
	}

	public void setLu_function(String lu_function) {
		this.lu_function = lu_function;
	}

	public void setLu_code(String lu_code) {
		this.lu_code = lu_code;
	}

	public void setLu_area(double lu_area) {
		this.lu_area = lu_area;
	}

	public void setLand_grade(String land_grade) {
		this.land_grade = land_grade;
	}

	public void setAdj_street_coef(double adj_street_coef) {
		this.adj_street_coef = adj_street_coef;
	}

	public void setLand_type(String land_type) {
		this.land_type = land_type;
	}

	public void setEstate_state(String estate_state) {
		this.estate_state = estate_state;
	}

	public void setLoc_canton(String loc_canton) {
		this.loc_canton = loc_canton;
	}

	public void setPre_lot_no(String pre_lot_no) {
		this.pre_lot_no = pre_lot_no;
	}

	public void setPro_lot_no(String pro_lot_no) {
		this.pro_lot_no = pro_lot_no;
	}

	public void setBase_area(double base_area) {
		this.base_area = base_area;
	}

	public void setLu_term(double lu_term) {
		this.lu_term = lu_term;
	}

	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}

	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}

	public void setLu_location(String lu_location) {
		this.lu_location = lu_location;
	}

	public void setNo_modify_flag(String no_modify_flag) {
		this.no_modify_flag = no_modify_flag;
	}

	public void setBack_flag(String back_flag) {
		this.back_flag = back_flag;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public void setRel_lot_no(String rel_lot_no) {
		this.rel_lot_no = rel_lot_no;
	}

	public void setChg_type(String chg_type) {
		this.chg_type = chg_type;
	}

	public void setDoc_doc_no(String doc_doc_no) {
		this.doc_doc_no = doc_doc_no;
	}

	public void setLp_lu_proj_no(String lp_lu_proj_no) {
		this.lp_lu_proj_no = lp_lu_proj_no;
	}

	public void setRl_redline_no(String rl_redline_no) {
		this.rl_redline_no = rl_redline_no;
	}

	public void setCreate_from(String create_from) {
		this.create_from = create_from;
	}

	public void setContract_type(String contract_type) {
		this.contract_type = contract_type;
	}

	public void setOwn_type(String own_type) {
		this.own_type = own_type;
	}

	public void setCommit_flag(String commit_flag) {
		this.commit_flag = commit_flag;
	}

	public void setSpecial_error(String special_error) {
		this.special_error = special_error;
	}

	public void setLot_status(String lot_status) {
		this.lot_status = lot_status;
	}

	public void setLam_flag(String lam_flag) {
		this.lam_flag = lam_flag;
	}

	public void setLot_no(String lot_no) {
		this.lot_no = lot_no;
	}

	public void setLu_term_adjust(String lu_term_adjust) {
		this.lu_term_adjust = lu_term_adjust;
	}

	public void setRedline_no(String redline_no) {
		this.redline_no = redline_no;
	}

	public void setFloor_area(double floor_area) {
		this.floor_area = floor_area;
	}

	public void setBranch_name(String branch_name) {
		this.branch_name = branch_name;
	}

	public void setValid_flag(String valid_flag) {
		this.valid_flag = valid_flag;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public void setLand_source(String land_source) {
		this.land_source = land_source;
	}

	public void setSolution(String solution) {
		this.solution = solution;
	}

	public void setLu_funnew(String lu_funnew) {
		this.lu_funnew = lu_funnew;
	}

	public void setLu_codenew(String lu_codenew) {
		this.lu_codenew = lu_codenew;
	}

	public void setAssem_flag(String assem_flag) {
		this.assem_flag = assem_flag;
	}

	public void setLam_no(String lam_no) {
		this.lam_no = lam_no;
	}

	public void setHist_flag(String hist_flag) {
		this.hist_flag = hist_flag;
	}

	public void setBirth_date(Date birth_date) {
		this.birth_date = birth_date;
	}

	public void setDeath_date(Date death_date) {
		this.death_date = death_date;
	}

	public void setLam_stage(String lam_stage) {
		this.lam_stage = lam_stage;
	}

	public void setBu_noinsert_flag(String bu_noinsert_flag) {
		this.bu_noinsert_flag = bu_noinsert_flag;
	}

	public void setLu_function_gb(String lu_function_gb) {
		this.lu_function_gb = lu_function_gb;
	}

	public void setLu_code_gb(String lu_code_gb) {
		this.lu_code_gb = lu_code_gb;
	}

	public void setSite_key(String site_key) {
		this.site_key = site_key;
	}

	public void setParcel_code(String parcel_code) {
		this.parcel_code = parcel_code;
	}

	public String getPar_id() {
		return par_id;
	}

	public String getParcel_no() {
		return parcel_no;
	}

	public String getContract_no() {
		return contract_no;
	}

	public String getLu_function() {
		return lu_function;
	}

	public String getLu_code() {
		return lu_code;
	}

	public double getLu_area() {
		return lu_area;
	}

	public String getLand_grade() {
		return land_grade;
	}

	public double getAdj_street_coef() {
		return adj_street_coef;
	}

	public String getLand_type() {
		return land_type;
	}

	public String getEstate_state() {
		return estate_state;
	}

	public String getLoc_canton() {
		return loc_canton;
	}

	public String getPre_lot_no() {
		return pre_lot_no;
	}

	public String getPro_lot_no() {
		return pro_lot_no;
	}

	public double getBase_area() {
		return base_area;
	}

	public double getLu_term() {
		return lu_term;
	}

	public Date getStart_date() {
		return start_date;
	}

	public Date getEnd_date() {
		return end_date;
	}

	public String getLu_location() {
		return lu_location;
	}

	public String getNo_modify_flag() {
		return no_modify_flag;
	}

	public String getBack_flag() {
		return back_flag;
	}

	public String getItem() {
		return item;
	}

	public String getRel_lot_no() {
		return rel_lot_no;
	}

	public String getChg_type() {
		return chg_type;
	}

	public String getDoc_doc_no() {
		return doc_doc_no;
	}

	public String getLp_lu_proj_no() {
		return lp_lu_proj_no;
	}

	public String getRl_redline_no() {
		return rl_redline_no;
	}

	public String getCreate_from() {
		return create_from;
	}

	public String getContract_type() {
		return contract_type;
	}

	public String getOwn_type() {
		return own_type;
	}

	public String getCommit_flag() {
		return commit_flag;
	}

	public String getSpecial_error() {
		return special_error;
	}

	public String getLot_status() {
		return lot_status;
	}

	public String getLam_flag() {
		return lam_flag;
	}

	public String getLot_no() {
		return lot_no;
	}

	public String getLu_term_adjust() {
		return lu_term_adjust;
	}

	public String getRedline_no() {
		return redline_no;
	}

	public double getFloor_area() {
		return floor_area;
	}

	public String getBranch_name() {
		return branch_name;
	}

	public String getValid_flag() {
		return valid_flag;
	}

	public String getRemark() {
		return remark;
	}

	public String getLand_source() {
		return land_source;
	}

	public String getSolution() {
		return solution;
	}

	public String getLu_funnew() {
		return lu_funnew;
	}

	public String getLu_codenew() {
		return lu_codenew;
	}

	public String getAssem_flag() {
		return assem_flag;
	}

	public String getLam_no() {
		return lam_no;
	}

	public String getHist_flag() {
		return hist_flag;
	}

	public Date getBirth_date() {
		return birth_date;
	}

	public Date getDeath_date() {
		return death_date;
	}

	public String getLam_stage() {
		return lam_stage;
	}

	public String getBu_noinsert_flag() {
		return bu_noinsert_flag;
	}

	public String getLu_function_gb() {
		return lu_function_gb;
	}

	public String getLu_code_gb() {
		return lu_code_gb;
	}

	public String getSite_key() {
		return site_key;
	}

	public String getParcel_code() {
		return parcel_code;
	}

}