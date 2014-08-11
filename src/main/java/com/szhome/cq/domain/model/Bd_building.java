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
@Table(name = "bd_building")
public class Bd_building extends BaseDomain<Bd_building> {
	/**报建证号 */
	private String rptid;
	/***创建人id */
	private String inputer_no;
	/**o创建人姓名 */
	private String inputer;
	/***创建日期 */
	private Date input_date;
	/**o最后修改人雇员号 */
	private double modifier_no;
	/**楼宇最新修改人 */
	private String modifier;
	/**楼宇最新修改日期 */
	private Date modify_date;
	/**预售、在建工程、竣工、灭失 */
	private String bldg_status;
	/**默认1为有效，0为逻辑删除或作废 */
	private String data_state;
	/**11：新建，测绘成果
		12：新建，补录\手工录入
		01：迁移：旧产权系统
		02：迁移：市场系统 */
	private String data_from;
	/**影像图id */
	private String yxt_id;
	/**测绘对应建筑物id */
	private String fcch_bldg_id;
	/**流水号 */
	@Id
	private String bldg_id;
	/**建筑物编码（深标） */
	private String bldg_code_sz;
	/**建筑物编码（国标）（预留字段） */
	private String bldg_code_gb;
	/**建筑物编码（临时编码）（数据迁移、楼盘表建库时编号） */
	private String bldg_code_tmp;
	/**宗地id */
	private String par_id;
	/**测绘报告首页：项目名称 */
	private String project_name;
	/**栋号 */
	private String rise_no;
	/***楼名称及栋号 保留，兼容旧数据 */
	private String bldg_name_no;
	/***所在行政区 取值：词典 */
	private String division;
	/**地址（路） */
	private String road;
	/**所在街道 */
	private String street;
	/**地址（门牌号） */
	private String house_num;
	/**坐落 */
	private String location;
	/**o宗地号及支号 */
	private String par_no_subno;
	/**o房屋性质 */
	private String bldg_attr;
	/***房屋类型 */
	private String bldg_type;
	/***房屋用途（规划） */
	private String bldg_usage;
	/**房屋用途（现状）测绘报告 */
	private String bldg_usage_now;
	/***房屋结构 取值：词典 */
	private String bldg_structure;
	/**o房屋层数 */
	private double bldg_floors;
	/**o开工日期 */
	private Date const_startdate;
	/**o竣工日期 */
	private Date const_enddate;
	/**土地出让年限 */
	private double lu_term;
	/**出让起始日期 */
	private Date start_date;
	/**出让终止日期 */
	private Date end_date;
	/**测绘报告房屋建筑面积总表页：基底面积 */
	private double base_area;
	/**测绘报告房屋建筑面积总表页：总建筑面积 */
	private double built_up_area;
	/**o其他面积 */
	private double other_area;
	/**o分摊用地面积 */
	private double shared_lu_area;
	/**测绘报告房屋建筑面积总表页：架空层  */
	private double b_jkc_count;
	/**测绘报告房屋建筑面积总表页：转换层 */
	private double b_zhc_count;
	/**测绘报告房屋建筑面积总表页：设备层 */
	private double b_sbc_count;
	/**测绘报告房屋建筑面积总表页：避难层 */
	private double b_bnc_count;
	/**测绘报告房屋建筑面积总表页：地面以上面积 */
	private double b_up_build_area;
	/**测绘报告房屋建筑面积总表页：半地下室面积 */
	private double b_ather_build_area;
	/**测绘报告房屋建筑面积总表页：地下室面积 */
	private double b_down_build_area;
	/**测绘报告房屋建筑面积总表页：x坐标 */
	private double b_build_pos_x;
	/**测绘报告房屋建筑面积总表页：y坐标 */
	private double b_build_pos_y;
	/**测绘报告房屋建筑面积总表页：建筑高度 */
	private double b_build_height;
	/**测绘报告房屋建筑面积总表页：裙楼层数 */
	private double b_skirt_count;
	/**测绘报告房屋建筑面积总表页：塔楼层数 */
	private double b_tower_count;
	/**测绘报告房屋建筑面积总表页：公用建筑面积合计 */
	private double b_common_area;
	/**测绘报告房屋建筑面积总表页：应分摊公用建筑面积 */
	private double b_apportion_common_area;
	/**测绘报告房屋建筑面积总表页：不应分摊公用建筑面积 */
	private double b_noapportion_common_area;
	/**测绘报告房屋建筑面积总表页：地面以上层数 */
	private double b_up_build_floor;
	/**测绘报告房屋建筑面积总表页：地面以下层数 */
	private double b_down_build_floor;
	/**半地下室层数 */
	private double ather_build_floor;
	/**房屋套数 */
	private double unit_suits;
	/**报建时间 */
	private Date rptdate;

