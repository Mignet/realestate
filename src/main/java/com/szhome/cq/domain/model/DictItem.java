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
@Table(name = "plat_dict_item")
public class DictItem extends BaseDomain<DictItem> {
	@Id
	private double item_id;
	private String name;
	private String value;
	private double turn;
	private double valid_flag;
	private String remark;
	private String editor;
	private Date edit_date;
	private double class_id;

	public DictItem(){
		super();
		this.t = DictItem.class;
	}

	public void setItem_id(double item_id) {
		this.item_id = item_id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public void setTurn(double turn) {
		this.turn = turn;
	}

	public void setValid_flag(double valid_flag) {
		this.valid_flag = valid_flag;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public void setEditor(String editor) {
		this.editor = editor;
	}

	public void setEdit_date(Date edit_date) {
		this.edit_date = edit_date;
	}

	public void setClass_id(double class_id) {
		this.class_id = class_id;
	}

	public double getItem_id() {
		return item_id;
	}

	public String getName() {
		return name;
	}

	public String getValue() {
		return value;
	}

	public double getTurn() {
		return turn;
	}

	public double getValid_flag() {
		return valid_flag;
	}

	public String getRemark() {
		return remark;
	}

	public String getEditor() {
		return editor;
	}

	public Date getEdit_date() {
		return edit_date;
	}

	public double getClass_id() {
		return class_id;
	}

}
