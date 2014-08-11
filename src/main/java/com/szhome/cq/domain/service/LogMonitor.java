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
 * ��־AOP - ������з���
 * @author Mignet
 * 
 */
@Aspect
public class LogMonitor {
	
	@Autowired
	private ServiceLog logService;//��־��¼Service
	
	/**
	 * �������ҵ���߼����������
	 */
    @Pointcut("execution(* com.szhome.cq.business.*Facade.*(..))")
    public void changeServiceCall() { }
    
    
	 /**
     * ������־(����֪ͨ)
     * @param joinPoint
     * @param rtv
     * @throws Throwable
     */
	@AfterReturning(value="changeServiceCall()", argNames="rtv", returning="rtv")
    public void changeServiceCallCalls(JoinPoint joinPoint, Object rtv) throws Throwable{
		
		RequestAttributes ra = RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = ((ServletRequestAttributes)ra).getRequest(); 
		//��ȡ��¼����Աid
		UserInfo sessionUser = (UserInfo)request.getSession().getAttribute("userInfo");
		if(sessionUser == null){//û�в���Ա��¼
			return;
		}
		
		//�жϲ���
		if(joinPoint.getArgs() == null){//û�в���
			return;
		}
		//��־����ķ������˵�
		if(joinPoint.getTarget().toString().contains("LogFacade")){
			return;
		}
		//��ȡ������
		String serviceName = joinPoint.getTarget().getClass().getName();
		//��ȡ������
		String methodName = joinPoint.getSignature().getName();
		//##��get��ͷ�ķ������˵�����ѯ����Ȳ���¼
		String actionName = "����";
		if(methodName.startsWith("get")){
			actionName = "��ѯ";
			//return;
		}
		if(methodName.startsWith("add")||methodName.startsWith("insert")){
			actionName = "����";
		}
		if(methodName.startsWith("update")){
			actionName = "����";
		}
		if(methodName.startsWith("save")){
			actionName = "����";
		}
		if(methodName.startsWith("del")){
			actionName = "ɾ��";
		}
		
		//��ȡ����ֵ
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
		String content =  sessionUser.getUserName()+"ִ��:"+methodName+":"+arguments+" �������ͣ�["+returnType+"] ����ֵ��["+returnValue+"]";
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
		//д��־
		logService.addOperationlog(Constants.SERVICECODE_LOG_SERVICE,sessionUser.getUserAccount(),serviceName.substring(serviceName.lastIndexOf(".")+1), methodName, actionName,
				"01",content, ip);//���ò���Աid
	}
	
	@AfterThrowing(throwing="e",pointcut="changeServiceCall()")  
    public void doRecoveryActions(JoinPoint joinPoint, Throwable e){  
	    Signature signature = joinPoint.getSignature();
	    String methodName = signature.getName();
	    String stuff = signature.toString();
	    String arguments = Arrays.toString(joinPoint.getArgs());
	    LogUtil.info("�����쳣�ķ�����  " + methodName
	        + " ����ֵ("
	        + arguments + ")\n�쳣��ϸ��Ϣ: " + stuff + "\nthe exception is: "
	        + e.getMessage(), e);
	    //���ڵȼ��ߵķ��񼰷������˴�����Ӷ���(�ʼ�)֪ͨ����Ա����
    }  
	
}

