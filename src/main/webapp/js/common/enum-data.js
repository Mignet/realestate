/*********************************************************************************
*��  ��  ��  ��: enum-data.js
*��  ��  ��  ��: ö������,����ǰ̨JS�Ƚ��ж� 
*Copyright (c): ���ڵ�����������������޹�˾
*��    ��    ��: xuzz
*��  ��  ��  �ڣ� 2014-03-27
*��  ��  ��  ʷ��
*��  ��  ��  ���� 1.0
**********************************************************************************/

window.enumdata = new enumdata();							//����ȫ�ֱ���
var app_name = '/dxtx_re';							//��ǰ����ҪĿ¼
function enumdata(){

/********************************************�Ǽǵ�Ԫ����***********************************************/
	this.house='009001';
	this.parcel='009003';
	this.build='009002';
/********************************************�������***********************************************/
	this.attach='066001';   //���
	this.reattach='066002';  //����
	this.lastattach='066003'; //�ֺ���
	this.chattach='066004';  //�ֺ���ת���
	
/********************************************�������***********************************************/
	this.unattach='067001';   //���
	this.unreattach='067002';  //����ֺ���
	this.unchattach='067003';  //�ֺ���ת���
	

	
	/********************************************�ֵ�����***********************************************/
	
	this.bustype='061';  //�Ǽ�����
	this.regstation='054';  //�Ǽǵ�
	/********************************************�ڵ�����***********************************************/
	this.ACCEPTED='����';
	this.EXAMINE='����';
	this.REEXAMINE='����';
	this.APPROVED='��׼';
	this.BULLETIN='����';
	this.INITIALEXAMINE='�������';
	this.INITIALAUDIT='�������';
	this.INITIALVALIDATION='������';
	this.CHARGE='�շ�';
	this.CERTIFICATE='��֤';
	this.POSTING='����';
	this.FILE='�鵵';
	
	/********************************************�Ǽ�����***********************************************/
	this.remark='1091';   //�ڲ���ʾ�Ա�ע
	this.unremark='1092';  //ע���ڲ���ʾ�Ա�ע
	
	this.PRE_SALE_RECORD = '108';	//����
	this.PRESALE_BACKUP = '1237';	//
	this.TIP_RECORD = '109';			//�ڲ���ʾ�Ա�ע
	this.JUDICIAL_TRANSFER = '110';	//˾���ö�����
	this.ATTACH='107';//���
	this.MORT='101';//��Ѻ
	this.MORT_CAN_REG='1236';//��Ѻע��
	this.MAX_MORT_CAN_REG='1243';//
	this.ALL='102';//����Ȩ
	this.DEMURRER='106';//����Ǽ�
	this.USE='103';//ʹ��Ȩ
	this.CORRECTION ='111';//���ز������Ǽ�
	this.REALESTATE_CAN='112';//���ز���֤ע��
	this.REISSUE='113';//��������Ȩ֤�Ǽ�
	this.REVOKEAPPROVAL='114';
	
	//���ز�Ȩ�����ѯ�����ʶ,������Register����Ԥ���������жϣ����ֵ����޹�
	this.PHPTY = '1001'; //���˷�����Ϣ��ѯ�������ӡ
	this.PRESALESRECORD = '1101'//Ԥ�۱�����Ϣ��ѯ
	this.ATTACHBI = '1011';//���Ǽ���Ϣ��ѯ
	this.PREADVICEBI = '1012';//Ԥ��Ǽ���Ϣ��ѯ
	this.DISSENTBI = '1013';//����Ǽ���Ϣ��ѯ
	this.MORTGAGEBI = '1014';//��Ѻ�Ǽ���Ϣ��ѯ
	this.OWNERSHIPBI = '1015';//����Ȩ�Ǽ���Ϣ��ѯ
	this.EASEMENTBI = '1016';//����Ȩ�Ǽ���Ϣ��ѯ
	this.USERIGHTBI = '1017';//ʹ��Ȩ�Ǽ���Ϣ��ѯ
	this.MAT_CORRECTION='1301';//������������
	
	
	this.UNDEMURRER='1062';//ע������Ǽǣ������ж��Ƿ�Ϊע������Ǽ�
	
	this.JUDREMARK='1102';     //ע��˾���ö�������ע
	this.UNREMARK='1092';     //ע���ڲ���ʾ�Ա�ע
	
	this.REJECTJDS='\u9a73\u56de\u53ca\u5ba1\u6279\u8868';        //���صǼǾ�����
	this.DEFERMENTJDS='\u6682\u7f13\u53ca\u5ba1\u6279\u8868';     //�ݻ��ǼǾ�����
	this.DELAYSQS='\u5ef6\u671f\u53ca\u5ba1\u6279\u8868';         //���������
	this.HANGUPSQS='\u6302\u8d77\u53ca\u5ba1\u6279\u8868';        //���������
	this.BACKLANGUAGESQS='\u9000\u6587\u53ca\u5ba1\u6279\u8868';  //���ļ�������
	
	this.REJECTJDSCODE='REJECTJDS';        //���صǼǾ��������
	this.DEFERMENTJDSCODE='DEFERMENTJDS';     //�ݻ��ǼǾ��������
	this.DELAYSQSCODE='DELAYSQS';         //������������
	this.HANGUPSQSCODE='HANGUPSQS';        //������������
	this.BACKLANGUAGESQSCODE='BACKLANGUAGESQS';  //������������
	
	
	this.PROCTOBECREATE = "068001";                   //�������̡���������
	this.PROCONTHEMARCH = "068002";                   //�������̡������С�
	this.PROCCOMPLETED = "068003";                    //�������̡�����ɡ�
	
	this.ADD = '1001';
	this.UPDATE = '1002';
	
	this.INSTITUTIONAL_CAPTION='�����з��ز�Ȩ��������';
	
	/********************��������*********************************/
	this.REC_TYPE_FLAG={
		RECEIVAL:'0',		//�Ӽ�����
		DISPACH:'1',		//���Ĳ���
		CORRECTION:'2'		//��������
	};
	/*******************��������end******************************/
	this.SCAN_RECMATERIAL_FOLDER = 'C\:/scanFolder';
}
/**********************************************************************************
*��������: pagerFilter
*����˵��: ��ҳ
*����˵��: 
*�� �� ֵ: 
*��������: Joyon
*��������: 2014-03-01
*�޸���ʷ: 
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





