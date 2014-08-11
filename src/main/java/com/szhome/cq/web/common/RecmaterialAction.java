/**
 * Project Name:dxtx_re
 * File Name:RecmaterialAction.java
 * Package Name:com.szhome.cq.web.common
 * Date:2014-1-15����9:52:34
 * Copyright (c) 2014, dxtx All Rights Reserved.
 *
*/

package com.szhome.cq.web.common;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.plan.commons.Row;
import com.plan.exceptions.GeneralException;
import com.szhome.commons.log.LogUtil;
import com.szhome.cq.business.FacadeFactory;
import com.szhome.cq.business.IRecmaterialFacade;
import com.szhome.cq.delegate.BaseDelegate;
import com.szhome.cq.domain.model.BusinessMain;
import com.szhome.cq.domain.model.MaterialReplenish;
import com.szhome.cq.domain.model.RecScanner;
import com.szhome.cq.utils.JsonUtil;

/**
 * ClassName:RecmaterialAction <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2014-1-15 ����9:52:34 <br/>
 * @author   Joyon
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class RecmaterialAction extends BaseDelegate{
	private IRecmaterialFacade recmaterialFacade = FacadeFactory.getRecmaterialFacade();

	/**
	 * 
	 * getRecmaterial:(��ȡ��ǰ�ǼǱ�ŵĽӼ�����). 
	 *
	 * @author Joyon
	 * @return
	 * @throws GeneralException 
	 * @since JDK 1.6
	 */
	public String getRecmaterial(Row row) throws GeneralException{
		Map<String,Object> paraMap = new HashMap();
		BusinessMain businessMain = null;
		String proc_id = row.getString("proc_id");
		String rec_type_flag = row.getString("rec_type_flag");
		List<Map<String,Object>> resultList = new ArrayList<Map<String,Object>>();
		try {
			businessMain = FacadeFactory.getCommonFacade().getBusinessMainByProcId(proc_id);
			if(businessMain!=null){
				paraMap.put("reg_code", businessMain.getReg_code());
				paraMap.put("bus_id", businessMain.getBus_id());
				paraMap.put("re_person", getOperatorInfo().getUserName());
				paraMap.put("rec_type_flag", rec_type_flag);
				
				resultList = recmaterialFacade.getRecmaterialMapListByRegId(paraMap);
			}
			
			
		} catch (Exception e) {
			LogUtil.error(e.getMessage());
		}
		
		
		return JsonUtil.object2json(resultList);
	}
