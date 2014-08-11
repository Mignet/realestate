package com.szhome.cq.web.sysmanage;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import com.plan.commons.Row;
import com.plan.commons.RowImpl;
import com.plan.exceptions.GeneralException;
import com.plan.util.json.tree.JsonTreeUtils;
import com.szhome.commons.log.LogUtil;
import com.szhome.cq.delegate.BaseDelegate;
import com.szhome.cq.utils.JsonUtil;

/**
 * ��֯���������ṩ�ӿ�
 * ���ݿͻ��˲����ж����������жϷ�����������Ϊjson��ʽ����jsonp��ʽ
 * ����json��ʽ���ò��ܿ���һ��ͬ������ʱ����json���ݿ�������ʱ����jsonp��ʽ����
 * 
 * @author chenli
 * @since 2013-9-25
 *
 */
public class OrgManageAction extends BaseDelegate {
	
	/**
	 * ��ȡ����������
	 * @param row
	 * @return
	 * @throws Exception
	 */
	public String getDeptTreeJson(Row row) throws Exception {
		
		String sql = null, bureauId = "1";
		
		String callback = row.getString("callback");
        
        String bureauCode = row.getString("bureauCode");
        
        if (bureauCode != null) {
        	sql = "select group_id from sec_bureaus where valid='1' and bureau_code = ?";
        	Row bureau = getPlanSupportDao().findRow(sql, new String[]{bureauCode.toUpperCase()});
        	if (bureau != null) {
        		bureauId = bureau.getString("group_id");
        	}
        }
        
        sql = "select dept_id id, parent_dept_id parent_id, dept_name text from platt_view_dept_dept_rel where type = '����' and rtype = '����' order by turn";
        
        String jsonStr = JsonTreeUtils.createTrees(bureauId, sql, getPlanSupportDao());
        
        if (callback == null) {
        	return "[" + jsonStr + "]";
        } else {
        	return callback + "([" + jsonStr + "])";
        }
		
	}
	
	/**
	 * ��ȡ�����û�����
	 * @param row
	 * @return
	 * @throws Exception
	 */
	public String getDeptUsersJson(Row row) throws Exception {
		String callback = row.getString("callback");
		
		String deptId = row.getString("deptId");
		String searchStr = row.getString("searchStr");
		
		List<Row> rows = new ArrayList<Row>();
		int count = 0;
		
		searchStr = searchStr == null ? "" : searchStr;
		searchStr = URLDecoder.decode(searchStr, "GBK");
		
		row.put("dept_id", row.getString("deptid"));
		
		String sql = "select user_id userId, user_name userName, dept_id groupId, dept_name groupName, duty dutyName from platt_view_dept_user_rel where dept_id = ? and (user_name like '%" + searchStr + "%' or dept_name like '%" + searchStr + "%') order by dept_id, rturn";
		Object[] objs;
		try {
			objs = getPlanSupportDao().findRowsForPaging(row, sql);
			
			rows = (List<Row>)objs[0];
	        count = (Integer)objs[1];
		} catch (Exception e) {
			LogUtil.error("OrgManagerAction.getDeptUsersJson:"+e.getMessage());
			throw new GeneralException("OrgManagerAction.getDeptUsersJson:"+e.getMessage());
		}
		
		
        
        if(callback == null) {
        	return "{total:" + count + ",rows:" + JsonUtil.list2json(rows) + "}";
        }else {
        	return callback + "({total:" + count + ",rows:" + JsonUtil.list2json(rows) + "})";
        }
		
	}
	
	/**
	 * ���ݹؼ���ģ�������û�
	 * ����ƥ���û�������������ְ��ȡ����
	 * @param row
	 * @return
	 * @throws Exception
	 */
	public String searchUsersJson(Row row) throws Exception {
		String callback = row.getString("callback");
        
        String deptId = row.getString("deptId");
        String searchStr = row.getString("searchStr");
        String pageNumber = row.getString("pageNumber");
        String maxPageItems = row.getString("maxPageItems");
        searchStr = searchStr == null ? "" : searchStr;
        
        //searchStr = new String(searchStr.getBytes("iso-8859-1"), "GBK");
        searchStr = URLDecoder.decode(searchStr, "GBK");
        
        Row param = new RowImpl();
        param.put("pageNumber", Integer.parseInt(pageNumber));
        param.put("maxPageItems", Integer.parseInt(maxPageItems));
        param.put("dtype", "����");
        param.put("dept_id", deptId);
        
        String sql = "select user_id userId, user_name userName, dept_id groupId, dept_name groupName, duty dutyName from platt_view_dept_user_rel where dtype = ? and dept_id = ? and (user_name like '%" + searchStr + "%' or dept_name like '%" + searchStr + "%' or duty like '%" + searchStr + "%') order by dept_id,rturn";
        
        Object[] objs = getPlanSupportDao().findRowsForPaging(param, sql);
        
        List<Row> rows = (List<Row>)objs[0];
        int count = (Integer)objs[1];
        
        if(callback == null) {
        	return "{total:" + count + ",rows:" + JsonUtil.list2json(rows) + "}";
        }else {
        	return callback + "({total:" + count + ",rows:" + JsonUtil.list2json(rows) + "})";
        }
	}

}

