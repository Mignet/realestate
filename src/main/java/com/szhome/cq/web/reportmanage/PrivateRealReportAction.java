package com.szhome.cq.web.reportmanage;

import java.util.ArrayList;
import java.util.Date;
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
import com.szhome.cq.business.IPrivateRealFacade;
import com.szhome.cq.common.SQLCriteriaBean;
import com.szhome.cq.delegate.BaseDelegate;
import com.szhome.cq.utils.Constants;
import com.szhome.cq.utils.DateUtils;
import com.szhome.cq.utils.Util;
import com.szhome.cq.utils.web.PrivateRealReportServiceUtil;
import com.szhome.security.ext.UserInfo;


public class PrivateRealReportAction extends BaseDelegate{
	//
    IPrivateRealFacade privaterealFacade = FacadeFactory.getPrivateRealFacade();               ///�õ����˲�Ȩ����Facade
	//
	private String REALESTATE_PERSON_SELECTRPT = Util.getMessage(Constants.MODULE_REPORT, "report.realestate_person_selectrpt");
//	private String holder_id;
	
	static String[] captions = {"register.propertyregistration","system.city","person.idno","person.presalecontracts",
								"person.policyhouseconditions","person.selectexpirydate","common.print_date",
								"printdatetime","common.gong","common.printer","common.one","common.two",
								"common.three","person.caption","common.left_bracket","common.right_bracket",
								"demurrer.remark","common.colon","common.ye","common.di","common.comma",
								"common.serialno","register.cer_no","house.house_kind","common.area",
								"common.centiare","house.flatlet_usage","register.share","register.price",
								"register.state","person.transfertime","person.realestate_nameandbuildno",
								"house.roomno","building.build_no","house.usage","building.nameandno","register.projectname"};
	
	//��Ԫ����,����ֵ����,1.���ݱ�����ֶ�,2.ʵ������Ӧ�ֶ�,3.�������֡�1:�����ַ�������,2:����ʱ������,3:�������ڼ�ʱ��,4:���������͡�
	static final String[][] options = {{"tab.holder_id","holderId","1"}};
	
