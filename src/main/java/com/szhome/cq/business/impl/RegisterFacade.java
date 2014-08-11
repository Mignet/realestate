/**
 * Project Name:dxtx_re
 * File Name:RegisterFacade.java
 * Package Name:com.szhome.cq.business.impl
 * Date:2014-1-8����11:08:39
 * Copyright (c) 2014, dxtx All Rights Reserved.
 *
*/

package com.szhome.cq.business.impl;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.tools.ant.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.plan.commons.Row;
import com.plan.commons.RowImpl;
import com.szhome.commons.log.LogUtil;
import com.szhome.cq.business.BusinessException;
import com.szhome.cq.business.FacadeFactory;
import com.szhome.cq.business.IRegisterFacade;
import com.szhome.cq.domain.model.AcceptRule;
import com.szhome.cq.domain.model.Applicant;
import com.szhome.cq.domain.model.BusOwnership;
import com.szhome.cq.domain.model.BusType;
import com.szhome.cq.domain.model.BusinessMain;
import com.szhome.cq.domain.model.Bususeright;
import com.szhome.cq.domain.model.Certificate;
import com.szhome.cq.domain.model.DictItem;
import com.szhome.cq.domain.model.Holder;
import com.szhome.cq.domain.model.HolderRelationship;
import com.szhome.cq.domain.model.House;
import com.szhome.cq.domain.model.HouseHistory;
import com.szhome.cq.domain.model.HouseTrend;
import com.szhome.cq.domain.model.Land;
import com.szhome.cq.domain.model.LandHistory;
import com.szhome.cq.domain.model.LandTrend;
import com.szhome.cq.domain.model.Mortgage;
import com.szhome.cq.domain.model.Reg_Advnotice;
import com.szhome.cq.domain.model.Reg_Demurrer;
import com.szhome.cq.domain.model.Reg_Distrain;
import com.szhome.cq.domain.model.Reg_Easement;
import com.szhome.cq.domain.model.Reg_Mortgage;
import com.szhome.cq.domain.model.Reg_Share;
import com.szhome.cq.domain.model.Reg_Useright;
import com.szhome.cq.domain.model.Reg_baseInfo;
import com.szhome.cq.domain.model.Reg_ownership;
import com.szhome.cq.domain.model.Reg_relationship;
import com.szhome.cq.domain.model.Remark;
import com.szhome.cq.utils.SequenceUtil;
import com.szhome.cq.utils.WbfConstants;

/**
 * �Ǽǲ�facade
 * Date:     2014-1-8 ����11:08:39
 * @author   Joyon
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
@Component
@Transactional
@Scope("prototype")
public class RegisterFacade implements IRegisterFacade{
	
	//public String  HOUSE_ONWERSHIP="102";
	//public String  HOUSE_ONWERSHIP="102";
	//public String  HOUSE_ONWERSHIP="102";
	//public String  HOUSE_ONWERSHIP="102";
	
	@Autowired
	private  DictItem dictItemDao;									//�ֵ�
	@Autowired
	private House houseDao;											//����
	@Autowired
	private Reg_baseInfo reg_baseInfoDao;							//�Ǽǲ���������Ϣ��
	@Autowired
	private BusType bus_typeDao;									//ҵ�����ͱ�
	@Autowired
	private Reg_ownership reg_ownershipDao;							//�Ǽǲ�������Ȩ���֣�
	@Autowired
	private Holder holderDao;										//Ȩ����
	@Autowired
	private HolderRelationship holderRelationshipDao;				//Ȩ���˹�����
	@Autowired
	private Certificate certificateDao;								//���ز�֤
	@Autowired
	private Reg_Advnotice reg_AdvnoticeDao;							//�Ǽǲ�����������_Ԥ��Ǽǣ�
	@Autowired
	private Remark remarkDao;										//�Ǽǲ�����ע��
	@Autowired
	private Reg_Useright reg_UserightDao;							//�Ǽǲ���ʹ��Ȩ���֣�
	@Autowired
	private Reg_Mortgage reg_MortgageDao;							//�Ǽǲ�������Ȩ������_��Ѻ�Ǽǣ�
	@Autowired
	private Reg_Share reg_ShareDao;									//�Ǽǲ�(���в���)
	@Autowired
	private Reg_Easement reg_EasementDao;							//�Ǽǲ�������Ȩ������_����Ȩ�Ǽǣ�
	@Autowired
	private Reg_Demurrer reg_DemurrerDao;							//�Ǽǲ�����������_����Ǽǣ�
	@Autowired
	private Reg_Distrain reg_DistrainDao;							//�Ǽǲ�����������_���Ǽǣ�
	@Autowired
	private AcceptRule acceptRuleDao;                               //ҵ���������
	@Autowired
	private Mortgage mortDao;                                       //��ѺȨ������Ϣ����
	@Autowired
	private BusinessMain businessMainDao;							//ҵ������ 
	@Autowired
	private Applicant applicantDao;									//�����˱�
	@Autowired
	private HouseHistory houseHistoryDao;							//������ʷ��	
	@Autowired
	private HouseTrend houseTrendDao;								//������Ʊ�
	@Autowired
	private BusOwnership busOwnershipDao;							//�Ǽ���Ϣ��
	@Autowired
	private Reg_relationship reg_relationshipDao;					//�Ǽǵ�Ԫ������
	@Autowired
	private Land landDao;											//����
	@Autowired
	private LandTrend landTrendDao;									//��������
	@Autowired
	private LandHistory landHistoryDao;								//������ʷ	
	@Autowired
	private Mortgage mortgageDao;									//��ѺȨ�Ǽ���Ϣ
	
	@Autowired
	private Bususeright bususerightDao;								//ʹ��Ȩ�Ǽ���Ϣ
	private String reg_code;										//�ǼǱ��
	 
	
	
	/**
	 * 
	 * �Ǽǲ�Ԥ������Ȩ����.
	 * @param paraMap.put--	reg_code:�ǼǱ��
	 * @see com.szhome.cq.business.IRegisterFacade#registerPreview(java.util.Map)
	 * @author Joyon
	 */
	public Map<String, Object> registerPreview(Map<String, Object> paraMap) {
		paraMap.put("search_type", "�Ǽǲ�Ԥ��");
		reg_code = paraMap.get("reg_code").toString();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		Map<String,Object> naturalInfoMap = getNaturalInfo(paraMap);			//��ȡ��Ȼ��Ϣ
		//������ط��ز�֤�� ��Ȩ������
		String parcel_code = naturalInfoMap.get("PARCEL_CODE").toString();
		Reg_Useright reg_useright = getEffectiveReg_userightByRegUnitCode(parcel_code);
		if(reg_useright!=null){
			naturalInfoMap.put("PA_CER_NO", reg_useright.getCer_no());
			String userright_type = FacadeFactory.getDictFacade().getDictItemNameByCodeAndTypeCode(reg_useright.getUseright_type(), "014");
			naturalInfoMap.put("USERRIGHT_TYPE",userright_type);
		}
		resultMap.put("naturalInfo", naturalInfoMap);							//������Ȼ��Ϣ
		resultMap.put("ownershipInfo",getOwnershipInfo(paraMap));				//��������Ȩ��Ϣ
		return resultMap;
	}
	/**
	 * 
	 * getuserInfo:(�Ǽǲ�Ԥ��ʹ��Ȩ����.). <br/>
	 * @author xuzz
	 * @param useMap
	 * @return
	 * @since JDK 1.6
	 */
	public Map<String,Object> getuserInfo(Map<String ,Object> useMap)
	{
		useMap.put("search_type", "�Ǽǲ�Ԥ��");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map<String , Object> resultnatural=getNaturalInfo(useMap);
		Map<String , Object> resultuser=getuseInfo(useMap);
		resultuser.put("PARCEL_CODE", resultnatural.get("PARCEL_CODE"));
		resultuser.put("LAND_ADDRESS", resultnatural.get("LAND_ADDRESS"));
		resultMap.put("naturalInfo", resultnatural);					//������Ȼ��Ϣ
		resultMap.put("userInfo",resultuser);				//����ʹ��ȨȨ��Ϣ
		return resultMap;
	}
	
	/**
	 * getRegTypeByProdefId ͨ����ǰ�ǼǱ�� ��ȡҵ������ID
	 * @param reg_code
	 * @author xuzz
	 * @return
	 */
	public Map getRegTypeByProdefId(Map map)
	{
		//Map<String, Object> map = new HashMap<String, Object>();
		//map.put("reg_code", reg_code);
		Map resultMap=bususerightDao.queryMapByKey("Register.getRegTypeByProdefId",map);
		if(resultMap!=null)
		{
			return  resultMap;
		}
		return  resultMap;
	}
	
	/**
	 * 
	 * getregisterInfo:(�Ǽǲ�Ԥ����ͨ��.). <br/>
	 * @author xuzz
	 * @param useMap
	 * @return
	 * @since JDK 1.6
	 */
	public Map<String,Object> getregisterInfo(Map<String ,Object> map)
	{
		String bus_class_id = getBusTypeParentIdByRegId(map);
		String cur_bus_type_id = getBusTypeIdByRegIdMap(map);
		reg_code = map.get("reg_code").toString();
		map.put("search_type", "�Ǽǲ�Ԥ��");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map<String , Object> resultnatural= null;
		//����ǵ�Ѻ�Ǽ�   ���ж������   ͨ���Ǽǵ�Ԫ��Ż�ȡ��Ȼ��Ϣ
		if(bus_class_id.equals(WbfConstants.MORTGAGE_RIGHT)){
			String reg_unit_code = map.get("reg_unit_code").toString();
			resultnatural = getNaturalInfoMapByRegUnitCode(reg_unit_code);
		}else{
			resultnatural = getNaturalInfo(map);
		}
		String typeid =getBusTypeParentIdByRegId(map);
		Map<String , Object> resultuser=null;
		Map<String , Object> resulthis=null;
		Map prodefMap= getRegTypeByProdefId(map);
		String prodefid="";
		if(prodefMap!=null)
		{
			prodefid=prodefMap.get("PRO_DEF_ID").toString();
		}
		//ͨ���ǼǱ�Ż�ȡ�Ǽǵ�Ԫ����������
		Map<String , Object> relationMap= null; 
		if(bus_class_id.equals(WbfConstants.REALESTATE_CAN)||prodefid.equals(WbfConstants.UNDEMURRER)||prodefid.equals(WbfConstants.UNATTACH)||prodefid.equals(WbfConstants.REVOKEAPPROVAL))
		{
			relationMap = getRegUnitRelMapByRegId(map);
			map.put("reg_code", relationMap.get("LAST_REG_CODE").toString());
			if(relationMap!=null)
			{
				resultuser=  getPropInfo(map,bus_class_id);						//��ȡ�Ǽǲ���ز�������
				map.put("reg_code", relationMap.get("REG_CODE").toString());
				map.put("REG_UNIT_TYPE", relationMap.get("REG_UNIT_TYPE").toString());
				resulthis=getPropInfoHis("book",map,bus_class_id);
				if(resulthis==null)
				{
					resulthis=getPropInfoHis("",map,bus_class_id);
					if(resulthis==null)
					{
						resulthis=new HashMap<String, Object>();
						resulthis.put("REG_CODE", relationMap.get("REG_CODE").toString());
					}
				}
				resulthis.put("REG_CODE", relationMap.get("REG_CODE").toString());
				if(resulthis.get("DIS_DATE")!=null&&resulthis.get("DIS_DATE")!="")
				{
					resulthis.put("DIS_DATE", DateUtils.format((Date)resulthis.get("DIS_DATE"), "yyyy-MM-dd"));
				}
				if(!prodefid.equals(WbfConstants.REVOKEAPPROVAL))
				{
					resultMap.put("userInfoHis",resulthis);				//����ע����Ϣ
				}
				
			}
		}
		else
		{
			//�����ǰҵ��Ϊ��Ѻע��  ���� ��߶��Ѻע�����ĵǼǱ��Ϊ��һ�ε�Ѻ���
			relationMap = getRegUnitRelMapByRegId(map);
			if(cur_bus_type_id.equals(WbfConstants.MORTGAGE_CANCEL) || cur_bus_type_id.equals(WbfConstants.MAX_MORTGAGE_CANCEL)){
				String bus_id = getBusMainMapByRegId(map).get("BUS_ID").toString();
				String origregcode = getOrigMortRegCode(bus_id);
				map.put("reg_code", origregcode);
			}
			
			
			resultuser=  getPropInfo(map,bus_class_id);						//��ȡ�Ǽǲ���ز�������
			resultuser.put("REG_CODE", relationMap.get("REG_CODE").toString());
			
			//�����ǰҵ��Ϊ��Ѻע��  ���� ��߶��Ѻע��  ����Ȩ��Ϣ���ϵ�ǰ�ĵǼǱ����Ϊ  ��Ѻע�����  ���ϵ�Ѻע��ʱ��  ����Ѻ�ǲ���
			if(cur_bus_type_id.equals(WbfConstants.MORTGAGE_CANCEL) || cur_bus_type_id.equals(WbfConstants.MAX_MORTGAGE_CANCEL)){
				map.put("reg_code", reg_code);
				List<Reg_Mortgage> reg_MortgageList= reg_MortgageDao.getAll("where reg_code=:reg_code", map);
				//����ǲ����е�Ѻ��Ϣ����ӵǼ�ʱ�� �͵ǲ���   �������ÿ�
				if(reg_MortgageList.size()>0){
					String can_reg_date = DateUtils.format(reg_MortgageList.get(0).getReg_date(),"yyyy-MM-dd");
					resultuser.put("can_reg_date", can_reg_date);
					resultuser.put("can_recorder", reg_MortgageList.get(0).getRecorder());
				}else{
					resultuser.put("can_reg_date", "");
					resultuser.put("can_recorder", "");
				}
				resultuser.put("can_mort_no", reg_code);
				
			}
			
		}
		resultuser.put("PARCEL_CODE", resultnatural.get("PARCEL_CODE"));
		resultuser.put("LAND_ADDRESS", resultnatural.get("LAND_ADDRESS"));
		
		if(resultuser.get("START_DATE")!=null&&!resultuser.get("START_DATE").toString().trim().equals(""))
		{
			resultuser.put("START_DATE", DateUtils.format((Date)resultuser.get("START_DATE"), "yyyy-MM-dd"));
		}
		if(resultuser.get("END_DATE")!=null&&!resultuser.get("END_DATE").toString().trim().equals(""))
		{
			resultuser.put("END_DATE", DateUtils.format((Date)resultuser.get("END_DATE"), "yyyy-MM-dd"));
		}
		if(resultuser.get("DIS_DATE")!=null&&!resultuser.get("DIS_DATE").toString().trim().equals("")) 
		{
			resultuser.put("DIS_DATE", DateUtils.format((Date)resultuser.get("DIS_DATE"), "yyyy-MM-dd"));
		}
		if(resultuser.get("REG_DATE")!=null && !resultuser.get("REG_DATE").toString().trim().equals(""))
		{
			resultuser.put("REG_DATE", DateUtils.format((Date)resultuser.get("REG_DATE"), "yyyy-MM-dd"));
		}
		
		
		//������ط��ز�֤�� ��Ȩ������
		String parcel_code = resultnatural.get("PARCEL_CODE").toString();
		Reg_Useright reg_useright = getEffectiveReg_userightByRegUnitCode(parcel_code);
		if(reg_useright!=null){
			resultnatural.put("PA_CER_NO", reg_useright.getCer_no());
			String userright_type = FacadeFactory.getDictFacade().getDictItemNameByCodeAndTypeCode(reg_useright.getUseright_type(), "014");
			resultnatural.put("USERRIGHT_TYPE",userright_type);
		}
		
		
		resultMap.put("naturalInfo", resultnatural);					//������Ȼ��Ϣ
		resultMap.put("userInfo",resultuser);				//����ʹ��ȨȨ��Ϣ
		return resultMap;
	}
	public Map<String,Object> registerSave(Map<String,Object> paraMap){
		
		Map<String,Object> resultMap = null;
		if(isRegisterSave(paraMap)){
			resultMap = new HashMap();
			resultMap.put("result", "exists");
			return resultMap;
		}
		//if(true){
			
			//return null;
		//}
		
		String busType = getBusTypeParentNameByRegId(paraMap);						//ҵ�����  �ܹ��а˴���
		if(busType.equals("��������Ȩ�Ǽ�")){
			resultMap = registerSaveOwnerShip(paraMap);
		}else if(busType.equals("ʹ��Ȩ�Ǽ�")){
			
		}else if(busType.equals("��Ѻ�Ǽ�")){
			
		}else if(busType.equals("����Ȩ�Ǽ�")){
			
		}else if(busType.equals("Ԥ��Ǽ�")){
			
		}else if(busType.equals("���Ǽ�")){
			
		}else if(busType.equals("���в���")){
			
		}else if(busType.equals("��ע")){
			
		}
		
		return resultMap;		
	}
	
