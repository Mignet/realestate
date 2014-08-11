package com.szhome.cq.web.bookmanage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.servlet.ModelAndView;

import com.plan.commons.Row;
import com.springjdbc.annotation.Page;
import com.szhome.cq.business.FacadeFactory;
import com.szhome.cq.business.IAttachFacade;
import com.szhome.cq.business.IBookFacade;
import com.szhome.cq.business.ICommonFacade;
import com.szhome.cq.business.IDissentFacade;
import com.szhome.cq.business.IEasementFacade;
import com.szhome.cq.business.IHouseFacade;
import com.szhome.cq.business.IMortgageFacade;
import com.szhome.cq.business.IParcelFacade;
import com.szhome.cq.business.IPreadviceFacade;
import com.szhome.cq.business.IPresaleFacade;
import com.szhome.cq.business.IPrivateRealFacade;
import com.szhome.cq.business.IPropertyRightFacade;
import com.szhome.cq.business.IRegisterFacade;
import com.szhome.cq.business.vo.SearchOptionsVO;
import com.szhome.cq.delegate.BaseDelegate;
import com.szhome.cq.utils.JsonUtil;
import com.szhome.cq.utils.SystemManageCommParam;
import com.szhome.cq.utils.WbfConstants;
import com.szhome.cq.utils.WebUtil;

public class BookManageAction extends BaseDelegate {

	private IParcelFacade  parcelPacade = FacadeFactory.getParcelFacade();          //宗地服务
	private IHouseFacade  housePacade = FacadeFactory.getHouseFacade();             //房屋服务
	private IRegisterFacade registerFacade = FacadeFactory.getRegisterFacade();		//登记簿服务
	private ICommonFacade   commonFacade   = FacadeFactory.getCommonFacade();       //共享服务服务
	private IBookFacade  bookFacade        = FacadeFactory.getBookFacade();         //登记簿服务
	private IMortgageFacade mortgageFacade = FacadeFactory.getMortgageFacade();     //抵押权服务
	private IAttachFacade     attachFacade = FacadeFactory.getAttachFacade();       //查封登记服务
	private IPreadviceFacade  preadviceFacade = FacadeFactory.getPreadviceFacade(); //预告登记服务
	private IEasementFacade  easementFacade = FacadeFactory.getEasementFacade();    //地役权服务
	private IDissentFacade   dissentFacade = FacadeFactory.getDissentFacade();      //异议登记服务
	//private IOwnershipFacade ownershipFacade = FacadeFactory.getOwnershipFacade();  //产权登记服务
    private IPropertyRightFacade propertyRightFacade = FacadeFactory.getPropertyRightFacade();//得到产权服务Facade
    private IPrivateRealFacade privaterealFacade = FacadeFactory.getPrivateRealFacade();///得到个人产权服务Facade
    private IPresaleFacade presaleFacade = FacadeFactory.getPresaleFacade();///得到个人产权服务Facade
	
