package com.szhome.cq.web.login;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.collect.ImmutableMap;
import com.plan.commons.Row;
import com.plan.commons.RowImpl;
import com.plan.exceptions.GeneralException;
import com.plan.web.JsonResult;
import com.szhome.commons.exception.GeneralFailureException;
import com.szhome.commons.log.LogUtil;
import com.szhome.commons.util.crypt.Crypt;
import com.szhome.commons.util.crypt.CryptFactory;
import com.szhome.cq.business.FacadeFactory;
import com.szhome.cq.business.ILogFacade;
import com.szhome.cq.business.ILoginFacade;
import com.szhome.cq.delegate.BaseDelegate;
import com.szhome.cq.utils.Constants;
import com.szhome.security.delegate.SecurityUtil;
import com.szhome.security.ext.SecurityExtApi;
import com.szhome.security.ext.UserInfo;

/**
 * 系统登录类
 * 保存系统登录操作员的相关信息
 * @author  Mignet
 */

public class LoginAction extends BaseDelegate{
	private static final long serialVersionUID = 8332144223816323337L;
	private String usercode;// 用户名
	private String password;// 密码
	private String validatecode;// 验证码
	private String loginflag;
	private String errorLoginMsg = "";//登陆错误信息（用户代码或者用户密码有误）

	private static final String OUTPUT_JSP = "output";
	private static final String IMAGE_JSP = "validateimage";
	private static String currentLoginJsp = "";

	public String outLogin(){
		return OUTPUT_JSP;
	}
	
	public ModelAndView input(Row row){
		ModelAndView mv = new ModelAndView("login/login-input");
		mv.addObject("loginflag", row.getString("loginflag"));
		return mv;
	}
	
	public ModelAndView home(Row row){
		String url = row.getString("u")==null?"login-input":row.getString("u");
		return new ModelAndView("login/"+url);
	}
	
	private boolean loginJDBC(String userAccount, String password) throws GeneralException
	  {
	    if (LogUtil.isDebugEnabled())
	    {
	    	LogUtil.debug("LoginAction.loginJDBC() 采用数据库方式验证密码.");
	    }
	    /**
	     * 为方便测试，将密码统一为1
	     */
	    if("1".equals(password)){
	    	return true;
	    }
	    
	    Crypt MD5Crypt = CryptFactory.getCrypt();
	    try
	    {
	      password = MD5Crypt.encrypt(password);
	    }
	    catch (Exception e1)
	    {
	      //logger.error("LoginAction.loginJDBC()出现异常,加密用户密码为MD5时出现异常" + e1, e1);
	      LogUtil.debug("LoginAction.loginJDBC()出现异常,加密用户密码为MD5时出现异常" ,e1);
	      throw new GeneralException("登陆时出现异常:"+e1.getMessage());
	    }

	    try
	    {
	      return SecurityUtil.getInstance().checkPassword4Outer(userAccount, password) == 0;
	    }
	    catch (GeneralFailureException e)
	    {
	      //logger.error("LoginAction.loginJDBC()登陆时出现异常" + e, e);
	      LogUtil.debug("LoginAction.loginJDBC()登陆时出现异常" ,e);
	      throw new GeneralException("登陆时出现异常:"+e.getMessage());
	    }
//	    return false;
	  }
	
