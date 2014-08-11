
package com.szhome.cq.web.common;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import net.sf.json.JSONArray;

import org.apache.tools.ant.util.DateUtils;

import com.plan.commons.Row;
import com.plan.commons.RowImpl;
import com.plan.exceptions.GeneralException;
import com.plan.web.JsonResult;
import com.szhome.commons.log.LogUtil;
import com.szhome.cq.business.BusinessException;
import com.szhome.cq.business.FacadeFactory;
import com.szhome.cq.business.IWorkflowFacade;
import com.szhome.cq.business.vo.Menu;
import com.szhome.cq.delegate.BaseDelegate;
import com.szhome.cq.domain.model.Announcement;
import com.szhome.cq.domain.model.BusMatter;
import com.szhome.cq.domain.model.BusType;
import com.szhome.cq.domain.model.BusinessMain;
import com.szhome.cq.domain.model.Certificate;
import com.szhome.cq.domain.model.ComLanguage;
import com.szhome.cq.domain.model.ConNodeRelation;
import com.szhome.cq.domain.model.ConOffice;
import com.szhome.cq.domain.model.Pigeonhole;
import com.szhome.cq.domain.model.ProcNode;
import com.szhome.cq.domain.model.RecMatConfigure;
import com.szhome.cq.domain.model.Testpaper;
import com.szhome.cq.domain.model.TreeMenu;
import com.szhome.cq.utils.JsonUtil;
import com.szhome.cq.utils.WbfConstants;
import com.szhome.process.client.WorkflowClient;
import com.szhome.process.client.WorkflowClientFacade;
import com.szhome.process.client.WorkflowClientFactory;
import com.szhome.process.entity.Procdef;

/**获取土地、房屋、产权信息等
 * 2013-12-4
 * @author pandalee
 *
 */
public class CertificateAction extends BaseDelegate{
	/*
	//领证人
	private String recperson;
	//领证人证件编号
	private String reccerno;
	//领证人类型
	private String rectype;
	//领证人证件类型
	private String reccertype;	
	//业务id
	private String busid;
	//流程实例表ID
	private String procid;

	//节点ID
	private String nodeid;
	//业务类型ID
	private String bus_typeid;


	
	private Menu menu;*/
//	/** 工作流客户端统一接口 */
	private WorkflowClientFacade workflowClientFacade = WorkflowClientFactory.getWorkflowClientFacade();
//	/** 工作流客户端接口*/
	private WorkflowClient workflowClient = workflowClientFacade.getWorkflowClient();
	IWorkflowFacade wfcade = FacadeFactory.getWorkflowFacade();
	
	String[] arr = {"0101","0102","0103","0104","0105"};
	
