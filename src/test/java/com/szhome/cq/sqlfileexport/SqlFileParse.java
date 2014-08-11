package com.szhome.cq.sqlfileexport;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.digester.Digester;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.io.Resource;
import org.xml.sax.SAXException;

import com.springjdbc.annotation.AnnotationExceptoin;

public class SqlFileParse {
	private Log logger = LogFactory.getLog(getClass());
	
	private String xmlFilePath;
	
	public SqlFileParse(String xmlFilePath){
	  this.xmlFilePath=xmlFilePath;
	}

	/**
	 * 解析单个文件
	 * 
	 * @param is
	 * @return
	 * @throws IOException
	 * @throws SAXException
	 * @throws AnnotationExceptoin
	 */
	private SqlFile getSqlFromXml(InputStream is) {
		Digester digester = new Digester();
		digester.setValidating(false);

		digester.addObjectCreate("sqlList", SqlFile.class);
		digester.addBeanPropertySetter("sqlList/comment");

		digester.addObjectCreate("sqlList/sql", SqlItemVo.class);
		digester.addSetNext("sqlList/sql", "addXmlSqlVo");
		digester.addCallMethod("sqlList/sql", "setSql", 0);
		digester.addSetProperties("sqlList/sql", "sqlId", "sqlId");
		digester.addSetProperties("sqlList/sql", "author", "auth");
		digester.addSetProperties("sqlList/sql", "function", "function");
		SqlFile xSqlUtils = new SqlFile();
		try {
			xSqlUtils = (SqlFile) digester.parse(is);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		return xSqlUtils;
	}

	private List<File> removeHiddenFile(){
	  File file=new File(xmlFilePath);
    File[] xmlfiles=file.listFiles();
    List<File> needFile=new ArrayList<File>();
    for(File f:xmlfiles){
      if(!f.isDirectory())needFile.add(f);
    }
    return needFile;
	}
	public String[] getFileNameShortName(){
	  List<File> needFile=this.removeHiddenFile();
    String[] names=new String[needFile.size()];
    for(int i=0;i<needFile.size();i++){
      File f=needFile.get(i);
      String fn=f.getName();
      fn=fn.substring(0,fn.lastIndexOf("."));
      System.out.println((i+1)+":"+fn);
      names[i]=fn;
    }
    return names;
	}
	public List<SqlFile> getAllSqlFromXmlFiles()throws Exception {
	  List<SqlFile> filelist = new ArrayList<SqlFile>();
	  List<File> needFile=this.removeHiddenFile();
	  for(File fi:needFile){
	    InputStream is=new FileInputStream(fi);
	    SqlFile sfile = this.getSqlFromXml(is);
	    sfile.setFilename(fi.getName());
      filelist.add(sfile);
	  }
		return filelist;
	}

}

