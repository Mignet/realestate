/*********************************************************************************
*文  件  名  称: enum-data.js
*功  能  描  述: 枚举数据,用于前台JS比较判断 
*Copyright (c): 深圳道行天下软件技术有限公司
*创    建    人: xuzz
*创  建  日  期： 2014-03-27
*修  改  历  史：
*文  件  版  本： 1.0
**********************************************************************************/

window.enumdata = new enumdata();							//定义全局变量
var app_name = '/dxtx_re';							//当前程序要目录
function enumdata(){

/********************************************登记单元类型***********************************************/
	this.house='009001';
	this.parcel='009003';
	this.build='009002';
/********************************************查封类型***********************************************/
	this.attach='066001';   //查封
	this.reattach='066002';  //续封
	this.lastattach='066003'; //轮候查封
	this.chattach='066004';  //轮候查封转查封
	
/********************************************解封类型***********************************************/
	this.unattach='067001';   //解封
	this.unreattach='067002';  //解除轮候查封
	this.unchattach='067003';  //轮候查封转查封
	

	
	/********************************************字典类型***********************************************/
	
	this.bustype='061';  //登记类型
	this.regstation='054';  //登记点
	/********************************************节点类型***********************************************/
	this.ACCEPTED='受理';
	this.EXAMINE='初审';
	this.REEXAMINE='复审';
	this.APPROVED='核准';
	this.BULLETIN='公告';
	this.INITIALEXAMINE='初步审查';
	this.INITIALAUDIT='初步审核';
	this.INITIALVALIDATION='初步审定';
	this.CHARGE='收费';
	this.CERTIFICATE='缮证';
	this.POSTING='发文';
	this.FILE='归档';
	
	/********************************************登记类型***********************************************/
	this.remark='1091';   //内部提示性备注
	this.unremark='1092';  //注销内部提示性备注
	
	this.PRE_SALE_RECORD = '108';	//备案
	this.PRESALE_BACKUP = '1237';	//
	this.TIP_RECORD = '109';			//内部提示性备注
	this.JUDICIAL_TRANSFER = '110';	//司法裁定过户
	this.ATTACH='107';//查封
	this.MORT='101';//抵押
	this.MORT_CAN_REG='1236';//抵押注销
	this.MAX_MORT_CAN_REG='1243';//
	this.ALL='102';//所有权
	this.DEMURRER='106';//异议登记
	this.USE='103';//使用权
	this.CORRECTION ='111';//房地产更正登记
	this.REALESTATE_CAN='112';//房地产地证注销
	this.REISSUE='113';//补换发产权证登记
	this.REVOKEAPPROVAL='114';
	
	//房地产权详情查询分类标识,适用于Register内容预览的条件判断，跟字典项无关
	this.PHPTY = '1001'; //个人房产信息查询及报表打印
	this.PRESALESRECORD = '1101'//预售备案信息查询
	this.ATTACHBI = '1011';//查封登记信息查询
	this.PREADVICEBI = '1012';//预告登记信息查询
	this.DISSENTBI = '1013';//异议登记信息查询
	this.MORTGAGEBI = '1014';//抵押登记信息查询
	this.OWNERSHIPBI = '1015';//所有权登记信息查询
	this.EASEMENTBI = '1016';//地役权登记信息查询
	this.USERIGHTBI = '1017';//使用权登记信息查询
	this.MAT_CORRECTION='1301';//补正材料流程
	
	
	this.UNDEMURRER='1062';//注销异议登记，用于判断是否为注销异议登记
	
	this.JUDREMARK='1102';     //注销司法裁定过户备注
	this.UNREMARK='1092';     //注销内部提示性备注
	
	this.REJECTJDS='\u9a73\u56de\u53ca\u5ba1\u6279\u8868';        //驳回登记决定书
	this.DEFERMENTJDS='\u6682\u7f13\u53ca\u5ba1\u6279\u8868';     //暂缓登记决定书
	this.DELAYSQS='\u5ef6\u671f\u53ca\u5ba1\u6279\u8868';         //延期申请表
	this.HANGUPSQS='\u6302\u8d77\u53ca\u5ba1\u6279\u8868';        //挂起申请表
	this.BACKLANGUAGESQS='\u9000\u6587\u53ca\u5ba1\u6279\u8868';  //退文及审批表
	
	this.REJECTJDSCODE='REJECTJDS';        //驳回登记决定书编码
	this.DEFERMENTJDSCODE='DEFERMENTJDS';     //暂缓登记决定书编码
	this.DELAYSQSCODE='DELAYSQS';         //延期申请表编码
	this.HANGUPSQSCODE='HANGUPSQS';        //挂起申请表编码
	this.BACKLANGUAGESQSCODE='BACKLANGUAGESQS';  //退文申请表编码
	
	
	this.PROCTOBECREATE = "068001";                   //特殊流程‘待创建’
	this.PROCONTHEMARCH = "068002";                   //特殊流程‘进行中’
	this.PROCCOMPLETED = "068003";                    //特殊流程‘已完成’
	
	this.ADD = '1001';
	this.UPDATE = '1002';
	
	this.INSTITUTIONAL_CAPTION='深圳市房地产权交易中心';
	
	/********************材料类型*********************************/
	this.REC_TYPE_FLAG={
		RECEIVAL:'0',		//接件材料
		DISPACH:'1',		//发文材料
		CORRECTION:'2'		//补正材料
	};
	/*******************材料类型end******************************/
	this.SCAN_RECMATERIAL_FOLDER = 'C\:/scanFolder';
}
/**********************************************************************************
*函数名称: pagerFilter
*功能说明: 分页
*参数说明: 
*返 回 值: 
*函数作者: Joyon
*创建日期: 2014-03-01
*修改历史: 
***********************************************************************************/
function pagerFilter(data){
	if (typeof data.length == 'number' && typeof data.splice == 'function'){	// is array
		data = {
			total: data.length,
			rows: data
		}
	}
	var dg = $(this);
	var opts = dg.datagrid('options');
	var pager = dg.datagrid('getPager');
	pager.pagination({
		onSelectPage:function(pageNum, pageSize){
			opts.pageNumber = pageNum;
			opts.pageSize = pageSize;
			pager.pagination('refresh',{
				pageNumber:pageNum,
				pageSize:pageSize
			});
			dg.datagrid('loadData',data);
		}
	});
	if (!data.originalRows){
		data.originalRows = (data.rows);
	}
	var start = (opts.pageNumber-1)*parseInt(opts.pageSize);
	var end = start + parseInt(opts.pageSize);
	data.rows = (data.originalRows.slice(start, end));
	return data;
}





