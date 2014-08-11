package com.szhome.cq.business;

import java.util.List;

import org.json.JSONException;

import com.springjdbc.annotation.Page;
import com.szhome.cq.domain.model.Method;
import com.szhome.cq.domain.model.Service;

/**
 * �������߹���
 * @author Mignet
 *
 */
public interface IMonitorFacade {

	public Page<Service> getAllAPIs(int intPage, int number);

	/**
	 * initAllAPIs:��ȡAPI�������ݿ�Ƚ�. <br/>
	 * ������ڣ���ô���ò��룬��������ڣ�����<br/>
	 *
	 * @author dxtx
	 * @since JDK 1.6
	 */
	public void initAllAPIs();

	/**
	 * saveAPIs:(����API). <br/>
	 *
	 * @author dxtx
	 * @param datas
	 * @throws JSONException 
	 * @since JDK 1.6
	 */
	public void saveAPIs(String datas) throws JSONException;

	/**
	 * 
	 * getMethodsBySID:(�����Ӧ�ķ���). <br/>
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
	 * saveMethods:(�����޸ĵķ���). <br/>
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

