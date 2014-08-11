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
@Table(name = "EST_BUILDING")
public class Building extends BaseDomain<Building> {
	private String jdt_type;
	private String jdt_directory;
	private String lmt_name;
	private String lmt_tyep;
	private String lmt_directoty;
	private String state;
	private String build_no;
	@Id
	private String building_id;
	private String parcel_id;
	private String b_deleteflag;
	private String building_pre_code;
	private String building_code;
	private String building_name;
	private String community_name;
	private String parcel_code;
	private double layer_count;
	private double up_floor;
	private double down_floor;
	private double ather_floor;
	private String build_floor_remark;
	private double build_height;
	private String isskirt;
	private double skirt_count;
	private String istower;
	private double tower_count;
	private double build_ld_area;
	private double floor_area;
	private double pr_area;
	private double common_area;
	private double apportion_common_area;
	private double noapportion_common_area;
	private double build_pos_x;
	private double build_pos_y;
	private String jdt_name;

	public Building(){
		super();
		this.t = Building.class;
	}

	public Building(String building_id){
		super();
		this.building_id = building_id;
	}

	public void setJdt_type(String jdt_type) {
		this.jdt_type = jdt_type;
	}

	public void setJdt_directory(String jdt_directory) {
		this.jdt_directory = jdt_directory;
	}

	public void setLmt_name(String lmt_name) {
		this.lmt_name = lmt_name;
	}

	public void setLmt_tyep(String lmt_tyep) {
		this.lmt_tyep = lmt_tyep;
	}

	public void setLmt_directoty(String lmt_directoty) {
		this.lmt_directoty = lmt_directoty;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setBuild_no(String build_no) {
		this.build_no = build_no;
	}

	public void setBuilding_id(String building_id) {
		this.building_id = building_id;
	}

	public void setParcel_id(String parcel_id) {
		this.parcel_id = parcel_id;
	}

	public void setB_deleteflag(String b_deleteflag) {
		this.b_deleteflag = b_deleteflag;
	}

	public void setBuilding_pre_code(String building_pre_code) {
		this.building_pre_code = building_pre_code;
	}

	public void setBuilding_code(String building_code) {
		this.building_code = building_code;
	}

	public void setBuilding_name(String building_name) {
		this.building_name = building_name;
	}

	public void setCommunity_name(String community_name) {
		this.community_name = community_name;
	}

	public void setParcel_code(String parcel_code) {
		this.parcel_code = parcel_code;
	}

	public void setLayer_count(double layer_count) {
		this.layer_count = layer_count;
	}

	public void setUp_floor(double up_floor) {
		this.up_floor = up_floor;
	}

	public void setDown_floor(double down_floor) {
		this.down_floor = down_floor;
	}

	public void setAther_floor(double ather_floor) {
		this.ather_floor = ather_floor;
	}

	public void setBuild_floor_remark(String build_floor_remark) {
		this.build_floor_remark = build_floor_remark;
	}

	public void setBuild_height(double build_height) {
		this.build_height = build_height;
	}

	public void setIsskirt(String isskirt) {
		this.isskirt = isskirt;
	}

	public void setSkirt_count(double skirt_count) {
		this.skirt_count = skirt_count;
	}

	public void setIstower(String istower) {
		this.istower = istower;
	}

	public void setTower_count(double tower_count) {
		this.tower_count = tower_count;
	}

	public void setBuild_ld_area(double build_ld_area) {
		this.build_ld_area = build_ld_area;
	}

	public void setFloor_area(double floor_area) {
		this.floor_area = floor_area;
	}

	public void setPr_area(double pr_area) {
		this.pr_area = pr_area;
	}

	public void setCommon_area(double common_area) {
		this.common_area = common_area;
	}

	public void setApportion_common_area(double apportion_common_area) {
		this.apportion_common_area = apportion_common_area;
	}

	public void setNoapportion_common_area(double noapportion_common_area) {
		this.noapportion_common_area = noapportion_common_area;
	}

	public void setBuild_pos_x(double build_pos_x) {
		this.build_pos_x = build_pos_x;
	}

	public void setBuild_pos_y(double build_pos_y) {
		this.build_pos_y = build_pos_y;
	}

	public void setJdt_name(String jdt_name) {
		this.jdt_name = jdt_name;
	}

	public String getJdt_type() {
		return jdt_type;
	}

	public String getJdt_directory() {
		return jdt_directory;
	}

	public String getLmt_name() {
		return lmt_name;
	}

	public String getLmt_tyep() {
		return lmt_tyep;
	}

	public String getLmt_directoty() {
		return lmt_directoty;
	}

	public String getState() {
		return state;
	}

	public String getBuild_no() {
		return build_no;
	}

	public String getBuilding_id() {
		return building_id;
	}

	public String getParcel_id() {
		return parcel_id;
	}

	public String getB_deleteflag() {
		return b_deleteflag;
	}

	public String getBuilding_pre_code() {
		return building_pre_code;
	}

	public String getBuilding_code() {
		return building_code;
	}

	public String getBuilding_name() {
		return building_name;
	}

	public String getCommunity_name() {
		return community_name;
	}

	public String getParcel_code() {
		return parcel_code;
	}

	public double getLayer_count() {
		return layer_count;
	}

	public double getUp_floor() {
		return up_floor;
	}

	public double getDown_floor() {
		return down_floor;
	}

	public double getAther_floor() {
		return ather_floor;
	}

	public String getBuild_floor_remark() {
		return build_floor_remark;
	}

	public double getBuild_height() {
		return build_height;
	}

	public String getIsskirt() {
		return isskirt;
	}

	public double getSkirt_count() {
		return skirt_count;
	}

	public String getIstower() {
		return istower;
	}

	public double getTower_count() {
		return tower_count;
	}

	public double getBuild_ld_area() {
		return build_ld_area;
	}

	public double getFloor_area() {
		return floor_area;
	}

	public double getPr_area() {
		return pr_area;
	}

	public double getCommon_area() {
		return common_area;
	}

	public double getApportion_common_area() {
		return apportion_common_area;
	}

	public double getNoapportion_common_area() {
		return noapportion_common_area;
	}

	public double getBuild_pos_x() {
		return build_pos_x;
	}

	public double getBuild_pos_y() {
		return build_pos_y;
	}

	public String getJdt_name() {
		return jdt_name;
	}

}
