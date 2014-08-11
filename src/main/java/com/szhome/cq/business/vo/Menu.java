package com.szhome.cq.business.vo;

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
@Table(name = "TREE")
public class Menu extends BaseDomain<Menu> {
	@Id
	private Integer tree_id;
	private String tree_name;
	private Integer parent_id;
	private Integer turn;
	private String url;
	private String icon;
	private Date create_date;
	private String creator;
	private String busnode;

	public Menu(){
		super();
		this.t = Menu.class;
	}

	public Menu(Integer tree_id){
		super();
		this.tree_id = tree_id;
	}

	public void setTree_id(Integer tree_id) {
		this.tree_id = tree_id;
	}

	public void setTree_name(String tree_name) {
		this.tree_name = tree_name;
	}

	public void setParent_id(Integer parent_id) {
		this.parent_id = parent_id;
	}

	public void setTurn(Integer turn) {
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

	public Integer getTree_id() {
		return tree_id;
	}

	public String getTree_name() {
		return tree_name;
	}

	public Integer getParent_id() {
		return parent_id;
	}

	public Integer getTurn() {
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
