package com.szhome.cq.business;

import java.util.List;
import java.util.Map;

import com.springjdbc.annotation.Page;
import com.szhome.cq.domain.model.Certificate;
import com.szhome.cq.domain.model.Mortgage;
/**
 * ҵ������
 * @author panda
 *
 */
public interface IMortgageFacade {
	/**
	 * 
	 * getMortById:����������ѯ������Ѻ�Ǽ���Ϣ. <br/>
	 * @author PANDA
	 * @param m
	 * @return
	 * @since JDK 1.6
	 */
	public Mortgage getMortById(Map m);
	/**
	 * 
	 * saveMortMess:���浥����Ѻ��Ϣ. <br/>
	 * @author PANDA
	 * @param mort
	 * @return
	 * @since JDK 1.6
	 */
	public void saveMortMess(Mortgage mort);
	
	
	/**
	 * 
	 * getMortcanById:����������ѯע����Ѻ�Ǽ���Ϣ. <br/>
	 * @author PANDA
	 * @param m
	 * @return
	 * @since JDK 1.6
	 */
	public Map  getMortcanById(String id);
	
	/**
	 * 
	 * getRegunitMess:��ȡ�Ǽǵ�Ԫ��Ϣ����. <br/>
	 * @author PANDA
	 * @param id
	 * @return
	 * @since JDK 1.6
	 */
	public Map getRegunitMess(String key,Map m);
	/**
	 * 
	 * getRegunitList:��ȡ�Ǽǵ�Ԫ����. <br/>
	 * @author PANDA
	 * @param m
	 * @return
	 * @since JDK 1.6
	 */
	public List<Map<String, Object>> getRegunitList(Map m);	
	/**
	 * 
	 * getRegById:��ȡ�Ǽ���Ϣ. <br/>
	 * @author PANDA
	 * @param id
	 * @return
	 * @since JDK 1.6
	 */
	public Map getRegById(String id);
    /**
     * 
     * ��Ѻ�����Ǽ�ҵ���У���ȡ��Ѻ����Ϣ����. <br/>
     * @author PANDA
     * @param m
     * @return
     * @since JDK 1.6
     */
	public List<Map<String, Object>> getMortgager(Map m);
	/**
	 * 
	 * getMortgagee:��Ѻ�����Ǽ�ҵ���У���ȡ��ѺȨ����Ϣ����. <br/>
	 * @author PANDA
	 * @param m
	 * @return
	 * @since JDK 1.6
	 */
	public List<Map<String, Object>> getMortgagee(Map m);
	
	
	/**
	 * 
	 * getMortgagerForCancel:��ȡĳ����Ѻ��¼�µĵ�Ѻ����Ϣ. <br/>
	 * @author PANDA
	 * @param m
	 * @return
	 * @since JDK 1.6
	 */
	public List<Map<String, Object>> getMortgagerSeted(Map m);
	
	/**
	 * 
	 * getMortgageeSeted:��ȡĳ����Ѻ��¼�µĵ�ѺȨ����Ϣ. <br/>
	 * @author PANDA
	 * @param m
	 * @return
	 * @since JDK 1.6
	 */
	public List<Map<String, Object>> getMortgageeSeted(Map m);
	
    
    /**
     * 
     * saveDispathcInfo:���淢����Ϣ. <br/>
     * @author PANDA
     * @param cer
     * @since JDK 1.6
     */
    public void saveDispathcInfo(Certificate cer,List<Map> list,String id);
    
    
    /**
	 * 
	 * getMortgageeTransferor:(��ȡ��ѺȨ��   ������̱��в�����   ��ӵǼǲ��л�ȡ). 
	 *
	 * @author Joyon
	 * @param m
	 * @return
	 * @since JDK 1.6
	 */
	public List<Map<String,Object>> getMortgageeTransferor(Map m);
	
	/**
	 * 
	 * saveBkMortgagerToBusMortgager:(��ǰ��Ѻ����µĵ�Ѻ�˱��浽�����˱���,�������������˵�Ѻ��  ���ٱ���  ����ֱ�ӷ���).
	 *
	 * @author Joyon
	 * @return
	 * @since JDK 1.6
	 */
	public List<Map<String,Object>> saveBkMortgagerToBusMortgager(String bus_id);
	
	/**
	 * 
	 * saveBkMortgageeToBusMortgagee:(����ǰ��Ѻ����µĵ�ѺȨ�˵������˱���   ������������е�ѺȨ��  ���ٱ���).
	 *
	 * @author Joyon
	 * @param paraMap
	 * @return
	 * @since JDK 1.6
	 */
	public List<Map<String,Object>> saveBkMortgageeToBusMortgagee(String bus_id);
	
	/**
	 * 
	 * saveBkMortageToBusMortgage:(��ǰ��Ѻ��ŵǼǲ��е�Ѻ��Ϣ���浽������Ϣ����   ���������Ϣ���м������򲻱���  ֱ�ӷ���).
	 *
	 * @author Joyon
	 * @param bus_id
	 * @return Mortgage
	 * @since JDK 1.6
	 */
	public Mortgage saveBkMortageToBusMortgage(String bus_id);

	 /**
     * 
     * getAllMortsByParam:���ݲ�������������е�ѺȨ��Ϣ. <br/>
     * @author Sam
     * @param paramMap
     * @since JDK 1.6
     */
    public Page getAllMortsByParam(Map<String,Object> paramMap);
    
    /**
	 * 
	 * getMortCancelMortgager:(��ȡ��Ѻע�� ��Ѻ��). 
	 *
	 * @author Joyon
	 * @param bus_id
	 * @return
	 * @since JDK 1.6
	 */
	public List<Map<String,Object>> getMortCancelMortgager(String bus_id);
	/**
	 * getMortsBybookCode:(����book_code�õ���Ѻ�����Ϣ,���ڱ����ӡ)
	 * @param paramMap
	 * @return
	 */
	public List<Map<String,Object>> getMortsBymortId(Map<String,Object> sqlMap,Map<String,Object> valueMap);
}

