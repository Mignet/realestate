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
import com.plan.exceptions.GeneralException;
import com.szhome.cq.business.FacadeFactory;
import com.szhome.cq.business.IAttachFacade;
import com.szhome.cq.common.SQLCriteriaBean;
import com.szhome.cq.delegate.BaseDelegate;
import com.szhome.cq.utils.Constants;
import com.szhome.cq.utils.Util;
import com.szhome.cq.utils.web.AttachReportServiceUtil;


public class AttachReportAction extends BaseDelegate{
	//
    IAttachFacade attachFacade = FacadeFactory.getAttachFacade();               ///得到查封服务Facade
	//
	private String REALESTATE_ATTACH_SELECTRPT = Util.getMessage(Constants.MODULE_REPORT, "report.realestate_attach_selectrpt");
	
	static String[] captions = {"attach.caption","attach.section","attach.location","attach.reg_code",
								"attach.reg_date","attach.dis_off","attach.dis_no","attach.lim_holder",
								"attach.dis_limit","attach.excursus","attach.cer_no","attach.can_reg_date",
								"attach.can_dis_no","attach.can_excursus","attach.mortsituation",
								"attach.mort_reg_date","register.cancel_date","building.nameandno",
								"common.asterisk","common.di","common.ye","common.colon","common.left_parentheses",
								"common.right_parentheses","common.print_date","printdatetime"};
	
	//二元数组,数组值意义,1.数据表里的字段,2.实体对象对应字段,3.类型区分【1:代表字符串类型,2:代表时间类型,3:代表日期加时间,4:代表金额类型】
		static final String[][] options = {{"tab.distress_id","distressId","1"},
			                                {"tab.reg_unit_type","reg_unit_type","1"}};
		//查封Id
//		private String distress_id;
//		private String items;
	
	public ModelAndView report(Row row) throws GeneralException{
		    String jasperFilePath = null;
		    String jrxmlFilePath = null;
		    ModelAndView mv = null;
		    String jasperFileName = REALESTATE_ATTACH_SELECTRPT+".jasper"; 
		    String jrxmlFileName = REALESTATE_ATTACH_SELECTRPT+".jrxml"; 
		    String jasperRelativePath = "/report/" + jasperFileName;
		    String jrxmlRelativePath = "/report/source/" + jrxmlFileName;
		    RequestAttributes ra = RequestContextHolder.getRequestAttributes();
			HttpServletRequest request = ((ServletRequestAttributes)ra).getRequest();
		    //获得报表jasper文件的完整路径
		    jasperFilePath = AttachReportServiceUtil.getJasperFileFullPath(request, jasperRelativePath, jasperFileName);
		    //获得报表jrxml文件的完整路径
		    jrxmlFilePath = AttachReportServiceUtil.getJasperFileFullPath(request, jrxmlRelativePath, jrxmlFileName);
		    List<Map<String,Object>> datas = null;
			Map<String, Object> parameters = new HashMap<String, Object>();
			
			Map<String,Object> paramMap = new HashMap<String,Object>();
			//String inselectOptions = Util.getInSubselectOptions(mort_id);
			paramMap.put("items", row.getString("items"));
			paramMap.put("flag", "");
			
			SQLCriteriaBean bean = AttachReportServiceUtil.getAttachReportSQLCriteria(paramMap, parameters, options);
			
			datas = attachFacade.getAttachsBydistressId(bean.getSqlMap(), bean.getValueLst());
			
			try {
				AttachReportServiceUtil.setReportCaptions("report", parameters, captions);
				mv = resultToPDF("AttachReport",jasperFilePath,jrxmlFilePath,parameters,datas);
			}catch(JRException e){
				logger.error("AttachReportAction--report:"+e.getMessage(), e);
				throw new GeneralException("AttachReportAction--report:"+e.getMessage());
			} catch (Exception e) {
				logger.error("AttachReportAction--report:"+e.getMessage(), e);
				throw new GeneralException("AttachReportAction--report:"+e.getMessage());
			}
			return mv;
     }
	
}