/**
	 * 
	 * ��ȡ��������    ͨ����ǰ����Id ��ȡ����Ҫ����ҵ��ID Ȼ���ȡ��������  
	 *
	 * @author Joyon
	 * @return
 * @throws GeneralException 
	 * @since JDK 1.6
	 */
	public String getCorrectionMaterialInfo(Row row) throws GeneralException{
		Map<String,Object> paraMap = new HashMap();
		BusinessMain businessMain = null;
		String proc_id = row.getString("proc_id");
		String rec_type_flag = row.getString("rec_type_flag");
		try {
			//��ȡ��ǰ������������  ҵ������ ��Ϣ
			businessMain = FacadeFactory.getCommonFacade().getBusinessMainByProcId(proc_id);
			//��ȡ��Ҫ�������ϵ�ҵ��
			businessMain = FacadeFactory.getCommonFacade().getBusinessMainByBusid(businessMain.getLast_bus_id());
		} catch (Exception e) {
			LogUtil.error(e.getMessage());
		}
		paraMap.put("reg_code", businessMain.getReg_code());
		paraMap.put("bus_id", businessMain.getBus_id());
		paraMap.put("re_person", getOperatorInfo().getUserName());
		paraMap.put("rec_type_flag", rec_type_flag);
		List<Map<String,Object>> resultList = recmaterialFacade.getRecmaterialMapListByRegId(paraMap);
		return JsonUtil.list2json(resultList);
	}
	
	/**
	 * 
	 * ���������ϱ༭������ݱ��浽��̨  ----���޸�
	 *
	 * @author Joyon
	 * @return
	 * @throws GeneralException 
	 * @since JDK 1.6
	 */
	public String correctionMaterialApplyEdit(Row row) throws GeneralException{
		String userName = getOperatorInfo().getUserName();
		String datas = row.getString("datas");
		String proc_id = row.getString("proc_id");
		String rec_type_flag = row.getString("rec_type_flag");
		Map<String,Object> map = new HashMap<String,Object>();
		String reg_code = "";
		try {
			//��ȡ��ǰ������������  ҵ������ ��Ϣ
			BusinessMain businessMain = businessMain = FacadeFactory.getCommonFacade().getBusinessMainByProcId(proc_id);
			//��ȡ��Ҫ�������ϵ�ҵ��
			businessMain = FacadeFactory.getCommonFacade().getBusinessMainByBusid(businessMain.getLast_bus_id());
			reg_code =businessMain.getReg_code();
		} catch (Exception e) {
			LogUtil.error(e.getMessage());
		}
		map.put("datas",datas);
		map.put("userName", userName);
		map.put("reg_code", reg_code);
		map.put("rec_type_flag", rec_type_flag);
		
		try {
			map = recmaterialFacade.applyEdit(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonUtil.map2json(map);
	}
	
	/**
	 * ���ֵ�༭�������Ӧ�õ���̨���ݿ�
	 * @throws GeneralException 
	 */
	public String applyEdit(Row row) throws GeneralException{
		String userName = getOperatorInfo().getUserName();
		String reg_code = row.getString("reg_code");
		String datas = row.getString("datas");
		String proc_id = row.getString("proc_id");
		String rec_type_flag = row.getString("rec_type_flag");
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			reg_code = FacadeFactory.getCommonFacade()
					.getBusinessMainByProcId(proc_id).getReg_code();
		} catch (Exception e) {
			LogUtil.error(e.getMessage());
		}
		map.put("datas",datas);
		map.put("userName", userName);
		map.put("reg_code", reg_code);
		map.put("rec_type_flag", rec_type_flag);
		
		try {
			map = recmaterialFacade.applyEdit(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonUtil.object2json(map);
	}
	
	public String getScannerInfo(Row row){
		List<Map<String, Object>> resultList = null;
		String proc_id = row.getString("proc_id");
		try {
			resultList = recmaterialFacade
					.getScannerListMapByProcId(proc_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonUtil.object2json(resultList);
	}
	
	public String scannerRelImg(Row row){
		String receival_id = row.getString("receival_id");
		String scanner_id = row.getString("scanner_id");
		RecScanner recScanner = new RecScanner();
		recScanner.setScanner_id(scanner_id);
		recScanner = recmaterialFacade.getRecScanner(recScanner);
		if(recScanner != null){
			recScanner.setReceival_id(receival_id);
		}
		
		recmaterialFacade.modifyRecScanner(recScanner);
		return null;
	}
/**
	 * 
	 * ��ȡ��һ��ҵ�������ID
	 *
	 * @author Joyon
	 * @return
	 * @since JDK 1.6
	 */
	public String getLastProcid(Row row){
		String proc_id = row.getString("proc_id");
		//ͨ����ǰ����ID��ȡ����һ��ҵ��ID����Ҫ�������ϵ�ҵ��ID��   
		String last_bus_id = FacadeFactory.getCommonFacade().getBusinessMainByProcId(proc_id).getLast_bus_id();
		//
		String last_proc_id = FacadeFactory.getCommonFacade().getBusinessMainByBusid(last_bus_id).getProc_id();
		
		Map<String,Object> resultMap = new HashMap<String,Object>();
		resultMap.put("proc_id", last_proc_id);
		return JsonUtil.map2json(resultMap);
	}
	
	
	/**
	 * 
	 * ����������������
	 *
	 * @author Joyon
	 * @return
	 * @since JDK 1.6
	 */
	public String startMaterialReplenishProc(Row row){
		Map resultMap  = null;
		String proc_id = row.getString("proc_id");
		try {
			 resultMap = FacadeFactory.getRecmaterialFacade()
					.startMaterialReplenishProc(proc_id, getOperatorInfo());
		} catch (Exception e) {
			LogUtil.error("RecmaterialAction.startMaterialReplenishProc ����������������ʧ��"+e.getMessage());
		}
		return JsonUtil.map2json(resultMap);
	}
	/**
	 * 
	 * ���²���������Ϣ   ��Ҫ���޸Ĳ���ԭ��
	 *
	 * @author Joyon
	 * @return
	 * @since JDK 1.6
	 */
	public String updateMaterialreplenish(Row row){
		String proc_id = row.getString("proc_id");
		//ͨ����ǰ����ID��ȡ��һ��ҵ��ĵǼǱ��   ��ȡ�ǼǱ������Ĳ��������Ƿ����  �������  �����  �������򱣴�
		BusinessMain businessMain = FacadeFactory.getCommonFacade().getBusinessMainByProcId(proc_id);
		BusinessMain lastbusinessmain = FacadeFactory.getCommonFacade().getBusinessMainByBusid(businessMain.getLast_bus_id());
		
		String lastregcode = lastbusinessmain.getReg_code();
		String reason = row.getString("reason");
		MaterialReplenish materialReplenish = null;
		try {
			 materialReplenish = FacadeFactory
					.getRecmaterialFacade().getMaterialReplenishByregcode(
							lastregcode);
		} catch (Exception e) {
			LogUtil.error("RecmaterialAction.updateMaterialreplenish ��ȡ����������Ϣ����"+e.getMessage());
		}
		//��������ݸ���   �����ݱ���
		if(materialReplenish==null){
			materialReplenish = new MaterialReplenish();
			materialReplenish.setReg_code(lastregcode);
			materialReplenish.setRepl_date(new Date());
			materialReplenish.setRepl_reson(reason);
			try {
				FacadeFactory.getRecmaterialFacade().saveMaterialReplenish(
						materialReplenish);
			} catch (Exception e) {
				LogUtil.error("RecmaterialAction.updateMaterialreplenish ���油��������Ϣ����"+e.getMessage());
			}
		}else{
			materialReplenish.setRepl_reson(reason);
			materialReplenish.setRepl_date(new Date());
			try {
				FacadeFactory.getRecmaterialFacade().updateMaterialReplenish(
						materialReplenish);
			} catch (Exception e2) {
				LogUtil.error("RecmaterialAction.updateMaterialreplenish ���油��������Ϣ����"+e2.getMessage());
			}
			
		}
		
		return null;
		
	}
	
	
	/**
	 * 
	 * ��ȡ����������Ϣ
	 *
	 * @author Joyon
	 * @param row
	 * @return
	 * @since JDK 1.6
	 */
	public String geteMaterialreplenish(Row row){
		//-------ajax---------
		String proc_id = row.getString("proc_id");
		//ͨ����ǰ����ID��ȡ��һ��ҵ��ĵǼǱ��   ��ȡ�ǼǱ������Ĳ��������Ƿ����  �������  �����  �������򱣴�
		BusinessMain businessMain = FacadeFactory.getCommonFacade().getBusinessMainByProcId(proc_id);
		BusinessMain lastbusinessmain = FacadeFactory.getCommonFacade().getBusinessMainByBusid(businessMain.getLast_bus_id());
		
		String lastregcode = lastbusinessmain.getReg_code();
		String reason = row.getString("reason");
		MaterialReplenish materialReplenish = null;
		try {
			 materialReplenish = FacadeFactory
					.getRecmaterialFacade().getMaterialReplenishByregcode(
							lastregcode);
		} catch (Exception e) {
			LogUtil.error("RecmaterialAction.updateMaterialreplenish ��ȡ����������Ϣ����"+e.getMessage());
		}
		return this.setActionJsonObject(JsonUtil.object2json(materialReplenish));
	}
	
}


