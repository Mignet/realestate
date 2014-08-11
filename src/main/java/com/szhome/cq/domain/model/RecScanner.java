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
@Table(name = "BUS_SCANNER")
public class RecScanner extends BaseDomain<RecScanner> {
	@Id
	private String scanner_id;
	private String receival_id;
	private String b_deleteflag;
	private String sca_extension;
	private Blob sca_content;
	private String directory;
	private String bus_id;

	public RecScanner(){
		super();
		this.t = RecScanner.class;
	}

	public RecScanner(String scanner_id){
		super();
		this.scanner_id = scanner_id;
	}

	public void setScanner_id(String scanner_id) {
		this.scanner_id = scanner_id;
	}

	public void setReceival_id(String receival_id) {
		this.receival_id = receival_id;
	}

	public void setB_deleteflag(String b_deleteflag) {
		this.b_deleteflag = b_deleteflag;
	}

	public void setSca_extension(String sca_extension) {
		this.sca_extension = sca_extension;
	}

	public void setSca_content(Blob sca_content) {
		this.sca_content = sca_content;
	}

	public void setDirectory(String directory) {
		this.directory = directory;
	}

	public void setBus_id(String bus_id) {
		this.bus_id = bus_id;
	}

	public String getScanner_id() {
		return scanner_id;
	}

	public String getReceival_id() {
		return receival_id;
	}

	public String getB_deleteflag() {
		return b_deleteflag;
	}

	public String getSca_extension() {
		return sca_extension;
	}

	public Blob getSca_content() {
		return sca_content;
	}

	public String getDirectory() {
		return directory;
	}

	public String getBus_id() {
		return bus_id;
	}

}
