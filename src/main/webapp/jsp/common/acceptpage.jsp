<%@ page language="java"  pageEncoding="GBK"
	contentType="text/html;charset=GBK" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
		"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%-- <%
	UserInfo userInfo=(UserInfo)session.getAttribute("userInfo");
	String userName=userInfo.getUserName();
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>登记信息</title>
<%@ include file="/base/taglibs.jsp"%>
<%@ include file="/base/prefix.jsp" %>
<script type="text/javascript">
var user = '<%=userName%>';	
var ctx = '${ctx}';
</script>
<script type="text/javascript" src="${ctx}/js/common/acceptpage.js"></script>
</head>
<body class="plui-layout"> --%>
<div class="plui-layout" fit="true">
	<div data-options="region:'center',border:true">	 
        <div class="plui-layout" fit="true">
        <!--   <div data-options="region:'north',border:false"  style="overflow: hidden;">
           
          </div> -->
          <div data-options="region:'center',border:true" style="overflow-x:hidden;">
          <form id="accept_app_form" class="searchrow" method="post">
	          <table id="house" class="edit_tab" style="width: 780px;"><!-- 设置宽度780 -->
					<tr>
						<td class="title bg1" style="width:150px;">办文编号：</td>
						<td class="td_1"><input value="" id="REG_CODE" name="REG_CODE" style="width:200px;" class="plui-validatebox input" disabled="disabled"/></td>
						<td class="title bg1" style="width:150px;">登记类型：</td>
						<td class="td_2"><input class="plui-combodict" value="" classCode="061" id="REG_TYPE" name="REG_TYPE" style="width:200px;" class="plui-validatebox input" disabled="disabled"/></td>
					</tr>
					<tr>
						<td class="title bg2" style="width:150px;">房屋所属区域：</td>
						<td class="td_1"><input class="plui-combodict" value="" classCode="054" id="REG_STATION" name="REG_STATION" style="width:200px;" class="plui-validatebox input" /></td>					
						<td class="title bg2" style="width:150px;">业务描叙：</td>
						<td class="td_2"><input value="" id="BUS_DETAIL" name="BUS_DETAIL" style="width:200px;" class="plui-validatebox input"/></td>
					</tr>
					 <tr>
					     <td class="title bg2" style="width:150px;">业务主办部门：</td>
						 <td class="td_2">
							<input class="plui-combodict" value="" classCode="073" id="DEPARTMENT" name="DEPARTMENT" style="width:150px;" class="plui-validatebox input" />
						</td>	
					</tr> 
				</table>
			</form>
           <table style="width:780px"><!-- 设置宽度780 -->
             <tr>
	             <td>
	                <div data-options="region:'center',border:true" style="border-bottom-color: black;border-bottom-style: solid;border-bottom-width: 2px;height:160px">
	                    <table id="table_house"></table>
	                </div>
	                <div id="optbtnI" style="text-align:right;">
				       <span style="margin:7px 7px 7px 7px;">
				       	   <!-- <button onclick="doSelectRegunit();">选择登记单元</button> -->
				           <!-- <input type="button" onclick="doSelectRegunit();" value="选择登记单元" style="margin-right: 2px;width:100px;height:25px"/> -->
				           <!-- <input type="button" onclick="doSelectRegunit()" value="+" style="margin-right: 2px;width:30px;height:25px"/>
				           <input type="button" onclick="" value="-" style="margin-right: 2px;width:30px;height:25px"/> -->
				             <a class="plui-linkbutton" href="javascript:;" iconCls='icon-add' onclick="doSelectRegunit()" style="margin-right: 1px;"> 新增</a>
				             <a class="plui-linkbutton" href="javascript:;" iconCls='icon-remove' onclick="" style="margin-right: 1px;"> 刪除</a>
				       </span>
			        </div>
	             </td>
             </tr>
             <tr>
	             <td>
	                <div data-options="region:'center',border:true" style="border-bottom-color: black;border-bottom-style: solid;border-bottom-width: 2px;height:170px">
	                    <table id="table_user"></table>
	                </div>
	                <div id="optbtnII" style="text-align:right;">
				       <span style="margin:7px 7px 7px 7px;">
				           <a class="plui-linkbutton" href="javascript:;" iconCls='icon-add' onclick="doUserAdd()" style="margin-right: 1px;"> 新增</a>
				           <a class="plui-linkbutton" href="javascript:;" iconCls='icon-remove' onclick="doUserDelete()" style="margin-right: 1px;"> 刪除</a>
				       </span>
			        </div>
	             </td>
             </tr>
             <tr>
	             <td>
	                <div data-options="region:'center',border:true" style="height:350">
	                   <table id="table_seematerials"></table>
	                </div>
	                <div id="optbtnIII" style="text-align:right;">
				       <span style="margin:7px 7px 7px 7px;">
				           <!-- <input type="button" onclick="doMaterialAdd()" value="+" style="margin-right: 2px;width:30px;height:25px"/>
				           <input type="button" onclick="doMaterialDel()" value="-" style="margin-right: 2px;width:30px;height:25px"/> -->
				           <a class="plui-linkbutton" href="javascript:;" iconCls='icon-add' onclick="doMaterialAdd()" style="margin-right: 1px;"> 新增</a>
				           <a class="plui-linkbutton" href="javascript:;" iconCls='icon-remove' onclick="doMaterialDel()" style="margin-right: 1px;"> 刪除</a>
				       </span>
			        </div>
	             </td>
             </tr>
           </table>
          </div>
          <div data-options="region:'south',border:false"  style="overflow: hidden;">
            <table style="width: 780px;"><!-- 设置宽度780 -->
               <tr>
                  <td style="width: 50%;padding:6px 6px 6px 6px;">
                    <input type="button" onclick="" value="不予受理" style="margin-right: 5px;width:80px;height:25px"/>
                    <input type="button" onclick="" value="作废" style="margin-right: 5px;width:60px;height:25px"/>
                  </td>               
                  <td style="width: 50%;text-align:right;padding:6px 6px 6px 6px;">
                      <input type="button" onclick="comfirmsave()" value="确认受理" style="margin-right: 5px;width:80px;height:25px"/>
                      <input type="button" onclick="" value="保存" style="margin-right: 5px;width:60px;height:25px"/>
                      <input type="button" onclick="" value="提交" style="margin-right: 5px;width:60px;height:25px"/>
                  </td>               
               </tr>
            </table>
          </div>
        </div>
	</div>
</div>
<!-- </body>
</html> -->
