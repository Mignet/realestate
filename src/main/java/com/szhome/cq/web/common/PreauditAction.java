package com.szhome.cq.web.common;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import com.plan.commons.Row;
import com.plan.exceptions.GeneralException;
import com.plan.web.JsonResult;
import com.szhome.commons.log.LogUtil;
import com.szhome.cq.business.FacadeFactory;
import com.szhome.cq.business.IAuditFacade;
import com.szhome.cq.business.ResultMsg;
import com.szhome.cq.business.vo.AuditVo;
import com.szhome.cq.delegate.BaseDelegate;
import com.szhome.cq.domain.model.AcceptRule;
import com.szhome.cq.utils.JsonUtil;

public class PreauditAction extends BaseDelegate{

//	private String parcelcode;
//	private String houselocation;
//	private String proname;
//	private String housecode;
//	private String serialName;
//	private String procName;
//	private String procdefId;
//	private int procId;
//	private AcceptRule acceptrule;
//	private String rulename;
//	private String ruletype;
//	private String ruleid;
//	private String bustype;
//	private String location;
//	private String houseid;
//	private String selectedrow;//ѡ���б���������
	
	IAuditFacade af = FacadeFactory.getAuditFacade();
	public String doApply(Row row) throws GeneralException{
		
		String lis = row.getString("selectedrow");
		String procName = row.getString("procName");
		String serialName = row.getString("serialName");
		String procdefId = row.getString("procdefId");
		JSONArray jsonArray = JSONArray.fromObject(lis);
		System.out.println("jsonarray:  " + jsonArray);
		List<Map> list = JSONArray.toList(jsonArray, Map.class);
		//-------ajax---------
		Map m = af.doApply(this.getOperatorInfo(),procName,serialName,procdefId,list);
//		JSONArray a = new JSONArray();
//		a.put(m);		
		return JsonUtil.object2json(m);//this.setActionJsonObject(a.toString());
	}
	
	
	/**
	 * 
	 * getHouseInfo:(��ѯ�ڵر����ݱ���¥������ű���Ϣ). <br/>
	 * @author xuzz
	 * @return
	 * @since JDK 1.6
	 */

	public String getHouseInfo(Row row){
		Map m=new HashMap();
//		System.out.println(parcelcode+houselocation+proname+housecode);
		
		String parcelcode = row.getString("parcelcode");
		String houselocation = row.getString("houselocation");
		String proname = row.getString("proname");
		String housecode = row.getString("housecode");
		if(parcelcode==null)
		{
			parcelcode="";
			houselocation="";
			proname="";
			housecode="";
		}
		m.put("parcelcode", parcelcode);
		m.put("houselocation", houselocation);
		m.put("proname", proname);
		m.put("housecode", housecode);
		List<AuditVo> list = af.getAuditInfo(m);
		return JsonUtil.object2json(list);
	}
	/**
	 * 
	 * getLandInfo:(��ѯ�����ڵر���Ϣ). <br/>
	 * @author xuzz
	 * @return
	 * @since JDK 1.6
	 */
	public String getLandInfo(Row row)
	{
		//-------ajax---------
		Map m=new HashMap();
		String parcelcode = row.getString("parcelcode");
		String houselocation = row.getString("houselocation");
		String location = row.getString("location");
		String proname = row.getString("proname");
		String housecode = row.getString("housecode");
		//		System.out.println(parcelcode+houselocation+proname+housecode);
		if(parcelcode==null)
		{
			parcelcode="";
			houselocation="";
			proname="";
			housecode="";
		}
		m.put("parcelcode", parcelcode);
		m.put("location", location);
		List<AuditVo> list =  af.getLandInfo(m);
		return JsonUtil.object2json(list);
	}
	
