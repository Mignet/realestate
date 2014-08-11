<%@ page language="java" import="java.util.*" pageEncoding="GBK" contentType="text/html;charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
		"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>常规布局</title>
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
        
        <div id="cleft" data-options="region:'west',title:'编号信息'" style="width:203px;height:600px">
        	
        	 <ul id="identifier_tree" ></ul>  
			    <div id="mm" class="plui-menu" style="width:120px;">  
			        <div onClick="append()" data-options="iconCls:'icon-add'">添加</div>  
			        <div onClick="remove()" data-options="iconCls:'icon-remove'">删除</div>  
			        <div class="menu-sep"></div>  
			        <div onClick="expand()">展开</div>  
			        <div onClick="collapse()">收缩</div>  
			    </div>  
        </div>
        <div data-options="region:'center',title:'编号详细信息'" style="text-align:center">
        	<div id="cur_rule">
	       	</div>
        	<div id="div_tabs">
	       		
	       		
	       		
	        		<div id="divhousehold" title="户籍">
	        			<input type="checkbox" id="iptHousehold" value="household" name="household" onclick="userHousehold(this)"/>是否使用户籍(深户9c  非深户9d)
	        		</div>
	        		
	        		<div id="divChar" title="常用字">
	        			常用字<input type="text" id="iptChar"/>
	        			<!-- 
	        			<input id="btnCharAdd" type="button" value="添加"/><input id="btnCharModify" type="button" value="修改" style="display:none;"/>
	        			 -->
	        			<a id="btnCharAdd" iconCls="icon-edit_add" href="#" class="plui-linkbutton">添加</a>
	        			<a id="btnCharModify" iconCls="icon-edit" href="#" class="plui-linkbutton" style="display:none;">修改</a>
	        		</div>
	        	
	        		<div id="area" title="区域">
	        			<input id="iptArea" type="checkbox" value="area" name="area" onclick="userArea(this)"/>是否使用区号
	        		</div>
	        		
	        		<div id="div_year" title="年份">
	        		<!-- 
	        			<input type="checkbox" id="use_year"/> <label for="use_year">是否使用年份</label><br></br>
	        		 -->
	        			<input type="radio" id="ra_y2" name="ra_year" value="yy"  onclick="appendYear(this.value)"/>年份2位
	        			<input type="radio" id="ra_y4" name="ra_year" value="yyyy"  onclick="appendYear(this.value)"/>年份4位
	        			<input type="radio" id="ra_ymd" name="ra_year" value="yyyymmdd"  onclick="appendYear(this.value)"/>年月日yyyymmdd
	        			<input type="radio" id="ra_ymd1" name="ra_year" value="yyyy-mm-dd"  onclick="appendYear(this.value)"/>年月日yyyy-mm-dd
	        		</div>
	        		<div id="squence" title="流水号" style="padding-left:20%;padding-top:20px;">
						<input id="input_squence"   style="width:80px;"></input>
	        		</div>
        		
        	</div>
        	<div style="position:absolute;bottom:10px;width:100%">
        	<!-- 
        		<input value="保存规则" type="button" onclick="saveRule();" style="text-align:center;"/>
        	-->
        		<a id="btnCharAdd" iconCls="icon-save" href="#" class="plui-linkbutton" onclick="saveRule();">保存</a>
        	</div>
        </div>
        
        <div data-options="region:'east',title:'排序'" style="width:150px;text-align:center">
        	
        	<div id="div_btn">
        		<a id="btnMoveUp" iconCls="icon-accordion_up" href="#" class="plui-linkbutton" onclick="upLi();">上移</a>
        		<a id="btnMoveDown" iconCls="icon-accordion_down" href="#" class="plui-linkbutton" onclick="downLi();">下移</a>
        		<a id="btnDelSortli" iconCls="icon-no" href="#" class="plui-linkbutton" onclick="delLi();">删除</a>
        	
        	<!-- 
	        	<input id="btnMoveUp" type="button" value="上移" onclick="upLi()" />
				<input id="btnMoveDown" type="button" value="下移" onclick="downLi()" />
				<input id="btnDelSortli" type="button" value="删除" onclick="delLi()"/>
			-->
        	</div>
        	
        	<div class="nav" id="div_sort" >
        		<ul id="ulTree" >
        		</ul>
        	</div>
						
        </div>
    </body>
</html>

