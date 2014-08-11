package com.szhome.cq.utils;

/**
 * 参数表常量
 * 
 * @author Mignet
 * 
 */
public class WbfConstants {
	
	
	//操作结果 00失败 01成功
	public static final String OPERATION_FAIL = "00"; // 00失败
	public static final String OPERATION_SUCCESS = "01"; // 01成功
	
	public final static String DEFAULT_OPERATORCODE = "999999"; // 默认系统后台用户代码
	public final static String DEFAULT_CHARACTERID = "0000000000"; // 默认系统后台角色
	
	
	//八大类变量
	public static final  String  HOUSE_ONWERSHIP="102";					//房屋所有权登记
		public static final  String HOUSE_ONWERSHIP_INIT="1217";			//房屋所有权初始登记
		public static final  String HOUSE_ONWERSHIP_SEC="1230";			//房屋所有权二级转移登记
		public static final  String HOUSE_ONWERSHIP_THR="1232";			//房屋所有权三级转移登记
		public static final  String HOUSE_ONWERSHIP_CHANGE="1002";			//房屋所有权变更登记
	public static final  String REG_ATTACH="107";					//查封大类
		public static final  String UNATTACH="1072";			//解封登记
		public static final  String ATTACH="1071";			//查封登记
		public static final  String MORTRE="1233";			//一般抵押权设立登记
		public static final  String UNMORTRE="1236";			//抵押权注销登记
		
	public static final  String REALESTATE_CAN="112";     //房地产注销
		
		public static final  String DEMURRER="106";     //异议登记
		public static final  String HDEMURRER="1061";     //异议登记
		public static final  String UNDEMURRER="1062";     //注销异议登记异议登记
		public static final  String JUDREMARK="1102";     //注销司法裁定过户备注
		public static final  String UNREMARK="1092";     //注销内部提示性备注
	public static final  String REVOKEAPPROVAL="1141";     //撤销核准
		public static final  String REVOKEAPPROVAL1="114";     //撤销核准
	public static final String PRE_SALE_BAKUP = "1237";				//预售备案
	public static final String CANCEL_SALE_BAKUP = "1238";				//预售备案
	public static final String BAKUP = "108";				//备案大类
		
		
		
		//变更更正登记变更项常量
		public static final String PRODUCT_NAME ="060001";			//房地产名称
		public static final String HOUSE_LOCATION="060002";			//房屋坐落
		public static final String BUILD_AREA="060003";				//建筑面积
		public static final String TAONEI_AREA="060004";				//套内面积
		public static final String FLATLET_USAGE="060005";			//房屋用途
		public static final String HOUSE_ATTR="060006";				//购房性质
		public static final String REG_VALUE="060007";				//登记价格
		public static final String HOL_NAME="060016";					//权利人名称
		public static final String HOL_CER_NO="060017";				//身份证号码
		public static final String PARCEL_CODE="060008";				//宗地号
		public static final String PARCEL_AREA="060009";				//宗地面积
		public static final String REAL_USAGE="060010";				//土地用途	
		public static final String LOCATION_AREA="060011";			//所在区
		public static final String LAND_ADDRESS="060012";				//土地位置
		public static final String USE_PER="060013";					//使用年限
		public static final String PAR_REG_VALUE="060014";			//土地价款
		public static final String ADD_PARCEL_PRICE="060015";			//补地价款
		
		
		//业务定义类型常量
		//public static String HOUSE_ONWERSHIP_INIT="1217";
	public static final String  LAND_USERIGHT="103";						//土地使用权
		public static final String  LAND_CHANGE="1034";						//土地使用权变更登记
	public static final String  MORTGAGE_RIGHT="101";						//房地产抵押登记
		public static final String MORTGAGE_SHIFT="1234";					//一般抵押权转移登记
		public static final String MORTGAGE_CHANGE="1235";					//一般抵押权变更登记
		public static final String MAX_MORTGAGE_SHIFT="1241";				//最高额抵押转移登记
		public static final String MAX_MORTGAGE_CHANGE="1242";				//最高额抵押变更登记
		public static final String MAX_CONFIRM_REG="1240";				//最高额抵押确定登记
		public static final String MAX_MORTGAGE_CANCEL="1243";				//最高额抵押注销登记
		public static final String MORTGAGE_CANCEL = "1236";				//一般抵押权注销登记
		
	public static final String MATERIAL_REPLENISH_PRODEFID = "1333";				//补正材料
	public static final String MATERIAL_REPLENISH = "1301";				//补正材料	
		public static final int QUALITY_INSPECTION=1290;				//质量抽检流程
	public static final String  ATTACH_DISTRAIN="107"; 					//查封登记
	public static final String CORRECTION="111";							//更正登记
	public static final String REISSUE	="113";								//补换发证登记
		public static final String REG_UNIT_HOUSE="009001";			//登记单元类型房屋
		public static final String REG_UNIT_BUILDING="009002";		//登记单元类型楼宇
		public static final String REG_UNIT_PARCEL="009003";			//登记单元类型宗地
		
