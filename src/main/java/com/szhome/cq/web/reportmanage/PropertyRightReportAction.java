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
import com.szhome.cq.business.IPropertyRightFacade;
import com.szhome.cq.common.SQLCriteriaBean;
import com.szhome.cq.delegate.BaseDelegate;
import com.szhome.cq.utils.Constants;
import com.szhome.cq.utils.Util;
import com.szhome.cq.utils.WbfConstants;
import com.szhome.cq.utils.web.PropertyRightReportServiceUtil;
import com.szhome.security.ext.UserInfo;


public class PropertyRightReportAction extends BaseDelegate{
	//
    private IPropertyRightFacade propertyRightFacade = FacadeFactory.getPropertyRightFacade();               ///得到产权服务Facade
    private IBookFacade  bookFacade        = FacadeFactory.getBookFacade();                                  //登记簿服务
	//
	private String PROPERTYRIGHT_DATA_SELECTRPT = Util.getMessage(Constants.MODULE_REPORT, "report.propertyright_data_selectrpt");
	//权利Id
//	private String right_id;
//	private String items;
	
	static String[] captions = {"printdate","mortgage.money","ownership.caption","ownership.houserightbook",
								"ownership.cer_no","ownership.purchaseprop","ownership.shiftmode",
								"ownership.assigneeholder","ownership.identity_card","ownership.excursus",
								"common.print_date","common.colon","common.centiare","common.yuan",
								"common.printor","parcel.parcel_code","parcel.land_grade","parcel.land_address",
								"parcel.use_right_type","parcel.real_usage","parcel.parcel_subcode",
								"parcel.use_per","building.nameandno","building.itself","building.build_ld_area",
								"building.floor_area","building.layer_count","house.roomno","house.house_kind",
								"house.house_type","house.flatlet_usage","house.house_strut","house.ft_glebe_area",
								"holder.obligee","holder.corporation","register.share","register.approveddate",
								"register.bus_name","register.reg_code","common.cn_usd","common.cn_hkd",
								"common.dollarsign","ownership.recommendedprice_rmb","ownership.recommendedprice_hkd",
								"ownership.bookprice_rmb","house.ft_glebe_price","house.build_area","parcel.itself",
								"house.itself"};
	
	 //二元数组,数组值意义,1.数据表里的字段,2.实体对象对应字段,3.类型区分【1:代表字符串类型,2:代表时间类型,3:代表日期加时间,4:代表金额类型】
     static final String[][] options = {{"tab.useright_id","userightId","1"},
			                               {"tab.ownership_id","ownershipId","1"}};
		/*static final String[][] uroptions = {{"tab.useright_id","userightId","1"}};
		static final String[][] osoptions = {{"tab.ownership_id","ownershipId","1"}};*/
		
	
	
	public ModelAndView report(Row row) throws GeneralException{
		    String jasperFilePath = null;
		    String jrxmlFilePath = null;
		    ModelAndView mv = null;
		    String jasperFileName = PROPERTYRIGHT_DATA_SELECTRPT+".jasper"; 
		    String jrxmlFileName = PROPERTYRIGHT_DATA_SELECTRPT+".jrxml"; 
		    String jasperRelativePath = "/report/" + jasperFileName;
		    String jrxmlRelativePath = "/report/source/" + jrxmlFileName;
		    RequestAttributes ra = RequestContextHolder.getRequestAttributes();
			HttpServletRequest request = ((ServletRequestAttributes)ra).getRequest();
		    //获得报表jasper文件的完整路径
		    jasperFilePath = PropertyRightReportServiceUtil.getJasperFileFullPath(request, jasperRelativePath, jasperFileName);
		    //获得报表jrxml文件的完整路径
		    jrxmlFilePath = PropertyRightReportServiceUtil.getJasperFileFullPath(request, jrxmlRelativePath, jrxmlFileName);
		    List<Map<String,Object>> datas = null;
			Map<String, Object> parameters = new HashMap<String, Object>();
			
			Map<String,Object> paramMap = new HashMap<String,Object>();
			//String inselectOptions = Util.getInSubselectOptions(mort_id);
			paramMap.put("items", row.getString("items"));
			paramMap.put("flag", Constants.CRITERIA_INSUBSELECT);
			
			SQLCriteriaBean bean = PropertyRightReportServiceUtil.getPropertyRightReportSQLCriteria(paramMap, parameters, options);
			
			datas = propertyRightFacade.getPropRightsByrightId(bean.getSqlMap(), bean.getValueMap());
			
			try {
				PropertyRightReportServiceUtil.setReportCaptions("report", parameters, captions);
			} catch (Exception e) {
				logger.error("PropertyRightReportAction--report:"+e.getMessage(), e);
			}
			
			for(int i=0;i<datas.size();i++){
				Map<String,Object> tempMap = datas.get(i);
				SQLCriteriaBean bean2 = null;
				List<Map<String,Object>> subdatas = null;
				try {
					if(tempMap.get("REG_UNIT_TYPE").toString().equals(WbfConstants.PARCEL)){
						paramMap.put("userightId",tempMap.get("USERIGHT_ID"));
						paramMap.put("flag", "");
						bean2 = PropertyRightReportServiceUtil.getHolderSQLCriteria(paramMap, parameters, new String[][]{options[0]});
						subdatas = bookFacade.getUserightHolderLst(bean2.getSqlMap(), bean2.getValueMap());
						tempMap.put("SUBDATA", subdatas);
					}else if(tempMap.get("REG_UNIT_TYPE").toString().equals(WbfConstants.HOUSE)){
						paramMap.put("ownershipId",tempMap.get("OWNERSHIP_ID"));
						paramMap.put("flag", "");
						bean2 = PropertyRightReportServiceUtil.getHolderSQLCriteria(paramMap, parameters, new String[][]{options[1]});
						subdatas = bookFacade.getOwnershipHolderLst(bean2.getSqlMap(), bean2.getValueMap());
						tempMap.put("SUBDATA", subdatas);
					}
				} catch (Exception e) {
					throw new GeneralException(e);
				}
			}
			try {
				//get current system user
				UserInfo ui = getOperatorInfo();
				parameters.put("printor"  ,ui.getUserName());
				mv = resultToPDF("PropertyRightReport",jasperFilePath,jrxmlFilePath,parameters,datas);
			} catch (JRException e) {
				logger.error("PropertyRightReportAction--report:"+e.getMessage(), e);
			}
			return mv;
     }
	
}

