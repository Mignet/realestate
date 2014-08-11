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
@Table(name = "BK_SHARE")
public class Reg_Share extends BaseDomain<Reg_Share> {
	@Id
	private String gybfb_id;
	private String book_code;
	private String b_deleteflag;
	private String reg_code;
	private String channel;
	private String wall;
	private String pub_service;
	private String pre_reg_code;
	private String procdef_id;
	public void setProcdef_id(String procdef_id) {
		this.procdef_id = procdef_id;
	}
	public String getProcdef_id() {
		return procdef_id;
	}
	public Reg_Share(){
		super();
		this.t = Reg_Share.class;
	}

	public Reg_Share(String gybfb_id){
		super();
		this.gybfb_id = gybfb_id;
	}

	public void setGybfb_id(String gybfb_id) {
		this.gybfb_id = gybfb_id;
	}

	public void setBook_code(String book_code) {
		this.book_code = book_code;
	}

	public void setB_deleteflag(String b_deleteflag) {
		this.b_deleteflag = b_deleteflag;
	}

	public void setReg_code(String reg_code) {
		this.reg_code = reg_code;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public void setWall(String wall) {
		this.wall = wall;
	}

	public void setPub_service(String pub_service) {
		this.pub_service = pub_service;
	}

	public void setPre_reg_code(String pre_reg_code) {
		this.pre_reg_code = pre_reg_code;
	}

	public String getGybfb_id() {
		return gybfb_id;
	}

	public String getBook_code() {
		return book_code;
	}

	public String getB_deleteflag() {
		return b_deleteflag;
	}

	public String getReg_code() {
		return reg_code;
	}

	public String getChannel() {
		return channel;
	}

	public String getWall() {
		return wall;
	}

	public String getPub_service() {
		return pub_service;
	}

	public String getPre_reg_code() {
		return pre_reg_code;
	}

}
