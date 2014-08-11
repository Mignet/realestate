/**
 * Project Name:dxtx_re
 * File Name:CommonFacade.java
 * Package Name:com.szhome.cq.business.impl
 * Date:2013-12-26����2:35:25
 * Copyright (c) 2013, chenzhou1025@126.com All Rights Reserved.
 *
*/

package com.szhome.cq.business.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.plan.commons.Row;
import com.plan.commons.RowImpl;
import com.szhome.commons.exception.GeneralFailureException;
import com.szhome.cq.business.BusinessException;
import com.szhome.cq.business.FacadeFactory;
import com.szhome.cq.business.IExaminationFacade;
import com.szhome.cq.business.vo.AnnounceVO;
import com.szhome.cq.business.vo.ExamVo;
import com.szhome.cq.domain.model.Announcement;
import com.szhome.cq.domain.model.BusinessMain;
import com.szhome.cq.domain.model.ExamFirst;
import com.szhome.cq.domain.model.ExamForth;
import com.szhome.cq.domain.model.ExamSecond;
import com.szhome.cq.domain.model.ExamThird;
import com.szhome.cq.domain.model.Examine;
import com.szhome.cq.domain.model.User;
import com.szhome.security.delegate.SecurityUtil;
import com.szhome.security.ext.SecurityExtApi;
import com.szhome.security.ext.UserInfo;

