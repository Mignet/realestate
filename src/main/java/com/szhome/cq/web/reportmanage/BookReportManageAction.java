package com.szhome.cq.web.reportmanage;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.plan.commons.Row;
import com.szhome.cq.delegate.BaseDelegate;

public class BookReportManageAction extends BaseDelegate{
	
	public ModelAndView dxtxReport(Row row){
			RequestAttributes ra = RequestContextHolder.getRequestAttributes();
		 	HttpServletRequest request = ((ServletRequestAttributes)ra).getRequest();
			String japserFile = "/reports/report_cer1.jasper";
			request.setAttribute("parameters", new HashMap());
			request.setAttribute("reportData",  new ArrayList());
			request.setAttribute("jasperFile", japserFile);
			return new ModelAndView("report");
     }

}

