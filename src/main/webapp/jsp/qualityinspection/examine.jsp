<%@ page language="java" import="java.util.*" pageEncoding="GBK"
	contentType="text/html;charset=GBK"%>
	

<div class="plui-layout" fit='true'>
<div data-options="region:'center'">
        <div class="page_con"  style="width:800px">
<!--  	<div class="plui-panel" data-options="fit:fasle,border:true" style="background-color:gray">-->

		<form id="add_user_form" method="post" >	
		<!-- �������1 -->
		<div style="background-color: rgb(224, 236, 255);line-height:18px;" class="panel-header">
		 &nbsp;<label style="font-size: 12px;font-weight: bold;" id='handling_suggestion01' >��������������</label>
		</div> 
		<div class="datagrid-wrap panel-body">
			<table  >
				<tr>
					<th colspan="8" scope="col" style="width:auto">
					<textarea colspan="8" id="csyj"
							name="csyj" style="height:100px;width:700px" ></textarea>
					</th>
					<th width="65" scope="col">
							<input type="button" name="Submit" id="cscyy1" value="������" onclick="selectCyy()"/>
						</th>
				</tr>
				<tr >
					<th width="123" scope="row">&nbsp;</th>
					<td width="71">&nbsp;</td>
					<td width="69">&nbsp;</td>
					<td width="76">&nbsp;</td>
					<td width="50" sytle="text-align:right">ǩ����</td>
					<td width="107"><input type="text" name="csr" id="csr"
						class="plui-validatebox input" data-options="required:true"
						style="width:140px;" value="" disabled="disabled"/>
					</td>
					<td width="78" sytle="text-align:right">ǩ�����ڣ�</td>
					<td width="84"><input id="cssj" name="cssj" disabled="disabled" 
						style="width:140px;" />
					</td>
					<td>&nbsp;</td>
				</tr>
			</table>
			</div>
		
		<!-- ������� -->	
			 <div style="background-color: rgb(224, 236, 255);line-height:18px;" class="panel-header">
		 &nbsp;<label style="font-size: 12px;font-weight: bold;" id='handling_suggestion02'>���Ҹ���������</label>
		</div> 
		  <div class="datagrid-wrap panel-body">
			<table >
				<tr>
					<th colspan="8" scope="col" style="width:auto">
					<textarea colspan="8" id="fsyj" 
							name="fsyj" style="height:100px;width:700px"></textarea>
					</th>
					<th width="65" scope="col">
							<input type="button" name="Submit"  id="fscyy" value="������" onclick="selectCyy()"/>
						</th>
				</tr>
				<tr >
					<th width="123" scope="row">&nbsp;</th>
					<td width="71">&nbsp;</td>
					<td width="69">&nbsp;</td>
					<td width="76">&nbsp;</td>
					<td width="50" sytle="text-align:right">ǩ����</td>
					<td width="107"><input type="text" name="fsr" id="fsr"
						class="plui-validatebox input" data-options="required:true"
						style="width:140px;" disabled="disabled"/>
					</td>
					<td width="78" sytle="text-align:right">ǩ�����ڣ�</td>
					<td width="84"><input id="fssj" name="fssj" disabled="disabled"
						style="width:140px;" />
					</td>
					<td>&nbsp;</td>
				</tr>
			</table>
         </div>
         <!-- ������1 -->
         <div id="handling_opinions01" style="display:block;">
	          <div style="background-color: rgb(224, 236, 255);line-height:18px;" class="panel-header">
			    &nbsp;<label style="font-size: 12px;font-weight: bold;" id='handling_suggestion03'>������</label>
			  </div> 
			  <div class="datagrid-wrap panel-body">
				<table >
					<tr>
						<th colspan="8" scope="col" style="width:auto">
						<textarea colspan="8" id="shyj1"
								name="shyj1" style="height:100px;width:700px"></textarea>
						</th>
						<th width="65" scope="col">
								<input type="button" name="Submit"  id="shcyy1" value="������" onclick="selectCyy()"/>
							</th>
					</tr>
					<tr >
						<th width="123" scope="row">&nbsp;</th>
						<td width="71">&nbsp;</td>
						<td width="69">&nbsp;</td>
						<td width="76">&nbsp;</td>
						<td width="50" sytle="text-align:right">ǩ����</td>
						<td width="107"><input type="text" name="shr1" id="shr1"
							class="plui-validatebox input" data-options="required:true"
							style="width:140px;" disabled="disabled"/>
						</td>
						<td width="78" sytle="text-align:right">ǩ�����ڣ�</td>
						<td width="84"><input id="shsj1" name="shsj1" disabled="disabled"
							style="width:140px;" />
						</td>
						<td>&nbsp;</td>
					</tr>
				</table>
	         </div>
         </div>
         <!-- ��׼���1 -->
          <div id="handling_opinions02" style="display:none;">
	         <div style="background-color: rgb(224, 236, 255);line-height:18px;" class="panel-header">
			 &nbsp;<label style="font-size: 12px;font-weight: bold;" id='handling_suggestion04'>������</label>
			 </div> 
			  <div class="datagrid-wrap panel-body">
				<table >
					<tr>
						<th colspan="8" scope="col" style="width:auto">
						<textarea colspan="8" id="hzyj"
								name="hzyj" style="height:100px;width:700px"></textarea>
						</th>
						<th width="65" scope="col">
								<input type="button" name="Submit"  id="hzcyy" value="������" onclick="selectCyy()"/>
							</th>
					</tr>
					<tr >
						<th width="123" scope="row">&nbsp;</th>
						<td width="71">&nbsp;</td>
						<td width="69">&nbsp;</td>
						<td width="76">&nbsp;</td>
						<td width="50" sytle="text-align:right">ǩ����</td>
						<td width="107"><input type="text" name="hzr" id="hzr"
							class="plui-validatebox input" data-options="required:true"
							style="width:140px;" disabled="disabled"/>
						</td>
						<td width="78" sytle="text-align:right">ǩ�����ڣ�</td>
						<td width="84"><input id="hzsj" name="hzsj" disabled="disabled"
							style="width:140px;" />
						</td>
						<td>&nbsp;</td>
					</tr>
				</table>
	         </div>
	        </div>
           <!-- һ������ -->
         <div id="handling_opinions03" style="display:block;">
	          <div style="background-color: rgb(224, 236, 255);line-height:18px;" class="panel-header">
			    &nbsp;<label style="font-size: 12px;font-weight: bold;" >һ������</label>
			  </div> 
			  <div class="datagrid-wrap panel-body">
				<table >
					<tr>
						<th colspan="8" scope="col" style="width:auto">
						<textarea colspan="8" id="yjspyj"
								name="yjspyj" style="height:100px;width:700px"></textarea>
						</th>
						<th width="65" scope="col">
								<input type="button" name="Submit"  id="yjspcyy" value="������" onclick="selectCyy()"/>
							</th>
					</tr>
					<tr >
						<th width="123" scope="row">&nbsp;</th>
						<td width="71">&nbsp;</td>
						<td width="69">&nbsp;</td>
						<td width="76">&nbsp;</td>
						<td width="50" sytle="text-align:right">ǩ����</td>
						<td width="107"><input type="text" name="yjspr" id="yjspr"
							class="plui-validatebox input" data-options="required:true"
							style="width:140px;" disabled="disabled"/>
						</td>
						<td width="78" sytle="text-align:right">ǩ�����ڣ�</td>
						<td width="84"><input id="yjspsj" name="yjspsj" disabled="disabled"
							style="width:140px;" />
						</td>
						<td>&nbsp;</td>
					</tr>
				</table>
	         </div>
	           <!-- �������� -->
	          <div style="background-color: rgb(224, 236, 255);line-height:18px;" class="panel-header">
			     &nbsp;<label style="font-size: 12px;font-weight: bold;" >�������� </label>
			  </div> 
			  <div class="datagrid-wrap panel-body">
				<table >
					<tr>
						<th colspan="8" scope="col" style="width:auto">
						<textarea colspan="8" id="ejspyj"
								name="ejspyj" style="height:100px;width:700px"></textarea>
						</th>
						<th width="65" scope="col">
								<input type="button" name="Submit"  id="ejspcyy" value="������" onclick="selectCyy()"/>
							</th>
					</tr>
					<tr >
						<th width="123" scope="row">&nbsp;</th>
						<td width="71">&nbsp;</td>
						<td width="69">&nbsp;</td>
						<td width="76">&nbsp;</td>
						<td width="50" sytle="text-align:right">ǩ����</td>
						<td width="107"><input type="text" name="ejspr" id="ejspr"
							class="plui-validatebox input" data-options="required:true"
							style="width:140px;" disabled="disabled"/>
						</td>
						<td width="78" sytle="text-align:right">ǩ�����ڣ�</td>
						<td width="84"><input id="ejspsj" name="ejspsj" disabled="disabled"
							style="width:140px;" />
						</td>
						<td>&nbsp;</td>
					</tr>
				</table>
	         </div>
         </div>
		</form>
		</br>
		 <div style="text-align:center;">
	            <a id="submit" class="plui-linkbutton" iconCls="icon-save" onclick="saveExamine()">����</a>
	            <a id="cancel" class="plui-linkbutton" iconCls="icon-undo" onclick="closeInTopWindow('add_user_win');">ȡ��</a>
	        </div>
</div>
</div>   

</div>
