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
@Table(name = "CFIG_DOC_TEMP")
public class ComLanguage extends BaseDomain<ComLanguage> {
	@Id
	private String language_id;
	private String language_name;
	private String language_content;
	private String b_deleteflag;
	private String bus_type_id;
	private String temp_type;

	public ComLanguage(){
		super();
		this.t = ComLanguage.class;
	}

	public ComLanguage(String language_id){
		super();
		this.language_id = language_id;
	}

	public void setLanguage_id(String language_id) {
		this.language_id = language_id;
	}

	public void setLanguage_name(String language_name) {
		this.language_name = language_name;
	}

	public void setLanguage_content(String language_content) {
		this.language_content = language_content;
	}

	public void setB_deleteflag(String b_deleteflag) {
		this.b_deleteflag = b_deleteflag;
	}

	public String getLanguage_id() {
		return language_id;
	}

	public String getLanguage_name() {
		return language_name;
	}

	public String getLanguage_content() {
		return language_content;
	}

	public String getB_deleteflag() {
		return b_deleteflag;
	}

	public String getBus_type_id() {
		return bus_type_id;
	}

	public void setBus_type_id(String bus_type_id) {
		this.bus_type_id = bus_type_id;
	}

	public String getTemp_type() {
		return temp_type;
	}

	public void setTemp_type(String temp_type) {
		this.temp_type = temp_type;
	}

}
