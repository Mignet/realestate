package com.springjdbc.annotation;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.digester.Digester;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.io.Resource;
import org.xml.sax.SAXException;

import com.szhome.cq.utils.DateUtils;

import java.util.concurrent.ConcurrentHashMap;

public class XmlSqlParse {
	private Log logger = LogFactory.getLog(getClass());

	private Resource[] xmlSqlFileList;
	private Map<String, String> sqlMap;

	public Map<String, String> getSqlMap() {
		return sqlMap;
	}

	public void setSqlMap(Map<String, String> sqlMap) {
		this.sqlMap = sqlMap;
	}

	public Resource[] getXmlSqlFileList() {
		return xmlSqlFileList;
	}

	public void setXmlSqlFileList(Resource[] xmlSqlFileList) {
		this.xmlSqlFileList = xmlSqlFileList;
	}

	public void setXmlSqlFileList(Resource xmlSqlFileList) {
		this.xmlSqlFileList = new Resource[] { xmlSqlFileList };
	}

	public Map<String, Map<String, String>> getAllSqlFromXmlFiles() {
		return parseAllXml();
	}

	private Map<String, String> getSqlFromXml(InputStream is)
			throws IOException, SAXException, AnnotationExceptoin {
		sqlMap = new HashMap<String, String>();
		Digester digester = new Digester();
		digester.setValidating(false);

		digester.addObjectCreate("sqlList", XmlSqlParse.class);
		digester.addObjectCreate("sqlList/sql", XmlSqlParseVo.class);
		digester.addSetNext("sqlList/sql", "addXmlSqlVo");
		digester.addCallMethod("sqlList/sql", "setSql", 0);
		digester.addSetProperties("sqlList/sql", "sqlId", "sqlId");
		XmlSqlParse xSqlUtils = (XmlSqlParse) digester.parse(is);
		return xSqlUtils.getSqlMap();
	}

	public void addXmlSqlVo(XmlSqlParseVo vo) {
		if (sqlMap == null)
			sqlMap = new HashMap<String, String>();
		if (vo.getSqlId() == null || vo.getSqlId().equals(""))
			throw new AnnotationExceptoin("严重错误: sql配置文件中的sql语句sqlId为空!");
		if (vo.getSql() == null || vo.getSql().equals(""))
			throw new AnnotationExceptoin("严重错误: sql配置文件中的sql语句为空! sqlid:"
					+ vo.getSqlId());
		if (sqlMap.containsKey(vo.getSqlId()))
			throw new AnnotationExceptoin("严重错误: sql配置文件中存在sqlId相同的配置！sqlid:"
					+ vo.getSqlId());
		sqlMap.put(vo.getSqlId(), vo.getSql());
	}

	private Map<String, Map<String, String>> parseAllXml() {
		logger.info(".......paramseAllXml start.............." + DateUtils.date2String(new Date(), DateUtils.DATE_FORMAT_DEFAULT));
		Map<String, Map<String, String>> xmlMapList = null;
		if (xmlSqlFileList == null || xmlSqlFileList.length < 1)
			throw new AnnotationExceptoin(
					"解析xml sql 文件严重错误:xml sql 文件 目录属性 xmlSqlFileList 为空或者没有配置! ");
		xmlMapList = new ConcurrentHashMap<String, Map<String, String>>();
		for (Resource r : xmlSqlFileList) {
			InputStream is = null;
			Map<String, String> map;
			try {
				is = r.getInputStream();
				map = this.getSqlFromXml(is);
				xmlMapList.put(r.getFilename().substring(0,
						r.getFilename().indexOf(".")), map);
			} catch (Exception ex) {
				throw new AnnotationExceptoin("解析文件" + r.getFilename()
						+ "严重错误:" + ex.getMessage());
			}
		}
		logger.info("...........paramseAllXml end.............." + DateUtils.date2String(new Date(), DateUtils.DATE_FORMAT_DEFAULT)
				+ " file amount:" + xmlSqlFileList.length);
		return xmlMapList;
	}

}

