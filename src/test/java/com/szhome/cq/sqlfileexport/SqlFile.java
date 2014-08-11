package com.szhome.cq.sqlfileexport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.springjdbc.annotation.AnnotationExceptoin;

public class SqlFile {

	private String comment;
	private String filename;
	private List<SqlItemVo> sqlList;
	
	private Map<String, String> sqlMap;
	
	
	public void addXmlSqlVo(SqlItemVo vo) {
		if (sqlList == null)
			sqlList = new ArrayList<SqlItemVo>();
		if(sqlMap==null)
			sqlMap=new HashMap<String, String>();
		if (vo.getSqlId() == null || vo.getSqlId().equals(""))
			throw new AnnotationExceptoin("严重错误: sql配置文件中的sql语句sqlId为空!");
		if (vo.getSql() == null || vo.getSql().equals(""))
			throw new AnnotationExceptoin("严重错误: sql配置文件中的sql语句为空! sqlid:"
					+ vo.getSqlId());
		if (sqlMap.containsKey(vo.getSqlId()))
			throw new AnnotationExceptoin("严重错误: sql配置文件中存在sqlId相同的配置！sqlid:"
					+ vo.getSqlId());
		System.out.println(vo.getSqlId()+":"+vo.getAuth()+":"+vo.getFunction());
		sqlList.add(vo);
	}
	
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public List<SqlItemVo> getSqlList() {
		return sqlList;
	}
	public void setSqlList(List<SqlItemVo> sqlList) {
		this.sqlList = sqlList;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}
	
	
	
	
}

