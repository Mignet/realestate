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
@Table(name = "CFIG_DIC_TYPE")
public class DicType extends BaseDomain<DicType> {
	@Id
	private String dic_type_id;
	private String b_deleteflag;
	private String dic_type_code;
	private String dic_type_des;
	private String dic_type_value;

	public DicType(){
		super();
		this.t = DicType.class;
	}

	public DicType(String dic_type_id){
		super();
		this.dic_type_id = dic_type_id;
	}

	public void setDic_type_id(String dic_type_id) {
		this.dic_type_id = dic_type_id;
	}

	public void setB_deleteflag(String b_deleteflag) {
		this.b_deleteflag = b_deleteflag;
	}

	public void setDic_type_code(String dic_type_code) {
		this.dic_type_code = dic_type_code;
	}

	public void setDic_type_des(String dic_type_des) {
		this.dic_type_des = dic_type_des;
	}

	public void setDic_type_value(String dic_type_value) {
		this.dic_type_value = dic_type_value;
	}

	public String getDic_type_id() {
		return dic_type_id;
	}

	public String getB_deleteflag() {
		return b_deleteflag;
	}

	public String getDic_type_code() {
		return dic_type_code;
	}

	public String getDic_type_des() {
		return dic_type_des;
	}

	public String getDic_type_value() {
		return dic_type_value;
	}

}
