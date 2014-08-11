package com.szhome.cq.business;

import java.util.List;

import org.json.JSONException;

import com.springjdbc.annotation.Page;
import com.szhome.cq.domain.model.Method;
import com.szhome.cq.domain.model.Service;

/**
 * 服务总线管理
 * @author Mignet
 *
 */
public interface IMonitorFacade {

	public Page<Service> getAllAPIs(int intPage, int number);

	/**
	 * initAllAPIs:获取API并与数据库比较. <br/>
	 * 如果存在，那么不用插入，如果不存在，插入<br/>
	 *
	 * @author dxtx
	 * @since JDK 1.6
	 */
	public void initAllAPIs();

	/**
	 * saveAPIs:(保存API). <br/>
	 *
	 * @author dxtx
	 * @param datas
	 * @throws JSONException 
	 * @since JDK 1.6
	 */
	public void saveAPIs(String datas) throws JSONException;

	/**
	 * 
	 * getMethodsBySID:(服务对应的方法). <br/>
	 *
	 * @author dxtx
	 * @param sid
	 * @param intPage
	 * @param number
	 * @return Page<Method>
	 * @since JDK 1.6
	 */
	public Page<Method> getMethodsBySID(String sid, int intPage, int number);

	/**
	 * 
	 * saveMethods:(保存修改的方法). <br/>
	 *
	 * @author dxtx
	 * @param datas
	 * @throws JSONException 
	 * @since JDK 1.6
	 */
	public void saveMethods(String datas) throws JSONException;

	public List<Service> getAllAPIs();

	public List<Method> getMethodsBySID(String sid);

}