/**
 * �������Facade����
 * Date:     2013-12-26 ����2:35:25 <br/>
 * @author   xuzz
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
@Component
@Transactional
@Scope("prototype")
public class ExaminationFacade implements IExaminationFacade {
	@Autowired
	private Examine examineDao;  //�������Dao�����ڳ�����׼------������
	public Examine getExamineDao() {
		return examineDao;
	}

	public void setExamineDao(Examine examineDao) {
		this.examineDao = examineDao;
	}
	private Examine examine; //������������ڳ�����׼------������
	public Examine getExamine() {
		return examine;
	}

	public void setExamine(Examine examine) {
		this.examine = examine;
	}
	@Autowired
	private ExamFirst examfDao;	
	@Autowired
	private ExamSecond examsDao;	
	@Autowired
	private ExamThird examtDao;	
	@Autowired
	private ExamForth examfoDao;	
	@Autowired
	private BusinessMain busDao;
	@Autowired
	private Announcement announceDao;
	@Autowired
	private User userDao;
	
	
	
	/**
	 * 
	 * @param �������.
	 * @author xuzz
	 * @see com.szhome.cq.business.ICommonFacade#saveExamine(com.szhome.cq.domain.model.Examine)
	 */
	@Override
	@Transactional
	public void saveExamine(Examine ex) {
		Map m=new HashMap();
		m.put("bus_id",ex.getBus_id());
		m.put("opinion_type", ex.getOpinion_type());
		Examine exa= examineDao.get(" where bus_id=:bus_id and opinion_type =:opinion_type", m);
		
		try {
			if(exa != null){
				ex.setOpinion_id(exa.getOpinion_id());
				examineDao.update(ex);
			}else{
				ex.setOpinion_id(examfDao.getSeqId());
				examineDao.save(ex);
		}
		} catch (Exception e1) {
			e1.printStackTrace();
			throw new BusinessException("������Ϣ:"+e1.getMessage());
		}
	}
	
	/**
	 * 
	 * ͨ��ҵ��ID ��������ͻ�ȡ���
	 *
	 * @author Joyon
	 * @param bus_id
	 * @param opinion_type
	 * @return
	 * @since JDK 1.6
	 */
	public Examine getExamineBybusidAndOpiniontype(String bus_id,String opinion_type) throws BusinessException{
		Map paraMap = new HashMap();
		paraMap.put("bus_id", bus_id);
		paraMap.put("opinion_type", opinion_type);
		return examineDao.get("where bus_id=:bus_id and opinion_type=:opinion_type", paraMap);
	}
	
	/**
	 * 
	 * TODO ����������.
	 * @see com.szhome.cq.business.ICommonFacade#saveExaMess(com.szhome.cq.domain.model.ExamSecond)
	 */
	@Override
	@Transactional
	public void saveExaFirst(ExamFirst ex) {
		/**
		 * 1,Spyjbid!=0,update
		 * else save
		 */
		Map m=new HashMap();
		m.put("bus_id",ex.getBus_id());
		m.put("opinion_type", ex.getOpinion_type());
		ExamFirst exa= examfDao.get(" where bus_id=:bus_id and opinion_type =:opinion_type", m);
		
		try {
			if(exa != null){
				ex.setOpinion_id(exa.getOpinion_id());
				examfDao.update(ex);
			}else{
				ex.setOpinion_id(examfDao.getSeqId());
				examfDao.save(ex);
		}
		} catch (Exception e1) {
			e1.printStackTrace();
			throw new BusinessException("������Ϣ:"+e1.getMessage());
		}
	}
	/**
	 * 
	 * ���渴�����.
	 * @see com.szhome.cq.business.IExaminationFacade#saveExaFirst(com.szhome.cq.domain.model.ExamFirst)
	 */
	@Override
	@Transactional
	public void saveExaSecond(ExamSecond ex) {
		Map m=new HashMap();
		m.put("bus_id",ex.getBus_id());
		m.put("opinion_type", ex.getOpinion_type());
		ExamSecond exa= examsDao.get(" where bus_id=:bus_id and opinion_type =:opinion_type", m);
		
		try {
			if(exa != null){
				ex.setOpinion_id(exa.getOpinion_id());
				examsDao.update(ex);
			}else{
				ex.setOpinion_id(examfDao.getSeqId());
				examsDao.save(ex);
		}
		} catch (Exception e1) {
			e1.printStackTrace();
			throw new BusinessException("������Ϣ:"+e1.getMessage());
		}
	}
	/**
	 * 
	 * ����������.
	 * @see com.szhome.cq.business.IExaminationFacade#saveExaFirst(com.szhome.cq.domain.model.ExamFirst)
	 */
	@Override
	@Transactional
	public void saveExaThird(ExamThird ex) {
		Map m=new HashMap();
		m.put("bus_id",ex.getBus_id());
		m.put("opinion_type", ex.getOpinion_type());
		ExamSecond exa= examsDao.get(" where bus_id=:bus_id and opinion_type =:opinion_type", m);
		
		try {
			if(exa != null){
				//Date d = new Date();
				//ex.setOpinion_time(Date.parse(ex.getOpinion_time()));
				ex.setOpinion_id(exa.getOpinion_id());
				//ex.setOpinion_time(Date.parse(ex.getOpinion_time()));
				examtDao.update(ex);
			}else{
				ex.setOpinion_id(examfDao.getSeqId());
				examtDao.save(ex);
		}
		} catch (Exception e1) {
			e1.printStackTrace();
			throw new BusinessException("������Ϣ:"+e1.getMessage());
		}
	}
	/**
	 * 
	 * �����׼���.
	 * @see com.szhome.cq.business.IExaminationFacade#saveExaFirst(com.szhome.cq.domain.model.ExamFirst)
	 */
	
	@Override
	@Transactional
	public void saveExaForth(ExamForth ex) {
		Map m=new HashMap();
		m.put("bus_id",ex.getBus_id());
		m.put("opinion_type", ex.getOpinion_type());
		ExamSecond exa= examsDao.get(" where bus_id=:bus_id and opinion_type =:opinion_type", m);
		
		try {
			if(exa != null){
				ex.setOpinion_id(exa.getOpinion_id());
				examfoDao.update(ex);
			}else{
				ex.setOpinion_id(examfDao.getSeqId());
				examfoDao.save(ex);
		}
		} catch (Exception e1) {
			e1.printStackTrace();
			throw new BusinessException("������Ϣ:"+e1.getMessage());
		}
		
	}
	/**
	 * getExamineById ����ҵ��ID��ѯ�������
	 * @author xuzz
	 * @param busid
	 * @return
	 */
	public List<Examine> getExamineById(String busid)
	{
		double userid = 0.0;
		String username = "";
		Map map = new HashMap();
		map.put("bus_id",busid);
		//��ѯ�������
		List<Examine> ListMapexam = examineDao.queryListByKey("Examination.getExamineById"," where bus_id =:bus_id",map);
		if(ListMapexam!=null)
		{
			if(ListMapexam.size()>0)
			{
				for(Examine ex:ListMapexam)
				{
					try {
						UserInfo sessionUser = SecurityExtApi.getUserInfoByUserID(ex.getChecker_no());
						ex.setChecker_no(sessionUser.getUserName());
					} catch (GeneralFailureException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				return ListMapexam;
			}
		}
		return null;
	}
	
	/**
	 * 
	 * TODO ��������ʵ��id��ȡ��������Ϣ.
	 * @see com.szhome.cq.business.ICommonFacade#getExamByid(java.util.Map)
	 */
	@Override
	public ExamVo getExamByid(String id) {
		double userid = 0.0;
		String username = "";
		Map map = new HashMap();
		map.put("bus_id",id);
		
		ExamVo  anvo = new ExamVo();
		
		//��ѯ�������
		ExamFirst  examf = examfDao.get(" where bus_id =:bus_id and opinion_type ='����'",map);
        if( examf == null){
			
			//throw new BusinessException("������Ϣ:"+new Exception().getMessage());
        	System.out.println();
		}else{
			
			userid = Double.valueOf(examf.getChecker_no());
			username =  this.getUserInfo(userid).getUser_name();
			
			examf.setChecker_no(username);
			
			anvo.setExamf(examf);
		}
	//��ѯ�������
		ExamSecond  exams = examsDao.get(" where bus_id =:bus_id and opinion_type ='����'",map);
		 if( exams == null){
				
				//throw new BusinessException("������Ϣ:"+new Exception().getMessage());
			 System.out.println();
			}else{
				
				userid = Double.valueOf(exams.getChecker_no());
				username =  this.getUserInfo(userid).getUser_name();
				
				exams.setChecker_no(username);
				
				anvo.setExams(exams);
				
			}
		//��ѯ������
		ExamThird  examt = examtDao.get(" where bus_id =:bus_id and opinion_type ='���'",map);
		if( examt == null){
			
			//throw new BusinessException("������Ϣ:"+new Exception().getMessage());
			System.out.println();
		}else{
			userid = Double.valueOf(examt.getChecker_no());
			username =  this.getUserInfo(userid).getUser_name();
			
			examt.setChecker_no(username);
			anvo.setExamt(examt);	
		}
		//��ѯ��׼���
		ExamForth  examfo = examfoDao.get(" where bus_id =:bus_id and opinion_type ='��׼'",map);
		if( examfo == null){
			
			//throw new BusinessException("������Ϣ:"+new Exception().getMessage());
			System.out.println();
		}else{
			//userid = Double.valueOf(examfo.getChecker_no());
			username =  this.getUserInfo(userid).getUser_name();
			
			examfo.setChecker_no(username);
			anvo.setExamfo(examfo);
			
		}		
		return anvo;
	}
	/**
	 * 
	 *  �������̽ڵ��ȡҵ����.
	 * @see com.szhome.cq.business.IExaminationFacade#getRegid(java.util.Map)
	 */
	@Override
	public String getRegid(String id) {
		Map map=new HashMap();
		map.put("proc_id", id);
		BusinessMain bus = busDao.get(" where proc_id =:proc_id",map);
		if(bus == null){
			
			//throw  new BusinessException("������Ϣ:��ѯ����");
			 return null;
		}
		return bus.getBus_id();
	}
	/**
	 * 
	 *  ��ѯ�������ݼ��������.
	 * @see com.szhome.cq.business.IExaminationFacade#getAnnounceByid(java.lang.String)
	 */
	@Override
	public AnnounceVO getAnnounceByid(String id) {
		double userid = 0.0;
		String username = "";
		Map map = new HashMap();
		map.put("bus_id",id);
		
		AnnounceVO  anvo = new AnnounceVO();
		//��ѯ���������Ϣ
		Announcement an = announceDao.get(" where bus_id =:bus_id",map);
		if( an == null){
			System.out.println();
		}else{
			anvo.setAnnouce(an);
		}
		return anvo;
	}
	/**
	 * 
	 * ���湫����Ϣ.
	 * @see com.szhome.cq.business.IExaminationFacade#saveAnnounce(com.szhome.cq.domain.model.Announcement)
	 */
	@Override
	@Transactional
	public void saveAnnounce(Announcement an) {
		
		Map m=new HashMap();
		m.put("bus_id",an.getBus_id());
		Announcement ann = announceDao.get(" where bus_id=:bus_id", m);
		
		try {
			if(ann != null){
				ann.setNotice_pro_person(an.getNotice_pro_person());
				ann.setNotice_content(an.getNotice_content());
				ann.setNotice_pro_time(an.getNotice_pro_time());
				announceDao.update(ann);
			}else{
				an.setNotice_id(announceDao.getSeqId());
				announceDao.save(an);
		}
		} catch (Exception e1) {
			e1.printStackTrace();
			throw new BusinessException("������Ϣ:"+e1.getMessage());
		}
		
	}
	/**
	 * 
	 * ���湫�淢����Ϣ.
	 * @see com.szhome.cq.business.IExaminationFacade#saveAnnouncePub(com.szhome.cq.domain.model.Announcement)
	 */
	@Override
	@Transactional
	public void saveAnnouncePub(Announcement an) {
		
		Map m=new HashMap();
		m.put("bus_id",an.getBus_id());
		Announcement ann = announceDao.get(" where bus_id=:bus_id", m);
		if(ann.getNotice_code()==null)
		{
			Row row = new RowImpl();
			row.put("name", "������");
			ann.setNotice_code(FacadeFactory.getIdentifierFacade().getSerialNumber(row));
			an.setNotice_code(ann.getNotice_code());
		}
		try 
		{
			if(ann != null){
				an.setNotice_pro_person(ann.getNotice_pro_person());
				an.setNotice_content(ann.getNotice_content());
				an.setNotice_pro_time(ann.getNotice_pro_time());
				an.setNotice_id(ann.getNotice_id());
				announceDao.update(an);
			}
			else
			{
				an.setNotice_id(announceDao.getSeqId());
				announceDao.save(an);
			}
		} 
		catch (Exception e1) {
			e1.printStackTrace();
			throw new BusinessException("������Ϣ:"+e1.getMessage());
		}
		
	}
	/**
	 * 
	 * �����û�id��ȡ�û���Ϣ.
	 * @see com.szhome.cq.business.IExaminationFacade#getUserInfo()
	 */
	@Override
	public User getUserInfo(double id) {
		Map m=new HashMap();
		m.put("id",id);
		User  user = userDao.get(" where user_id=:id", m);
		return user;
	}
	
	
	
	
	


   
	
	
}


