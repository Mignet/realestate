<%@ page language="java" pageEncoding="GBK" contentType="text/html;charset=GBK" import="com.szhome.security.ext.UserInfo"%>
 <%@ include file="/base/taglibs.jsp"%>
 <script type="text/javascript" src="${ctx}/js/login/north.js"></script>
<%
  UserInfo userInfo=(UserInfo)session.getAttribute("userInfo");
  String userName=userInfo.getUserName();
  String deptName=userInfo.getOrganName();
%>
  	<div class="top" id="top">
	<div class="title">�����в������Ǽ�ϵͳ<span id="tttt"></span></div>
    <div class="list">
    	<div class="tleft">
        	<span>��ӭ��,<%=deptName%>��</span>
            <span id="litmenu" style="z-index:99999;">
            	<font style="float:left;color:#000;font-weight:bold;"><%=userName%></font>
              <img src="${ctx}/images/index/index_06.gif" style="float:left;margin-top:9px;cursor:pointer;" />
          </span>
          <span><font style="color:#006699;margin-left:5px;">[</font><a  href="javascript:void(0)" onclick="logoutConf()" style="color:#006699;">�˳���¼</a><font style="color:#006699;">]</font></span>
      </div>
      
      <div class="tright">
      	<div class="search1">
              <input type="text" value="����ؼ�������.." id="text" class="sin"/>
              <input type="button" class="sbut" id="sbut"/>
          </div>
          <div style="float:right;">
              <select name="DART" id="DART" class="plui-combobox" style="width:100px;">
                  <option value="���߷���">���߷���</option>
                  <option value="�Ʒ���">�Ʒ���</option>
                  <option value="������Ŀ�ɹ�">������Ŀ�ɹ�</option>
                  <option value="����">����</option>
              </select>
          </div>
          <div class="dock" id="dock">
              <div class="dock-container">
                  <a class="dock-item" id="btnHome" href="javascript:;" onClick="">
                  	<span class="dispan">
                      	<span class="dispanc" style="width:16px;"></span>
                          <span class="dispanr"></span>
                      	<span class="itemtx" style="line-height:22px;">ͨѶ¼</span>
                      </span>
                      <img src="${ctx}/images/index/icon01.png" alt="ͨѶ¼"/>
                  </a>
                  <a class="dock-item" id="btnFeedback" href="javascript:;" onClick="">
                  	<span class="dispan">
                      	<span class="dispanc" style="width:16px;"></span>
                          <span class="dispanr"></span>
                      	<span class="itemtx" style="line-height:22px;">�����</span>
                      </span>
                  	<img src="${ctx}/images/index/icon02.png" alt="�����" />
                  </a>
                  <div id="win"></div>
                  <a class="dock-item" id="btnApi" href="javascript:;" onClick="">
                  	<span class="dispan">
                      	<span class="dispanc" style="width:26px;"></span>
                          <span class="dispanr"></span>
                      	<span class="itemtx" style="line-height:22px;">ϵͳAPI</span>
                      </span>
                  	<img src="${ctx}/images/index/icon03.png" alt="ϵͳAPI" />
                  </a>
                  <a class="dock-item" id="btnHouse" href="javascript:;" onClick="">
                  	<span class="dispan">
                      	<span class="dispanc" style="width:50px;"></span>
                          <span class="dispanr"></span>
                      	<span class="itemtx" style="line-height:22px;">¥����Ϣ��ѯ</span>
                      </span>
                  	<img src="${ctx}/images/index/icon04.png" alt="¥����Ϣ��ѯ" />
                  </a>
                  <a class="dock-item" id="btnKnow" href="javascript:;" onClick="">
                  	<span class="dispan">
                      	<span class="dispanc" style="width:26px;"></span>
                          <span class="dispanr"></span>
                      	<span class="itemtx" style="line-height:22px;">��������</span>
                      </span>
                  	<img src="${ctx}/images/index/icon05.png" alt="��������" />
                  </a>
                  <a class="dock-item" id="btnChat" href="javascript:void(0)" target="" onClick="">
                  	<span class="dispan">
                      	<span class="dispanc" style="width:26px;"></span>
                          <span class="dispanr"></span>
                      	<span class="itemtx" style="line-height:22px;">��̳����</span>
                        </span>
                    	<img src="${ctx}/images/index/icon06.png" alt="��̳����" />
                    </a>
                    
                </div>
            </div>
            
        </div>
    </div>
</div>


