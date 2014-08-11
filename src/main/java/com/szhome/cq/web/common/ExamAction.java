/**
 * Project Name:dxtx_re
 * File Name:DictAction.java
 * Package Name:com.szhome.cq.web.common
 * Date:2014-1-2下午3:22:30
 * Copyright (c) 2014, dxtx All Rights Reserved.
 *
*/

package com.szhome.cq.web.common;

import java.util.Date;
import java.util.List;

import com.plan.commons.Row;
import com.plan.web.JsonResult;
import com.szhome.commons.log.LogUtil;
import com.szhome.cq.business.FacadeFactory;
import com.szhome.cq.business.vo.AnnounceVO;
import com.szhome.cq.business.vo.ExamVo;
import com.szhome.cq.delegate.BaseDelegate;
import com.szhome.cq.domain.model.Announcement;
import com.szhome.cq.domain.model.ExamFirst;
import com.szhome.cq.domain.model.ExamForth;
import com.szhome.cq.domain.model.ExamSecond;
import com.szhome.cq.domain.model.ExamThird;
import com.szhome.cq.domain.model.Examine;
import com.szhome.cq.utils.DateUtils;
import com.szhome.cq.utils.JsonUtil;

/**
 * ClassName:DictAction <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2014-1-2 下午3:22:30 <br/>
 * @author   Joyon
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class ExamAction extends BaseDelegate{
	
	 /**
	  * 
	  * saveExaine:保存意见. <br/>
	  * @author xuzz
	  * @return
	  * @since JDK 1.6
	  */
	 public JsonResult saveExaine(Row row){
		 //-------ajax---------
		 String bus_id = null;
			try {
				String proc_id = row.getString("proc_id");
				bus_id = FacadeFactory.getExaminationFacade().getRegid(proc_id);
			} catch (Exception e) {
				
				return new JsonResult(false,"业务主表id不存在", null);
			}
		 try {   
			// String bus_id = FacadeFactory.getExaminationFacade().getRegid(proc_id);
			 String userid = this.getOperatorInfo().getUserID();
			 //保存意见，用于撤销核准------许泽忠
			 Examine examine = new Examine();
			 examine.setChecker_no(row.getString("examine.checker_no"));
			 examine.setOpinion_content(row.getString("examine.opinion_content"));
			 examine.setOpinion_type(row.getString("examine.opinion_type"));
			 examine.setBus_id(bus_id);
			 examine.setOpinion_time(new Date());
			 examine.setChecker_no(userid);
				FacadeFactory.getExaminationFacade().saveExamine(examine);	
			} catch(Exception e) {
				LogUtil.error(e.getMessage(), e);
				return (new JsonResult(false, e.getMessage(), null));
			}
			return (new JsonResult(true, "保存成功！", null));
	 }
	
	
	 /**
	  * 
	  * saveExaMess:保存初审意见. <br/>
	  * @author PANDA
	  * @return
	  * @since JDK 1.6
	  */
	 public JsonResult saveExaFirst(Row row){
		 //-------ajax---------
		 String bus_id = null;
		 String proc_id = row.getString("proc_id");
			try {
				bus_id = FacadeFactory.getExaminationFacade().getRegid(proc_id);
			} catch (Exception e) {
				
				return (new JsonResult(false,"业务主表id不存在", null));
			}
		 try {   
			// String bus_id = FacadeFactory.getExaminationFacade().getRegid(proc_id);
			 String userid = this.getOperatorInfo().getUserID();
			 ExamFirst exf = new ExamFirst();
			 exf.setChecker_no(row.getString("exf.checker_no"));
			 exf.setOpinion_content(row.getString("exf.opinion_content"));
			 exf.setOpinion_time(row.getDate("exf.opinion_time"));
			 exf.setOpinion_type(row.getString("exf.opinion_type"));
			 exf.setBus_id(bus_id);
			 exf.setChecker_no(userid);
				FacadeFactory.getExaminationFacade().saveExaFirst(exf);	
			} catch(Exception e) {
				LogUtil.error(e.getMessage(), e);
				return (new JsonResult(false, e.getMessage(), null));
			}
			return (new JsonResult(true, "保存成功！", null));
	 }
	 
	 /**
	  * 
	  * saveExaSecond:保存复审意见. <br/>
	  * @author PANDA
	  * @return
	  * @since JDK 1.6
	  */
	 public JsonResult saveExaSecond(Row row){
		 //-------ajax---------
		 String bus_id = null;
		 String proc_id = row.getString("proc_id");
			try {
				bus_id = FacadeFactory.getExaminationFacade().getRegid(proc_id);
			} catch (Exception e) {
				return (new JsonResult(false,"业务主表id不存在", null));
			}
		 try {
			 //String bus_id = FacadeFactory.getExaminationFacade().getRegid(proc_id);
			 String userid = this.getOperatorInfo().getUserID();
			 ExamSecond exs = new ExamSecond();
			 exs.setChecker_no(row.getString("exs.checker_no"));
			 exs.setOpinion_content(row.getString("exs.opinion_content"));
			 exs.setOpinion_time(row.getDate("exs.opinion_time"));
			 exs.setOpinion_type(row.getString("exs.opinion_type"));
			 exs.setBus_id(bus_id);
			 exs.setChecker_no(userid);
				FacadeFactory.getExaminationFacade().saveExaSecond(exs);	
			} catch(Exception e) {
				LogUtil.error(e.getMessage(), e);
				return (new JsonResult(false, e.getMessage(), null));
			}
			return (new JsonResult(true, "保存成功！", null));
	 }
	 /**
	  * 
	  * saveExaThird:保存审核意见. <br/>
	  * @author PANDA
	  * @return
	  * @since JDK 1.6
	  */
	 public JsonResult saveExaThird(Row row){
		 //-------ajax---------
		 String bus_id = null;
		 String proc_id = row.getString("proc_id");
			try {
				bus_id = FacadeFactory.getExaminationFacade().getRegid(proc_id);
			} catch (Exception e) {
				//e.printStackTrace();
				return (new JsonResult(false,"业务主表id不存在", null));
			}
		 try {  
			 //String bus_id = FacadeFactory.getExaminationFacade().getRegid(proc_id);
			 String userid = this.getOperatorInfo().getUserID();
			 ExamThird ext = new ExamThird();
			 ext.setChecker_no(row.getString("ext.checker_no"));
			 ext.setOpinion_content(row.getString("ext.opinion_content"));
			 ext.setOpinion_time(row.getDate("ext.opinion_time"));
			 ext.setOpinion_type(row.getString("ext.opinion_type"));
			 ext.setChecker_no(userid);
			 ext.setBus_id(bus_id);
				FacadeFactory.getExaminationFacade().saveExaThird(ext);	
			} catch(Exception e) {
				LogUtil.error(e.getMessage(), e);
				return (new JsonResult(false, e.getMessage(), null));
			}
			return (new JsonResult(true, "保存成功！", null));
	 }
	 
	 /**
	  * 
	  * saveExaForth:保存核准意见. <br/>
	  * @author PANDA
	  * @return
	  * @since JDK 1.6
	  */
	 public JsonResult saveExaForth(Row row){
		 //-------ajax---------
		 String bus_id = null;
		 String proc_id = row.getString("proc_id");
			try {
				bus_id = FacadeFactory.getExaminationFacade().getRegid(proc_id);
			} catch (Exception e) {
				return (new JsonResult(false,"业务主表id不存在", null));
			}
		 try {   
			   // String bus_id = FacadeFactory.getExaminationFacade().getRegid(proc_id);
			    String userid = this.getOperatorInfo().getUserID();
			    ExamForth exfo = new ExamForth();
			    exfo.setChecker_no(row.getString("exfo.checker_no"));
			    exfo.setOpinion_content(row.getString("exfo.opinion_content"));
			    exfo.setOpinion_time(row.getDate("exfo.opinion_time"));
			    exfo.setOpinion_type(row.getString("exfo.opinion_type"));
			    exfo.setChecker_no(userid);
			    exfo.setBus_id(bus_id);
				FacadeFactory.getExaminationFacade().saveExaForth(exfo);	
			} catch(Exception e) {
				LogUtil.error(e.getMessage(), e);
				return (new JsonResult(false, e.getMessage(), null));
			}
			return (new JsonResult(true, "保存成功！", null));
	 }
	 
	 
	 /**
	  * 
	  * getExamByid:根据流程实例id获取审批表信息. <br/>
	  * @author PANDA
	  * @return
	  * @since JDK 1.6
	  */
	 public String getExamByid(Row row){
		 String proc_id = row.getString("proc_id");
		 String bus_id = FacadeFactory.getExaminationFacade().getRegid(proc_id);
		 ExamVo  an = FacadeFactory.getExaminationFacade().getExamByid(bus_id);
		 if(an == null){
			 return null;
		 }else{
		 return JsonUtil.object2json(an);
		 }
			
	 }
	 /**
	  * 
	  * getExamineById:根据流程实例id获取审批表信息. <br/>
	  * @author xuzz
	  * @return
	  * @since JDK 1.6
	  */
	 public String getExamineById(Row row){
		 //-------ajax---------
		 String proc_id = row.getString("proc_id");
		 String bus_id = FacadeFactory.getExaminationFacade().getRegid(proc_id);
		 List<Examine> ListMapexam = FacadeFactory.getExaminationFacade().getExamineById(bus_id);
		 if(ListMapexam!=null)
		 {
			 if(ListMapexam.size()>0){
				 return this.setActionJsonObject(JsonUtil.list2json(ListMapexam));
				 
			 }
			 else{
				 return null;
				 }
		 }
		 else{
			 return null;
			 }
			
	 }
	 /**
	  * 
	  * getAnnounceByid:获取公告表信息. <br/>
	  * @author PANDA
	  * @return
	  * @since JDK 1.6
	  */
	 public String getAnnounceByid(Row row){
		 String proc_id = row.getString("proc_id");
		 String bus_id = FacadeFactory.getExaminationFacade().getRegid(proc_id);
		 AnnounceVO  an = FacadeFactory.getExaminationFacade().getAnnounceByid(bus_id);
		 return JsonUtil.object2json(an);
	 }
	 /**
	  * 
	  * saveAnnounce:保存公告拟定信息. <br/>
	  * @author PANDA
	  * @return
	  * @since JDK 1.6
	  */
	 
	 public JsonResult saveAnnounce(Row row){
		 //-------ajax---------
		 String bus_id = null;
		 String proc_id = row.getString("proc_id");
		 try {
				bus_id = FacadeFactory.getExaminationFacade().getRegid(proc_id);
			} catch (Exception e) {
				return new JsonResult(false,"业务主表id不存在", null);
			}
		 
		 try {   
			    //String bus_id = FacadeFactory.getExaminationFacade().getRegid(proc_id);
				 Announcement an = new Announcement();
				 an.setNotice_pro_person(this.getOperatorInfo().getUserName());
				 an.setNotice_content(row.getString("notice_content"));
				 an.setNotice_pro_time(new Date());
				 an.setBus_id(bus_id);
				 FacadeFactory.getExaminationFacade().saveAnnounce(an);	
			} catch(Exception e) {
				LogUtil.error(e.getMessage(), e);
				return new JsonResult(false, e.getMessage(), null);
			}
			return new JsonResult(true, "保存成功！", null);
	 }
	 
	 /**
	  * 
	  * saveAnnouncePub:保存公告发布信息. <br/>
	  * @author PANDA
	  * @return
	  * @since JDK 1.6
	  */
	 public JsonResult saveAnnouncePub(Row row){
		 //-------ajax---------
		 String proc_id = row.getString("proc_id");
		 try {   
			 String bus_id = FacadeFactory.getExaminationFacade().getRegid(proc_id);
			 Announcement an = new Announcement();
			 an.setNotice_code(row.getString("notice_code"));
			 an.setNotice_person(row.getString("notice_person"));
			 an.setNotice_limit(row.getDouble("notice_limit"));
			 an.setNoticie_date(DateUtils.string2Date(row.getString("noticie_date"),"yyyy-MM-dd"));
			 an.setNotice_pub_off(row.getString("notice_pub_off"));
			 an.setNotice_pub_time(DateUtils.string2Date(row.getString("notice_pub_time"),"yyyy-MM-dd"));
			 an.setPub_date(DateUtils.string2Date(row.getString("pub_date"),"yyyy-MM-dd"));
			 an.setPub_name_date(row.getString("pub_name_date"));
			 an.setNotice_content(row.getString("notice_content"));
			 an.setNotice_pro_person(row.getString("notice_pro_person"));
			 //an.setNotice_pro_time(DateUtils.string2Date(row.getString("notice_pro_time"),"yyyy-MM-dd"));
			 an.setBus_id(bus_id);
			 FacadeFactory.getExaminationFacade().saveAnnouncePub(an);	
		 } catch(Exception e) {
			 LogUtil.error(e.getMessage(), e);
			 return (new JsonResult(false, e.getMessage(), null));
		 }
		 return (new JsonResult(true, "保存成功！", null));
	 }
}


