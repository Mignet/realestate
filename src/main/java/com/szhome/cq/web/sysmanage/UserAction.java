package com.szhome.cq.web.sysmanage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.plan.commons.Row;
import com.szhome.cq.business.FacadeFactory;
import com.szhome.cq.business.IUserManageFacade;
import com.szhome.cq.delegate.BaseDelegate;
import com.szhome.cq.domain.model.User;
import com.szhome.cq.utils.JsonUtil;
import com.szhome.cq.web.JsonResult;

/**
 * 
 * @author user
 *
 */
public class UserAction extends BaseDelegate{

	IUserManageFacade urmFacade = FacadeFactory.getUserManageFacade();
	
	public String getUserList(){
		List<User> userroleList = urmFacade.queryAllUsers();
		return JsonUtil.list2json(userroleList);
	}
	
	public String saveWeights(Row row){
		String datas = row.getString("datas");
		try{
			urmFacade.saveUsers(datas);
		} catch (Exception e) {
			return JsonUtil.object2json(new JsonResult(false,"更新失败！",e.getMessage()));
		}
		return JsonUtil.object2json(new JsonResult(true, "更新成功！"));
	}

}