		public static final String TRANSFEROR ="063001";				//转让方
		public static final String TRANSFEROREE ="063002";			//受让方
		public static final String MORTGAGER ="063003";				//抵押人
		public static final String MORTGAGEE ="063004";				//抵押权人
		public static final String MORTGAGE_TRANSFEROR ="063005";		//抵押权转让方
		public static final String MORTGAGE_TRANSFEROREE ="063006";	//抵押权受让方
		public static final String NOTICE_OBLIGOR ="063007";			//预告义务人
		public static final String NOTICE_HOLDER ="063008";			//预告权利人
		public static final String NEED_EASEMENT ="063009";			//需役地方
		public static final String PROVIDER_EASEMENT ="063010";		//供役地方
		public static final String PRE_SALER ="063014";		//预售方
		public static final String PRE_BUYER ="063015";		//预售方
		
		
		//受理条件配置常量
		
		public static final String PREAUDIT="064001";              //前置条件
		public static final String LIMIT="064002";              //限制条件
		public static final String MESSAGE="064003";              //提示条件
		
		
		//----------------业务状态常量  
		public static final String EFFECTIVE="053002";                     //有效
		public static final String UNEFFECTIVE="053001";					//无效
		public static final String PROCESSING="053007";					//在途业务，处理中
		
		
		
		
		//-------------------------登记单元类型-------------------------
		public static final String PARCEL="009003";                     //宗地
		public static final String BUILDING="009002";					//建筑物
		public static final String HOUSE="009001";					//房屋
		
		
		//-------------------------登记点常量配置------------------------
		public static final String FUTIAN="054001";					//福田
		public static final String LUOHU="054002";					//罗湖
		public static final String NANSHAN="054003";					//南山
		public static final String YANTIAN="054004";					//盐田
		public static final String BAOAN="054005";					//宝安
		public static final String LONGGANG="054006";					//龙岗
		public static final String GUANGMING="054007";					//光明
		public static final String PINGSHAN="054008";					//坪山
		public static final String LONGHUA="054009";					//龙华
		public static final String DAPENG="0540010";					//大鹏
		
		public static final String FUTIAN_CODE="2";						//福田编号对应值 
		public static final String LUOHU_CODE="1";						//罗湖编号对应值 
		public static final String NANSHAN_CODE="3";					//南山编号对应值 
		public static final String YANTIAN_CODE="6";					//盐田编号对应值 
		public static final String BAOAN_CODE="4";						//宝安编号对应值 
		public static final String LONGGANG_CODE="5";					//龙岗编号对应值 
		public static final String GUANGMING_CODE="8";					//光明编号对应值 
		public static final String PINGSHAN_CODE="7";					//坪山编号对应值 
		public static final String LONGHUA_CODE="9";					//龙华编号对应值 
		public static final String DAPENG_CODE="10";					//大鹏编号对应值 
		
		public static final String HOUSEHOLD_SHENZHEN="shenzhen";		//深户 
		public static final String HOUSEHOLD_OTHER="other";				//非深户
		
		public static final String HOUSEHOLD_SHENZHEN_CODE="9c";				//深户 编号对应值
		public static final String HOUSEHOLD_OTHER_CODE="9d";				//非深户 编号对应值
		
		
		
		
		/**************************************查封类型****************************************/
		
		//查封类型，用于查封
		public static final String T_ATTACH="066001";					//查封
		public static final String T_REATTACH="066002";					//续封
		public static final String T_LASTATTACH="066003";				//轮候查封
		public static final String T_CHATTACH="066004";					//轮候查封转查封
		//解封类型，用于解封
		public static final String T_UNATTACH="067001";					//解封
		public static final String T_UNREATTACH="067002";					//解除轮候查封
		public static final String T_UNCHATTACH="067003";					//轮候查封转查封
		
		/***************************************特殊流程**************************************/
		public static final String REJECTJDS="REJECTJDS";                  //"驳回决定书";
		public static final String DEFERMENTJDS="DEFERMENTJDS";                        //"暂缓决定书";
		public static final String DELAYSQS="DELAYSQS";                   ///"延期申请表";
		public static final String HANGUPSQS="HANGUPSQS";                             //"挂起申请表";
		public static final String BACKLANGUAGESQS="BACKLANGUAGESQS";      //"退文申请表";
		//
		public static final String REFUND="1302";                     //退文 流程定义ID
		public static final String REJECTION="1298";                  //驳回 流程定义ID
		public static final String RESPITE="1299";                    //暂缓 流程定义ID
		public static final String SUSPEND="1300";                    //挂起 流程定义ID
		public static final String DELAY="1301";                      //延期 流程定义ID
		/***************************************特殊流程状态**************************************/
		public static final String PROCTOBECREATE = "068001";                   //特殊流程‘待创建’
		public static final String PROCONTHEMARCH = "068002";                   //特殊流程‘进行中’
		public static final String PROCCOMPLETED = "068003";                    //特殊流程‘已完成’
		
		
		/*************************************材料类型标识***************************************/
		public static final String REC_TYPE_FLAG_RECEIVAL = "0";  				//接件材料
		public static final String REC_TYPE_FLAG_DISPACH = "1";  				//必文材料
		public static final String REC_TYPE_FLAG_CORRECTION = "2";  			//补正材料
}

