package com.szhome.cq.business.impl;

import java.lang.reflect.Method;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.ImmutableMap;
import com.springjdbc.annotation.Page;
import com.szhome.cq.business.IMonitorFacade;
import com.szhome.cq.domain.model.Service;
import com.szhome.cq.domain.model.User;
import com.szhome.cq.utils.SequenceUtil;

/**
 * API管理
 * @author  Mignet
 */
@Component
@Transactional
@Scope("prototype")
public class MonitorFacade implements IMonitorFacade {

	@Autowired
	private Service sDao;
	@Autowired
	private com.szhome.cq.domain.model.Method mDao;
	
	@Override
	public Page<Service> getAllAPIs(int pageNo, int pageSize) {
		return sDao.queryDomainPageBykeyForOracle("Monitor.queryAPIs", null, pageNo, pageSize);
	}
	
	private boolean isExist(String key,String name){
		if("S".equals(key)){
			if(sDao.get(" where sname=':sname'", ImmutableMap.of("sname",name))!=null)return true;
		}
		if("M".equals(key)){
			if(mDao.get(" where mname=':mname'", ImmutableMap.of("mname",name))!=null)return true;
		}
		return false;
	}

	@Override
	public void initAllAPIs() {
		try {
			Class<?>  factory = Class
					.forName("com.szhome.cq.business.FacadeFactory");
			for (Method m : factory.getDeclaredMethods()) {
				String name = m.getName();
				if (name.endsWith("Facade")) {
					String serviceName = "I" + m.getName().substring(3);
					System.out.println("serviceName:"+serviceName);
					if(!isExist("S",serviceName)){
						Service s = new Service();
						s.setSid(SequenceUtil.getGlobalSeqID());
						s.setSname(serviceName.length()>20?serviceName.substring(0,20):serviceName);
						s.setRegflag("01");//已注册
						s.setInfo(serviceName);
						sDao.save(s);
						//System.out.println(m.getReturnType());
						Class<?> c = m.getReturnType();
						for (Method a : c.getDeclaredMethods()) {
							String methodName = a.getName();
							System.out.println("Method:"+methodName);
							if(!isExist("M",methodName)){
								com.szhome.cq.domain.model.Method me = new com.szhome.cq.domain.model.Method();
								me.setMid(SequenceUtil.getGlobalSeqID());
								me.setSid(s.getSid());
								me.setMname(methodName.length()>20?methodName.substring(0,20):methodName);
								String returnType= a.getReturnType().getName();
								me.setReturntype(returnType.substring(returnType.lastIndexOf(".")+1));
								me.setInfo(methodName);
								me.setInlineflag("00");//对内
								mDao.save(me);
							}
						}
					}
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void saveAPIs(String datas) throws JSONException {
		JSONArray jsonArray = new JSONArray(datas);
		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject service = jsonArray.getJSONObject(i);
			Service u = sDao.get(new Service(service.getString("sid")));
			u.setSname(service.getString("sname"));
			u.setRegflag(service.getString("regflag"));
			u.setInfo(service.getString("info"));
			u.setRemark(service.getString("remark"));
			sDao.update(u);
		}
	}

	@Override
	public Page<com.szhome.cq.domain.model.Method> getMethodsBySID(
			String sid, int pageNo, int pageSize) {
		return mDao.queryDomainPageBykeyForOracle("Monitor.queryMethodsBySID", ImmutableMap.of("sid",sid==null?"":sid), pageNo, pageSize);
	}

	@Override
	public void saveMethods(String datas) throws JSONException {
		JSONArray jsonArray = new JSONArray(datas);
		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject method = jsonArray.getJSONObject(i);
			com.szhome.cq.domain.model.Method u = mDao.get(new com.szhome.cq.domain.model.Method(method.getString("mid")));
			u.setSid(method.getString("sid"));
			u.setMname(method.getString("mname"));
			u.setParameters(method.getString("parameters"));
			u.setReturntype(method.getString("returntype"));
			u.setInlineflag(method.getString("inlineflag"));
			u.setInfo(method.getString("info"));
			u.setRemark(method.getString("remark"));
			mDao.update(u);
		}
	}

	@Override
	public List<Service> getAllAPIs() {
		return sDao.queryListByKey("Monitor.queryAPIs",null);
	}

	@Override
	public List<com.szhome.cq.domain.model.Method> getMethodsBySID(
			String sid) {
		return mDao.queryListByKey("Monitor.queryMethodsBySID", ImmutableMap.of("sid",sid==null?"":sid));
	}
	
}
