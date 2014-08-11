package com.szhome.cq.utils;

/**
 * ��������
 * 
 * @author Mignet
 * 
 */
public class WbfConstants {
	
	
	//������� 00ʧ�� 01�ɹ�
	public static final String OPERATION_FAIL = "00"; // 00ʧ��
	public static final String OPERATION_SUCCESS = "01"; // 01�ɹ�
	
	public final static String DEFAULT_OPERATORCODE = "999999"; // Ĭ��ϵͳ��̨�û�����
	public final static String DEFAULT_CHARACTERID = "0000000000"; // Ĭ��ϵͳ��̨��ɫ
	
	
	//�˴������
	public static final  String  HOUSE_ONWERSHIP="102";					//��������Ȩ�Ǽ�
		public static final  String HOUSE_ONWERSHIP_INIT="1217";			//��������Ȩ��ʼ�Ǽ�
		public static final  String HOUSE_ONWERSHIP_SEC="1230";			//��������Ȩ����ת�ƵǼ�
		public static final  String HOUSE_ONWERSHIP_THR="1232";			//��������Ȩ����ת�ƵǼ�
		public static final  String HOUSE_ONWERSHIP_CHANGE="1002";			//��������Ȩ����Ǽ�
	public static final  String REG_ATTACH="107";					//������
		public static final  String UNATTACH="1072";			//���Ǽ�
		public static final  String ATTACH="1071";			//���Ǽ�
		public static final  String MORTRE="1233";			//һ���ѺȨ�����Ǽ�
		public static final  String UNMORTRE="1236";			//��ѺȨע���Ǽ�
		
	public static final  String REALESTATE_CAN="112";     //���ز�ע��
		
		public static final  String DEMURRER="106";     //����Ǽ�
		public static final  String HDEMURRER="1061";     //����Ǽ�
		public static final  String UNDEMURRER="1062";     //ע������Ǽ�����Ǽ�
		public static final  String JUDREMARK="1102";     //ע��˾���ö�������ע
		public static final  String UNREMARK="1092";     //ע���ڲ���ʾ�Ա�ע
	public static final  String REVOKEAPPROVAL="1141";     //������׼
		public static final  String REVOKEAPPROVAL1="114";     //������׼
	public static final String PRE_SALE_BAKUP = "1237";				//Ԥ�۱���
	public static final String CANCEL_SALE_BAKUP = "1238";				//Ԥ�۱���
	public static final String BAKUP = "108";				//��������
		
		
		
		//��������ǼǱ�����
		public static final String PRODUCT_NAME ="060001";			//���ز�����
		public static final String HOUSE_LOCATION="060002";			//��������
		public static final String BUILD_AREA="060003";				//�������
		public static final String TAONEI_AREA="060004";				//�������
		public static final String FLATLET_USAGE="060005";			//������;
		public static final String HOUSE_ATTR="060006";				//��������
		public static final String REG_VALUE="060007";				//�ǼǼ۸�
		public static final String HOL_NAME="060016";					//Ȩ��������
		public static final String HOL_CER_NO="060017";				//���֤����
		public static final String PARCEL_CODE="060008";				//�ڵغ�
		public static final String PARCEL_AREA="060009";				//�ڵ����
		public static final String REAL_USAGE="060010";				//������;	
		public static final String LOCATION_AREA="060011";			//������
		public static final String LAND_ADDRESS="060012";				//����λ��
		public static final String USE_PER="060013";					//ʹ������
		public static final String PAR_REG_VALUE="060014";			//���ؼۿ�
		public static final String ADD_PARCEL_PRICE="060015";			//���ؼۿ�
		
		
		//ҵ�������ͳ���
		//public static String HOUSE_ONWERSHIP_INIT="1217";
	public static final String  LAND_USERIGHT="103";						//����ʹ��Ȩ
		public static final String  LAND_CHANGE="1034";						//����ʹ��Ȩ����Ǽ�
	public static final String  MORTGAGE_RIGHT="101";						//���ز���Ѻ�Ǽ�
		public static final String MORTGAGE_SHIFT="1234";					//һ���ѺȨת�ƵǼ�
		public static final String MORTGAGE_CHANGE="1235";					//һ���ѺȨ����Ǽ�
		public static final String MAX_MORTGAGE_SHIFT="1241";				//��߶��Ѻת�ƵǼ�
		public static final String MAX_MORTGAGE_CHANGE="1242";				//��߶��Ѻ����Ǽ�
		public static final String MAX_CONFIRM_REG="1240";				//��߶��Ѻȷ���Ǽ�
		public static final String MAX_MORTGAGE_CANCEL="1243";				//��߶��Ѻע���Ǽ�
		public static final String MORTGAGE_CANCEL = "1236";				//һ���ѺȨע���Ǽ�
		
	public static final String MATERIAL_REPLENISH_PRODEFID = "1333";				//��������
	public static final String MATERIAL_REPLENISH = "1301";				//��������	
		public static final int QUALITY_INSPECTION=1290;				//�����������
	public static final String  ATTACH_DISTRAIN="107"; 					//���Ǽ�
	public static final String CORRECTION="111";							//�����Ǽ�
	public static final String REISSUE	="113";								//������֤�Ǽ�
		public static final String REG_UNIT_HOUSE="009001";			//�Ǽǵ�Ԫ���ͷ���
		public static final String REG_UNIT_BUILDING="009002";		//�Ǽǵ�Ԫ����¥��
		public static final String REG_UNIT_PARCEL="009003";			//�Ǽǵ�Ԫ�����ڵ�
		
