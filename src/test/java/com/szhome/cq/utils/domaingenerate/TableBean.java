package com.szhome.cq.utils.domaingenerate;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.springjdbc.annotation.BaseDomain;
import com.springjdbc.annotation.BeanUtil;
import com.springjdbc.annotation.Entity;
import com.springjdbc.annotation.Table;

/**
 * 表类,对应于数据库中的每一个表.
 * 携带了数据库中表的表名，以及表包含的所有字段名和主键信息.
 * 
 * 每个表类会生成一个java文件.
 * 
 * @author  Mignet
 */

@Scope("prototype")
@Component
@Entity
@Table(name = "user_all_tables")
public class TableBean  /*extends BaseDomain<TableBean>*/ implements IFormatedTableBean {

	//表名
	private String table_name;
	//字段的集合
	private List<TableColumnBean> tableColumnBeanList;

	private String packageInfom;
	private List<String> primaryKeyList;
	private boolean isContainDate;
	private boolean isContainBlob;
	private boolean isContainClob;

	private StringBuilder propertyString;
	private StringBuilder setFunctionString;
	private StringBuilder getFunctionString;
	private StringBuilder sbConstruct;
	private StringBuilder sbVar;

    /**
     * Constructs a  TableBean
     */
	public TableBean(){
		super();
		//this.t=TableBean.class;
		primaryKeyList = new ArrayList<String>();
		tableColumnBeanList = new ArrayList<TableColumnBean>();

		propertyString = new StringBuilder ();
		setFunctionString = new StringBuilder ();
		getFunctionString = new StringBuilder ();
		sbConstruct = new StringBuilder ();
		sbVar = new StringBuilder ();

		isContainDate =false;
		isContainBlob =false;
		isContainClob =false;
	}

	public String getTable_name() {
		return table_name;
	}

	public void setTable_name(String table_name) {
		this.table_name = table_name;
	}

    /**
     * 设置包名
     */
	public void setPackageInfom(String packageInfom) {
		String pack = packageInfom;
		if(!packageInfom.startsWith("package")){
			StringBuilder sb = new StringBuilder();
			sb.append("package " + packageInfom);
			pack = sb.toString();
		}

		if(!packageInfom.endsWith(";")){
			StringBuffer sb = new StringBuffer(pack);
			pack = sb.append(";").toString();
		}
		this.packageInfom = pack;
	}

    /**
     * 获取一个表上所有的主键字段
     */
	public void setPrimaryKeyList(List<Map<String, Object>> pList) {
		for (Map<String, Object> m : pList) {
			Iterator<Entry<String, Object>> iterator= m.entrySet().iterator();
			while(iterator.hasNext()){
				Entry<String, Object> entry = iterator.next();
				this.primaryKeyList.add((String)entry.getValue());
			}
		}
	}

	/**
     * 获取一个表关联的字段信息，并且判断每个字段是否为主键
     */
	public void setTableColumnBeanList(List<Map<String, Object>> clist) {
		for (Map<String, Object> m : clist) {
			TableColumnBean columnBean = new TableColumnBean();
			BeanUtil.copyProperties(columnBean, m);
			if(this.primaryKeyList.contains(columnBean.getColumn_name())){
				columnBean.setPrimary(true);
			}else{
				columnBean.setPrimary(false);
			}
			this.tableColumnBeanList.add(columnBean);
		}
	}

	/**
     * 返回所有生成java文件时所需要的数据.
     */
	public String getOutPutString() {
		return this.formatColumn().assembleAllInform();
	}

	/**
     * 返回被生成java文件时的存放路径.
     */
	public String getFileSavePath() {
		return "/"+StringUtils.capitalize(this.getClassName())+".java";
	}

	/**
     * 解析表中的每一个字段。
     */
	private TableBean formatColumn(){
		List<TableColumnBean> columnList = this.tableColumnBeanList;
		if(columnList != null)
		{
			Iterator<TableColumnBean> iterator = (Iterator<TableColumnBean>)columnList.iterator();
			while(iterator.hasNext()){
				TableColumnBean columnBean = iterator.next();
				Builder builder = new BuilderFactory(columnBean).getBuilder();
				//对字段增加注释
				propertyString.append(builder.buildComment()).append(builder.buildProperty());
				setFunctionString.append(builder.buildSetFunction());
				getFunctionString.append(builder.buildGetFunction());

				if(columnBean.isPrimary()){
					sbConstruct.append(builder.buildConstruct());
					sbVar.append(builder.buildConstructVar());
				}

				if(columnBean.isDateType()){
					isContainDate = true;
				}
				if(columnBean.isBlobType()){
					isContainBlob = true;
				}
				if(columnBean.isClobType()){
					isContainClob = true;
				}

			}
		}
		return this;
	}

	/**
     * 按生成文件的格式的顺序来组合所有的数据.
     */
	private String assembleAllInform(){
		StringBuilder sb = new StringBuilder();
		sb.append(packageInfom());
		if(isContainDate)
			sb.append(importDateInform());
		if(isContainBlob)
			sb.append(importBlobInform());
		if(isContainClob)
			sb.append(importClobInform());
		if(isContainDate||isContainBlob||isContainClob)
			sb.append("\n");
		sb.append(importInform());
		sb.append(annocationInform());
		sb.append(classHeadInform());
		sb.append(propertyInform());
		sb.append(defaultConstructInform());
		if(sbConstruct.toString().length()>1){
			sb.append(primaryKeyConstructInform());
		}
		sb.append(allSetFunctionInform());
		sb.append(allGetFunctionInform());
		sb.append(classEndInform());
		return sb.toString();
	}

	/**
     * 包的打印格式信息。
     */
	private String packageInfom(){
		return packageInfom + "\n\n";
	}

	private String importDateInform(){
		return Inform.IMPORT_STRING_DATE;
	}

	private String importBlobInform(){
		return Inform.IMPORT_STRING_BLOB;
	}

	private String importClobInform(){
		return Inform.IMPORT_STRING_CLOB;
	}
	/**
     * import的打印格式信息。
     */
	private String importInform(){
		return Inform.IMPORT_STRING;
	}

	/**
     * annocation的打印格式信息。
     */
	private String annocationInform(){
		return Inform.ANNOTATION_STRING.replace("#{5}",getTable_name());
	}

	private String classHeadInform(){
		return getClassNameString(Inform.CLASS_HEAD_STRING);
	}

	private String propertyInform(){
		return propertyString.toString() + "\n";
	}

	/**
     * 缺省构造函数打印格式信息。
     */
	private String defaultConstructInform(){
		return getClassNameString(Inform.DEFAULT_CONSTRUCT);
	}

	/**
     * 非缺省构造函数打印格式信息。
     */
	private String primaryKeyConstructInform(){
		return getClassNameString(Inform.PRIMARY_KEY_CONSTRUCT)
			   .replace("#{7}", sbConstruct.toString().substring(0, sbConstruct.toString().length()-1))
			   .replace("#{8}", sbVar.toString());
	}

	private String allSetFunctionInform(){
		return setFunctionString.toString();
	}

	private String allGetFunctionInform(){
		return getFunctionString.toString();
	}

	private String classEndInform(){
		return Inform.CLASS_END_STRING;
	}

	private String getClassNameString(String s){
		return s.replace("#{6}", StringUtils.capitalize(getClassName()));
	}

	/**
     * 返回类名。
     */
	private String getClassName(){
		if(!table_name.startsWith("t_")){
			return table_name;
		}else{
			return table_name.substring("t_".length());
		}
	}
}


