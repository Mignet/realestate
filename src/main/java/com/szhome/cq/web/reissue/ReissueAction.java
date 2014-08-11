/**
 * Project Name:dxtx_re
 * File Name:ReissueAction.java
 * Package Name:com.szhome.cq.web.reissue
 * Date:2014-3-31上午10:18:56
 * Copyright (c) 2014, DXTX All Rights Reserved.
 *
*/

package com.szhome.cq.web.reissue;

import java.util.Map;

import com.plan.commons.Row;
import com.szhome.commons.log.LogUtil;
import com.szhome.cq.business.FacadeFactory;
import com.szhome.cq.delegate.BaseDelegate;
import com.szhome.cq.domain.model.Certificate;
import com.szhome.cq.domain.model.Reg_relationship;
import com.szhome.cq.utils.JsonUtil;
import com.szhome.cq.utils.WbfConstants;

/**
 * ClassName:ReissueAction <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2014-3-31 上午10:18:56 <br/>
 * @author   Joyon
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class ReissueAction extends BaseDelegate{
	
	/**
	 * 
	 * saveBKOwnerShipToBusOwnerShip:(保存前登记簿吕有交往的数据到登记信息中).
	 *
	 * @author Joyon
	 * @param proc_id
	 * @since JDK 1.6
	 */
	public String saveBKOwnerShipToBusOwnerShip(){
		
		return null;
	}
	
	/**
	 * 
	 * saveBkInfoToBus:(把登记簿数据保存到过程表中). 
	 *
	 * @author Joyon
	 * @return
	 * @since JDK 1.6
	 */
	public String saveBkInfoToBus(Row row){
		//保存基本信息   然后再保存房地产证信息
		String proc_id = row.getString("proc_id");
		try {
			Reg_relationship reg_relationship = FacadeFactory.getCommonFacade().getReg_relationshipByProcId(proc_id);
			String reg_unit_type = reg_relationship.getReg_unit_type();
			String reg_unit_code = reg_relationship.getReg_unit_code();
			Certificate certificate = FacadeFactory.getCommonFacade().getCertificateByProcId(proc_id);
			
			if (reg_unit_type.equals(WbfConstants.PARCEL)) {
				FacadeFactory.getChangeRecordFacade().saveBKUserightShipToBusUserightShip(proc_id);
				//如果当前业务房地产证信息为空  则保存一条  附记 和业务表ID
				if(certificate==null){
					certificate = new Certificate();
					certificate.setBus_id(FacadeFactory.getCommonFacade().getBusinessMainByProcId(proc_id).getBus_id());
					certificate.setExcursus(FacadeFactory.getRegisterFacade().getEffectiveReg_userightByRegUnitCode(reg_unit_code).getExcursus());
					FacadeFactory.getCommonFacade().saveCertificate(certificate);
				}
			} else if (reg_unit_type.equals(WbfConstants.HOUSE)) {
				FacadeFactory.getChangeRecordFacade().saveBKOwnerShipToBusOwnerShip(proc_id);
				//如果当前业务房地产证信息为空  则保存一条  附记 和业务表ID
				if(certificate==null){
					certificate = new Certificate();
					certificate.setBus_id(FacadeFactory.getCommonFacade().getBusinessMainByProcId(proc_id).getBus_id());
					certificate.setExcursus(FacadeFactory.getRegisterFacade().getEffectiveReg_OwnershipByRegUnitCode(reg_unit_code).getExcursus());
					FacadeFactory.getCommonFacade().saveCertificate(certificate);
				}
				
			}
		} catch (Exception e) {
			LogUtil.error("保存登记簿信息到业务过程表中出错:ReissueAction.saveBkInfoToBus()    message:"+e.getMessage());
		}
		return null;
	}
	
	/**
	 * 
	 * regRegInfo:(补换发登记信息获取). 
	 *
	 * @author Joyon
	 * @return
	 * @since JDK 1.6
	 */
	public String regRegInfo(Row row){
		Map<String,Object> resultMap = null;
		String proc_id = row.getString("proc_id");
		try {
			resultMap = FacadeFactory.getChangeRecordFacade().getRegInfoMapByProcId(proc_id);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonUtil.object2json(resultMap);
	}
	
}


