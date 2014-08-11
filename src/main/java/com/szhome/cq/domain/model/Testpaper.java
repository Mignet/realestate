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
@Table(name = "BUS_ARRANGE")
public class Testpaper extends BaseDomain<Testpaper> {
	@Id
	private String arrange_id;
	private String bus_id;
	private String arranger;
	private Date arrange_time;
	private String arrange_state;
	private String b_deleteflag;

	public Testpaper(){
		super();
		this.t = Testpaper.class;
	}

	public Testpaper(String arrange_id){
		super();
		this.arrange_id = arrange_id;
	}

	public void setArrange_id(String arrange_id) {
		this.arrange_id = arrange_id;
	}

	public void setBus_id(String bus_id) {
		this.bus_id = bus_id;
	}

	public void setArranger(String arranger) {
		this.arranger = arranger;
	}

	public void setArrange_time(Date arrange_time) {
		this.arrange_time = arrange_time;
	}

	public void setArrange_state(String arrange_state) {
		this.arrange_state = arrange_state;
	}

	public void setB_deleteflag(String b_deleteflag) {
		this.b_deleteflag = b_deleteflag;
	}

	public String getArrange_id() {
		return arrange_id;
	}

	public String getBus_id() {
		return bus_id;
	}

	public String getArranger() {
		return arranger;
	}

	public Date getArrange_time() {
		return arrange_time;
	}

	public String getArrange_state() {
		return arrange_state;
	}

	public String getB_deleteflag() {
		return b_deleteflag;
	}

}
