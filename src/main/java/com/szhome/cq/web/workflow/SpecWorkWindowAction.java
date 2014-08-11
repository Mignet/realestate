/**
 * Project Name:dxtx_re
 * File Name:SpecWorkWindowAction.java
 * Package Name:com.szhome.cq.web.workflow
 * Date:2014-6-10����12:48:14
 * Copyright (c) 2014, DXTX All Rights Reserved.
 *
*/

package com.szhome.cq.web.workflow;

import org.springframework.web.servlet.ModelAndView;

import com.plan.commons.Row;
import com.plan.exceptions.GeneralException;
import com.szhome.cq.delegate.BaseDelegate;

/**
 * ClassName:SpecWorkWindowAction <br/>
 * ��������: �������̴��� <br/>
 * ����ԭ��:	 Spring MVC�滻Struts Convention. <br/>
 * ����ʱ��:  2014-6-10 ����12:48:14 <br/>
 * @author   dxtx
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class SpecWorkWindowAction extends BaseDelegate{
	
	public ModelAndView home(Row row) throws GeneralException{
		ModelAndView mv = new ModelAndView();
		String jds_sqs_tw = row.getString("jds_sqs_tw");
		String user = this.getOperatorInfo().getUserName();
		mv.addObject("jds_sqs_tw",jds_sqs_tw);
		mv.addObject("user",user);
		mv.setViewName("workflow/spec-work-window");
		return mv;
	}
	
}