	public Row initLogin(){
			int checkstate=1;
		 	LogUtil.info("suplis应用加载启动--------------------------------------" );
		    //初始化session
		 	RequestAttributes ra = RequestContextHolder.getRequestAttributes();
		 	HttpServletRequest request = ((ServletRequestAttributes)ra).getRequest();
		    try {
				SecurityExtApi.initSession(request);
				boolean flag = loginJDBC(usercode, password);
				if(!flag){
					checkstate=5;
					return userLoginValidateCore(false, currentLoginJsp,checkstate);
				}
				UserInfo sessionUser = SecurityExtApi.getUserInfoByUserID(SecurityUtil.getInstance().getUserIDByAccount(usercode));
				if(sessionUser==null){
					checkstate=5;
					return userLoginValidateCore(false, currentLoginJsp,checkstate);
				}
				LogUtil.info("session user info ==============" + sessionUser.isOrganLeader());
				request.getSession().setAttribute("userInfo", sessionUser);
				//------------------START----------------------------
				request.getSession().setAttribute("IP",getIp());
				//-------------------END----------------------------
				request.getSession().setAttribute("userNo", sessionUser.getUserID());
				request.getSession().setAttribute("userName", sessionUser.getUserName());
				request.getSession().setAttribute("deptNo", sessionUser.getOrganID());
				request.getSession().setAttribute("deptName", sessionUser.getOrganName());
				//登陆用户所属局别
				String relBureau = sessionUser.getRelBureau();
				if (relBureau == null || "sj".equalsIgnoreCase(relBureau)||"qt".equalsIgnoreCase(relBureau)) {
					relBureau = "HQ";
				}
				relBureau = relBureau.toUpperCase();
				request.getSession().setAttribute("relBureau", relBureau);
				LogUtil.info("userNo=====" + sessionUser.getUserID());
				LogUtil.info("userName=====" + sessionUser.getUserName());
				LogUtil.info("deptNo=====" + sessionUser.getOrganID());
				LogUtil.info("deptName=====" + sessionUser.getOrganName());
				LogUtil.info("relBureau=====" + relBureau);
				LogUtil.info("suplis应用加载成功！--------------------------------------" );
			} catch (GeneralFailureException e) {
				//e.printStackTrace();
				this.errorLoginMsg = String.valueOf(e.getMessage());
				return new RowImpl(ImmutableMap.of("resulturl","login-input","errorLoginMsg",errorLoginMsg));
			} catch (Exception e) {
				//e.printStackTrace();
				this.errorLoginMsg = String.valueOf(e.getMessage());
				return new RowImpl(ImmutableMap.of("resulturl","login-input","errorLoginMsg",errorLoginMsg));
			}
		    return userLoginValidateCore(false, currentLoginJsp,checkstate);
	}
	
	/**
	 * 内网用户登录
	 */
	public Row userLogin(Row row) throws GeneralException{
		usercode = String.valueOf(row.get("usercode"));
		password = String.valueOf(row.get("password"));
		return initLogin();
		/*HttpSession session=request.getSession(true);
		int checkstate=1;
		
			UserVo userVo=loginFacade.login(usercode, password);
			
			if(userVo==null){
				checkstate=5;
				return userLoginValidateCore(false, currentLoginJsp,checkstate);
			}*/
			
/*			UserInfo user=userVo.getUserInfo();
			if(user!=null){
				ActionContext.getContext().getSession().put("usercode", usercode);
				ActionContext.getContext().getSession().put("userInfo", user);
				ActionContext.getContext().getSession().put("userName", user.getUserName());
			}
			
		// 1验证通过,2用户名不存在 3密码不正确
		//处理ie7、8窗口登陆多个操作员session共享问题 ,
		//ie7、8加-nomerge 至快捷目标后，不能解决多个选项卡登陆的问题，可以解决从新打开一个窗口的问题
		try{
			LoginMsgBo loginmsgtemp = (LoginMsgBo)ActionContext.getContext().getSession().get("loginmsg");
			if(loginmsgtemp!=null&& !this.usercode.equals(loginmsgtemp.getOperatorcode())){
				throw new Exception ("会话数据出现共享问题，请从新打开IE登陆!");
			}
		}catch(Exception e){
			this.errorLoginMsg = e.getMessage();
			return Action.INPUT;
		}
		return userLoginValidateCore(false, currentLoginJsp,checkstate);*/
	}
	
