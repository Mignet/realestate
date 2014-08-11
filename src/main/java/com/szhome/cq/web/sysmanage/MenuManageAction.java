package com.szhome.cq.web.sysmanage;
import org.springframework.web.servlet.ModelAndView;

import com.plan.commons.Row;
import com.szhome.cq.delegate.BaseDelegate;

/**
 * 
 * @author user
 *
 */
public class MenuManageAction extends BaseDelegate{

	public ModelAndView home(Row row){
		return new ModelAndView("common/menu/menu");
	}
}

