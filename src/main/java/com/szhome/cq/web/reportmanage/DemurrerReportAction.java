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
import com.szhome.cq.business.IBookFacade;
import com.szhome.cq.business.IDissentFacade;
import com.szhome.cq.common.SQLCriteriaBean;
import com.szhome.cq.delegate.BaseDelegate;
import com.szhome.cq.utils.Constants;
import com.szhome.cq.utils.Util;
import com.szhome.cq.utils.WbfConstants;
import com.szhome.cq.utils.web.DemurrerReportServiceUtil;
import com.szhome.security.ext.UserInfo;


public class DemurrerReportAction extends BaseDelegate{
	//
    IDissentFacade dissentFacade = FacadeFactory.getDissentFacade();               ///�õ��������Facade
    IBookFacade bookFacade = FacadeFactory.getBookFacade();                       ///�õ��ǼǱ�����Facade
	//
	private String REALESTATE_DEMURRER_SELECTRPT = Util.getMessage(Constants.MODULE_REPORT, "report.realestate_demurrer_selectrpt");
	
	static String[] captions = {"printdatetime","register.cancel_date","register.reg_date","common.printor",
								"common.colon","common.print_date","house.itself","register.applicantname",
								"parcel.land_address","demurrer.caption","demurrer.reg_info","demurrer.applicantinfo",
								"demurrer.inofobjection","demurrer.remark","demurrer.reg_code","demurrer.diss_item",
								"demurrer.identity_card","demurrer.inofobjectionname","demurrer.realestateidtype",
								"demurrer.id_code","demurrer.cq_reg_code","demurrer.ioo_realestate_nameandroom","parcel.itself"};
	
	//��Ԫ����,����ֵ����,1.���ݱ�����ֶ�,2.ʵ������Ӧ�ֶ�,3.�������֡�1:�����ַ�������,2:����ʱ������,3:�������ڼ�ʱ��,4:���������͡�
		static final String[][] options = {{"tab.demurrer_id","demurrerId","1"},
			                               {"tab.reg_unit_type","reg_unit_type","1"}};
		static final String[][] bookcodeoptions = {{"tab.book_code","bookcode","1"}};
	
	
	/**
	 * ����Ǽǲ���ѯ��Ϣ�����ӡ
	 * @return
	 * @throws GeneralException 
	 */
	public ModelAndView report(Row row) throws GeneralException{
		    String jasperFilePath = null;
		    String jrxmlFilePath = null;
		    ModelAndView mv = null;
		    String jasperFileName = REALESTATE_DEMURRER_SELECTRPT+".jasper"; 
		    String jrxmlFileName = REALESTATE_DEMURRER_SELECTRPT+".jrxml"; 
		    String jasperRelativePath = "/report/" + jasperFileName;
		    String jrxmlRelativePath = "/report/source/" + jrxmlFileName;
		    RequestAttributes ra = RequestContextHolder.getRequestAttributes();
			HttpServletRequest request = ((ServletRequestAttributes)ra).getRequest();
		    //��ñ���jasper�ļ�������·��
		    jasperFilePath = DemurrerReportServiceUtil.getJasperFileFullPath(request, jasperRelativePath, jasperFileName);
		    //��ñ���jrxml�ļ�������·��
		    jrxmlFilePath = DemurrerReportServiceUtil.getJasperFileFullPath(request, jrxmlRelativePath, jrxmlFileName);
		    List<Map<String,Object>> datas = null;
			Map<String, Object> parameters = new HashMap<String, Object>();
			
			Map<String,Object> paramMap = new HashMap<String,Object>();
			//String inselectOptions = Util.getInSubselectOptions(mort_id);
			paramMap.put("items", row.getString("items"));
			paramMap.put("flag", "");
			
			SQLCriteriaBean bean = DemurrerReportServiceUtil.getDemurrerReportSQLCriteria(paramMap, parameters, options);
			
			datas = dissentFacade.getDemurrersBydemurrerId(bean.getSqlMap(), bean.getValueLst());
			
			for(int i=0;i<datas.size();i++){
				Map<String,Object> tempMap = datas.get(i);
				SQLCriteriaBean bean2 = null;
				List<Map<String,Object>> subdatas = null;
				try {
					if(tempMap.get("REG_UNIT_TYPE").toString().equals(WbfConstants.PARCEL)){
						paramMap.put("bookcode",tempMap.get("BOOK_CODE"));
						paramMap.put("flag", "");
						bean2 = DemurrerReportServiceUtil.getDemurrerSQLCriteria(paramMap, parameters, bookcodeoptions);
						subdatas = bookFacade.getUserightInfoLst(bean2.getSqlMap(), bean2.getValueMap());
						tempMap.putAll(subdatas.get(0));
					}else if(tempMap.get("REG_UNIT_TYPE").toString().equals(WbfConstants.HOUSE)){
						paramMap.put("bookcode",tempMap.get("BOOK_CODE"));
						paramMap.put("flag", "");
						bean2 = DemurrerReportServiceUtil.getDemurrerSQLCriteria(paramMap, parameters, bookcodeoptions);
						subdatas = bookFacade.getOwnershipInfoLst(bean2.getSqlMap(), bean2.getValueMap());
						tempMap.putAll(subdatas.get(0));
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
			
			try {
				DemurrerReportServiceUtil.setReportCaptions("report", parameters, captions);
				//get current system user
				UserInfo ui = getOperatorInfo();
				parameters.put("printor"  ,ui.getUserName());
				mv = resultToPDF("DemurrerReport",jasperFilePath,jrxmlFilePath,parameters,datas);
			} catch (JRException e){
				logger.error("DemurrerReportAction--report:"+e.getMessage(), e);
			} catch (Exception e) {
				logger.error("DemurrerReportAction--report:"+e.getMessage(), e);
			}
			return mv;
     }
}

