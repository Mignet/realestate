package com.szhome.cq.utils.filter;

/**
 * JSP网页过滤器
 * author: Mignet
 */

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;

import com.szhome.commons.log.LogUtil;
import com.szhome.cq.utils.SystemManageCommParam;
import com.szhome.security.ext.UserInfo;

/**
 * 权限过滤
 *
 */
public class AuthorizationFilter extends OncePerRequestFilter {

	@Override
	public void destroy() {

	}

	@Override
	public void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String currentURL = request.getRequestURI().toLowerCase();
		HttpSession session = request.getSession(false);
		//验证JSP
		if ((request.getHeader("x-requested-with") != null && request.getHeader("x-requested-with").equals(
				"XMLHttpRequest"))
				|| (request.getHeader("x-request") != null && request.getHeader("x-request").equals("JSON"))) {
			filterChain.doFilter(request, response);
		} else {
			//登录页/错误页/成功页不判断
			if (!((currentURL.indexOf(SystemManageCommParam.LOGIN_PAGE) > -1)
					|| (currentURL.indexOf(SystemManageCommParam.ERROR_PAGE) > -1)
					|| (currentURL.indexOf("login.action") > -1) 
					|| (currentURL.indexOf("unknown-error!") > -1)
					|| (currentURL.indexOf("commissionfilter.jsp") > -1) 
					|| (currentURL.indexOf("userloginout.action") > -1))
					&& (currentURL.indexOf(".action") > -1)) {
				LogUtil.debug("**********登陆检测," + " URL: " + currentURL + "**********");
				if (session == null || session.getAttribute("userInfo") == null) {
					UserInfo userInfo = (UserInfo)WebUtils.getSessionAttribute(request, "userInfo");
					LogUtil.error("访问："+currentURL+" 遇到session问题"+userInfo);
					if (currentURL.indexOf("redirectnopage") > -1) {//未登录时出现异常，不弹出提示
						response.sendRedirect(request.getContextPath() + "/" + SystemManageCommParam.LOGIN_ACTION_INDEX);
					} else {
						response.sendRedirect(request.getContextPath() + "/" + SystemManageCommParam.LOGIN_ACTION);
					}
					LogUtil.error("**********出错：未登录," + " URL: " + currentURL);
					return;
				}
				/*String context = request.getContextPath();
				if (session.getAttribute("userInfo") != null && currentURL.indexOf("redirectnopage") > -1) {//已登录异常，跳入主页
					context = context + SystemManageCommParam.MAIN_INDEX;
					response.sendRedirect(context);
				}*/
			}else{
				//LogUtil.info("**********排除路径" + " URL: " + currentURL);
			}
			filterChain.doFilter(request, response);
		}
	}

	public AuthorizationFilter(){
		LogUtil.debug("**********初始化登录检查过滤器************");
	}

}

