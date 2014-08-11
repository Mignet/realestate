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
 * ϵͳ��¼��
 * ����ϵͳ��¼����Ա�������Ϣ
 * @author  Mignet
 */

public class LoginAction extends BaseDelegate{
	private static final long serialVersionUID = 8332144223816323337L;
	private String usercode;// �û���
	private String password;// ����
	private String validatecode;// ��֤��
	private String loginflag;
	private String errorLoginMsg = "";//��½������Ϣ���û���������û���������

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
	    	LogUtil.debug("LoginAction.loginJDBC() �������ݿⷽʽ��֤����.");
	    }
	    /**
	     * Ϊ������ԣ�������ͳһΪ1
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
	      //logger.error("LoginAction.loginJDBC()�����쳣,�����û�����ΪMD5ʱ�����쳣" + e1, e1);
	      LogUtil.debug("LoginAction.loginJDBC()�����쳣,�����û�����ΪMD5ʱ�����쳣" ,e1);
	      throw new GeneralException("��½ʱ�����쳣:"+e1.getMessage());
	    }

	    try
	    {
	      return SecurityUtil.getInstance().checkPassword4Outer(userAccount, password) == 0;
	    }
	    catch (GeneralFailureException e)
	    {
	      //logger.error("LoginAction.loginJDBC()��½ʱ�����쳣" + e, e);
	      LogUtil.debug("LoginAction.loginJDBC()��½ʱ�����쳣" ,e);
	      throw new GeneralException("��½ʱ�����쳣:"+e.getMessage());
	    }
//	    return false;
	  }
	
	public Row initLogin(){
			int checkstate=1;
		 	LogUtil.info("suplisӦ�ü�������--------------------------------------" );
		    //��ʼ��session
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
				//��½�û������ֱ�
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
				LogUtil.info("suplisӦ�ü��سɹ���--------------------------------------" );
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
	 * �����û���¼
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
			
		// 1��֤ͨ��,2�û��������� 3���벻��ȷ
		//����ie7��8���ڵ�½�������Աsession�������� ,
		//ie7��8��-nomerge �����Ŀ��󣬲��ܽ�����ѡ���½�����⣬���Խ�����´�һ�����ڵ�����
		try{
			LoginMsgBo loginmsgtemp = (LoginMsgBo)ActionContext.getContext().getSession().get("loginmsg");
			if(loginmsgtemp!=null&& !this.usercode.equals(loginmsgtemp.getOperatorcode())){
				throw new Exception ("�Ự���ݳ��ֹ������⣬����´�IE��½!");
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
				// 1��֤ͨ��,2�û��������� 3���벻��ȷ
				if(checkstate==2)
					this.errorLoginMsg = "�û�����  "+this.usercode+" ������ ��";
				if(checkstate==3)
					this.errorLoginMsg = "�û����벻��ȷ��";
				if(checkstate==4)
					this.errorLoginMsg = "�û�����  "+this.usercode+" ��Ч ��";
				if(checkstate==5)
					this.errorLoginMsg = "�û��������벻��ȷ";
				logFacade
						.addOperationlog(Constants.SERVICECODE_OPERATOR_LOGIN, this.usercode, "����Ա��¼", "0000", "����",
								"01", "����Ա��¼ʧ��,����Ա���������������", getIp());
				return new RowImpl(ImmutableMap.of("resulturl","login-input","errorLoginMsg",errorLoginMsg));
			}
		} catch (Exception e) {
			//e.printStackTrace();
			LogUtil.error(e);
			//return "fail";
			this.errorLoginMsg = "����Ա��������������������µ�½��";
			logFacade.addOperationlog(Constants.SERVICECODE_OPERATOR_LOGIN, this.usercode, "����Ա��¼", "0000", "����",
					"01", "����Ա��¼ʧ��,����Ա���������������", getIp());
			return new RowImpl(ImmutableMap.of("resulturl",targetJsp,"errorLoginMsg",errorLoginMsg));
		}
	}
	
	
	/**
	 * �û���¼
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
		this.loginmsg.setBusinesstype(operator.getBusinesstype());//ҵ������
		this.loginmsg.setWorkcode(operator.getWorkcode());
		this.loginmsg.setVipflag(Constants.OPERATOR_ISVIP_YES.equals(operator.getIsvipflag())?true:false);
		//ȡ��ԤԼ�����ַ���
		//this.loginmsg.setNotePadMessage(getNotePadMessage(this.usercode));
		//ȡ�ò˵�
		getOneTwoLevelMenu(operator.getOperatorcode());
		//ȡ�ò���Ա�����Ϣ
		getOperatorMessage(operator.getOperatorcode());*/
		//дSESSION
//		ActionContext.getContext().getSession().put("userInfo", this.loginmsg);
		
		//�����Ƿ��ս���session
		//ajaxIsAlreadyDaily(this.usercode);
		//д������־
		logFacade.addOperationlog(Constants.SERVICECODE_OPERATOR_LOGIN, this.usercode,
				"����Ա��¼", "0000", "����", "01", "����Ա�ɹ���¼", getIp());
			return new RowImpl(ImmutableMap.of("resulturl","iframe"));
//		return "index";
	}
	
	/**
	 * �û��˳���¼
	 */
	public String userLoginOut(Row row) {
		ILogFacade logFacade = FacadeFactory.getLogFacade();
		try {
			LogUtil.debug("���Session!");
			logFacade.addOperationlog(Constants.SERVICECODE_OPERATOR_LOGINOUT,this.getOperatorInfo().getUserAccount(), "����Ա�˳�ϵͳ", "0000", "����", "01",
					"����Ա�˳�ϵͳ", getIp());
			//���session			
			RequestAttributes ra = RequestContextHolder.getRequestAttributes();
			HttpServletRequest request = ((ServletRequestAttributes)ra).getRequest();
		 	request.getSession().invalidate();
		} catch (Exception e) {
			LogUtil.error("�˳���¼�쳣��" + e);
		}
		return new JsonResult(true,"��ȫ�˳��ɹ���").toJsonString();
	}
	
	//��ȡ���ص�IP
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
