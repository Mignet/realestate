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
@Table(name = "CFIG_OFFICE")
public class ConOffice extends BaseDomain<ConOffice> {
	private String bus_type_id;
	@Id
	private String office_id;
	private String b_deleteflag;
	private String office_type;
	private String office_name;
	private String office_url;

	public ConOffice(){
		super();
		this.t = ConOffice.class;
	}

	public ConOffice(String office_id){
		super();
		this.office_id = office_id;
	}

	public void setBus_type_id(String bus_type_id) {
		this.bus_type_id = bus_type_id;
	}

	public void setOffice_id(String office_id) {
		this.office_id = office_id;
	}

	public void setB_deleteflag(String b_deleteflag) {
		this.b_deleteflag = b_deleteflag;
	}

	public void setOffice_type(String office_type) {
		this.office_type = office_type;
	}

	public void setOffice_name(String office_name) {
		this.office_name = office_name;
	}

	public void setOffice_url(String office_url) {
		this.office_url = office_url;
	}

	public String getBus_type_id() {
		return bus_type_id;
	}

	public String getOffice_id() {
		return office_id;
	}

	public String getB_deleteflag() {
		return b_deleteflag;
	}

	public String getOffice_type() {
		return office_type;
	}

	public String getOffice_name() {
		return office_name;
	}

	public String getOffice_url() {
		return office_url;
	}

}