	public Bd_building(){
		super();
		this.t = Bd_building.class;
	}

	public Bd_building(String bldg_id){
		super();
		this.bldg_id = bldg_id;
	}

	public void setRptid(String rptid) {
		this.rptid = rptid;
	}

	public void setInputer_no(String inputer_no) {
		this.inputer_no = inputer_no;
	}

	public void setInputer(String inputer) {
		this.inputer = inputer;
	}

	public void setInput_date(Date input_date) {
		this.input_date = input_date;
	}

	public void setModifier_no(double modifier_no) {
		this.modifier_no = modifier_no;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	public void setModify_date(Date modify_date) {
		this.modify_date = modify_date;
	}

	public void setBldg_status(String bldg_status) {
		this.bldg_status = bldg_status;
	}

	public void setData_state(String data_state) {
		this.data_state = data_state;
	}

	public void setData_from(String data_from) {
		this.data_from = data_from;
	}

	public void setYxt_id(String yxt_id) {
		this.yxt_id = yxt_id;
	}

	public void setFcch_bldg_id(String fcch_bldg_id) {
		this.fcch_bldg_id = fcch_bldg_id;
	}

	public void setBldg_id(String bldg_id) {
		this.bldg_id = bldg_id;
	}

	public void setBldg_code_sz(String bldg_code_sz) {
		this.bldg_code_sz = bldg_code_sz;
	}

	public void setBldg_code_gb(String bldg_code_gb) {
		this.bldg_code_gb = bldg_code_gb;
	}

	public void setBldg_code_tmp(String bldg_code_tmp) {
		this.bldg_code_tmp = bldg_code_tmp;
	}

	public void setPar_id(String par_id) {
		this.par_id = par_id;
	}

	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}

	public void setRise_no(String rise_no) {
		this.rise_no = rise_no;
	}

	public void setBldg_name_no(String bldg_name_no) {
		this.bldg_name_no = bldg_name_no;
	}

	public void setDivision(String division) {
		this.division = division;
	}

