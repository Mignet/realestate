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
@Table(name = "cfig_dic_item")
public class DicItem extends BaseDomain<DicItem> {
	@Id
	private String dic_item_id;
	private String dic_type_id;
	private String b_deleteflag;
	private String dic_item_code;
	private String dic_item_des;
	private String dic_item_value;

	public DicItem(){
		super();
		this.t = DicItem.class;
	}

	public DicItem(String dic_item_id){
		super();
		this.dic_item_id = dic_item_id;
	}

	public void setDic_item_id(String dic_item_id) {
		this.dic_item_id = dic_item_id;
	}

	public void setDic_type_id(String dic_type_id) {
		this.dic_type_id = dic_type_id;
	}

	public void setB_deleteflag(String b_deleteflag) {
		this.b_deleteflag = b_deleteflag;
	}

	public void setDic_item_code(String dic_item_code) {
		this.dic_item_code = dic_item_code;
	}

	public void setDic_item_des(String dic_item_des) {
		this.dic_item_des = dic_item_des;
	}

	public void setDic_item_value(String dic_item_value) {
		this.dic_item_value = dic_item_value;
	}

	public String getDic_item_id() {
		return dic_item_id;
	}

	public String getDic_type_id() {
		return dic_type_id;
	}

	public String getB_deleteflag() {
		return b_deleteflag;
	}

	public String getDic_item_code() {
		return dic_item_code;
	}

	public String getDic_item_des() {
		return dic_item_des;
	}

	public String getDic_item_value() {
		return dic_item_value;
	}

}
