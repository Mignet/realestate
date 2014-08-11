package com.szhome.cq.utils.domaingenerate;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.springjdbc.annotation.BaseDomain;
import com.springjdbc.annotation.Entity;
import com.springjdbc.annotation.Table;

/**
 * 表的字段类。
 *  @author	Mignet
 */

@Scope("prototype")
@Component
@Entity
//@Table(name = "user_tab_columns")
public class TableColumnBean/* extends BaseDomain<TableColumnBean>*/ {

	//字段名称
	private String column_name;
	//字段注释
	private String comment;
	//字段的数据类型
	private String data_type;
	//是否为主键
	private boolean  isPrimary;

	private String table_name;

	/*public TableColumnBean(){
		this.t=TableColumnBean.class;
	}*/

	public String getColumn_name() {
		return column_name;
	}
	public void setColumn_name(String column_name) {
		this.column_name = column_name;
	}
	public String getData_type() {
		return data_type;
	}
	public void setData_type(String data_type) {
		this.data_type = data_type;
	}
	public boolean isPrimary() {
		return isPrimary;
	}
	public void setPrimary(boolean isPrimary) {
		this.isPrimary = isPrimary;
	}

	public String getTable_name() {
		return table_name;
	}
	public void setTable_name(String table_name) {
		this.table_name = table_name;
	}

	public boolean isDateType(){
		return  data_type.equals("date") ? true:false;
	}

	public boolean isBlobType(){
		return  data_type.equals("blob") ? true:false;
	}

	public boolean isClobType(){
		return  data_type.equals("clob") ? true:false;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
}