    /**
     * 
     * 传递参数并跳转页面
     *
     * @author dxtx
     * @param row
     * @return
     * @since JDK 1.6
     */
    public ModelAndView home(Row row){
    	ModelAndView mv = new ModelAndView();
    	mv.addObject("reg_unit_type",row.getString("reg_unit_type"));
    	mv.addObject("realestate_type",row.getString("realestate_type")==null?"":row.getString("realestate_type"));
    	mv.addObject("PARCELCODE",WbfConstants.PARCEL);
    	mv.addObject("HOUSECODE",WbfConstants.HOUSE);
    	mv.setViewName("bookmanage/registerView");
    	return mv;
    }
    
    
	/* 登记薄信息处理  */
	//功能处理代码板块  Begin
	public String bookInfoView(Row row){
		String str = null;
		Map<String,Object> resultMap = null;
		List<Map<String,Object>> resultLst = null;
		Map<String,Object> paramMap = new HashMap<String, Object>();
		String book_code = row.getString("book_code");
		String reg_code = row.getString("reg_code");
		String reg_unit_type = row.getString("reg_unit_type");
		String where_code = row.getString("where_code");
		String bok_is_type = row.getString("bok_is_type");
		paramMap.put("book_code", book_code!=null?book_code.trim():"");
		paramMap.put("reg_code", reg_code!=null?reg_code.trim():"");
		paramMap.put("reg_unit_type", reg_unit_type!=null?reg_unit_type.trim():"");
		if(!(reg_unit_type!=null?reg_unit_type.trim():"").equals(""))
			if(reg_unit_type.equals(WbfConstants.PARCEL))
				   paramMap.put("parcel_code", where_code!=null?where_code.trim():"");
			else if(reg_unit_type.equals(WbfConstants.HOUSE))
				   paramMap.put("house_code", where_code!=null?where_code.trim():"");
			else{
				   logger.debug("getBookHolderLstByParam --:reg_unit_type="+reg_unit_type+";where_code="+where_code);
				   return this.setActionJsonObject(str);
			}
		try {
			if(bok_is_type.equals(SystemManageCommParam.NATURAL)){
				resultMap = bookFacade.getBookNatualInfo(paramMap);
			}else if(bok_is_type.equals(SystemManageCommParam.OWNERSHIPORUSER)){
				if(reg_unit_type.equals(WbfConstants.HOUSE)){
					resultLst = bookFacade.getBookOwnershipInfoLst(paramMap);
				}else{
					/*resultMap = new HashMap<String, Object>();
					List<Map<String,Object>> holderLst = bookFacade.getBookHolderLst(paramMap);
					List<Map<String,Object>> userinfoLst = bookFacade.getBookUserInfoLst(paramMap);
					resultMap.put("holder", holderLst);
					resultMap.put("userright", userinfoLst);*/
					resultLst = bookFacade.getBookUserInfoLst(paramMap);
				}
			}else if(bok_is_type.equals(SystemManageCommParam.MORT)){
				resultLst = bookFacade.getBookMortInfoLst(paramMap);
			}else if(bok_is_type.equals(SystemManageCommParam.ATTACH)){
				resultLst = bookFacade.getBookAttachInfoLst(paramMap);
			}else if(bok_is_type.equals(SystemManageCommParam.DISSENT)){
				resultLst = bookFacade.getBookDissentInfoLst(paramMap);
			}else if(bok_is_type.equals(SystemManageCommParam.EASEMENT)){
				resultLst = bookFacade.getBookEasementInfoLst(paramMap);
			}else if(bok_is_type.equals(SystemManageCommParam.PREADVICE)){
				resultLst = bookFacade.getBookHouseAdvInfoLst(paramMap);
			}
			if(resultMap != null && resultMap.size() > 0){
				str = JsonUtil.map2json(resultMap);
			}else if(resultLst != null && resultLst.size() > 0){
				str = JsonUtil.list2json(resultLst);
			}
			logger.info("bookInfoView --"+bok_is_type+":"+str);
		} catch (Exception e) {
             logger.error("bookInfoView --"+bok_is_type+":"+e.getMessage(), e);			
		}
		return this.setActionJsonObject(str);
	}
	
