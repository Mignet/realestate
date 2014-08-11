package com.szhome.cq.web.reportmanage;

import java.util.ArrayList;
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
import com.szhome.cq.business.IPresaleFacade;
import com.szhome.cq.common.SQLCriteriaBean;
import com.szhome.cq.delegate.BaseDelegate;
import com.szhome.cq.utils.Constants;
import com.szhome.cq.utils.Util;
import com.szhome.cq.utils.web.PresaleReportServiceUtil;


public class PresaleReportAction extends BaseDelegate{

	//
    IPresaleFacade presaleFacade = FacadeFactory.getPresaleFacade();               ///得到预售备案服务Facade
	//
	private String REALESTATE_PRESALE_SELECTRPT = Util.getMessage(Constants.MODULE_REPORT, "report.realestate_presale_selectrpt");
	//预售备案Id
//	private String pre_rec_id;
	
	static String[] captions = {"presale.caption","presale.projectname","presale.presalepermit","presale.approve_date",
								"presale.developer","presale.purchaser","presale.contractno","presale.con_pric",
								"presale.presale_area","presale.isrecord","presale.recorddate","presale.recordnote",
								"presale.finished_area","common.colon","common.centiare","building.nameandno",
								"parcel.location","parcel.parcel_code","house.roomno","house.housetype","house.usage",
								"house.build_area","house.taonei_area","house.fentan_area","common.print_date","printdatetime",
								"register.name","register.no.","register.share","register.identity_card"};
	
	//二元数组,数组值意义,1.数据表里的字段,2.实体对象对应字段,3.类型区分【1:代表字符串类型,2:代表时间类型,3:代表日期加时间,4:代表金额类型】
		static final String[][] options = {{"tab.pre_rec_id","preRecId","1"}};
	
	
	public ModelAndView report(Row row){
		    String jasperFilePath = null;
		    String jrxmlFilePath = null;
		    ModelAndView mv = null;
		    String jasperFileName = REALESTATE_PRESALE_SELECTRPT+".jasper"; 
		    String jrxmlFileName = REALESTATE_PRESALE_SELECTRPT+".jrxml"; 
		    String jasperRelativePath = "/report/" + jasperFileName;
		    String jrxmlRelativePath = "/report/source/" + jrxmlFileName;
		    RequestAttributes ra = RequestContextHolder.getRequestAttributes();
			HttpServletRequest request = ((ServletRequestAttributes)ra).getRequest();
		    //获得报表jasper文件的完整路径
		    jasperFilePath = PresaleReportServiceUtil.getJasperFileFullPath(request, jasperRelativePath, jasperFileName);
		    //获得报表jrxml文件的完整路径
		    jrxmlFilePath = PresaleReportServiceUtil.getJasperFileFullPath(request, jrxmlRelativePath, jrxmlFileName);
		    List<Map<String,Object>> datas = null;
			Map<String, Object> parameters = new HashMap<String, Object>();
			
			Map<String,Object> paramMap = new HashMap<String,Object>();
			//String inselectOptions = Util.getInSubselectOptions(mort_id);
			String flag = Constants.CRITERIA_INSUBSELECT;
			Object obj = null;
			String pre_rec_id = row.getString("pre_rec_id");
			if(flag.equals(Constants.CRITERIA_INSUBSELECT)){
				obj = pre_rec_id .split(",");
			}else{
				obj = pre_rec_id;
			}
			paramMap.put("preRecId", obj);
			paramMap.put("flag", flag);
			
			SQLCriteriaBean bean = PresaleReportServiceUtil.getPresaleReportSQLCriteria(paramMap, parameters, options);
			
			datas = presaleFacade.getPresalesBypreRecId(bean.getSqlMap(), bean.getValueMap());
			
			List<Map<String,Object>> subdatas = null;
			for(int i=0;i<datas.size();i++){
				Map<String,Object> tempMap = datas.get(i);
				SQLCriteriaBean bean2 = null;
				try {
					String id = tempMap.get("PRE_REC_ID").toString();
					subdatas = new ArrayList<Map<String,Object>>();
					if(id.equals("102")){
						Map<String,Object> data1 = new HashMap<String,Object>();
						data1.put("APP_NAME", "王五");
						data1.put("APP_CER_NO", "452601198106259865");
						data1.put("APP_PORT", "60%");
						Map<String,Object> data2 = new HashMap<String,Object>();
						data2.put("APP_NAME", "李四");
						data2.put("APP_CER_NO", "452601198606259862");
						data2.put("APP_PORT", "40%");
						subdatas.add(data1);
						subdatas.add(data2);
						tempMap.put("subdata", subdatas);
						}else if(id.equals("105")){
							Map<String,Object> data1 = new HashMap<String,Object>();
							data1.put("APP_NAME", "赵六");
							data1.put("APP_CER_NO", "451601198106259865");
							data1.put("APP_PORT", "68%");
							Map<String,Object> data2 = new HashMap<String,Object>();
							data2.put("APP_NAME", "张三");
							data2.put("APP_CER_NO", "454601198606259862");
							data2.put("APP_PORT", "32%");
							subdatas.add(data1);
							subdatas.add(data2);
							tempMap.put("subdata", subdatas);
					}
					/*if(tempMap.get("REG_UNIT_TYPE").toString().equals(WbfConstants.PARCEL)){
						paramMap.put("userightId",tempMap.get("USERIGHT_ID"));
						paramMap.put("flag", "");
						bean2 = PropertyRightReportServiceUtil.getHolderSQLCriteria(paramMap, parameters, new String[][]{options[0]});
						//subdatas = bookFacade.getUserightHolderLst(bean2.getSqlMap(), bean2.getValueMap());
						tempMap.put("SUBDATA", subdatas);
					}else if(tempMap.get("REG_UNIT_TYPE").toString().equals(WbfConstants.HOUSE)){
						paramMap.put("ownershipId",tempMap.get("OWNERSHIP_ID"));
						paramMap.put("flag", "");
						bean2 = PropertyRightReportServiceUtil.getHolderSQLCriteria(paramMap, parameters, new String[][]{options[1]});
						//subdatas = bookFacade.getOwnershipHolderLst(bean2.getSqlMap(), bean2.getValueMap());
						tempMap.put("SUBDATA", subdatas);
					}*/
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
			
			try {
				PresaleReportServiceUtil.setReportCaptions("report", parameters, captions);
				
				mv = resultToPDF("PresaleReport",jasperFilePath,jrxmlFilePath,parameters,datas);
				
			}catch(JRException e) {
				logger.error("PresaleReportAction--report:"+e.getMessage(), e);
			}catch (Exception e) {
				logger.error("PresaleReportAction--report:"+e.getMessage(), e);
			}
			return mv;
     }


	
}

