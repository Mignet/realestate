package com.szhome.cq.business;

import java.util.List;
import java.util.Map;

import com.springjdbc.annotation.Page;
import com.szhome.cq.domain.model.Certificate;
import com.szhome.cq.domain.model.Mortgage;
/**
 * 业务主表
 * @author panda
 *
 */
public interface IMortgageFacade {
	/**
	 * 
	 * getMortById:根据条件查询单条抵押登记信息. <br/>
	 * @author PANDA
	 * @param m
	 * @return
	 * @since JDK 1.6
	 */
	public Mortgage getMortById(Map m);
	/**
	 * 
	 * saveMortMess:保存单条抵押信息. <br/>
	 * @author PANDA
	 * @param mort
	 * @return
	 * @since JDK 1.6
	 */
	public void saveMortMess(Mortgage mort);
	
	
	/**
	 * 
	 * getMortcanById:根据条件查询注销抵押登记信息. <br/>
	 * @author PANDA
	 * @param m
	 * @return
	 * @since JDK 1.6
	 */
	public Map  getMortcanById(String id);
	
	/**
	 * 
	 * getRegunitMess:获取登记单元信息集合. <br/>
	 * @author PANDA
	 * @param id
	 * @return
	 * @since JDK 1.6
	 */
	public Map getRegunitMess(String key,Map m);
	/**
	 * 
	 * getRegunitList:获取登记单元集合. <br/>
	 * @author PANDA
	 * @param m
	 * @return
	 * @since JDK 1.6
	 */
	public List<Map<String, Object>> getRegunitList(Map m);	
	/**
	 * 
	 * getRegById:获取登记信息. <br/>
	 * @author PANDA
	 * @param id
	 * @return
	 * @since JDK 1.6
	 */
	public Map getRegById(String id);
    /**
     * 
     * 抵押设立登记业务中，获取抵押人信息集合. <br/>
     * @author PANDA
     * @param m
     * @return
     * @since JDK 1.6
     */
	public List<Map<String, Object>> getMortgager(Map m);
	/**
	 * 
	 * getMortgagee:抵押设立登记业务中，获取抵押权人信息集合. <br/>
	 * @author PANDA
	 * @param m
	 * @return
	 * @since JDK 1.6
	 */
	public List<Map<String, Object>> getMortgagee(Map m);
	
	
	/**
	 * 
	 * getMortgagerForCancel:获取某条抵押记录下的抵押人信息. <br/>
	 * @author PANDA
	 * @param m
	 * @return
	 * @since JDK 1.6
	 */
	public List<Map<String, Object>> getMortgagerSeted(Map m);
	
	/**
	 * 
	 * getMortgageeSeted:获取某条抵押记录下的抵押权人信息. <br/>
	 * @author PANDA
	 * @param m
	 * @return
	 * @since JDK 1.6
	 */
	public List<Map<String, Object>> getMortgageeSeted(Map m);
	
    
    /**
     * 
     * saveDispathcInfo:保存发文信息. <br/>
     * @author PANDA
     * @param cer
     * @since JDK 1.6
     */
    public void saveDispathcInfo(Certificate cer,List<Map> list,String id);
    
    
    /**
	 * 
	 * getMortgageeTransferor:(获取抵押权人   如果过程表中不存在   则从登记簿中获取). 
	 *
	 * @author Joyon
	 * @param m
	 * @return
	 * @since JDK 1.6
	 */
	public List<Map<String,Object>> getMortgageeTransferor(Map m);
	
	/**
	 * 
	 * saveBkMortgagerToBusMortgager:(把前抵押编号下的抵押人保存到申请人表中,如果申请表中有了抵押人  则不再保存  而是直接返回).
	 *
	 * @author Joyon
	 * @return
	 * @since JDK 1.6
	 */
	public List<Map<String,Object>> saveBkMortgagerToBusMortgager(String bus_id);
	
	/**
	 * 
	 * saveBkMortgageeToBusMortgagee:(保存前抵押编号下的抵押权人到申请人表中   如果申请人中有抵押权人  则不再保存).
	 *
	 * @author Joyon
	 * @param paraMap
	 * @return
	 * @since JDK 1.6
	 */
	public List<Map<String,Object>> saveBkMortgageeToBusMortgagee(String bus_id);
	
	/**
	 * 
	 * saveBkMortageToBusMortgage:(把前抵押编号登记簿中抵押信息保存到过程信息表中   如果过程信息表中己存在则不保存  直接返回).
	 *
	 * @author Joyon
	 * @param bus_id
	 * @return Mortgage
	 * @since JDK 1.6
	 */
	public Mortgage saveBkMortageToBusMortgage(String bus_id);

	 /**
     * 
     * getAllMortsByParam:根据参数条件获得所有抵押权信息. <br/>
     * @author Sam
     * @param paramMap
     * @since JDK 1.6
     */
    public Page getAllMortsByParam(Map<String,Object> paramMap);
    
    /**
	 * 
	 * getMortCancelMortgager:(获取抵押注销 抵押人). 
	 *
	 * @author Joyon
	 * @param bus_id
	 * @return
	 * @since JDK 1.6
	 */
	public List<Map<String,Object>> getMortCancelMortgager(String bus_id);
	/**
	 * getMortsBybookCode:(根据book_code得到抵押相关信息,用于报表打印)
	 * @param paramMap
	 * @return
	 */
	public List<Map<String,Object>> getMortsBymortId(Map<String,Object> sqlMap,Map<String,Object> valueMap);
}

