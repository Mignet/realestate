package com.szhome.cq.web.reportmanage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.jasperreports.engine.JRException;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.plan.commons.Row;
import com.szhome.cq.business.FacadeFactory;
import com.szhome.cq.business.IRecmaterialFacade;
import com.szhome.cq.delegate.BaseDelegate;
import com.szhome.cq.utils.BaseServiceUtil;
import com.szhome.cq.utils.Constants;
import com.szhome.cq.utils.Util;

public class BarcodePrintAction extends BaseDelegate {
	
	 IRecmaterialFacade recFacade = FacadeFactory.getRecmaterialFacade();
	 
	 private String BARCODE_PRINT_BY_REGCODE = Util.getMessage(Constants.MODULE_REPORT, "report.barcode_print_by_regcode");
	 
     public ModelAndView report(Row row){
    	  String jasperFilePath = null;
		    String jrxmlFilePath = null;
		    ModelAndView mv = null;
		    String jasperFileName = BARCODE_PRINT_BY_REGCODE+".jasper"; 
		    String jrxmlFileName = BARCODE_PRINT_BY_REGCODE+".jrxml"; 
		    String jasperRelativePath = "/report/" + jasperFileName;
		    String jrxmlRelativePath = "/report/source/" + jrxmlFileName;
		   
		    RequestAttributes ra = RequestContextHolder.getRequestAttributes();
			HttpServletRequest request = ((ServletRequestAttributes)ra).getRequest();
			
			 //获得报表jasper文件的完整路径
		    jasperFilePath = BaseServiceUtil.getJasperFileFullPath(request, jasperRelativePath, jasperFileName);
		    //获得报表jrxml文件的完整路径
		    jrxmlFilePath = BaseServiceUtil.getJasperFileFullPath(request, jrxmlRelativePath, jrxmlFileName);
			
		    
		    List<Map<String,Object>> datas = null;
			Map<String, Object> parameters = new HashMap<String, Object>();
			Map<String,Object> paramMap = new HashMap<String,Object>();
			//String inselectOptions = Util.getInSubselectOptions(mort_id);
			paramMap.put("reg_code", row.getString("regcode"));
			datas = recFacade.getMaterialIdListByregcode(paramMap);
			try {
				mv = resultToPDF("BarcodePrint",jasperFilePath,jrxmlFilePath,parameters,datas);
			} catch (JRException e){
				logger.error("BarcodePrintAction--report:"+e.getMessage(), e);
			} catch (Exception e) {
				logger.error("BarcodePrintAction--report:"+e.getMessage(), e);
			}
			return mv;
     }
}

