package com.szhome.cq.utils.filter;

/**
 * JSP��ҳ������
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
 * Ȩ�޹���
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
		//��֤JSP
		if ((request.getHeader("x-requested-with") != null && request.getHeader("x-requested-with").equals(
				"XMLHttpRequest"))
				|| (request.getHeader("x-request") != null && request.getHeader("x-request").equals("JSON"))) {
			filterChain.doFilter(request, response);
		} else {
			//��¼ҳ/����ҳ/�ɹ�ҳ���ж�
			if (!((currentURL.indexOf(SystemManageCommParam.LOGIN_PAGE) > -1)
					|| (currentURL.indexOf(SystemManageCommParam.ERROR_PAGE) > -1)
					|| (currentURL.indexOf("login.action") > -1) 
					|| (currentURL.indexOf("unknown-error!") > -1)
					|| (currentURL.indexOf("commissionfilter.jsp") > -1) 
					|| (currentURL.indexOf("userloginout.action") > -1))
					&& (currentURL.indexOf(".action") > -1)) {
				LogUtil.debug("**********��½���," + " URL: " + currentURL + "**********");
				if (session == null || session.getAttribute("userInfo") == null) {
					UserInfo userInfo = (UserInfo)WebUtils.getSessionAttribute(request, "userInfo");
					LogUtil.error("���ʣ�"+currentURL+" ����session����"+userInfo);
					if (currentURL.indexOf("redirectnopage") > -1) {//δ��¼ʱ�����쳣����������ʾ
						response.sendRedirect(request.getContextPath() + "/" + SystemManageCommParam.LOGIN_ACTION_INDEX);
					} else {
						response.sendRedirect(request.getContextPath() + "/" + SystemManageCommParam.LOGIN_ACTION);
					}
					LogUtil.error("**********����δ��¼," + " URL: " + currentURL);
					return;
				}
				/*String context = request.getContextPath();
				if (session.getAttribute("userInfo") != null && currentURL.indexOf("redirectnopage") > -1) {//�ѵ�¼�쳣��������ҳ
					context = context + SystemManageCommParam.MAIN_INDEX;
					response.sendRedirect(context);
				}*/
			}else{
				//LogUtil.info("**********�ų�·��" + " URL: " + currentURL);
			}
			filterChain.doFilter(request, response);
		}
	}

	public AuthorizationFilter(){
		LogUtil.debug("**********��ʼ����¼��������************");
	}

}