		public static final String TRANSFEROR ="063001";				//ת�÷�
		public static final String TRANSFEROREE ="063002";			//���÷�
		public static final String MORTGAGER ="063003";				//��Ѻ��
		public static final String MORTGAGEE ="063004";				//��ѺȨ��
		public static final String MORTGAGE_TRANSFEROR ="063005";		//��ѺȨת�÷�
		public static final String MORTGAGE_TRANSFEROREE ="063006";	//��ѺȨ���÷�
		public static final String NOTICE_OBLIGOR ="063007";			//Ԥ��������
		public static final String NOTICE_HOLDER ="063008";			//Ԥ��Ȩ����
		public static final String NEED_EASEMENT ="063009";			//���۵ط�
		public static final String PROVIDER_EASEMENT ="063010";		//���۵ط�
		public static final String PRE_SALER ="063014";		//Ԥ�۷�
		public static final String PRE_BUYER ="063015";		//Ԥ�۷�
		
		
		//�����������ó���
		
		public static final String PREAUDIT="064001";              //ǰ������
		public static final String LIMIT="064002";              //��������
		public static final String MESSAGE="064003";              //��ʾ����
		
		
		//----------------ҵ��״̬����  
		public static final String EFFECTIVE="053002";                     //��Ч
		public static final String UNEFFECTIVE="053001";					//��Ч
		public static final String PROCESSING="053007";					//��;ҵ�񣬴�����
		
		
		
		
		//-------------------------�Ǽǵ�Ԫ����-------------------------
		public static final String PARCEL="009003";                     //�ڵ�
		public static final String BUILDING="009002";					//������
		public static final String HOUSE="009001";					//����
		
		
		//-------------------------�Ǽǵ㳣������------------------------
		public static final String FUTIAN="054001";					//����
		public static final String LUOHU="054002";					//�޺�
		public static final String NANSHAN="054003";					//��ɽ
		public static final String YANTIAN="054004";					//����
		public static final String BAOAN="054005";					//����
		public static final String LONGGANG="054006";					//����
		public static final String GUANGMING="054007";					//����
		public static final String PINGSHAN="054008";					//ƺɽ
		public static final String LONGHUA="054009";					//����
		public static final String DAPENG="0540010";					//����
		
		public static final String FUTIAN_CODE="2";						//�����Ŷ�Ӧֵ 
		public static final String LUOHU_CODE="1";						//�޺���Ŷ�Ӧֵ 
		public static final String NANSHAN_CODE="3";					//��ɽ��Ŷ�Ӧֵ 
		public static final String YANTIAN_CODE="6";					//�����Ŷ�Ӧֵ 
		public static final String BAOAN_CODE="4";						//������Ŷ�Ӧֵ 
		public static final String LONGGANG_CODE="5";					//���ڱ�Ŷ�Ӧֵ 
		public static final String GUANGMING_CODE="8";					//������Ŷ�Ӧֵ 
		public static final String PINGSHAN_CODE="7";					//ƺɽ��Ŷ�Ӧֵ 
		public static final String LONGHUA_CODE="9";					//������Ŷ�Ӧֵ 
		public static final String DAPENG_CODE="10";					//������Ŷ�Ӧֵ 
		
		public static final String HOUSEHOLD_SHENZHEN="shenzhen";		//� 
		public static final String HOUSEHOLD_OTHER="other";				//���
		
		public static final String HOUSEHOLD_SHENZHEN_CODE="9c";				//� ��Ŷ�Ӧֵ
		public static final String HOUSEHOLD_OTHER_CODE="9d";				//��� ��Ŷ�Ӧֵ
		
		
		
		
		/**************************************�������****************************************/
		
		//������ͣ����ڲ��
		public static final String T_ATTACH="066001";					//���
		public static final String T_REATTACH="066002";					//����
		public static final String T_LASTATTACH="066003";				//�ֺ���
		public static final String T_CHATTACH="066004";					//�ֺ���ת���
		//������ͣ����ڽ��
		public static final String T_UNATTACH="067001";					//���
		public static final String T_UNREATTACH="067002";					//����ֺ���
		public static final String T_UNCHATTACH="067003";					//�ֺ���ת���
		
		/***************************************��������**************************************/
		public static final String REJECTJDS="REJECTJDS";                  //"���ؾ�����";
		public static final String DEFERMENTJDS="DEFERMENTJDS";                        //"�ݻ�������";
		public static final String DELAYSQS="DELAYSQS";                   ///"���������";
		public static final String HANGUPSQS="HANGUPSQS";                             //"���������";
		public static final String BACKLANGUAGESQS="BACKLANGUAGESQS";      //"���������";
		//
		public static final String REFUND="1302";                     //���� ���̶���ID
		public static final String REJECTION="1298";                  //���� ���̶���ID
		public static final String RESPITE="1299";                    //�ݻ� ���̶���ID
		public static final String SUSPEND="1300";                    //���� ���̶���ID
		public static final String DELAY="1301";                      //���� ���̶���ID
		/***************************************��������״̬**************************************/
		public static final String PROCTOBECREATE = "068001";                   //�������̡���������
		public static final String PROCONTHEMARCH = "068002";                   //�������̡������С�
		public static final String PROCCOMPLETED = "068003";                    //�������̡�����ɡ�
		
		
		/*************************************�������ͱ�ʶ***************************************/
		public static final String REC_TYPE_FLAG_RECEIVAL = "0";  				//�Ӽ�����
		public static final String REC_TYPE_FLAG_DISPACH = "1";  				//���Ĳ���
		public static final String REC_TYPE_FLAG_CORRECTION = "2";  			//��������
}

