/**
 * Project Name:dxtx_re
 * File Name:DispathcherAction.java
 * Package Name:com.szhome.cq.web.common
 * Date:2014-6-10обнГ5:16:45
 * Copyright (c) 2014, DXTX All Rights Reserved.
 *
*/

package com.szhome.cq.web.common;

import org.springframework.web.servlet.ModelAndView;

import com.plan.commons.Row;
import com.szhome.cq.delegate.BaseDelegate;

/**
 * ClassName:DispathcherAction <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2014-6-10 обнГ5:16:45 <br/>
 * @author   dxtx
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class DispathcherAction extends BaseDelegate{

    public ModelAndView home(Row row){
    	ModelAndView mv = new ModelAndView();
    	mv.addObject("row",row);
    	mv.addObject("reg_unit_type",row.getString("reg_unit_type"));
    	mv.addObject("realestate_type",row.getString("realestate_type"));
    	mv.setViewName(row.getString("q"));
    	return mv;
    }
    
}