	/**
	 * 
	 * getInitInfo:(初始化). <br/>
	 * @author xuzz
	 * @return
	 * @since JDK 1.6
	 */
	public String getInitInfo(Row row){		
//		Map map=new HashMap();
//		map.put("proc_id", proc_id);
		Map m= FacadeFactory.getCommonFacade().getInitInfo(row);
		
		return JsonUtil.map2json(m);
	}
	/**
	 * 获取分栋汇总表数据
	 * @param row
	 * @return
	 * @throws Exception
	 */
	public String getBuildById(Row row)  {
		Map map=new HashMap();
		Map<String,Object> ma = new HashMap<String,Object>();
		String proc_id = row.getString("proc_id");
		String bus_id = FacadeFactory.getExaminationFacade().getRegid(proc_id);
		map.put("bus_id", bus_id);
		List<Map<String, Object>> list= FacadeFactory.getCommonFacade().getBuildById(map);
		String str="";
		try {
			//记录了查询总数
			ma.put("total", list.size());
			//记录了当前页的数据
			ma.put("rows",list);
			str = JsonUtil.map2json(ma);
			System.out.println(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (str.toLowerCase(Locale.CHINESE));
		//return setActionJsonObject(m);
	}
	/**
	 * 获取分户汇总表数据
	 * @param row
	 * @return
	 * @throws Exception
	 */
	public String getHouseById(Row row)  {
		Map<String,Object> ma = new HashMap<String,Object>();
		
		String bus_id = FacadeFactory.getExaminationFacade().getRegid(row.getString("proc_id"));
		Map map=new HashMap();
		map.put("bus_id", bus_id);
		List<Map<String, Object>> list= FacadeFactory.getCommonFacade().getHouseById(map);
		String str="";
		try {
			//记录了查询总数
			ma.put("total", list.size());
			//记录了当前页的数据
			ma.put("rows",list);
			str = JsonUtil.map2json(ma);
			System.out.println(str);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (str.toLowerCase(Locale.CHINESE));
	}
	
	/**
	 * 
	 * getBuildingByID:(这里用一句话描述这个方法的作用). <br/>
	 * @author PANDA
	 * @return
	 * @since JDK 1.6
	 */
	public String getBuildingByID(Row row){
		Row map=new RowImpl();
		String bus_id = FacadeFactory.getExaminationFacade().getRegid(row.getString("proc_id"));
		map.put("bus_id", bus_id);
		Map<String,Object> m= FacadeFactory.getCommonFacade().getLandById(map);
		String str="";
		try {
			str = JsonUtil.map2json(m);
			System.out.println(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (str.toLowerCase(Locale.CHINESE));
	}

	/**
	 * 获取宗地信息
	 * @return
	 * @throws Exception
	 */
	public String getLandById(Row row){
		//-------ajax---------
		Map map=new HashMap();
		String proc_id = row.getString("proc_id");
		String bus_id = FacadeFactory.getExaminationFacade().getRegid(proc_id);
		map.put("bus_id", bus_id);
		Map<String,Object> m= FacadeFactory.getCommonFacade().getLandById(map);
		String str="";
		try {
			str = JsonUtil.map2json(m);
			System.out.println(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this.setActionJsonObject(str.toLowerCase(Locale.CHINESE));
	}   
	
	
	/**
	 * 根据编号查询房地产证具体信息
	 * @return
	 * @throws Exception
	 */
	public String getCertificateByid(Row row){
		String proc_id = row.getString("proc_id");
		BusinessMain business= FacadeFactory.getCommonFacade().getBusinessMainByProcId(proc_id);
		Map map=new HashMap();

		//map.put("proc_id", proc_id);
		// c=FacadeFactory.getCommonFacade().getFdczByid(map);

		map.put("busid", business.getBus_id());
		 Certificate c = null;
		try {
			c = FacadeFactory.getCertificateFacade().getCertificateByid(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonUtil.object2json(c);
	}
	
	/**
	 * 
	 * getCertificateByProcId:(通过流程实例ID获得房地产证信息).
	 *
	 * @author Joyon
	 * @return
	 * @since JDK 1.6
	 */
	public String getCertificateByProcId(Row row){
		List<Certificate> cerList = null;
		String proc_id = row.getString("proc_id");
		try {
			cerList = FacadeFactory.getCertificateFacade().getCertificateListByProcId(proc_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Map<String,Object> resultMap = new HashMap<String,Object>();
		
		//如果房地产证中有数据     把房地产证信息中第一个转为map  返到前端    因为时间没做处理  格式化下
		if(cerList!=null && !cerList.isEmpty()){
			Certificate c = cerList.get(0);
			if(c.getRec_date()!=null){
				resultMap.put("rec_date",DateUtils.format(c.getRec_date(), "yyyy-MM-dd"));
			}
			
				try {
					object2MapWithoutNull(c,resultMap);
				} catch (IllegalArgumentException e) {
					
				} catch (IllegalAccessException e) {
					
				}
		}
		
		return JsonUtil.object2json(resultMap);
//		if(cerList!=null && !cerList.isEmpty()){
//			for(cerList)
//		}
		//return this.setActionJsonObject( JsonUtil.list2json(cerList));
	}
	/*
	 * 将类转换成Map
	 */
	private static void object2MapWithoutNull(Object obj, Map map)throws IllegalArgumentException, IllegalAccessException {
		Field[] fields = obj.getClass().getDeclaredFields();
		for (int j = 0; j < fields.length; j++) {
			fields[j].setAccessible(true);// 设置这个变量不进行访问权限检查 在类里设置的变量可以为private
			if (fields[j].get(obj) != null
				&& (((fields[j].get(obj) instanceof String) && !""
				.equals(fields[j].get(obj))) || ((fields[j]
				.get(obj) instanceof Integer)))) {
				map.put(fields[j].getName(), fields[j].get(obj));
			}
		}
	}
	/**
	 * 保存缮证信息
	 * @param row 前端页面传递进来的需插入到表的数据
	 * @return 保存的数据
	 * @throws Exception
	 */
	public String updateCertificate(Row row){
		Certificate dbCertificate = null;
		try {
			//c.setSzrq(new Date());
			String proc_id = row.getString("proc_id");
			Certificate certificate = new Certificate();
			certificate.setReg_unit_code(row.getString("certificate.reg_unit_code"));
			certificate.setPrinter(row.getString("certificate.printer"));
			certificate.setPrint_date(row.getDate("certificate.print_date"));
			certificate.setCer_state(row.getString("certificate.cer_state"));
			certificate.setCertificate_type(row.getString("certificate.certificate_type"));
			BusinessMain businessMain = FacadeFactory.getCommonFacade().getBusinessMainByProcId(proc_id );
			//如果属于预售备案  则保存信息到房地产证表中
			if(businessMain.getReg_type().equals(WbfConstants.PRE_SALE_BAKUP)){
				List certificateList = FacadeFactory.getCertificateFacade().getCertificateListByProcId(proc_id);
				//如果数据库中存在 更新  不存在 保存
				if(certificateList!=null && certificateList.size()>0){
					dbCertificate = (Certificate) certificateList.get(0);
					dbCertificate.setCer_state(certificate.getCer_state());
					dbCertificate.setCertificate_type(certificate.getCertificate_type());
					dbCertificate.setPrint_date(new Date());
					dbCertificate.setPrinter(getOperatorInfo().getUserName());
					FacadeFactory.getCommonFacade().updateCertificate(dbCertificate);
				}else{
					dbCertificate = new Certificate();
					dbCertificate.setBus_id(businessMain.getBus_id());
					dbCertificate.setCer_state(certificate.getCer_state());
					dbCertificate.setCertificate_type(certificate.getCertificate_type());
					dbCertificate.setPrint_date(new Date());
					dbCertificate.setPrinter(getOperatorInfo().getUserName());
					FacadeFactory.getCertificateFacade().saveCertificate(dbCertificate);
				}
				
			}else{
				
				//20140314000000000001
				 dbCertificate = FacadeFactory.getCertificateFacade().getCertificateByRegunitcodeAndBusid(businessMain.getBus_id(),certificate.getReg_unit_code());
				//certificate.setBus_id(businessMain.getBus_id());
				dbCertificate.setCer_state(certificate.getCer_state());
				dbCertificate.setCertificate_type(certificate.getCertificate_type());
				dbCertificate.setPrint_date(new Date());
				dbCertificate.setPrinter(getOperatorInfo().getUserName());
				FacadeFactory.getCommonFacade().updateCertificate(dbCertificate);
			}
		} catch(Exception e) {
			LogUtil.error(e.getMessage(), e);
			return this.setActionJsonObject(new JsonResult(false, e.getMessage(), null).toJsonString());
		}
		return this.setActionJsonObject(new JsonResult(true, "缮证成功！", null).toJsonString());
	}
	/**
	 * 
	 * saveFileMess:保存组卷信息. <br/>
	 * @author PANDA
	 * @return
	 * @since JDK 1.6
	 */
	public String saveFileMess(Row row){
		//-------ajax---------
		String proc_id = row.getString("proc_id");
		BusinessMain businessMain = FacadeFactory.getCommonFacade().getBusinessMainByProcId(proc_id);
		
//		Map<String,Object> paraMap = new HashMap<String,Object>();
//		paraMap.put("reg_code",businessMain.getReg_code()); 
//		testpaper =FacadeFactory.getCommonFacade().getFileByid(paraMap);
//		if(testpaper !=null){
//			return this.setActionJsonObject(new JsonResult(true, "己组卷，请勿重复保存！", null).toJsonString());
//		}
		try {
			Testpaper testpaper = new Testpaper();
			testpaper.setArrange_time(row.getDate("testpaper.arrange_time"));
			testpaper.setArranger(row.getString("testpaper.arrange"));
			testpaper.setArrange_state(row.getString("testpaper.arrange_state"));
			testpaper.setBus_id(businessMain.getBus_id());
			FacadeFactory.getCommonFacade().saveFileMess(testpaper);	
		} catch(Exception e) {
			LogUtil.error(e.getMessage(), e);
			return this.setActionJsonObject(new JsonResult(false, e.getMessage(), null).toJsonString());
		}
		return this.setActionJsonObject(new JsonResult(true, "组卷信息保存成功！", null).toJsonString());
	}
	/**
	 * 
	 * savePigeoMess:保存归档信息. <br/>
	 * @author PANDA
	 * @return
	 * @since JDK 1.6
	 */
	 public String savePigeoMess(Row row){
		 //-------ajax---------
		 try {
			 Pigeonhole pigeonhole = new Pigeonhole();
			 pigeonhole.setArch_handler(row.getString("pigeonhole.arch_handler"));
			 pigeonhole.setArch_no(row.getString("pigeonhole.arch_no"));
			 pigeonhole.setArch_date(row.getDate("pigeonhole.arch_date"));
			 pigeonhole.setTransfer(row.getString("pigeonhole.transfer"));
			 pigeonhole.setTransfer_date(row.getDate("pigeonhole.transfer_date"));
			 pigeonhole.setArch_handler_no(row.getString("pigeonhole.arch_handler_no"));
			 pigeonhole.setArbox_code(row.getString("pigeonhole.arbox_code"));
			String proc_id = row.getString("proc_id");
			pigeonhole .setBus_id(FacadeFactory.getCommonFacade().getBusinessMainByProcId(proc_id ).getBus_id());
				FacadeFactory.getCommonFacade().savePigeoMess(pigeonhole);	
			} catch(Exception e) {
				LogUtil.error(e.getMessage(), e);
				return this.setActionJsonObject(new JsonResult(false, e.getMessage(), null).toJsonString());
			}
			return this.setActionJsonObject(new JsonResult(true, "归档信息保存成功！", null).toJsonString());
	 
	 }
	 /**
	  * 
	  * saveExaMess:保存审批表信息. <br/>
	  * @author PANDA
	  * @return
	  * @since JDK 1.6
	  */
	 public JsonResult saveExaMess(Row row){
		 try {
				//FacadeFactory.getCommonFacade().saveExaMess(ex);	
			} catch(Exception e) {
				LogUtil.error(e.getMessage(), e);
				return (new JsonResult(false, e.getMessage(), null));
			}
			return (new JsonResult(true, "审批意见保存成功！", null));
	 }
	 /**
	  * 
	  * getExamByid:根据流程实例id获取审批表信息. <br/>
	  * @author PANDA
	  * @return
	  * @since JDK 1.6
	  */
	/* public String getExamByid(Row row){
			Map map=new HashMap();
			String proc_id = row.getString("proc_id");
			map.put("lcslbid", proc_id);
//			 ex =FacadeFactory.getCommonFacade().getExamByid(map);
			return JsonUtil.object2json(ex);
	 }*/
	 /**
	  * 
	  * getFileByid:根据流程实例id获取组卷信息. <br/>
	  * @author PANDA
	  * @return
	  * @since JDK 1.6
	  */
	 public String getFileByid(Row row){
			Map map=new HashMap();
			BusinessMain busMain = null;
			try {
				String proc_id = row.getString("proc_id");
				busMain = FacadeFactory.getCommonFacade()
						.getBusinessMainByProcId(proc_id);
			} catch (Exception e) {
				// TODO: handle exception
			}
			String reg_code = busMain.getReg_code();
			map.put("reg_code", reg_code);
			Testpaper testpaper = FacadeFactory.getCommonFacade().getFileByid(map);
			if(testpaper == null){
				map.put("sign", "faild");
				return JsonUtil.object2json(map);
			}
			 return JsonUtil.object2json(testpaper);
	 }
	 /**
	  * 
	  * getPigeByid:根据流程实例id获取归档信息. <br/>
	  * @author PANDA
	  * @return
	 * @throws GeneralException 
	  * @since JDK 1.6
	  */
	 public String getPigeByid(Row row) throws GeneralException{
		 String proc_id = row.getString("proc_id");
			Map map=new HashMap();
			Map m = new HashMap();
			String reg_code = null;
			try {
				reg_code = FacadeFactory.getCommonFacade().getBusinessMainByProcId(proc_id).getReg_code();
			} catch (Exception e) {
				LogUtil.error("获取业务主表信息出错"+e.getMessage());
			}
			map.put("reg_code", reg_code);
		  try {
			m = FacadeFactory.getCommonFacade().getPigeByid(map);
		} catch (Exception e) {
			LogUtil.error("获取归档信息出错"+e.getMessage());
		}
		m.put("reg_code", reg_code);
		  m.put("arch_handler_no", getOperatorInfo().getUserID());							//工作编号				
		  return JsonUtil.object2json(m);
	 }
	 /**
	  * 
	  * saveComLanguage:保存单条常用语. <br/>
	  * @author PANDA
	  * @return
	  * @since JDK 1.6
	  */
	 public String saveComLanguage(Row row){
		 //-------ajax---------
		 try {
				ComLanguage co = new ComLanguage();
				co.setBus_type_id(row.getString("co.bus_type_id"));
				co.setLanguage_name(row.getString("co.language_name"));
				co.setTemp_type(row.getString("co.temp_type"));
				co.setLanguage_content(row.getString("co.language_content"));
				FacadeFactory.getCommonFacade().saveComLanguage(co);	
			} catch(Exception e) {
				LogUtil.error(e.getMessage(), e);
				return this.setActionJsonObject(new JsonResult(false, e.getMessage(), null).toJsonString());
			}
			return this.setActionJsonObject(new JsonResult(true, "常用语保存成功！", null).toJsonString());	 
	 }
	 
	 /**
	  * 
	  * updateComLanguage:更新单条常用语. <br/>
	  * @author PANDA
	  * @return
	  * @since JDK 1.6
	  */
	 public String updateComLanguage(Row row){	
			try 
			{
				ComLanguage co = new ComLanguage();
				co.setBus_type_id(row.getString("co.bus_type_id"));
				co.setLanguage_name(row.getString("co.language_name"));
				co.setTemp_type(row.getString("co.temp_type"));
				co.setLanguage_content(row.getString("co.language_content"));
				FacadeFactory.getCommonFacade().updateComLanguage(co);			
			} 
			catch (Exception e) {
				e.printStackTrace();
				return this.setActionJsonObject(new JsonResult(false, e.getMessage(), null).toJsonString());
			}	
			return this.setActionJsonObject(new JsonResult(true, "常用语更新成功！", null).toJsonString());
	 } 
	 /**
	  * 
	  * delComLanguage:删除单条常用语. <br/>
	  * @author PANDA
	  * @return
	  * @since JDK 1.6
	  */
	 public String delComLanguage(Row row){
		 //-------ajax---------
			int id = row.getInt("cyyid");
			 try {
				FacadeFactory.getCommonFacade().delComLanguage(id);
			} catch (Exception e) {

				e.printStackTrace();
				return this.setActionJsonObject(new JsonResult(false, e.getMessage(), null).toJsonString());
			}
			
			 return this.setActionJsonObject(new JsonResult(true, "删除常用语成功！", null).toJsonString());			 
	 }
	 
	 /**
	  * 
	  * getComLanById:获取常用语. <br/>
	  * @author PANDA
	  * @return
	  * @since JDK 1.6
	  */
	 public String getComLanById(){
			//-------ajax---------
			//获取流程实例表id
			//int lcslbid = new Integer((String)row.getString("lcslbid"));
			String str = "";
			List<Map<String, Object>> apps = FacadeFactory.getCommonFacade().getComLanByid();
			Map<String,Object> map = new HashMap<String,Object>();
			//记录了查询总数
			map.put("total", apps.size());
			//记录了当前页的数据
			map.put("rows", apps);
			try {
				str = JsonUtil.map2json(map);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return this.setActionJsonObject(str);
		 }
	 
	 /**
	  * 
	  * saveAnnounce:保存单个公告或审批意见. <br/>
	  * @author PANDA
	  * @return
	  * @since JDK 1.6
	  */
	 public String saveAnnounce(Row row){
		 //-------ajax---------
		 try {
			 Announcement an = new Announcement();
			 an.setNotice_pro_person(row.getString("an.notice_pro_person"));
			 an.setNotice_content(row.getString("an.notice_content"));
			 an.setNotice_pro_time(row.getDate("an.notice_pro_time"));
			 FacadeFactory.getCommonFacade().saveAnnounce(an);	
		 } catch(Exception e) {
				LogUtil.error(e.getMessage(), e);
				return this.setActionJsonObject(new JsonResult(false, e.getMessage(), null).toJsonString());
		 }
		 return this.setActionJsonObject(new JsonResult(true, "保存成功！", null).toJsonString());	 
	 }
	 /**
	  * 
	  * getAnnounceByid:查询公告或公告意见. <br/>
	  * @author PANDA
	  * @return
	  * @since JDK 1.6
	  */
	 public String getAnnounceByid(Row row){
//			Map map=new HashMap();
//			map.put("lcslbid", proc_id);
			Announcement a = FacadeFactory.getCommonFacade().getAnnounceByid(row);
			return JsonUtil.object2json(a);	 
		 }
		/**
		 * 
		 * getRegPreCerInfo:获取登记簿预览房地产证信息
		 *
		 * @author Joyon
		 * @return
		 * @since JDK 1.6
		 */
		public String getRegPreCerInfo(Row row){
			//String procId = "1000015699";
			String proc_id = row.getString("proc_id");
			List<Map<String, Object>> resultList = FacadeFactory.getCommonFacade().getRegPreCerInfo(String.valueOf(proc_id));
			return JsonUtil.object2json(resultList);
		}

	 
	     /**
	      * 
	      * saveDispatch:保存领证人信息. <br/>
	      * @author PANDA
	      * @return
	      * @since JDK 1.6
	      */
		 public String saveDispatch(Row row){
			 String proc_id = row.getString("proc_id");
			BusinessMain business= FacadeFactory.getCommonFacade().getBusinessMainByProcId(proc_id);
			 Certificate cer=new Certificate();
			 cer.setBus_id(business.getBus_id());
			 cer.setRec_cer_no(row.getString("reccerno"));
			 cer.setRec_cer_type(row.getString("reccertype"));
			 cer.setRec_date(new Date());
			 cer.setRec_person(row.getString("recperson"));
			 cer.setRec_type(row.getString("rectype"));
			 try {
					FacadeFactory.getCommonFacade().saveDispatch(cer);	
				} catch(Exception e) {
					LogUtil.error(e.getMessage(), e);
					return this.setActionJsonObject(new JsonResult(false, e.getMessage(), null).toJsonString());
				}
				return this.setActionJsonObject(new JsonResult(true, "领证人信息保存成功！", null).toJsonString());	 
		 }
	 
	     /**
	      * 
	      * getRegsMessage:根据流程实例id 获取登记信息集合. <br/>
	      * @author PANDA
	      * @return
	      * @since JDK 1.6
	      */
		 public String getRegsMessage(Row row){
				//获取流程实例表id  
				String procid= row.getString("procId");
				
				String str = "";
				List<Map<String, Object>> apps = null;//FacadeFactory.getCertificateFacade().getRegsMessByLcslbid(procid);
				Map<String,Object> map = new HashMap<String,Object>();
				//记录了查询总数
				map.put("total", apps.size());
				//记录了当前页的数据
				map.put("rows", apps);
				try {
					str = JsonUtil.map2json(map);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return this.setActionJsonObject(str);
			}
		 /**
		  * 		 
		  * getRecMaterial:获取业务相关接件材料表信息. <br/>
		  * @author PANDA
		  * @return
		  * @since JDK 1.6
		  */
		 public String getRecMaterial(Row row){
				//-------ajax---------
				//获取流程实例表id
				int lcslbid = new Integer((String)row.getString("lcslbid"));
				String str = "";
				List<Map<String, Object>> apps = FacadeFactory.getCommonFacade().getRecMaterial(lcslbid);
				Map<String,Object> map = new HashMap<String,Object>();
				//记录了查询总数
				map.put("total", apps.size());
				//记录了当前页的数据
				map.put("rows", apps);
				try {
					str = JsonUtil.map2json(map);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return this.setActionJsonObject(str);

			}
		 /**
		  * 
		  * getRecMaterial:获取接件材料表配置信息. <br/>
		  * @author PANDA
		  * @return
		  * @since JDK 1.6
		  */
		 public String getRMaterialCon(Row row){
				//-------ajax---------
				//获取流程实例表id
				int ywlxid = new Integer((String)row.getString("ywlxid"));
				String str = "";
				List<Map<String, Object>> apps = FacadeFactory.getCommonFacade().getMaterialCon(ywlxid);
				Map<String,Object> map = new HashMap<String,Object>();
				//记录了查询总数
				map.put("total", apps.size());
				//记录了当前页的数据
				map.put("rows", apps);
				try {
					str = JsonUtil.map2json(map);
					System.out.println(str);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return this.setActionJsonObject(str);

			}
		 
		 /**
		  * 
		  * saveRecMaterial:保存接件材料表配置信息. <br/>
		  * @author PANDA
		  * @return
		  * @since JDK 1.6
		  */
		 public String saveRecMaterialCon(Row row){
			 try {
					RecMatConfigure rmc = new RecMatConfigure();
					rmc.setCfig_rec_name(row.getString("rmc.cfig_rec_name"));
					rmc.setCfig_rec_type(row.getString("rmc.cfig_rec_type"));
					rmc.setCfig_rec_style(row.getString("rmc.cfig_rec_style"));
					rmc.setCfig_rec_copy(row.getString("rmc.cfig_rec_copy"));
					rmc.setCfig_page(row.getString("rmc.cfig_page"));
					rmc.setCfig_person(row.getString("rmc.cfig_person"));
					rmc.setCfig_date(row.getDate("rmc.cfig_date"));
					FacadeFactory.getCommonFacade().saveRecMaterial(rmc);	
				} catch(Exception e) {
					LogUtil.error(e.getMessage(), e);
					return this.setActionJsonObject(new JsonResult(false, e.getMessage(), null).toJsonString());
				}
				return this.setActionJsonObject(new JsonResult(true, "接件材料配置信息保存成功！", null).toJsonString());	 
		 }
		 /**
		  * 
		  * updateRecMaterial:更新接件材料表配置信息. <br/>
		  * @author PANDA
		  * @return
		  * @since JDK 1.6
		  */
		 
		 public String updateRecMaterialCon(Row row){
			 try {
				 RecMatConfigure rmc = new RecMatConfigure();
					rmc.setCfig_rec_name(row.getString("rmc.cfig_rec_name"));
					rmc.setCfig_rec_type(row.getString("rmc.cfig_rec_type"));
					rmc.setCfig_rec_style(row.getString("rmc.cfig_rec_style"));
					rmc.setCfig_rec_copy(row.getString("rmc.cfig_rec_copy"));
					rmc.setCfig_page(row.getString("rmc.cfig_page"));
					rmc.setCfig_person(row.getString("rmc.cfig_person"));
					rmc.setCfig_date(row.getDate("rmc.cfig_date"));
					FacadeFactory.getCommonFacade().updateRecMaterial(rmc);	
				} catch(Exception e) {
					LogUtil.error(e.getMessage(), e);
					return this.setActionJsonObject(new JsonResult(false, e.getMessage(), null).toJsonString());
				}
				return this.setActionJsonObject(new JsonResult(true, "接件材料配置信息更新成功！", null).toJsonString());	 
		 }
		 /**
		  * 
		  * delRecMaterial:删除接件材料表配置信息. <br/>
		  * @author PANDA
		  * @return
		  * @since JDK 1.6
		  */		 
		 public String delRecMaterialCon(Row row){
			 //-------ajax---------
			int id = new Integer((String)row.getString("jjclpzbid"));
			 try {
					FacadeFactory.getCommonFacade().delRecMaterial(id);	
				} catch(Exception e) {
					LogUtil.error(e.getMessage(), e);
					return this.setActionJsonObject(new JsonResult(false, e.getMessage(), null).toJsonString());
				}
				return this.setActionJsonObject(new JsonResult(true, "材料删除成功！", null).toJsonString());	 
		 } 
			//递归获取子节点			
			public void  getProcNode(Row parentNode,String parentID){
				Row  treeRow = new RowImpl();
				List<Row> treeChildren=new ArrayList();			
				List<ProcNode> p=new ArrayList<ProcNode>();
				p=FacadeFactory.getCommonFacade().getProcNodeById(parentID);
				for(ProcNode row:p){
							treeRow=new RowImpl();					 
							treeRow.put("id",row.getNode_id());
							treeRow.put("parent_id", row.getBus_type_id());
							treeRow.put("iconCls", row.getBus_type_id());
							Map attributes = new HashMap();
							attributes.put("url",row.getProc_node_id());
							treeRow.put("attributes",attributes);
							treeRow.put("text", row.getNode_name());
							treeChildren.add(treeRow);
							parentNode.put("children", treeChildren);
				}		
			}
			
//			 private String maxPageItems;//每页显示的记录数  
//		       
//			 private String pageNumber;//当前第几页  
//			private String officeid;
//			private ConOffice con;
//			private String formurl;
//			private String formtype;
//			private String formname;
			 
			
			public void  getProcChildToTree(Map parentNode,List<BusType> listtree,String parentID){
				List<Row> listTreeRow=new ArrayList<Row>(); 
				List<Row> treeChildren=new ArrayList<Row>();
				Row  map = null;
				try {
					//List<Row> treeChildren=null;
					for (BusType bt : listtree) {
						//System.out.println(count++);	
						if (parentID.equals(bt.getParent_id())) {
							//if(row.getBusnode().contains(getWorkItemById(wiId))){
							map = new RowImpl();
							map.put("id", "");
							map.put("parent_id", bt.getParent_id());
							map.put("iconCls", bt.getBus_type_id());
							Map attributes = new HashMap();
							attributes.put("url", "");
							map.put("text", bt.getBus_name());
							map.put("state", "closed");
							treeChildren.add(map);
							parentNode.put("children", treeChildren);
							//getBusTypeChildToTree(map, listtree,bt.getBus_type_id());
							getProcNode(map,bt.getBus_type_id());
							//getProcNode(treeRow, row1.getBus_type_id());
							//}
						}
					}
				} catch (Exception e) {
					
					e.printStackTrace();
					throw new BusinessException();
					// TODO: handle exception
				}		
			}
			/**
			 * queryProcessdef:(获得所有流程定义). <br/>
			 * @author xuzz
			 * @param row
			 * @return
			 * @since JDK 1.6
			 */
			public String queryProcessdef(Row row){
				//queryAllSysModule();
				List<Row> rows=new ArrayList<Row>();
				Row taskRows=new RowImpl();	
				int count=0;
				try{
					List listquery= wfcade.getAllqueryProcessde();
					for(int j=0;j<listquery.size();j++)
					{
						Map map=(Map)listquery.get(j);
						List list= workflowClient.getProcessdefManager().queryProcessdef(map.get("module_id").toString());
						count+=list.size();
						Procdef pd=new Procdef();
						taskRows.put("total", count);
						for(int i=0;i<list.size();i++){
							row=new RowImpl();
							pd=(Procdef)list.get(i);
							row.put("procdefId", pd.getProcdefId());
							row.put("name", pd.getName());
							rows.add(row);
						}
					}
					taskRows.put("rows", rows);
				}
				catch(Exception e){
					e.printStackTrace();
				}
				return JsonUtil.object2json(rows);
			}
			
			
			
			
			
			/**
			 * 
			 * getProMatterChildToTree:(查询业务事项树子类). <br/>
			 * @author xuzz
			 * @return
			 * @since JDK 1.6
			 */
			public void  getProMatterChildToTree(List<BusMatter> listmatter,Map parentNode,String parentID){
				List<Row> listTreeRow=new ArrayList<Row>(); 
				List<Row> treeChildren=new ArrayList<Row>();
				Row  map = null;
				try {
					for (BusMatter bt : listmatter) {
						if (parentID.equals(bt.getParent_id())) {
							map = new RowImpl();
							map.put("id", bt.getId());
							map.put("parent_id", bt.getParent_id());
							map.put("iconCls", bt.getBus_type_id());
							map.put("text", bt.getName());
							map.put("url", "");
							treeChildren.add(map);
							parentNode.put("children", treeChildren);
							getProMatterChildToTree(listmatter,map,bt.getBus_type_id());
						}
					}
				} catch (Exception e) {
					
					e.printStackTrace();
					throw new BusinessException();
				}		
			}
			/**
			 * 
			 * deleteMatter:(删除业务事项树). <br/>
			 * @author xuzz
			 * @return
			 * @since JDK 1.6
			 */
			public String deleteMatter(Row row){
				String result=null;
				try {	
					result=FacadeFactory.getCommonFacade().deleteMatter(row.getString("id"));	
				} catch(Exception e) {
					LogUtil.error(e.getMessage(), e);
				}
				return result;// this.setActionJsonObject(result);
			}
			/**
			 * 
			 * getMatter:(查询业务事项详细). <br/>
			 * @author xuzz
			 * @return
			 * @since JDK 1.6
			 */
			public  String getMatter(Row row)
			{
				BusMatter matter=new BusMatter();
				try {	
					matter=FacadeFactory.getCommonFacade().getMatter(row.getString("id"));	
				} catch(Exception e) {
					LogUtil.error(e.getMessage(), e);
				}
				return JsonUtil.object2json(matter);
			}
			
			/**
			 * 
			 * getMatterDetail:(查询业务事项，用于展示在页面上). <br/>
			 * @author xuzz
			 * @return
			 * @since JDK 1.6
			 */
			public  String getMatterDetail(Row row)
			{
				List<BusMatter> matter=new ArrayList<BusMatter>();
				try {	
					matter=FacadeFactory.getCommonFacade().getProMatter();	
				} catch(Exception e) {
					LogUtil.error(e.getMessage(), e);
				}
				return JsonUtil.object2json(matter);
			}
			
			/**
			 * 
			 * saveMatter:(保存务事项树). <br/>
			 * @author xuzz
			 * @return
			 * @since JDK 1.6
			 */
			public String saveMatter(Row row){
				//String bustypeid=row.getString("bustypeid");
				String parentid=row.getString("parentid");
				String name=row.getString("name");
				BusMatter matter=new BusMatter();
				//matter.setBus_type_id(bustypeid);
				matter.setParent_id(parentid);
				matter.setName(name);
				matter = FacadeFactory.getCommonFacade().saveMatter(matter);
				return JsonUtil.object2json(matter);
			}
			/**
			 * 
			 * updateMatter:(更新务事项树). <br/>
			 * @author xuzz
			 * @return
			 * @since JDK 1.6
			 */
			public String updateMatter(Row row){
				String bustypeid=row.getString("bustypeid");
				String parentid=row.getString("parentid");
				String name=row.getString("name");
				String proc_id=row.getString("procid");
				String protype=row.getString("protype");
				String sort=row.getString("sort");
				String id=row.getString("id");
				BusMatter matter=new BusMatter();
				matter.setBus_type_id(bustypeid);
				matter.setProc_id(proc_id);
				matter.setPro_type(protype);
				matter.setSort(sort);
				matter.setId(id);
				matter.setParent_id(parentid);
				matter.setName(name);
				String result = FacadeFactory.getCommonFacade().updateMatter(matter);
				return this.setActionJsonObject(result);
			}
			
			
			/**
			 * 
			 * getProMatter:(查询业务事项树). <br/>
			 * @author xuzz
			 * @return
			 * @since JDK 1.6
			 */
			public String getProMatter(Row row){
				List<BusMatter> listtree = new ArrayList<BusMatter>();
				String pageNumber = row.getString("pageNumber");
				String maxPageItems = row.getString("maxPageItems");
				//当前页  
		        int intPage = Integer.parseInt((pageNumber == null || pageNumber == "0") ? "1":pageNumber);  
				//每页显示条数  
		        int number = Integer.parseInt((maxPageItems == null || maxPageItems == "0") ? "10":maxPageItems);  
		        //每页的开始记录  第一页为1  第二页为number +1   
		        int start = (intPage-1)*number;  	           
				try {
					listtree = FacadeFactory.getCommonFacade().getProMatter();
				} 
				catch (Exception e1) {
					e1.printStackTrace();
				}
				Menu tree=null;
				List<Map<String,Object>> listTreeRow=new ArrayList<Map<String,Object>>(); 
				Row treeRow=null;
				for(BusMatter row1:listtree){	
					if(row1.getParent_id()!=null &&row1.getParent_id().equals("-1"))
					{
						treeRow=new RowImpl();
						 treeRow.put("id",row1.getId());
						 treeRow.put("parent_id", row1.getParent_id());
						 treeRow.put("iconCls", row1.getBus_type_id());
						 Map attributes = new HashMap();
						 attributes.put("url","");
						 treeRow.put("text", row1.getName());	                
		                //添加子节点
						 getProMatterChildToTree(listtree,treeRow,row1.getBus_type_id());
		                listTreeRow.add(treeRow);
					}
				}
				String str = "";
				try {
					 org.json.JSONArray ja = new  org.json.JSONArray(listTreeRow);
					str = ja.toString();
				} catch (Exception e) {
					e.printStackTrace();
				}
//				System.out.println("str:"+str);
				return setActionJsonObject(str);
			}
			
		/**
		 * 
		 * getProcName:(查询流程定义，用于配置表单及报表配置). <br/>
		 * @author xuzz
		 * @return
		 * @since JDK 1.6
		 */
		public String getProcName(Row row){
			List<BusType> listtree = new ArrayList<BusType>();
			String pageNumber = row.getString("pageNumber");
			String maxPageItems = row.getString("maxPageItems");
			//当前页  
	        int intPage = Integer.parseInt((pageNumber == null || pageNumber == "0") ? "1":pageNumber);  
			//每页显示条数  
	        int number = Integer.parseInt((maxPageItems == null || maxPageItems == "0") ? "10":maxPageItems);  
	        //每页的开始记录  第一页为1  第二页为number +1   
	        int start = (intPage-1)*number;  	           
			try {
				listtree = FacadeFactory.getCommonFacade().getProcName();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			Menu tree=null;
			List<Map<String,Object>> listTreeRow=new ArrayList<Map<String,Object>>(); 
			Row treeRow=null;
			for(BusType row1:listtree){	
				if(row1.getParent_id().equals("-1"))
				{
					treeRow=new RowImpl();
					 treeRow.put("id","");
					 treeRow.put("parent_id", row1.getBus_type_id());
					 treeRow.put("iconCls", row1.getBus_type_id());
					 Map attributes = new HashMap();
					 attributes.put("url","");
					 
					 treeRow.put("text", row1.getBus_name());	                
	                //添加子节点
					 getProcChildToTree(treeRow,listtree,row1.getBus_type_id());
					 treeRow.put("state", "closed");
	                listTreeRow.add(treeRow);
				}
					 
			}
			String str = "";
			try {
				 org.json.JSONArray ja = new  org.json.JSONArray(listTreeRow);
				str = ja.toString();
			} catch (Exception e) {
				e.printStackTrace();
			}
//			System.out.println("str:"+str);
			return setActionJsonObject(str);
		}
		 
		
		/**
		 * 
		 * getFormById:(这里用一句话描述这个方法的作用). <br/>
		 * @author xuzz
		 * @param nodeid
		 * @return
		 * @since JDK 1.6
		 */
		public String getFormById(Row row)
		{
			Map<String,Object> ma = new HashMap<String,Object>();
			List<Map<String,Object>> list=new ArrayList<Map<String,Object>>(); 
			String nodeid = row.getString("nodeid");
			String bus_typeid = row.getString("bus_typeid");
			list=FacadeFactory.getCommonFacade().getFormById(nodeid,bus_typeid);
			String str="";
			try {
				if(list!=null)
				{
					//记录了查询总数
					ma.put("total", list.size());
					//记录了当前页的数据
					ma.put("rows",list);
					str = JsonUtil.map2json(ma);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return this.setActionJsonObject(str);
			
		}
	 

		 
		 /**
		  * 
		  * saveRecMat:保存业务相关接件材料信息. <br/>
		  * @author PANDA
		  * @return
		 * @throws GeneralException 
		  * @since JDK 1.6
		  */
		 public String saveRecMat(Row row) throws GeneralException{
				String userName = getOperatorInfo().getUserName();
				Map map = new HashMap();
//				map.put("datas",datas);
//				map.put("proc_id", proc_id);
				map = FacadeFactory.getCommonFacade().saveRecMat(row);
				return JsonUtil.object2json(map);
		}
		
		/**
		  * 
		  * saveMenu:保存菜单. <br/>
		  * @author PANDA
		  * @return
		  * @since JDK 1.6
		  */
		 @Deprecated
		 public String saveMenu(Row row){
			 try {
				 TreeMenu menu = new TreeMenu();
					menu.setMenu_name(row.getString("menu.menu_name"));
					menu.setParent_id(row.getString("menu.parent_id"));
					menu.setUrl(row.getString("menu.url"));
					menu.setCreator(row.getString("menu.creator"));
					menu.setCreate_date(row.getDate("menu.create_date"));
					menu.setMenu_order(row.getString("menu.menu_order"));
//					FacadeFactory.getCommonFacade().saveMenu(menu);	
				} catch(Exception e) {
					LogUtil.error(e.getMessage(), e);
					return this.setActionJsonObject(new JsonResult(false, e.getMessage(), null).toJsonString());
				}
				return this.setActionJsonObject(new JsonResult(true, "菜单保存成功！", null).toJsonString());	 
		 }
		 
		 /**
		  * 
		  * updateMenu:更新菜单. <br/>
		  * @author PANDA
		  * @return
		  * @since JDK 1.6
		  */
		 @Deprecated
		 public String updateMenu(Row row){
			 //-------ajax---------
			 try {
					//FacadeFactory.getCommonFacade().updateMenu(menu);	
				} catch(Exception e) {
					LogUtil.error(e.getMessage(), e);
					return this.setActionJsonObject(new JsonResult(false, e.getMessage(), null).toJsonString());
				}
				return this.setActionJsonObject(new JsonResult(true, "菜单更新成功！", null).toJsonString());	 
		 }	         
		 /**
		  * 
		  * delMenu:删除菜单项. <br/>
		  * @author PANDA
		  * @return
		  * @since JDK 1.6
		  */
		 public String delMenu(Row row){
			 
			 //-------ajax---------
				int id = new Integer((String)row.getString("id"));
				
				List m = FacadeFactory.getCommonFacade().getMenuChild(id);
				if(m.size() != 0){
					
					return this.setActionJsonObject(new JsonResult(true, "该节点存在子节点，请先删除子节点。", null).toJsonString());
					
				}
				 try {
						FacadeFactory.getCommonFacade().delMenu(id);	
					} catch(Exception e) {
						LogUtil.error(e.getMessage(), e);
						return this.setActionJsonObject(new JsonResult(false, e.getMessage(), null).toJsonString());
					}
					return this.setActionJsonObject(new JsonResult(true, "菜单删除成功！", null).toJsonString());	
		 };
		 
		 
		 /**
		  * 
		  * getMenuList:获取菜单项列表. <br/>
		  * @author PANDA
		  * @return
		  * @since JDK 1.6
		  */
		 public String getMenuList(Row row){
				//获取流程实例表id
				String id = (String)row.getString("id");
				String str = "";
				List<Map<String, Object>> apps = FacadeFactory.getCommonFacade().getMenuList(id);
				/*Map<String,Object> map = new HashMap<String,Object>();
				//记录了查询总数
				map.put("total", apps.size());
				//记录了当前页的数据
				map.put("rows", apps);*/
				JSONArray list = JSONArray.fromObject(apps);
				str = list.toString();
				System.out.println(str);
				return str.toLowerCase(Locale.CHINESE);

			}
		 /**
		  * 
		  * insertNode:(插入是否选中节点上的表单). <br/>
		  * @author xuzz
		  * @return
		  * @since JDK 1.6
		  */
		 public String insertNode(Row row)
		 {
			 ConNodeRelation con=new ConNodeRelation();
			 con.setNode_id(row.getString("nodeid"));
			 con.setOffice_id(row.getString("officeid"));
			 try {	
				 FacadeFactory.getCommonFacade().insertNode(con,row.getString("bus_typeid"));
				} catch(Exception e) {
					LogUtil.error(e.getMessage(), e);
					return this.setActionJsonObject(new JsonResult(false, e.getMessage(), null).toJsonString());
				}
			
			 return this.setActionJsonObject(new JsonResult(true, "保存成功！", null).toJsonString());
		 }
		 /**
		  * getBusType:(查询所有业务流程定义). <br/>
		  * @author xuzz
		  * @return
		  * @since JDK 1.6
		  */
		 public String getBusType(Row row)
		 {
			 //-------ajax---------
			 List<BusType> list = new ArrayList<BusType>();
			 List<Map<String,Object>> listmap=new ArrayList<Map<String,Object>>();
			 Map map=null;
			 String str = "";
			 try {	
				 	list= FacadeFactory.getCommonFacade().getProcName();
				 	for(BusType bt:list){
				 		map=new HashMap();
				 		if(!bt.getParent_id().equals("-1"))
				 		{
				 			map.put("busname", bt.getBus_name());
					 		map.put("bustype", bt.getBus_type_id());
					 		listmap.add(map);
				 		}
				 		
				 	}
				 	org.json.JSONArray ja = new  org.json.JSONArray(listmap);
					str = ja.toString(); 
			}
			catch (Exception e) 
			{
				e.printStackTrace();
			}
			return this.setActionJsonObject(str);
		 }
		 
		//递归获取子节点
			
			public void  getBusTypeChildToTree(Map parentNode,List<BusType> listtree,String parentID){
				List<Row> listTreeRow=new ArrayList<Row>(); 
				List<Row> treeChildren=new ArrayList<Row>();
				Row  map = null;
				try {
					//List<Row> treeChildren=null;
					for (BusType bt : listtree) {
						//System.out.println(count++);
						if (parentID.equals(bt.getParent_id())) {
							//if(row.getBusnode().contains(getWorkItemById(wiId))){
							map = new RowImpl();
							map.put("id", bt.getBus_type_id());
							map.put("parent_id", bt.getParent_id());
							map.put("iconCls", bt.getBus_name());
							Map attributes = new HashMap();
							attributes.put("url", bt.getProc_id());
							map.put("text", bt.getBus_name());
							//map.put("state", "closed");
							treeChildren.add(map);
							parentNode.put("children", treeChildren);
							
							//getBusTypeChildToTree(map, listtree,bt.getBus_type_id());
							//getProcNode(map,bt.getBus_type_id());
							//}
						}
					}
				} catch (Exception e) {
					
					e.printStackTrace();
					throw new BusinessException();
					// TODO: handle exception
				}		
			}
		 /**
		  * getBusType:(查询所有业务流程定义生成树). <br/>
		  * @author xuzz
		  * @return
		  * @since JDK 1.6
		  */
		 public String getBusTypeToTree(Row row)
		 {
			 //-------ajax---------
			 List<BusType> list = new ArrayList<BusType>();
			 List<Map<String,Object>> listmap=new ArrayList<Map<String,Object>>();
			 Row map=null;
			 String str = "";
			 try {	
				 	list= FacadeFactory.getCommonFacade().getProcName();
				 	for(BusType bt:list){
				 		if(bt.getParent_id().equals("-1"))
				 		{
					 		map=new RowImpl();
					 		map.put("id",bt.getBus_type_id());
							map.put("parent_id", bt.getParent_id());
							map.put("iconCls", bt.getBus_name());
							Map attributes = new HashMap();
							attributes.put("url",bt.getProc_id());
							map.put("text", bt.getBus_name());
							getBusTypeChildToTree(map,list,bt.getBus_type_id());
							map.put("state", "closed");
					 		listmap.add(map);
				 		}
				 	}
				 	org.json.JSONArray ja = new  org.json.JSONArray(listmap);
					str = ja.toString(); 
			}
			catch (Exception e) 
			{
				e.printStackTrace();
				throw new BusinessException();
			}
			return this.setActionJsonObject(str);
		 }
		 
		 
		 /**
		  * 
		  * insertNode:(删除选中节点上的表单). <br/>
		  * @author xuzz
		  * @return
		  * @since JDK 1.6
		  */
		 public String deleteNode(Row row)
		 {
			 ConNodeRelation con=new ConNodeRelation();
			 con.setNode_id(row.getString("nodeid"));
			 con.setOffice_id(row.getString("officeid"));
			 //-------ajax---------
			 try {	
				 FacadeFactory.getCommonFacade().deleteNode(con,row.getString("bus_typeid"));
				} catch(Exception e) {
					LogUtil.error(e.getMessage(), e);
					return this.setActionJsonObject(new JsonResult(false, e.getMessage(), null).toJsonString());
				}
			
			 return this.setActionJsonObject(new JsonResult(true, "删除成功！", null).toJsonString());
		 }
		 
		 /**
		  * 
		  * saveForm:(保存表单数据). <br/>
		  * @author xuzz
		  * @return
		  * @since JDK 1.6
		  */
		 public String saveForm(Row row)
		 {			
			 ConOffice con = new ConOffice();
				con.setBus_type_id(row.getString("bus_typeid"));
				 con.setOffice_name(row.getString("formname"));
				 con.setOffice_type(row.getString("formtype"));
				 con.setOffice_url(row.getString("formurl"));
				 con.setOffice_id(row.getString("officeid"));
				try {	
					FacadeFactory.getCommonFacade().saveForm(con);
				} catch(Exception e) {
					LogUtil.error(e.getMessage(), e);
					return this.setActionJsonObject(new JsonResult(false, e.getMessage(), null).toJsonString());
				}
				return this.setActionJsonObject(new JsonResult(true, "保存成功！", null).toJsonString());
		 }
		 /**
		  * 
		  * updateForm:(更新表单数据). <br/>
		  * @author xuzz
		  * @return
		  * @since JDK 1.6
		  */
		 public String updateForm(Row row)
		 {
			 ConOffice con = new ConOffice();
				con.setBus_type_id(row.getString("bus_typeid"));
				 con.setOffice_name(row.getString("formname"));
				 con.setOffice_type(row.getString("formtype"));
				 con.setOffice_url(row.getString("formurl"));
				 con.setOffice_id(row.getString("officeid"));
				try {	
					FacadeFactory.getCommonFacade().updateForm(con);	
				} catch(Exception e) {
					LogUtil.error(e.getMessage(), e);
					return this.setActionJsonObject(new JsonResult(false, e.getMessage(), null).toJsonString());
				}
				return this.setActionJsonObject(new JsonResult(true, "更新成功！", null).toJsonString());
		 }
		 /**
		  * 
		  * deleteForm:(删除表单数据). <br/>
		  * @author xuzz
		  * @return
		  * @since JDK 1.6
		  */
		 public String deleteForm(Row row)
		 {
			 //-------ajax---------
				try {	
					FacadeFactory.getCommonFacade().deleteForm(row.getString("officeid"));	
				} catch(Exception e) {
					LogUtil.error(e.getMessage(), e);
					return this.setActionJsonObject(new JsonResult(false, e.getMessage(), null).toJsonString());
				}
				return this.setActionJsonObject(new JsonResult(true, "删除成功！", null).toJsonString());
		 }
		 
		 /**
		  * 
		  * getCertificateInfo:(获取缮证页面信息). 
		  *
		  * @author Joyon
		  * @return
		 * @throws GeneralException 
		  * @since JDK 1.6
		  */
		 public String getCertificateInfo(Row row) throws GeneralException{
			 Map<String,Object> paraMap = new HashMap<String,Object>();
			 BusinessMain busMain = null;
			try {
				String proc_id = row.getString("proc_id");
				busMain = FacadeFactory.getCommonFacade().getBusinessMainByProcId(proc_id );
			} catch (Exception e) {
				e.printStackTrace();
			}
			 String reg_code = busMain.getReg_code();
			 paraMap.put("reg_code", reg_code);
			 paraMap.put("userName", getOperatorInfo().getUserName());
			 Map<String,Object> resultMap = null;
			 try {
				resultMap = FacadeFactory.getCommonFacade().getCertificateInfo(
						paraMap);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return JsonUtil.object2json(resultMap);
		 }
		 
		 /**
		  * 
		  * getContractInfo:(获取合同信息).
		  *
		  * @author Joyon
		  * @return
		  * @since JDK 1.6
		  */
		 public String getContractInfo(Row row){
			 Map<String, Object> resultMap = null;
			 try {
				 String proc_id = row.getString("proc_id");
				resultMap = FacadeFactory.getCommonFacade()
						.getContractInfoByProcId(proc_id );
			} catch (Exception e) {
				e.printStackTrace();
			}
			return JsonUtil.object2json(resultMap);
		 }
		 
		 /**
		  * 
		  * getDeclarationFormInfo:(获取税费申报表信息).
		  *
		  * @author Joyon
		  * @return
		  * @since JDK 1.6
		  */
		 public String getDeclarationFormInfo(Row row){
			 String proc_id = row.getString("proc_id");
			 Map<String,Object> resultMap = FacadeFactory.getCommonFacade()
						.getDeclarationFormInfo(proc_id);
			 if(resultMap == null){
				 return null;
			 }
			return JsonUtil.object2json(resultMap);
		 }
		 
		 public String sendLandTax(Row row){
			 String proc_id = row.getString("proc_id");
			 Map<String,Object> resultMap   = FacadeFactory.getCommonFacade()
						.sendLandTax(proc_id);
			return JsonUtil.object2json(resultMap);
		 }
		 
		 /**
		  * 
		  * getBusTypeParentId:(获取八大业务类型).
		  *
		  * @author Joyon
		  * @return
		  * @since JDK 1.6
		  */
		 public String getBusTypeParentId(Row row){
			 String proc_id = row.getString("proc_id");
			 Map<String,Object> resultMap = new HashMap<String,Object>();
			 
			 if(proc_id == null){
				 resultMap.put("bus_type_id", "");
				 return JsonUtil.object2json(resultMap);
			 }
			 BusinessMain businessMain = FacadeFactory.getCommonFacade().getBusinessMainByProcId(proc_id);
			 if(businessMain == null){
				 resultMap.put("bus_type_id", "");
				 return JsonUtil.object2json(resultMap);
			 }
			 
			 String reg_code = businessMain.getReg_code();
			 Map<String,Object> paraMap = new HashMap<String,Object>();
			 paraMap.put("reg_code", reg_code);
			 String busTypeParentId = null;
			 try {
				busTypeParentId = FacadeFactory.getRegisterFacade()
						.getBusTypeParentIdByRegId(paraMap);
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			 resultMap.put("bus_type_id", busTypeParentId);
			return JsonUtil.object2json(resultMap);
		 }
		 
		 /**
		  * 
		  * getBusTypeId:(获取当前业务类型ID).
		  *
		  * @author Joyon
		  * @return
		  * @since JDK 1.6
		  */
		 public String getBusTypeId(Row row){
			 Map<String,Object> resultMap = new HashMap<String,Object>();
			 String proc_id = row.getString("proc_id");
			 BusinessMain businessMain = FacadeFactory.getCommonFacade().getBusinessMainByProcId(proc_id);
			 if(businessMain == null){
				 resultMap.put("busTypeId", "");
				 return JsonUtil.map2json(resultMap);
			 }
			 String busTypeId = businessMain.getReg_type();
			 
			 resultMap.put("busTypeId", busTypeId);
			 return JsonUtil.map2json(resultMap);
		 }
		 
		 /**
		  * 
		  * 获取缮证自然信息
		  *
		  * @author Joyon
		  * @return
		 * @throws GeneralException 
		  * @since JDK 1.6
		  */
		 public String getCertificateNatural(Row row) throws GeneralException{
			 String proc_id = row.getString("proc_id");
			 String bus_id = FacadeFactory.getCommonFacade().getBusinessMainByProcId(proc_id).getBus_id();
			 List resultList = FacadeFactory.getCertificateFacade().getCertificateNaturalByBusid(bus_id,getOperatorInfo());
			 return JsonUtil.object2json(resultList);
		 }
 
		 /**
		  * 
		  * 保存房地产证附记  考虑到多大数情况  
		  *
		  * @author Joyon
		  * @return
		  * @since JDK 1.6
		  */
		 public String saveExcursus(Row row){
			 Map<String,Object> resultMap = new HashMap<String,Object>();
			 resultMap.put("message", "保存成功");
			 resultMap.put("sign", "1111");
			 String proc_id = row.getString("proc_id");
			 String excursus = row.getString("excursus");
			 String bus_id = FacadeFactory.getCommonFacade().getBusinessMainByProcId(proc_id).getBus_id();
			 try {
				FacadeFactory.getCertificateFacade().saveorupdateExcursus(
						bus_id, excursus);
			} catch (Exception e) {
				LogUtil.error("CertificateFacade.saveExcursus 保存房地产证附记出错 bus_id:"+bus_id+" excursus:"+excursus+" "+e.getMessage());
				return null;
			}
			 return JsonUtil.object2json(resultMap);
		 }
		 
		 /**
		  * 
		  * getLandContractInfo:(获取土地合同信息).
		  *
		  * @author xuzz
		  * @return
		  * @since JDK 1.6
		  */
		 public String getLandContractInfo(Row row){
			 Map<String, Object> resultMap = null;
			 try {
				 resultMap = FacadeFactory.getCommonFacade()
						.getLandContractInfo();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return JsonUtil.map2json(resultMap);
		 }
		 
}

