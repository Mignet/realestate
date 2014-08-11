package com.szhome.cq.web.common;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

import org.apache.tools.ant.util.DateUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.plan.commons.Row;
import com.plan.exceptions.GeneralException;
import com.szhome.commons.log.LogUtil;
import com.szhome.commons.util.DateUtil;
import com.szhome.cq.business.FacadeFactory;
import com.szhome.cq.business.IReportFacade;
import com.szhome.cq.common.report.ChartDataSource;
import com.szhome.cq.common.report.ChartDataSourceProvider;
import com.szhome.cq.delegate.BaseDelegate;
import com.szhome.cq.domain.model.Applicant;
import com.szhome.cq.domain.model.BusinessMain;
import com.szhome.cq.domain.model.MaterialReplenish;
import com.szhome.cq.domain.model.Reg_Useright;
import com.szhome.cq.domain.model.Reg_ownership;
import com.szhome.cq.ireport.ReportCreater;
import com.szhome.cq.ireport.ReportPrint;
import com.szhome.cq.ireport.ReportView;
import com.szhome.cq.utils.WbfConstants;
import com.szhome.process.entity.ProcessInst;

public class PrintAction extends BaseDelegate{
	private IReportFacade reportFacade=FacadeFactory.getReportFacade();
	
	/**
	 * 
	 * ����֪ͨ��
	 *
	 * @author Joyon
	 * @return
	 * @since JDK 1.6
	 */
	public ModelAndView printAcceptanceNotice(Row row){
		String proc_id = row.getString("proc_id");	 //"1000025311";"1000026187"	
		String number = "9c-201293234";						//�ĺ�
		String serial_number = "802848920384355805934";		//�걨��ˮ��
		int date_limit = 30;								//ҵ��ʱ��
		Map<String,Object> paraMap = new HashMap<String,Object>();
		String reg_code = (FacadeFactory.getCommonFacade().getBusinessMainByProcId(proc_id) == null)?"":FacadeFactory.getCommonFacade().getBusinessMainByProcId(proc_id).getReg_code();
		paraMap.put("reg_code", reg_code);
		String bus_type = FacadeFactory.getRegisterFacade().getRegTypeByRegCode(paraMap);
		//��ȡ��������ʱ����Ϊ ����ʱ��
		Date procStartdate = new Date();
		ProcessInst processInst = FacadeFactory.getWorkflowFacade().getProcessInstByProcid(proc_id);
		if(processInst!=null){
			procStartdate = processInst.getStartDate();
		}
		//��ȡ30��������
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(procStartdate);
		calendar.add(calendar.DATE, date_limit);
		Date laterDate = calendar.getTime();
		String date_end = DateUtils.format(laterDate, "yyyy��MM��dd��");
		
		String date_rec = DateUtils.format(procStartdate, "yyyy��MM��dd��");
		
		
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		//������
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("proc_id", proc_id);	
		parameters.put("date_rec", date_rec);
		parameters.put("date_end", date_end);
		parameters.put("number", number);
		parameters.put("date_limit", date_limit);
		parameters.put("serial_number", serial_number);
		parameters.put("bus_type", bus_type);
		parameters.put("SUBREPORT_DIR", request.getSession().getServletContext().getRealPath("/report"));
		ServletContext servletContext =request.getSession().getServletContext();
		   JasperPrint jasperPrint = null;
		   JasperPrint jasperPrint1 = null;
		   JasperPrint jasperPrint2 = null;
		try {
			jasperPrint = JasperFillManager.fillReport(servletContext.getRealPath("/report/reportAccNotice.jasper"),parameters, reportFacade.getConn());
			//jasperPrint1 = JasperFillManager.fillReport(servletContext.getRealPath("/report/reportAccNotice1.jasper"),parameters, reportFacade.getConn()); 
			jasperPrint2 = JasperFillManager.fillReport(servletContext.getRealPath("/report/reportAccNotice2.jasper"), null, reportFacade.getConn());
		} catch (JRException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		   
		   List jasperPrintList = new ArrayList();
		   jasperPrintList.add(jasperPrint);
		  // jasperPrintList.add(jasperPrint1);
		   jasperPrintList.add(jasperPrint2);
		
		   ReportPrint reportPrint = new ReportPrint();
		   reportPrint.setJasperList(jasperPrintList);
		   
			parameters.put("reportPrint",reportPrint);
			
			//��������
			String jasperFilename = "acceptanceNotice";
			parameters.put("reportName",jasperFilename);
			parameters.put("format","pdf");
			
			ModelAndView mv = new ModelAndView(jasperFilename+".report",parameters);
			return mv;
	}
	
	/**
	 * 
	 * ��ӡ�Ӽ�����
	 *
	 * @author Joyon
	 * @return
	 * @throws JRException 
	 * @since JDK 1.6
	 */
	public ModelAndView printMatRecCre(Row row) throws JRException{
		String proc_id = row.getString("proc_id");
		Map<String,Object> paraMap = new HashMap<String,Object>();
		Map<String, Object> parameters = new HashMap<String, Object>();
		BusinessMain businessMain = FacadeFactory.getCommonFacade().getBusinessMainByProcId(proc_id);
		if(businessMain != null){
			String reg_code = businessMain.getReg_code();
			paraMap.put("reg_code", reg_code);
			String bus_type = FacadeFactory.getRegisterFacade().getRegTypeByRegCode(paraMap);					//ҵ������
			String bus_id = businessMain.getBus_id();		//ҵ������ID
			String bus_detail  = businessMain.getProc_name();
			
			
			parameters.put("bus_id", bus_id);	
			parameters.put("bus_detail",bus_detail);
			parameters.put("reg_type",bus_type);
			parameters.put("reg_code", reg_code);
		}
		String date = DateUtils.format(new Date(), "yyyy��MM��dd��");											//��ǰ����
		String number = "9c-201293234";																		//�ĺ�
		
//		Building building = FacadeFactory.getCommonFacade().getBuildingByProcId(proc_id);
//		House house = FacadeFactory.getCommonFacade().getHouseByProcId(proc_id);
//		String houseName = "";
//		if(building != null && house!=null){
//			houseName = building.getCommunity_name()+building.getBuilding_name()+house.getRoomname();
//		}
//		
//		String bus_detail = bus_type+"("+houseName+")";														//ҵ����ϸ
		

		
		parameters.put("DATE",date);
		parameters.put("NUMBER", number);
		//��������
		String jasperFilename = "reportMatRecCre";
		parameters.put("reportName",jasperFilename);
		parameters.put("format","pdf");
		RequestAttributes ra = RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = ((ServletRequestAttributes)ra).getRequest();
//		String reportPath = request.getContextPath()+("\\webapp\\report\\");
		String reportPath = request.getSession().getServletContext().getRealPath("/report/");
//		String reportPath = "E:\\cq\\��Ȩϵͳ�ؽ���Ŀ\\10 - ϵͳ����\\dxtx_re\\src\\main\\webapp\\report\\";
		
		ReportCreater rc = ReportCreater.getInstance();
		rc.setJasperReportPath(reportPath);
		ReportPrint reportPrint = rc.createReport(jasperFilename, parameters, reportFacade.getConn());
		parameters.put("reportPrint",reportPrint);
		
		ModelAndView mv = new ModelAndView(jasperFilename+".report",parameters);
		return mv;
		//return resultToPDF("reportMatRecCre", null, parameters, reportFacade.getConn());
	}

	/**
	 * 
	 * ��ӡ���ز�֤ 
	 *
	 * @author Joyon
	 * @return
	 * @since JDK 1.6
	 */
	public ModelAndView printCertificate(Row row){
		String proc_id = row.getString("proc_id");
		BusinessMain businessMain = FacadeFactory.getCommonFacade().getBusinessMainByProcId(proc_id);
		String reg_code = businessMain.getReg_code();
		String location_reg_unit_name = "";
		if(businessMain.getLocation_reg_unit() != null){
			 location_reg_unit_name = FacadeFactory.getDictFacade().getDictItemNameByCodeAndTypeCode(businessMain.getLocation_reg_unit(),"065");
		}
		//Certificate certificate =  FacadeFactory.getCommonFacade().getCertificateByProcId(proc_id);
		//BusOwnership busOwnership = FacadeFactory.getCommonFacade().getBusOwnershipByProcId(proc_id);
		String reg_unit_type = FacadeFactory.getCommonFacade().getReg_relationshipByProcId(proc_id).getReg_unit_type();
		Map<String, Object> parameters = new HashMap<String, Object>();
		
		//��ȡ��Ȼ��Ϣ   ͨ���ǼǱ��
		Map<String,Object> paraMap = new HashMap<String,Object>();
		paraMap.put("reg_code", reg_code);
		Map<String,Object> naturalMap = FacadeFactory.getRegisterFacade().getNaturalInfo(paraMap);
		
		if(reg_unit_type.equals(WbfConstants.HOUSE)){
			Reg_ownership reg_ownership = FacadeFactory.getCommonFacade().getReg_ownershipByProcId(proc_id);
			//������
			if(reg_ownership!=null){
				parameters.put("CER_CODE", reg_ownership.getCer_no());						//����
				parameters.put("REG_DATE", reg_ownership.getReg_date());					//�Ǽ�ʱ��
				parameters.put("REG_PRICCE", reg_ownership.getReg_value());					//�ǼǼۿ�
				parameters.put("EXCURSUS",reg_ownership.getExcursus());						//���ز�֤����
				
			}
			String flat_usage =  FacadeFactory.getDictFacade().getDictItemNameByCodeAndTypeCode(naturalMap.get("FLATLET_USAGE").toString(),"062");
			if(flat_usage == null){
				flat_usage = naturalMap.get("FLATLET_USAGE").toString();
			}
			parameters.put("BUILD_AREA", naturalMap.get("BUILD_AREA").toString());						//����
			parameters.put("TAONEI_AREA",naturalMap.get("TAONEI_AREA").toString());					//�Ǽ�ʱ��
			parameters.put("FLATLET_USAGE", flat_usage);					//�ǼǼۿ�
			parameters.put("BUILD_COMPLEION_DATE",naturalMap.get("BUILD_COMPLEION_DATE").toString());		
			parameters.put("PRO_NAME",naturalMap.get("PRO_NAME").toString());	
		}else if(reg_unit_type.equals(WbfConstants.PARCEL)){
			Reg_Useright regUserRight =  FacadeFactory.getCommonFacade().getReg_UserightByProcId(proc_id);
			//������
			if(regUserRight!=null){
				parameters.put("CER_CODE", regUserRight.getCer_no());						//����
				parameters.put("REG_DATE", regUserRight.getReg_date());					//�Ǽ�ʱ��
				parameters.put("REG_PRICCE", regUserRight.getReg_pri());					//�ǼǼۿ�
				parameters.put("EXCURSUS",regUserRight.getExcursus());						//���ز�֤����
			}
			parameters.put("BUILD_AREA","");						//����
			parameters.put("TAONEI_AREA","");					//�Ǽ�ʱ��
			parameters.put("FLATLET_USAGE","");					//�ǼǼۿ�
			parameters.put("BUILD_COMPLEION_DATE","");		
			parameters.put("PRO_NAME","");	
		}
		//ʹ������  ��Sql���Ѿ�ת��Ϊ�ֵ���  
		//String use_per =FacadeFactory.getDictFacade().getDictItemNameByCodeAndTypeCode( naturalMap.get("USE_PER").toString(),"006");
		//ʹ�������ִ�
		String str_use_per = naturalMap.get("USE_PER_VALUE").toString()+"��"+naturalMap.get("PA_START_DATE").toString()+"��"+naturalMap.get("PA_END_DATE").toString();
		//������;
		String real_usage = FacadeFactory.getDictFacade().getDictItemNameByCodeAndTypeCode(naturalMap.get("REAL_USAGE").toString(),"015");
		//��ֹ���ݿ���δ���ֵ�����Ĵ�
		if(real_usage == null){
			real_usage = naturalMap.get("REAL_USAGE").toString();
		}
		parameters.put("PARCEL_CODE", naturalMap.get("PARCEL_CODE").toString());				//�ڵغ�
		parameters.put("PARCEL_AREA",naturalMap.get("PARCEL_AREA").toString());					//�ڵ����
		parameters.put("REAL_USAGE",real_usage);												//�ڵ���;
		parameters.put("LAND_ADDRESS",naturalMap.get("LAND_ADDRESS").toString());				//�ڵص�ַ
		parameters.put("USE_PER",str_use_per);													//ʹ������
		
		parameters.put("REG_CODE", reg_code);													//�ǼǱ��
		
		parameters.put("proc_id", proc_id);														//����ʵ��ID
		parameters.put("AREA", location_reg_unit_name);											//������  ���ݿ�����ʱû���ֶ�
		
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		parameters.put("SUBREPORT_DIR", request.getSession().getServletContext().getRealPath("/report"));
		ServletContext servletContext =request.getSession().getServletContext();
		  JasperPrint jasperPrint1 = null;
		   JasperPrint jasperPrint2 = null;
		   JasperPrint jasperPrint3 = null;
		try {
			//jasperPrint1 = JasperFillManager.fillReport(servletContext.getRealPath("/report/report_cer1.jasper"),parameters, reportFacade.getConn());
			 jasperPrint2 = JasperFillManager.fillReport(servletContext.getRealPath("/report/report_cer2.jasper"), parameters, reportFacade.getConn());
			 jasperPrint3 = JasperFillManager.fillReport(servletContext.getRealPath("/report/report_cer3.jasper"), parameters, reportFacade.getConn());
		} catch (JRException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
		List<JasperPrint> jasperPrintList = new ArrayList<JasperPrint>();
		   //jasperPrintList.add(jasperPrint1);
		   jasperPrintList.add(jasperPrint2);
		   jasperPrintList.add(jasperPrint3);
		
		   ReportPrint reportPrint = new ReportPrint();
		   reportPrint.setJasperList(jasperPrintList);
		   
			parameters.put("reportPrint",reportPrint);
			
			//��������
			String jasperFilename = "acceptanceNotice";
			parameters.put("reportName",jasperFilename);
			parameters.put("format","pdf");
			
			ModelAndView mv = new ModelAndView(jasperFilename+".report",parameters);
			return mv;
	}
	
	/**
	 * 
	 * ��ӡ˰���걨�� 
	 *
	 * @author Joyon
	 * @return
	 * @since JDK 1.6
	 */
	public ModelAndView printTaxReturn(Row row){
		Map<String, Object> parameters = new HashMap<String, Object>();
		String proc_id = row.getString("proc_id");
		Map resultMap = FacadeFactory.getCommonFacade().sendLandTax(proc_id);
		
		parameters.put("tax_list", resultMap.get("taxList"));
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		parameters.put("SUBREPORT_DIR", request.getSession().getServletContext().getRealPath("/report"));
		ServletContext servletContext =request.getSession().getServletContext();
		 JasperPrint jasperPrint1 = null;
		 try {
			jasperPrint1 = JasperFillManager.fillReport(servletContext.getRealPath("/report/report_tax_return.jasper"),parameters, reportFacade.getConn());
		} catch (JRException e) {
			
			// TODO Auto-generated catch block 
			e.printStackTrace();
			
		}
		 
		 List<JasperPrint> jasperPrintList = new ArrayList<JasperPrint>();
		 jasperPrintList.add(jasperPrint1);
		 
		  ReportPrint reportPrint = new ReportPrint();
		   reportPrint.setJasperList(jasperPrintList);
		   
			parameters.put("reportPrint",reportPrint);
			
			//��������
			String jasperFilename = "acceptanceNotice";
			parameters.put("reportName",jasperFilename);
			parameters.put("format","pdf");
			
			ModelAndView mv = new ModelAndView(jasperFilename+".report",parameters);
			return mv;
	}
	
	/**
	 * 
	 * ��ӡ�ɿ�֪ͨ��
	 *
	 * @author Joyon
	 * @return
	 * @since JDK 1.6
	 */
	public ModelAndView printPaymentNotice(Row row){
		Map<String, Object> parameters = new HashMap<String, Object>();
		String proc_id = row.getString("proc_id");
		Map resultMap = FacadeFactory.getCommonFacade().sendLandTax(proc_id);
		
		parameters.put("price_list", resultMap.get("paymentList"));
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		parameters.put("SUBREPORT_DIR", request.getSession().getServletContext().getRealPath("/report"));
		ServletContext servletContext =request.getSession().getServletContext();
		 JasperPrint jasperPrint1 = null;
		 try {
			jasperPrint1 = JasperFillManager.fillReport(servletContext.getRealPath("/report/report_payment_notice.jasper"),parameters, reportFacade.getConn());
		} catch (JRException e) {
			
			// TODO Auto-generated catch block 
			e.printStackTrace();
			
		}
		 
		 List<JasperPrint> jasperPrintList = new ArrayList<JasperPrint>();
		 jasperPrintList.add(jasperPrint1);
		 
		  ReportPrint reportPrint = new ReportPrint();
		   reportPrint.setJasperList(jasperPrintList);
		   
			parameters.put("reportPrint",reportPrint);
			
			//��������
			String jasperFilename = "acceptanceNotice";
			parameters.put("reportName",jasperFilename);
			parameters.put("format","pdf");
			
			ModelAndView mv = new ModelAndView(jasperFilename+".report",parameters);
			return mv;
	}
	
	/**
	 * 
	 * ��ӡ����ͳ��.
	 *
	 * @author Joyon
	 * @return
	 * @throws GeneralException 
	 * @since JDK 1.6
	 */
	public ModelAndView printUrage(Row row) throws GeneralException{
		Map<String, Object> parameters = new HashMap<String, Object>();
		
		//Map resultMap = FacadeFactory.getCommonFacade().sendLandTax(proc_id);
		
		//parameters.put("price_list", resultMap.get("paymentList"));
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		//parameters.put("SUBREPORT_DIR", ServletActionContext.getServletContext().getRealPath("/report"));
		Map strMap = (Map) request.getSession().getAttribute("strMap");   
		Vector dataList = ChartDataSourceProvider.getQueryResult(strMap);
		ServletContext servletContext =request.getSession().getServletContext();
		String path_url = servletContext.getRealPath ("/report/report-urage.jasper");
		
		 JasperPrint jasperPrint1 = null;
		 try {
			//jasperPrint1 = JasperFillManager.fillReport(jasperReport,parameters, new ChartDataSource(dataList));
			 jasperPrint1 = JasperFillManager.fillReport(servletContext.getRealPath ("/report/report-urage.jasper"), null, new ChartDataSource(dataList));
		} catch (JRException e) {
			throw new GeneralException(e.getLocalizedMessage());
		}
		 
		 List<JasperPrint> jasperPrintList = new ArrayList<JasperPrint>();
		 jasperPrintList.add(jasperPrint1);
		 
		 //-------------------begin-----------------------------
		 ReportPrint reportPrint = new ReportPrint();
		 reportPrint.setJasperList(jasperPrintList);
		 parameters.put("reportPrint",reportPrint);
			
			//��������
			String jasperFilename = "report-urage";
			parameters.put(ReportView.REPORT_NAME,jasperFilename);
			parameters.put(ReportView.FORMAT,ReportView.IMG);
			
			ModelAndView mv = new ModelAndView(jasperFilename+".report",parameters);
			return mv;
		 //return resultListToImage(jasperPrintList);
	}
	
	
	/**
	 * 
	 * ��ӡ����֪ͨ��
	 *
	 * @author Joyon
	 * @return
	 * @since JDK 1.6
	 */
	public ModelAndView printCorrectionNotice(Row row){
		Map<String, Object> parameters = new HashMap<String, Object>();
		String proc_id = row.getString("proc_id");
		//��ǰ��������ҵ��
		BusinessMain  cur_busiiness = FacadeFactory.getCommonFacade().getBusinessMainByProcId(proc_id );
		String reg_code =cur_busiiness.getReg_code();
		
		
		proc_id = FacadeFactory.getCommonFacade().getBusinessMainByBusid(FacadeFactory.getCommonFacade().getBusinessMainByProcId(proc_id).getLast_bus_id()).getProc_id();
		
		//��ʱ����ϵͳ�ǲ���ȥ     ʵ�ʲ����д˴�proc_idӦ�õ���ǰһ��ҵ�������ID
		BusinessMain businessMain = FacadeFactory.getCommonFacade().getBusinessMainByProcId(proc_id);
		
		//��ȡ����������
		List<Applicant> applicantList = FacadeFactory.getApplicantFacade().getApplicantListByProcId(proc_id);
		String apps_name = "";
		for(Applicant tmpApplicant:applicantList){
			apps_name+=" "+tmpApplicant.getApp_name();
		}
		
		//��ȡ�Ǽ�����
		String bus_type =  FacadeFactory.getCommonFacade().getBustypenameByBusid(businessMain.getBus_id());
		
		//��ȡҵ������ʱ��
		String rec_date_str = " ";
		ProcessInst processInst = FacadeFactory.getWorkflowFacade().getProcessInstByProcid(proc_id);
		if(processInst!=null){
			Date procStartdate = processInst.getStartDate();
			
			rec_date_str = com.szhome.cq.utils.DateUtils.date2String(procStartdate, "yyyy��MM��dd��");
		}
		
		
		//���ԭ��   
		String reason = "�����Ϲ涨";
		MaterialReplenish materialReplenish = null;
		try {
			 materialReplenish = FacadeFactory
					.getRecmaterialFacade().getMaterialReplenishByregcode(
							businessMain.getReg_code());
			 String reason_code = materialReplenish.getRepl_reson();
			 if(reason_code.equals("1")){
				 reason="����ȫ";
			 }else if(reason_code.equals("2")){
				 
			 }else if(reason_code.equals("3")){
				 reason="����ȫ�Ҳ����Ϲ涨";
			 }
		} catch (Exception e) {
			LogUtil.error("RecmaterialAction.updateMaterialreplenish ��ȡ����������Ϣ����"+e.getMessage());
		}
		
		
		
		
		parameters.put("bus_id", businessMain.getBus_id());
		parameters.put("REC_DATE", rec_date_str);
		parameters.put("APPS_NAME", apps_name);
		parameters.put("REASON", reason);
		parameters.put("REG_TYPE", bus_type);
		parameters.put("REG_CODE", reg_code);
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		ServletContext servletContext =request.getSession().getServletContext();
		parameters.put("SUBREPORT_DIR", servletContext.getRealPath("/report"));
		 JasperPrint jasperPrint1 = null;
		 try {
			jasperPrint1 = JasperFillManager.fillReport(servletContext.getRealPath("/report/correction.jasper"),parameters, reportFacade.getConn());
		} catch (JRException e) {
			
			// TODO Auto-generated catch block 
			e.printStackTrace();
			
		}
		 
		 List<JasperPrint> jasperPrintList = new ArrayList<JasperPrint>();
		 jasperPrintList.add(jasperPrint1);
		 
		  ReportPrint reportPrint = new ReportPrint();
		   reportPrint.setJasperList(jasperPrintList);
		   
			parameters.put("reportPrint",reportPrint);
			
			//��������
			String jasperFilename = "acceptanceNotice";
			parameters.put("reportName",jasperFilename);
			parameters.put("format","pdf");
			
			ModelAndView mv = new ModelAndView(jasperFilename+".report",parameters);
			return mv;
	}

	
}
