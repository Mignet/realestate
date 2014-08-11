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
@Table(name = "est_parcel_trend")
public class LandTrend extends BaseDomain<LandTrend> {
	@Id
	private String trend_id;
	private String history_id;
	private String parcel_id;
	private String parcel_code;
	private String pre_content;
	private String next_content;
	private Date bg_time;

	public LandTrend(){
		super();
		this.t = LandTrend.class;
	}

	public LandTrend(String trend_id){
		super();
		this.trend_id = trend_id;
	}

	public void setTrend_id(String trend_id) {
		this.trend_id = trend_id;
	}

	public void setHistory_id(String history_id) {
		this.history_id = history_id;
	}

	public void setParcel_id(String parcel_id) {
		this.parcel_id = parcel_id;
	}

	public void setParcel_code(String parcel_code) {
		this.parcel_code = parcel_code;
	}

	public void setPre_content(String pre_content) {
		this.pre_content = pre_content;
	}

	public void setNext_content(String next_content) {
		this.next_content = next_content;
	}

	public void setBg_time(Date bg_time) {
		this.bg_time = bg_time;
	}

	public String getTrend_id() {
		return trend_id;
	}

	public String getHistory_id() {
		return history_id;
	}

	public String getParcel_id() {
		return parcel_id;
	}

	public String getParcel_code() {
		return parcel_code;
	}

	public String getPre_content() {
		return pre_content;
	}

	public String getNext_content() {
		return next_content;
	}

	public Date getBg_time() {
		return bg_time;
	}

}
