<%@ page language="java" import="java.util.*" pageEncoding="GBK" contentType="text/html;charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
		"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>���沼��</title>
        <%@ include file="/base/taglibs.jsp"%>
		<%@ include file="/base/prefix.jsp" %>
		        
       
        
        
        
       <style type="text/css" >
			.nav {
				width:100%;	
			}
			.nav ul{
				list-style:none;
				width:120px;
			}
			.nav ul li{
				padding:4px 0px;
				width:100%;
				text-align:left;
				
			}
			.nav ul li:hover{
				background-color:#EEE;
			}
			.nav ul li.selected{
				background-color:#D3E0F8;
			}
			h3{
				text-align:center;
			}
			
			.ulSy {
				width:160px;	
			}
			
			.ulSy ul{
				list-style:none;
			}
			.ulSy ul ol{
				padding:4px 0px;
			}
			.ulSy ul ol:hover{
				background-color:#EEE;
			}
			.ulSy ul ol.selected{
				background-color:#D3E0F8;
			}
			
		</style>
        
        <script>	
        	var ctx ='${ctx}';
        </script>
        <script type="text/javascript" src="${ctx}/js/common/identifier.js"></script>
    </head>
    <body style="width:100%;height:100%;">    	        
        
        <div id="cleft" data-options="region:'west',title:'�����Ϣ'" style="width:203px;height:600px">
        	
        	 <ul id="identifier_tree" ></ul>  
			    <div id="mm" class="plui-menu" style="width:120px;">  
			        <div onClick="append()" data-options="iconCls:'icon-add'">���</div>  
			        <div onClick="remove()" data-options="iconCls:'icon-remove'">ɾ��</div>  
			        <div class="menu-sep"></div>  
			        <div onClick="expand()">չ��</div>  
			        <div onClick="collapse()">����</div>  
			    </div>  
        </div>
        <div data-options="region:'center',title:'�����ϸ��Ϣ'" style="text-align:center">
        	<div id="cur_rule">
	       	</div>
        	<div id="div_tabs">
	       		
	       		
	       		
	        		<div id="divhousehold" title="����">
	        			<input type="checkbox" id="iptHousehold" value="household" name="household" onclick="userHousehold(this)"/>�Ƿ�ʹ�û���(�9c  ���9d)
	        		</div>
	        		
	        		<div id="divChar" title="������">
	        			������<input type="text" id="iptChar"/>
	        			<!-- 
	        			<input id="btnCharAdd" type="button" value="���"/><input id="btnCharModify" type="button" value="�޸�" style="display:none;"/>
	        			 -->
	        			<a id="btnCharAdd" iconCls="icon-edit_add" href="#" class="plui-linkbutton">���</a>
	        			<a id="btnCharModify" iconCls="icon-edit" href="#" class="plui-linkbutton" style="display:none;">�޸�</a>
	        		</div>
	        	
	        		<div id="area" title="����">
	        			<input id="iptArea" type="checkbox" value="area" name="area" onclick="userArea(this)"/>�Ƿ�ʹ������
	        		</div>
	        		
	        		<div id="div_year" title="���">
	        		<!-- 
	        			<input type="checkbox" id="use_year"/> <label for="use_year">�Ƿ�ʹ�����</label><br></br>
	        		 -->
	        			<input type="radio" id="ra_y2" name="ra_year" value="yy"  onclick="appendYear(this.value)"/>���2λ
	        			<input type="radio" id="ra_y4" name="ra_year" value="yyyy"  onclick="appendYear(this.value)"/>���4λ
	        			<input type="radio" id="ra_ymd" name="ra_year" value="yyyymmdd"  onclick="appendYear(this.value)"/>������yyyymmdd
	        			<input type="radio" id="ra_ymd1" name="ra_year" value="yyyy-mm-dd"  onclick="appendYear(this.value)"/>������yyyy-mm-dd
	        		</div>
	        		<div id="squence" title="��ˮ��" style="padding-left:20%;padding-top:20px;">
						<input id="input_squence"   style="width:80px;"></input>
	        		</div>
        		
        	</div>
        	<div style="position:absolute;bottom:10px;width:100%">
        	<!-- 
        		<input value="�������" type="button" onclick="saveRule();" style="text-align:center;"/>
        	-->
        		<a id="btnCharAdd" iconCls="icon-save" href="#" class="plui-linkbutton" onclick="saveRule();">����</a>
        	</div>
        </div>
        
        <div data-options="region:'east',title:'����'" style="width:150px;text-align:center">
        	
        	<div id="div_btn">
        		<a id="btnMoveUp" iconCls="icon-accordion_up" href="#" class="plui-linkbutton" onclick="upLi();">����</a>
        		<a id="btnMoveDown" iconCls="icon-accordion_down" href="#" class="plui-linkbutton" onclick="downLi();">����</a>
        		<a id="btnDelSortli" iconCls="icon-no" href="#" class="plui-linkbutton" onclick="delLi();">ɾ��</a>
        	
        	<!-- 
	        	<input id="btnMoveUp" type="button" value="����" onclick="upLi()" />
				<input id="btnMoveDown" type="button" value="����" onclick="downLi()" />
				<input id="btnDelSortli" type="button" value="ɾ��" onclick="delLi()"/>
			-->
        	</div>
        	
        	<div class="nav" id="div_sort" >
        		<ul id="ulTree" >
        		</ul>
        	</div>
						
        </div>
    </body>
</html>

