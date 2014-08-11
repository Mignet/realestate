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
@Table(name = "EST_PARCEL")
public class Land extends BaseDomain<Land> {
	private String parcel_area;
	@Id
	private String parcel_id;
	private String b_deleteflag;
	private String parcel_code;
	private String use_area;
	private String single_area;
	private String glebe_area;
	private String land_address;
	private String real_usage;
	private String plan_usage;
	private String attribute;
	private String use_right_type;
	private String sources_name;
	private String sources_no;
	private Date sources_date;
	private String land_attribute;
	private String land_grade;
	private String use_per;
	private Date start_date;
	private Date end_date;
	private double build_total_area;
	private double build_plot_ratio;
	private double building_density;
	private double building_height_limit;
	private double constructioin_area;
	private String constructioin_type;
	private String dedare_ownership;
	private String pre_parcel_code;

	public Land(){
		super();
		this.t = Land.class;
	}

	public Land(String parcel_id){
		super();
		this.parcel_id = parcel_id;
	}

	public void setParcel_area(String parcel_area) {
		this.parcel_area = parcel_area;
	}

	public void setParcel_id(String parcel_id) {
		this.parcel_id = parcel_id;
	}

	public void setB_deleteflag(String b_deleteflag) {
		this.b_deleteflag = b_deleteflag;
	}

	public void setParcel_code(String parcel_code) {
		this.parcel_code = parcel_code;
	}

	public void setUse_area(String use_area) {
		this.use_area = use_area;
	}

	public void setSingle_area(String single_area) {
		this.single_area = single_area;
	}

	public void setGlebe_area(String glebe_area) {
		this.glebe_area = glebe_area;
	}

	public void setLand_address(String land_address) {
		this.land_address = land_address;
	}

	public void setReal_usage(String real_usage) {
		this.real_usage = real_usage;
	}

	public void setPlan_usage(String plan_usage) {
		this.plan_usage = plan_usage;
	}

	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}

	public void setUse_right_type(String use_right_type) {
		this.use_right_type = use_right_type;
	}

	public void setSources_name(String sources_name) {
		this.sources_name = sources_name;
	}

	public void setSources_no(String sources_no) {
		this.sources_no = sources_no;
	}

	public void setSources_date(Date sources_date) {
		this.sources_date = sources_date;
	}

	public void setLand_attribute(String land_attribute) {
		this.land_attribute = land_attribute;
	}

	public void setLand_grade(String land_grade) {
		this.land_grade = land_grade;
	}

	public void setUse_per(String use_per) {
		this.use_per = use_per;
	}

	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}

	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}

	public void setBuild_total_area(double build_total_area) {
		this.build_total_area = build_total_area;
	}

	public void setBuild_plot_ratio(double build_plot_ratio) {
		this.build_plot_ratio = build_plot_ratio;
	}

	public void setBuilding_density(double building_density) {
		this.building_density = building_density;
	}

	public void setBuilding_height_limit(double building_height_limit) {
		this.building_height_limit = building_height_limit;
	}

	public void setConstructioin_area(double constructioin_area) {
		this.constructioin_area = constructioin_area;
	}

	public void setConstructioin_type(String constructioin_type) {
		this.constructioin_type = constructioin_type;
	}

	public void setDedare_ownership(String dedare_ownership) {
		this.dedare_ownership = dedare_ownership;
	}

	public void setPre_parcel_code(String pre_parcel_code) {
		this.pre_parcel_code = pre_parcel_code;
	}

	public String getParcel_area() {
		return parcel_area;
	}

	public String getParcel_id() {
		return parcel_id;
	}

	public String getB_deleteflag() {
		return b_deleteflag;
	}

	public String getParcel_code() {
		return parcel_code;
	}

	public String getUse_area() {
		return use_area;
	}

	public String getSingle_area() {
		return single_area;
	}

	public String getGlebe_area() {
		return glebe_area;
	}

	public String getLand_address() {
		return land_address;
	}

	public String getReal_usage() {
		return real_usage;
	}

	public String getPlan_usage() {
		return plan_usage;
	}

	public String getAttribute() {
		return attribute;
	}

	public String getUse_right_type() {
		return use_right_type;
	}

	public String getSources_name() {
		return sources_name;
	}

	public String getSources_no() {
		return sources_no;
	}

	public Date getSources_date() {
		return sources_date;
	}

	public String getLand_attribute() {
		return land_attribute;
	}

	public String getLand_grade() {
		return land_grade;
	}

	public String getUse_per() {
		return use_per;
	}

	public Date getStart_date() {
		return start_date;
	}

	public Date getEnd_date() {
		return end_date;
	}

	public double getBuild_total_area() {
		return build_total_area;
	}

	public double getBuild_plot_ratio() {
		return build_plot_ratio;
	}

	public double getBuilding_density() {
		return building_density;
	}

	public double getBuilding_height_limit() {
		return building_height_limit;
	}

	public double getConstructioin_area() {
		return constructioin_area;
	}

	public String getConstructioin_type() {
		return constructioin_type;
	}

	public String getDedare_ownership() {
		return dedare_ownership;
	}

	public String getPre_parcel_code() {
		return pre_parcel_code;
	}

}