	/**
	 * 根据权利人集合ID,登记单元类型,登记单元编号,查找权利人信息
	 * @return
	 */
	public String getBookHolderLstByParam(Row row){
		String str = null;
		String holder_id = row.getString("holder_id");
		String reg_unit_type = row.getString("reg_unit_type");
		String book_code = row.getString("book_code");
		String reg_code = row.getString("reg_code");
		List<Map<String,Object>> resultLst = null;
		Map<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("holder_id", holder_id!=null?holder_id.trim():"");
		paramMap.put("reg_unit_type", reg_unit_type!=null?reg_unit_type.trim():"");
		paramMap.put("reg_code", reg_code!=null?reg_code.trim():"");
		if(!(reg_unit_type!=null?reg_unit_type.trim():"").equals(""))
		if(reg_unit_type.equals(WbfConstants.PARCEL))
			   paramMap.put("parcel_id", book_code!=null?book_code.trim():"");
		else if(reg_unit_type.equals(WbfConstants.HOUSE))
			   paramMap.put("house_id", book_code!=null?book_code.trim():"");
		else{
			   logger.debug("getBookHolderLstByParam --:reg_unit_type="+reg_unit_type+";book_code="+book_code);
			   return this.setActionJsonObject(str);
		}
		try {
			resultLst = bookFacade.getBookHolderLst(paramMap);
			if(resultLst != null && resultLst.size() > 0){
					str = JsonUtil.list2json(resultLst);
			}
			logger.info("getBookHolderLstByParam --:"+str);
		} catch (Exception e) {
             logger.error("getBookHolderLstByParam --:"+e.getMessage(), e);			
		}
		return this.setActionJsonObject(str);
	}
	//功能处理代码板块  End
	/**
	 * 根据查询条件得到宗地信息
	 * getParcelInfosBySearch <br/>
	 * @author Sam
	 * @Date  2014-03-14
	 * @return
	 * @since JDK 1.6
	 */
	public String getParcelInfosBySearch(Row row){
		String pageNumber = row.getString("pageNumber");
		String maxPageItems = row.getString("maxPageItems");
		SearchOptionsVO sovo = (SearchOptionsVO)WebUtil.packageEntity("SearchOptionsVO", row, "sovo");
		/*sovo.setHoldername(row.getString("sovo.holdername"));
		sovo.setIdno(row.getString("sovo.idno"));
		sovo.setCerno(row.getString("sovo.cerno"));
		sovo.setParcelcode(row.getString("sovo.parcelcode"));
		sovo.setParceladdr(row.getString("sovo.parceladdr"));
		sovo.setParcelusage(row.getString("sovo.parcelusage"));
		sovo.setRegcode(row.getString("sovo.regcode"));
		sovo.setRightstate(row.getString("sovo.rightstate"));*/
		List<Map<String, Object>> parcellist = null;
		Map<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("PAGENO", pageNumber);
		paramMap.put("PAGESIZE", maxPageItems);
		paramMap.put("DXTXOBJECT", sovo);
		String str = null;
		try {
			parcellist = parcelPacade.getParcelInfosByOptions(paramMap);
			if(parcellist!=null && parcellist.size() > 0){
			    str =JsonUtil.list2json(parcellist);
			}
			logger.info("getParcelInfosBySearch --:"+str);
		} catch (Exception e) {
             logger.error("getParcelInfosBySearch --:"+e.getMessage(), e);			
		}
		logger.info("getParcelInfosBySearch str:"+str);
		return this.setActionJsonObject(str);
	}
	
