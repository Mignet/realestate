package com.springjdbc.annotation;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationContextUtils {
	private static Log logger = LogFactory.getLog(ApplicationContextUtils.class);
	private volatile static ApplicationContext ac;
	private final static String DEFAULT_CONFIG_FILE = "applicationContext.xml";

	public static ApplicationContext getApplicationContext() {
		return getApplicationContext(null);
	}

	public static ApplicationContext getApplicationContext(ApplicationContext acontext) {
		if (ac == null) {
			synchronized (ApplicationContext.class) {
				if (ac != null) {
					logger.info("ApplicationContext is aleady exist ...");
					return ac;
				}
				if (acontext != null) {
					logger.info("ApplicationContext come from outer...");
					ac = acontext;
					return ac;
				} else {
					ac = new ClassPathXmlApplicationContext(DEFAULT_CONFIG_FILE);
					logger.warn("ApplicationContext new instance ....");
				}

			}
		}
		return ac;
	}

}

