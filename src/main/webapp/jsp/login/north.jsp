<%@ page language="java" pageEncoding="GBK" contentType="text/html;charset=GBK" import="com.szhome.security.ext.UserInfo"%>
 <%@ include file="/base/taglibs.jsp"%>
 <script type="text/javascript" src="${ctx}/js/login/north.js"></script>
<%
  UserInfo userInfo=(UserInfo)session.getAttribute("userInfo");
  String userName=userInfo.getUserName();
  String deptName=userInfo.getOrganName();
%>
  	<div class="top" id="top">
	<div class="title">深圳市不动产登记系统<span id="tttt"></span></div>
    <div class="list">
    	<div class="tleft">
        	<span>欢迎您,<%=deptName%>：</span>
            <span id="litmenu" style="z-index:99999;">
            	<font style="float:left;color:#000;font-weight:bold;"><%=userName%></font>
              <img src="${ctx}/images/index/index_06.gif" style="float:left;margin-top:9px;cursor:pointer;" />
          </span>
          <span><font style="color:#006699;margin-left:5px;">[</font><a  href="javascript:void(0)" onclick="logoutConf()" style="color:#006699;">退出登录</a><font style="color:#006699;">]</font></span>
      </div>
      
      <div class="tright">
      	<div class="search1">
              <input type="text" value="输入关键字搜索.." id="text" class="sin"/>
              <input type="button" class="sbut" id="sbut"/>
          </div>
          <div style="float:right;">
              <select name="DART" id="DART" class="plui-combobox" style="width:100px;">
                  <option value="政策法规">政策法规</option>
                  <option value="制发文">制发文</option>
                  <option value="建设项目成果">建设项目成果</option>
                  <option value="档案">档案</option>
              </select>
          </div>
          <div class="dock" id="dock">
              <div class="dock-container">
                  <a class="dock-item" id="btnHome" href="javascript:;" onClick="">
                  	<span class="dispan">
                      	<span class="dispanc" style="width:16px;"></span>
                          <span class="dispanr"></span>
                      	<span class="itemtx" style="line-height:22px;">通讯录</span>
                      </span>
                      <img src="${ctx}/images/index/icon01.png" alt="通讯录"/>
                  </a>
                  <a class="dock-item" id="btnFeedback" href="javascript:;" onClick="">
                  	<span class="dispan">
                      	<span class="dispanc" style="width:16px;"></span>
                          <span class="dispanr"></span>
                      	<span class="itemtx" style="line-height:22px;">意见箱</span>
                      </span>
                  	<img src="${ctx}/images/index/icon02.png" alt="意见箱" />
                  </a>
                  <div id="win"></div>
                  <a class="dock-item" id="btnApi" href="javascript:;" onClick="">
                  	<span class="dispan">
                      	<span class="dispanc" style="width:26px;"></span>
                          <span class="dispanr"></span>
                      	<span class="itemtx" style="line-height:22px;">系统API</span>
                      </span>
                  	<img src="${ctx}/images/index/icon03.png" alt="系统API" />
                  </a>
                  <a class="dock-item" id="btnHouse" href="javascript:;" onClick="">
                  	<span class="dispan">
                      	<span class="dispanc" style="width:50px;"></span>
                          <span class="dispanr"></span>
                      	<span class="itemtx" style="line-height:22px;">楼盘信息查询</span>
                      </span>
                  	<img src="${ctx}/images/index/icon04.png" alt="楼盘信息查询" />
                  </a>
                  <a class="dock-item" id="btnKnow" href="javascript:;" onClick="">
                  	<span class="dispan">
                      	<span class="dispanc" style="width:26px;"></span>
                          <span class="dispanr"></span>
                      	<span class="itemtx" style="line-height:22px;">资料中心</span>
                      </span>
                  	<img src="${ctx}/images/index/icon05.png" alt="资料中心" />
                  </a>
                  <a class="dock-item" id="btnChat" href="javascript:void(0)" target="" onClick="">
                  	<span class="dispan">
                      	<span class="dispanc" style="width:26px;"></span>
                          <span class="dispanr"></span>
                      	<span class="itemtx" style="line-height:22px;">论坛交流</span>
                        </span>
                    	<img src="${ctx}/images/index/icon06.png" alt="论坛交流" />
                    </a>
                    
                </div>
            </div>
            
        </div>
    </div>
</div>


