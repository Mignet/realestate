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
@Table(name = "bd_project")
public class Bd_project extends BaseDomain<Bd_project> {
	@Id
	private String prj_id;
	private String project_no;
	private String project_name;
	private String conscertificatenu;
	private String contract_no;
	private String project_dis;
	private double building_num;
	private String location;
	private double glebe_area;
	private double floor_area;
	private double build_bestrow_per;
	private String build_unit;

	public Bd_project(){
		super();
		this.t = Bd_project.class;
	}

	public Bd_project(String prj_id){
		super();
		this.prj_id = prj_id;
	}

	public void setPrj_id(String prj_id) {
		this.prj_id = prj_id;
	}

	public void setProject_no(String project_no) {
		this.project_no = project_no;
	}

	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}

	public void setConscertificatenu(String conscertificatenu) {
		this.conscertificatenu = conscertificatenu;
	}

	public void setContract_no(String contract_no) {
		this.contract_no = contract_no;
	}

	public void setProject_dis(String project_dis) {
		this.project_dis = project_dis;
	}

	public void setBuilding_num(double building_num) {
		this.building_num = building_num;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public void setGlebe_area(double glebe_area) {
		this.glebe_area = glebe_area;
	}

	public void setFloor_area(double floor_area) {
		this.floor_area = floor_area;
	}

	public void setBuild_bestrow_per(double build_bestrow_per) {
		this.build_bestrow_per = build_bestrow_per;
	}

	public void setBuild_unit(String build_unit) {
		this.build_unit = build_unit;
	}

	public String getPrj_id() {
		return prj_id;
	}

	public String getProject_no() {
		return project_no;
	}

	public String getProject_name() {
		return project_name;
	}

	public String getConscertificatenu() {
		return conscertificatenu;
	}

	public String getContract_no() {
		return contract_no;
	}

	public String getProject_dis() {
		return project_dis;
	}

	public double getBuilding_num() {
		return building_num;
	}

	public String getLocation() {
		return location;
	}

	public double getGlebe_area() {
		return glebe_area;
	}

	public double getFloor_area() {
		return floor_area;
	}

	public double getBuild_bestrow_per() {
		return build_bestrow_per;
	}

	public String getBuild_unit() {
		return build_unit;
	}

}