package com.szhome.cq.domain.service;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.szhome.commons.log.LogUtil;
import com.szhome.cq.utils.Constants;
import com.szhome.cq.utils.JsonUtil;
import com.szhome.security.ext.UserInfo;

/**
 * 日志AOP - 监控所有服务
 * @author Mignet
 * 
 */
@Aspect
public class LogMonitor {
	
	@Autowired
	private ServiceLog logService;//日志记录Service
	
	/**
	 * 监控所有业务逻辑方法切入点
	 */
    @Pointcut("execution(* com.szhome.cq.business.*Facade.*(..))")
    public void changeServiceCall() { }
    
    
	 /**
     * 操作日志(后置通知)
     * @param joinPoint
     * @param rtv
     * @throws Throwable
     */
	@AfterReturning(value="changeServiceCall()", argNames="rtv", returning="rtv")
    public void changeServiceCallCalls(JoinPoint joinPoint, Object rtv) throws Throwable{
		
		RequestAttributes ra = RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = ((ServletRequestAttributes)ra).getRequest(); 
		//获取登录操作员id
		UserInfo sessionUser = (UserInfo)request.getSession().getAttribute("userInfo");
		if(sessionUser == null){//没有操作员登录
			return;
		}
		
		//判断参数
		if(joinPoint.getArgs() == null){//没有参数
			return;
		}
		//日志本身的方法过滤掉
		if(joinPoint.getTarget().toString().contains("LogFacade")){
			return;
		}
		//获取服务名
		String serviceName = joinPoint.getTarget().getClass().getName();
		//获取方法名
		String methodName = joinPoint.getSignature().getName();
		//##将get开头的方法过滤掉，查询类的先不记录
		String actionName = "调用";
		if(methodName.startsWith("get")){
			actionName = "查询";
			//return;
		}
		if(methodName.startsWith("add")||methodName.startsWith("insert")){
			actionName = "新增";
		}
		if(methodName.startsWith("update")){
			actionName = "更新";
		}
		if(methodName.startsWith("save")){
			actionName = "保存";
		}
		if(methodName.startsWith("del")){
			actionName = "删除";
		}
		
		//获取参数值
		String arguments = Arrays.toString(joinPoint.getArgs());
		if(arguments!=null&&arguments.length()>100){
			arguments = arguments.substring(0, 100)+"...";
		}
		String returnValue = JsonUtil.object2json(rtv);
		if(returnValue!=null&&returnValue.length()>100){
			returnValue = returnValue.substring(0, 100)+"...";
		}
		String returnType = rtv==null?"":rtv.getClass().toString();
		returnType = returnType.substring(returnType.lastIndexOf(".")+1);
		String content =  sessionUser.getUserName()+"执行:"+methodName+":"+arguments+" 返回类型：["+returnType+"] 返回值：["+returnValue+"]";
		content = content.replaceAll("\\n", "");
		if(content!=null&&content.length()>200){
			content = content.substring(0, 200)+"...";
		}
		
		String ip = request.getHeader("X-Forwarded-For");
		if (ip == null || ip.length() == 0) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0) {
			ip = request.getRemoteAddr();
		}
		//写日志
		logService.addOperationlog(Constants.SERVICECODE_LOG_SERVICE,sessionUser.getUserAccount(),serviceName.substring(serviceName.lastIndexOf(".")+1), methodName, actionName,
				"01",content, ip);//设置操作员id
	}
	
	@AfterThrowing(throwing="e",pointcut="changeServiceCall()")  
    public void doRecoveryActions(JoinPoint joinPoint, Throwable e){  
	    Signature signature = joinPoint.getSignature();
	    String methodName = signature.getName();
	    String stuff = signature.toString();
	    String arguments = Arrays.toString(joinPoint.getArgs());
	    LogUtil.info("产生异常的方法：  " + methodName
	        + " 参数值("
	        + arguments + ")\n异常详细信息: " + stuff + "\nthe exception is: "
	        + e.getMessage(), e);
	    //对于等级高的服务及方法，此处可添加短信(邮件)通知管理员功能
    }  
	
}

