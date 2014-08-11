/**
 * Project Name:dxtx_re
 * File Name:FormReportFacade.java
 * Package Name:com.szhome.cq.business.impl
 * Date:2013-12-25����2:59:05
 * Copyright (c) 2013, chenzhou1025@126.com All Rights Reserved.
 *
*/

package com.szhome.cq.business.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.szhome.cq.business.FacadeFactory;
import com.szhome.cq.business.IWorkwindowFacade;
import com.szhome.cq.domain.model.ConOffice;
import com.szhome.cq.domain.model.FormReportAds;
import com.szhome.security.ext.UserInfo;

/**
 * ��������facade
 * Date:     2013-12-25 ����2:59:05 <br/>
 * @author   xuzz
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
@Component
@Transactional
@Scope("prototype")
public class WorkwindowFacade implements IWorkwindowFacade {
	
	@Autowired
	private ConOffice conOfficeDao;
	
	/**
	 * 
	 * getFormTree:(��ȡ������������). <br/>
	 *getFormTreeurl
	 * @author xuzz
	 * @return
	 * @since JDK 1.6
	 */
	public List<ConOffice> getOfficeByNodeid(Map map)
	{
		
		return conOfficeDao.queryListByKey("Workwindow.getOfficeByNodeid", " and P.BUS_TYPE_ID=:bustype and p.proc_node_id=:nodeid", map);
	}
	/**
	 * 
	 *  ��ȡ������������.
	 * @see com.szhome.cq.business.IWorkwindowFacade#getFormTreeurl(java.lang.String)
	 */
	public Map getFormTreeurl(String formid)
	{
		Map map=new HashMap();
		map.put("formid", formid);
		return null;// formReportDao.queryMapByKey("Common.getFormTree", " where form_id=:formid",map);
	}
	
	/**
	 * ��ʼ���칫ҳ��
	 * @return
	 * @throws Exception
	 */
	@Override
	@Transactional
	public Map getFormAndsubmitbtn(UserInfo userInfo,Long procdefId,String activdefId){
		Map result = new HashMap();
		//IFrame��ַ��url
		//Map m=FacadeFactory.getFormFacade().getFormTreeurl(formid);
//		if(m!=null)
//		{
//			result.put("url",m.get("url").toString());
//		}
		//��̬���ذ�ť
		List list=FacadeFactory.getWorkflowFacade().getsubmitbtn(userInfo, procdefId, activdefId);
		if(list!=null&&!list.isEmpty()){
			result.put("rows", list);
		}
		return result;
	}
	
	

}