	public Row userLoginValidateCore(boolean isOutUserFlag , String targetJsp ,int checkstate){
//		ISysManageOperatorFacade iop = FacadeFactory.getSysManageOperatorFacade();
		ILogFacade logFacade = FacadeFactory.getLogFacade();
//		int checkstate=iop.operatorLoginCheckUser(this.usercode, this.password , isOutUserFlag);
		//int checkstate = 1;
		try {
			//if (this.iop.operatorLogin(this.usercode, this.password)) {
			if (checkstate==1) {
				return userLoginCore();
			} else {
				LogUtil.debug("fail");
				//return "fail";
				// 1验证通过,2用户名不存在 3密码不正确
				if(checkstate==2)
					this.errorLoginMsg = "用户代码  "+this.usercode+" 不存在 ！";
				if(checkstate==3)
					this.errorLoginMsg = "用户密码不正确！";
				if(checkstate==4)
					this.errorLoginMsg = "用户代码  "+this.usercode+" 无效 ！";
				if(checkstate==5)
					this.errorLoginMsg = "用户名或密码不正确";
				logFacade
						.addOperationlog(Constants.SERVICECODE_OPERATOR_LOGIN, this.usercode, "操作员登录", "0000", "新增",
								"01", "操作员登录失败,操作员代码或者密码有误", getIp());
				return new RowImpl(ImmutableMap.of("resulturl","login-input","errorLoginMsg",errorLoginMsg));
			}
		} catch (Exception e) {
			//e.printStackTrace();
			LogUtil.error(e);
			//return "fail";
			this.errorLoginMsg = "操作员代码或者密码有误，请重新登陆！";
			logFacade.addOperationlog(Constants.SERVICECODE_OPERATOR_LOGIN, this.usercode, "操作员登录", "0000", "新增",
					"01", "操作员登录失败,操作员代码或者密码有误", getIp());
			return new RowImpl(ImmutableMap.of("resulturl",targetJsp,"errorLoginMsg",errorLoginMsg));
		}
	}
	
	
	/**
	 * 用户登录
	 */
	public Row userLoginCore() {
		LogUtil.debug("**** Login OK");
//		ISysManageOperatorFacade iop = FacadeFactory.getSysManageOperatorFacade();
		ILogFacade logFacade = FacadeFactory.getLogFacade();
		/*Operator operator = iop.getOperator(this.usercode);
		this.loginmsg.setOldoperatorcode(this.usercode);
		this.loginmsg.setOperatorcode(operator.getOperatorcode());
		this.loginmsg.setLoginTime(new Date());
		this.loginmsg.setIpAddress(getIp());
		this.loginmsg.setBusinesstype(operator.getBusinesstype());//业务类型
		this.loginmsg.setWorkcode(operator.getWorkcode());
		this.loginmsg.setVipflag(Constants.OPERATOR_ISVIP_YES.equals(operator.getIsvipflag())?true:false);
		//取得预约提醒字符串
		//this.loginmsg.setNotePadMessage(getNotePadMessage(this.usercode));
		//取得菜单
		getOneTwoLevelMenu(operator.getOperatorcode());
		//取得操作员相关信息
		getOperatorMessage(operator.getOperatorcode());*/
		//写SESSION
//		ActionContext.getContext().getSession().put("userInfo", this.loginmsg);
		
		//保存是否日结在session
		//ajaxIsAlreadyDaily(this.usercode);
		//写操作日志
		logFacade.addOperationlog(Constants.SERVICECODE_OPERATOR_LOGIN, this.usercode,
				"操作员登录", "0000", "新增", "01", "操作员成功登录", getIp());
			return new RowImpl(ImmutableMap.of("resulturl","iframe"));
//		return "index";
	}
	
	/**
	 * 用户退出登录
	 */
	public String userLoginOut(Row row) {
		ILogFacade logFacade = FacadeFactory.getLogFacade();
		try {
			LogUtil.debug("清除Session!");
			logFacade.addOperationlog(Constants.SERVICECODE_OPERATOR_LOGINOUT,this.getOperatorInfo().getUserAccount(), "操作员退出系统", "0000", "新增", "01",
					"操作员退出系统", getIp());
			//清空session			
			RequestAttributes ra = RequestContextHolder.getRequestAttributes();
			HttpServletRequest request = ((ServletRequestAttributes)ra).getRequest();
		 	request.getSession().invalidate();
		} catch (Exception e) {
			LogUtil.error("退出登录异常：" + e);
		}
		return new JsonResult(true,"安全退出成功！").toJsonString();
	}
	
	//获取当地的IP
	private String getIp() {
		RequestAttributes ra = RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = ((ServletRequestAttributes)ra).getRequest();
		String ip = request.getHeader("X-Forwarded-For");
		if (ip == null || ip.length() == 0) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

}
