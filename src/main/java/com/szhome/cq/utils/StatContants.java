package com.szhome.cq.utils;

public class StatContants {
	//统计报表状态-有效
	public static final String STAT_REPORT_STATE_VALID = "00";
	//统计报表状态-无效
	public static final String STAT_REPORT_STATE_INVALID = "01";
	//统计报表类型-静态
	public static final String STAT_REPORT_TYPE_STATIC = "00";
	//统计报表类型-动态
	public static final String STAT_REPORT_STATE_DYNAMIC = "01";
	//统计报表调用类型-JAVA程序
	public static final String STAT_REPORT_INVOKE_TYPE_JAVA = "00";
	//统计报表调用类型-存储过程
	public static final String STAT_REPORT_INVOKE_TYPE_PROCEDURE = "01";
	//统计报表调用类型-SQL
	public static final String STAT_REPORT_INVOKE_TYPE_SQL = "02";
	
	//统计报表元素类型-行
	public static final String STAT_REPORT_ITEM_TYPE_ROW = "01";
	//统计报表元素类型-列
	public static final String STAT_REPORT_ITEM_TYPE_COLUMN = "00";
	//统计报表元素对齐方式-左对齐
	public static final String STAT_REPORT_ITEM_ALIGN_LEFT = "00";
	//统计报表元素对齐方式-中间对齐
	public static final String STAT_REPORT_ITEM_ALIGN_CENTER = "01";
	//统计报表元素对齐方式-右对齐
	public static final String STAT_REPORT_ITEM_ALIGN_RIGHT = "02";
	//统计报表元素显示标志-显示
	public static final String STAT_REPORT_ITEM_SHOWFLAG_YES = "00";
	//统计报表元素显示标志-不显示
	public static final String STAT_REPORT_ITEM_SHOWFLAG_NO = "01";
	
	//统计报表参数类型-字符串
	public static final String STAT_REPORT_PARAM_TYPE_STRING = "00";
	//统计报表参数类型-日期
	public static final String STAT_REPORT_PARAM_TYPE_DATE = "01";
	
	//统计报表参数是否必填-非必填
	public static final String STAT_REPORT_PARAM_MUST_NO = "00";
	//统计报表参数是否必填-必填
	public static final String STAT_REPORT_PARAM_MUST_YES = "01";
	
	//统计报表参数显示类型-文本
	public static final String STAT_REPORT_PARAM_SHOWTYPE_TEXT = "00";
	//统计报表参数显示类型-单选
	public static final String STAT_REPORT_PARAM_SHOWTYPE_RADIO = "01";
	//统计报表参数显示类型-多选
	public static final String STAT_REPORT_PARAM_SHOWTYPE_MULTI = "02";
}

