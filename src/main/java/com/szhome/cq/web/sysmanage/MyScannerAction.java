package com.szhome.cq.web.sysmanage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.plan.commons.Row;
import com.plan.exceptions.GeneralException;
import com.plan.web.JsonResult;
import com.szhome.cq.business.FacadeFactory;
import com.szhome.cq.business.IBusinessFacade;
import com.szhome.cq.business.ICommonFacade;
import com.szhome.cq.business.IRecmaterialFacade;
import com.szhome.cq.delegate.BaseDelegate;
import com.szhome.cq.domain.model.BusinessMain;
import com.szhome.cq.utils.Constants;
import com.szhome.cq.utils.JsonUtil;
import com.szhome.cq.utils.Util;

/**
 * @author user
 *
 */
public class MyScannerAction extends BaseDelegate {
	  //公共服务接口
      ICommonFacade commonFacade = FacadeFactory.getCommonFacade();
      //接见材料服务接口
      IRecmaterialFacade recFacade = FacadeFactory.getRecmaterialFacade();
      //业务办理服务接口
      IBusinessFacade businessFacade = FacadeFactory.getBusinessFacade();	
      
      String scan_directory = Util.getMessage(Constants.MODULE_SYSTEM, "system.scan_directory");
	  
      String scan_extension = Util.getMessage(Constants.MODULE_SYSTEM, "system.scan_extension");
      
      String order_cmd_start = Util.getMessage(Constants.MODULE_SYSTEM, "system.exec_order_CMDstart");
	
    protected String getScanTreeData(List<Map<String,Object>> datas){
	      List<Map<String,Object>> treeLst = new ArrayList<Map<String,Object>>();
	      for(int i=0;i<datas.size();i++){
	    	  Map<String,Object> map1 = datas.get(i);
	    	  Map<String,Object> map2 = new HashMap<String,Object>();
		      map2.put("DirectoryId", map1.get("RECEIVAL_ID"));
		      map2.put("DocumentName", map1.get("RE_NAME").toString().trim());
		      map2.put("BarCode", map1.get("RECEIVAL_ID"));
		      treeLst.add(map2);
	      }
    	  return JsonUtil.list2json(treeLst);
      }
    
    /**
     * @param row
     * @return
     */
    public String getBusIdByProcId(Row row){
    	   String proc_id = row.getString("proc_id");
    	   BusinessMain bm = commonFacade.getBusinessMainByProcId(proc_id);
    	   return JsonUtil.bean2json(bm);
    }
    
    /**
     * @param row
     * @return
     */
    public String getRecmaterialMapLstByRegCode(Row row) {
    	   List<Map<String,Object>> recLst = new ArrayList<Map<String,Object>>();
    	   Map<String,Object> paramMap = new HashMap<String,Object>();
    	   String bus_id = row.getString("bus_id");
    	   String reg_code = row.getString("reg_code");
    	   try {
			     String userName = getOperatorInfo().getUserName();
			     paramMap.put("re_person", userName);
			     paramMap.put("bus_id", bus_id);
			     paramMap.put("reg_code", reg_code);
			     paramMap.put("rec_type_flag", 0);
			} catch (GeneralException e) {
				logger.error("getRecmaterialMapLstByRegCode:"+e.getMessage(), e);
			}
    	   recLst = recFacade.getRecmaterialMapListByRegId(paramMap);
    	   return getScanTreeData(recLst);
    }
    
    public JsonResult createBusScanner(Row row){
       List<Map<String,Object>> recLst = new ArrayList<Map<String,Object>>();
 	   Map<String,Object> paramMap = new HashMap<String,Object>();
 	   String bus_id = row.getString("bus_id");
 	   String reg_code = row.getString("reg_code");
 	   try {
			     String userName = getOperatorInfo().getUserName();
			     paramMap.put("re_person", userName);
			     paramMap.put("bus_id", bus_id);
			     paramMap.put("reg_code", reg_code);
			     paramMap.put("rec_type_flag", 0);			     
			} catch (GeneralException e) {
				logger.error("getRecmaterialMapLstByRegCode:"+e.getMessage(), e);
			}
 	   recLst = recFacade.getRecmaterialMapListByRegId(paramMap);
 	   paramMap.put("recLst", recLst);
 	   paramMap.put("directory", scan_directory);
 	   paramMap.put("extension", scan_extension);
 	   
 	    try {
		    businessFacade.batch_saveBusScanners(paramMap);
		} catch (Exception e) {
			logger.error("createBusScanner:"+e.getMessage(), e);
			return (new JsonResult(false, e.getMessage(), null));
		}
    	return (new JsonResult(true, "保存成功！", null));
    }
    
    public JsonResult openFolderExpoler(Row row){
    	 String proc_id = row.getString("proc_id");
  	     BusinessMain bm = commonFacade.getBusinessMainByProcId(proc_id);
  	     String reg_code = bm.getReg_code();
  	     String order = order_cmd_start+" "+scan_directory+"/"+reg_code;
    	try{
			Runtime.getRuntime().exec(order);
		}catch(IOException e){
			return (new JsonResult(false, e.getMessage(), null));
		} 
    	return (new JsonResult(true, "OK", null));
    }
      
}

