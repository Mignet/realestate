
	//主键值数组，记录复选框选中的行（包括翻页）主键值
window.seq_id_set = [];
var id;
var bustypeid;
var parentid;
var add=0;
var nodes;

var selectedNode;//选中的左侧菜单业务类型
$(function(){
	//初始化产品树
	var productTree = $('#getProcName').tree({
		url : ctx+"/common/certificate!getProMatter.action",
		
		onLoadSuccess : function() {
			/*if(add==0)
			{
				//$('#getProcName').tree('collapseAll');
			}*/
		},
		onSelect : function(node) {
			$('#table_user').datagrid('load',{'nodeid':node.id,'bus_typeid':node.iconCls});
			var node = $('#getProcName').tree('getSelected');
			nodes=node;
			id=node.id;
			bustypeid = node.iconCls;
			parentid=node.parent_id;
			if(node.id && !node.children){
			var objs={
					url:ctx+"/common/certificate!getMatter.action",
					type:'post',
					data:{"id":id},
					success:function(data){
						data=JSON.parse(data);
						if(data)
						{
							$('#name').val(data.name);
							$('#sort').val(data.sort);
							$('#cc').combobox('setValue',data.proc_id);
							$('#pro_type').combodict('setValue',data.pro_type);
							$('#submit').linkbutton('enable');
						}
					}
				};
				$.ajax(objs);
			}
			$('#submit').linkbutton('disable');
		},
		animate: true,
		onContextMenu: function(e,node){  
			                e.preventDefault();  
			                $(this).tree('select',node.target);  
			                $('#mm').menu('show',{  
			                	left: e.pageX,  
			                	top: e.pageY
			                });
			                
			            }
	});
	$('#cc').combobox({     
		   url:ctx+"/common/certificate!queryProcessdef.action",
		   valueField:'procdefId',     
		   textField:'name',
	   });
});

function append(){
	var t = $('#getProcName');  
    var node = t.tree('getSelected');
var objs={					
			url:ctx+"/common/certificate!saveMatter.action",
			type:'post',
			data:{"name":"新增节点","parentid":node.iconCls},
			success:function(data){
				data=JSON.parse(data);
				 t.tree('append', {  
			            parent: (node?node.target:null),  
			            data: [{  
			                text: '新增节点',
			                parent_id:data.parent_id,
			                iconCls:data.bus_type_id,
			                id:data.id,	
			            }]  
			        });  
			}
		};
		$.ajax(objs);
		add=1;
}  
function removeIt(){
    var node = $('#getProcName').tree('getSelected');
    	var objs={					
    			url:ctx+"/common/certificate!deleteMatter.action",
    			type:'post',
    			data:{"id":node.id},
    			success:function(data){
    				if(data==1)
    				{
    					$('#getProcName').tree('remove', node.target);
    				}
    				else
    				{
    					alert("存在子节点，不予许删除！");
    				}
    					
    			}
    		};
    		$.ajax(objs);
}  
function updateIt(){
	var t = $('#getProcName');
    var objs={					
			url:ctx+"/common/certificate!updateMatter.action",
			type:'post',
			data:{"id":id,"bustypeid":bustypeid,"parentid":parentid,"name":$('#name').val(),"procid":$('#cc').combobox('getValue'),"protype":$('#pro_type').combodict('getValue'),"sort":$('#sort').val()},
			success:function(data){
				if(data==1)
				{
					alert("保存成功！");
					t.tree('update', {
						target: nodes.target,
						text: $('#name').val(),
					});

				}
			}
		};
		$.ajax(objs);
}  


