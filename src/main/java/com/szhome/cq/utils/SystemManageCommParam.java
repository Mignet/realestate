package com.szhome.cq.utils;

/**
 * ϵͳ����  ��������
 * @author Mignet
 *
 */
public class SystemManageCommParam {
  
  /**
   * ϵͳ����  ���ݹ���
   */
  
  public static final String LOGIN_PAGE="login!input.action"; //��¼ҳ
  public static final String SUCCESS_PAGE = "index.action";
  public static final String ERROR_PAGE = "error.jsp"; //������Ϣҳ
  public static final String LOGIN_ACTION="login/login!input.action?loginflag=1";
  public static final String LOGIN_ACTION_INDEX="login/login!input.action?loginflag=0";
  public static final String MAIN_INDEX="/login/index.action";
  
  /**
   * ϵͳ����  ��ɫί��״̬
   */
  public static final String  STATUS_NEW = "0";//����״̬
  public static final String  STATUS_AUDIT = "1";//�����
  public static final String  STATUS_PASSED = "2";//ͨ��
  public static final String  STATUS_REFUSE = "3";//�ܾ�
  
  /**
   * ϵͳ����  ����Ȩ���趨
   */
  public static final String  STATUS_TEMP = "1";//��ʱ��ɫ
  public static final String  STATUS_NORMAL = "0";//������ɫ
  
  /**
   * ϵͳ����  �쳣����ҳ�����Ӳ���
   */
  public static final String  URL_SYS_ROLELIST = "./sysmanagerole!roleList.action";//��ɫ�б�
  public static final String  URL_SYS_ROLECOMMLIST = "./sysmanageroleconfirm!roleCommList.action";//��ɫ���
  public static final String  URL_SYS_ROLEMENULIST = "./sysmanagerolermenu!roleMenuList.action";//��ɫȨ��
  public static final String  URL_SYS_TEMPCOMMISIONLIST ="./sysmanagetempcommision!tempCommisionList.action";//����Ȩ�� 
  public static final String  URL_SYS_SYSMANAGERLIST = "./sysmanagetempcommision!sysManagerList.action";//����Ȩ��
  
  public static final String  URL_SYS_SETROLEOPETOR = "./sysmanageroleoperator!operatorInRole.action?roleid=";//���ý�ɫ����Աҳ��
  public static final String  URL_SYS_ADDROLE = "./sysmanagerole!roleAdd.action";//��ӽ�ɫҳ�� 
  public static final String  URL_SYS_UPDATEROLE = "./sysmanagerole!roleModify.action?roleid=";//�޸Ľ�ɫҳ��
  
  
  /**
   * ϵͳ����  ��ɫ
   */
  public static final String  STATUS_FFECTFLAG_1 = "1";//��Ч
  public static final String  STATUS_EFFECTFLAG_0 = "0";//��Ч
  
  
  /**
   *  �Ǽǲ����ڵ�|����|¥����ݹ����ʶ
   */
  public static final String NATURAL = new Integer(1).toString();                //��Ȼ��Ϣ
  public static final String OWNERSHIPORUSER = new Integer(2).toString();        //����Ȩ��Ϣ/ʹ��Ȩ
  public static final String MORT = new Integer(3).toString();                   //��ѺȨ��Ϣ
  public static final String EASEMENT = new Integer(4).toString();               //����Ȩ��Ϣ
  public static final String PREADVICE = new Integer(5).toString();              //Ԥ��Ǽ���Ϣ
  public static final String DISSENT = new Integer(6).toString();                //����Ǽ���Ϣ
  public static final String ATTACH = new Integer(7).toString();                 //���Ǽ���Ϣ
  
}

