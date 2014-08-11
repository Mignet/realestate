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
@Table(name = "PLATT_USERS")
public class User extends BaseDomain<User> {
	@Id
	private String user_id;
	private String user_name;
	private String nick_name;
	private String account;
	private String password;
	private String gender;
	private String blood_type;
	private Date birthday;
	private String comefrom;
	private String email;
	private String mobile_phone;
	private String office_phone;
	private String home_phone;
	private String id_no;
	private String status;
	private String remark;
	private String creator;
	private Date create_date;
	private String editor;
	private Date edit_date;
	private Double weight;

	public User(){
		super();
		this.t = User.class;
	}

	public User(String user_id){
		super();
		this.user_id = user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public void setNick_name(String nick_name) {
		this.nick_name = nick_name;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void setBlood_type(String blood_type) {
		this.blood_type = blood_type;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public void setComefrom(String comefrom) {
		this.comefrom = comefrom;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setMobile_phone(String mobile_phone) {
		this.mobile_phone = mobile_phone;
	}

	public void setOffice_phone(String office_phone) {
		this.office_phone = office_phone;
	}

	public void setHome_phone(String home_phone) {
		this.home_phone = home_phone;
	}

	public void setId_no(String id_no) {
		this.id_no = id_no;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}

	public void setEditor(String editor) {
		this.editor = editor;
	}

	public void setEdit_date(Date edit_date) {
		this.edit_date = edit_date;
	}

	public String getUser_id() {
		return user_id;
	}

	public String getUser_name() {
		return user_name;
	}

	public String getNick_name() {
		return nick_name;
	}

	public String getAccount() {
		return account;
	}

	public String getPassword() {
		return password;
	}

	public String getGender() {
		return gender;
	}

	public String getBlood_type() {
		return blood_type;
	}

	public Date getBirthday() {
		return birthday;
	}

	public String getComefrom() {
		return comefrom;
	}

	public String getEmail() {
		return email;
	}

	public String getMobile_phone() {
		return mobile_phone;
	}

	public String getOffice_phone() {
		return office_phone;
	}

	public String getHome_phone() {
		return home_phone;
	}

	public String getId_no() {
		return id_no;
	}

	public String getStatus() {
		return status;
	}

	public String getRemark() {
		return remark;
	}

	public String getCreator() {
		return creator;
	}

	public Date getCreate_date() {
		return create_date;
	}

	public String getEditor() {
		return editor;
	}

	public Date getEdit_date() {
		return edit_date;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

}
