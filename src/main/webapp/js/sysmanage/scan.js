/*
* 初始化扫描控件
* 设置文档类型
*/
var selectNode;
var proc_id;											//流程ID
var proc_def_id;										//流程定义ID
var reg_code;										    //登记编号
var bus_id;												//业务ID
var proc_name;											//流程业务名
function init(proc){
	proc_id = proc.procId;
	var scaner = document.getElementById("NKOSmartScan");
	if(scaner != null){
		var obj = {
				   url: ctx + '/sysmanage/my-scanner!getBusIdByProcId.action',
			       dataType:'json',
				   contentType:"application/x-www-form-urlencoded; charset=GBK",
				   data:{"proc_id":proc_id,"time":new Date()},
				   success:function(data){
					   if(data){
						   bus_id = data.bus_id;
						   reg_code = data.reg_code;
						   proc_name = data.proc_name;
						   if(bus_id){
							   var obj3 = {
									   url: ctx + '/sysmanage/my-scanner!createBusScanner.action',
									   dataType:'json',
									   contentType:"application/x-www-form-urlencoded; charset=GBK",
									   data:{"bus_id":bus_id,"reg_code":reg_code,"time":new Date()},
									   success:function(dataIII){
										   // do nothing
									   },
									   error:function(msg){
										   top.$.messager.alert('新增扫描纪录错误',msg.errorMessage,'error');
									   }
							   };
							   
							   var obj2 = {
									   url: ctx + '/sysmanage/my-scanner!getRecmaterialMapLstByRegCode.action',
									   dataType:'json',
									   contentType:"application/x-www-form-urlencoded; charset=GBK",
									   data:{"bus_id":bus_id,"reg_code":reg_code,"time":new Date()},
									   success:function(dataII){
										   if(dataII){
											    	scaner.thumbnailsCtrl.InitRootFolderOpertion(reg_code, proc_name);
											    	var scanDocs = dataII;
												    scanDocs = SerializeObject(scanDocs);
											        scaner.thumbnailsCtrl.scanSet.AddDcumentType(scanDocs);
											        //创建文档树
											        scaner.thumbnailsCtrl.CreateTree();
											        scaner.thumbnailsCtrl.refreshFolderTree(); 	
											        $.ajax(obj3);
											    }
										   }
									   
							   };
							   $.ajax(obj2);
							   //end if(bus_id)
						   }
						   
					   }
				   }
				
		};
		$.ajax(obj);
		//end if(scaner != null)
	}
}

/* js array serialize to string
* split with */

function SerializeObject(ary) {
    var newAry = new Array();
    if (ary != null && ary.length > 0) {
        for (var i = 0; i < ary.length; i++) {
            newAry.push($.toJSON(ary[i]));
        }
    }
    return newAry.join("*");
}
/*$(function(){
	 var isscaner = document.getElementById("NKOSmartScan");
	 if (isscaner != null) {
	        $.ajax({
				url: ctx + '/json/scan_20141712.json',
				success: function(data){
					if(data){
						    var scaner = document.getElementById("NKOSmartScan");
						    if(scaner != null){
						    	scaner.thumbnailsCtrl.InitRootFolderOpertion("root", "章节");
						    	var scanDocs = eval('(' + data + ')');
								alert(scanDocs);
							    scanDocs = data.parseObject();
							    alert(scanDocs);
							    scanDocs = JSON.parse(data);
							    alert(scanDocs);
							    scanDocs = JQuery.parseJSON(data);
							    alert(scanDocs);
							    scanDocs = SerializeObject(scanDocs);
						        scaner.thumbnailsCtrl.scanSet.AddDcumentType(scanDocs);
						        //创建文档树
						        scaner.thumbnailsCtrl.CreateTree();
						        scaner.thumbnailsCtrl.refreshFolderTree(); 	
						    }
					}							
				}
			});
		 $.ajax({
		       url: ctx + '/sysmanage/my-scanner!getScanTreeData.action?time=' + new Date(),
		       dataType:'json',
			   contentType:"application/x-www-form-urlencoded; charset=GBK",
			   data:{},
			   success:function(data){
				   if(data){
					    var scaner = document.getElementById("NKOSmartScan");
					    if(scaner != null){
					    	scaner.thumbnailsCtrl.InitRootFolderOpertion("root42", "章节");
					    	var scanDocs = data;
					    	var scanDocs = eval('(' + data + ')');
							alert(scanDocs);
						    scanDocs = data.parseObject();
						    alert(scanDocs);
						    scanDocs = JSON.parse(data);
						    alert(scanDocs);
						    scanDocs = JQuery.parseJSON(data);
						    alert(scanDocs);
						    scanDocs = SerializeObject(scanDocs);
					        scaner.thumbnailsCtrl.scanSet.AddDcumentType(scanDocs);
					        //创建文档树
					        scaner.thumbnailsCtrl.CreateTree();
					        scaner.thumbnailsCtrl.refreshFolderTree(); 	
					    }
				}							
			   },
			   error:function(msg){
				   alert(msg);
			   }
		 });
	 }
	 

	    
	    * js array serialize to string
	    * split with *
	    
	    function SerializeObject(ary) {
	        var newAry = new Array();
	        if (ary != null && ary.length > 0) {
	            for (var i = 0; i < ary.length; i++) {
	                newAry.push($.toJSON(ary[i]));
	            }
	        }
	        return newAry.join("*");
	    }
});*/


