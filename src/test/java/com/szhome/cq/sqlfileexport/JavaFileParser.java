package com.szhome.cq.sqlfileexport;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;


public class JavaFileParser {

	private Map<String,List<SqlUserVo>> keyMap=new HashMap<String, List<SqlUserVo>>();
	
	public Map<String,List<SqlUserVo>> readFile(String path,String[] xmlFilename)throws Exception{
	  System.out.println(path);
		File filepath=new File(path);
		if(!filepath.isDirectory()){
			throw new RuntimeException(path+"不是一个目录");
		}
		if(!filepath.isHidden()){
			File[] subFiles=filepath.listFiles();
			for(File file:subFiles){
				if(file.isDirectory()){
					readFile(file.getPath(),xmlFilename);
				}else{
					readFromJavaFile(file.getPath(),xmlFilename);
				}
			}
		}		
		return keyMap;
	}	
	private void readFromJavaFile(String file,String[] xmlFilename)throws Exception{
		BufferedReader reader=null;
		try{
			reader=new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			String line;
			int linenum=1;
			//是否注释
			boolean isRemark=false;
			while((line=reader.readLine())!=null){
				if(line.indexOf("/*")!=-1)isRemark=true;
				this.compareUse(file, line, linenum,xmlFilename,isRemark);
				if(line.indexOf("*/")!=-1)isRemark=false;
				linenum++;
			}
		}finally{
			if(reader!=null){
				reader.close();
			}
		}
	}
	private void compareUse(String file,String line,int linenum,String[] xmlFilename,boolean isRemark){
		int index=-1;
		for(String filename:xmlFilename){
			index=line.indexOf("\""+filename+".");
			if(index!=-1){
				index=line.indexOf(filename+".");
				//System.out.println("*****************"+file+":"+linenum+":"+filename);
				String subline=line.substring(index);
				int lastIndex=subline.indexOf('"');
				String key=line.substring(index, index+lastIndex);
				boolean remark=isRemark;
				if(!isRemark){
					int lineRemarkIndex=line.indexOf("//");
					if(lineRemarkIndex!=-1 && lineRemarkIndex<index)remark=true;
				}
				file=file.replace("\\", ".");
				int srcInd=file.indexOf(".java");
				file=file.substring(srcInd+6);
				SqlUserVo vo=new SqlUserVo(file,linenum,remark);
				if(keyMap.containsKey(key)){
					List<SqlUserVo> list=keyMap.get(key);
					list.add(vo);
				}else{
					List<SqlUserVo> list=new ArrayList<SqlUserVo>();
					list.add(vo);
					keyMap.put(key, list);
				}
				break;
			}
		}
		
	}

}

