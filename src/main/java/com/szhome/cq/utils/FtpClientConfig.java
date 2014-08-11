package com.szhome.cq.utils;

import java.util.Map;

import org.apache.commons.lang.StringUtils;

/**
 * 
 * FTP Client
 * 
 * @author Mignet
 */
public final class FtpClientConfig {

	/** ftp???????? */
	public static String server;

	/** ftp???????? */
	public static int port;

	/** ftp??????????? */
	public static String username;

	/** ftp?????????? */
	public static String password;

	/** ftp???????????? ????unix ????nt */
	public static String FTPStyle;

	/** ????????? */
	public static String localEncoding;

	/** ???????? */
	public static String remoteEncoding;

	/** ??????? passiveMode?? */
	public static boolean passiveMode;

	/** ???????????????????? */
	public static boolean binaryFileType;

	/** ftp??????????? */
	public static String rootPath;

	/** ??????????? */
	public static String localpath = "c://";

	/** ftp??????????? */
	public static boolean handSwitch;

	/** ftp??????????? */
	public static boolean configued;

	/**
	 * ?????????
	 * 
	 */
	public FtpClientConfig() {
	}

	/**
	 * ????????
	 * 
	 * @throws Exception
	 */
	public static void initConfig(Map<String, String> map) throws Exception {
		if (!configued) {
			FtpClientConfig.server = StringUtils.isEmpty(map.get("ip")) ? FtpClientConfig.localpath
					: map.get("ip");
			FtpClientConfig.port = getPortStr(map.get("port"));
			FtpClientConfig.username = StringUtils.isEmpty(map.get("user")) ? FtpClientConfig.localpath
					: map.get("user");
			FtpClientConfig.password = StringUtils.isEmpty(map.get("password")) ? FtpClientConfig.localpath
					: map.get("password");
			FtpClientConfig.localpath = StringUtils.isEmpty(map
					.get("localpath")) ? FtpClientConfig.localpath : map
					.get("localpath");
			/**
			 * 
			 * if("ip".equalsIgnoreCase((String)iteratoritem.get("PARAMCODE"))){
			 * serverparams.put("ip",(String)iteratoritem.get("PARAMVALUE"));
			 * }else
			 * if("port".equalsIgnoreCase((String)iteratoritem.get("PARAMCODE"
			 * ))){
			 * serverparams.put("port",(String)iteratoritem.get("PARAMVALUE"));
			 * }else
			 * if("user".equalsIgnoreCase((String)iteratoritem.get("PARAMCODE"
			 * ))){
			 * serverparams.put("user",(String)iteratoritem.get("PARAMVALUE"));
			 * }else if("password".equalsIgnoreCase((String)iteratoritem.get(
			 * "PARAMCODE"))){
			 * serverparams.put("password",(String)iteratoritem.get
			 * ("PARAMVALUE")); } configued = true; String prePath =
			 * "D:\\javaproject\\imageUpload\\src"; String path = prePath +
			 * File.separator + "boss_config.properties"; FileInputStream input
			 * = new FileInputStream(path); Properties mProperty = new
			 * Properties(); mProperty.load(input); FtpClientConfig.server =
			 * (mProperty.getProperty("imageserver")).trim();
			 * FtpClientConfig.port =
			 * getPortStr(mProperty.getProperty("imageport"));
			 * FtpClientConfig.username =
			 * (mProperty.getProperty("imageusername")).trim();
			 * FtpClientConfig.password =
			 * (mProperty.getProperty("imagepassword")).trim();
			 * FtpClientConfig.FTPStyle =
			 * (mProperty.getProperty("imageftpstyle")).trim();
			 * FtpClientConfig.localEncoding =
			 * (mProperty.getProperty("imagelocalencoding")).trim();
			 * FtpClientConfig.remoteEncoding =
			 * (mProperty.getProperty("imageremoteencoding")).trim();
			 * FtpClientConfig.passiveMode =
			 * getBoleanByStr(mProperty.getProperty("imagepassivemode"));
			 * FtpClientConfig.binaryFileType =
			 * getBoleanByStr(mProperty.getProperty("imagebinaryfiletype"));
			 * FtpClientConfig.handSwitch =
			 * getBoleanByStr(mProperty.getProperty("imagehandSwitch"));
			 * FtpClientConfig.rootPath =
			 * (mProperty.getProperty("imagerootpath")).trim(); //localpath
			 * FtpClientConfig.localpath =
			 * (mProperty.getProperty("imagelocalpath")).trim(); //
			 */
			FtpClientConfig.configued = true;
		}
	}

	/**
	 * ????Boolean??????
	 * 
	 * @return
	 */
	public static boolean getBoleanByStr(String str) {
		boolean returnvalue = true;
		if (null == str) {
			returnvalue = true;
		} else {
			if ("".equals(str.trim())) {
				returnvalue = true;
			} else if ("true".equals(str.trim())) {
				returnvalue = true;
			} else if ("false".equals(str.trim())) {
				returnvalue = false;
			}
		}
		return returnvalue;
	}

	public String getLocalEncoding() {
		return localEncoding;
	}

	public String getRemoteEncoding() {
		return remoteEncoding;
	}

	public String getFTPStyle() {
		return FTPStyle;
	}

	public String getPassword() {
		return password;
	}

	public int getPort() {
		return port;
	}

	/**
	 * ??????21
	 * 
	 * @param port
	 */
	public static int getPortStr(String port) {
		if (null == port) {
			port = "21";
		} else {
			if ("".equals(port.trim()))
				port = "21";
		}
		return Integer.parseInt(port.trim());
	}

	public String getRootPath() {
		return rootPath;
	}

	public String getServer() {
		return server;
	}

	public String getUsername() {
		return username;
	}

}

