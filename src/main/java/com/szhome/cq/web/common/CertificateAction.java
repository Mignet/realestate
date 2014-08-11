
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

/**��ȡ���ء����ݡ���Ȩ��Ϣ��
 * 2013-12-4
 * @author pandalee
 *
 */
public class CertificateAction extends BaseDelegate{
	/*
	//��֤��
	private String recperson;
	//��֤��֤�����
	private String reccerno;
	//��֤������
	private String rectype;
	//��֤��֤������
	private String reccertype;	
	//ҵ��id
	private String busid;
	//����ʵ����ID
	private String procid;

	//�ڵ�ID
	private String nodeid;
	//ҵ������ID
	private String bus_typeid;


	
	private Menu menu;*/
//	/** �������ͻ���ͳһ�ӿ� */
	private WorkflowClientFacade workflowClientFacade = WorkflowClientFactory.getWorkflowClientFacade();
//	/** �������ͻ��˽ӿ�*/
	private WorkflowClient workflowClient = workflowClientFacade.getWorkflowClient();
	IWorkflowFacade wfcade = FacadeFactory.getWorkflowFacade();
	
	String[] arr = {"0101","0102","0103","0104","0105"};
	
	/**
	 * 
	 * getInitInfo:(��ʼ��). <br/>
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
	 * ��ȡ�ֶ����ܱ�����
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
			//��¼�˲�ѯ����
			ma.put("total", list.size());
			//��¼�˵�ǰҳ������
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
	 * ��ȡ�ֻ����ܱ�����
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
			//��¼�˲�ѯ����
			ma.put("total", list.size());
			//��¼�˵�ǰҳ������
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
	 * getBuildingByID:(������һ�仰�����������������). <br/>
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
	 * ��ȡ�ڵ���Ϣ
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
	 * ���ݱ�Ų�ѯ���ز�֤������Ϣ
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
	 * getCertificateByProcId:(ͨ������ʵ��ID��÷��ز�֤��Ϣ).
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
		
		//������ز�֤��������     �ѷ��ز�֤��Ϣ�е�һ��תΪmap  ����ǰ��    ��Ϊʱ��û������  ��ʽ����
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
	 * ����ת����Map
	 */
	private static void object2MapWithoutNull(Object obj, Map map)throws IllegalArgumentException, IllegalAccessException {
		Field[] fields = obj.getClass().getDeclaredFields();
		for (int j = 0; j < fields.length; j++) {
			fields[j].setAccessible(true);// ����������������з���Ȩ�޼�� ���������õı�������Ϊprivate
			if (fields[j].get(obj) != null
				&& (((fields[j].get(obj) instanceof String) && !""
				.equals(fields[j].get(obj))) || ((fields[j]
				.get(obj) instanceof Integer)))) {
				map.put(fields[j].getName(), fields[j].get(obj));
			}
		}
	}
	/**
	 * ������֤��Ϣ
	 * @param row ǰ��ҳ�洫�ݽ���������뵽�������
	 * @return ���������
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
			//�������Ԥ�۱���  �򱣴���Ϣ�����ز�֤����
			if(businessMain.getReg_type().equals(WbfConstants.PRE_SALE_BAKUP)){
				List certificateList = FacadeFactory.getCertificateFacade().getCertificateListByProcId(proc_id);
				//������ݿ��д��� ����  ������ ����
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
		return this.setActionJsonObject(new JsonResult(true, "��֤�ɹ���", null).toJsonString());
	}
	/**
	 * 
	 * saveFileMess:���������Ϣ. <br/>
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
//			return this.setActionJsonObject(new JsonResult(true, "����������ظ����棡", null).toJsonString());
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
		return this.setActionJsonObject(new JsonResult(true, "�����Ϣ����ɹ���", null).toJsonString());
	}
	/**
	 * 
	 * savePigeoMess:����鵵��Ϣ. <br/>
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
			return this.setActionJsonObject(new JsonResult(true, "�鵵��Ϣ����ɹ���", null).toJsonString());
	 
	 }
	 /**
	  * 
	  * saveExaMess:������������Ϣ. <br/>
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
			return (new JsonResult(true, "�����������ɹ���", null));
	 }
	 /**
	  * 
	  * getExamByid:��������ʵ��id��ȡ��������Ϣ. <br/>
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
	  * getFileByid:��������ʵ��id��ȡ�����Ϣ. <br/>
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
	  * getPigeByid:��������ʵ��id��ȡ�鵵��Ϣ. <br/>
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
				LogUtil.error("��ȡҵ��������Ϣ����"+e.getMessage());
			}
			map.put("reg_code", reg_code);
		  try {
			m = FacadeFactory.getCommonFacade().getPigeByid(map);
		} catch (Exception e) {
			LogUtil.error("��ȡ�鵵��Ϣ����"+e.getMessage());
		}
		m.put("reg_code", reg_code);
		  m.put("arch_handler_no", getOperatorInfo().getUserID());							//�������				
		  return JsonUtil.object2json(m);
	 }
	 /**
	  * 
	  * saveComLanguage:���浥��������. <br/>
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
			return this.setActionJsonObject(new JsonResult(true, "�����ﱣ��ɹ���", null).toJsonString());	 
	 }
	 
	 /**
	  * 
	  * updateComLanguage:���µ���������. <br/>
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
			return this.setActionJsonObject(new JsonResult(true, "��������³ɹ���", null).toJsonString());
	 } 
	 /**
	  * 
	  * delComLanguage:ɾ������������. <br/>
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
			
			 return this.setActionJsonObject(new JsonResult(true, "ɾ��������ɹ���", null).toJsonString());			 
	 }
	 
	 /**
	  * 
	  * getComLanById:��ȡ������. <br/>
	  * @author PANDA
	  * @return
	  * @since JDK 1.6
	  */
	 public String getComLanById(){
			//-------ajax---------
			//��ȡ����ʵ����id
			//int lcslbid = new Integer((String)row.getString("lcslbid"));
			String str = "";
			List<Map<String, Object>> apps = FacadeFactory.getCommonFacade().getComLanByid();
			Map<String,Object> map = new HashMap<String,Object>();
			//��¼�˲�ѯ����
			map.put("total", apps.size());
			//��¼�˵�ǰҳ������
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
	  * saveAnnounce:���浥��������������. <br/>
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
		 return this.setActionJsonObject(new JsonResult(true, "����ɹ���", null).toJsonString());	 
	 }
	 /**
	  * 
	  * getAnnounceByid:��ѯ����򹫸����. <br/>
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
		 * getRegPreCerInfo:��ȡ�Ǽǲ�Ԥ�����ز�֤��Ϣ
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
	      * saveDispatch:������֤����Ϣ. <br/>
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
				return this.setActionJsonObject(new JsonResult(true, "��֤����Ϣ����ɹ���", null).toJsonString());	 
		 }
	 
	     /**
	      * 
	      * getRegsMessage:��������ʵ��id ��ȡ�Ǽ���Ϣ����. <br/>
	      * @author PANDA
	      * @return
	      * @since JDK 1.6
	      */
		 public String getRegsMessage(Row row){
				//��ȡ����ʵ����id  
				String procid= row.getString("procId");
				
				String str = "";
				List<Map<String, Object>> apps = null;//FacadeFactory.getCertificateFacade().getRegsMessByLcslbid(procid);
				Map<String,Object> map = new HashMap<String,Object>();
				//��¼�˲�ѯ����
				map.put("total", apps.size());
				//��¼�˵�ǰҳ������
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
		  * getRecMaterial:��ȡҵ����ؽӼ����ϱ���Ϣ. <br/>
		  * @author PANDA
		  * @return
		  * @since JDK 1.6
		  */
		 public String getRecMaterial(Row row){
				//-------ajax---------
				//��ȡ����ʵ����id
				int lcslbid = new Integer((String)row.getString("lcslbid"));
				String str = "";
				List<Map<String, Object>> apps = FacadeFactory.getCommonFacade().getRecMaterial(lcslbid);
				Map<String,Object> map = new HashMap<String,Object>();
				//��¼�˲�ѯ����
				map.put("total", apps.size());
				//��¼�˵�ǰҳ������
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
		  * getRecMaterial:��ȡ�Ӽ����ϱ�������Ϣ. <br/>
		  * @author PANDA
		  * @return
		  * @since JDK 1.6
		  */
		 public String getRMaterialCon(Row row){
				//-------ajax---------
				//��ȡ����ʵ����id
				int ywlxid = new Integer((String)row.getString("ywlxid"));
				String str = "";
				List<Map<String, Object>> apps = FacadeFactory.getCommonFacade().getMaterialCon(ywlxid);
				Map<String,Object> map = new HashMap<String,Object>();
				//��¼�˲�ѯ����
				map.put("total", apps.size());
				//��¼�˵�ǰҳ������
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
		  * saveRecMaterial:����Ӽ����ϱ�������Ϣ. <br/>
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
				return this.setActionJsonObject(new JsonResult(true, "�Ӽ�����������Ϣ����ɹ���", null).toJsonString());	 
		 }
		 /**
		  * 
		  * updateRecMaterial:���½Ӽ����ϱ�������Ϣ. <br/>
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
				return this.setActionJsonObject(new JsonResult(true, "�Ӽ�����������Ϣ���³ɹ���", null).toJsonString());	 
		 }
		 /**
		  * 
		  * delRecMaterial:ɾ���Ӽ����ϱ�������Ϣ. <br/>
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
				return this.setActionJsonObject(new JsonResult(true, "����ɾ���ɹ���", null).toJsonString());	 
		 } 
			//�ݹ��ȡ�ӽڵ�			
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
			
//			 private String maxPageItems;//ÿҳ��ʾ�ļ�¼��  
//		       
//			 private String pageNumber;//��ǰ�ڼ�ҳ  
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
			 * queryProcessdef:(����������̶���). <br/>
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
			 * getProMatterChildToTree:(��ѯҵ������������). <br/>
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
			 * deleteMatter:(ɾ��ҵ��������). <br/>
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
			 * getMatter:(��ѯҵ��������ϸ). <br/>
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
			 * getMatterDetail:(��ѯҵ���������չʾ��ҳ����). <br/>
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
			 * saveMatter:(������������). <br/>
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
			 * updateMatter:(������������). <br/>
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
			 * getProMatter:(��ѯҵ��������). <br/>
			 * @author xuzz
			 * @return
			 * @since JDK 1.6
			 */
			public String getProMatter(Row row){
				List<BusMatter> listtree = new ArrayList<BusMatter>();
				String pageNumber = row.getString("pageNumber");
				String maxPageItems = row.getString("maxPageItems");
				//��ǰҳ  
		        int intPage = Integer.parseInt((pageNumber == null || pageNumber == "0") ? "1":pageNumber);  
				//ÿҳ��ʾ����  
		        int number = Integer.parseInt((maxPageItems == null || maxPageItems == "0") ? "10":maxPageItems);  
		        //ÿҳ�Ŀ�ʼ��¼  ��һҳΪ1  �ڶ�ҳΪnumber +1   
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
		                //����ӽڵ�
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
		 * getProcName:(��ѯ���̶��壬�������ñ�����������). <br/>
		 * @author xuzz
		 * @return
		 * @since JDK 1.6
		 */
		public String getProcName(Row row){
			List<BusType> listtree = new ArrayList<BusType>();
			String pageNumber = row.getString("pageNumber");
			String maxPageItems = row.getString("maxPageItems");
			//��ǰҳ  
	        int intPage = Integer.parseInt((pageNumber == null || pageNumber == "0") ? "1":pageNumber);  
			//ÿҳ��ʾ����  
	        int number = Integer.parseInt((maxPageItems == null || maxPageItems == "0") ? "10":maxPageItems);  
	        //ÿҳ�Ŀ�ʼ��¼  ��һҳΪ1  �ڶ�ҳΪnumber +1   
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
	                //����ӽڵ�
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
		 * getFormById:(������һ�仰�����������������). <br/>
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
					//��¼�˲�ѯ����
					ma.put("total", list.size());
					//��¼�˵�ǰҳ������
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
		  * saveRecMat:����ҵ����ؽӼ�������Ϣ. <br/>
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
		  * saveMenu:����˵�. <br/>
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
				return this.setActionJsonObject(new JsonResult(true, "�˵�����ɹ���", null).toJsonString());	 
		 }
		 
		 /**
		  * 
		  * updateMenu:���²˵�. <br/>
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
				return this.setActionJsonObject(new JsonResult(true, "�˵����³ɹ���", null).toJsonString());	 
		 }	         
		 /**
		  * 
		  * delMenu:ɾ���˵���. <br/>
		  * @author PANDA
		  * @return
		  * @since JDK 1.6
		  */
		 public String delMenu(Row row){
			 
			 //-------ajax---------
				int id = new Integer((String)row.getString("id"));
				
				List m = FacadeFactory.getCommonFacade().getMenuChild(id);
				if(m.size() != 0){
					
					return this.setActionJsonObject(new JsonResult(true, "�ýڵ�����ӽڵ㣬����ɾ���ӽڵ㡣", null).toJsonString());
					
				}
				 try {
						FacadeFactory.getCommonFacade().delMenu(id);	
					} catch(Exception e) {
						LogUtil.error(e.getMessage(), e);
						return this.setActionJsonObject(new JsonResult(false, e.getMessage(), null).toJsonString());
					}
					return this.setActionJsonObject(new JsonResult(true, "�˵�ɾ���ɹ���", null).toJsonString());	
		 };
		 
		 
		 /**
		  * 
		  * getMenuList:��ȡ�˵����б�. <br/>
		  * @author PANDA
		  * @return
		  * @since JDK 1.6
		  */
		 public String getMenuList(Row row){
				//��ȡ����ʵ����id
				String id = (String)row.getString("id");
				String str = "";
				List<Map<String, Object>> apps = FacadeFactory.getCommonFacade().getMenuList(id);
				/*Map<String,Object> map = new HashMap<String,Object>();
				//��¼�˲�ѯ����
				map.put("total", apps.size());
				//��¼�˵�ǰҳ������
				map.put("rows", apps);*/
				JSONArray list = JSONArray.fromObject(apps);
				str = list.toString();
				System.out.println(str);
				return str.toLowerCase(Locale.CHINESE);

			}
		 /**
		  * 
		  * insertNode:(�����Ƿ�ѡ�нڵ��ϵı�). <br/>
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
			
			 return this.setActionJsonObject(new JsonResult(true, "����ɹ���", null).toJsonString());
		 }
		 /**
		  * getBusType:(��ѯ����ҵ�����̶���). <br/>
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
		 
		//�ݹ��ȡ�ӽڵ�
			
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
		  * getBusType:(��ѯ����ҵ�����̶���������). <br/>
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
		  * insertNode:(ɾ��ѡ�нڵ��ϵı�). <br/>
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
			
			 return this.setActionJsonObject(new JsonResult(true, "ɾ���ɹ���", null).toJsonString());
		 }
		 
		 /**
		  * 
		  * saveForm:(���������). <br/>
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
				return this.setActionJsonObject(new JsonResult(true, "����ɹ���", null).toJsonString());
		 }
		 /**
		  * 
		  * updateForm:(���±�����). <br/>
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
				return this.setActionJsonObject(new JsonResult(true, "���³ɹ���", null).toJsonString());
		 }
		 /**
		  * 
		  * deleteForm:(ɾ��������). <br/>
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
				return this.setActionJsonObject(new JsonResult(true, "ɾ���ɹ���", null).toJsonString());
		 }
		 
		 /**
		  * 
		  * getCertificateInfo:(��ȡ��֤ҳ����Ϣ). 
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
		  * getContractInfo:(��ȡ��ͬ��Ϣ).
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
		  * getDeclarationFormInfo:(��ȡ˰���걨����Ϣ).
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
		  * getBusTypeParentId:(��ȡ�˴�ҵ������).
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
		  * getBusTypeId:(��ȡ��ǰҵ������ID).
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
		  * ��ȡ��֤��Ȼ��Ϣ
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
		  * ���淿�ز�֤����  ���ǵ���������  
		  *
		  * @author Joyon
		  * @return
		  * @since JDK 1.6
		  */
		 public String saveExcursus(Row row){
			 Map<String,Object> resultMap = new HashMap<String,Object>();
			 resultMap.put("message", "����ɹ�");
			 resultMap.put("sign", "1111");
			 String proc_id = row.getString("proc_id");
			 String excursus = row.getString("excursus");
			 String bus_id = FacadeFactory.getCommonFacade().getBusinessMainByProcId(proc_id).getBus_id();
			 try {
				FacadeFactory.getCertificateFacade().saveorupdateExcursus(
						bus_id, excursus);
			} catch (Exception e) {
				LogUtil.error("CertificateFacade.saveExcursus ���淿�ز�֤���ǳ��� bus_id:"+bus_id+" excursus:"+excursus+" "+e.getMessage());
				return null;
			}
			 return JsonUtil.object2json(resultMap);
		 }
		 
		 /**
		  * 
		  * getLandContractInfo:(��ȡ���غ�ͬ��Ϣ).
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

