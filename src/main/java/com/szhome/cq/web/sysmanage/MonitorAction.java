package com.szhome.cq.web.sysmanage;
import java.util.List;

import org.springframework.web.servlet.ModelAndView;

import com.plan.commons.Row;
import com.plan.web.JsonResult;
import com.springjdbc.annotation.Page;
import com.szhome.cq.business.FacadeFactory;
import com.szhome.cq.business.IMonitorFacade;
import com.szhome.cq.delegate.BaseDelegate;
import com.szhome.cq.domain.model.Method;
import com.szhome.cq.domain.model.Service;
import com.szhome.cq.utils.JsonUtil;

/**
 * 
 * @author user
 *
 */
public class MonitorAction extends BaseDelegate{

	IMonitorFacade urmFacade = FacadeFactory.getMonitorFacade();
	
	public ModelAndView home(Row row){
		ModelAndView mv = new ModelAndView();
		List<Service> serviceList = urmFacade.getAllAPIs();
		mv.addObject(serviceList);
		mv.setViewName("sysmanage/monitor-show");
		return mv;
	}
	
	public ModelAndView edit(Row row){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("sysmanage/api-list");
		return mv;
	}
	
	public String getAPIList(Row row){
		String pageNumber = row.getString("pageNumber");
		String maxPageItems = row.getString("maxPageItems");
		//当前页  
        int intPage = Integer.parseInt((pageNumber == null || pageNumber == "0") ? "1":pageNumber);  
        //每页显示条数  
        int number = Integer.parseInt((maxPageItems == null || maxPageItems == "0") ? "10":maxPageItems);  
		Page<Service> userroleList = urmFacade.getAllAPIs(intPage, number);
		return this.pageResultToJson(userroleList);
	}
	
	public String getAllMethodsBySID(Row row){
		String sid = row.getString("sid");
		List<Method> userroleList = urmFacade.getMethodsBySID(sid);
		return JsonUtil.object2json(userroleList);
	}
	
	public String getMethodsBySID(Row row){
		String pageNumber = row.getString("pageNumber");
		String maxPageItems = row.getString("maxPageItems");
		//当前页  
		int intPage = Integer.parseInt((pageNumber == null || pageNumber == "0") ? "1":pageNumber);  
		//每页显示条数  
		int number = Integer.parseInt((maxPageItems == null || maxPageItems == "0") ? "10":maxPageItems);  
		String sid = row.getString("sid");
		Page<Method> userroleList = urmFacade.getMethodsBySID(sid,intPage, number);
		return this.pageResultToJson(userroleList);
	}
	
	public String saveAPIs(Row row){
		String datas = row.getString("datas");
		try{
			urmFacade.saveAPIs(datas);
		} catch (Exception e) {
			return this.setActionJsonObject(new JsonResult(false,"更新失败！",e.getMessage()).toJsonString());
		}
		return this.setActionJsonObject(new JsonResult(true, "更新成功！").toJsonString());
	}
	
	public String saveMethods(Row row){
		String datas = row.getString("datas");
		try{
			urmFacade.saveMethods(datas);
		} catch (Exception e) {
			return this.setActionJsonObject(new JsonResult(false,"更新失败！",e.getMessage()).toJsonString());
		}
		return this.setActionJsonObject(new JsonResult(true, "更新成功！").toJsonString());
	}

}

