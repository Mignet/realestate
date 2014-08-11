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
import com.szhome.cq.business.IMortgageFacade;
import com.szhome.cq.common.SQLCriteriaBean;
import com.szhome.cq.delegate.BaseDelegate;
import com.szhome.cq.utils.Constants;
import com.szhome.cq.utils.Util;
import com.szhome.cq.utils.web.MortgageReportServiceUtil;


public class MortgageReportAction extends BaseDelegate{

	//
    IMortgageFacade mortgageFacade = FacadeFactory.getMortgageFacade();               ///�õ���Ѻ����Facade
	//
	private String REALESTATE_MORTGAGE_SELECTRPT = Util.getMessage(Constants.MODULE_REPORT, "report.realestate_mortgage_selectrpt");
	//��ѺId
//	private String mort_id;
	
	static String[] captions = {"mortgage.caption","mortgage.reg_code","mortgage.mort_no","mortgage.pre_reg_code",
								"mortgage.borrower","mortgage.full_address","mortgage.contractno",
								"mortgage.mortgagee","mortgage.mortgager","mortgage.start_date",
								"mortgage.build_area","register.cer_type","register.cer_no","mortgage.money",
								"register.house_deal_contractno","register.file_no","register.reg_date",
								"register.contract_date","register.cancel_date","register.cancel_note",
								"register.identity_card","register.loan_purpose","register.loan",
								"register.suggest_price","parcel.parcel_code","building.location",
								"common.colon","common.left_parentheses","common.right_parentheses",
								"common.centiare","common.rmb","common.hkd","common.usd","common.print_date"};
	
	//��Ԫ����,����ֵ����,1.���ݱ�����ֶ�,2.ʵ������Ӧ�ֶ�,3.�������֡�1:�����ַ�������,2:����ʱ������,3:�������ڼ�ʱ��,4:���������͡�
		static final String[][] options = {{"tab.mort_id","mortId","1"}};
	
	
	public ModelAndView report(Row row){
		    String jasperFilePath = null;
		    String jrxmlFilePath = null;
		    ModelAndView mv = null;
		    String jasperFileName = REALESTATE_MORTGAGE_SELECTRPT+".jasper"; 
		    String jrxmlFileName = REALESTATE_MORTGAGE_SELECTRPT+".jrxml"; 
		    String jasperRelativePath = "/report/" + jasperFileName;
		    String jrxmlRelativePath = "/report/source/" + jrxmlFileName;
		    RequestAttributes ra = RequestContextHolder.getRequestAttributes();
			HttpServletRequest request = ((ServletRequestAttributes)ra).getRequest();
		    //��ñ���jasper�ļ�������·��
		    jasperFilePath = MortgageReportServiceUtil.getJasperFileFullPath(request, jasperRelativePath, jasperFileName);
		    //��ñ���jrxml�ļ�������·��
		    jrxmlFilePath = MortgageReportServiceUtil.getJasperFileFullPath(request, jrxmlRelativePath, jrxmlFileName);
		    List<Map<String,Object>> datas = null;
			Map<String, Object> parameters = new HashMap<String, Object>();
			
			Map<String,Object> paramMap = new HashMap<String,Object>();
			//String inselectOptions = Util.getInSubselectOptions(mort_id);
			paramMap.put("mortId", row.getString("mort_id").split(","));
			paramMap.put("flag", Constants.CRITERIA_INSUBSELECT);
			
			SQLCriteriaBean bean = MortgageReportServiceUtil.getMortReportSQLCriteria(paramMap, parameters, options);
			
			datas = mortgageFacade.getMortsBymortId(bean.getSqlMap(), bean.getValueMap());
			
			try {
				MortgageReportServiceUtil.setReportCaptions("report", parameters, captions);
				mv = resultToPDF("MortgageReport",jasperFilePath,jrxmlFilePath,parameters,datas);
			} catch (JRException e){
				logger.error("MortgageReportAction--report:"+e.getMessage(), e);
			} catch (Exception e) {
				logger.error("MortgageReportAction--report:"+e.getMessage(), e);
			}
			return mv;
     }
}

