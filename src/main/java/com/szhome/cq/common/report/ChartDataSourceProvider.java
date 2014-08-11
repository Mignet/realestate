/**
 * Project Name:dxtx_re
 * File Name:ChartDataSourceProvider.java
 * Package Name:com.szhome.cq.report
 * Date:2014-4-30下午3:00:53
 * Copyright (c) 2014, DXTX All Rights Reserved.
 *
*/

package com.szhome.cq.common.report;

import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRAbstractBeanDataSourceProvider;

import com.szhome.cq.business.FacadeFactory;

/**
 * ClassName:ChartDataSourceProvider <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2014-4-30 下午3:00:53 <br/>
 * @author   Joyon
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class ChartDataSourceProvider extends JRAbstractBeanDataSourceProvider{

    public ChartDataSourceProvider(Class arg0) {

       super(arg0);

       // TODO Auto-generated constructor stub

    }

 

    public ChartDataSourceProvider() {

       super(ChartDataBean.class);

       // TODO Auto-generated constructor stub

    }

    public JRDataSource create(JasperReport arg0) throws JRException {

       // TODO Auto-generated method stub

       ChartDataSource ds=new ChartDataSource(getQueryResult(null));

       return ds;

    }

 

    public void dispose(JRDataSource arg0) throws JRException {

       // TODO Auto-generated method stub

 

    }

 
    public static Vector<ChartDataBean> getQueryResult(Map paraMap){

        Vector<ChartDataBean> vector = new Vector<ChartDataBean>();
        ChartDataBean result = new ChartDataBean();
       // Map strMap = (Map) request.getSession().getAttribute("strMap");
        List<Map<String,Object>> maplist = FacadeFactory.getQualityinspectionFacade().getUrgeStatistics(paraMap);
        for(Map<String,Object> tmpMap : maplist){
        	String bus_type_name = "";
        	if(tmpMap.get("BUS_TYPE_NAME")!=null){
        		bus_type_name = tmpMap.get("BUS_TYPE_NAME").toString();
        	}
        	
        	Float acc_count= null;;
        	if(tmpMap.get("ACC_COUNT")!=null){
        		acc_count =  Float.valueOf(tmpMap.get("ACC_COUNT").toString());
        	}
        	
        	Float app_count= null;;
        	if(tmpMap.get("APP_COUNT")!=null){
        		app_count = Float.valueOf(tmpMap.get("ARCH_COUNT").toString());
        	}
        	Float arch_count= null;;
        	if(tmpMap.get("ARCH_COUNT")!=null){
        		arch_count = Float.valueOf(tmpMap.get("ARCH_COUNT").toString());
        	}
        	Float re_count= null;;
        	if(tmpMap.get("RE_COUNT")!=null){
        		re_count = Float.valueOf(tmpMap.get("RE_COUNT").toString());
        	}
        	Float to_count= null;;
        	if(tmpMap.get("TO_COUNT")!=null){
        		to_count = Float.valueOf(tmpMap.get("TO_COUNT").toString());
        	}
        	//Float acc_count = Float.valueOf(tmpMap.get("ACC_COUNT").toString());
        	//Float app_count = Float.valueOf(tmpMap.get("APP_COUNT").toString());
        	//Float arch_count = Float.valueOf(tmpMap.get("ARCH_COUNT").toString());
        	//Float re_count = Float.valueOf(tmpMap.get("RE_COUNT").toString());
        	//Float to_count = Float.valueOf(tmpMap.get("TO_COUNT").toString());
        	
        	result = new ChartDataBean();
        	
        	result.setCategory(bus_type_name);

            result.setSerieas("受理数量");

            result.setValue(acc_count);

            vector.addElement(result);
            
            
            result = new ChartDataBean();
            result.setCategory(bus_type_name);

            result.setSerieas("核准数量");

            result.setValue(app_count);

            vector.addElement(result);

            
            
            
            result = new ChartDataBean();

            result.setCategory(bus_type_name);

            result.setSerieas("归档数量");

            result.setValue(arch_count);

            vector.addElement(result);

            result = new ChartDataBean();

            
            
            result = new ChartDataBean();
            
            result.setCategory(bus_type_name);

            result.setSerieas("退文量");

            result.setValue(re_count);

            vector.addElement(result);

            
            
            result = new ChartDataBean();
            
            result.setCategory(bus_type_name);

            result.setSerieas("超时量");

            result.setValue(to_count);

            vector.addElement(result);

        }


        return vector;

    }


}