	///*******************************Select Action Region**********************************//
	/**
	 * 根据查询条件得到房屋信息
	 * getHouseInfosBySearch <br/>
	 * @author Sam
	 * @Date  2014-03-27
	 * @return
	 * @since JDK 1.6
	 */
	public String getHouseInfosBySearch(Row row){
		String pageNumber = row.getString("pageNumber");
		String maxPageItems = row.getString("maxPageItems");
		SearchOptionsVO sovo = (SearchOptionsVO)WebUtil.packageEntity("SearchOptionsVO", row, "sovo");
		/*sovo.setHoldername(row.getString("sovo.holdername"));
		sovo.setIdno(row.getString("sovo.idno"));
		sovo.setCerno(row.getString("sovo.cerno"));
		sovo.setParcelcode(row.getString("sovo.parcelcode"));
		sovo.setParceladdr(row.getString("sovo.parceladdr"));
		sovo.setParcelusage(row.getString("sovo.parcelusage"));
		sovo.setRegcode(row.getString("sovo.regcode"));
		sovo.setRightstate(row.getString("sovo.rightstate"));*/
		List<Map<String, Object>> houselist = null;
		Map<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("PAGENO", pageNumber);
		paramMap.put("PAGESIZE", maxPageItems);
		paramMap.put("DXTXOBJECT", sovo);
		String str = null;
		try {
			houselist = housePacade.getHouseInfosByOptions(paramMap);
			if(houselist!=null && houselist.size() > 0){
				str =JsonUtil.list2json(houselist);
			}
			logger.info("getHouseInfosBySearch --:"+str);
		} catch (Exception e) {
			logger.error("getHouseInfosBySearch --:"+e.getMessage(), e);			
		}
		logger.info("getHouseInfosBySearch str:"+str);
		return this.setActionJsonObject(str);
	}
	/**
	 * 根据查询条件得到所有抵押权信息
	 * getAllMortInfosBySearch <br/>
	 * @author Sam
	 * @Date  2014-04-08
	 * @return
	 * @since JDK 1.6
	 */
	public String getAllMortInfosBySearch(Row row){
		String pageNumber = row.getString("pageNumber");
		String maxPageItems = row.getString("maxPageItems");
		SearchOptionsVO sovo = (SearchOptionsVO)WebUtil.packageEntity("SearchOptionsVO", row, "sovo");
		/*sovo.setHoldername(row.getString("sovo.holdername"));
		sovo.setIdno(row.getString("sovo.idno"));
		sovo.setCerno(row.getString("sovo.cerno"));
		sovo.setParcelcode(row.getString("sovo.parcelcode"));
		sovo.setParceladdr(row.getString("sovo.parceladdr"));
		sovo.setParcelusage(row.getString("sovo.parcelusage"));
		sovo.setRegcode(row.getString("sovo.regcode"));
		sovo.setRightstate(row.getString("sovo.rightstate"));*/
		Page<Map<String, Object>> mortPage = null;
		Map<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("PAGENO", pageNumber);
		paramMap.put("PAGESIZE", maxPageItems);
		paramMap.put("DXTXOBJECT", sovo);
		//paramMap.put("DXTXARRAY", selflag);
		try {
			mortPage = mortgageFacade.getAllMortsByParam(paramMap);
		} catch (Exception e) {
			logger.error("getAllMortInfosBySearch --:"+e.getMessage(), e);		
		}
		return pageResultToJson(mortPage);
	}
	/**
	 * 根据查询条件得到所有查封信息
	 * getAllAttachInfosBySearch <br/>
	 * @author Sam
	 * @Date  2014-04-08
	 * @return
	 * @since JDK 1.6
	 */
	public String getAllAttachInfosBySearch(Row row){
		String pageNumber = row.getString("pageNumber");
		String maxPageItems = row.getString("maxPageItems");
		SearchOptionsVO sovo = (SearchOptionsVO)WebUtil.packageEntity("SearchOptionsVO", row, "sovo");
		/*sovo.setHoldername(row.getString("sovo.holdername"));
		sovo.setIdno(row.getString("sovo.idno"));
		sovo.setCerno(row.getString("sovo.cerno"));
		sovo.setParcelcode(row.getString("sovo.parcelcode"));
		sovo.setParceladdr(row.getString("sovo.parceladdr"));
		sovo.setParcelusage(row.getString("sovo.parcelusage"));
		sovo.setRegcode(row.getString("sovo.regcode"));
		sovo.setRightstate(row.getString("sovo.rightstate"));*/
		Page<Map<String, Object>> attachPage = null;
		Map<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("PAGENO", pageNumber);
		paramMap.put("PAGESIZE", maxPageItems);
		paramMap.put("DXTXOBJECT", sovo);
		//paramMap.put("DXTXARRAY", selflag);
		try {
			attachPage = attachFacade.getAllAttachsByParam(paramMap);
		} catch (Exception e) {
			logger.error("getAllAttachInfosBySearch --:"+e.getMessage(), e);		
		}
		return pageResultToJson(attachPage);
	}
	/**
	 * 根据查询条件得到所有预告信息
	 * getAllPreadviceInfosBySearch <br/>
	 * @author Sam
	 * @Date  2014-04-08
	 * @return
	 * @since JDK 1.6
	 */
	public String getAllPreadviceInfosBySearch(Row row){
		String pageNumber = row.getString("pageNumber");
		String maxPageItems = row.getString("maxPageItems");
		SearchOptionsVO sovo = (SearchOptionsVO)WebUtil.packageEntity("SearchOptionsVO", row, "sovo");
		/*sovo.setHoldername(row.getString("sovo.holdername"));
		sovo.setIdno(row.getString("sovo.idno"));
		sovo.setCerno(row.getString("sovo.cerno"));
		sovo.setParcelcode(row.getString("sovo.parcelcode"));
		sovo.setParceladdr(row.getString("sovo.parceladdr"));
		sovo.setParcelusage(row.getString("sovo.parcelusage"));
		sovo.setRegcode(row.getString("sovo.regcode"));
		sovo.setRightstate(row.getString("sovo.rightstate"));*/
		Page<Map<String, Object>> preadvicePage = null;
		Map<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("PAGENO", pageNumber);
		paramMap.put("PAGESIZE", maxPageItems);
		paramMap.put("DXTXOBJECT", sovo);
		//paramMap.put("DXTXARRAY", selflag);
		try {
			preadvicePage = preadviceFacade.getAllPreadvicesByParam(paramMap);
		} catch (Exception e) {
			logger.error("getAllPreadviceInfosBySearch --:"+e.getMessage(), e);		
		}
		return pageResultToJson(preadvicePage);
	}
	/**
	 * 根据查询条件得到所有地役权信息
	 * getAllEasementInfosBySearch <br/>
	 * @author Sam
	 * @Date  2014-04-08
	 * @return
	 * @since JDK 1.6
	 */
	public String getAllEasementInfosBySearch(Row row){
		String pageNumber = row.getString("pageNumber");
		String maxPageItems = row.getString("maxPageItems");
		SearchOptionsVO sovo = (SearchOptionsVO)WebUtil.packageEntity("SearchOptionsVO", row, "sovo");
		/*sovo.setHoldername(row.getString("sovo.holdername"));
		sovo.setIdno(row.getString("sovo.idno"));
		sovo.setCerno(row.getString("sovo.cerno"));
		sovo.setParcelcode(row.getString("sovo.parcelcode"));
		sovo.setParceladdr(row.getString("sovo.parceladdr"));
		sovo.setParcelusage(row.getString("sovo.parcelusage"));
		sovo.setRegcode(row.getString("sovo.regcode"));
		sovo.setRightstate(row.getString("sovo.rightstate"));*/
		Page<Map<String, Object>> easementPage = null;
		Map<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("PAGENO", pageNumber);
		paramMap.put("PAGESIZE", maxPageItems);
		paramMap.put("DXTXOBJECT", sovo);
		//paramMap.put("DXTXARRAY", selflag);
		try {
			easementPage = easementFacade.getAllEasementsByParam(paramMap);
		} catch (Exception e) {
			logger.error("getAllEasementInfosBySearch --:"+e.getMessage(), e);		
		}
		return pageResultToJson(easementPage);
	}
	/**
	 * 根据查询条件得到所有异议信息
	 * getAllDissentInfosBySearch <br/>
	 * @author Sam
	 * @Date  2014-04-08
	 * @return
	 * @since JDK 1.6
	 */
	public String getAllDissentInfosBySearch(Row row){
		String pageNumber = row.getString("pageNumber");
		String maxPageItems = row.getString("maxPageItems");
		SearchOptionsVO sovo = (SearchOptionsVO)WebUtil.packageEntity("SearchOptionsVO", row, "sovo");
		/*sovo.setHoldername(row.getString("sovo.holdername"));
		sovo.setIdno(row.getString("sovo.idno"));
		sovo.setCerno(row.getString("sovo.cerno"));
		sovo.setParcelcode(row.getString("sovo.parcelcode"));
		sovo.setParceladdr(row.getString("sovo.parceladdr"));
		sovo.setParcelusage(row.getString("sovo.parcelusage"));
		sovo.setRegcode(row.getString("sovo.regcode"));
		sovo.setRightstate(row.getString("sovo.rightstate"));*/
		Page<Map<String, Object>> dissentPage = null;
		Map<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("PAGENO", pageNumber);
		paramMap.put("PAGESIZE", maxPageItems);
		paramMap.put("DXTXOBJECT", sovo);
		//paramMap.put("DXTXARRAY", selflag);
		try {
			dissentPage = dissentFacade.getAllDissentsByParam(paramMap);
		} catch (Exception e) {
			logger.error("getAllDissentInfosBySearch --:"+e.getMessage(), e);		
		}
		return pageResultToJson(dissentPage);
	}
	/**
	 * 根据查询条件得到所有产权信息
	 * getAllPropertyRightInfosBySearch <br/>
	 * @author Sam
	 * @Date  2014-04-08
	 * @return
	 * @since JDK 1.6
	 */
	public String getAllPropertyRightInfosBySearch(Row row){
		String pageNumber = row.getString("pageNumber");
		String maxPageItems = row.getString("maxPageItems");
		SearchOptionsVO sovo = (SearchOptionsVO)WebUtil.packageEntity("SearchOptionsVO", row, "sovo");
		/*sovo.setHoldername(row.getString("sovo.holdername"));
		sovo.setIdno(row.getString("sovo.idno"));
		sovo.setCerno(row.getString("sovo.cerno"));
		sovo.setParcelcode(row.getString("sovo.parcelcode"));
		sovo.setParceladdr(row.getString("sovo.parceladdr"));
		sovo.setParcelusage(row.getString("sovo.parcelusage"));
		sovo.setRegcode(row.getString("sovo.regcode"));
		sovo.setRightstate(row.getString("sovo.rightstate"));*/
		Page<Map<String, Object>> propRightPage = null;
		Map<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("PAGENO", pageNumber);
		paramMap.put("PAGESIZE", maxPageItems);
		paramMap.put("DXTXOBJECT", sovo);
		//paramMap.put("DXTXARRAY", selflag);
		try {
			propRightPage = propertyRightFacade.getAllPropRightsByParam(paramMap);
		} catch (Exception e) {
			logger.error("getAllPropertyRightInfosBySearch --:"+e.getMessage(), e);		
		}
		return pageResultToJson(propRightPage);
	}
	/**
	 * 根据查询条件得到所有个产信息
	 * getAllPrivateRealInfosBySearch <br/>
	 * @author Sam
	 * @Date  2014-04-08
	 * @return
	 * @since JDK 1.6
	 */
	public String getAllPrivateRealInfosBySearch(Row row){
		String pageNumber = row.getString("pageNumber");
		String maxPageItems = row.getString("maxPageItems");
		SearchOptionsVO sovo = (SearchOptionsVO)WebUtil.packageEntity("SearchOptionsVO", row, "sovo");
		/*sovo.setHoldername(row.getString("sovo.holdername"));
		sovo.setIdno(row.getString("sovo.idno"));
		sovo.setCerno(row.getString("sovo.cerno"));
		sovo.setParcelcode(row.getString("sovo.parcelcode"));
		sovo.setParceladdr(row.getString("sovo.parceladdr"));
		sovo.setParcelusage(row.getString("sovo.parcelusage"));
		sovo.setRegcode(row.getString("sovo.regcode"));
		sovo.setRightstate(row.getString("sovo.rightstate"));*/
		Page<Map<String, Object>> privaterealPage = null;
		Map<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("PAGENO", pageNumber);
		paramMap.put("PAGESIZE", maxPageItems);
		paramMap.put("DXTXOBJECT", sovo);
		//paramMap.put("DXTXARRAY", selflag);
		try {
			privaterealPage = privaterealFacade.getAllPrivateRealsByParam(paramMap);
		} catch (Exception e) {
			logger.error("getAllPrivateRealInfosBySearch --:"+e.getMessage(), e);		
		}
		return pageResultToJson(privaterealPage);
	}
	/**
	 * 根据查询条件得到所有个产信息
	 * getAllPrivateRealInfosBySearch <br/>
	 * @author Sam
	 * @Date  2014-04-08
	 * @return
	 * @since JDK 1.6
	 */
	public String getAllPresaleInfosBySearch(Row row){
		String pageNumber = row.getString("pageNumber");
		String maxPageItems = row.getString("maxPageItems");
		SearchOptionsVO sovo = (SearchOptionsVO)WebUtil.packageEntity("SearchOptionsVO", row, "sovo");
		/*sovo.setHoldername(row.getString("sovo.holdername"));
		sovo.setIdno(row.getString("sovo.idno"));
		sovo.setCerno(row.getString("sovo.cerno"));
		sovo.setParcelcode(row.getString("sovo.parcelcode"));
		sovo.setParceladdr(row.getString("sovo.parceladdr"));
		sovo.setParcelusage(row.getString("sovo.parcelusage"));
		sovo.setRegcode(row.getString("sovo.regcode"));
		sovo.setRightstate(row.getString("sovo.rightstate"));*/
		Page<Map<String, Object>> privaterealPage = null;
		Map<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("PAGENO", pageNumber);
		paramMap.put("PAGESIZE", maxPageItems);
		paramMap.put("DXTXOBJECT", sovo);
		//paramMap.put("DXTXARRAY", selflag);
		try {
			privaterealPage = presaleFacade.getAllPresalesByParam(paramMap);
		} catch (Exception e) {
			logger.error("getAllPrivateRealInfosBySearch --:"+e.getMessage(), e);		
		}
		return pageResultToJson(privaterealPage);
	}
	///*******************************Select Action Region End**********************************//
}

