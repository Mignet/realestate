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
@Table(name = "plat_dict_class")
public class DictClass extends BaseDomain<DictClass> {
	@Id
	private double class_id;
	private double parent_class_id;
	private double product_id;
	private String class_name;
	private String class_code;
	private String remark;
	private String editor;
	private Date edit_date;

	public DictClass(){
		super();
		this.t = DictClass.class;
	}

	public void setClass_id(double class_id) {
		this.class_id = class_id;
	}

	public void setParent_class_id(double parent_class_id) {
		this.parent_class_id = parent_class_id;
	}

	public void setProduct_id(double product_id) {
		this.product_id = product_id;
	}

	public void setClass_name(String class_name) {
		this.class_name = class_name;
	}

	public void setClass_code(String class_code) {
		this.class_code = class_code;
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

	public double getClass_id() {
		return class_id;
	}

	public double getParent_class_id() {
		return parent_class_id;
	}

	public double getProduct_id() {
		return product_id;
	}

	public String getClass_name() {
		return class_name;
	}

	public String getClass_code() {
		return class_code;
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

}
