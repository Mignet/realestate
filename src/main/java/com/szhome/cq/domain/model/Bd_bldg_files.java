package com.szhome.cq.domain.model;

import java.sql.Blob;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.springjdbc.annotation.BaseDomain;
import com.springjdbc.annotation.Entity;
import com.springjdbc.annotation.Id;
import com.springjdbc.annotation.Table;

@Scope("prototype")
@Component
@Entity
@Table(name = "bd_bldg_files")
public class Bd_bldg_files extends BaseDomain<Bd_bldg_files> {
	@Id
	private String file_id;
	private String file_name;
	private String file_type;
	private String file_fmt;
	private Blob content;

	public Bd_bldg_files(){
		super();
		this.t = Bd_bldg_files.class;
	}

	public Bd_bldg_files(String file_id){
		super();
		this.file_id = file_id;
	}

	public void setFile_id(String file_id) {
		this.file_id = file_id;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	public void setFile_type(String file_type) {
		this.file_type = file_type;
	}

	public void setFile_fmt(String file_fmt) {
		this.file_fmt = file_fmt;
	}

	public void setContent(Blob content) {
		this.content = content;
	}

	public String getFile_id() {
		return file_id;
	}

	public String getFile_name() {
		return file_name;
	}

	public String getFile_type() {
		return file_type;
	}

	public String getFile_fmt() {
		return file_fmt;
	}

	public Blob getContent() {
		return content;
	}

}