	public void setRoad(String road) {
		this.road = road;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public void setHouse_num(String house_num) {
		this.house_num = house_num;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public void setPar_no_subno(String par_no_subno) {
		this.par_no_subno = par_no_subno;
	}

	public void setBldg_attr(String bldg_attr) {
		this.bldg_attr = bldg_attr;
	}

	public void setBldg_type(String bldg_type) {
		this.bldg_type = bldg_type;
	}

	public void setBldg_usage(String bldg_usage) {
		this.bldg_usage = bldg_usage;
	}

	public void setBldg_usage_now(String bldg_usage_now) {
		this.bldg_usage_now = bldg_usage_now;
	}

	public void setBldg_structure(String bldg_structure) {
		this.bldg_structure = bldg_structure;
	}

	public void setBldg_floors(double bldg_floors) {
		this.bldg_floors = bldg_floors;
	}

	public void setConst_startdate(Date const_startdate) {
		this.const_startdate = const_startdate;
	}

	public void setConst_enddate(Date const_enddate) {
		this.const_enddate = const_enddate;
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

	public void setBase_area(double base_area) {
		this.base_area = base_area;
	}

	public void setBuilt_up_area(double built_up_area) {
		this.built_up_area = built_up_area;
	}

	public void setOther_area(double other_area) {
		this.other_area = other_area;
	}

	public void setShared_lu_area(double shared_lu_area) {
		this.shared_lu_area = shared_lu_area;
	}

	public void setB_jkc_count(double b_jkc_count) {
		this.b_jkc_count = b_jkc_count;
	}

	public void setB_zhc_count(double b_zhc_count) {
		this.b_zhc_count = b_zhc_count;
	}

	public void setB_sbc_count(double b_sbc_count) {
		this.b_sbc_count = b_sbc_count;
	}

	public void setB_bnc_count(double b_bnc_count) {
		this.b_bnc_count = b_bnc_count;
	}

	public void setB_up_build_area(double b_up_build_area) {
		this.b_up_build_area = b_up_build_area;
	}

	public void setB_ather_build_area(double b_ather_build_area) {
		this.b_ather_build_area = b_ather_build_area;
	}

	public void setB_down_build_area(double b_down_build_area) {
		this.b_down_build_area = b_down_build_area;
	}

	public void setB_build_pos_x(double b_build_pos_x) {
		this.b_build_pos_x = b_build_pos_x;
	}

	public void setB_build_pos_y(double b_build_pos_y) {
		this.b_build_pos_y = b_build_pos_y;
	}

	public void setB_build_height(double b_build_height) {
		this.b_build_height = b_build_height;
	}

	public void setB_skirt_count(double b_skirt_count) {
		this.b_skirt_count = b_skirt_count;
	}

	public void setB_tower_count(double b_tower_count) {
		this.b_tower_count = b_tower_count;
	}

	public void setB_common_area(double b_common_area) {
		this.b_common_area = b_common_area;
	}

	public void setB_apportion_common_area(double b_apportion_common_area) {
		this.b_apportion_common_area = b_apportion_common_area;
	}

	public void setB_noapportion_common_area(double b_noapportion_common_area) {
		this.b_noapportion_common_area = b_noapportion_common_area;
	}

	public void setB_up_build_floor(double b_up_build_floor) {
		this.b_up_build_floor = b_up_build_floor;
	}

	public void setB_down_build_floor(double b_down_build_floor) {
		this.b_down_build_floor = b_down_build_floor;
	}

	public void setAther_build_floor(double ather_build_floor) {
		this.ather_build_floor = ather_build_floor;
	}

	public void setUnit_suits(double unit_suits) {
		this.unit_suits = unit_suits;
	}

	public void setRptdate(Date rptdate) {
		this.rptdate = rptdate;
	}

	public String getRptid() {
		return rptid;
	}

	public String getInputer_no() {
		return inputer_no;
	}

	public String getInputer() {
		return inputer;
	}

	public Date getInput_date() {
		return input_date;
	}

	public double getModifier_no() {
		return modifier_no;
	}

	public String getModifier() {
		return modifier;
	}

	public Date getModify_date() {
		return modify_date;
	}

	public String getBldg_status() {
		return bldg_status;
	}

	public String getData_state() {
		return data_state;
	}

	public String getData_from() {
		return data_from;
	}

	public String getYxt_id() {
		return yxt_id;
	}

	public String getFcch_bldg_id() {
		return fcch_bldg_id;
	}

	public String getBldg_id() {
		return bldg_id;
	}

	public String getBldg_code_sz() {
		return bldg_code_sz;
	}

	public String getBldg_code_gb() {
		return bldg_code_gb;
	}

	public String getBldg_code_tmp() {
		return bldg_code_tmp;
	}

	public String getPar_id() {
		return par_id;
	}

	public String getProject_name() {
		return project_name;
	}

	public String getRise_no() {
		return rise_no;
	}

	public String getBldg_name_no() {
		return bldg_name_no;
	}

	public String getDivision() {
		return division;
	}

	public String getRoad() {
		return road;
	}

	public String getStreet() {
		return street;
	}

	public String getHouse_num() {
		return house_num;
	}

	public String getLocation() {
		return location;
	}

	public String getPar_no_subno() {
		return par_no_subno;
	}

	public String getBldg_attr() {
		return bldg_attr;
	}

	public String getBldg_type() {
		return bldg_type;
	}

	public String getBldg_usage() {
		return bldg_usage;
	}

	public String getBldg_usage_now() {
		return bldg_usage_now;
	}

	public String getBldg_structure() {
		return bldg_structure;
	}

	public double getBldg_floors() {
		return bldg_floors;
	}

	public Date getConst_startdate() {
		return const_startdate;
	}

	public Date getConst_enddate() {
		return const_enddate;
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

	public double getBase_area() {
		return base_area;
	}

	public double getBuilt_up_area() {
		return built_up_area;
	}

	public double getOther_area() {
		return other_area;
	}

	public double getShared_lu_area() {
		return shared_lu_area;
	}

	public double getB_jkc_count() {
		return b_jkc_count;
	}

	public double getB_zhc_count() {
		return b_zhc_count;
	}

	public double getB_sbc_count() {
		return b_sbc_count;
	}

	public double getB_bnc_count() {
		return b_bnc_count;
	}

	public double getB_up_build_area() {
		return b_up_build_area;
	}

	public double getB_ather_build_area() {
		return b_ather_build_area;
	}

	public double getB_down_build_area() {
		return b_down_build_area;
	}

	public double getB_build_pos_x() {
		return b_build_pos_x;
	}

	public double getB_build_pos_y() {
		return b_build_pos_y;
	}

	public double getB_build_height() {
		return b_build_height;
	}

	public double getB_skirt_count() {
		return b_skirt_count;
	}

	public double getB_tower_count() {
		return b_tower_count;
	}

	public double getB_common_area() {
		return b_common_area;
	}

	public double getB_apportion_common_area() {
		return b_apportion_common_area;
	}

	public double getB_noapportion_common_area() {
		return b_noapportion_common_area;
	}

	public double getB_up_build_floor() {
		return b_up_build_floor;
	}

	public double getB_down_build_floor() {
		return b_down_build_floor;
	}

	public double getAther_build_floor() {
		return ather_build_floor;
	}

	public double getUnit_suits() {
		return unit_suits;
	}

	public Date getRptdate() {
		return rptdate;
	}

}