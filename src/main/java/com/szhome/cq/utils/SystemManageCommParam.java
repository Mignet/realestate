package com.szhome.cq.utils;

/**
 * 系统管理  公共参数
 * @author Mignet
 *
 */
public class SystemManageCommParam {
  
  /**
   * 系统管理  数据过滤
   */
  
  public static final String LOGIN_PAGE="login!input.action"; //登录页
  public static final String SUCCESS_PAGE = "index.action";
  public static final String ERROR_PAGE = "error.jsp"; //报错信息页
  public static final String LOGIN_ACTION="login/login!input.action?loginflag=1";
  public static final String LOGIN_ACTION_INDEX="login/login!input.action?loginflag=0";
  public static final String MAIN_INDEX="/login/index.action";
  
  /**
   * 系统管理  角色委托状态
   */
  public static final String  STATUS_NEW = "0";//申请状态
  public static final String  STATUS_AUDIT = "1";//待审核
  public static final String  STATUS_PASSED = "2";//通过
  public static final String  STATUS_REFUSE = "3";//拒绝
  
  /**
   * 系统管理  个人权限设定
   */
  public static final String  STATUS_TEMP = "1";//临时角色
  public static final String  STATUS_NORMAL = "0";//正常角色
  
  /**
   * 系统管理  异常返回页面链接参数
   */
  public static final String  URL_SYS_ROLELIST = "./sysmanagerole!roleList.action";//角色列表
  public static final String  URL_SYS_ROLECOMMLIST = "./sysmanageroleconfirm!roleCommList.action";//角色审核
  public static final String  URL_SYS_ROLEMENULIST = "./sysmanagerolermenu!roleMenuList.action";//角色权限
  public static final String  URL_SYS_TEMPCOMMISIONLIST ="./sysmanagetempcommision!tempCommisionList.action";//个人权限 
  public static final String  URL_SYS_SYSMANAGERLIST = "./sysmanagetempcommision!sysManagerList.action";//他人权限
  
  public static final String  URL_SYS_SETROLEOPETOR = "./sysmanageroleoperator!operatorInRole.action?roleid=";//设置角色操作员页面
  public static final String  URL_SYS_ADDROLE = "./sysmanagerole!roleAdd.action";//添加角色页面 
  public static final String  URL_SYS_UPDATEROLE = "./sysmanagerole!roleModify.action?roleid=";//修改角色页面
  
  
  /**
   * 系统管理  角色
   */
  public static final String  STATUS_FFECTFLAG_1 = "1";//有效
  public static final String  STATUS_EFFECTFLAG_0 = "0";//无效
  
  
  /**
   *  登记簿（宗地|房屋|楼宇）内容管理标识
   */
  public static final String NATURAL = new Integer(1).toString();                //自然信息
  public static final String OWNERSHIPORUSER = new Integer(2).toString();        //所有权信息/使用权
  public static final String MORT = new Integer(3).toString();                   //抵押权信息
  public static final String EASEMENT = new Integer(4).toString();               //地役权信息
  public static final String PREADVICE = new Integer(5).toString();              //预告登记信息
  public static final String DISSENT = new Integer(6).toString();                //异议登记信息
  public static final String ATTACH = new Integer(7).toString();                 //查封登记信息
  
}