	/**
	 * 
	 * getBuildInfo:(��ѯ���н�������Ϣ). <br/>
	 * @author xuzz
	 * @return
	 * @since JDK 1.6
	 */
	public String getBuildInfo(Row row)
	{
		//-------ajax---------
		Map m=new HashMap();
//		System.out.println(parcelcode+houselocation+proname+housecode);
		String parcelcode = row.getString("parcelcode");
		String houselocation = row.getString("houselocation");
		String proname = row.getString("proname");
		String housecode = row.getString("housecode");
		if(parcelcode==null)
		{
			parcelcode="";
			houselocation="";
			proname="";
			housecode="";
		}
		m.put("parcelcode", parcelcode);
		m.put("location", houselocation);
		m.put("proname", proname);
		m.put("housecode", housecode);
		List<AuditVo> list =  af.getBuildInfo(m);
		return JsonUtil.object2json(list);
	}
	/**
	 * 
	 * checkData:(���÷����Ƿ���԰����ݳ�ʼ�Ǽ�ҵ��). <br/>
	 * @author xuzz
	 * @return
	 * @since JDK 1.6
	 */
	public String checkData(Row row)  {
		//-------ajax---------
		String housecode = row.getString("housecode");
		List<Map<String, Object>> list = af.checkData(housecode);
		if(list!=null&&list.size()>0){
			return this.setActionJsonObject("1");
		}
		return this.setActionJsonObject("0");
	}
	/**
	 * 
	 * insertRegInfo:(ִ������ť������Ǽǵ�Ԫ��������Ϣ������Ȩ�Ǽ���Ϣ. <br/>
	 * @author xuzz
	 * @return
	 * @since JDK 1.6
	 */
	public String insertRegInfo(Row row)  {
		String housecode = row.getString("housecode");
		int procId = row.getInt("procId");
		Map map=getHouse(housecode,procId);
		ResultMsg msg=new ResultMsg();
		if(map.size()>=0){
			try 
			{
				//msg= af.insertRegInfo(map);
				//insertRegInfo
				
			} 
			catch (Exception e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}	
		}
		return msg.toString();	
	}
	/**
	 * getHouse:(���ݷ��ݱ����ѯ���������Ϣ). <br/>
	 *
	 * @author xuzz
	 * @param fwbh
	 * @param procId
	 * @return
	 * @since JDK 1.6
	 */
	public Map getHouse(String housecode,int procId){
		Map m=new HashMap();
		try {
			m=af.getHouse(housecode);
			//row = getPlanSupportDao().findRow("select *  from BDC_DJB_ZD z  left   join   BDC_DJB_LY l  on  z.zdh=L.ZDH left   join   BDC_DJB_FW f  on   l.jzwbm=f.jzwbm where f.fwbh =? ",new Object[]{fwbh});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		m.put("lcslbid", procId);
	    return m;
	}
	/**
	 * 
	 * saveDelete:(ɾ�����������������). <br/>
	 * @author xuzz
	 * @return
	 * @since JDK 1.6
	 */
	public String saveDeleteRule(Row row)
	{
		String bustype = row.getString("bustype");
		String ruleid = row.getString("ruleid");
		String rulename = row.getString("rulename");
		String ruletype = row.getString("ruletype");
		String housecode = row.getString("housecode");
		AcceptRule acc=new AcceptRule();
		acc.setAcc_enter_date(new Date());
		//acceptrule.setAcc_rule_id(bustype);
		acc.setAcc_recorder("");
		acc.setBus_type_id(bustype);
		acc.setRule(ruleid);
		acc.setRule_name(rulename);
		acc.setRule_type(ruletype);
		acc.setRule_content("");
		//-------ajax---------
		try {
			af.saveDeleteRule(acc);	
		} catch(Exception e) {
			LogUtil.error(e.getMessage(), e);
			return this.setActionJsonObject(new JsonResult(false, e.getMessage(), null).toJsonString());
		}
		return this.setActionJsonObject(new JsonResult(true, "�����ɹ���", null).toJsonString());
	}
	/**
	 * 
	 * selectRule:(����ҵ�����Ͳ�ѯ�������). <br/>
	 * @author xuzz
	 * @return
	 * @since JDK 1.6
	 */
	public String selectRule(Row row)
	{
		//-------ajax---------
		String bustype = row.getString("bustype");
		Map<String,Object> ma = new HashMap<String,Object>();
		List<Map<String, Object>> list= af.selectRuleByBusType(bustype);
		String str="";
		try {
			//��¼�˲�ѯ����
			ma.put("total", list.size());
			//��¼�˵�ǰҳ������
			ma.put("rows",list);
			str = JsonUtil.object2json(ma);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this.setActionJsonObject(str);
	}
}

