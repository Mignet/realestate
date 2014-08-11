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
@Table(name = "BDC_PRESALE_INFO")
public class PresaleLicense extends BaseDomain<PresaleLicense> {
	@Id
	private double reg_no;
	private String pre_con_no;
	private String pre_lic_no;
	private double holder_id;
	private String pro_location;
	private String parcel_no;
	private String land_gra_con;
	private String dev_fiem_no;
	private float appro_pre_area;
	private String appro_pro_build;
	private String remark;
	private String pre_lic_text;
	private double b_deleteflag;

	public PresaleLicense(){
		super();
		this.t = PresaleLicense.class;
	}

	public PresaleLicense(double reg_no){
		super();
		this.reg_no = reg_no;
	}

	public void setReg_no(double reg_no) {
		this.reg_no = reg_no;
	}

	public void setPre_con_no(String pre_con_no) {
		this.pre_con_no = pre_con_no;
	}

	public void setPre_lic_no(String pre_lic_no) {
		this.pre_lic_no = pre_lic_no;
	}

	public void setHolder_id(double holder_id) {
		this.holder_id = holder_id;
	}

	public void setPro_location(String pro_location) {
		this.pro_location = pro_location;
	}

	public void setParcel_no(String parcel_no) {
		this.parcel_no = parcel_no;
	}

	public void setLand_gra_con(String land_gra_con) {
		this.land_gra_con = land_gra_con;
	}

	public void setDev_fiem_no(String dev_fiem_no) {
		this.dev_fiem_no = dev_fiem_no;
	}

	public void setAppro_pre_area(float appro_pre_area) {
		this.appro_pre_area = appro_pre_area;
	}

	public void setAppro_pro_build(String appro_pro_build) {
		this.appro_pro_build = appro_pro_build;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public void setPre_lic_text(String pre_lic_text) {
		this.pre_lic_text = pre_lic_text;
	}

	public void setB_deleteflag(double b_deleteflag) {
		this.b_deleteflag = b_deleteflag;
	}

	public double getReg_no() {
		return reg_no;
	}

	public String getPre_con_no() {
		return pre_con_no;
	}

	public String getPre_lic_no() {
		return pre_lic_no;
	}

	public double getHolder_id() {
		return holder_id;
	}

	public String getPro_location() {
		return pro_location;
	}

	public String getParcel_no() {
		return parcel_no;
	}

	public String getLand_gra_con() {
		return land_gra_con;
	}

	public String getDev_fiem_no() {
		return dev_fiem_no;
	}

	public float getAppro_pre_area() {
		return appro_pre_area;
	}

	public String getAppro_pro_build() {
		return appro_pro_build;
	}

	public String getRemark() {
		return remark;
	}

	public String getPre_lic_text() {
		return pre_lic_text;
	}

	public double getB_deleteflag() {
		return b_deleteflag;
	}

}

