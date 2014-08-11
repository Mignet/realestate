/**
 * Project Name:dxtx_re
 * File Name:RegisterAction.java
 * Package Name:com.szhome.cq.web.common
 * Date:2014-1-8����11:03:24
 * Copyright (c) 2014, dxtx All Rights Reserved.
 *
*/

package com.szhome.cq.web.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.plan.commons.Row;
import com.plan.exceptions.GeneralException;
import com.plan.web.JsonResult;
import com.szhome.commons.log.LogUtil;
import com.szhome.cq.business.FacadeFactory;
import com.szhome.cq.business.IRegisterFacade;
import com.szhome.cq.delegate.BaseDelegate;
import com.szhome.cq.domain.model.BusinessMain;
import com.szhome.cq.domain.model.Reg_relationship;
import com.szhome.cq.utils.JsonUtil;

/**
 * ClassName:RegisterAction <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2014-1-8 ����11:03:24 <br/>
 * @author   Joyon
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class RegisterAction extends BaseDelegate{
	
	private IRegisterFacade registerFacade = FacadeFactory.getRegisterFacade();		//�Ǽǲ�����
	
	/**
	 * 
	 * registerPreview:�Ǽǲ�Ԥ��
	 * @author Joyon
	 * @return
	 * @throws GeneralException 
	 * @since JDK 1.6
	 */
	public String registerPreview(Row row) throws GeneralException{
		String registerPerson=getOperatorInfo().getUserName();			//��ǰ��½���û�����
		Map<String,Object> paramMap = new HashMap<String,Object>();
		String reg_code = "";
		String proc_id = row.getString("proc_id");
		try{
			reg_code = FacadeFactory.getCommonFacade()
					.getBusinessMainByProcId(proc_id).getReg_code();
		} catch (Exception e) {
			LogUtil.error(e.getMessage());
		}
		
		paramMap.put("reg_code", reg_code);
		Map<String,Object> resultMap = null;
		try {
			resultMap = registerFacade.registerPreview(paramMap);
			//resultMap = registerFacade.getregisterInfo(paramMap);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return JsonUtil.object2json(resultMap);
	}
	
	/**
	 * 
	 * getuserInfo:(��ȡʹ��Ȩ�ڵصĵǼǲ�Ԥ��). <br/>
	 * @author xuzz
	 * @return
	 * @throws GeneralException 
	 * @since JDK 1.6
	 */
	public String getuserInfo(Row row) throws GeneralException
	{
		String reg_code = "";
		String proc_id = row.getString("proc_id");
		String registerPerson=getOperatorInfo().getUserName();			//��ǰ��½���û�����
		Map<String,Object> paramMap = new HashMap<String,Object>();
		try{
			reg_code = FacadeFactory.getCommonFacade().getBusinessMainByProcId(proc_id).getReg_code();
		} catch (Exception e) {
			LogUtil.error(e.getMessage());
		}
		
		paramMap.put("reg_code", reg_code);
		Map<String,Object> resultMap=registerFacade.getuserInfo(paramMap);
		
		return JsonUtil.object2json(resultMap);
	}
	/**
	 * 
	 * getregisterInfo:(�Ǽǲ�Ԥ����ͨ��). <br/>
	 * @author xuzz
	 * @return  getattachInfo
	 * @since JDK 1.6
	 */
	public String getregisterInfo(Row row)
	{
		Map<String,Object> paramMap = new HashMap<String,Object>();
		String reg_code = "";
		String proc_id = row.getString("proc_id");
		try{
			reg_code = FacadeFactory.getCommonFacade().getBusinessMainByProcId(proc_id).getReg_code();
		} catch (Exception e) {
			LogUtil.error(e.getMessage());
		}
		paramMap.put("reg_code", reg_code);
		Map<String,Object> resultMap=registerFacade.getregisterInfo(paramMap);
		return JsonUtil.object2json(resultMap);
	}
	
	public String getOwnershipInfo(Row row){
		Map paramMap = new HashMap();
		String reg_code = row.getString("reg_code");
		//paramMap.put("search_type", "�Ǽǲ�Ԥ��");
		paramMap.put("reg_code", reg_code);
		Map resultMap = null;
		try {
			resultMap = registerFacade.getOwnershipInfo(paramMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonUtil.object2json(resultMap);
	}
	
	
	/**
	 * 
	 * registerSave:�Ǽǲ�����
	 *
	 * @author xuzz
	 * @return
	 * @throws GeneralException 
	 * @since JDK 1.6
	 */
	public String registerSave(Row row) throws GeneralException{
		Map<String,Object> paraMap = new HashMap<String,Object>();
		String bus_id = null;
		String reg_code = "";
		String proc_id = row.getString("proc_id");
		//����ǰ�ǼǱ��
		String pre_reg_code="";
		try{
			reg_code = FacadeFactory.getCommonFacade().getBusinessMainByProcId(proc_id).getReg_code();
			bus_id = FacadeFactory.getCommonFacade().getBusinessMainByProcId(proc_id).getBus_id();
		} catch (Exception e) {
			LogUtil.error(e.getMessage());
		}
		paraMap.put("bus_id", bus_id);
		paraMap.put("reg_code", reg_code);
		paraMap.put("pre_reg_code", pre_reg_code);		
		paraMap.put("recorder",getOperatorInfo().getUserName());
		String procdefId = row.getString("procdefId");
		Map<String,Object> resultMap = null;
		try {
			resultMap = registerFacade.saveRegister(paraMap);
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return JsonUtil.object2json(resultMap);
	}
	/**
	 * ���Ǽǵ�Ԫ�Ƿ��������
	 * @author peter
	 * @return
	 */
	public String checkBusData(Row row)
	{
		//-------ajax---------
		String reg_unit_code = row.getString("reg_unit_code");
		String regtype = row.getString("regtype");
		String bustype = row.getString("bustype");
		Map map= registerFacade.checkBusData(reg_unit_code,regtype,bustype);
		return JsonUtil.object2json(map);
	}
	
	/**
	 * 
	 * getMortMess:��ȡ��Ѻ�Ǽǹ�������. <br/>
	 * @author PANDA
	 * @return
	 * @throws GeneralException 
	 * @since JDK 1.6
	 */
	public String getMortMess(Row row) throws GeneralException{
		//-------ajax---------
		String proc_id = row.getString("proc_id");
		BusinessMain bus = FacadeFactory.getCommonFacade().getBusinessMainByProcId(proc_id);
		
		String user = getOperatorInfo().getUserName();
		Map ma = new HashMap();
		ma.put("id",bus.getBus_id());
		Map map = FacadeFactory.getRegisterFacade().getMortMess(ma);
		map.put("reg_code",bus.getReg_code());
		map.put("procdef_id", bus.getPro_def_id());
		map.put("recorder", user);
		if( map.isEmpty()){
			
			return this.setActionJsonObject(new JsonResult(true, "��Ѻ�������ݲ����ڣ�", null).toJsonString());
		}
		
		return JsonUtil.object2json(map);
	}
	
	/**
	 * 
	 * getReg_relationship:(��ȡ�Ǽǵ�Ԫ����������). 
	 *
	 * @author Joyon
	 * @return
	 * @since JDK 1.6
	 */
	public String getReg_relationship(Row row){
		Reg_relationship reg_relationship=null;
		String proc_id = row.getString("proc_id");
		try {
			reg_relationship = FacadeFactory.getCommonFacade()
					.getReg_relationshipByProcId(proc_id);
		} catch (Exception e) {
			LogUtil.error("��ȡ�Ǽǵ�Ԫ���������ݳ���"+e.getMessage());
		}
		
		return JsonUtil.object2json(reg_relationship);
	}
	/**
	 * 
	 * getTreeForMortPre:��ȡ��Ѻ�Ǽǲ�Ԥ����������ṹ. <br/>
	 * @author PANDA
	 * @return
	 * @since JDK 1.6
	 */
	public String getTreeForMortPre(Row row){
		 //-------ajax---------
		String proc_id = row.getString("proc_id");
		 String str = "";
		 String busid = FacadeFactory.getCommonFacade().getBusinessMainByProcId(proc_id).getBus_id();
		 Map m = new HashMap();
		 m.put("id", busid);
		 
		 List<Map<String, Object>>  listmap = FacadeFactory.getRegisterFacade().getRegBookTreeForMort(m);
		 org.json.JSONArray ja = new  org.json.JSONArray(listmap);
	     str = ja.toString(); 
	     System.out.println(str);
	     return this.setActionJsonObject(str);
   }
	
	/**
	 * 
	 * getMorPre:(��ȡ��Ѻ�Ǽǲ�Ԥ������). 
	 *
	 * @author Joyon
	 * @return
	 * @throws GeneralException 
	 * @since JDK 1.6
	 */
	public String getMorPre(Row row) throws GeneralException{
		String proc_id = row.getString("proc_id");
		String reg_unit_code = row.getString("reg_unit_code");
		String reg_code = "";
		String registerPerson=getOperatorInfo().getUserName();			//��ǰ��½���û�����
		Map<String,Object> paraMap = new HashMap<String,Object>();
		if(proc_id!=null && !proc_id.equals("")){
			try{
				reg_code = FacadeFactory.getCommonFacade().getBusinessMainByProcId(proc_id).getReg_code();
			} catch (Exception e) {
				LogUtil.error(e.getMessage());
			}
		}
		paraMap.put("reg_code", reg_code);
		paraMap.put("reg_unit_code",reg_unit_code);
		Map<String,Object> resultMap=registerFacade.getregisterInfo(paraMap);
		
		return JsonUtil.object2json(resultMap);
	}
	
	/**
	 * 
	 * isRegisterSave:(�жϵ�ǰ�Ƿ��Ѿ��ǲ�). <br/>
	 * TODO(�ں�׼���ڿ���)
	 *
	 * @author Joyon
	 * @return
	 * @since JDK 1.6
	 */
	public String isRegisterSave(Row row){
		String proc_id = row.getString("proc_id");
		Map<String,Object> resultMap = new HashMap<String,Object>();
		BusinessMain businessMain = FacadeFactory.getCommonFacade().getBusinessMainByProcId(proc_id);
		
		if(businessMain == null){
			resultMap.put("result", "1");
			return JsonUtil.object2json(resultMap);
		}
		
		String reg_code = businessMain.getReg_code();
		Map<String,Object> paraMap = new HashMap<String,Object>();
		paraMap.put("reg_code", reg_code);
		
		Boolean result  = FacadeFactory.getRegisterFacade().isRegisterSave(paraMap);
		if(result){
			resultMap.put("result", "1");
		}else{
			resultMap.put("result", "0");
		}
		
		return JsonUtil.object2json(resultMap);
	}
	
}