	public ModelAndView report(Row row) throws GeneralException{
		    String jasperFilePath = null;
		    String jrxmlFilePath = null;
		    ModelAndView mv = null;
		    String jasperFileName = REALESTATE_PERSON_SELECTRPT+".jasper"; 
		    String jrxmlFileName = REALESTATE_PERSON_SELECTRPT+".jrxml"; 
		    String jasperRelativePath = "/report/" + jasperFileName;
		    String jrxmlRelativePath = "/report/source/" + jrxmlFileName;
		    RequestAttributes ra = RequestContextHolder.getRequestAttributes();
			HttpServletRequest request = ((ServletRequestAttributes)ra).getRequest();
		    //��ñ���jasper�ļ�������·��
		    jasperFilePath = PrivateRealReportServiceUtil.getJasperFileFullPath(request, jasperRelativePath, jasperFileName);
		    //��ñ���jrxml�ļ�������·��
		    jrxmlFilePath = PrivateRealReportServiceUtil.getJasperFileFullPath(request, jrxmlRelativePath, jrxmlFileName);
		  /*  String jasperFilePathsub = null;
		    String jasperFileNamesub = REALESTATE_PERSON_SELECTRPT_SUB+".jasper"; 
		    String jasperRelativePathsub = "/report/" + jasperFileNamesub;
		    //��ñ���jasper�ļ�������·��
		    jasperFilePathsub = PrivateRealReportServiceUtil.getJasperFileFullPath(request, jasperRelativePathsub, jasperFileNamesub);
		    */
		    List<Map<String,Object>> datas = new ArrayList<Map<String,Object>>();
			Map<String, Object> parameters = new HashMap<String, Object>();
			
			Map<String,Object> paramMap = new HashMap<String,Object>();
			//String inselectOptions = Util.getInSubselectOptions(mort_id);
			paramMap.put("holderId", row.getString("holder_id"));//(mort_id=(mort_id==null || mort_id.trim().equals(""))?"111":mort_id).split(","));//mort_id.split(","));
			paramMap.put("flag", "");
			
			SQLCriteriaBean bean = PrivateRealReportServiceUtil.getPrivateRealReportSQLCriteria(paramMap, parameters, options);
			Map<String,Object> dataMap = privaterealFacade.getPrivateRealsById(bean.getSqlMap(), bean.getValueMap());
			if(dataMap!=null)
			datas.add(dataMap);
			List<Map<String,Object>> retlst = new ArrayList<Map<String,Object>>();
			List<Map<String,Object>> retlst2 = new ArrayList<Map<String,Object>>();
			List<Map<String,Object>> retlst3 = new ArrayList<Map<String,Object>>();
			/*String book_code = dataMap.get("BOOK_CODE").toString();
			if(Util.isNotNull2Empty(book_code)){
               
			}*/
			/*ReportDataSource subdata = null;
			if( datas!=null && datas.size()>0 ) {

				subdata = new ReportDataSource(datas);
			}*/
			try {
				PrivateRealReportServiceUtil.setReportCaptions("report", parameters, captions);
			} catch (Exception e) {
				logger.error("PrivateRealReportAction--report:"+e.getMessage(), e);
			}
			
			
			Map<String,Object> data1 = new HashMap<String, Object>();
			data1.put("AREG_DATE", "2013-05-26");
			data1.put("STATE", "��Ѻ");
			data1.put("REG_VALUE", "120000");
			data1.put("PORTION", "100%");
			data1.put("FLATLET_USAGE", "סլ");
			data1.put("TAONEI_AREA", "80");
			data1.put("AHOUSE_KIND", "������ס��");
			data1.put("CER_NO", "2522321321");
			data1.put("BUILDING_NAME", "�Ǻ�С��");
			data1.put("BUILD_NO", "B��");
			data1.put("ROOMNAME", "303��");
			Map<String,Object> data2 = new HashMap<String, Object>();
			data2.put("AREG_DATE", "2012-05-26");
			data2.put("STATE", "��Ѻ");
			data2.put("REG_VALUE", "1520000");
			data2.put("PORTION", "80%");
			data2.put("FLATLET_USAGE", "סլ");
			data2.put("TAONEI_AREA", "123");
			data2.put("AHOUSE_KIND", "��Ʒ��");
			data2.put("CER_NO", "2522321322");
			data2.put("BUILDING_NAME", "ͩ�ֹ�Ԣ");
			data2.put("BUILD_NO", "B��");
			data2.put("ROOMNAME", "303��");
			Map<String,Object> data3 = new HashMap<String, Object>();
			data3.put("AREG_DATE", "2011-05-26");
			data3.put("STATE", "��Ѻ");
			data3.put("REG_VALUE", "1520000");
			data3.put("PORTION", "60%");
			data3.put("FLATLET_USAGE", "סլ");
			data3.put("TAONEI_AREA", "126");
			data3.put("AHOUSE_KIND", "��Ʒ��");
			data3.put("CER_NO", "2522321323");
			data3.put("BUILDING_NAME", "ͳͳ��԰");
			data3.put("BUILD_NO", "B��");
			data3.put("ROOMNAME", "303��");
			Map<String,Object> data11 = new HashMap<String, Object>();
			data11.put("PRO_NAME", "�Ǻ�С��");
			data11.put("FLATLET_USAGE", "סլ");
			data11.put("BUILDING_NAME", "�Ǻ�С��");
			data11.put("BUILD_NO", "B��");
			data11.put("ROOMNAME", "303��");
			Map<String,Object> data12 = new HashMap<String, Object>();
			data12.put("PRO_NAME", "ͩ�ֹ�Ԣ");
			data12.put("FLATLET_USAGE", "סլ");
			data12.put("BUILDING_NAME", "ͩ�ֹ�Ԣ");
			data12.put("BUILD_NO", "B��");
			data12.put("ROOMNAME", "303��");
			Map<String,Object> data13 = new HashMap<String, Object>();
			data13.put("PRO_NAME", "ͳͳ��԰");
			data13.put("FLATLET_USAGE", "סլ");
			data13.put("BUILDING_NAME", "ͳͳ��԰");
			data13.put("BUILD_NO", "B��");
			data13.put("ROOMNAME", "303��");
			Map<String,Object> data21 = new HashMap<String, Object>();
			data21.put("PRO_NAME", "������԰");
			data21.put("FLATLET_USAGE", "סլ");
			data21.put("BUILDING_NAME", "������԰");
			data21.put("BUILD_NO", "B��");
			data21.put("ROOMNAME", "403��");
			Map<String,Object> data22 = new HashMap<String, Object>();
			data22.put("PRO_NAME", "����С��");
			data22.put("FLATLET_USAGE", "סլ");
			data22.put("BUILDING_NAME", "����С��");
			data22.put("BUILD_NO", "B��");
			data22.put("ROOMNAME", "323��");
			Map<String,Object> data23 = new HashMap<String, Object>();
			data23.put("PRO_NAME", "��ƻ�԰");
			data23.put("FLATLET_USAGE", "סլ");
			data23.put("BUILDING_NAME", "��ƻ�԰");
			data23.put("BUILD_NO", "B��");
			data23.put("ROOMNAME", "313��");
			retlst.add(data1);
			retlst.add(data2);
			retlst.add(data3);
			retlst2.add(data11);
			retlst2.add(data12);
			retlst2.add(data13);
			retlst3.add(data21);
			retlst3.add(data22);
			retlst3.add(data23);
			parameters.put("SubData", retlst);
			parameters.put("SubData2", retlst2);
			parameters.put("SubData3", retlst3);
			parameters.put("SELECT_END_DATE",DateUtils.date2String(new Date(),"yyyy��MM��dd�� HHʱmm��"));
			//get current system user
			UserInfo ui = getOperatorInfo();
			parameters.put("printor"  ,ui.getUserName());
			try {
				mv =  resultToPDF("PrivateRealReport",jasperFilePath,jrxmlFilePath,parameters,datas);
			} catch (JRException e) {
				logger.error("PrivateRealReportAction--report:"+e.getMessage(), e);
			}
			return mv;
     }
}

