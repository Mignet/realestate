/*********************************************************************************
*文  件  名  称: dict-data.js
*功  能  描  述: 字典项值获取 
*Copyright (c): 深圳道行天下软件技术有限公司
*创    建    人: Joyon
*创  建  日  期： 2014-03-01
*修  改  历  史：
*文  件  版  本： 1.0
**********************************************************************************/


(function(){
	var app_type_dict_data;
	var app_cer_type_dict_data;
	var reg_unit_type_dict_data;
	window.dicts= window.dicts||{
		app_name:'/dxtx_re',
		
		/**********************************************************************************
		*函数名称:getDict_data_by_code
		*功能说明:获取申请人类型字典项值  供修改时 下拉框选择
		*参数说明: 
		*返 回 值: 返回字典项值数组 
		*函数作者: Joyon
		*创建日期: 2014-03-01
		*修改历史: 
		***********************************************************************************/
		getDict_data_by_code: function (code){
			var _dict_data;
			//if(!app_cer_type_dict_data){
				$.ajax({
					url:app_name+'/common/dict!getNewDictByCode.action?code='+code,  
					dataType : 'json',
					type : 'post',
					async:false,
					success : function(data) {
						_dict_data = data;
					}
				});
			//}
			//alert(JSON.stringify(_dict_data));
			return _dict_data;
		},
		/**********************************************************************************
		*函数名称:formatDictitem
		*功能说明:获取申请人类型字典项值  供修改时 下拉框选择
		*参数说明: 
		*返 回 值: 返回字典项值数组 
		*函数作者: Joyon
		*创建日期: 2014-03-01
		*修改历史: 
		***********************************************************************************/
		formatDictitem: function (code){
			var _dict_data;
			//if(!app_cer_type_dict_data){
				$.ajax({
					url:app_name+'/common/dict!getDictitemnameBycode.action?code='+code,  
					dataType : 'json',
					type : 'post',
					async:false,
					success : function(data) {
						_dict_data = data.dictitemvalue;
					}
				});
			//}
			//alert(JSON.stringify(_dict_data));
			return _dict_data;
		},
		/**********************************************************************************
		*函数名称:getApp_type_dict_data
		*功能说明:获取申请人类型字典项值  
		*参数说明: 
		*返 回 值: 返回申请人类型字典项值
		*函数作者: Joyon
		*创建日期: 2014-03-01
		*修改历史: 
		***********************************************************************************/
		getApp_type_dict_data : function(){
		
			return this.getDict_data_by_code('043');
		},
		/**********************************************************************************
		*函数名称:getApp_cer_type_dict_data
		*功能说明:获取申请人证件类型字典项数据
		*参数说明: 
		*返 回 值: 返回申请人证件类型字典项数据
		*函数作者: Joyon
		*创建日期: 2014-03-01
		*修改历史: 
		***********************************************************************************/
		getApp_cer_type_dict_data : function (){
			 return this.getDict_data_by_code('002');
		},
		/**********************************************************************************
		*函数名称:getReg_unit_type_dict_data
		*功能说明:获取登记单元类型字典项数据
		*参数说明: 
		*返 回 值: 返回登记单元类型字典项数据
		*函数作者: Joyon
		*创建日期: 2014-03-01
		*修改历史: 
		***********************************************************************************/
		getReg_unit_type_dict_data : function (){
			 return this.getDict_data_by_code('009');
		},
		
		format:{
			/**********************************************************************************
			*函数名称:app_type_format
			*功能说明:申请人类型字典项格式化
			*参数说明: value 字典项code
			*返 回 值: 返回申请人类型前code的值
			*函数作者: Joyon
			*创建日期: 2014-03-01
			*修改历史: 
			***********************************************************************************/
			app_type_format : function (value){
				if(!app_type_dict_data){
					app_type_dict_data = dicts.getApp_type_dict_data();
				}
				var tmpData = app_type_dict_data;
				for(var i =0;i<tmpData.length;i++){
					if(tmpData[i].value == value){
						return  tmpData[i].name;
					}
				}
			},
			/**********************************************************************************
			*函数名称:app_cer_type_format
			*功能说明:申请人证件类型字典项格式化
			*参数说明: value 字典项code
			*返 回 值: 返回申请人类型前code的值
			*函数作者: Joyon
			*创建日期: 2014-03-01
			*修改历史: 
			***********************************************************************************/
			app_cer_type_format : function(value) {
				if(!app_cer_type_dict_data){
					app_cer_type_dict_data = dicts.getApp_cer_type_dict_data();
				}
				var tmpData = app_cer_type_dict_data;
				for(var i =0;i<tmpData.length;i++){
					if(tmpData[i].value == value){
						return  tmpData[i].name;
					}
				}
			},
			/**********************************************************************************
			*函数名称:app_cer_type_format
			*功能说明:申请人证件类型字典项格式化
			*参数说明: value 字典项code
			*返 回 值: 返回申请人类型前code的值
			*函数作者: Joyon
			*创建日期: 2014-03-01
			*修改历史: 
			***********************************************************************************/
			reg_unit_type_format : function(value) {
				if(!reg_unit_type_dict_data){
					reg_unit_type_dict_data = dicts.getReg_unit_type_dict_data();
				}
				var tmpData = reg_unit_type_dict_data;
				if(tmpData){
					for(var i =0;i<tmpData.length;i++){
						if(tmpData[i].value == value){
							return  tmpData[i].name;
						}
					}
				}else{
					return value;
				}
			}
		}
	}
})()