/**********************************     Ԥ������          *************************************/
	/**********************************     ��Ȼ��Ϣ����            *************************************/
	
	/**
	 * 
	 * getNaturalInfo:(��ȡ��Ȼ��Ϣ )
	 *
	 * @author Joyon
	 * @param paraMap.put--search_type:�Ǽǲ�Ԥ��/�Ǽǲ���ѯ	reg_code:�ǼǱ��
	 * @return
	 * @since JDK 1.6
	 */
	public  Map<String, Object> getNaturalInfo(Map<String, Object> paraMap){
		String search_type = checkRegTypeByRegId(paraMap);							//search_type:�Ǽǲ�Ԥ��/�Ǽǲ���ѯ  reg_code:�ǼǱ��
		Map<String, Object> resultMap = new HashMap<String,Object>();
		String key = null;
		String reg_unit_type = "";													//�Ǽǵ�Ԫ����   ���� �ڵ� ¥��
		String reg_unit_code = "";													//�Ǽǵ�Ԫ���
		//Map<String,Object> naturalTypeMap = getRegNaturalTypeMapByRegCode(paraMap);
		//��ȡ�Ǽǵ�Ԫ����������   ���Ϊ���������־
		Map<String,Object> regUnitTypeMap = getRegUnitRelMapByRegId(paraMap);
		if(regUnitTypeMap != null || regUnitTypeMap.isEmpty()){
			reg_unit_type = regUnitTypeMap.get("REG_UNIT_TYPE").toString();
			reg_unit_code = regUnitTypeMap.get("REG_UNIT_CODE").toString();
			if(reg_unit_code.equals("") || reg_unit_code == null){
				LogUtil.error("�Ǽǵ�Ԫ���Ϊ�գ�");
				throw new BusinessException("�Ǽǵ�Ԫ���Ϊ�գ�");
			}
		}else{
			LogUtil.error("�Ǽǵ�Ԫ����������Ϊ�գ�");
			throw new BusinessException("�Ǽǵ�Ԫ����������Ϊ�գ�");
		}
		//����
		if(reg_unit_type.equals(WbfConstants.HOUSE)){
			key = "Register.getNaturalInfoMapByHouseRegUnitCode";
		}else if(reg_unit_type.equals(WbfConstants.PARCEL)){
			key = "Register.getNaturalInfoMapByParcelRegUnitCode";
		}else if(reg_unit_type.equals(WbfConstants.BUILDING)){
			
		}
		paraMap.put("reg_unit_code", reg_unit_code);
		try {
			resultMap.putAll(dictItemDao.queryMapByKey(key, paraMap));
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("��ȡ��Ȼ��Ϣʧ��"+e.getMessage());
		}
//		Map certificateMap = getCertificateNoMapByRegCode(paraMap);
//		if(certificateMap!=null && !certificateMap.isEmpty()){
//			resultMap.putAll(certificateMap);
//		}
		//��ȡ��������
		 Map house_attr_name_map = getHouseAttrNameMapByRegCode(paraMap);
		 if(house_attr_name_map != null && !house_attr_name_map.isEmpty()){
			 resultMap.putAll(house_attr_name_map);
		 }else{
			 LogUtil.error("RegisterFacade.getnaturalinfo() ������������Ϊ��");
		 }
		 
		 String pacel_real_useage_name = FacadeFactory.getDictFacade().getDictItemNameByCodeAndTypeCode(resultMap.get("REAL_USAGE").toString(), "015");
		
		 resultMap.put("PA_REAL_USAGE_NAME", pacel_real_useage_name);
		 resultMap.putAll(paraMap);
		return resultMap;
	}
	
	/**
	 * 
	 * getNaturalInfoMapByRegUnitCode:(���ݵǼǵ�Ԫ��Ż�ȡ��Ȼ��Ϣ )
	 *
	 * @author Joyon
	 * @param reg_unit_code
	 * @return ��Ȼ��ϢMap
	 * @since JDK 1.6
	 */
	public  Map<String, Object> getNaturalInfoMapByRegUnitCode(String reg_unit_code){
		Map<String, Object> resultMap = new HashMap<String,Object>();
		Map<String,Object> paraMap =new HashMap<String,Object>();
		Map<String,Object> certificateMap = new HashMap<String,Object>();			//���صķ��ز�֤��Ϣ  ��ǰ�Ǽǵ�Ԫ��Ч�ķ��ز�֤��
		paraMap.put("reg_unit_code", reg_unit_code);
		String key = null;
		String reg_unit_type = "";													//�Ǽǵ�Ԫ����   ���� �ڵ� ¥��
		//��ȡ�Ǽǵ�Ԫ����������   ���Ϊ���������־
		List<Reg_relationship> regRelationShipList = reg_relationshipDao.getAll(" where reg_unit_code=:reg_unit_code",paraMap);
		if(regRelationShipList!=null && !regRelationShipList.isEmpty()){
			reg_unit_type = regRelationShipList.get(0).getReg_unit_type();
			if(reg_unit_code.equals("") || reg_unit_code == null){
				LogUtil.error("�Ǽǵ�Ԫ���Ϊ�գ�");
				throw new BusinessException("RegisterFacade.getNaturalInfoMapByRegUnitCode �Ǽǵ�Ԫ���Ϊ�գ�");
			}
		}else{
			LogUtil.error("RegisterFacade.getNaturalInfoMapByRegUnitCode:��ȡ�Ǽǵ�Ԫ�������еǼǵ�Ԫ���ͳ���");
		}
		//����
		
		if(reg_unit_type.equals(WbfConstants.HOUSE)){
			key = "Register.getNaturalInfoMapByHouseRegUnitCode";
			Reg_ownership effictveOwnership = getEffectiveReg_OwnershipByRegUnitCode(reg_unit_code);
			if(effictveOwnership!=null){
				certificateMap.put("CER_NO",effictveOwnership.getCer_no());
			}else{
				LogUtil.error("RegisterFacade.getNaturalInfoMapByRegUnitCode ��ǰ�ǼǱ����Ч�ĵǼǲ�����Ȩ������ϢΪ��   reg_unit_code:"+reg_unit_code);
			}
			//certificateMap.put("CER_NO",getEffectiveReg_OwnershipByRegUnitCode(reg_unit_code).getCer_no());
		}else if(reg_unit_type.equals(WbfConstants.PARCEL)){
			key = "Register.getNaturalInfoMapByParcelRegUnitCode";
			Reg_Useright effictveUseright = getEffectiveReg_userightByRegUnitCode(reg_unit_code);
			if(effictveUseright!=null){
				certificateMap.put("CER_NO",effictveUseright.getCer_no());
			}else{
				LogUtil.error("RegisterFacade.getNaturalInfoMapByRegUnitCode ��ǰ�ǼǱ����Ч�ĵǼǲ�ʹ��Ȩ������ϢΪ��");
			}
		}else if(reg_unit_type.equals(WbfConstants.BUILDING)){
			
		}
		try {
			resultMap.putAll(dictItemDao.queryMapByKey(key, paraMap));
		} catch (Exception e) {
			LogUtil.error("RegisterFacade.getNaturalInfoMapByRegUnitCode����ȡ��Ȼ��Ϣ����"+e.getMessage());
			throw new BusinessException("��ȡ��Ȼ��Ϣʧ��"+e.getMessage());
		}
		
		if(certificateMap!=null && !certificateMap.isEmpty()){
			resultMap.putAll(certificateMap);
		}
		
		return resultMap;
	}
	
	/**
	 * 
	 * checkRegTypeByRegId:(ͨ���ǼǱ�� ��鵱ǰִ�е��ǵǼǲ�Ԥ�� ���ǵǼǲ���ѯʹ��Ȩ����). 
	 * @author xuzz
	 * @param paraMap	reg_code
	 * @return		�Ǽǲ�Ԥ��/�Ǽǲ���ѯ
	 * @since JDK 1.6
	 */
	public String checkuserByRegId(Map paraMap){
		String result = null;
		Map<String,Object> resultMap = null;
		
		try {
			resultMap = houseDao.queryMapByKey(
					"Register.getBkuserInfoByRegId", paraMap);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("��ȡ�Ǽǲ�ʹ��Ȩ������Ϣ����"+e.getMessage());
		}
		//����Ȩ���ֵǼ���Ϣ������  ���صǼǲ�Ԥ��
		if(resultMap == null || resultMap.isEmpty()){
			result="�Ǽǲ�Ԥ��";
		}else{
			result = "�Ǽǲ���ѯ";
		}
		return result;
	}
	
	
	/**
	 * 
	 * getuserInfoByRegId:(ͨ���ǼǱ�Ż�ȡ �Ǽǲ�ʹ��Ȩ������Ϣ). 
	 * @author xuzz
	 * @param paraMap reg_code �ǼǱ��
	 * @return
	 * @since JDK 1.6
	 */
	public Map<String, Object> getuserInfoByRegId(Map<String, Object> paraMap){
		Map<String, Object> bkOwnershipInfoMap = null;
		try {
			bkOwnershipInfoMap = houseDao.queryMapByKey("Register.getBkuserInfoByRegId", paraMap);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("��ȡ�Ǽǲ�ʹ��Ȩ������Ϣ����"+e.getMessage());
		}
		return bkOwnershipInfoMap;
	}
	/**
	 * 
	 * getPropInfoHis:(�Ǽǲ�Ԥ�����ز�֤ע��ҵ�����ڲ�ѯע����Ϣ). <br/>
	 * @author xuzz
	 * @param useMap
	 * @return
	 * @since JDK 1.6
	 */
	public Map<String,Object> getPropInfoHis(String book,Map<String, Object> useMap,String typeid)
	{
		String whereSql="";
		if(book!="")
		{
			whereSql = " where REG_CODE=:reg_code ";
		}
		Map<String, Object> registerMap = getBKInfoByRegId(getDataTableKeyByBusTypeID(typeid,book),whereSql,useMap);
		if(registerMap==null)
		{
			registerMap=getBKInfoByRegId(getDataTableKeyByBusTypeID(typeid,""),"",useMap);
			return registerMap;
		}
		registerMap.put("REG_DATE", DateUtils.format((Date)registerMap.get("REG_DATE"), "yyyy-MM-dd"));
		return registerMap;
	}
	
	/**
	 * 
	 * getPropInfo:(�Ǽǲ�Ԥ��Ȩ�����֣�����). <br/>
	 * @author xuzz
	 * @param useMap
	 * @return
	 * @since JDK 1.6
	 */
	public Map<String,Object> getPropInfo(Map<String, Object> useMap,String bus_class_id)
	{
		String typeid="";
		typeid =getBusTypeParentIdByRegId(useMap);
		
		String cur_bus_type_id = getBusTypeIdByRegIdMap(useMap);			//��ǰҵ��ID
		String whereSql = " where REG_CODE=:reg_code ";
		//��Ѻ�Ǽǲ� ��ȡʱ ���յǼǵ�Ԫ��Ż�ȡ  �Ѿ���key ��д�� 
		if(typeid.equals(WbfConstants.MORTGAGE_RIGHT)){
			whereSql="";
		}
		
		Map<String, Object> registerMap = getBKInfoByRegId(getDataTableKeyByBusTypeID(typeid,"book"),whereSql,useMap);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String holderkey="";
		String cerkey=""; 
		//���õǲ�����  ���ǲ���  
		//�����ǰҵ���� ��߶��Ѻȷ���Ǽ�  �ѵ�ǰҵ���Ѻ��ŵǲ��˺�ʱ������߶�������ʾ   ��Ҫ�õ�ǰһ��ҵ��ĵǲ��ˡ��ǲ�ʱ��͵�Ѻ���
		if(cur_bus_type_id.equals(WbfConstants.MAX_CONFIRM_REG)){
			if(registerMap==null||registerMap.isEmpty()){
				resultMap = getBKInfoByRegId(getDataTableKeyByBusTypeID(typeid,""),null,useMap);
				if(resultMap!=null)
				{
					
					resultMap.put("sure_reg_code",useMap.get("reg_code").toString());
					resultMap.put("sure_reg_date"," ");
					resultMap.put("sure_recorder"," ");
					holderkey = "Register.getAppAsHolderByRegId";										//�Ǽǲ�Ԥ��ʱ���������в�ѯ  
					cerkey = "Register.getRightCertificateNo";											//�Ǽǲ�Ԥ��ʱ�ӷ��ز�֤���в�ѯ  
				}
				
			}
			else
			{
				
				resultMap =registerMap;
				resultMap.put("sure_reg_code",useMap.get("reg_code").toString());
				resultMap.put("sure_reg_date",registerMap.get("REG_DATE").toString());
				resultMap.put("sure_recorder",registerMap.get("RECORDER").toString());
				holderkey = "Register.getHolderInfoByRegId";
				cerkey="Register.getRightCertificateNoFromBkOwnership";
			}
			//��ȡ��һ�ε�Ѻ�ĵ�Ѻ��� �ǲ��˺� �ǲ�ʱ��
			String bus_id = getBusMainMapByRegId(useMap).get("BUS_ID").toString();
			String orig_mort_reg_code  = getOrigMortRegCode(bus_id);
			//��һ�ε�Ѻ���
			resultMap.put("mo_reg_code", orig_mort_reg_code);
			String tmp_reg_code = useMap.get("reg_code").toString();
			String tmp_reg_unit_code = useMap.get("reg_unit_code").toString();
			Reg_Mortgage reg_Mortgage = getRegMortgageByRegUnitCodeAndRegCode(tmp_reg_unit_code,orig_mort_reg_code);
			
			//��һ�εǼ�ʱ��͵ǲ���
			resultMap.put("reg_date", reg_Mortgage.getReg_date());
			resultMap.put("recorder", reg_Mortgage.getRecorder());
			
		}else{
			if(registerMap==null||registerMap.isEmpty()){
				resultMap = getBKInfoByRegId(getDataTableKeyByBusTypeID(typeid,""),null,useMap);
				if(resultMap!=null)
				{
					resultMap.put("reg_date"," ");
					resultMap.put("recorder"," ");
					holderkey = "Register.getAppAsHolderByRegId";										//�Ǽǲ�Ԥ��ʱ���������в�ѯ  
					cerkey = "Register.getRightCertificateNo";											//�Ǽǲ�Ԥ��ʱ�ӷ��ز�֤���в�ѯ  
				}
				
			}
			else
			{
				//�Ǽǲ���ѯʱ�еǼǲ��˺͵Ǽ�ʱ��
				//Map<String,Object> userInfoMap = getuserInfoByRegId(useMap);
				resultMap =registerMap;
				holderkey = "Register.getHolderInfoByRegId";
				cerkey="Register.getRightCertificateNoFromBkOwnership";
			}
			
		}
		if(typeid.equals(""))
		{
			//getHistoryHolderMapListByProcId(useMap);
		}
		List<Map<String, Object>> holderList = null;
		List<Map<String, Object>> cerList = null;
		String reg_unit_code = useMap.get("reg_unit_code").toString();
		//����ǵ�Ѻ�Ǽ�   ���ж������   ͨ���Ǽǵ�Ԫ��Ż�ȡ��Ȼ��Ϣ
		if(typeid.equals(WbfConstants.MORTGAGE_RIGHT)){
			
			
			//����ǲ���������Ϊ��   ��ӹ�����������  ������Ҳ��
			if(registerMap==null||registerMap.isEmpty()){
				List<Map<String,Object>> tmpholderList = houseDao.queryMapListByKey(holderkey, useMap);
				holderList = new ArrayList<Map<String,Object>>();
				//ɸѡ����ǰ�Ǽǵ�Ԫ��������
				for(Map<String,Object> tmpMap:tmpholderList){
					Object tmpRegUnitCodeObj = tmpMap.get("REG_UNIT_CODE");
					//ɸѡ����ѺȨ��
					Object tmpHolRelObj = tmpMap.get("HOL_REL");
					if(tmpHolRelObj!=null){
						if(tmpHolRelObj.toString().equals(WbfConstants.MORTGAGEE)){
							holderList.add(tmpMap);
							continue;
						}
					}
					//ɸѡ����Ѻ��
					if(tmpRegUnitCodeObj!=null ){
						if(tmpRegUnitCodeObj.toString().equals(reg_unit_code)){
							holderList.add(tmpMap);
						}
					}
					
				}
			}
			else{	//����Ѿ��ǲ�    ���Ȩ���˱��л�ȡ����
				holderList = holderDao.queryMapListByKey("Register.getHolder", "where RIGHT_REL_ID=(select RIGHT_REL_ID from BK_RIGHT_REL where reg_code=:reg_code and book_code=(select book_code from BK_BASEINFO where REG_UNIT_CODE=:reg_unit_code))", useMap);
			}
			//�����Ѻ���Ϊ��ʱ  ��ѵ�ǰ�ĵǼǱ����Ϊ��Ѻ���
			if(resultMap.get("mo_reg_code")==null){
				resultMap.put("mo_reg_code", useMap.get("reg_code").toString());
			}
			
			//��ȡ��Ѻ������
			String MORT_TYPE = resultMap.get("MORT_TYPE").toString();
			MORT_TYPE = FacadeFactory.getDictFacade().getDictItemNameByCodeAndTypeCode(MORT_TYPE, "007");
			resultMap.put("MORT_TYPE", MORT_TYPE);
			
			try {
				resultMap.put(
						"hisRegInfo",
						getHistoryRegInfoMapByRegUnitCodeAndBusClassId(
								reg_unit_code, WbfConstants.MORTGAGE_RIGHT));
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		else{
			
			holderList = houseDao.queryMapListByKey(holderkey, useMap);
			cerList = houseDao.queryMapListByKey(cerkey, useMap);
			
			resultMap.put("rightCertificateNo", cerList);							//���ز���Ϣ
			resultMap.put("historyOwnershipInfo",getHistoryRegInfoMapByRegUnitCodeAndBusClassId(reg_unit_code,bus_class_id));  //��������Ȩ��ʷ��Ϣ
		}
		Map<String,Object> busNameMap = getBusNameMapByRegCode(useMap);				//��ȡҵ����
		if(busNameMap!=null){
			resultMap.putAll(busNameMap);
		}
		
		 
		//��ȡȡ�÷�ʽ
		 Map get_mode_name_map = getGetModeNameMapByRegCode(useMap);
		 if(get_mode_name_map != null && !get_mode_name_map.isEmpty()){
			 resultMap.putAll(get_mode_name_map);
		 }else{
			 LogUtil.error("ȡ�÷�ʽ����Ϊ��");
		 }
		resultMap.put("holder", holderList);										//��ȡȨ������Ϣ
		return resultMap;
	}
	
	
	
	/**
	 * 
	 * getuseInfo:(�Ǽǲ�Ԥ��ʹ��Ȩ����). <br/>
	 * @author xuzz
	 * @param useMap
	 * @return
	 * @since JDK 1.6
	 */
	public Map<String,Object> getuseInfo(Map<String, Object> useMap)
	{
		String search_type = checkuserByRegId(useMap);							//search_type:�Ǽǲ�Ԥ��/�Ǽǲ���ѯ  reg_code:�ǼǱ��
		 Map<String, Object> resultMap = new HashMap<String, Object>();
		//��ȡʹ��Ȩ��Ϣ
		try {
			resultMap = dictItemDao.queryMapByKey("Register.getuserInfoByRegId", useMap);
			if(resultMap == null || resultMap.isEmpty()){
				throw new BusinessException("��ȡʹ��ȨȨ��Ϣ���� ʹ��Ȩ��ϢΪ��");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("��ȡʹ��Ȩ��Ϣ����"+e.getMessage());
		}
		//���õǲ�����  ���ǲ���  
		if(search_type.equals("�Ǽǲ�Ԥ��")){
			resultMap.put("regDate"," ");
			resultMap.put("recorder"," ");
			
		}else if(search_type.equals("�Ǽǲ���ѯ")){
			//�Ǽǲ���ѯʱ�еǼǲ���  �͵Ǽ�ʱ��
			Map<String,Object> userInfoMap = getuserInfoByRegId(useMap);
			if(userInfoMap!=null)
			{
				resultMap.put("regDate",userInfoMap.get("REG_DATE").toString());
				resultMap.put("recorder",userInfoMap.get("RECORDER").toString());
			}

			
		}
		
		resultMap.put("holder", getHolderInfoByRegId(useMap));		//��ȡȨ������Ϣ
		resultMap.put("rightCertificateNo", getCertificateNoByRegId(useMap));	//���ز���Ϣ
		resultMap.put("historyOwnershipInfo",getHistoryOwnershipInfo(useMap)); //��������Ȩ��ʷ��Ϣ
		return resultMap;
	}
	
	/**
	 * 
	 * getRegNaturalTypeMapByRegCode:(�жϵ�ǰ�Ǽǵ���Ȼ�����Ƿ��ݻ����ڵ�).
	 *
	 * @author Joyon
	 * @param paraMap  natural_type(�ڵ�/����)  id(�ڵ�ID/����ID) 
	 * @return
	 * @since JDK 1.6
	 */
	public Map<String,Object> getRegNaturalTypeMapByRegCode(Map<String,Object> paraMap){
		Map<String,Object> regUnitRelMap =  dictItemDao.queryMapByKey("Register.getBusRegunitrelMapByRegCode",paraMap);
		Map<String,Object> reusltMap = new HashMap<String,Object>();
		String natural_type = null;
		
		Object house_idObj = regUnitRelMap.get("HOUSE_ID");
		Object	parcel_idObj = regUnitRelMap.get("PARCEL_ID");
		//String house_id = regUnitRelMap.get("HOUSE_ID").toString();
		//String parcel_id = regUnitRelMap.get("PARCEL_ID").toString();
		if(house_idObj != null){
			natural_type = "����";
			reusltMap.put("id", house_idObj.toString());
		}
		if(parcel_idObj != null ){
			natural_type = "�ڵ�";
			reusltMap.put("id", parcel_idObj.toString());
		}
		
		reusltMap.put("natural_type", natural_type);
		
		return reusltMap;
	}
	/**********************************       ��Ȼ��Ϣ����  ����          *************************************/
	
	
/**********************************     Ԥ������    ���� *************************************/
	
	
	
/**********************************     �Ǽǲ����沿��          *************************************/
	
	
	
	/**********************************       ����Ȩ����            *************************************/
	
	
	/**
	 * 
			 * getOwnershipInfo:(��ȡ����Ȩ������Ϣ)
			 * ��Ҫ��ȡ������Ϣ����������Ϣ
			 * @author Joyon
			 * @param paraMap search_type:�Ǽǲ�Ԥ��/�Ǽǲ���ѯ  reg_code:�ǼǱ��
			 * @return
			 * @since JDK 1.6
			 */
			public  Map<String, Object> getOwnershipInfo( Map<String, Object> paraMap){
				if(reg_code == null || reg_code .equals("")){
					reg_code = paraMap.get("reg_code").toString();
				}
				String search_type = checkRegTypeByRegId(paraMap);							//search_type:�Ǽǲ�Ԥ��/�Ǽǲ���ѯ  reg_code:�ǼǱ��
				
				
				 Map<String, Object> resultMap = new HashMap<String, Object>();
				 
				 
				 
				
				 Map bus_onwership_map = null;
				//��ȡ����Ȩ��Ϣ
				try {
					bus_onwership_map = dictItemDao.queryMapByKey("Register.getOwnershipInfoByRegId", paraMap);
					if(bus_onwership_map == null || bus_onwership_map.isEmpty()){
						LogUtil.error("��ȡ����Ȩ��Ϣ���� ����Ȩ��ϢΪ��");
					}else{
						resultMap.putAll(bus_onwership_map);
					}
				} catch (Exception e) {
					e.printStackTrace();
					throw new BusinessException("��ȡ����Ȩ��Ϣ����"+e.getMessage());
				}
				
				//��ȡҵ������
				 Map bus_name_map = getBusNameMapByRegCode(paraMap);
				 if(bus_name_map != null && !bus_name_map.isEmpty()){
					 resultMap.putAll(bus_name_map);
				 }else{
					 LogUtil.error("ҵ����������Ϊ��");
				 }
				 
				//��ȡ��������
				 Map house_attr_name_map = getHouseAttrNameMapByRegCode(paraMap);
				 if(house_attr_name_map != null && !house_attr_name_map.isEmpty()){
					 resultMap.putAll(house_attr_name_map);
				 }else{
					 LogUtil.error("������������Ϊ��");
				 }
				 
				//��ȡȡ�÷�ʽ
				 Map get_mode_name_map = getGetModeNameMapByRegCode(paraMap);
				 if(get_mode_name_map != null && !get_mode_name_map.isEmpty()){
					 resultMap.putAll(get_mode_name_map);
				 }else{
					 LogUtil.error("ȡ�÷�ʽ����Ϊ��");
				 }
				 
				 
				 
				
				
				//��ӷ��ݱ��  �Լ���������
				Map<String,Object> houseMap =getHouseMapByRegCode(paraMap);
				String adv_house_code = null;
				String hoser_locaton = null;
				if(houseMap != null && !houseMap.isEmpty()){
					adv_house_code = houseMap.get("ADV_HOUSE_CODE").toString();
					hoser_locaton = houseMap.get("HOUSE_LOCATION").toString();
					resultMap.put("HOUSE_LOCATION", hoser_locaton);
					resultMap.put("ADV_HOUSE_CODE", adv_house_code);
				}
				
				//���õǲ�����  ���ǲ���  
				if(search_type.equals("�Ǽǲ�Ԥ��")){
					resultMap.put("REG_DATE"," ");
					resultMap.put("recorder"," ");
					Certificate certificate = certificateDao.get("where bus_id=(select bus_id from bus_main where reg_code=:reg_code)", paraMap);
					if(certificate!=null){
						resultMap.put("EXCURSUS", certificate.getExcursus());
					}
				}else if(search_type.equals("�Ǽǲ���ѯ")){
					//�Ǽǲ���ѯʱ�еǼǲ���  �͵Ǽ�ʱ��
					Map<String,Object> bkOSInfoMap = getBkOwnershipInfoByRegId(paraMap);
					resultMap.put("REG_DATE",bkOSInfoMap.get("REG_DATE").toString());
					resultMap.put("recorder",bkOSInfoMap.get("RECORDER").toString());
					resultMap.put("EXCURSUS", bkOSInfoMap.get("EXCURSUS").toString());
					
				}
				
				resultMap.put("holder", getHolderInfoByRegId(paraMap));		//��ȡȨ������Ϣ
				resultMap.put("rightCertificateNo", getCertificateNoByRegId(paraMap));	//���ز���Ϣ
				resultMap.put("historyOwnershipInfo",getHistoryOwnershipInfo(paraMap)); //��������Ȩ��ʷ��Ϣ
				return resultMap;
			}
			
			/**
			 * 
			 * getGetModeNameMapByRegCode:(���ȡ�÷�ʽ������).
			 *
			 * @author Joyon
			 * @param paraMap
			 * @return
			 * @since JDK 1.6
			 */
			private Map getGetModeNameMapByRegCode(Map<String, Object> paraMap) throws BusinessException{
				String search_type = checkRegTypeByRegId(paraMap);							//search_type:�Ǽǲ�Ԥ��/�Ǽǲ���ѯ  reg_code:�ǼǱ��
				String key = "Register.getDicCodeNameFromDicItm";
				String whereSql = "";
				//�Ǽǲ�Ԥ��ʱ�ӻ�����Ϣ�в�
				if(search_type.equals("�Ǽǲ�Ԥ��")){
					//ͨ���ǼǱ�Ŵ�����Ȩ�Ǽ���Ϣ��ȡ��
					whereSql="where DIC_ITEM_CODE=(select GET_MODE from BUS_OWNERSHIP where bus_id=(select bus_id from bus_main where reg_code=:reg_code))";
				}else{
					whereSql="where  DIC_ITEM_CODE=(select GET_MODE from BK_OWNERSHIP where reg_code=:reg_code)";
				}
				Map resultMap = new HashMap<String,Object>();
				Map tmpMap = certificateDao.queryMapByKey(key, whereSql, paraMap);
				if(tmpMap != null && !tmpMap.isEmpty()){
					
					Object tempObject = tmpMap.get("DIC_ITEM_VALUE");
					if(tempObject!=null){
						resultMap.put("GET_MODE_NAME",tempObject.toString());
					}
				}
				
				
				return resultMap;
			}
			
			/**
			 * 
			 * getHouseAttrNameMapByRegCode:(��÷������ʵ�����). 
			 *
			 * @author Joyon
			 * @param paraMap
			 * @return
			 * @since JDK 1.6
			 */
			private Map getHouseAttrNameMapByRegCode(Map<String, Object> paraMap) throws BusinessException{
				
				String search_type = checkRegTypeByRegId(paraMap);							//search_type:�Ǽǲ�Ԥ��/�Ǽǲ���ѯ  reg_code:�ǼǱ��
				String key = "Register.getDicCodeNameFromDicItm";
				String whereSql = "";
				//�Ǽǲ�Ԥ��ʱ�ӻ�����Ϣ�в�
				if(search_type.equals("�Ǽǲ�Ԥ��")){
					//ͨ���ǼǱ�Ŵ�����Ȩ�Ǽ���Ϣ��ȡ��
					whereSql=" where DIC_ITEM_CODE=(select HOUSE_ATTR from BUS_OWNERSHIP where bus_id=(select bus_id from bus_main where reg_code=:reg_code))";
				}else{
					whereSql=" where  DIC_ITEM_CODE=(select HOUSE_ATTR from BK_OWNERSHIP where reg_code=:reg_code)";
				}
				
				Map resultMap = new HashMap<String,Object>();
				
				Map tmpMap = certificateDao.queryMapByKey(key, whereSql, paraMap);
				if(tmpMap!=null && !tmpMap.isEmpty()){
					Object tempObject =tmpMap.get("DIC_ITEM_VALUE");
					if(tempObject!=null){
						resultMap.put("HOUSE_ATTR_NAME",tempObject.toString());
					}
				}
				
				
				return resultMap;
			}
			/**
			 * 
			 * getHouseMapByRegCode:(ͨ���ǼǱ�Ż�ȡ���ݱ���Ϣ).
			 *
			 * @author Joyon
			 * @param paraMap
			 * @return
			 * @since JDK 1.6
			 */
			public Map<String,Object> getHouseMapByRegCode(Map<String,Object> paraMap){
				
				
				return  houseDao.queryMapByKey("Register.getHouseMapByRegCode", paraMap);
			}
			
			/**
			 * 
			 * getRegPreAplPerInfo:��ȡ��������Ϣͨ���ǼǱ��
			 *
			 * @author Joyon
			 * @param �ǼǱ��
			 * @return  
			 * @since JDK 1.6
			 */
			public List<Map<String, Object>> getAplPerInfoByRegId(Map<String, Object> paraMap){
				
				List<Map<String, Object>> resultList = null;
				try {
					resultList = dictItemDao.queryMapListByKey("Register.getAplPerInfoByRegId", paraMap);
					if(resultList == null || resultList.isEmpty()){

						LogUtil.error("��������ϢΪ��!");
					}
				} catch (Exception e) {
					throw new BusinessException("��ȡ��������Ϣ����"+e.getMessage());
				}
				
				return resultList;
			}
			
			/**
			 * 
			 * getHistoryOwnershipInfo:(��ȡ��ʷҵ��Ϊ����Ȩ���ֵ���Ϣ). 
			 *
			 * @author Joyon
			 * @param paraMap  paramMap ��put  reg_code  �ǼǱ��
			 * @return
			 * @since JDK 1.6
			 */
			public List<Map<String, Object>> getHistoryOwnershipInfo(Map<String,Object> paraMap) throws BusinessException{
				
				//��ȡ��ʷ  ҵ������   ҵ����
				List<Map<String, Object>> hisBusIdList = null;
				String typeId = getBusTypeParentIdByRegId(paraMap);				//��ҵ������  ����ɸѡ��ǰ�Ǽǵ�Ԫ  ��ǰ��ҵ���µ���ʷ��¼
				
				 
			//1����ȡ��ǰ�Ǽǵ�Ԫ   ��Ӧ�Ǽǲ�������      
				 //��ȡ�Ǽǵ�Ԫ����
				 String reg_unit_type = null;
				 String reg_unit_code = null;
				 Map<String,Object> regUnitRelMap = getRegUnitRelMapByRegId(paraMap);
				 if(regUnitRelMap!=null){
					 reg_unit_type = regUnitRelMap.get("REG_UNIT_TYPE").toString();
					 reg_unit_code = regUnitRelMap.get("reg_unit_code").toString();
					 paraMap.put("reg_unit_code", reg_unit_code);
				 }else{
					 LogUtil.error("RegisterFacade.getHistoryOwnershipInfo ��ȡ�Ǽǵ�Ԫ���������� ����");
				 }
				 //���ݵǼǵ�Ԫ����  ������Ȩ��ʹ��Ȩ�л�ȡ��ʷҵ����Ϣ
				 List<Map<String,Object>> hisRegUnitBkInfoMapList = null;								//�ӵǼǲ���ȡ�� ��list Mapֵ 
				 String hisRegUnitBkInfoKey = "Register.getBkOwnership";								//key Ĭ��ֵ   Ĭ�ϴ�����Ȩ��ȡ
				 if(reg_unit_type.equals(WbfConstants.HOUSE)){
				 }else{
					 hisRegUnitBkInfoKey = "Register.getBkuserInfoByRegId";
				 }
				 //����ʷҵ������ ��ֵ   ���������ѭ���Ա� ��
				 hisRegUnitBkInfoMapList = reg_UserightDao.queryMapListByKey(hisRegUnitBkInfoKey,"where book_code=(select book_code from bk_baseinfo where reg_unit_code=:reg_unit_code)",paraMap);
				
			//2��ɸѡ����ǰ�Ǽǵ�Ԫ   ����Ȩ ����   ʹ��Ȩ����ʷҵ�������     
				 
				//��ȡ��ǰ�Ǽǵ�Ԫ���е���ʷ�Ǽ�ҵ����Map list   �ӵǼǵ�Ԫ�������л�ȡ          �Ա� �ں͵Ǽǲ������ݶԱ�       ��Ҫ��ɸѡ����ǰ�Ǽǲ�Ԥ��ʱ��ҵ��
				 try {
					 hisBusIdList = houseDao.queryMapListByKey("Register.getHisBusIdByRegId", paraMap);
				} catch (Exception e) {
					e.printStackTrace();
					throw new BusinessException("��ȡҵ����ʷ�ǼǱ�ų���"+e.getMessage());
				}
				 //��ʷ�ǼǱ��Ϊnullʱ ���ý����κβ���ֱ�ӷ���null
				 if(hisBusIdList == null || hisBusIdList.isEmpty()){
					 return null;
				 }
				 
				 //ѭ��ɸѡ  �Ǽǲ� �д��ڵ�ҵ��    �ͱ��ΰ����ҵ��
				 List<Map<String, Object>> hisOwnershipRegIdList = new ArrayList<Map<String, Object>>();	//�����洢���� ��������Ȩ ҵ�����͵� �ǼǱ��
				 Map<String, Object> tempBusMainMap = null;
				 Map<String,Object> tempParaBusIdMap = null;												//���������ʷҵ����Ϣ �Ĳ��� 
				 String tempBusParentType=null;																//��ʷ��ҵ������
				 try {
					 for(Map<String, Object> tempMap:hisBusIdList){
						 String his_reg_code =tempMap.get("REG_CODE").toString();
						 //����Ǽǲ��еǼǱ��   ��   �Ǽǵ�Ԫ�����������     ����    �ǵ�ǰҵ��ĵǼǱ��  ����ӵ���ʷ�Ǽ���Ϣ��  
						 for(Map tmpHisRegUnitBkInfoMap:hisRegUnitBkInfoMapList){
							 if(tmpHisRegUnitBkInfoMap.get("REG_CODE").toString().equals(his_reg_code) || paraMap.get("reg_code").toString().equals(his_reg_code)){
								 hisOwnershipRegIdList.add(tempMap);	 
								 break;
							 }
							 
						 }
					 }
				 } catch (Exception e) {
						LogUtil.error("��ȡҵ��Ǽ�����"+e.getMessage());
						throw new BusinessException("��ȡҵ��Ǽ�����"+e.getMessage());
				 }
				 
			//3����ʷҵ������ ���   �ǼǱ�� ��ȡ����Ȩ��  �Ǽ�ʱ��  �Ǽ�����  
				 List<Map<String, Object>> hisOwnershipList = new ArrayList<Map<String, Object>>();
				 List<Map<String, Object>> holderList = null;
				 Map<String, Object> resultMap = null;
				 String busName = null;																		//ҵ��������
				 for(Map<String, Object> tempRegIdMap:hisOwnershipRegIdList){
					 resultMap = new HashMap<String, Object>();
					
					 
					 Map<String, Object>  tempParaMap = new HashMap<String, Object> ();
					 String reg_code = tempRegIdMap.get("reg_code").toString();
					 tempParaMap.put("reg_code",reg_code);					//sql��ѯ����   reg_code�ǼǱ��
					 
					 holderList = getHolderInfoByRegId(tempParaMap);			//��ȡ����Ȩ������      ���Ϊ�����ʾ��  �Ǽǲ�Ԥ������    ��Ҫ���������л�ȡ
					 
					 if(holderList == null || holderList.isEmpty()){
						 try {
							holderList = houseDao.queryMapListByKey(
									"Register.getAppAsHolderByRegId", tempParaMap);
						} catch (Exception e) {
							e.printStackTrace();
							throw new BusinessException("������תΪȨ����ʧ��"+e.getMessage());
						}
					 }
					 
					 busName = getRegTypeByRegCode(tempParaMap);
					 String typeid="";
					 typeid =getBusTypeParentIdByRegId(paraMap);
					 String key = getDataTableKeyByBusTypeID(typeid,"book");
					 Map<String, Object> registerMap = getBKInfoByRegId(key," where REG_CODE=:reg_code ",tempParaMap);
					 String regDate = null;											//�Ǽ�ʱ���ַ���
					 //�ǲ���ϢΪ��   �Ǽǲ�Ԥ��  ʱ����Ϊ��  
					 Date date =null;												//��������
					 if(registerMap != null && !registerMap.isEmpty()){
						 
						 date = (Date)registerMap.get("REG_DATE");
						 regDate = DateUtils.format(date, "yyyy-MM-dd HH:mm:ss");
					 }
					 
					 resultMap.put("holder", holderList);							//Ȩ���˸�ֵ
					 resultMap.put("busName", busName);								//ҵ����������ֵ
					 resultMap.put("regDate", regDate);								//�Ǽ�ʱ�丳ֵ
					 resultMap.put("regId", reg_code);	
					 resultMap.put("date", date);									//��������
					 //�ǼǱ��
					 hisOwnershipList.add(resultMap);								//��ӵ���ʷȨ������Ϣ��
				 }
				 
				 Map<String,Object> tmpMap = null;
				 Map<String,Object> maxMap = null;									//���Ȱ��ҵ��
				 int tmpLength = hisOwnershipList.size();							//��ʷ���ݳ���
			//4��ѭ����ʱ������     ʱ��Ϊ�յ�������
				 for(int i=0;i<tmpLength;i++){
					 maxMap =hisOwnershipList.get(i);								//ÿ��ѭ��ʱ  �ѵ�ǰ��ֵ��Ϊ���ֵ   ɸѡ�������ǰindex�µ�ֵ��Ϊ���ֵ
					 //�����ķ��ڵ�һ��
					 for(int j=i;j<tmpLength;j++){
						// maxMap = hisOwnershipList.get(i);
						 if(hisOwnershipList.get(i).get("date")==null){
							 //������һ�� ���ƶ�
							 if(i+1 == tmpLength){
								 continue;
							 }
							 if(i!=j){
								 maxMap = hisOwnershipList.get(j);
								 hisOwnershipList.set(j,hisOwnershipList.get(i));
								 hisOwnershipList.set(i,maxMap); 
							 }if(i==j){
								 maxMap = hisOwnershipList.get(i+1);
								 hisOwnershipList.set(i+1,hisOwnershipList.get(i));
								 hisOwnershipList.set(i,maxMap); 
							 }
							 
						 }else{
							 if(i+1 == tmpLength){
								 break;
							 }
							 if(i==j){
								 if(hisOwnershipList.get(i+1).get("date") == null){
									 continue;
								 }
								 
								 if(((Date)maxMap.get("date")).before(((Date)hisOwnershipList.get(i+1).get("date")))){
									 
								 }else{
									 maxMap = hisOwnershipList.get(i+1);
									 hisOwnershipList.set(i+1,hisOwnershipList.get(i));
									 hisOwnershipList.set(i,maxMap); 
								 }
							 }
							 if(i!=j){
								 if(hisOwnershipList.get(j).get("date") == null){
									 continue;
								 }
								 Date c_tmp_date = (Date)hisOwnershipList.get(j).get("date");
								 Date max_tmp_date = (Date)maxMap.get("date");
								 if((max_tmp_date).before(c_tmp_date)){
									 
								 }else{
									 maxMap = hisOwnershipList.get(j);
									 hisOwnershipList.set(j,hisOwnershipList.get(i));
									 hisOwnershipList.set(i,maxMap); 
								 }
								 
							 }
						 }
					 }
					 
				 }
				 
				return hisOwnershipList;
			}
			
			/**
			 * 
			 * getHistoryRegInfoMapByRegUnitCodeAndBusClassId:(���Ǽǵ�Ԫ��Ż��ҵ��ID(�˴�ҵ��)��ȡȡ��ʷҵ�����Ϣ). 
			 *
			 * @author Joyon
			 * @param reg_unit_code(�Ǽǵ�Ԫ���) bus_class_id(�˴�ҵ����ֵ���ֵ)
			 * @return
			 * @since JDK 1.6
			 */
			public List<Map<String, Object>> getHistoryRegInfoMapByRegUnitCodeAndBusClassId(String reg_unit_code,String bus_class_id) throws BusinessException{
				
				//��ȡ��ʷ  ҵ������   ҵ����
				List<Map<String, Object>> hisBusIdList = null;
				String typeId = bus_class_id;				//��ҵ������  ����ɸѡ��ǰ�Ǽǵ�Ԫ  ��ǰ��ҵ���µ���ʷ��¼
				
				Map<String,Object> paraMap = new HashMap<String,Object>();
				paraMap.put("reg_unit_code", reg_unit_code);
				//��ȡ��ǰ����ID�µ����е���ʷ�Ǽ�ҵ����Map list
				List<Reg_relationship> hisRegRelationShipList = null;
				
				 try {
					 hisRegRelationShipList = reg_relationshipDao.getAll(" where reg_unit_code=:reg_unit_code",paraMap);
					 //hisBusIdList = houseDao.queryMapListByKey("Register.getHisBusIdByRegId", paraMap);
				} catch (Exception e) {
					e.printStackTrace();
					throw new BusinessException("��ȡҵ����ʷ�ǼǱ�ų���"+e.getMessage());
				}
				 //��ʷ�ǼǱ��Ϊnullʱ ���ý����κβ���ֱ�ӷ���null
				 if(hisRegRelationShipList == null || hisRegRelationShipList.isEmpty()){
					 return null;
				 }
				 
				 //ѭ��ɸѡҵ������Ϊ   ��ǰҵ�� �ĵǼǱ��  
				 List<Map<String, Object>> hisRegIdList = new ArrayList<Map<String, Object>>();				//�����洢���� ��ǰ ҵ�����͵� �ǼǱ��
				 //Map<String, Object> tempBusMainMap = null;													//�洢��ʷҵ����ϢMap
				// Map<String,Object> tempParaBusIdMap = null;												//���������ʷҵ����Ϣ �Ĳ��� 
				Map<String,Object> tempParaMap =null;								//��ʱsql�Ĳ���
				 String tempBusParentTypeID=null;																//��ʷ��ҵ�������ֵ���code
				 try {
					 
					 for(Reg_relationship tempReg_relationship:hisRegRelationShipList){
						 tempParaMap = new HashMap<String,Object>();
						 String reg_code = tempReg_relationship.getReg_code();
						 tempParaMap.put("reg_code", reg_code);
						 //�˴� ҵ��Ǽ�����    ����ɸѡ��ʷҵ��
						 tempBusParentTypeID =getBusTypeParentIdByRegId(tempParaMap);
						 //ɸѡ����ǰҵ�����͵���ʷ��¼    ���ҵ�����͵� �����Ͳ��Ƿ�������Ȩ continue   �Ƿ�������Ȩ ��ѸõǼǱ�ż��뵽hisOwnershipRegIdList
						 if(!typeId.equals(tempBusParentTypeID)){
							 continue;
						 }
							 //ͨ��ҵ���Ż�ȡҵ��ǼǱ��
						 hisRegIdList.add(tempParaMap);				
						
					 }
				 } catch (Exception e) {
						LogUtil.error("��ȡҵ��Ǽ�����"+e.getMessage());
						//throw new BusinessException("��ȡҵ��Ǽ�����"+e.getMessage());
				 }
				 
				 //ͨ�� ��������Ȩҵ�� �ǼǱ�� ��ȡ����Ȩ��  �Ǽ�ʱ��  �Ǽ�����
				 List<Map<String, Object>> hisRegInfoList = new ArrayList<Map<String, Object>>();			//��ʷҵ��Ǽ���Ϣ
				 List<Map<String, Object>> holderList = null;
				 Map<String, Object> resultMap = null;
				 String busName = null;																		//ҵ��������
				 for(Map<String, Object> tempRegIdMap:hisRegIdList){
					 resultMap = new HashMap<String, Object>();
					 
					
					 
					tempParaMap = new HashMap<String, Object> ();
					 String reg_code = tempRegIdMap.get("reg_code").toString();
					 tempParaMap.put("reg_code",reg_code);					//sql��ѯ����   reg_code�ǼǱ��
					 
					 holderList = getHolderInfoByRegId(tempParaMap);			//��ȡ����Ȩ������      ���Ϊ�����ʾ��  �Ǽǲ�Ԥ������    ��Ҫ���������л�ȡ
					 
					 if(holderList == null || holderList.isEmpty()){
						 try {
							holderList = houseDao.queryMapListByKey(
									"Register.getAppAsHolderByRegId", tempParaMap);
						} catch (Exception e) {
							e.printStackTrace();
							throw new BusinessException("������תΪȨ����ʧ��"+e.getMessage());
						}
					 }
					 busName = getRegTypeByRegCode(tempParaMap);
					 //String typeid="";
					// typeid =getBusTypeParentIdByRegId(tempParaMap);
					 String key = getDataTableKeyByBusTypeID(typeId,"book");
					 String whereSql =" where REG_CODE=:reg_code ";
					 if(typeId.equals(WbfConstants.MORTGAGE_RIGHT)){
						 whereSql="";
						 tempParaMap.put("reg_unit_code", reg_unit_code);
					 }
					 Map<String, Object> registerMap = getBKInfoByRegId(key,whereSql,tempParaMap);
					 String regDate = null;
					 //�ǲ���ϢΪ��   �Ǽǲ�Ԥ��  ʱ����Ϊ��  
					 Date date =null;
					 if(registerMap != null && !registerMap.isEmpty()){
						 
						 date = (Date)registerMap.get("REG_DATE");
						 regDate = DateUtils.format(date, "yyyy-MM-dd HH:mm:ss");
						 
						 //����ǵ�ѺȨ�Ǽ� ��ȡծȨ��
						 if(typeId.equals(WbfConstants.MORTGAGE_RIGHT)){
							 Object tmpBorrower = registerMap.get("BORROWER");
							 if(tmpBorrower!=null){
								 resultMap.put("borrower", tmpBorrower.toString());
							 }else{
								 resultMap.put("borrower", " ");
							 }
						 }
						 if(typeId.equals(WbfConstants.REG_ATTACH))
						 {
							 resultMap.put("dis_off", registerMap.get("DIS_OFF").toString());
							 if(registerMap.get("EFFECTIVE").toString().equals(WbfConstants.EFFECTIVE))
							 {
								 resultMap.put("state", "��Ч");
							 }
							 else
							 {
								 resultMap.put("state", "��Ч");
							 }
						 }
					 }else{
						 
						 //����ǵ�ѺȨ�Ǽ� ��ȡծȨ��
						 if(typeId.equals(WbfConstants.MORTGAGE_RIGHT)){
							 Mortgage mortgage = mortgageDao.get("where bus_id =(select bus_id from bus_main where reg_code=:reg_code)", tempParaMap);
							 if(mortgage!=null){
								 String borrower = mortgage.getBorrower();
								 resultMap.put("borrower", borrower);
							 }
							
						 }
						 else
						 {
							 key = getDataTableKeyByBusTypeID(typeId,"");
							 registerMap = getBKInfoByRegId(key,"",tempParaMap);
							 if(typeId.equals(WbfConstants.REG_ATTACH))
							 {
								 resultMap.put("dis_off", registerMap.get("DIS_OFF").toString());
								 resultMap.put("state", "��Ч");
							 }
						 }
					 }
					 
					 
					 resultMap.put("holder", holderList);							//Ȩ���˸�ֵ
					 resultMap.put("busName", busName);								//ҵ����������ֵ
					 resultMap.put("regDate", regDate);								//�Ǽ�ʱ�丳ֵ
					 resultMap.put("regId", reg_code);	
					 resultMap.put("date", date);									//��������
					 //�ǼǱ��
					 hisRegInfoList.add(resultMap);								//��ӵ���ʷȨ������Ϣ��
				 }
				 
				 Map<String,Object> tmpMap = null;
				 Map<String,Object> maxMap = null;									//���Ȱ��ҵ��
				 int tmpLength = hisRegInfoList.size();							//��ʷ���ݳ���
				 
				 //ѭ����ʱ������     ʱ��Ϊ�յ�������
				 for(int i=0;i<tmpLength;i++){
					 maxMap =hisRegInfoList.get(i);								//ÿ��ѭ��ʱ  �ѵ�ǰ��ֵ��Ϊ���ֵ   ɸѡ�������ǰindex�µ�ֵ��Ϊ���ֵ
					 //�����ķ��ڵ�һ��
					 for(int j=i;j<tmpLength;j++){
						// maxMap = hisOwnershipList.get(i);
						 if(hisRegInfoList.get(i).get("date")==null){
							 //������һ�� ���ƶ�
							 if(i+1 == tmpLength){
								 continue;
							 }
							 if(i!=j){
								 maxMap = hisRegInfoList.get(j);
								 hisRegInfoList.set(j,hisRegInfoList.get(i));
								 hisRegInfoList.set(i,maxMap); 
							 }if(i==j){
								 maxMap = hisRegInfoList.get(i+1);
								 hisRegInfoList.set(i+1,hisRegInfoList.get(i));
								 hisRegInfoList.set(i,maxMap); 
							 }
							 
						 }else{
							 if(i+1 == tmpLength){
								 break;
							 }
							 if(i==j){
								 if(hisRegInfoList.get(i+1).get("date") == null){
									 continue;
								 }
								 
								 if(((Date)maxMap.get("date")).before(((Date)hisRegInfoList.get(i+1).get("date")))){
									 
								 }else{
									 maxMap = hisRegInfoList.get(i+1);
									 hisRegInfoList.set(i+1,hisRegInfoList.get(i));
									 hisRegInfoList.set(i,maxMap); 
								 }
							 }
							 if(i!=j){
								 if(hisRegInfoList.get(j).get("date") == null){
									 continue;
								 }
								 Date c_tmp_date = (Date)hisRegInfoList.get(j).get("date");
								 Date max_tmp_date = (Date)maxMap.get("date");
								 if((max_tmp_date).before(c_tmp_date)){
									 
								 }else{
									 maxMap = hisRegInfoList.get(j);
									 hisRegInfoList.set(j,hisRegInfoList.get(i));
									 hisRegInfoList.set(i,maxMap); 
								 }
								 
							 }
						 }
					 }
					 
				 }
				 
				return hisRegInfoList;
			}
			
			/**
			 * 
			 * getDataTableKeyByBusTypeID:(��ȡҵ�����͸���ID��ͨ������ID����ѯ��Ӧ�ĵǼǲ�). <br/>
			 * @author xuzz
			 * @param paraMap
			 * @return
			 * @since JDK 1.6
			 */
			public String getDataTableKeyByBusTypeID(String typeid,String booktype)
			{
				//��Ѻ
				if(typeid.equals(WbfConstants.MORTGAGE_RIGHT))
				{
					if(booktype.equals("book"))
					{
						return "Register.getBkMortgageInfoByRegUnitCode";
					}
					else
					{
						return "Register.getMortgageInfoByRegId";
					}
					
				}
				//��������Ȩ
				else if(typeid.equals(WbfConstants.HOUSE_ONWERSHIP))
				{
					if(booktype.equals("book"))
					{
						return "Register.getBkOwnershipInfoByRegId";
					}
					else
					{
						return "Register.getOwnershipInfoByRegId";
					}
					
				}
				//����ʹ��Ȩ
				else if(typeid.equals(WbfConstants.LAND_USERIGHT))
				{
					if(booktype.equals("book"))
					{
						return "Register.getBkuserInfoByRegId";
					}
					else
					{
						return "Register.getuserInfoByRegId";
					}
					
				}
				//����Ȩ
				else if(typeid.equals("104"))
				{
					if(booktype.equals("book"))
					{
						return "";
					}
					else
					{
						return "";
					}
				}
				//Ԥ��Ǽ�
				else if(typeid.equals("105"))
				{
					if(booktype.equals("book"))
					{
						return "";
					}
					else
					{
						return "";
					}
				}
				//����Ǽ�
				else if(typeid.equals("106"))
				{
					if(booktype.equals("book"))
					{
						return "Register.getBkDemurrerInfoByRegId";
					}
					else
					{
						return "Register.getDemurrerInfoByRegId";
					}
				}
				//���Ǽ�
				else if(typeid.equals(WbfConstants.ATTACH_DISTRAIN))
				{
					if(booktype.equals("book"))
					{
						return "Register.getBkDistrainInfoByRegId";
					}
					else
					{
						return "Register.getattachInfoByRegId";
					}
				}
				//���ز�ע��
				else if(typeid.equals(WbfConstants.REALESTATE_CAN))
				{
					Map<String,Object> paraMap = new HashMap<String,Object>();
					paraMap.put("reg_code", reg_code);
					String unit_type =getRegUnitRelMapByRegId(paraMap).get("REG_UNIT_TYPE").toString();// reg_relationshipDao.get("where bus_id =(select bus_id from bus_main where reg_code=:reg_code)", paraMap).getReg_unit_type();
					if(booktype.equals("book"))
					{
					if(unit_type.equals(WbfConstants.REG_UNIT_HOUSE)){
							return "Register.getBkOwnershipInfoByRegId";
						}
					else if(unit_type.equals(WbfConstants.REG_UNIT_PARCEL)){
						return "Register.getBkuserInfoByRegId";
					}
					}
					else
					{
						if(unit_type.equals(WbfConstants.REG_UNIT_HOUSE)){
							return "Register.getOwnershipInfoByRegId";
						}
						else if(unit_type.equals(WbfConstants.REG_UNIT_PARCEL)){
							return "Register.getuserInfoByRegId";
						}
					}
				}
				//������׼
				else if(typeid.equals(WbfConstants.REVOKEAPPROVAL1))
				{
					Map<String,Object> paraMap = new HashMap<String,Object>();
					paraMap.put("reg_code", reg_code);
					String unit_type =getRegUnitRelMapByRegId(paraMap).get("REG_UNIT_TYPE").toString();// reg_relationshipDao.get("where bus_id =(select bus_id from bus_main where reg_code=:reg_code)", paraMap).getReg_unit_type();
					if(booktype.equals("book"))
					{
					if(unit_type.equals(WbfConstants.REG_UNIT_HOUSE)){
							return "Register.getBkOwnershipInfoByRegId";
						}
					else if(unit_type.equals(WbfConstants.REG_UNIT_PARCEL)){
						return "Register.getBkuserInfoByRegId";
					}
					}
					else
					{
						if(unit_type.equals(WbfConstants.REG_UNIT_HOUSE)){
							return "Register.getOwnershipInfoByRegId";
						}
						else if(unit_type.equals(WbfConstants.REG_UNIT_PARCEL)){
							return "Register.getuserInfoByRegId";
						}
					}
				}
				//�����Ǽ�   �����ظ����Ǽ�  �ͷ��ݵǼ�
				else if(typeid.equals(WbfConstants.CORRECTION))
				{
					Map<String,Object> paraMap = new HashMap<String,Object>();
					paraMap.put("reg_code", reg_code);
					String unit_type =getRegUnitRelMapByRegId(paraMap).get("REG_UNIT_TYPE").toString();// reg_relationshipDao.get("where bus_id =(select bus_id from bus_main where reg_code=:reg_code)", paraMap).getReg_unit_type();
					if(booktype.equals("book"))
					{
						if(unit_type.equals(WbfConstants.REG_UNIT_HOUSE)){
							return "Register.getBkOwnershipInfoByRegId";
						}else if(unit_type.equals(WbfConstants.REG_UNIT_PARCEL)){
							return "Register.getBkuserInfoByRegId";
						}
						
					}
					else
					{
						if(unit_type.equals(WbfConstants.REG_UNIT_HOUSE)){
							return "Register.getOwnershipInfoByRegId";
						}else if(unit_type.equals(WbfConstants.REG_UNIT_PARCEL)){
							return "Register.getuserInfoByRegId";
						}
					}
				}
				//�������Ǽ�   �����صǼ�  �ͷ��ݵǼ�
				else if(typeid.equals(WbfConstants.REISSUE))
				{
					Map<String,Object> paraMap = new HashMap<String,Object>();
					paraMap.put("reg_code", reg_code);
					String unit_type =getRegUnitRelMapByRegId(paraMap).get("REG_UNIT_TYPE").toString();// reg_relationshipDao.get("where bus_id =(select bus_id from bus_main where reg_code=:reg_code)", paraMap).getReg_unit_type();
					if(booktype.equals("book"))
					{
						if(unit_type.equals(WbfConstants.REG_UNIT_HOUSE)){
							return "Register.getBkOwnershipInfoByRegId";
						}else if(unit_type.equals(WbfConstants.REG_UNIT_PARCEL)){
							return "Register.getBkuserInfoByRegId";
						}
						
					}
					else
					{
						if(unit_type.equals(WbfConstants.REG_UNIT_HOUSE)){
							return "Register.getOwnershipInfoByRegId";
						}else if(unit_type.equals(WbfConstants.REG_UNIT_PARCEL)){
							return "Register.getuserInfoByRegId";
						}
					}
				}
				return typeid;
			}
			
			
			/**
			 * 
			 * getHolderInfo:(��ȡȨ������Ϣ). 
			 *
			 * @author Joyon
			 * @param paraMap reg_code �ǼǱ��
			 * @return
			 * @since JDK 1.6
			 */
			public List<Map<String, Object>> getHolderInfoByRegId(Map paraMap){
				String search_type = checkRegTypeByRegId(paraMap);		
				List<Map<String, Object>> resultList = null;
				String key = null;
				if(search_type.equals("�Ǽǲ���ѯ")){
					key = "Register.getHolderInfoByRegId";										//�Ǽǲ���ѯ��Ȩ�����в�ѯȨ����
				}else if(search_type.equals("�Ǽǲ�Ԥ��")){
					key = "Register.getAppAsHolderByRegId";										//�Ǽǲ�Ԥ��ʱ���������в�ѯ  
				}
				
				try {
				 resultList = houseDao.queryMapListByKey(key, paraMap);
				} catch (Exception e) {
					e.printStackTrace();
					throw new BusinessException("ͨ���ǼǱ�Ż�ȡȨ���˳���"+e.getMessage());
				}
				return resultList;
			}
			
			/**
			 * ��������ʵ��ID ��ȡ��ǰ ҵ��ǰһ��ҵ���Ȩ������Ϣ
			 */
			public List<Map<String,Object>> getHistoryHolderMapListByProcId(String proc_id) throws BusinessException{
				Map<String,Object> paraMap = new HashMap<String,Object>();
				paraMap.put("proc_id", proc_id);
				return houseDao.queryMapListByKey("Register.getHistoryHolderMapListByProcId", paraMap);
			}
			
			/**
			 * 
			 * getEffictiveHolderMapListByRegUnitCode:(��ȡ��ǰ�Ǽǵ�Ԫ��Ч��Ȩ����).
			 *
			 * @author Joyon
			 * @param reg_unit_code  hol_rel_code(Ȩ���˹�ϵ����code   Ϊ""ʱ����ѯ����Ȩ����   )
			 * @return
			 * @since JDK 1.6
			 */
			public List<Map<String,Object>> getEffictiveHolderMapListByRegUnitCode(String reg_unit_code,String hol_rel_code){
				Map<String,Object> paraMap = new HashMap<String,Object>();
				paraMap.put("reg_unit_code", reg_unit_code);
				//��Ѻʱ���������� ����ӵǼǲ��в�ѯ����Ч�ĵǼǱ��   ��ͨ���ǼǱ�Ŵ�Ȩ���˼����в�ѯ����
				//String whereSql ="where RIGHT_REL_ID =(select RIGHT_REL_ID from BK_RIGHT_REL where BOOK_CODE=(select BOOK_CODE from BK_BASEINFO where REG_UNIT_CODE=:reg_unit_code) and EFFECTIVE='"+EFFECTIVE+"')";
				String whereSql="";
				String reg_unit_type = null;
				String hol_rel_str="";
				if(hol_rel_code.equals("")){
					
				}else{
					hol_rel_str ="HOL_REL='"+hol_rel_code+"' and ";
				}
				try {
					reg_unit_type = reg_baseInfoDao.get("where reg_unit_code=:reg_unit_code", paraMap).getReg_unit_type();
				} catch (Exception e) {
					LogUtil.error("��ȡ�Ǽǵ�Ԫ���ͳ���"+e.getMessage());
				}
				whereSql = "where "+hol_rel_str+" RIGHT_REL_ID =(select RIGHT_REL_ID from BK_RIGHT_REL where book_code=(select book_code from BK_BASEINFO where REG_UNIT_CODE=:reg_unit_code) and EFFECTIVE='"+WbfConstants.EFFECTIVE+"' )";		
				return houseDao.queryMapListByKey("Register.getHolder",whereSql, paraMap);
			}
			
			
			/**
			 * 
			 * getRegTypeByRegId:(ͨ���ǼǱ�� ��ȡ�Ǽ�����).
			 *
			 * @author Joyon
			 * @param paraMap
			 * @return
			 * @since JDK 1.6
			 */
			public String getRegTypeByRegCode(Map<String, Object> paraMap){
				Map<String, Object> regTypeMap = null;
				try {
					regTypeMap= houseDao.queryMapByKey("Register.getRegTypeByRegId", paraMap);
					if(regTypeMap == null || regTypeMap.isEmpty()){
						LogUtil.error("��ȡ�Ǽ����ͳ���");
						throw new BusinessException("��ȡ�Ǽ����ͳ���");
					}
				} catch (Exception e) {
					e.printStackTrace();
					throw new BusinessException("��ȡ�Ǽ����ͳ���"+e.getMessage());
				}
				
				return regTypeMap.get("BUS_NAME").toString();
			}
			
			/**
			 * 
			 * getBusTypeParentNameByRegId:(ͨ���ǼǱ�Ż�ȡ  ҵ�����͵ĸ�����). 
			 *
			 * @author Joyon
			 * @param paraMap
			 * @return
			 * @since JDK 1.6
			 */
			public String getBusTypeParentNameByRegId(Map<String, Object> paraMap){
				Map<String, Object> resutMap = null;
				try {
					resutMap = houseDao.queryMapByKey("Register.getBusTypeParentNameByRegId", paraMap); //����Ϊreg_code
					if(resutMap == null || resutMap.isEmpty()){
						throw new BusinessException("��ȡҵ�����ͷ������ ҵ�����ͷ���Ϊ��");
					}
				} catch (Exception e) {
					throw new BusinessException("��ȡҵ�����ͷ������"+e.getMessage());
				}
				return resutMap.get("BUS_NAME").toString();
			}
			
			/**
			 * 
			 * getBusTypeParentIdByRegId:(ͨ���ǼǱ�Ż�ȡ  ҵ�����͵ĸ�����). 
			 *
			 * @author Joyon
			 * @param paraMap
			 * @return
			 * @since JDK 1.6
			 */
			public String getBusTypeParentIdByRegId(Map<String, Object> paraMap){
				Map<String, Object> resutMap = null;
				try {
					resutMap = houseDao.queryMapByKey("Register.getBusTypeParentNameByRegId", paraMap); //����Ϊreg_code
					if(resutMap == null || resutMap.isEmpty()){
						return "";
						//throw new BusinessException("��ȡҵ�����ͷ������ ҵ�����ͷ���Ϊ��");
					}
				} catch (Exception e) {
					throw new BusinessException("��ȡҵ�����ͷ������"+e.getMessage());
				}
				return resutMap.get("bus_type_id").toString();
			}
			
			/**
			 * 
			 * getBusTypeIdByRegIdMap:(ͨ���ǼǱ��Map��ȡҵ�����͵�ID). 
			 *
			 * @author Joyon
			 * @param paraMap reg_code
			 * @return
			 * @since JDK 1.6
			 */
			public String getBusTypeIdByRegIdMap(Map<String, Object> paraMap){
					BusinessMain businessMain = businessMainDao.get("where reg_code=:reg_code", paraMap);
					if(businessMain !=null){
						return businessMain.getReg_type();
					}else{
						LogUtil.error("ҵ����������Ϊ�գ�");
					}
					return null;
			}
			
			
			/**
			 * 
			 * getBkOwnershipInfoByRegId:(ͨ���ǼǱ�Ż�ȡ �Ǽǲ���Ϣ). 
			 * @author Joyon
			 * @param paraMap reg_code �ǼǱ��
			 * @return
			 * @since JDK 1.6
			 */
			public Map<String, Object> getBkOwnershipInfoByRegId(Map<String, Object> paraMap){
				Map<String, Object> bkOwnershipInfoMap = null;
				try {
					bkOwnershipInfoMap = houseDao.queryMapByKey("Register.getBkOwnershipInfoByRegId","where reg_code=:reg_code", paraMap);
				} catch (Exception e) {
					e.printStackTrace();
					throw new BusinessException("��ȡ�Ǽǲ�����Ȩ������Ϣ����"+e.getMessage());
				}
				return bkOwnershipInfoMap;
			}
			
			
			
			/**
			 * 
			 * getBKInfoByRegId:(ͨ���ǼǱ�Ż�ȡ �Ǽǲ���Ϣ). 
			 * @author Joyon
			 * @param paraMap reg_code �ǼǱ��
			 * @return
			 * @since JDK 1.6
			 */
			public Map<String, Object> getBKInfoByRegId(String key,String sql,Map<String, Object> paraMap){
				Map<String, Object> bkOwnershipInfoMap = null;
				try {
					bkOwnershipInfoMap = houseDao.queryMapByKey(key, sql, paraMap);
				} catch (Exception e) {
					e.printStackTrace();
					throw new BusinessException("��ȡ�Ǽǲ�����Ȩ������Ϣ����"+e.getMessage());
				}
				return bkOwnershipInfoMap;
			}
			
			/**
			 * 
			 * getRightCertificateNo:(ͨ���ǼǱ�Ż�ȡ���ز�֤��)
			 * @author Joyon
			 * @param paraMap reg_code
			 * @return CER_NO
			 * @since JDK 1.6
			 */
			public List<Map<String, Object>> getCertificateNoByRegId(Map<String, Object> paraMap){
				String search_type = checkRegTypeByRegId(paraMap);		
				String key = null;
				if(search_type.equals("�Ǽǲ���ѯ")){
					key = "Register.getRightCertificateNoFromBkOwnership";							//�Ǽǲ���ѯ�ӵǼǲ�����Ȩ���ֲ�ѯ���ز�֤��
				}else if(search_type.equals("�Ǽǲ�Ԥ��")){
					return null;
					//key = "Register.getRightCertificateNo";											//�Ǽǲ�Ԥ��ʱ�ӷ��ز�֤���в�ѯ  
				}
				
				List<Map<String, Object>> resultList = null;
				try {
					resultList = houseDao.queryMapListByKey(key, paraMap);
				} catch (Exception e) {
					e.printStackTrace();
					throw new BusinessException("��ȡȨ֤����Ϣ����"+e.getMessage());
				}
				return resultList;
			}
			
			
			/**
			 * 
			 * checkRegTypeByRegId:(ͨ���ǼǱ�� ��鵱ǰִ�е��ǵǼǲ�Ԥ�� ���ǵǼǲ���ѯ). 
			 * @author Joyon
			 * @param paraMap	reg_code
			 * @return		�Ǽǲ�Ԥ��/�Ǽǲ���ѯ
			 * @since JDK 1.6
			 */
			public String checkRegTypeByRegId(Map paraMap){
				String result = null;
				Map<String,Object> resultMap = null;
				
				try {
					resultMap = houseDao.queryMapByKey(
							"Register.getBkOwnershipInfoByRegId","where reg_code=:reg_code", paraMap);
				} catch (Exception e) {
					e.printStackTrace();
					throw new BusinessException("��ȡ�Ǽǲ�����Ȩ������Ϣ����"+e.getMessage());
				}
				//����Ȩ���ֵǼ���Ϣ������  ���صǼǲ�Ԥ��
				if(resultMap == null || resultMap.isEmpty()){
					result="�Ǽǲ�Ԥ��";
				}else{
					result = "�Ǽǲ���ѯ";
				}
				return result;
			}
			/**
			 * 
			 * save_Baseinfo:(����Ǽǲ�������Ϣ�����ڵǲ�). <br/>
			 * @author xuzz
			 * @param regUnitRelMap
			 * @param busMainMap
			 * @since JDK 1.6
			 */
			public Reg_baseInfo save_Baseinfo(Map<String,Object> regUnitRelMap,Map<String,Object> busMainMap)
			{
				
				//�Ǽǲ� ������Ϣ  ÿ���Ǽǵ�Ԫֻ����һ������  �����ȸ��ݵǼǵ�Ԫ�����ж�   ��Ǽǲ�������Ϣ��������  �򷵻��� ������  ���������  ������
				String reg_unit_code = regUnitRelMap.get("REG_UNIT_CODE").toString();
				
				//����Ǽǲ�������Ϣʱ   ��ͨ���Ǽǵ�Ԫ����  ȥ���ݿ���ȡ  ����Ѿ�����      �򷵻ص�ǰ���ڵĵǼǲ�������Ϣ����      ���������  ������һ��
				Reg_baseInfo reg_baseInfo = getReg_baseInfoByUnitCode(reg_unit_code);
				if(reg_baseInfo != null){
					return reg_baseInfo;
				}
				
				
				
				//����Ǽǲ�������Ϣ
				//String houser_id = regUnitRelMap.get("HOUSE_ID").toString();
				//String parcel_id = getParcelMapByHouseId(houser_id).get("PARCEL_ID").toString();
				reg_baseInfo = new Reg_baseInfo();
				reg_baseInfo.setBook_code(String.valueOf(reg_baseInfoDao.getSeqId()));
				//reg_baseInfo.setHouse_id(houser_id);
				//reg_baseInfo.setParcel_id(parcel_id);
				reg_baseInfo.setReg_unit_type(regUnitRelMap.get("REG_UNIT_TYPE").toString());
				reg_baseInfo.setReg_unit_code(regUnitRelMap.get("REG_UNIT_CODE").toString());
				reg_baseInfo.setReg_organization(busMainMap.get("REG_STATION").toString());
				try {
					reg_baseInfoDao.save(reg_baseInfo);
				} catch (Exception e) {
					e.printStackTrace();
					throw new BusinessException("����Ǽǲ�������Ϣ����"+e.getMessage());
				}
				return reg_baseInfo;
			}
			/**
			 * 
			 * save_cerno:(���淿�ز�֤�ţ����ڵǲ�). <br/>
			 * @author xuzz
			 * @param paraMap
			 * @return
			 * @since JDK 1.6
			 */
			public String save_cerno(Map<String,Object> paraMap)
			{
				Row row = new RowImpl();
				row.put("name", "���ز�֤��");
				String cer_no = FacadeFactory.getIdentifierFacade().getSerialNumber(row);
				if(cer_no != null && cer_no != ""){
					Certificate certificate = new Certificate();
					try {
						String cer_id = certificateDao.queryMapByKey("Register.getCertificateMapByRegCode",paraMap).get("CERTIFICATE_ID").toString();
						if(cer_id == null || cer_id == ""){
							throw new BusinessException("��ȡ���ز�֤��ID ����  IDΪ��");
						}
						certificate.setCertificate_id(cer_id);
						certificate = certificateDao.get(certificate);
						certificate.setCertificate_code(cer_no);
						certificateDao.update(certificate);
					} catch (Exception e) {
						e.printStackTrace();
						throw new BusinessException("��ȡ���ز�֤��ID ����  "+e.getMessage());
					}
					
				}
				return cer_no;
			}
			/**
			 * saveHolder ����Ȩ����
			 * @param paraMap
			 * @param reg_baseInfo
			 */
			public void saveHolder(Map<String,Object> paraMap,Reg_baseInfo reg_baseInfo)
			{
				
				String bus_class_id = paraMap.get("bus_class_id").toString();
				
				//����Ȩ���˼�����Ϣ
				HolderRelationship holderRelationship = new HolderRelationship();
				holderRelationship.setRight_rel_id(String.valueOf(holderRelationshipDao.getSeqId()));
				holderRelationship.setBook_code(reg_baseInfo.getBook_code());
				holderRelationship.setReg_code(paraMap.get("reg_code").toString());
				
				//������list
				List<Map<String,Object>> appList = null;
				
				//�����ǰҵ��Ϊ��ѺȨ
				if(bus_class_id.equals(WbfConstants.MORTGAGE_RIGHT)){
					//��ѺȨҵ��ʱ   Ȩ���˼�����Ϊ��Ч  
					holderRelationship.setEffective(WbfConstants.UNEFFECTIVE);
					
					paraMap.put("reg_unit_code", reg_baseInfo.getReg_unit_code());
					//���ݵ�ǰ�Ǽǵ�Ԫ��Ż�ȡȨ��������
					appList = holderRelationshipDao.queryMapListByKey("Register.getApplicaent","where reg_unit_code=:reg_unit_code and bus_id=:bus_id and hol_rel='"+WbfConstants.MORTGAGER+"'", paraMap);
					List<Map<String,Object>>  appMortgeMapList =null;
					String busTypeId = getBusTypeIdByRegIdMap(paraMap);						//ͨ����ǰ�ǼǱ�� ��ȡҵ������ID   �����ж��Ƿ����ڵ�ѺȨת�ƵǼ�
					//����ǵ�Ѻת�ƵǼ�   ��ѵ�ѺȨ���÷�����Ϊ��ѺȨ��
					if(busTypeId.equals(WbfConstants.MORTGAGE_SHIFT) || busTypeId.equals(WbfConstants.MAX_MORTGAGE_SHIFT) ){
						appMortgeMapList = holderRelationshipDao.queryMapListByKey("Register.getApplicaent","where bus_id=:bus_id and hol_rel='"+WbfConstants.MORTGAGE_TRANSFEROREE+"'",paraMap);
						if(appMortgeMapList!=null && !appMortgeMapList.isEmpty()){
							//�����÷���Ϊ��ѺȨ�˲����浽Ȩ������Ϣ��
							for(int i=0;i<appMortgeMapList.size();i++){
								appMortgeMapList.get(i).put("HOL_REL", WbfConstants.MORTGAGEE);
							}
						}
						
					}else{
						//��ȡȨ��������(hol_rel)Ϊ��ѺȨ�˵�����������    063004
						 appMortgeMapList = holderRelationshipDao.queryMapListByKey("Register.getApplicaent","where bus_id=:bus_id and hol_rel='"+WbfConstants.MORTGAGEE+"'",paraMap);
					}
					//�����ѺȨ�˲�Ϊ��   ����ӵ�������list��
					if(appMortgeMapList != null && !appMortgeMapList.isEmpty()){
						appList.addAll(appMortgeMapList);
					}
				}else{
					//�Ȼ�ȡ��Ч��Ȩ���˼���    ��ΪʧЧ      �ٰѵ�ǰȨ���˼��ϸ�Ϊ��Ч      ����ЧȨ����ʱ�������
					HolderRelationship effHolderRelationship = getEffectiveholderRelationshipRegUnitCode(reg_baseInfo.getReg_unit_code());
					if(effHolderRelationship != null){
						effHolderRelationship.setEffective(WbfConstants.UNEFFECTIVE);
						holderRelationshipDao.update(effHolderRelationship);
					}
					
					//����ҵ������ǰȨ���˼���ʧЧ��    �ѵ�ǰȨ���˼�����Ϊ��Ч
					holderRelationship.setEffective(WbfConstants.EFFECTIVE);
					
					//����������ֶ������ҵ���   ��ȡ�����˼���
					appList = getAplPerInfoByRegId(paraMap);	
				}
				
				try {
					holderRelationshipDao.save(holderRelationship);
				} catch (Exception e) {
					e.printStackTrace();
					throw new BusinessException("���汣��Ȩ���˼�����Ϣ����"+e.getMessage());
				}
				
				Holder holder = null;
				try {
					if(appList.size()>0)
					{
						for (Map<String,Object> tempApp : appList) {
							holder = new Holder();
							holder.setHolder_id(String.valueOf(holderDao.getSeqId()));
							holder.setRight_rel_id(holderRelationship.getRight_rel_id());
							holder.setHol_type(tempApp.get("APP_TYPE").toString());
							holder.setHol_name(tempApp.get("APP_NAME").toString());
							holder.setHol_cer_type(tempApp.get("APP_CER_TYPE").toString());
							holder.setHol_cer_no(tempApp.get("APP_CER_NO").toString());
							holder.setHol_address(tempApp.get("APP_ADDRESS").toString());
							
							Object holRelObj = tempApp.get("HOL_REL");
							if(holRelObj !=null){
								holder.setHol_rel(holRelObj.toString());
							}
							
							//holder.setDepart_type(tempApp.get("DEPART_TYPE").toString());
							
							Object legalObject = tempApp.get("LEGAL_NAME");//.toString()
							
							//�ж�  �������Ϊ�� �򲻱��淨��    
							if(legalObject!=null){
								holder.setLegal_name(legalObject.toString());
							}
							Object agent_name = tempApp.get("AGENT_NAME");
							if(agent_name != null){
								holder.setAgent_name(agent_name.toString());
							}
							
							Object agent_cer = tempApp.get("AGENT_CER");
							if(agent_cer != null){
								holder.setAgent_cer(agent_cer.toString());
							}
							
							Object agent_tel = tempApp.get("AGENT_TEL");
							if(agent_tel != null){
								holder.setAgent_tel(agent_tel.toString());
							}
							
							Object portion = tempApp.get("APP_PORT");
							if(portion != null){
								holder.setPortion(portion.toString());
							}
							//holder.setHis_holder_id(his_holder_id);
							
							holderDao.save(holder);
							
							//his_holder_id = holder.getHolder_id();							//��������ʷȨ����ID Ϊ��һ�α����ID	
							//temp_count++;														//��������
					}
					}
					
				} catch (Exception e) {
					e.printStackTrace();
					throw new BusinessException("���汣��Ȩ������Ϣ����"+e.getMessage());
				}
			}
			/**
			 * 
			 * save_ownshipbook:(����Ǽǲ�����Ȩ���֣����ڵǲ�). <br/>
			 * @author xuzz
			 * @param ownershipInfoMap
			 * @param paraMap
			 * @param reg_baseInfo
			 * @param busMainMap
			 * @param certificateDB
			 * @param cer_no
			 * @since JDK 1.6
			 */
			public void saveOwnshipBook(Map<String,Object> ownershipInfoMap,Map<String,Object> paraMap,Reg_baseInfo reg_baseInfo,Map<String,Object> busMainMap,Certificate certificateDB,String cer_no)
			{
				//�Ȱѵ�ǰ�Ǽǵ�Ԫ��Ч�ĵǼǲ�  ��Ϊ��Ч 00    ���浱ǰ��ϢΪ����Ч01   �������Ч��¼   ��ֻ���浱ǰΪ��Ч��
				String proc_id =  busMainMap.get("PROC_ID").toString();
				Reg_ownership effReg_Ownership = getEffectiveReg_OwnershipByProcId(proc_id);
				if(effReg_Ownership != null){
					effReg_Ownership.setEffective(WbfConstants.UNEFFECTIVE);
					reg_ownershipDao.update(effReg_Ownership);
				}
				
				Reg_ownership   reg_ownership = new Reg_ownership();
				reg_ownership.setOwnership_id(String.valueOf(reg_ownershipDao.getSeqId()));
				reg_ownership.setReg_date(new Date());
				reg_ownership.setReg_code(paraMap.get("reg_code").toString());
				reg_ownership.setBook_code(reg_baseInfo.getBook_code());
				reg_ownership.setRecorder(paraMap.get("recorder").toString());
				reg_ownership.setReg_value(ownershipInfoMap.get("REG_VALUE").toString());
				reg_ownership.setProcdef_id(busMainMap.get("PRO_DEF_ID").toString());
				reg_ownership.setExcursus(certificateDB.getExcursus());
				reg_ownership.setCer_no(cer_no);
				
				
				Object tmpObj = ownershipInfoMap.get("GET_MODE");
				if(tmpObj!=null){
					reg_ownership.setGet_mode(tmpObj.toString());
				}
				
				tmpObj = ownershipInfoMap.get("LU_TERM");
				if(tmpObj!=null){
					reg_ownership.setLu_term(tmpObj.toString());
				}
				
				tmpObj = ownershipInfoMap.get("START_DATE");
				if(tmpObj!=null){
					reg_ownership.setStart_date((Date)ownershipInfoMap.get("START_DATE"));
				}
				
				tmpObj = ownershipInfoMap.get("END_DATE");
				if(tmpObj!=null){
					reg_ownership.setEnd_date((Date)ownershipInfoMap.get("END_DATE"));
				}
				
				tmpObj = ownershipInfoMap.get("HOUSE_USAGE");
				if(tmpObj!=null){
					reg_ownership.setHouse_usage(tmpObj.toString());
				}
				
				tmpObj = ownershipInfoMap.get("HOUSE_ATTR");
				if(tmpObj!=null){
					reg_ownership.setHouse_attr(tmpObj.toString());
				}
				reg_ownership.setEffective(WbfConstants.EFFECTIVE);
				//reg_ownership.setPre_reg_code(getPreRegCodeByRegId(paraMap));						//��ʼ�Ǽ�  ��ǰ�ǼǱ��
				try {
					reg_ownershipDao.save(reg_ownership);
				} catch (Exception e) {
					LogUtil.error("����Ǽǲ�����Ȩ������Ϣ����"+e.getMessage());
					throw new BusinessException("����Ǽǲ�����Ȩ������Ϣ����"+e.getMessage());
				}
				
				updateRegUnitState(paraMap);
				
				//save_holder(paraMap,reg_baseInfo);
			}
			
			
			
			/**
			 * �ǲ��ɹ������ò�Ȩ״̬Ϊ��Ч
			 * @param paraMap  ��reg_code,reg_unit_code��
			 */
			public void updateRegUnitState(Map paraMap)
			{
				String whereAndSql = "";								//�еǼǵ�Ԫ��ŵļ��ϵǼǵ�Ԫ�������
				if(paraMap.get("reg_unit_code")!=null){
					 whereAndSql = "and reg_unit_code=:reg_unit_code";
				}
				//�ǲ��ɹ������ò�Ȩ״̬Ϊ��Ч
				Reg_relationship regRelationship =  reg_relationshipDao.get("where reg_code=:reg_code "+whereAndSql,paraMap);
				if(regRelationship!=null){
					regRelationship.setReg_state(WbfConstants.EFFECTIVE);
					reg_relationshipDao.update(regRelationship);
				}else{
					LogUtil.error("�޸ĵǼǵ�Ԫ��Ȩ״̬�������");
				}
			}
			/**
			 * 
			 * save_userightbook:(����Ǽǲ�ʹ��Ȩ���ǲ�). <br/>
			 * @author xuzz
			 * @param allbookInfoMap
			 * @param paraMap
			 * @param reg_baseInfo
			 * @param busMainMap
			 * @param certificateDB
			 * @param cer_no
			 * @since JDK 1.6
			 */
			/**
			 * 
			 * saveOwnshipChange:(�����������Ϣ ���뵽������ʷ���޸ķ��ݱ����뷿�����Ʊ�).
			 *
			 * @author Joyon
			 * @param busMainMap
			 * @since JDK 1.6
			 */
			public void  saveHouseChange(Map<String,Object> busMainMap){
				String proc_id = busMainMap.get("proc_id").toString();
				String bus_id = busMainMap.get("BUS_ID").toString();
				String reg_code = busMainMap.get("REG_CODE").toString();
				Map<String,Object> paraMap = new HashMap<String,Object>();
				paraMap.put("bus_id",bus_id);			//��ѯ���ݵĲ���
				
				//��ȡ�����MapList
				List<Map<String,Object>> changeRecordMapList = FacadeFactory.getChangeRecordFacade().getChangeRecordMapListByProcId(proc_id);
				String tempChangeCode = null;						//�����¼��  code
				
				House newHouse = null;								//�·��ݱ�����
				//House hisHouse = null;								//�·��ݱ�����
				HouseHistory houseHistory = null;					//������ʷ��
				
				//������������  ��Ϊ��
				if(changeRecordMapList != null){
					
//					//�ж��Ƿ��з������ݱ��  �еĻ��ͻ�ȡ�����ݱ�������
//					for(Map tempMap:changeRecordMapList){
//						tempChangeCode = tempMap.get("CHANGE_CODE").toString();
//						if(tempChangeCode.equals(WbfConstants.PRODUCT_NAME) || tempChangeCode.equals(WbfConstants.HOUSE_LOCATION) || tempChangeCode.equals(WbfConstants.BUILD_AREA) || tempChangeCode.equals(WbfConstants.TAONEI_AREA) || tempChangeCode.equals(WbfConstants.FLATLET_USAGE) || tempChangeCode.equals(WbfConstants.HOUSE_ATTR)){
//							newHouse = houseDao.get("where ADV_HOUSE_CODE = (select REG_UNIT_CODE from BUS_REGUNITREL where bus_id=:bus_id)", paraMap);
//							break;
//						}
//						
//					}
					newHouse = houseDao.get("where ADV_HOUSE_CODE = (select REG_UNIT_CODE from BUS_REGUNITREL where bus_id=:bus_id)", paraMap);
					//���������Ϣ��Ϊ��      copyһ�ݵ�������ʷ��
//					if(newHouse!=null){
						houseHistory = new HouseHistory();
						houseHistoryDao.copyProperties(houseHistory, newHouse);
						houseHistory.setHouse_his_id(SequenceUtil.getGlobalSeqID());
						String last_bus_id = getLastBusIdByRegCode(reg_code);
						houseHistory.setLast_bus_id(last_bus_id);
						
						//����һ������ʷ��
						houseHistoryDao.save(houseHistory);
						
//					}
					
					/***** ���������Ʊ�����  ****/
					String strOldDataItem = "";		//���ǰ����
					String strNewDataItem = "";		//���������
					String strTemp = "";				//�����жϼ��˼���
					int trendCount = 0;
					
					//��ñ��ǰ���� strOldDataItem ���������     ����еǼǼ۸�    ����������еĵǼǼ۸�
					for(Map tempMap:changeRecordMapList){
						
						tempChangeCode = tempMap.get("CHANGE_CODE").toString();
						//tempMap.get("");
						if(tempChangeCode.equals(WbfConstants.PRODUCT_NAME)){
							newHouse.setPro_name(tempMap.get("NEW_DATA").toString());
							strTemp="���ز�����:";
						}else if(tempChangeCode.equals(WbfConstants.HOUSE_LOCATION)){
							newHouse.setHouse_location(tempMap.get("NEW_DATA").toString());
							strTemp="��������:";
						}else if(tempChangeCode.equals(WbfConstants.BUILD_AREA)){
							newHouse.setBuild_area(Double.valueOf(tempMap.get("NEW_DATA").toString()));
							strTemp="�������:";
						}else if(tempChangeCode.equals(WbfConstants.TAONEI_AREA)){
							newHouse.setTaonei_area((Double.valueOf(tempMap.get("NEW_DATA").toString())));
							strTemp="�������:";
						}else if(tempChangeCode.equals(WbfConstants.FLATLET_USAGE)){
							newHouse.setFlatlet_usage((tempMap.get("NEW_DATA").toString()));
							strTemp="������;:";
						}else if(tempChangeCode.equals(WbfConstants.HOUSE_ATTR)){
							newHouse.setHouse_kind((tempMap.get("NEW_DATA").toString()));
							strTemp="��������:";
						}else if(tempChangeCode.equals(WbfConstants.REG_VALUE)){
							strTemp="�ǼǼ۸�:";
							//����еǼǼ۸�   ��������Ȩ�Ǽ���Ϣ��ǼǼ۸�    
							BusOwnership busOwnership = FacadeFactory.getCommonFacade().getBusOwnershipByProcId(proc_id);
							busOwnership.setReg_value((Float.valueOf(tempMap.get("NEW_DATA").toString())));
							busOwnershipDao.update(busOwnership);
							
						}else if(tempChangeCode.equals(WbfConstants.HOL_NAME)){
							strTemp="Ȩ��������:";
						}else if(tempChangeCode.equals(WbfConstants.HOL_CER_NO)){
							strTemp="���֤����:";
						}
						
						
						//ƴ�ַ���
						if(trendCount>0){
							strOldDataItem+=(","+strTemp+tempMap.get("OLD_DATA").toString());
							
							strNewDataItem+=(","+strTemp+tempMap.get("NEW_DATA").toString());
						}else{
							strOldDataItem+=(strTemp+tempMap.get("OLD_DATA").toString());
							
							strNewDataItem+=(strTemp+tempMap.get("NEW_DATA").toString());
						}
						
						trendCount ++;
					}
					
					HouseTrend houseTrend = new HouseTrend();
					
					if(newHouse == null){
						newHouse = houseDao.get("where ADV_HOUSE_CODE = (select REG_UNIT_CODE from BUS_REGUNITREL where bus_id=:bus_id)", paraMap);
					}
					
					houseTrend.setTrend_id(SequenceUtil.getGlobalSeqID());
					houseTrend.setBg_time(new Date());
					houseTrend.setPre_content(strOldDataItem);
					houseTrend.setNext_content(strNewDataItem);
					houseTrend.setFlatlet_code(newHouse.getAdv_house_code());
					if(houseHistory!=null){
						houseTrend.setHistory_id(houseHistory.getHouse_his_id());
					}
					houseTrend.setFlatlet_id(newHouse.getHouse_id());
					houseTrendDao.save(houseTrend);
					/**** ���������Ʊ���Ϣ���� ****/
					
					//�޸ķ��ݱ�����
					houseDao.update(newHouse);
					
					//
				}
				 
				
			}
			
			/**
			 * 
			 * getLastBusIdByRegCode:(ͨ����ǰ�ǼǱ�Ż�ȡǰһ��ҵ��ID).
			 *
			 * @author Joyon
			 * @param reg_code
			 * @return
			 * @since JDK 1.6
			 */
			private String getLastBusIdByRegCode(String reg_code) throws BusinessException{
				Map<String,Object> paraMap = new HashMap<String,Object>();
				paraMap.put("reg_code",reg_code);
				String whereSql = "where reg_code = (select LAST_REG_CODE from BUS_REGUNITREL where reg_code=:reg_code)";
				String last_bus_id = null;
				try {
					last_bus_id = reg_relationshipDao.get(whereSql, paraMap).getBus_id();
				} catch (Exception e) {
					LogUtil.error("RegisterFacade.getLastBusIdByRegCode  ��ȡǰҵ��ID����"+e.getMessage());
				}
				return last_bus_id;
			}
			/**
			 * 
			 * saveOwnshipChange:(�����������Ϣ ���뵽������ʷ���޸ķ��ݱ����뷿�����Ʊ�).
			 *
			 * @author Joyon
			 * @param busMainMap
			 * @since JDK 1.6
			 */
			public void  saveParcelChange(Map<String,Object> busMainMap){
				String proc_id = busMainMap.get("proc_id").toString();
				String bus_id = busMainMap.get("BUS_ID").toString();
				String reg_code = busMainMap.get("REG_CODE").toString();
				Map<String,Object> paraMap = new HashMap<String,Object>();
				paraMap.put("bus_id",bus_id);			//��ѯ���ݵĲ���
				
				//��ȡ�����MapList
				List<Map<String,Object>> changeRecordMapList = FacadeFactory.getChangeRecordFacade().getChangeRecordMapListByProcId(proc_id);
				String tempChangeCode = null;						//�����¼��  code
				
				Land newLand = null;								//�·��ݱ�����
				//House hisHouse = null;								//�·��ݱ�����
				LandHistory landHistory = null;					//������ʷ��
				
				//������������  ��Ϊ��
				if(changeRecordMapList != null){
					
					//�ж��Ƿ��з������ݱ��  �еĻ��ͻ�ȡ�����ݱ�������
//					for(Map tempMap:changeRecordMapList){
//						tempChangeCode = tempMap.get("CHANGE_CODE").toString();
//						if(tempChangeCode.equals(WbfConstants.PARCEL_CODE) || tempChangeCode.equals(WbfConstants.PARCEL_AREA) || tempChangeCode.equals(WbfConstants.REAL_USAGE) || tempChangeCode.equals(WbfConstants.LOCATION_AREA) || tempChangeCode.equals(WbfConstants.LAND_ADDRESS) || tempChangeCode.equals(WbfConstants.USE_PER)){
//							newLand = landDao.get("where PARCEL_CODE = (select REG_UNIT_CODE from BUS_REGUNITREL where bus_id=:bus_id)", paraMap);
//							break;
//						}
//						
//					}
					
					//��ʱȫ�����浽������ʷ����
					newLand = landDao.get("where PARCEL_CODE = (select REG_UNIT_CODE from BUS_REGUNITREL where bus_id=:bus_id)", paraMap);
					//���������Ϣ��Ϊ��      copyһ�ݵ�������ʷ��
					//if(newLand!=null){
						landHistory = new LandHistory();
						landHistoryDao.copyProperties(landHistory, newLand);
						landHistory.setParcel_his_id(SequenceUtil.getGlobalSeqID());
						String last_bus_id = getLastBusIdByRegCode(reg_code);
						landHistory.setLast_bus_id(last_bus_id);
						
						//����һ������ʷ��
						landHistoryDao.save(landHistory);
						
					//}
					
					/***** ���������Ʊ�����  ****/
					String strOldDataItem = "";		//���ǰ����
					String strNewDataItem = "";		//���������
					String strTemp = "";				//�����жϼ��˼���
					int trendCount = 0;
					
					Bususeright bususeright = FacadeFactory.getCommonFacade().getBususerightByProcId(proc_id);		//�������¹��̱��е�����
					//��ñ��ǰ���� strOldDataItem ���������     ����еǼǼ۸�    ����������еĵǼǼ۸�
					for(Map tempMap:changeRecordMapList){
						
						tempChangeCode = tempMap.get("CHANGE_CODE").toString();
						//tempMap.get("");
						if(tempChangeCode.equals(WbfConstants.PARCEL_CODE)){
							newLand.setParcel_code(tempMap.get("NEW_DATA").toString());
							strTemp="�ڵغ�:";
						}else if(tempChangeCode.equals(WbfConstants.PARCEL_AREA)){
							newLand.setParcel_area(tempMap.get("NEW_DATA").toString());
							strTemp="�ڵ����:";
						}else if(tempChangeCode.equals(WbfConstants.REAL_USAGE)){
							newLand.setReal_usage(tempMap.get("NEW_DATA").toString());
							strTemp="������;:";
						}else if(tempChangeCode.equals(WbfConstants.LOCATION_AREA)){						//������   ��ʱû��
							//newLand.setTaonei_area((Double.valueOf(tempMap.get("NEW_DATA").toString())));
							strTemp="������:";
						}else if(tempChangeCode.equals(WbfConstants.LAND_ADDRESS)){
							newLand.setLand_address((tempMap.get("NEW_DATA").toString()));
							strTemp="�ڵ�����:";
						}else if(tempChangeCode.equals(WbfConstants.USE_PER)){
							newLand.setUse_per(tempMap.get("NEW_DATA").toString());
							strTemp="ʹ������:";
						}else if(tempChangeCode.equals(WbfConstants.PAR_REG_VALUE)){
							strTemp="���ؼۿ�:";
							//����еǼǼ۸�   ��������Ȩ�Ǽ���Ϣ��ǼǼ۸�    
							
							bususeright.setGet_price(Double.valueOf(tempMap.get("NEW_DATA").toString()));
							bususerightDao.update(bususeright);
							
						}else if(tempChangeCode.equals(WbfConstants.ADD_PARCEL_PRICE)){
							strTemp="���ؼۿ�:";
						}else if(tempChangeCode.equals(WbfConstants.HOL_NAME)){
							strTemp="Ȩ��������:";
						}else if(tempChangeCode.equals(WbfConstants.HOL_CER_NO)){
							strTemp="���֤����:";
						}
						
						
						//ƴ�ַ���
						if(trendCount>0){
							strOldDataItem+=(","+strTemp+tempMap.get("OLD_DATA").toString());
							
							strNewDataItem+=(","+strTemp+tempMap.get("NEW_DATA").toString());
						}else{
							strOldDataItem+=(strTemp+tempMap.get("OLD_DATA").toString());
							
							strNewDataItem+=(strTemp+tempMap.get("NEW_DATA").toString());
						}
						
						trendCount ++;
					}
					LandTrend landTrend = new LandTrend();
					
					
					//δ�����ڵػ�����Ϣ��  ����ڵر��л�ȡһ������
					if(newLand == null){
						newLand = landDao.get("where PARCEL_CODE = (select REG_UNIT_CODE from BUS_REGUNITREL where bus_id=:bus_id)", paraMap);
					}
					
					landTrend.setTrend_id(SequenceUtil.getGlobalSeqID());
					landTrend.setBg_time(new Date());
					landTrend.setPre_content(strOldDataItem);
					landTrend.setNext_content(strNewDataItem);
					landTrend.setParcel_code(newLand.getParcel_code());
					if(landHistory!=null){
						landTrend.setHistory_id(landHistory.getParcel_his_id());
					}
					landTrend.setParcel_id(newLand.getParcel_id());
					landTrendDao.save(landTrend);
					/**** ���������Ʊ���Ϣ���� ****/
					
					//�޸��ڵر�����
					landDao.update(newLand);
					
					//
				}
				 
				
			}
			/**
			 * ʹ��Ȩ�Ǽǲ�������
			 * @param saveUserightbook
			 * @param paraMap
			 * @param reg_baseInfo
			 * @param busMainMap
			 * @param certificateDB
			 * @param cer_no
			 */
			public void saveUserightbook(Map<String,Object> allbookInfoMap,Map<String,Object> paraMap,Reg_baseInfo reg_baseInfo,Map<String,Object> busMainMap,Certificate certificateDB,String cer_no)
			{
				//��ȡ�Ǽǲ�����Ч������      ����и�Ϊ��Ч   ����������
				Reg_Useright effReguseright = getEffectiveReg_userightByRegUnitCode(reg_baseInfo.getReg_unit_code());
				if(effReguseright != null){
					effReguseright.setEffective(WbfConstants.UNEFFECTIVE);
					reg_UserightDao.update(effReguseright);
				}
				
				Reg_Useright reguseright=new Reg_Useright();
				//Reg_ownership   reg_ownership = new Reg_ownership();
				reguseright.setUseright_id(String.valueOf(reg_UserightDao.getSeqId()));
				reguseright.setReg_date(new Date());
				reguseright.setReg_code(paraMap.get("reg_code").toString());
				reguseright.setBook_code(reg_baseInfo.getBook_code());
				reguseright.setRecorder(paraMap.get("recorder").toString());
				reguseright.setReg_pri(allbookInfoMap.get("GET_PRICE").toString());
				reguseright.setProcdef_id(busMainMap.get("PRO_DEF_ID").toString());
				reguseright.setExcursus(certificateDB.getExcursus());
				reguseright.setCer_no(cer_no);				
				//reguseright.setGet_mode(ownershipInfoMap.get("GET_MODE").toString());
				reguseright.setLu_term(allbookInfoMap.get("USE_LIMIT").toString());
				reguseright.setStart_date((Date)allbookInfoMap.get("START_DATE"));
				reguseright.setEnd_date((Date)allbookInfoMap.get("END_DATE"));
				reguseright.setLand_use(allbookInfoMap.get("LAND_USE").toString());
				reguseright.setUseright_type(allbookInfoMap.get("USERIGHT_TYPE").toString());
				reguseright.setEffective(WbfConstants.EFFECTIVE);
				//reg_ownership.setPre_reg_code(getPreRegCodeByRegId(paraMap));						//��ʼ�Ǽ�  ��ǰ�ǼǱ��
				try {
					//throw new BusinessException("fffff");
					reg_UserightDao.save(reguseright);
				} catch (Exception e) {
					e.printStackTrace();
					throw new BusinessException("����Ǽǲ�����Ȩ������Ϣ����"+e.getMessage());
				}
				updateRegUnitState(paraMap);
				//save_holder(paraMap,reg_baseInfo);
			}
			/**
			 * 
			 * save_realstateCan:(����Ǽǲ����ز�֤ע���Ǽǣ��ǲ�). <br/>
			 * @author xuzz
			 * @since JDK 1.6
			 */
			@Transactional
			public void save_realstateCan(Map<String,Object> allbookInfoMap,Map<String,Object> paraMap,Reg_baseInfo reg_baseInfo,Map<String,Object> busMainMap)
			{
				//ʹ��Ȩ
				Reg_Useright reguseright=new Reg_Useright();
				//����Ȩ
				Reg_ownership reg_ownership=new Reg_ownership();		
				//ͨ���ǼǱ�Ż�ȡ�Ǽǵ�Ԫ����������
				Map<String , Object> relationMap= getRegUnitRelMapByRegId(paraMap);
				
				//paraMap.put("REG_UNIT_TYPE", relationMap.get("REG_UNIT_TYPE").toString());
				if(relationMap.get("REG_UNIT_TYPE").toString().equals(WbfConstants.PARCEL))
				{
					//��ȡ�Ǽǲ�����Ч������      ����и�Ϊ��Ч   ����������
					Reg_Useright effReguseright = getEffectiveReg_userightByRegUnitCode(reg_baseInfo.getReg_unit_code());
					if(effReguseright != null){
						effReguseright.setEffective(WbfConstants.UNEFFECTIVE);
						reg_UserightDao.update(effReguseright);
					}
					reguseright.setReason(allbookInfoMap.get("reason").toString());
					reguseright.setUseright_id(String.valueOf(reg_UserightDao.getSeqId()));
					reguseright.setReg_date(new Date());
					reguseright.setReg_code(paraMap.get("reg_code").toString());
					reguseright.setBook_code(reg_baseInfo.getBook_code());
					reguseright.setRecorder(paraMap.get("recorder").toString());
					reguseright.setEffective(WbfConstants.EFFECTIVE);
					reguseright.setProcdef_id(busMainMap.get("PRO_DEF_ID").toString());
					reg_ownership.setPre_reg_code(getPreRegCodeByRegId(paraMap));						//��ʼ�Ǽ�  ��ǰ�ǼǱ��
					try {
						//throw new BusinessException("fffff");
						reg_UserightDao.save(reguseright);
					} catch (Exception e) {
						e.printStackTrace();
						throw new BusinessException("����Ǽǲ�����Ȩ������Ϣ����"+e.getMessage());
					}
				}
				else
				{
					String proc_id =  busMainMap.get("PROC_ID").toString();
					Reg_ownership effReg_Ownership = getEffectiveReg_OwnershipByProcId(proc_id);
					if(effReg_Ownership != null){
						effReg_Ownership.setEffective(WbfConstants.UNEFFECTIVE);
						reg_ownershipDao.update(effReg_Ownership);
					}
					//reg_ownership.copyProperties(reg_ownership, allbookInfoMap);
					reg_ownership.setReason(allbookInfoMap.get("reason").toString());
					reg_ownership.setOwnership_id(String.valueOf(reg_ownershipDao.getSeqId()));
					reg_ownership.setReg_date(new Date());
					reg_ownership.setReg_code(paraMap.get("reg_code").toString());
					reg_ownership.setBook_code(reg_baseInfo.getBook_code());
					reg_ownership.setRecorder(paraMap.get("recorder").toString());					
					reg_ownership.setEffective(WbfConstants.EFFECTIVE);
					reg_ownership.setProcdef_id(busMainMap.get("PRO_DEF_ID").toString());
					reg_ownership.setPre_reg_code(getPreRegCodeByRegId(paraMap));						//��ʼ�Ǽ�  ��ǰ�ǼǱ��
					try {
						reg_ownershipDao.save(reg_ownership);
					} catch (Exception e) {
						LogUtil.error("����Ǽǲ�����Ȩ������Ϣ����"+e.getMessage());
						throw new BusinessException("����Ǽǲ�����Ȩ������Ϣ����"+e.getMessage());
					}
				}
				updateRegUnitState(paraMap);
			}
			
			/**
			 * 
			 * saveRevokeapproval:(������׼�ǲ�������������Ȩ����ʹ��Ȩ�������Ч���ݸ�Ϊ��Ч). <br/>
			 * @author xuzz
			 * @since JDK 1.6
			 */
			@Transactional
			public void saveRevokeapproval(Map<String,Object> paraMap,Map<String,Object> busMainMap)
			{
				//ʹ��Ȩ
				Reg_Useright reguseright=new Reg_Useright();
				//����Ȩ
				Reg_ownership reg_ownership=new Reg_ownership();		
				//ͨ���ǼǱ�Ż�ȡ�Ǽǵ�Ԫ����������
				Map<String , Object> relationMap= getRegUnitRelMapByRegId(paraMap);
				if(relationMap.get("REG_UNIT_TYPE").toString().equals(WbfConstants.PARCEL))
				{
					//��ȡ�Ǽǲ�����Ч������      ����и�Ϊ��Ч   ����������
					Reg_Useright effReguseright = getEffectiveReg_userightByRegUnitCode(relationMap.get("REG_UNIT_CODE").toString());
					if(effReguseright != null){
						effReguseright.setEffective(WbfConstants.UNEFFECTIVE);
						reg_UserightDao.update(effReguseright);
					}
				}
				else
				{
					String proc_id =  busMainMap.get("PROC_ID").toString();
					Reg_ownership effReg_Ownership = getEffectiveReg_OwnershipByProcId(proc_id);
					if(effReg_Ownership != null){
						effReg_Ownership.setEffective(WbfConstants.UNEFFECTIVE);
						reg_ownershipDao.update(effReg_Ownership);
					}	
				}
			}
			
			
			
			/**
			 * 
			 * saveDemurrerbook:(����Ǽǲ����飬�ǲ�). <br/>
			 * @author xuzz
			 * @param allbookInfoMap
			 * @param paraMap
			 * @param reg_baseInfo
			 * @param busMainMap
			 * @param certificateDB
			 * @param cer_no  Reg_Demurrer reg_DemurrerDao
			 * @since JDK 1.6
			 */
			@Transactional
			public void saveDemurrerbook(Map<String,Object> allbookInfoMap,Reg_baseInfo reg_baseInfo,Map<String,Object> paraMap,Map<String,Object> busMainMap,Map<String,Object> regUnitMap)
			{
				Reg_Demurrer demurrer=new Reg_Demurrer();

				/*List<Map<String,Object>> all = getBKcerno(paraMap);
				if(all.size()>0)
				{
					for(Map tempMap:all){*/
				//��ȡ��ǰ�Ǽǵ�Ԫ��Ч�� ���ز�֤��		
				String cerNo = null;
				try {
					cerNo = getEffectiveCerNoByRegUnitCode(reg_baseInfo
							.getReg_unit_code());
				} catch (Exception e) {
					LogUtil.error("RegisterFacade.save_mortgagebook ��ȡ��Ч���ز�֤�ų���"+e.getMessage());
				}
				demurrer.setDemurrer_id(reg_DemurrerDao.getSeqId());
				demurrer.setReg_code(paraMap.get("reg_code").toString());
				if(allbookInfoMap!=null)
				{
					demurrer.setDiss_item(allbookInfoMap.get("DISS_ITEM").toString());
				}
				demurrer.setRecorder(paraMap.get("recorder").toString());
				demurrer.setBook_code(reg_baseInfo.getBook_code());
				demurrer.setReg_date(new Date());
				demurrer.setCer_no(cerNo);
				demurrer.setPre_reg_code(getPreRegCodeByRegId(paraMap));
				demurrer.setEffective(WbfConstants.EFFECTIVE);
				demurrer.setProcdef_id(busMainMap.get("PRO_DEF_ID").toString());	
				try {
					updateRegUnitState(paraMap);
					reg_DemurrerDao.save(demurrer);
					if(!busMainMap.get("PRO_DEF_ID").toString().equals(WbfConstants.HDEMURRER)){
						updateBkDemurrerState(regUnitMap.get("LAST_REG_CODE").toString(),regUnitMap.get("REG_UNIT_CODE").toString(),WbfConstants.UNEFFECTIVE);
					}
				} catch (Exception e) {
					e.printStackTrace();
					throw new BusinessException("��������ǼǵǼǲ���Ϣ����"+e.getMessage());
				}		
					//}
				//}
			}
			
			
			
			
			/**
			 * 
			 * saveArrachbook:(����Ǽǲ���⣬�ǲ�). <br/>
			 * @author xuzz
			 * @param allbookInfoMap
			 * @param paraMap
			 * @param reg_baseInfo
			 * @param busMainMap
			 * @param certificateDB
			 * @param cer_no  Reg_Distrain reg_DistrainDao
			 * @since JDK 1.6
			 */
			@Transactional
			public void saveArrachbook(Map<String,Object> allbookInfoMap,Reg_baseInfo reg_baseInfo,Map<String,Object> paraMap,Map<String,Object> busMainMap,Map<String,Object> regUnitMap)
			{
				Reg_Distrain regdistrain=new Reg_Distrain();
				
				Reg_Distrain distrain=new Reg_Distrain();
				//��ȡ��ǰ�Ǽǵ�Ԫ��Ч�� ���ز�֤��		
				String cerNo = null;
				Map resultMap =new HashMap();
				try {
					cerNo = getEffectiveCerNoByRegUnitCode(reg_baseInfo
							.getReg_unit_code());
				} 
				catch (Exception e) {
					LogUtil.error("RegisterFacade.save_mortgagebook ��ȡ��Ч���ز�֤�ų���"+e.getMessage());
				}
				try {
							regdistrain.copyProperties(regdistrain, allbookInfoMap);
							regdistrain.setDistress_id(reg_DistrainDao.getSeqId());
							regdistrain.setReg_code(paraMap.get("reg_code").toString());
							regdistrain.setRecorder(paraMap.get("recorder").toString());
							regdistrain.setBook_code(reg_baseInfo.getBook_code());
							regdistrain.setReg_date(new Date());
							regdistrain.setExcursus(allbookInfoMap.get("REMARK").toString());
							regdistrain.setCer_no(cerNo);
							regdistrain.setPre_reg_code(regUnitMap.get("LAST_REG_CODE").toString());
							regdistrain.setEffective(WbfConstants.EFFECTIVE);
							regdistrain.setProcdef_id(busMainMap.get("PRO_DEF_ID").toString());	
							//����,�����ҵ����Ϊ��Ч,,���ҽ������������޸�Ϊ���
							if(allbookInfoMap.get("DIS_TYPE").toString().equals(WbfConstants.T_REATTACH))
							{
								resultMap= FacadeFactory.getApplicantFacade().getAttachByRegcode(reg_code,WbfConstants.T_REATTACH).get(0);
								distrain.copyProperties(distrain, resultMap);
								distrain.setEffective(WbfConstants.UNEFFECTIVE);
								reg_DistrainDao.update(distrain);
								regdistrain.setDis_type(WbfConstants.T_ATTACH);
								reg_DistrainDao.save(regdistrain);
								updateRegUnitState(paraMap);
							}
							//�ֺ���ת��⣬�����ҵ����Ϊ��Ч�������ֺ���ҵ���޸�Ϊ���,ֻ���£�������
							else if(allbookInfoMap.get("DIS_TYPE").toString().equals(WbfConstants.T_CHATTACH)||allbookInfoMap.get("DIS_TYPE").toString().equals(WbfConstants.T_UNCHATTACH))
							{
								Map resultAttach =new HashMap();
								Reg_Distrain distrainAttach=new Reg_Distrain();
								//�ֺ�������
								resultMap=FacadeFactory.getApplicantFacade().getAttachByRegcode(reg_code,WbfConstants.T_CHATTACH).get(0);
								distrain.copyProperties(distrain, resultMap);
								distrain.setEffective(WbfConstants.EFFECTIVE);
								distrain.setRecorder(paraMap.get("recorder").toString());
								distrain.setStart_date(new Date());
								Calendar curr = Calendar.getInstance(); 
								curr.set(Calendar.YEAR,curr.get(Calendar.YEAR)+Integer.valueOf(distrain.getDis_limit())); 
								Date date=curr.getTime();
								distrain.setEnd_date(date);
								distrain.setReg_date(new Date());
								Reg_Distrain redistrain=new Reg_Distrain();
								redistrain.copyProperties(redistrain, resultMap);
								redistrain.setDis_type(WbfConstants.T_ATTACH);
								reg_DistrainDao.update(distrain);
								//�������
								resultAttach= FacadeFactory.getApplicantFacade().getAttachByRegcode(reg_code,WbfConstants.T_REATTACH).get(0);
								distrainAttach.copyProperties(distrainAttach, resultAttach);
								distrainAttach.setEffective(WbfConstants.UNEFFECTIVE);
								//regdistrain.setDis_type(WbfConstants.T_ATTACH);
								reg_DistrainDao.update(distrainAttach);
							}
							//����ֺ��⣬�����ֺ���ҵ����Ϊ��Ч
							else if(allbookInfoMap.get("DIS_TYPE").toString().equals(WbfConstants.T_CHATTACH))
							{
								resultMap=FacadeFactory.getApplicantFacade().getAttachByRegcode(reg_code,WbfConstants.T_UNREATTACH).get(0);
								distrain.copyProperties(distrain, resultMap);
								distrain.setEffective(WbfConstants.UNEFFECTIVE);
								reg_DistrainDao.update(distrain);
								reg_DistrainDao.save(regdistrain);
								updateRegUnitState(paraMap);
							}
							else
							{
								reg_DistrainDao.save(regdistrain);
								updateRegUnitState(paraMap);
								if(!busMainMap.get("PRO_DEF_ID").toString().equals(WbfConstants.ATTACH)&&allbookInfoMap.get("DIS_TYPE").toString().equals(WbfConstants.T_UNATTACH)){
								updateBkAttachState(regUnitMap.get("LAST_REG_CODE").toString(),regUnitMap.get("REG_UNIT_CODE").toString(),WbfConstants.UNEFFECTIVE);
							}
						}
						} catch (Exception e) {
							e.printStackTrace();
							throw new BusinessException("����Ǽǲ������Ϣ����"+e.getMessage());
						}
			}
			
			/**
			 * 
			 * save_mortgagebook:�����ѺȨ�Ǽǲ���Ϣ. <br/>
			 * @author PANDA
			 * @param allbookInfoMap
			 * @param paraMap
			 * @param reg_baseInfo
			 * @param busMainMap
			 * @since JDK 1.6
			 */
			@Transactional
			public void save_mortgagebook(Map<String,Object> allbookInfoMap,Map<String,Object> paraMap,Map<String,Object> busMainMap,Reg_baseInfo regBaseInfo,Map<String,Object> regUnitMap) throws BusinessException{
				//�����ѺȨ��Ϣ����Ѻ�Ǽǲ���
				//List<Map<String,Object>> all = getBKcerno(paraMap)
				String bus_type_id = busMainMap.get("REG_TYPE").toString();		
				
				//��ȡ��ǰ�Ǽǵ�Ԫ��Ч�� ���ز�֤��		
				String cerNo = null;
				try {
					cerNo = getEffectiveCerNoByRegUnitCode(regBaseInfo
							.getReg_unit_code());
				} catch (Exception e) {
					LogUtil.error("RegisterFacade.save_mortgagebook ��ȡ��Ч���ز�֤�ų���"+e.getMessage());
				}
				
				Reg_Mortgage mort = null;
				Object tempObj = null;
			    String limit = null;
				//��ȡ��һ��ҵ��ǼǱ��
				String lastregcode = null;
				Object tmpLastRegCodeObj = regUnitMap.get("LAST_REG_CODE");
				if(tmpLastRegCodeObj!=null){
					lastregcode =tmpLastRegCodeObj.toString();
				}
				
				//�жϵ�Ѻ��ʼ�����Ƿ�ǿգ�Ϊ�գ��򲻽����κβ���
				if(allbookInfoMap.get("CREDITOR_START_DATE") != null){
				 limit = "��"+allbookInfoMap.get("CREDITOR_START_DATE").toString()+"��"+allbookInfoMap.get("CREDITOR_END_DATE").toString();
				}
				mort = new Reg_Mortgage();
				//���õǼǲ�����id
				mort.setMort_id(reg_MortgageDao.getSeqId());
				//���õǼǱ��
                mort.setReg_code(paraMap.get("reg_code").toString());
                //���õ�Ѻ����
                tempObj = allbookInfoMap.get("MORT_TYPE");
    			if(tempObj != null){
    				mort.setMort_type(tempObj.toString());
    			}
                //����һ���Ѻ
                mort.setGen_mort("");
                //������߶��Ѻ
                mort.setMax_mort("");
                //���ñ���������ծȨ����
                tempObj = allbookInfoMap.get("MORT_ASSURE_RIGHT");
    			if(tempObj != null){
    				mort.setAssure_amount(tempObj.toString());
    			}
                //���õ�����Χ
    			tempObj = allbookInfoMap.get("ASSUER_RANGE");
    			if(tempObj != null){
    				mort.setAssuer_range(tempObj.toString());
    			}             
				//����ծ����������
    			if(limit != null){
                mort.setDebt_dis_limit(limit);
    			}
                //���÷��ز�����
    			mort.setCer_no(cerNo);
				//���õ�Ѻ˳λ
    			tempObj = allbookInfoMap.get("MORT_SEQ");
    			if(tempObj != null){
    				mort.setMort_seq(tempObj.toString());
    			} 
				//���õǼ�ʱ��
				mort.setReg_date(new Date());
				//���õǲ���
				mort.setRecorder(paraMap.get("recorder").toString());
				//���õǼǲ�id
    			mort.setBook_code(regBaseInfo.getBook_code());
				//������߶�ծȨȷ����ʵ
    			tempObj = allbookInfoMap.get("MAX_AMOUNT");
    			if(tempObj != null){
    				mort.setMax_mort(tempObj.toString());
    				mort.setMax_amount(tempObj.toString());
    			} 
				//����ȷ��������ծȨ����
    			tempObj = allbookInfoMap.get("SURE_AMOUNT");
    			if(tempObj != null){
    				mort.setSure_amount(tempObj.toString());
    			} 
				//����ǰ�ǼǱ��
				mort.setPre_reg_code(lastregcode);
				//���ý����
				tempObj = allbookInfoMap.get("BORROWER");
    			if(tempObj != null){
    				mort.setBorrower(tempObj.toString());
    			} 
				//�������̶���id���Ǽ����ͣ�
    			tempObj = busMainMap.get("PRO_DEF_ID");
    			if(tempObj != null){
    				mort.setProcdef_id(tempObj.toString());
    			} 
    			//���õ�Ѻ��¼Ϊ��Ч״̬
    			//����ǵ�Ѻע����������Ч  
    			if(bus_type_id.equals(WbfConstants.MAX_MORTGAGE_CANCEL) || bus_type_id.equals(WbfConstants.MORTGAGE_CANCEL)){
    				mort.setEffective(WbfConstants.UNEFFECTIVE);
    			}else{
    				mort.setEffective(WbfConstants.EFFECTIVE);
    			}
    		
				try {
					reg_MortgageDao.save(mort);
				} catch (Exception e) {
					e.printStackTrace();
					throw new BusinessException("����Ǽǲ���ѺȨ������Ϣ����"+e.getMessage());
				}
				String reg_unit_code = regBaseInfo.getReg_unit_code();
				paraMap.put("reg_unit_code", reg_unit_code);
				//���µǼǹ������в�Ȩ״̬
				updateRegUnitState(paraMap);
				
				//����ǵ�Ѻת��  �����һ����Ѻ��¼ʧЧ
				
				if(bus_type_id.equals(WbfConstants.MORTGAGE_SHIFT) || bus_type_id.equals(WbfConstants.MORTGAGE_CHANGE) || bus_type_id.equals(WbfConstants.MAX_MORTGAGE_SHIFT) || bus_type_id.equals(WbfConstants.MAX_MORTGAGE_CHANGE)|| bus_type_id.equals(WbfConstants.MAX_CONFIRM_REG)|| bus_type_id.equals(WbfConstants.MAX_MORTGAGE_CANCEL)|| bus_type_id.equals(WbfConstants.MORTGAGE_CANCEL)){
					
					String orig_mort_reg_code = regUnitMap.get("LAST_REG_CODE").toString();
					//���õ�ǰ�Ǽǵ�ԪԸ ��Ѻ��¼״̬ʧЧ
					updateBkMortState(orig_mort_reg_code,reg_unit_code,WbfConstants.UNEFFECTIVE);
				}
			}
				
				
				
				
			
			
			/**
			 * 
			 * saveMortHoldertorel:��Ѻ�����Ǽ��У��������ݵ�Ȩ���˹�������. <br/>
			 * @author PANDA
			 * @param m
			 * @since JDK 1.6
			 */
			public void saveMortgagerToHolder(Map m){
				//���ݵǼǱ�Ų�ѯ��Ȩ���˹��������Ƿ��Ѵ�������
				List<Map<String, Object>> check = mortDao.queryMapListByKey("Register.getRelByRegcode",m);
				//��ȡ�����еĵǼǱ��
				String reg_code = m.get("reg_code").toString();
				//�����������ݣ�������¼
				if(check.isEmpty()){
					List<Map<String, Object>> regunitlist = mortDao.queryMapListByKey("Register.getRegunitList", m);
					HolderRelationship relship = null;
					Map map = null;
					//ѭ������ǼǱ�š��Ǽǲ�id��Ȩ���˹�������
					for(Map rel:regunitlist){
						relship = new HolderRelationship();
						//����Ȩ���˹�����id
						relship.setRight_rel_id(holderRelationshipDao.getSeqId());
						//���õǼǲ����
						relship.setBook_code(rel.get("BOOK_CODE").toString());
						//���õǼǱ��
						relship.setReg_code(reg_code);
						//
						relship.setEffective(WbfConstants.EFFECTIVE);
						//ִ�б������
						holderRelationshipDao.save(relship);
						m.put("type",WbfConstants.MORTGAGER);
						m.put("code",rel.get("REG_UNIT_CODE").toString());
						Holder holder = null;
						     //���ݵǼǵ�Ԫ��š�ҵ��id��Ȩ����ϵ��ȡ��������Ϣ
							List<Map<String, Object>> app = mortDao.queryMapListByKey("Register.getMortgagerFormApp", m);
							//ѭ�����������˵�Ȩ���˱���
							for(Map tempApp:app){
								//���ñ����Ѻ�����˵ķ���
								saveMortAppToHolder(tempApp,relship.getRight_rel_id());
								}													
						}	
				}			
			}
			/**
			 * 
			 * saveMortAppToHolder:ͨ�÷����������Ѻ�����˵�Ȩ���˱���. <br/>
			 * @author PANDA
			 * @param tempApp
			 * @param id
			 * @since JDK 1.6
			 */
			public void saveMortAppToHolder(Map tempApp,String id){
				Holder   holder = new Holder();
				//����Ȩ��������id
				holder.setHolder_id(holderDao.getSeqId());
				//����Ȩ���˹�����id
				holder.setRight_rel_id(id);
				//����Ȩ��������
				holder.setHol_type(tempApp.get("APP_TYPE").toString());
				//����Ȩ��������
				holder.setHol_name(tempApp.get("APP_NAME").toString());
				//����Ȩ����֤������
				holder.setHol_cer_type(tempApp.get("APP_CER_TYPE").toString());
				//����Ȩ����֤�����
				holder.setHol_cer_no(tempApp.get("APP_CER_NO").toString());
				//����Ȩ���˵�ַ
				holder.setHol_address(tempApp.get("APP_ADDRESS").toString());
				//����Ȩ���˹�ϵ
				holder.setHol_rel(tempApp.get("HOL_REL").toString());
				//���淨������
				Object legalObject = tempApp.get("LEGAL_NAME");//.toString() 
				if(legalObject!=null){
					holder.setLegal_name(legalObject.toString());
				}
				//�������������
				Object agent_name = tempApp.get("AGENT_NAME");
				if(agent_name != null){
					holder.setAgent_name(agent_name.toString());
				}
				//���������֤������
				Object agent_cer = tempApp.get("AGENT_CER");
				if(agent_cer != null){
					holder.setAgent_cer(agent_cer.toString());
				}
				//��������˵绰
				Object agent_tel = tempApp.get("AGENT_TEL");
				if(agent_tel != null){
					holder.setAgent_tel(agent_tel.toString());
				}
				//���������֤������
				Object agent_cer_type = tempApp.get("AGENT_CER_TYPE");
				if(agent_cer_type != null){
					holder.setAgent_cer_type(agent_cer_type.toString());
				}
				//����ݶ�
				Object portion = tempApp.get("APP_PORT");
				if(portion != null){
					holder.setPortion(portion.toString());
				}						
				holderDao.save(holder);
			}
			/**
			 * 
			 * saveMortgageeToHolder:�����ѺȨ�˵�Ȩ���˱���. <br/>
			 * TODO(����������������������� �C ��ѡ).<br/>
			 * @author PANDA
			 * @param m
			 * @since JDK 1.6
			 */
			public void saveMortgageeToHolder(Map m){
				//���ݵǼǱ�Ų�ѯ��Ȩ���˹��������Ƿ��Ѵ�������
				List<Map<String, Object>> check = mortDao.queryMapListByKey("Register.getRelByRegcode",m);
				//��ȡ�����еĵǼǱ��
				String reg_code = m.get("reg_code").toString();
				//�����������ݣ�������¼
				if(!check.isEmpty()){
					//
					List<Map<String, Object>> app = mortDao.queryMapListByKey("Register.getMortgageeFormApp", m);
					for(Map rel:check){
						      
						      m.put("type",WbfConstants.MORTGAGEE);
						     //���ݵǼǵ�Ԫ��š�ҵ��id��Ȩ����ϵ��ȡ��������Ϣ
							
							//ѭ�����������˵�Ȩ���˱���
							for(Map tempApp:app){
								//���ñ����Ѻ�����˵ķ���
								saveMortAppToHolder(tempApp,rel.get("RIGHT_REL_ID").toString());
								}													
						}	
				}			
			}
		
			
			/**
			 * 
			 * getBKcerno:�ӵǼǲ��У���ȡ���ز�֤�ź͵Ǽǲ����. <br/>
			 * @author PANDA
			 * @param m
			 * @return
			 * @since JDK 1.6
			 */
			public List<Map<String,Object>> getBKcerno(Map m){
				//����ҵ��id�ӵǼǵ�Ԫ�������л�ȡ�Ǽǵ�Ԫ��ţ��Ǽǵ�Ԫ����
				Map macon = new HashMap();
				macon.put("id", m.get("bus_id").toString());
				List<Map<String, Object>> regunitlist = mortDao.queryMapListByKey("Mortgage.getRegunitList", macon);
				List<Map<String, Object>> regesate = new ArrayList<Map<String, Object>>();
				Map resultList = null;	
				//��������
				String sql=" and EFFECTIVE='"+WbfConstants.EFFECTIVE+"'";
				for(Map map:regunitlist){
					String type = map.get("REG_UNIT_TYPE").toString();
					String code = map.get("REG_UNIT_CODE").toString();
					macon.put("code",code);
					//�жϵ�ǰ�Ǽǵ�Ԫ����Ϊ����
					if(type.trim().equals(WbfConstants.REG_UNIT_HOUSE)){
						
						resultList = mortDao.queryMapByKey("Register.getBkOwnershipList",sql, macon); 
						
					}
					//�жϵ�ǰ�Ǽǵ�Ԫ����Ϊ������
					if(type.trim().equals(WbfConstants.REG_UNIT_BUILDING)){
						resultList = mortDao.queryMapByKey("Register.getBuildInfo",sql, macon);
						
					}
					//�жϵ�ǰ�Ǽǵ�Ԫ����Ϊ����
					if(type.trim().equals(WbfConstants.REG_UNIT_PARCEL)){				
						resultList = mortDao.queryMapByKey("Register.getBkUserightList",sql, macon);		
						
					}
					regesate.add(resultList);			
				}
				
				return regesate;
			}
	
			/**
			 * 
			 * getPrevRegcode:���ݵ�ǰ�ǼǱ�ţ���ȡǰһ��ҵ��ĵǼǱ��. <br/>
			 * @author PANDA
			 * @param paraMap
			 * @return
			 * @since JDK 1.6
			 */
			public String getPrevRegcode(Map<String,Object> paraMap ){
				
				BusinessMain bus = businessMainDao.queryDomainBykey("Register.getPrevRegcode",paraMap);
				if(bus == null){
					return "";
				}else{
				    return bus.getReg_code();
				}
			}
			
			/**
			 * 
			 * getRelRegcode:���ݵ�ǰ�ǼǱ�ţ���ȡǰһ��ҵ��ĵǼǱ��,��ѯ�Ǽǵ�Ԫ������. <br/>
			 * @author xuzz
			 * @param paraMap
			 * @return
			 * @since JDK 1.6
			 */
			public String getRelRegcode(Map<String,Object> paraMap ){
				
				BusinessMain bus = businessMainDao.queryDomainBykey("Register.getRelByRegcode",paraMap);
				if(bus == null){
					return "";
				}else{
				    return bus.getReg_code();
				}
			}
			
			/**
			 * 
			 * saveRegister:(�Ǽǲ�����). <br/>
			 * @author xuzz
			 * @param paraMap
			 * @return
			 * @since JDK 1.6  getuserInfoByRegId
			 */
			public Map<String,Object> saveRegister(Map<String,Object> paraMap){
				reg_code = paraMap.get("reg_code").toString();
				//��ȡ�˴�ҵ������id
				String bus_class_id = getBusTypeParentIdByRegId(paraMap);
				//��ȡ�˴�ҵ������    ��ҵ������id
				String bus_class_sub_id = getBusTypeIdByRegIdMap(paraMap);
				paraMap.put("bus_class_id", bus_class_id);
				//���ص�map
				Map<String,Object> resultMap = null;
				 
				//�ж��Ƿ�ǲ�   ����Ѿ��ǲ�����  exists   ������д�÷���
				if(isRegisterSave(paraMap)){
					resultMap = new HashMap();
					resultMap.put("result", "exists");
					return resultMap;
				}
				
				//��ѯ��Ӧ�Ǽǲ���Ϣ
				Map<String,Object> allbookInfoMap = getAllbookInfoMapByRegId(paraMap,getDataTableKeyByBusTypeID(bus_class_id,""));
				Map<String,Object> regUnitRelMap = null;
				//��ѯҵ������
				Map<String,Object> busMainMap = getBusMainMapByRegId(paraMap);
				paraMap.put("bus_id", busMainMap.get("BUS_ID").toString());						//�ѵ�ǰҵ��id put��������
				//��ѯ���ز�֤����Ϣ
				
				//�Ǽǲ�������Ϣ��
				Reg_baseInfo reg_baseInfo = new Reg_baseInfo();
				//������Ӧ�Ǽǲ�
				//����Ǽǲ�������Ϣ
				//reg_baseInfo= save_Baseinfo(regUnitRelMap,busMainMap);
				//�жϲ����浽��Ӧ�Ǽǲ�
				if( bus_class_id.equals(WbfConstants.HOUSE_ONWERSHIP)){
					Certificate certificateDB = certificateDao.get("where bus_id=(select bus_id from bus_main where reg_code = :reg_code)", paraMap);
					//���ɷ��ز�֤��  �����浽���ز�֤����
					String cer_no= save_cerno(paraMap);
					//����Ǳ���Ǽ�  ���ȱ�������Ϣ
					//regUnitRelMap = getRegUnitRelMapByRegId(paraMap);
					List<Map<String,Object>> regUnitRelMapList = reg_relationshipDao.queryMapListByKey("Register.getRegUnitRelMapByRegId", paraMap);
					//����Ǹ����Ǽ�
					if(bus_class_sub_id.equals(WbfConstants.HOUSE_ONWERSHIP_CHANGE)){
						saveHouseChange(busMainMap);
					}
					//����Ƕ���ת�ƵǼ�  �����¥����Ѻ   ���뷿�ز�֤�ŵ�¥����Ѻ��¼��     --�����Ԥ���ҵ��  ���淿�ز�֤�ŵ�����¼��
					else if(bus_class_sub_id.equals(WbfConstants.HOUSE_ONWERSHIP_SEC)){
						String reg_unit_code = regUnitRelMap.get("REG_UNIT_CODE").toString();
						List<Reg_Mortgage> mortgageList = getEffectiveReg_MortgageListByRegUnitCode(reg_unit_code);
						List<Reg_Distrain> distrainList = getEffectiveReg_DistrainListByRegUnitCode(reg_unit_code);
						//����¥����Ѻ��¼   ����¥����Ѻ����
						if(mortgageList!=null && mortgageList.size()>0){
							for(Reg_Mortgage mortgage:mortgageList){
								mortgage.setCer_no(cer_no);
								reg_MortgageDao.update(mortgage);
							}
						}
						//����Ԥ����¼ ���淿�ز�֤��  ������¼��
						if(distrainList!=null && distrainList.size()>0){
							for(Reg_Distrain distrain:distrainList){
								distrain.setCer_no(cer_no);
								distrain.setExcursus("����ת�� Ԥ���ת���");
								reg_DistrainDao.update(distrain);
							}
						}
					}
					allbookInfoMap = getAllbookInfoMapByRegId(paraMap,getDataTableKeyByBusTypeID(bus_class_id,""));
					//���淿�ز�֤��
					
					if(regUnitRelMapList!=null && !regUnitRelMapList.isEmpty()){
						//ѭ������ǲ���Ϣ  Ȩ����
						for(Map<String,Object> tempRegUnitRelMap:regUnitRelMapList){
							reg_baseInfo= save_Baseinfo(tempRegUnitRelMap,busMainMap);
							saveOwnshipBook(allbookInfoMap, paraMap,reg_baseInfo,busMainMap,certificateDB,cer_no);
						}
					}
					
					//����Ȩ���˼����Լ�Ȩ������Ϣ
					saveHolder(paraMap,reg_baseInfo);
				}else if (bus_class_id.equals(WbfConstants.LAND_USERIGHT)){
					Certificate certificateDB = certificateDao.get("where bus_id=(select bus_id from bus_main where reg_code = :reg_code)", paraMap);
					regUnitRelMap = getRegUnitRelMapByRegId(paraMap);
					//����Ǽǲ�������Ϣ
					reg_baseInfo= save_Baseinfo(regUnitRelMap,busMainMap);
					if(bus_class_sub_id.equals(WbfConstants.LAND_CHANGE)){
						saveParcelChange(busMainMap);
					}
					allbookInfoMap = getAllbookInfoMapByRegId(paraMap,getDataTableKeyByBusTypeID(bus_class_id,""));
					//���淿�ز�֤��
					String cer_no= save_cerno(paraMap);
					//reg_baseInfo= save_Baseinfo(regUnitRelMap,busMainMap);
					saveUserightbook(allbookInfoMap, paraMap,reg_baseInfo,busMainMap,certificateDB,cer_no);
					//����Ȩ���˼����Լ�Ȩ������Ϣ
					saveHolder(paraMap,reg_baseInfo);
				}else if(bus_class_id.equals(WbfConstants.MORTGAGE_RIGHT)){
					List<Map<String,Object>> regUnitRelMapList = reg_relationshipDao.queryMapListByKey("Register.getRegUnitRelMapByRegId", paraMap);
					
					if(regUnitRelMapList!=null && !regUnitRelMapList.isEmpty()){
						//ѭ������ǲ���Ϣ  Ȩ����
						for(Map<String,Object> tempRegUnitRelMap:regUnitRelMapList){
							reg_baseInfo= save_Baseinfo(tempRegUnitRelMap,busMainMap);
							save_mortgagebook(allbookInfoMap, paraMap, busMainMap,reg_baseInfo,tempRegUnitRelMap);
							if(!(bus_class_sub_id.equals(WbfConstants.MORTGAGE_CANCEL) || bus_class_sub_id.equals(WbfConstants.MAX_MORTGAGE_CANCEL))){
								saveHolder(paraMap,reg_baseInfo);
							}
						}
					}else{
						LogUtil.error("RegisterFacade.saveRegiser ��ȡ�Ǽǵ�Ԫ����������ʧ�ܣ�����Ϊ��");
					}
					//�����Ѻ��¼���Ǽǲ�
					//save_mortgagebook(allbookInfoMap, paraMap, busMainMap)
					
					//�����Ѻ����Ϣ��Ȩ���˱���
					//saveMortgagerToHolder(paraMap);
					//�����ѺȨ����Ϣ��Ȩ���˱���
					//saveMortgageeToHolder(paraMap);
				}else if(bus_class_id.equals(WbfConstants.ATTACH_DISTRAIN)){
					List<Map<String,Object>> regUnitRelMapList = reg_relationshipDao.queryMapListByKey("Register.getRegUnitRelMapByRegId", paraMap);
					for(Map<String,Object> tempRegUnitRelMap:regUnitRelMapList){
						reg_baseInfo= save_Baseinfo(tempRegUnitRelMap,busMainMap);
						saveArrachbook(allbookInfoMap, reg_baseInfo,paraMap, busMainMap,tempRegUnitRelMap);
						//saveHolder(paraMap,reg_baseInfo);
						
					}
					//regUnitRelMap = getRegUnitRelMapByRegId(paraMap);
					//reg_baseInfo= save_Baseinfo(regUnitRelMap,busMainMap);
					//saveArrachbook(allbookInfoMap,reg_baseInfo, paraMap, busMainMap,certificateDB);
				}
				else if(bus_class_id.equals(WbfConstants.DEMURRER)){
					List<Map<String,Object>> regUnitRelMapList = reg_relationshipDao.queryMapListByKey("Register.getRegUnitRelMapByRegId", paraMap);
					for(Map<String,Object> tempRegUnitRelMap:regUnitRelMapList){
						reg_baseInfo= save_Baseinfo(tempRegUnitRelMap,busMainMap);
						saveDemurrerbook(allbookInfoMap, reg_baseInfo,paraMap, busMainMap,tempRegUnitRelMap);
						saveHolder(paraMap,reg_baseInfo);
						
					}
					/*regUnitRelMap = getRegUnitRelMapByRegId(paraMap);
					reg_baseInfo= save_Baseinfo(regUnitRelMap,busMainMap);
					saveDemurrerbook(allbookInfoMap,reg_baseInfo, paraMap, busMainMap,certificateDB);
					//����Ȩ���˼����Լ�Ȩ������Ϣ
					saveHolder(paraMap,reg_baseInfo);*/
				}
				else if(bus_class_id.equals(WbfConstants.CORRECTION)){				//�����Ǽ�  �ǲ�ʱ  ���Ǽǵ�Ԫ����  �жϴ�ʹ��Ȩ��ȡ���Ǵ�����Ȩ��ȡ
					Certificate certificateDB = certificateDao.get("where bus_id=(select bus_id from bus_main where reg_code = :reg_code)", paraMap);
					regUnitRelMap = getRegUnitRelMapByRegId(paraMap);
					reg_baseInfo= save_Baseinfo(regUnitRelMap,busMainMap);
					String reg_unit_type = regUnitRelMap.get("REG_UNIT_TYPE").toString();
					if(reg_unit_type.equals(WbfConstants.REG_UNIT_HOUSE)){			//���ݸ����Ǽ�
						
						saveHouseChange(busMainMap);
						
						allbookInfoMap = getAllbookInfoMapByRegId(paraMap,getDataTableKeyByBusTypeID(bus_class_id,""));
					
						//���淿�ز�֤��
						String cer_no= save_cerno(paraMap);
						saveOwnshipBook(allbookInfoMap, paraMap,reg_baseInfo,busMainMap,certificateDB,cer_no);
						//����Ȩ���˼����Լ�Ȩ������Ϣ
						saveHolder(paraMap,reg_baseInfo);
					}else if(reg_unit_type.equals(WbfConstants.REG_UNIT_PARCEL)){	//���ظ����Ǽ�
						saveParcelChange(busMainMap);
						allbookInfoMap = getAllbookInfoMapByRegId(paraMap,getDataTableKeyByBusTypeID(bus_class_id,""));
						//���淿�ز�֤��
						String cer_no= save_cerno(paraMap);
						saveUserightbook(allbookInfoMap, paraMap,reg_baseInfo,busMainMap,certificateDB,cer_no);
						//����Ȩ���˼����Լ�Ȩ������Ϣ
						saveHolder(paraMap,reg_baseInfo);
					}
				}else if(bus_class_id.equals(WbfConstants.REISSUE)){				//�����Ǽ�  �ǲ�ʱ  ���Ǽǵ�Ԫ����  �жϴ�ʹ��Ȩ��ȡ���Ǵ�����Ȩ��ȡ
					Certificate certificateDB = certificateDao.get("where bus_id=(select bus_id from bus_main where reg_code = :reg_code)", paraMap);
					regUnitRelMap = getRegUnitRelMapByRegId(paraMap);
					reg_baseInfo= save_Baseinfo(regUnitRelMap,busMainMap);
					String reg_unit_type = regUnitRelMap.get("REG_UNIT_TYPE").toString();
					if(reg_unit_type.equals(WbfConstants.REG_UNIT_HOUSE)){			//���ݸ����Ǽ�
						
						allbookInfoMap = getAllbookInfoMapByRegId(paraMap,getDataTableKeyByBusTypeID(bus_class_id,""));
						//���淿�ز�֤��
						String cer_no= save_cerno(paraMap);
						saveOwnshipBook(allbookInfoMap, paraMap,reg_baseInfo,busMainMap,certificateDB,cer_no);
						//����Ȩ���˼����Լ�Ȩ������Ϣ
						saveHolder(paraMap,reg_baseInfo);
					}else if(reg_unit_type.equals(WbfConstants.REG_UNIT_PARCEL)){	//���ظ����Ǽ�
						allbookInfoMap = getAllbookInfoMapByRegId(paraMap,getDataTableKeyByBusTypeID(bus_class_id,""));
						//���淿�ز�֤��
						String cer_no= save_cerno(paraMap);
						saveUserightbook(allbookInfoMap, paraMap,reg_baseInfo,busMainMap,certificateDB,cer_no);
						//����Ȩ���˼����Լ�Ȩ������Ϣ
						saveHolder(paraMap,reg_baseInfo);
					}
				}
				else if(bus_class_id.equals(WbfConstants.REALESTATE_CAN))
				{
					regUnitRelMap = getRegUnitRelMapByRegId(paraMap);
					reg_baseInfo= save_Baseinfo(regUnitRelMap,busMainMap);
					save_realstateCan(allbookInfoMap, paraMap,reg_baseInfo,busMainMap);
				}
				else if(bus_class_id.equals(WbfConstants.REVOKEAPPROVAL1))
				{
					regUnitRelMap = getRegUnitRelMapByRegId(paraMap);
					//reg_baseInfo= save_Baseinfo(regUnitRelMap,busMainMap);
					saveRevokeapproval(paraMap,busMainMap);
				}
				resultMap = new HashMap<String,Object>();
				resultMap.put("result", "success");
				return resultMap;
			}
			
			/**
			 * 
			 * registerSaveOwnerShip:(�Ǽǲ���������Ȩ����).
			 *
			 * @author Joyon
			 * @param paraMap
			 * @return
			 * @since JDK 1.6
			 */
			private Map<String,Object> registerSaveOwnerShip(Map<String,Object> paraMap){
				Map<String,Object> resultMap = null;											//����ֵ 
				String regType = getRegTypeByRegCode(paraMap);
				if(regType == null || regType.equals("")){
					throw new BusinessException("�Ǽ�����Ϊ��");
				}
				if(regType.equals("��������Ȩ��ʼ�Ǽ�")){
					resultMap = registerSaveOwnerShipInit(paraMap);
				}else if(regType.equals("��������Ȩ����Ǽ�")){
					
				}else if(regType.equals("��������Ȩ����ת�ƵǼ�")){
					resultMap = registerSaveOwnerShipChange(paraMap);
				}else if(regType.equals("��������Ȩ����ת�ƵǼ�")){
					resultMap = registerSaveOwnerShipChange(paraMap);
				}else if(regType.equals("��������Ȩע���Ǽ�")){
					
				}
				
				return resultMap;
			}
			
			public Map<String,Object> getParcelMapByHouseId(String house_id){
				Map<String,Object>  paraMap = new HashMap<String,Object>();
				paraMap.put("house_id", house_id);
				
				return houseDao.queryMapByKey("Register.getParcelMapByHouseId", paraMap);
			}
		
			/**
			 * ��������Ȩ��ʼ�Ǽ� �ǲ�
			 */
			private Map<String,Object> registerSaveOwnerShipInit(Map<String,Object> paraMap){
				Map<String,Object> ownershipInfoMap = getOwnershipInfoMapByRegId(paraMap);
				Map<String,Object> regUnitRelMap = getRegUnitRelMapByRegId(paraMap);
				Map<String,Object> busMainMap = getBusMainMapByRegId(paraMap);
				Certificate certificateDB = certificateDao.get("where bus_id=(select bus_id from bus_main where reg_code = :reg_code)", paraMap);
				//����Ǽǲ�������Ϣ
				Reg_baseInfo reg_baseInfo = new Reg_baseInfo();
				reg_baseInfo= save_Baseinfo(regUnitRelMap,busMainMap);
				
				String cer_no= save_cerno(paraMap);
				saveOwnshipBook(ownershipInfoMap, paraMap,reg_baseInfo,busMainMap,certificateDB,cer_no);
				saveHolder(paraMap,reg_baseInfo);
				Map<String,Object> resultMap = new HashMap<String,Object>();
				resultMap.put("result", "success");
				return resultMap;
			}
			
			/**
			 * 
			 * getAllbookInfoMapByRegId:(ͨ���ǼǱ�Ż�ȡ�Ǽ���Ϣ������).
			 *
			 * @author xuzz
			 * @param paraMap
			 * @return
			 * @since JDK 1.6
			 */
			public Map<String,Object> getAllbookInfoMapByRegId(Map<String,Object> paraMap,String key){
				Map<String,Object> resultMap = null;
				try {
					resultMap = dictItemDao.queryMapByKey(key, paraMap);
					if(resultMap == null || resultMap.isEmpty()){
						//throw new BusinessException("��ȡ�Ǽ���ϢMap���� �Ǽ���ϢMap����Ϊ��");
					}
				} catch (Exception e) {
					e.printStackTrace();
					throw new BusinessException("��ȡ�Ǽ���ϢMap����"+e.getMessage());
				}
				return resultMap;
			}
			
			
		
			/**
			 * 
			 * getOwnershipInfoByRegId:(ͨ���ǼǱ�Ż�ȡ����Ȩ�Ǽ���Ϣ������).
			 *
			 * @author Joyon
			 * @param paraMap
			 * @return
			 * @since JDK 1.6
			 */
			public Map<String,Object> getOwnershipInfoMapByRegId(Map<String,Object> paraMap){
				Map<String,Object> resultMap = null;
				try {
					resultMap = dictItemDao.queryMapByKey("Register.getOwnershipInfoByRegId", paraMap);
					if(resultMap == null || resultMap.isEmpty()){
						throw new BusinessException("��ȡ����Ȩ�Ǽ���ϢMap���� ����Ȩ�Ǽ���ϢMap����Ϊ��");
					}
				} catch (Exception e) {
					e.printStackTrace();
					throw new BusinessException("��ȡ����Ȩ�Ǽ���ϢMap����"+e.getMessage());
				}
				return resultMap;
			}
			
			/**
			 * 
			 * getRegUnitRelMapByRegId:(ͨ����ǰ�ǼǱ�� �Ǽǵ�Ԫ������Map).
			 *
			 * @author Joyon
			 * @param paraMap
			 * @return
			 * @since JDK 1.6
			 */
			public Map<String,Object> getRegUnitRelMapByRegId(Map<String,Object> paraMap){
				String whereSql = "";
				if(paraMap.get("reg_unit_code")!=null){
					whereSql =" and reg_unit_code=:reg_unit_code";
				}
				
				Map<String,Object> resultMap = null;
				try {
					resultMap = dictItemDao.queryMapByKey("Register.getRegUnitRelMapByRegId",whereSql, paraMap);
					if(resultMap == null || resultMap.isEmpty()){
						LogUtil.error("��ȡ�Ǽǵ�Ԫ������Map���� �Ǽǵ�Ԫ������Map����Ϊ��");
						throw new BusinessException("��ȡ�Ǽǵ�Ԫ������Map���� �Ǽǵ�Ԫ������Map����Ϊ��");
					}
				} catch (Exception e) {
						LogUtil.error("��ȡ�Ǽǵ�Ԫ���������"+e.getMessage());
				}
				return resultMap;
			}
			
			/**
			 * 
			 * getBusMainMapByRegId:(ͨ����ǰ�ǼǱ�� ҵ������Map). 
			 *
			 * @author Joyon
			 * @param paraMap
			 * @return
			 * @since JDK 1.6
			 */
			public Map<String,Object> getBusMainMapByRegId(Map<String,Object> paraMap){
				Map<String,Object> resultMap = null;
				try {
					resultMap = dictItemDao.queryMapByKey("Register.getBusMainMapByRegId", paraMap);
					if(resultMap == null || resultMap.isEmpty()){
						throw new BusinessException("��ȡҵ������Map���� ҵ������Map������Ϊ��");
					}
				} catch (Exception e) {
					e.printStackTrace();
					throw new BusinessException("��ȡҵ������Map����"+e.getMessage());
				}
				return resultMap;
			}
			/**
			 * ��ȡǰһ��ҵ��ǼǱ�ţ���ѯ�Ǽǵ�Ԫ������
			 * @param paraMap
			 * @return
			 */
			public String getPreRegCodeByRegId(Map paraMap){
				Map<String,Object> resultMap = null;
				try {
					resultMap = dictItemDao.queryMapByKey("Register.getPreRegCodeByRegId", paraMap);
					if(resultMap == null || resultMap.isEmpty()){
						return "";
					}
				} catch (Exception e) {
					e.printStackTrace();
					throw new BusinessException("��ȡǰ�ǼǱ�ų���"+e.getMessage());
				}
				return resultMap.get("LAST_REG_CODE").toString();
			}
			
			private String getHisHolderId(Map<String, Object> paraMap) {
		
				
				Map<String,Object> resultMap = null;
				try {
					resultMap = dictItemDao.queryMapByKey("Register.getHisHolderId", paraMap);
					if(resultMap == null || resultMap.isEmpty()){
						return "";
					}
				} catch (Exception e) {
					e.printStackTrace();
					throw new BusinessException("��ȡǰȨ���˱�ų���"+e.getMessage());
				}
				return resultMap.get("HOLDER_ID").toString();
			}
			
		/*****  ����Ȩ����ת�ƵǼ�  �ǲ�   ******/
			private Map<String,Object> registerSaveOwnerShipChange(Map<String, Object> paraMap){
				
				Map<String,Object> ownershipInfoMap = getOwnershipInfoMapByRegId(paraMap);
				Map<String,Object> regUnitRelMap = getRegUnitRelMapByRegId(paraMap);
				Map<String,Object> busMainMap = getBusMainMapByRegId(paraMap);
				
				//����Ǽǲ�������Ϣ
				Reg_baseInfo reg_baseInfo = new Reg_baseInfo();
				reg_baseInfo.setBook_code(String.valueOf(reg_baseInfoDao.getSeqId()));
				reg_baseInfo.setHouse_id(regUnitRelMap.get("HOUSE_ID").toString());
				//reg_baseInfo.setParcel_id(regUnitRelMap.get("PARCEL_ID").toString());
				reg_baseInfo.setReg_unit_type(regUnitRelMap.get("REG_UNIT_TYPE").toString());
				reg_baseInfo.setReg_unit_code(regUnitRelMap.get("REG_UNIT_CODE").toString());
				reg_baseInfo.setReg_organization(busMainMap.get("REG_STATION").toString());
				try {
					reg_baseInfoDao.save(reg_baseInfo);
				} catch (Exception e) {
					e.printStackTrace();
					throw new BusinessException("����Ǽǲ�������Ϣ����"+e.getMessage());
				}
				
				//�ǲ�ʱ���ɷ��ز�֤��  �����浽���ز�֤����
				Row row = new RowImpl();
				row.put("name", "���ز�֤��");
				String cer_no = FacadeFactory.getIdentifierFacade().getSerialNumber(row);
				if(cer_no != null && cer_no != ""){
					Certificate certificate = new Certificate();
					try {
						String cer_id = certificateDao.queryMapByKey("Register.getCertificateMapByRegCode",paraMap).get("CERTIFICATE_ID").toString();
						if(cer_id == null || cer_id == ""){
							throw new BusinessException("��ȡ���ز�֤��ID ����  IDΪ��");
						}
						certificate.setCertificate_id(cer_id);
						certificate = certificateDao.get(certificate);
						certificate.setCertificate_code(cer_no);
						certificateDao.update(certificate);
					} catch (Exception e) {
						e.printStackTrace();
						throw new BusinessException("��ȡ���ز�֤��ID ����  "+e.getMessage());
					}
					
				}
			 
				//����Ǽǲ�����Ȩ������Ϣ
				Reg_ownership   reg_ownership = new Reg_ownership();
				reg_ownership.setOwnership_id(String.valueOf(reg_ownershipDao.getSeqId()));
				reg_ownership.setReg_date(new Date());
				reg_ownership.setReg_code(paraMap.get("reg_code").toString());
				reg_ownership.setBook_code(reg_baseInfo.getBook_code());
				reg_ownership.setRecorder(paraMap.get("recorder").toString());
				reg_ownership.setReg_value(ownershipInfoMap.get("REG_VALUE").toString());
				reg_ownership.setProcdef_id(busMainMap.get("PRO_DEF_ID").toString());
				reg_ownership.setExcursus(ownershipInfoMap.get("EXCURSUS").toString());
				reg_ownership.setCer_no(cer_no);
				
				reg_ownership.setGet_mode(ownershipInfoMap.get("GET_MODE").toString());
				reg_ownership.setLu_term(ownershipInfoMap.get("LU_TERM").toString());
				reg_ownership.setStart_date((Date)ownershipInfoMap.get("START_DATE"));
				reg_ownership.setEnd_date((Date)ownershipInfoMap.get("END_DATE"));
				reg_ownership.setHouse_usage(ownershipInfoMap.get("HOUSE_USAGE").toString());
				reg_ownership.setHouse_attr(ownershipInfoMap.get("HOUSE_ATTR").toString());
				//reg_ownership.setPre_reg_code(getPreRegCodeByRegId(paraMap));						//��ʼ�Ǽ�  ��ǰ�ǼǱ��
				reg_ownership.setPre_reg_code(getPreRegCodeByRegId(paraMap));
				try {
					reg_ownershipDao.save(reg_ownership);
				} catch (Exception e) {
					e.printStackTrace();
					throw new BusinessException("����Ǽǲ�����Ȩ������Ϣ����"+e.getMessage());
				}
				
				
				//����Ȩ���˼�����Ϣ
				HolderRelationship holderRelationship = new HolderRelationship();
				holderRelationship.setRight_rel_id(String.valueOf(holderRelationshipDao.getSeqId()));
				holderRelationship.setBook_code(reg_baseInfo.getBook_code());
				holderRelationship.setReg_code(paraMap.get("reg_code").toString());
				try {
					holderRelationshipDao.save(holderRelationship);
				} catch (Exception e) {
					e.printStackTrace();
					throw new BusinessException("���汣��Ȩ���˼�����Ϣ����"+e.getMessage());
				}
				
				
				//����Ȩ������Ϣ
				//String his_holder_id = null;		//ǰȨ����ID
				//int temp_count = 0;					//ѭ������  ������ȡȨ����ǰID  ��һ�δ����ݿ���ȡ   ����Ķ���ǰ��һ����ȡ
				List<Map<String,Object>> appList = getAplPerInfoByRegId(paraMap);					//���Ȩ����  �����浽Ȩ���˱�
				Holder holder = null;
				
				try {
					
					for (Map<String,Object> tempApp : appList) {
							//if(temp_count == 0){
							//	his_holder_id = getHisHolderId(paraMap);
							//}
							holder = new Holder();
							holder.setHolder_id(String.valueOf(holderDao.getSeqId()));
							holder.setRight_rel_id(holderRelationship.getRight_rel_id());
							holder.setHol_type(tempApp.get("APP_TYPE").toString());
							holder.setHol_name(tempApp.get("APP_NAME").toString());
							holder.setHol_cer_type(tempApp.get("APP_CER_TYPE").toString());
							holder.setHol_cer_no(tempApp.get("APP_CER_NO").toString());
							holder.setHol_address(tempApp.get("APP_ADDRESS").toString());
							//holder.setDepart_type(tempApp.get("DEPART_TYPE").toString());		//��ʱ�����浥λ����
							Object legalObject = tempApp.get("LEGAL_NAME");//.toString()
							
							//�ж�  �������Ϊ�� �򲻱��淨��    
							if(legalObject!=null){
								holder.setLegal_name(legalObject.toString());
							}
							
							//holder.setLegal_cer(tempApp.get("LEGAL_CER").toString());			//�����˱���  �޷������֤
							Object agent_name = tempApp.get("AGENT_NAME");
							if(agent_name != null){
								holder.setAgent_name(agent_name.toString());
							}
							
							Object agent_cer = tempApp.get("AGENT_CER");
							if(agent_cer != null){
								holder.setAgent_cer(agent_cer.toString());
							}
							
							Object agent_tel = tempApp.get("AGENT_TEL");
							if(agent_tel != null){
								holder.setAgent_tel(agent_tel.toString());
							}
							
							Object portion = tempApp.get("APP_PORT");
							if(portion != null){
								holder.setPortion(portion.toString());
							}
							
							//holder.setAgent_cer(tempApp.get("AGENT_CER").toString());
							//holder.setAgent_tel(tempApp.get("AGENT_TEL").toString());
							//holder.setPortion(tempApp.get("APP_PORT").toString());
							//holder.setHis_holder_id(his_holder_id);
							
							holderDao.save(holder);
							
							//his_holder_id = holder.getHolder_id();							//��������ʷȨ����ID Ϊ��һ�α����ID	
							//temp_count++;														//��������
					}
				} catch (Exception e) {
					e.printStackTrace();
					throw new BusinessException("���汣��Ȩ������Ϣ����"+e.getMessage());
				}
				
				Map<String,Object> resultMap = new HashMap<String,Object>();
				resultMap.put("result", "success");
				return resultMap;
			}
			
			
			/**
			 * 
			 * getSecondTransferApp:(��ȡ����ת�Ƶ����÷�  ɸѡ��δת�÷ݶ��Ȩ���˺ϲ�). 
			 *
			 * @author Joyon
			 * @param paraMap
			 * @return
			 * @since JDK 1.6
			 */
			public List<Map<String,Object>> getSecondTransferApp(Map<String, Object> paraMap){
				//������÷��ݶ�Ϊ�ٷ�֮��  ��ֱ�Ӱ����÷������˷���
				//��ȡ���÷���Ϣ
				paraMap.put("app_type", "���÷�");
				List<Map<String,Object>> transfereeMapList = getAppMapListByAppTypeAndRegId(paraMap);
				
				//ת�÷���Ϣ
				paraMap.put("app_type", "ת�÷�");
				List<Map<String,Object>> transferorMapList = getAppMapListByAppTypeAndRegId(paraMap);
				if(checkTransfereePortIsHundredPercent(paraMap)){
					return transfereeMapList;
				}else{
					List<Map<String,Object>> tempHolderAsAppMapList = null;
					try {
						tempHolderAsAppMapList = houseDao.queryMapListByKey("Register.getCurBusHouseHolderAsAPPMapListByRegId",paraMap);
					} catch (Exception e) {
						e.printStackTrace();
						throw new BusinessException("��ѯ��������Ȩ����Ϣ����"+e.getMessage());
					}
					
					//ѭ��ȥ��ת�÷�   ��Ȩ���˱���
					for(int i=0;i<tempHolderAsAppMapList.size();i++){
						Map<String,Object> tempMap = tempHolderAsAppMapList.get(i);
						
						String strTempHolderCerNo = tempMap.get("APP_CER_NO").toString();					//��ȡ��ǰȨ���˵�֤�����  ������������  ת�÷���Ϣ���жԱ�   �����ͬ����մ�Ȩ���� ѭ���Ա�
						
						for(Map<String,Object> tempTransferor:transferorMapList){
							String strTempTransferorCerNo =  tempTransferor.get("APP_CER_NO").toString();  //ת�÷����֤
							//���Ȩ������ת�÷����֤���   ���list��ɾ����ǰȨ����
							if(strTempHolderCerNo.equals(strTempTransferorCerNo)){
								tempHolderAsAppMapList.remove(i);
							}
						}
					}
					
					transfereeMapList.addAll(tempHolderAsAppMapList);										//�ϲ����÷�   ��δת�÷ݶ��Ȩ����  ������
					
				}
				
				return transfereeMapList;
			}
			
			/**
			 * 
			 * getSecondTransferHolderMapListByProcId:(ת�ƵǼǻ�ȡ��ʷȨ����)
			 *
			 * @author Joyon
			 * @param proc_id
			 * @return
			 * @since JDK 1.6
			 */
			public List<Map<String,Object>> getSecondTransferHolderMapListByProcId(String proc_id){
				
				return null;
			}
			
			public List<Map<String,Object>> getHolderMapListByHouseId(String house_id){
				
				return null;
			}
			
			/**
			 * 
			 * checkTransfereePortIsHundredPercent:(������÷��ķݶ��Ƿ��ǰٷ�֮��)
			 *
			 * @author Joyon
			 * @since JDK 1.6
			 */
			private boolean checkTransfereePortIsHundredPercent(Map<String, Object> paraMap){
				boolean result = false;
				paraMap.put("app_type", "���÷�");
				List<Map<String,Object>> transfereeMapList = getAppMapListByAppTypeAndRegId(paraMap);
				int portSum = 0;
				int startIndex = 0;
				int endIndex = 0;
				
				//ѭ����ȥ���÷��ķݶ� ���������
				for(Map<String,Object> tempMap:transfereeMapList){
					String strTempPort = tempMap.get("APP_PORT").toString();
					endIndex = strTempPort.indexOf("%");
					strTempPort = strTempPort.substring(startIndex, endIndex);
					int intTempPort = Integer.valueOf(strTempPort);
					portSum+=intTempPort;
				}
				
				//�ж� ����ݶ�֮�͵���100  �򷵻�true
				if(portSum == 100){
					result = true;
				}
				return result;
			}
			
			/**
			 * 
			 * getAppMapListByAppTypeAndRegId:(���ݵǼǱ�� ���������� ��ȡ��������Ϣ). 
			 * @author Joyon
			 * @param paraMap reg_code app_type
			 * @return
			 * @since JDK 1.6
			 */
			public List<Map<String,Object>> getAppMapListByAppTypeAndRegId(Map<String, Object> paraMap){
				//paraMap.put("app_type", "���÷�");
				List<Map<String,Object>> resultList = null;
				try {
					resultList = houseDao.queryMapListByKey("Register.getTransfereeMapListByRegId", paraMap);
					if(resultList == null || resultList.isEmpty()){
						throw new BusinessException("��ȡ�ܷ���Ϣ���� ������ϢΪ��");
					}
				} catch (Exception e) {
					e.printStackTrace();
					throw new BusinessException("��ȡ�ܷ���Ϣ����"+e.getMessage());
				}
				return resultList;
			}
		/*****  ����Ȩ����ת�ƵǼǽ���    ******/
	/**********************************       ����Ȩ����  ����          *************************************/
/**********************************     ���沿��  ����        *************************************/	
	
	/**
	 * 
	 * getProcid:(��ѯҵ�������̶���ID). <br/>
	 * @author xuzz
	 * @param procid
	 * @return
	 * @since JDK 1.6
	 */
	public String getProcid(String procid)
	{
		Map map=new HashMap();
		map.put("procid", procid);
		Map m= acceptRuleDao.queryMapByKey("Register.getProcid", " where proc_id=:procid", map);
		return m.get("bus_type_id").toString();
	}
	/**
	 * ���Ǽǵ�Ԫ�Ƿ��������
	 * @author peter
	 * @return
	 */
	public Map checkBusData(String reg_unit_code,String regtype,String bustype)
	{
		bustype= FacadeFactory.getRegisterFacade().getProcid(bustype);
		Map map=new HashMap();
		Map resultMap=new HashMap();
		map.put("bustype", bustype);
		Map regcodeMap=new HashMap();
		regcodeMap.put("reg_unit_code", reg_unit_code);
		String state="true";
		String statemes="<p style='color:blue;'>��������õǼǵ�Ԫ��</p>";
		int precount=0;
		int limcount=0;
		int mescount=0;
		try
		{
			//��ѯ�õǼǵ�Ԫ�Ƿ���;
			List<Map<String,Object>> checkBusStateMap=reg_baseInfoDao.queryMapListByKey("Register.checkBusState", "and M.REG_UNIT_CODE=:reg_unit_code", regcodeMap);
			if(checkBusStateMap.size()>0)
			{
				state="false";
				statemes="<p style='color:red;'>�õǼǵ�Ԫ������;ҵ��δ�����������Ƚ�����ǰ��;ҵ��<br/></p>";
				resultMap.put("statemes", statemes);
				resultMap.put("state", state);
				return resultMap;
			}
			//---------------------------------------�������õ�������ѯ��ǰ�Ǽǵ�Ԫ�Ƿ��������
			//���ݵ�ǰҵ����ID��ѯ����������������ǰ����������ʾ�����ȡ�
			List<AcceptRule> selectRule=acceptRuleDao.queryListByKey("PreAudit.selectRule", " where bus_type_id=:bustype", map);
			for(AcceptRule acceptrule: selectRule)
			{
				String busname= getParentByProcId(acceptrule.getRule().toString()).get("BUS_NAME").toString();
				String rule=acceptrule.getRule_type().toString();
				String sql=" where  REG_CODE in (SELECT distinct(REG_CODE)  FROM BUS_REGUNITREL r WHERE     BUS_ID IN (SELECT BUS_ID   FROM BUS_MAIN b WHERE b.PRO_DEF_ID =:prodefid) AND r.REG_STATE = '053002' AND REG_UNIT_CODE =:reg_unit_code)";
				Map bookmap=new HashMap();
				bookmap.put("reg_unit_code", reg_unit_code);
				//bookmap.put("sql", sql);
				bookmap.put("prodefid", acceptrule.getRule().toString());
				Map resultBookMap= getAllBook(bookmap,sql);
				
				//List<Map<String,Object>> listMap=getRegCode(reg_unit_code,acceptrule.getRule().toString());
				//ǰ������
				if(rule.equals(WbfConstants.PREAUDIT))
				{
					
					System.out.println();
					if(resultBookMap!=null)
					{
						
					}
					else
					{
						state="false";
						statemes="<p style='color:red;'>ȱ��ǰ��������<br/>"+busname+"</p>";
						break;
					}
				}
				//��������
				else if(rule.equals(WbfConstants.LIMIT))
				{
					System.out.println();
					if(resultBookMap==null)
					{
						
					}
					else
					{
						state="false";
						statemes="<p style='color:red;'>��������������<br/>"+busname+"</p>";
						break;
					}
				}
				//��ʾ����
				else if(rule.equals(WbfConstants.MESSAGE))
				{
					System.out.println();
					
					if(mescount==0)
					{
						statemes+="<p style='color:red;'>������ʾ��<br/>"+busname+"</p>";
					}
					else
					{
						statemes+="<p style='color:red;'>"+busname+"</p>";
					}
				}
			}
			resultMap.put("statemes", statemes);
			resultMap.put("state", state);
		
		}catch(Exception err)
		{
			err.getMessage();
		}
		return resultMap;	
	}
	
	
	
	
	/**
	 * �������̶���ID��ȡ���̶��常ID
	 * @param bus_type_id
	 * @return
	 */
	public Map getParentByProcId(String bus_type_id)
	{
		Map map=new HashMap();
		map.put("bus_type_id", bus_type_id);
		Map<String, Object> ParentMap=bus_typeDao.queryMapByKey("Register.getProcid", " where bus_type_id=:bus_type_id", map);
		if(ParentMap==null||ParentMap.isEmpty())
		{
			return null;
		}
		return ParentMap;
	}
	/**
	 * ͨ���Ǽǵ�Ԫ��ź�ҵ����ID��ȡ�ǼǱ�ż��� 
	 * @param regunitcode
	 * @param procdefid
	 * @return
	 */
	public List<Map<String,Object>> getRegCode(String regunitcode,String procdefid)
	{
		Map map=new HashMap();
		map.put("reg_unit_code", regunitcode);
		map.put("prodefid", procdefid);
		List<Map<String,Object>> listMap=bus_typeDao.queryMapListByKey("Register.getRegCode",map);
		return listMap;
	}
	
	/**
	 * ͨ���ǼǱ�Ż�ȡ��ѯ��ǰҵ����صǼǲ���Ϣ
	 * @param regcode
	 * @return
	 */
	public Map getAllBook(Map map,String appendsql)
	{
		String typeid="";
		typeid =getParentByProcId(map.get("prodefid").toString()).get("PARENT_ID").toString();
		if(typeid!=null)
		{
			Map<String, Object> registerMap = getBKInfoByRegId(getDataTableKeyByBusTypeID(typeid,"book"),appendsql,map);
			return registerMap;
		}
		return null;
	}
	
	/**
	 * 
	 * checkBus:(���ҵ�������Ƿ��������). <br/>
	 * @author xuzz
	 * @since JDK 1.6  
	 */
	/*public Map checkBusData1(String reg_code,String regtype,String bustype)
	{
		Map resultMap=new HashMap();
		Map map=new HashMap();
		map.put("bustype", bustype);
		Map regcodeMap=new HashMap();
		regcodeMap.put("regcode", reg_code);
		int count=0;
		String passage="";
		String passageState="true";
		String preaudit="";
		String preauditState="true";
		String message="";
		String limit="";
		String limitState="true";
		resultMap.put("passage", passage);
		resultMap.put("passageState", passageState);
		resultMap.put("preaudit", preaudit);
		resultMap.put("preauditState", preauditState);
		resultMap.put("limit", limit);
		resultMap.put("limitState", limitState);
		resultMap.put("message", message);
		int i=0;
		List<Map<String,Object>> checkBusStateMap=reg_baseInfoDao.queryMapListByKey("Register.checkBusState", "and M.REG_UNIT_CODE=:regcode", regcodeMap);
		if(checkBusStateMap.size()>0)
		{
			passage="�õǼǵ�Ԫ������;ҵ��δ�����������Ƚ�����ǰ��;ҵ��<br/>";
			passageState="false";
			resultMap.put("passage", passage);
			resultMap.put("passageState", passageState);	
		}
		else
		{
			Map ma=reg_baseInfoDao.queryMapByKey("Register.selectBaseInfo", " where house_id=:houseid", regcodeMap);
			if(ma!=null)
			{
				String bookcode= ma.get("BOOK_CODE").toString();
				//����ǰ������
				List<AcceptRule> listaccept= acceptRuleDao.queryListByKey("PreAudit.selectRule", " where bus_type_id=:bustype and rule_type=1", map);

				for(AcceptRule acceptrule: listaccept)
				{
				    Map parentid=selectparentid(acceptrule.getRule());
					Map m= selectAllReg(bookcode,parentid.get("PARENT_ID").toString());
					if(m!=null)
					{
						count++;
					}
					else
					{
						if(i==0)
						{
							preaudit+=parentid.get("BUS_NAME");
							i++;
						}	
						else
						{
							preaudit+=",<br/>"+parentid.get("BUS_NAME");
						}
						preaudit+=m.get("map");
						preauditState="false";
					}
				}
				if(count==listaccept.size())
				{
					preaudit="�õǼǵ�Ԫ���������ҵ��<br/>";
					preauditState="true";
				}
				resultMap.put("preaudit", preaudit);
				resultMap.put("preauditState", preauditState);	
				count=0;
				//������������
				List<AcceptRule> listacceptlimit= acceptRuleDao.queryListByKey("PreAudit.selectRule", " where bus_type_id=:bustype and rule_type=0", map);
				i=0;
				for(AcceptRule acceptrule: listacceptlimit)
				{
					//String parentid=selectparentid(acceptrule.getRule()).get("PARENT_ID").toString();
					Map parentid=selectparentid(acceptrule.getRule());
					Map m= selectAllReg(bookcode,parentid.get("PARENT_ID").toString());
					if(m!=null)
					{
						//limit+=m.get("map");
						if(i==0)
						{
							limit+=parentid.get("BUS_NAME");
							i++;
						}	
						else
						{
							limit+=",<br/>"+parentid.get("BUS_NAME");
						}
						limitState="false";
					}
					else
					{
						count++;
					}
				}
				if(count==listacceptlimit.size())
				{
					limit="�޲���¼��<br/>";
					limitState="true";
				}
				resultMap.put("limit", limit);
				resultMap.put("limitState", limitState);
				count=0;
				//������ʾ����
				List<AcceptRule> listacceptmessage= acceptRuleDao.queryListByKey("PreAudit.selectRule", " where bus_type_id=:bustype and rule_type=2", map);
				i=0;
				for(AcceptRule acceptrule: listacceptmessage)
				{
					Map parentid=selectparentid(acceptrule.getRule());
					Map m= selectAllReg(bookcode,parentid.get("PARENT_ID").toString());
					if(m!=null)
					{
						if(i==0)
						{
							message+=parentid.get("BUS_NAME");
							i++;
						}	
						else
						{
							message+=",<br/>"+parentid.get("BUS_NAME");
						}
					}
					else
					{
					}
				}
				resultMap.put("message", message);
			}	
			else
			{
				resultMap.put("limit", "�޲���¼��<br/>");
				resultMap.put("limitState", "true");
				resultMap.put("preaudit", "�õǼǵ�Ԫ���������ҵ��<br/>");
				resultMap.put("preauditState", "true");
			}
		}
		
		return resultMap;
		
	}*/
	/**
	 * ��ѯ�Ƿ���;ҵ��
	 * @param regcode
	 * @param prodefid
	 * @return
	 */
	public Map getRegUnitRel(String regcode,String prodefid)
	{
		Map map=new HashMap();
		map.put("regcode", regcode);
		map.put("procdefid", prodefid);
		return map;
		//Map resultMap=acceptRuleDao.queryMapByKey(key, whereSql, paramsObject);
	}
	
	
	/**
	 * 
	 * selectparentid:(��ȡҵ�����ͱ���Ϣ). <br/>
	 * @author xuzz
	 * @param parentid
	 * @return
	 * @since JDK 1.6
	 */
	public Map selectparentid(String bustypeid)
	{
		Map mapaccept=new HashMap();
		mapaccept.put("bustypeid", bustypeid);
		Map mapbustype=reg_baseInfoDao.queryMapByKey("Common.getProcName", " where BUS_TYPE_ID=:bustypeid", mapaccept);		
		return mapbustype; 
	}
	
	
	/**
	 * 
	 * selectAllReg:(��ѯ���еǼǲ���Ϣ). <br/>
	 * @author xuzz
	 * @return
	 * @since JDK 1.6
	 *//*
	public Map selectAllReg(String bookcode,String bookid)
	{
		Map map=new HashMap();
		Map m=new HashMap();
		map.put("bookcode", bookcode);
		//��ѯ���еǼǲ�����
		if(Integer.valueOf(bookid)==100)
		{
			List<Reg_Share> listshare=reg_ShareDao.queryListByKey("Register.getShare", " where book_code=:bookcode", map);
			if(listshare.size()>0)
			{
				for(Reg_Share regshare : listshare)
				{
					if(regshare.getPre_reg_code()!="")
					{
						Map s=new HashMap();
						s.put("regcode", regshare.getPre_reg_code());
						Map sharemap=reg_UserightDao.queryMapByKey("Register.getShare", " where Pre_reg_code=:regcode", s);
						if(sharemap!=null)
						{
							
						}
						else 
						{
							//String busname=selectparentid(regshare.getProcdef_id()).get("BUS_NAME").toString();
							m.put("map", regshare.getProcdef_id());
							return m;
						}	
					}
				}
			}
			else
			{
				return null;
			}
		}
		//��ѯʹ��Ȩ����
		else if(Integer.valueOf(bookid)==101)
		{
			List<Reg_Useright> listuseright=reg_UserightDao.queryListByKey("Register.getUseright", " where book_code=:bookcode", map);
			if(listuseright.size()>0)
			{
				for(Reg_Useright reguseright : listuseright)
				{
					if(reguseright.getPre_reg_code()!="")
					{
							Map s=new HashMap();
							s.put("regcode", reguseright.getPre_reg_code());
							Map userightemap=reg_UserightDao.queryMapByKey("Register.getUseright", " where Pre_reg_code=:regcode", s);
							if(userightemap!=null)
							{
								
							}
							else 
							{
								//String busname=selectparentid(reguseright.getProcdef_id()).get("BUS_NAME").toString();
								m.put("map", reguseright.getProcdef_id());
								return m;
							}
					}
				}
			}
			else
			{
				return null;
			}
		}
		//��ѯ����Ȩ����
		else if(Integer.valueOf(bookid).equals(WbfConstants.HOUSE_ONWERSHIP))
		{
			List<Reg_ownership> listregownership=reg_ownershipDao.queryListByKey("Register.getOwnership", " where book_code=:bookcode", map);
			if(listregownership.size()>0)
			{
				for(Reg_ownership regownership : listregownership)
				{
					if(regownership.getPre_reg_code()!="")
					{
						Map s=new HashMap();
						s.put("regcode", regownership.getReg_code());
						Map ownershipmap=reg_UserightDao.queryMapByKey("Register.getOwnership", " where Pre_reg_code=:regcode", s);
						if(ownershipmap!=null)
						{
							
						}
						else 
						{
							//String busname=selectparentid(regownership.getProcdef_id()).get("BUS_NAME").toString();
							m.put("map", regownership.getProcdef_id());
							return m;
						}
					}
					
				}
			}
			else
			{
				return null;
			}
		}
		//�Ǽǲ�������Ȩ������_��Ѻ�Ǽǣ�
		else if(Integer.valueOf(bookid).equals(WbfConstants.LAND_USERIGHT))
		{
			List<Reg_Mortgage> listmortgage=reg_MortgageDao.queryListByKey("Register.getMortgage", " where book_code=:bookcode", map);
			if(listmortgage.size()>0)
			{
				for(Reg_Mortgage regmortgage : listmortgage)
				{
					if(regmortgage.getPre_reg_code()!="")
					{
						Map s=new HashMap();
						s.put("regcode", regmortgage.getPre_reg_code());
						Map mortgagemap=reg_UserightDao.queryMapByKey("Register.getMortgage", " where Pre_reg_code=:regcode", s);
						if(mortgagemap!=null)
						{
							
						}
						else 
						{
							//String busname=selectparentid(regmortgage.getProcdef_id()).get("BUS_NAME").toString();
							m.put("map", regmortgage.getProcdef_id());
							return m;
						}
					}	
					
				}
			}
			else
			{
				return null;
			}
		}
		//�Ǽǲ�������Ȩ������_����Ȩ�Ǽǣ�
		else if(Integer.valueOf(bookid)==104)
		{
			List<Reg_Easement> listeasement=reg_EasementDao.queryListByKey("Register.getEasement", " where book_code=:bookcode", map);
			if(listeasement.size()>0)
			{
				for(Reg_Easement regeasement : listeasement)
				{
					if(regeasement.getPre_reg_code()!="")
					{
						Map s=new HashMap();
						s.put("regcode", regeasement.getPre_reg_code());
						Map easementmap=reg_UserightDao.queryMapByKey("Register.getEasement", " where Pre_reg_code=:regcode", s);
						if(easementmap!=null)
						{
							
						}
						else 
						{
							//String busname=selectparentid(regeasement.getProcdef_id()).get("BUS_NAME").toString();
							m.put("map", regeasement.getProcdef_id());
							return m;
						}
					}	
					
				}
			}
			else
			{
				return null;
			}
		}
		//�Ǽǲ�����������_Ԥ��Ǽǣ�
				else if(Integer.valueOf(bookid)==105)
				{
					List<Reg_Advnotice> listadvnotice=reg_AdvnoticeDao.queryListByKey("Register.getAdvnotice", " where book_code=:bookcode", map);
					if(listadvnotice.size()>0)
					{
						for(Reg_Advnotice regadvnotice : listadvnotice)
						{
							if(regadvnotice.getPre_reg_code()!="")
							{
								Map s=new HashMap();
								s.put("regcode", regadvnotice.getPre_reg_code());
								Map gadvnoticemap=reg_UserightDao.queryMapByKey("Register.getAdvnotice", " where Pre_reg_code=:regcode", s);
								if(gadvnoticemap!=null)
								{
									
								}
								else 
								{
									//String busname=selectparentid(regadvnotice.getProcdef_id()).get("BUS_NAME").toString();
									m.put("map", regadvnotice.getProcdef_id());
									return m;
								}
							}
						}
					}
					else
					{
						return null;
					}
				}
		//�Ǽǲ�����������_����Ǽǣ�
				else if(Integer.valueOf(bookid)==106)
				{
					List<Reg_Demurrer> listdemurrer=reg_DemurrerDao.queryListByKey("Register.getDemurrer", " where book_code=:bookcode", map);
					if(listdemurrer.size()>0)
					{
						for(Reg_Demurrer regdemurrer : listdemurrer)
						{
							if(regdemurrer.getPre_reg_code()!="")
							{
								Map s=new HashMap();
								s.put("regcode", regdemurrer.getPre_reg_code());
								Map demurrermap=reg_UserightDao.queryMapByKey("Register.getDemurrer", " where Pre_reg_code=:regcode", s);
								if(demurrermap!=null)
								{
									
								}
								else 
								{
									//String busname=selectparentid(regdemurrer.getProcdef_id()).get("BUS_NAME").toString();
									m.put("map", regdemurrer.getProcdef_id());
									return m;
								}
							}
						}
					}
					else
					{
						return null;
					}
				}
		//�Ǽǲ�����������_���Ǽǣ�
				else if(Integer.valueOf(bookid)==107)
				{
					List<Reg_Distrain> listdistrain=reg_DistrainDao.queryListByKey("Register.getDistrain", " where book_code=:bookcode", map);
					if(listdistrain.size()>0)
					{
						for(Reg_Distrain regdistrain : listdistrain)
						{
							if(regdistrain.getPre_reg_code()!="")
							{
								Map s=new HashMap();
								s.put("regcode", regdistrain.getPre_reg_code());
								Map distrainmap=reg_UserightDao.queryMapByKey("Register.getDistrain", " where Pre_reg_code=:regcode", s);
								if(distrainmap!=null)
								{
									
								}
								else 
								{
									//String busname=selectparentid(regdistrain.getProcdef_id()).get("BUS_NAME").toString();
									m.put("map", regdistrain.getProcdef_id());
									return m;
								}
							}
						}
					}
					else
					{
						return null;
					}
				}
		
		
		return null;
	}*/
	
	
	/**
	 * 
	 * isRegisterSave:(�ж��Ƿ��Ѿ��ǲ�     �Ѿ��ǲ�����true   δ�ǲ�����false  ������ֻ����д else if�е��ж�  ����).
	 *
	 * @author Joyon
	 * @param paraMap reg_code �ǼǱ��  
	 * @return
	 * @since JDK 1.6
	 */
	public boolean isRegisterSave(Map paraMap){
		//���ؽ��
		boolean result = false;
		//�ӵǼǲ��л�õ�ֵ    ����з���True   �޷���false
		Object tempObject = null;
		
		//��ҵ��id
		//String bus_class_id = paraMap.get("bus_class_id").toString();
		String bus_class_id = getBusTypeParentIdByRegId(paraMap);
		if( bus_class_id.equals(WbfConstants.HOUSE_ONWERSHIP)){
			tempObject =reg_ownershipDao.get("where reg_code = :reg_code",paraMap);
		}else if(bus_class_id.equals(WbfConstants.LAND_USERIGHT)){
			tempObject =reg_UserightDao.get("where reg_code = :reg_code",paraMap);
		}else if(bus_class_id.equals(WbfConstants.MORTGAGE_RIGHT)){
			List tmpList =reg_MortgageDao.getAll("where reg_code = :reg_code",paraMap);
			if(tmpList.isEmpty() || tmpList==null){
				return false;
			}else{
				return true;
			}
		}else if(bus_class_id.equals(WbfConstants.CORRECTION))		//�����Ǽ�   ����Ƿ� �͵�����Ȩ���в�     ����ǵ���ʹ��Ȩ�в�
		{
			
			String unit_type = getRegUnitRelMapByRegId(paraMap).get("REG_UNIT_TYPE").toString(); //reg_relationshipDao.get("where bus_id =(select bus_id from bus_main where reg_code=:reg_code)", paraMap).getReg_unit_type();
			if(unit_type.equals(WbfConstants.REG_UNIT_HOUSE)){
					tempObject =reg_ownershipDao.get("where reg_code = :reg_code",paraMap);
			}else if(unit_type.equals(WbfConstants.REG_UNIT_PARCEL)){
				tempObject =reg_UserightDao.get("where reg_code = :reg_code",paraMap);
			}
				
		}
		else if(bus_class_id.equals(WbfConstants.REALESTATE_CAN))		//ע���Ǽ�   ����Ƿ� �͵�����Ȩ���в�     ����ǵ���ʹ��Ȩ�в�
		{
			
			String unit_type = getRegUnitRelMapByRegId(paraMap).get("REG_UNIT_TYPE").toString(); //reg_relationshipDao.get("where bus_id =(select bus_id from bus_main where reg_code=:reg_code)", paraMap).getReg_unit_type();
			if(unit_type.equals(WbfConstants.REG_UNIT_PARCEL)){
					
					tempObject =reg_UserightDao.get("where reg_code = :reg_code",paraMap);
			}else{
				tempObject =reg_ownershipDao.get("where reg_code = :reg_code",paraMap);
			}
				
		}
		else if(bus_class_id.equals(WbfConstants.REG_ATTACH))
		{
			List tempList =reg_DistrainDao.getAll("where reg_code = :reg_code",paraMap);
			if(tempList.size()>0)
			{
				return true;
			}
		}
		else if(bus_class_id.equals(WbfConstants.DEMURRER))
		{
			List tempList =reg_DemurrerDao.getAll("where reg_code = :reg_code",paraMap);
			if(tempList.size()>0)
			{
				return true;
			}
		}
		else if(bus_class_id.equals(WbfConstants.REVOKEAPPROVAL1))
		{
			Map map = getRegUnitRelMapByRegId(paraMap); 
			String unit_type= map.get("REG_UNIT_TYPE").toString();
			if(unit_type.equals(WbfConstants.REG_UNIT_HOUSE)){
					tempObject =reg_ownershipDao.get(" where reg_code = :LAST_REG_CODE and effective='"+WbfConstants.UNEFFECTIVE+"'",map);
			}else if(unit_type.equals(WbfConstants.REG_UNIT_PARCEL)){
				tempObject =reg_UserightDao.get("where reg_code = :LAST_REG_CODE  and effective='"+WbfConstants.UNEFFECTIVE+"'",map);
			}
		}
		else if(bus_class_id.equals(WbfConstants.REISSUE))		//��֤�Ǽ�   ����Ƿ� �͵�����Ȩ���в�     ����ǵ���ʹ��Ȩ�в�
		{
			
			String unit_type = getRegUnitRelMapByRegId(paraMap).get("REG_UNIT_TYPE").toString(); //reg_relationshipDao.get("where bus_id =(select bus_id from bus_main where reg_code=:reg_code)", paraMap).getReg_unit_type();
			if(unit_type.equals(WbfConstants.REG_UNIT_HOUSE)){
					tempObject =reg_ownershipDao.get("where reg_code = :reg_code",paraMap);
			}else if(unit_type.equals(WbfConstants.REG_UNIT_PARCEL)){
				tempObject =reg_UserightDao.get("where reg_code = :reg_code",paraMap);
			}
				
		}
		
		if(tempObject!=null){
			result = true;
		}
		return result;
	}
	
	/**
	 * 
	 * ����bus_id��ȡ��ѺȨ��������.
	 * @see com.szhome.cq.business.IRegisterFacade#getMortMess(java.util.Map)
	 */
	@Override
	public Map<String, Object> getMortMess(Map m) {
		Mortgage mort = mortDao.queryDomainBykey("Register.getMortByid", m);
		if( mort == null){			
			return null;			
		};
		String start = DateUtils.format(mort.getCreditor_start_date(), "yyyy��MM��dd��");
		String end = DateUtils.format(mort.getCreditor_end_date(), "yyyy��MM��dd��");
		String debt_dis_limit = "��"+start+"��"+end;
		Map map = new HashMap();
		map.put("mort_type", mort.getMort_type());
		map.put("assure_amount", mort.getMort_assure_right());
		map.put("assuer_range", mort.getAssuer_range());
		map.put("mort_seq", mort.getMort_seq());
		map.put("reg_date",DateUtils.format(com.szhome.cq.utils.DateUtils.getCurTime(),"yyyy��MM��dd��"));
		map.put("debt_dis_limit", debt_dis_limit);
		map.put("max_amount", mort.getMax_amount());
		map.put("sure_amount", mort.getSure_amount());
		map.put("borrower", mort.getBorrower());		
		
		return map;
	}
	
	/**
	 * 
	 * getReg_baseInfoByUnitCode:(ͨ���Ǽǵ�Ԫ�����ȡ�Ǽǲ�������Ϣ).
	 *
	 * @author Joyon
	 * @param reg_unit_no
	 * @return
	 * @since JDK 1.6
	 */
	public Reg_baseInfo getReg_baseInfoByUnitCode(String reg_unit_no){
		Map<String,Object> paraMap = new HashMap<String,Object>();
		paraMap.put("reg_unit_code", reg_unit_no);
		return reg_baseInfoDao.get("where reg_unit_code = :reg_unit_code", paraMap);
	}
	
	/**
	 * 
	 * getLastRegOnwershipMapByProcId:(ͨ������ʵ��ID��ȡ��һ��ҵ��ĵǼǲ�����Ȩ��ϢMap).
	 *
	 * @author Joyon
	 * @param proc_id
	 * @return
	 * @since JDK 1.6
	 */
	public Map<String,Object> getLastRegOnwershipMapByProcId(String proc_id) throws BusinessException{
		Map<String,Object> paraMap = new HashMap<String,Object>();
		paraMap.put("proc_id",proc_id);
		
		String last_bus_id = FacadeFactory.getCommonFacade().getBusinessMainByProcId(proc_id).getLast_bus_id();
		paraMap.put("last_bus_id", last_bus_id);
		String reg_code = businessMainDao.get("where bus_id=:last_bus_id", paraMap).getReg_code();
		paraMap.put("reg_code",reg_code);
		
		return getOwnershipInfoMapByRegId(paraMap);
		 
		
	}
	
	/**
	 * 
	 * saveHistoryHolderToApp:(����ʷȨ���˱��浽�������). 
	 *
	 * @author Joyon
	 * @param proc_id
	 * @return
	 * @since JDK 1.6
	 */
	public Map<String,Object> saveHistoryHolderToApp(String proc_id) throws BusinessException{
		String reg_unit_code = FacadeFactory.getCommonFacade().getReg_relationshipByProcId(proc_id).getReg_unit_code();
		List<Map<String,Object>> historyHolderMapList = getEffictiveHolderMapListByRegUnitCode(reg_unit_code,"");
		BusinessMain businessMain = FacadeFactory.getCommonFacade().getBusinessMainByProcId(proc_id);
		String bus_id = businessMain.getBus_id();
		
		Applicant applicant = null;
		Object tempObj = null;
		for(Map tempHolder : historyHolderMapList){
			
			applicant = new Applicant();
			applicant.setBus_id(bus_id);
			applicant.setApplicant_id(applicantDao.getSeqId());
			
			tempObj = tempHolder.get("HOL_NAME");
			if(tempObj != null){
				applicant.setApp_name(tempObj.toString());
			}
			
			tempObj = tempHolder.get("HOL_TYPE");
			if(tempObj != null){
				applicant.setApp_type(tempObj.toString());
			}
			
			tempObj = tempHolder.get("HOL_CER_TYPE");
			if(tempObj != null){
				applicant.setApp_cer_type(tempObj.toString());
			}
			
			tempObj = tempHolder.get("HOL_CER_NO");
			if(tempObj != null){
				applicant.setApp_cer_no(tempObj.toString());
			}
			
			tempObj = tempHolder.get("HOL_ADDRESS");
			if(tempObj != null){
				applicant.setApp_address(tempObj.toString());
			}
			
			tempObj = tempHolder.get("DEPART_TYPE");
			if(tempObj != null){
				applicant.setDepart_type(tempObj.toString());
			}
			
			tempObj = tempHolder.get("PORTION");
			if(tempObj != null){
				applicant.setApp_port(tempObj.toString());
			}
			
			tempObj = tempHolder.get("LEGAL_NAME");
			if(tempObj != null){
				applicant.setLegal_name(tempObj.toString());
			}
			
			tempObj = tempHolder.get("AGENT_NAME");
			if(tempObj != null){
				applicant.setAgent_name(tempObj.toString());
			}
			
			tempObj = tempHolder.get("AGENT_CER_TYPE");
			if(tempObj != null){
				applicant.setAgent_cer_type(tempObj.toString());
			}
			
			tempObj = tempHolder.get("AGENT_CER");
			if(tempObj != null){
				applicant.setAgent_cer(tempObj.toString());
			}
			
			tempObj = tempHolder.get("AGENT_TEL");
			if(tempObj != null){
				applicant.setAgent_tel(tempObj.toString());
			}
			
			applicantDao.save(applicant);
		}
		
		Map<String,Object> resultMap = new HashMap<String,Object>();
		
		resultMap.put("result", "success");
		
		return resultMap;
	}
	
	
	/**
	 * 
	 * getBusNameMapByRegCode:(ͨ���ǼǱ�Ż�ȡҵ��������).
	 *
	 * @author Joyon
	 * @param paraMap  reg_code
	 * @return
	 * @since JDK 1.6
	 */
	private Map<String,Object> getBusNameMapByRegCode(Map paraMap) throws BusinessException{
		return businessMainDao.queryMapByKey("Register.getBusNameMapByRegCode", paraMap);
	}
	
	
	/**
	 * 
	 * getEffectiveReg_OwnershipByProcId:(ͨ������ʵ��ID��ȡ��Ч�ĵǼǲ�����Ȩ������Ϣ)
	 *
	 * @author Joyon
	 * @return
	 * @since JDK 1.6
	 */
	public Reg_ownership getEffectiveReg_OwnershipByProcId(String proc_id) throws BusinessException{
		Map<String,Object> paraMap = new HashMap<String,Object>();
		paraMap.put("proc_id", proc_id);
		
		//��ʱ�����ַ�����ȡ��Ч����
		//String tempWhereSql ="where reg_code=(select reg_code from bus_main where bus_id=(select last_bus_id from bus_main where proc_id=:proc_id))";
		
		//�ȿ�Ĺ�����  ����������ȡ      ͨ�����ݱ����ȡ ��Ч�ĵǼ���Ϣ
		String realWhereSql ="where book_code=(select book_code from BK_BASEINFO where REG_UNIT_CODE=(select REG_UNIT_CODE from BUS_REGUNITREL where bus_id=(select bus_id from bus_main where proc_id=:proc_id))) and EFFECTIVE='"+WbfConstants.EFFECTIVE+"'";
		return reg_ownershipDao.get(realWhereSql, paraMap);
	}
	/**
	 * 
	 * getEffectiveReg_OwnershipByRegUnitCode:(��ȡ��ǰ�Ǽǵ�Ԫ�������Ч������Ȩ����)
	 *
	 * @author Joyon
	 * @param reg_unit_code
	 * @return Reg_Ownership
	 * @since JDK 1.6
	 */
	public Reg_ownership getEffectiveReg_OwnershipByRegUnitCode(String reg_unit_code) throws BusinessException{
		
		Map<String,Object> paraMap = new HashMap<String,Object>();
		paraMap.put("reg_unit_code", reg_unit_code);
		//�ȿ�Ĺ�����  ����������ȡ      ͨ�����ݱ����ȡ ��Ч�ĵǼ���Ϣ
		String realWhereSql ="where book_code=(select book_code from BK_BASEINFO where REG_UNIT_CODE=:reg_unit_code) and EFFECTIVE='"+WbfConstants.EFFECTIVE+"'";//(select REG_UNIT_CODE from BUS_REGUNITREL where bus_id=(select bus_id from bus_main where proc_id=:proc_id)))";
		return reg_ownershipDao.get(realWhereSql, paraMap);
	}
	/**
	 * 
	 * ��ȡ��ǰ�Ǽǵ�Ԫ�������Ч�ĵ�ѺȨ����
	 *
	 * @author Joyon
	 * @param reg_unit_code �Ǽǵ�Ԫ���
	 * @return list
	 * @since JDK 1.6
	 */
	public List<Reg_Mortgage> getEffectiveReg_MortgageListByRegUnitCode(String reg_unit_code) throws BusinessException{
		
		Map<String,Object> paraMap = new HashMap<String,Object>();
		paraMap.put("reg_unit_code", reg_unit_code);
		//�ȿ�Ĺ�����  ����������ȡ      ͨ�����ݱ����ȡ ��Ч�ĵǼ���Ϣ
		String realWhereSql ="where book_code=(select book_code from BK_BASEINFO where REG_UNIT_CODE=:reg_unit_code) and EFFECTIVE='"+WbfConstants.EFFECTIVE+"'";//(select REG_UNIT_CODE from BUS_REGUNITREL where bus_id=(select bus_id from bus_main where proc_id=:proc_id)))";
		return reg_MortgageDao.getAll(realWhereSql, paraMap);
	}
	/**
	 * 
	 * ��ȡ��ǰ�Ǽǵ�Ԫ�������Ч�Ĳ������
	 *
	 * @author Joyon
	 * @param reg_unit_code �Ǽǵ�Ԫ���
	 * @return list
	 * @since JDK 1.6
	 */
	public List<Reg_Distrain> getEffectiveReg_DistrainListByRegUnitCode(String reg_unit_code) throws BusinessException{
		
		Map<String,Object> paraMap = new HashMap<String,Object>();
		paraMap.put("reg_unit_code", reg_unit_code);
		//�ȿ�Ĺ�����  ����������ȡ      ͨ�����ݱ����ȡ ��Ч�ĵǼ���Ϣ
		String realWhereSql ="where book_code=(select book_code from BK_BASEINFO where REG_UNIT_CODE=:reg_unit_code) and EFFECTIVE='"+WbfConstants.EFFECTIVE+"'";//(select REG_UNIT_CODE from BUS_REGUNITREL where bus_id=(select bus_id from bus_main where proc_id=:proc_id)))";
		return reg_DistrainDao.getAll(realWhereSql, paraMap);
	}
	/**
	 * 
	 * getEffectiveCerNoByRegUnitCode:(��ȡ��ǰ�Ǽǵ�Ԫ�������Ч�ķ��ز�֤��)
	 *
	 * @author Joyon
	 * @param reg_unit_code
	 * @return String  cer_no
	 * @since JDK 1.6
	 */
	public String getEffectiveCerNoByRegUnitCode(String reg_unit_code) throws BusinessException{
		Map<String,Object> paraMap = new HashMap<String,Object>();
		paraMap.put("reg_unit_code", reg_unit_code);
		List<Reg_relationship> regRelationShipList = reg_relationshipDao.getAll("where reg_unit_code=:reg_unit_code",paraMap);
		String reg_unti_type = regRelationShipList.get(0).getReg_unit_type();
		if(reg_unti_type.equals(WbfConstants.HOUSE)){
			Reg_ownership reg_ownership = getEffectiveReg_OwnershipByRegUnitCode(reg_unit_code);
			if(reg_ownership!=null)
				return reg_ownership.getCer_no();
		}else if(reg_unti_type.equals(WbfConstants.PARCEL)){
			return getEffectiveReg_userightByRegUnitCode(reg_unit_code).getCer_no();
		}
		return null;
	}
	
	/**
	 * 
	 * getEffectiveReg_OwnershipMapByProcId:(ͨ������ʵ��ID��ȡ��Ч�ĵǼǲ�����Ȩ������Ϣ)
	 *
	 * @author Joyon
	 * @return
	 * @since JDK 1.6
	 */
	public Map<String,Object> getEffectiveReg_OwnershipMapByProcId(String proc_id) throws BusinessException{
		Map<String,Object> paraMap = new HashMap<String,Object>();
		paraMap.put("proc_id", proc_id);
		
		//��ʱ�����ַ�����ȡ��Ч����
		//String tempWhereSql ="where reg_code=(select reg_code from bus_main where bus_id=(select last_bus_id from bus_main where proc_id=:proc_id))";
		//�ȿ�Ĺ�����  ����������ȡ      ͨ�����ݱ����ȡ ��Ч�ĵǼ���Ϣ
		String realWhereSql ="where book_code=(select book_code from BK_BASEINFO where REG_UNIT_CODE=(select REG_UNIT_CODE from BUS_REGUNITREL where bus_id=(select bus_id from bus_main where proc_id=:proc_id))) and EFFECTIVE='"+WbfConstants.EFFECTIVE+"'";
		return reg_ownershipDao.queryMapByKey("Register.getBkOwnership", realWhereSql, paraMap);
	}
	/**
	 * 
	 * getEffectiveReg_userightMapByProcId:(ͨ������ʵ��ID��ȡ��Ч�ĵǼǲ�ʹ�ò�����Ϣ)
	 *
	 * @author Joyon
	 * @return  Map<String,Object>
	 * @since JDK 1.6
	 */
	public Map<String,Object> getEffectiveReg_userightMapByProcId(String proc_id) throws BusinessException{
		Map<String,Object> paraMap = new HashMap<String,Object>();
		paraMap.put("proc_id", proc_id);
		
		
		//��ʱ�����ַ�����ȡ��Ч����
		String tempWhereSql ="where reg_code=(select reg_code from bus_main where bus_id=(select last_bus_id from bus_main where proc_id=:proc_id))";
		
		//�ȿ�Ĺ�����  ����������ȡ      ͨ�����ݱ����ȡ ��Ч�ĵǼ���Ϣ
		String realWhereSql ="where book_code=(select book_code from BK_BASEINFO where REG_UNIT_CODE=(select REG_UNIT_CODE from BUS_REGUNITREL where bus_id=(select bus_id from bus_main where proc_id=:proc_id))) and EFFECTIVE='"+WbfConstants.EFFECTIVE+"'";
		return reg_ownershipDao.queryMapByKey("Register.getUseright", realWhereSql, paraMap);
	}
	
	/**
	 * 
	 * getEffectiveReg_userightByRegUnitCode:(��ȡ��ǰ�Ǽǵ�Ԫ�������Ч��ʹ��Ȩ����)
	 *
	 * @author Joyon
	 * @param reg_unit_code
	 * @return Reg_Useright
	 * @since JDK 1.6
	 */
	public Reg_Useright getEffectiveReg_userightByRegUnitCode(String reg_unit_code) {
		
		Map<String,Object> paraMap = new HashMap<String,Object>();
		paraMap.put("reg_unit_code", reg_unit_code);
		//�ȿ�Ĺ�����  ����������ȡ      ͨ�����ݱ����ȡ ��Ч�ĵǼ���Ϣ
		String realWhereSql ="where book_code=(select book_code from BK_BASEINFO where REG_UNIT_CODE=:reg_unit_code) and EFFECTIVE='"+WbfConstants.EFFECTIVE+"'";//(select REG_UNIT_CODE from BUS_REGUNITREL where bus_id=(select bus_id from bus_main where proc_id=:proc_id)))";
		return reg_UserightDao.get(realWhereSql, paraMap);
	}
	
	
	/**
	 * 
	 * getEffectiveholderRelationshipRegUnitCode:(��ȡ��ǰ�Ǽǵ�Ԫ��� ����Ч��Ȩ���˼�����Ϣ).
	 * @author Joyon
	 * @param reg_unit_code
	 * @return
	 * @throws BusinessException
	 * @since JDK 1.6
	 */
	public HolderRelationship getEffectiveholderRelationshipRegUnitCode(String reg_unit_code) throws BusinessException{
		
		Map<String,Object> paraMap = new HashMap<String,Object>();
		paraMap.put("reg_unit_code", reg_unit_code);
		//�ȿ�Ĺ�����  ����������ȡ      ͨ�����ݱ����ȡ ��Ч�ĵǼ���Ϣ
		String realWhereSql ="where book_code=(select book_code from BK_BASEINFO where REG_UNIT_CODE=:reg_unit_code) and EFFECTIVE='"+WbfConstants.EFFECTIVE+"'";//(select REG_UNIT_CODE from BUS_REGUNITREL where bus_id=(select bus_id from bus_main where proc_id=:proc_id)))";
		return holderRelationshipDao.get(realWhereSql, paraMap);
	}
	/**
	 * 
	 * TODO ��Ѻ�Ǽǲ�Ԥ��ʱ����ȡҳ��������ṹ.
	 * @see com.szhome.cq.business.IRegisterFacade#getRegBookInfoForMort(java.util.Map)
	 */
	public List<Map<String, Object>> getRegBookTreeForMort(Map m) {
		//����ҵ��id�ӵǼǵ�Ԫ�������л�ȡ�Ǽǵ�Ԫ��ţ��Ǽǵ�Ԫ����
				List<Map<String, Object>> regunitlist = mortDao.queryMapListByKey("Mortgage.getRegunitList", m);
				List<Map<String, Object>> regesate = new ArrayList<Map<String, Object>>();
				Map result = null;	
				int i = 0;
				String name = null;
				//��������
				Map macon = new HashMap();
				Map<String,Object> attributesMap = null;			//�����洢�����Զ������Ե����ڵ�Ϊ��    ��Ҫ�ŵǼǵ�Ԫ���
				for(Map map:regunitlist){
					attributesMap = new HashMap<String,Object>();
					String type = map.get("REG_UNIT_TYPE").toString();
					String code = map.get("REG_UNIT_CODE").toString();
					macon.put("code",code);
					attributesMap.put("code",code);
					//�жϵ�ǰ�Ǽǵ�Ԫ����Ϊ����
					if(type.trim().equals(WbfConstants.REG_UNIT_HOUSE)){
						result = FacadeFactory.getMortgageFacade().getRegunitMess("Mortgage.getHouseInfo", macon);
						String buildingname = result.get("BUILDING_NAME").toString();
						String buildno = result.get("BUILD_NO").toString();
						String roomname = result.get("ROOMNAME").toString();
						String proname = result.get("PRO_NAME").toString();
						name = proname+buildingname+buildno+roomname;
						map.put("text", name);
						map.put("attributes",attributesMap);
					}
					//�жϵ�ǰ�Ǽǵ�Ԫ����Ϊ������
					if(type.trim().equals(WbfConstants.REG_UNIT_BUILDING)){
						String buildingname = result.get("BUILDING_NAME").toString();
						String buildno = result.get("BUILD_NO").toString();
						name = buildingname+buildno;
						map.put("text", name);
						map.put("attributes",attributesMap);
						result = FacadeFactory.getMortgageFacade().getRegunitMess("Mortgage.getBuildInfo", macon);
					}
					//�жϵ�ǰ�Ǽǵ�Ԫ����Ϊ����
					if(type.trim().equals(WbfConstants.REG_UNIT_PARCEL)){				
						result = FacadeFactory.getMortgageFacade().getRegunitMess("Mortgage.getLandInfo", macon);
						map.put("text", "�ڵ�"+code);
						map.put("attributes",attributesMap);
					}
					
					map.put("id",i);
					map.put("children",setTreeChildrenForMort(attributesMap));
					i++;
					regesate.add(map);			
				}
				
				return regesate;
	}
	/**
	 * 
	 * setTreeChildrenForMort:�Ǽǲ�Ԥ��ʱ����������ṹ����ӽڵ�. <br/>
	 * @author PANDA
	 * @return
	 * @since JDK 1.6
	 */
	private List<Map<String, Object>>  setTreeChildrenForMort(Map<String,Object> attributesMap){
		List<Map<String, Object>> children = new ArrayList<Map<String, Object>>();
		Map nature = new HashMap();
		nature.put("id","1");
		nature.put("text","��Ȼ��Ϣ");
		nature.put("attributes", attributesMap);
		children.add(nature);
		Map useRight = new HashMap();
		useRight.put("id","2");
		useRight.put("text","��ѺȨ��Ϣ");
		useRight.put("attributes", attributesMap);
		children.add(useRight);
		return children;
	}
	
	/**
	 * 
	 * updateBkAttachState:(���²��Ǽǲ�״̬).
	 *
	 * @author xuzz
	 * @param reg_code	ǰ�ǼǱ��
	 * @param reg_unit_code  �Ǽǵ�Ԫ���
	 * @param state   Ҫ���õ�״̬
	 * @since JDK 1.6
	 */
	private void updateBkAttachState(String reg_code,String reg_unit_code,String state){
		if((reg_code == null || reg_code.equals("")) || (reg_unit_code == null || reg_unit_code.equals("")) || (state == null || state.equals(""))){
			//throw new BusinessException("RegisterFacade.updateBkMortState ����Ϊ�� ");
			LogUtil.error("RegisterFacade.updateBkAttachState---���²��Ǽǲ�״̬  ����Ϊ��  reg_code��"+reg_code+" reg_unit_code:"+reg_unit_code+" state:"+state);
			return;
		}else{
			Map<String,Object> paraMap = new HashMap<String,Object>();
			paraMap.put("reg_code", reg_code);
			paraMap.put("reg_unit_code", reg_unit_code);
			paraMap.put("state", state);
			//�ȴӲ��Ǽǲ��л�ȡ   ͨ���ǼǱ��   �͵Ǽǵ�Ԫ���
			Reg_Distrain regDistrain = reg_DistrainDao.get("where reg_code=:reg_code and book_code=(select book_code from bk_baseinfo where reg_unit_code=:reg_unit_code)", paraMap);
			//����״̬
			regDistrain.setEffective(state);
			reg_DistrainDao.update(regDistrain);
		}
		 
	}
	/**
	 * 
	 * updateBkDemurrerState:(��������Ǽǲ�״̬).
	 *
	 * @author xuzz
	 * @param reg_code	ǰ�ǼǱ��
	 * @param reg_unit_code  �Ǽǵ�Ԫ���
	 * @param state   Ҫ���õ�״̬
	 * @since JDK 1.6
	 */
	private void updateBkDemurrerState(String reg_code,String reg_unit_code,String state){
		if((reg_code == null || reg_code.equals("")) || (reg_unit_code == null || reg_unit_code.equals("")) || (state == null || state.equals(""))){
			//throw new BusinessException("RegisterFacade.updateBkMortState ����Ϊ�� ");
			LogUtil.error("RegisterFacade.updateBkDemurrerState---��������Ǽǲ�״̬  ����Ϊ��  reg_code��"+reg_code+" reg_unit_code:"+reg_unit_code+" state:"+state);
			return;
		}else{
			Map<String,Object> paraMap = new HashMap<String,Object>();
			paraMap.put("reg_code", reg_code);
			paraMap.put("reg_unit_code", reg_unit_code);
			paraMap.put("state", state);
			//�ȴӲ��Ǽǲ��л�ȡ   ͨ���ǼǱ��   �͵Ǽǵ�Ԫ���
			Reg_Demurrer regDemurrer = reg_DemurrerDao.get("where reg_code=:reg_code and book_code=(select book_code from bk_baseinfo where reg_unit_code=:reg_unit_code)", paraMap);
			//����״̬
			regDemurrer.setEffective(state);
			reg_DemurrerDao.update(regDemurrer);
		}
	}
	
	/**
	 * 
	 * updateBkMortState:(���µ�ѺȨ�Ǽǲ�״̬).
	 *
	 * @author Joyon
	 * @param reg_code	��Ѻ���
	 * @param reg_unit_code  ��Ѻ�Ǽǵ�Ԫ���
	 * @param state   Ҫ���õ�״̬
	 * @since JDK 1.6
	 */
	private void updateBkMortState(String reg_code,String reg_unit_code,String state){
		if((reg_code == null || reg_code.equals("")) || (reg_unit_code == null || reg_unit_code.equals("")) || (state == null || state.equals(""))){
			//throw new BusinessException("RegisterFacade.updateBkMortState ����Ϊ�� ");
			LogUtil.error("RegisterFacade.updateBkMortState---���µ�Ѻ�Ǽǲ�״̬  ����Ϊ��  reg_code��"+reg_code+" reg_unit_code:"+reg_unit_code+" state:"+state);
			return;
		}else{
			Map<String,Object> paraMap = new HashMap<String,Object>();
			paraMap.put("reg_code", reg_code);
			paraMap.put("reg_unit_code", reg_unit_code);
			paraMap.put("state", state);
			//�ȴӵ�Ѻ�Ǽǲ��л�ȡ   ͨ����Ѻ���   �͵�Ѻ�Ǽǵ�Ԫ���
			Reg_Mortgage regMortage = reg_MortgageDao.get("where reg_code=:reg_code and book_code=(select book_code from bk_baseinfo where reg_unit_code=:reg_unit_code)", paraMap);
			//����״̬
			regMortage.setEffective(state);
			reg_MortgageDao.update(regMortage);
		}
		
	}
	
	
	/**
	 * 
	 * getOrigMortRegCode:(��ȡԭ��Ѻ�ǼǱ��). 
	 * @author Joyon
	 * @param bus_id
	 * @return
	 * @since JDK 1.6
	 */
	private String getOrigMortRegCode(String bus_id){
		Map<String,Object> paraMap = new HashMap<String,Object>();
		paraMap.put("bus_id", bus_id);
		String origMortRegCode = null;
		List<Map<String,Object>> regUnitRelMapList = reg_MortgageDao.queryMapListByKey("Mortgage.getRegUnitRel","where bus_id=:bus_id",paraMap);
		if(!(regUnitRelMapList.size()==0)){
			origMortRegCode = regUnitRelMapList.get(0).get("LAST_REG_CODE").toString();
		}else{
			LogUtil.error("MortgageFacade.getOrigMortRegCode  �Ǽǵ�Ԫ������Ϊ��   ");
		}
		return origMortRegCode;
	}
	
	/**
	 * 
	 * getRegMortgageByRegUnitCodeAndRegCode:(ͨ���Ǽǵ�Ԫ��ź͵ǼǱ�Ż�ȡ��Ѻ�Ǽǲ���Ϣ). 
	 *
	 * @author Joyon
	 * @param reg_unit_code
	 * @param reg_code
	 * @return
	 * @since JDK 1.6
	 */
	public Reg_Mortgage getRegMortgageByRegUnitCodeAndRegCode(String reg_unit_code,String reg_code){
		Map<String,Object> paraMap = new HashMap<String,Object>();
		paraMap.put("reg_unit_code", reg_unit_code);
		paraMap.put("reg_code", reg_code);
		String whereSql = "where reg_code=:reg_code and book_code=(select book_code from BK_BASEINFO where reg_unit_code=:reg_unit_code)";
		Reg_Mortgage reg_Mortgage = null;
		try {
			reg_Mortgage = reg_MortgageDao.get(whereSql, paraMap);
		} catch (Exception e) {
			LogUtil.error("RegisterFacade.getRegMortgageByRegUnitCodeAndRegCode ��ȡ��Ѻ�Ǽǲ���Ϣ����");
		}
		return reg_Mortgage;
	}
	@Override
	public List<Map<String, Object>> getCurrentHolderMapListByProcId(
			Map<String, Object> params) {
		return houseDao.queryMapListByKey("Register.getCurrentHolderMapListByProcId", params);
	}
	
}





