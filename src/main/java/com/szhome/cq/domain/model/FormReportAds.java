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
@Table(name = "FORM_REPORT_ADDRESS")
public class FormReportAds extends BaseDomain<FormReportAds> {
	private double form_id;
	private String form_name;
	private double parent_id;
	private double turn;
	private String url;
	private String icon;
	private Date create_date;
	private String creator;
	private String busnode;

	public FormReportAds(){
		super();
		this.t = FormReportAds.class;
	}

	public void setForm_id(double form_id) {
		this.form_id = form_id;
	}

	public void setForm_name(String form_name) {
		this.form_name = form_name;
	}

	public void setParent_id(double parent_id) {
		this.parent_id = parent_id;
	}

	public void setTurn(double turn) {
		this.turn = turn;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public void setBusnode(String busnode) {
		this.busnode = busnode;
	}

	public double getForm_id() {
		return form_id;
	}

	public String getForm_name() {
		return form_name;
	}

	public double getParent_id() {
		return parent_id;
	}

	public double getTurn() {
		return turn;
	}

	public String getUrl() {
		return url;
	}

	public String getIcon() {
		return icon;
	}

	public Date getCreate_date() {
		return create_date;
	}

	public String getCreator() {
		return creator;
	}

	public String getBusnode() {
		return busnode;
	}

}
