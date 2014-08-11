/*********************************************************************************
*��  ��  ��  ��: dict-data.js
*��  ��  ��  ��: �ֵ���ֵ��ȡ 
*Copyright (c): ���ڵ�����������������޹�˾
*��    ��    ��: Joyon
*��  ��  ��  �ڣ� 2014-03-01
*��  ��  ��  ʷ��
*��  ��  ��  ���� 1.0
**********************************************************************************/


(function(){
	var app_type_dict_data;
	var app_cer_type_dict_data;
	var reg_unit_type_dict_data;
	window.dicts= window.dicts||{
		app_name:'/dxtx_re',
		
		/**********************************************************************************
		*��������:getDict_data_by_code
		*����˵��:��ȡ�����������ֵ���ֵ  ���޸�ʱ ������ѡ��
		*����˵��: 
		*�� �� ֵ: �����ֵ���ֵ���� 
		*��������: Joyon
		*��������: 2014-03-01
		*�޸���ʷ: 
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
		*��������:formatDictitem
		*����˵��:��ȡ�����������ֵ���ֵ  ���޸�ʱ ������ѡ��
		*����˵��: 
		*�� �� ֵ: �����ֵ���ֵ���� 
		*��������: Joyon
		*��������: 2014-03-01
		*�޸���ʷ: 
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
		*��������:getApp_type_dict_data
		*����˵��:��ȡ�����������ֵ���ֵ  
		*����˵��: 
		*�� �� ֵ: ���������������ֵ���ֵ
		*��������: Joyon
		*��������: 2014-03-01
		*�޸���ʷ: 
		***********************************************************************************/
		getApp_type_dict_data : function(){
		
			return this.getDict_data_by_code('043');
		},
		/**********************************************************************************
		*��������:getApp_cer_type_dict_data
		*����˵��:��ȡ������֤�������ֵ�������
		*����˵��: 
		*�� �� ֵ: ����������֤�������ֵ�������
		*��������: Joyon
		*��������: 2014-03-01
		*�޸���ʷ: 
		***********************************************************************************/
		getApp_cer_type_dict_data : function (){
			 return this.getDict_data_by_code('002');
		},
		/**********************************************************************************
		*��������:getReg_unit_type_dict_data
		*����˵��:��ȡ�Ǽǵ�Ԫ�����ֵ�������
		*����˵��: 
		*�� �� ֵ: ���صǼǵ�Ԫ�����ֵ�������
		*��������: Joyon
		*��������: 2014-03-01
		*�޸���ʷ: 
		***********************************************************************************/
		getReg_unit_type_dict_data : function (){
			 return this.getDict_data_by_code('009');
		},
		
		format:{
			/**********************************************************************************
			*��������:app_type_format
			*����˵��:�����������ֵ����ʽ��
			*����˵��: value �ֵ���code
			*�� �� ֵ: ��������������ǰcode��ֵ
			*��������: Joyon
			*��������: 2014-03-01
			*�޸���ʷ: 
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
			*��������:app_cer_type_format
			*����˵��:������֤�������ֵ����ʽ��
			*����˵��: value �ֵ���code
			*�� �� ֵ: ��������������ǰcode��ֵ
			*��������: Joyon
			*��������: 2014-03-01
			*�޸���ʷ: 
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
			*��������:app_cer_type_format
			*����˵��:������֤�������ֵ����ʽ��
			*����˵��: value �ֵ���code
			*�� �� ֵ: ��������������ǰcode��ֵ
			*��������: Joyon
			*��������: 2014-03-01
			*�޸���ʷ: 
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




