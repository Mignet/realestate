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
@Table(name = "EST_HOUSE")
public class House extends BaseDomain<House> {
	private String house_location;
	private String pro_name;
	@Id
	private String house_id;
	private String building_id;
	private String b_deleteflag;
	private String building_code;
	private String adv_house_code;
	private String flatlet_code;
	private String flatlet_code2;
	private String flatletname;
	private String roomname;
	private String at_floor;
	private double build_area;
	private double taonei_area;
	private double fentan_area;
	private String flatlet_usage;
	private double ft_glebe_area;
	private double ft_common_area;
	private double ft_build_ld_area;
	private double other_area;
	private String house_strut;
	private String house_type;
	private String house_kind;
	private String pre_house_code;

	public House(){
		super();
		this.t = House.class;
	}

	public House(String house_id){
		super();
		this.house_id = house_id;
	}

	public void setHouse_location(String house_location) {
		this.house_location = house_location;
	}

	public void setPro_name(String pro_name) {
		this.pro_name = pro_name;
	}

	public void setHouse_id(String house_id) {
		this.house_id = house_id;
	}

	public void setBuilding_id(String building_id) {
		this.building_id = building_id;
	}

	public void setB_deleteflag(String b_deleteflag) {
		this.b_deleteflag = b_deleteflag;
	}

	public void setBuilding_code(String building_code) {
		this.building_code = building_code;
	}

	public void setAdv_house_code(String adv_house_code) {
		this.adv_house_code = adv_house_code;
	}

	public void setFlatlet_code(String flatlet_code) {
		this.flatlet_code = flatlet_code;
	}

	public void setFlatlet_code2(String flatlet_code2) {
		this.flatlet_code2 = flatlet_code2;
	}

	public void setFlatletname(String flatletname) {
		this.flatletname = flatletname;
	}

	public void setRoomname(String roomname) {
		this.roomname = roomname;
	}

	public void setAt_floor(String at_floor) {
		this.at_floor = at_floor;
	}

	public void setBuild_area(double build_area) {
		this.build_area = build_area;
	}

	public void setTaonei_area(double taonei_area) {
		this.taonei_area = taonei_area;
	}

	public void setFentan_area(double fentan_area) {
		this.fentan_area = fentan_area;
	}

	public void setFlatlet_usage(String flatlet_usage) {
		this.flatlet_usage = flatlet_usage;
	}

	public void setFt_glebe_area(double ft_glebe_area) {
		this.ft_glebe_area = ft_glebe_area;
	}

	public void setFt_common_area(double ft_common_area) {
		this.ft_common_area = ft_common_area;
	}

	public void setFt_build_ld_area(double ft_build_ld_area) {
		this.ft_build_ld_area = ft_build_ld_area;
	}

	public void setOther_area(double other_area) {
		this.other_area = other_area;
	}

	public void setHouse_strut(String house_strut) {
		this.house_strut = house_strut;
	}

	public void setHouse_type(String house_type) {
		this.house_type = house_type;
	}

	public void setHouse_kind(String house_kind) {
		this.house_kind = house_kind;
	}

	public void setPre_house_code(String pre_house_code) {
		this.pre_house_code = pre_house_code;
	}

	public String getHouse_location() {
		return house_location;
	}

	public String getPro_name() {
		return pro_name;
	}

	public String getHouse_id() {
		return house_id;
	}

	public String getBuilding_id() {
		return building_id;
	}

	public String getB_deleteflag() {
		return b_deleteflag;
	}

	public String getBuilding_code() {
		return building_code;
	}

	public String getAdv_house_code() {
		return adv_house_code;
	}

	public String getFlatlet_code() {
		return flatlet_code;
	}

	public String getFlatlet_code2() {
		return flatlet_code2;
	}

	public String getFlatletname() {
		return flatletname;
	}

	public String getRoomname() {
		return roomname;
	}

	public String getAt_floor() {
		return at_floor;
	}

	public double getBuild_area() {
		return build_area;
	}

	public double getTaonei_area() {
		return taonei_area;
	}

	public double getFentan_area() {
		return fentan_area;
	}

	public String getFlatlet_usage() {
		return flatlet_usage;
	}

	public double getFt_glebe_area() {
		return ft_glebe_area;
	}

	public double getFt_common_area() {
		return ft_common_area;
	}

	public double getFt_build_ld_area() {
		return ft_build_ld_area;
	}

	public double getOther_area() {
		return other_area;
	}

	public String getHouse_strut() {
		return house_strut;
	}

	public String getHouse_type() {
		return house_type;
	}

	public String getHouse_kind() {
		return house_kind;
	}

	public String getPre_house_code() {
		return pre_house_code;
	}

}
