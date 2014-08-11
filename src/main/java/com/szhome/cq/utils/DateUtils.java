package com.szhome.cq.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.szhome.cq.business.FacadeFactory;
import com.szhome.cq.domain.PublicDomain;




public class DateUtils {
	private static Log logger = LogFactory.getLog(DateUtils.class);
	private static PublicDomain sd = FacadeFactory.getPublicDomain();
	public final static String DATE_FORMAT_DEFAULT = "yyyy-MM-dd HH:mm:ss";
	public final static String DATE_FORMAT_DEFAULT_SHORT = "yyyyMMddHHmmss";
	public final static String DATE_FORMAT_MINUTE = "yyyy-MM-dd HH:mm";
	public final static String DATE_FORMAT_MINUTE_SHORT = "yyyyMMddHHmm";
	public final static String DATE_FORMAT_DAY = "yyyy-MM-dd";
	public final static String DATE_FORMAT_DAY_SHORT = "yyyyMMdd";
	public final static String DATE_FORMAT_YYYYMM = "yyyyMM";
	public final static String DATE_FORMAT_HOUR = "yyyy-MM-dd HH";
	public final static String DATE_FORMAT_MONTH = "yyyy-MM";
	public final static String DATE_FORMAT_MONTH_SHORT = "yyyyMM";
	public final static String DATE_FORMAT_YEAR = "yyyy";
	public final static String DATE_FORMAT_CHINA_DEFAULT = "yyyy-MM-dd 下午HH:mm:ss";

	/**
	 * 获取当前时间
	 * @return
	 */
	public static Date getCurTime() {
		return sd.queryCurrentTime();
	}

	/**
	 * 获取当前日期字符数
	 * @return
	 */
	public static String getCurDateStr() {
		return date2String(getCurTime(), DATE_FORMAT_DAY_SHORT);
	}
	
	/**
	 * 获取当前年月字符串
	 * @return
	 */
	public static String getCurYYYYMMStr() {
		return date2String(getCurTime(), DATE_FORMAT_YYYYMM);
	}
	
	/**
	 * 获取当前时间字符数
	 * @return
	 */
	public static String getCurTimeStr() {
		return date2String(getCurTime(), DATE_FORMAT_DEFAULT);
	}

	/**
	 * 获取当前时间带格式的字符串
	 * @param format
	 * @return
	 */
	public static String getCurTimeStrByFormate(String format) {
		return date2String(getCurTime(), format);
	}

	/**
	 * 当前日期加上年
	 * @param year
	 * @param 
	 * @return
	 */
	public static Date dateAddYears(Integer year) {
		Date date=new Date();
		try {
			Calendar curr = Calendar.getInstance(); 
			curr.set(Calendar.YEAR,curr.get(Calendar.YEAR)+year); 
			date=curr.getTime();
			//SimpleDateFormat df = new SimpleDateFormat(date);
			
		} catch (Exception ex) {
			logger.error(ex);
		}
		return date;
		//return date2String(date, DATE_FORMAT_DEFAULT);
	}
	/**
	 * DATE 转换 string
	 * @param date
	 * @param format
	 * @return
	 */
	public static String date2String(Date date, String format) {
		try {
			if (date == null)
				return "";
			SimpleDateFormat df = new SimpleDateFormat(format);
			return df.format(date);
		} catch (Exception ex) {
			logger.error(ex);
		}
		return date2String(date, DATE_FORMAT_DEFAULT);
	}

	/**
	 * String 转换 Date
	 * @param date
	 * @param format
	 * @return
	 */
	public static Date string2Date(String date, String format) {
		try {
			if (date == null || date.equals(""))
				return null;
			java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(format);
			return sdf.parse(date);
		} catch (Exception e) {
			logger.error(e);
		}
		return null;

	}

	/**
	 * 比较两个时间相差多少天
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static long compareDates(Date startDate, Date endDate) {
		Date startDate1 = string2Date(date2String(startDate, DATE_FORMAT_DAY_SHORT), DATE_FORMAT_DAY_SHORT);
		Date endDate1 = string2Date(date2String(endDate, DATE_FORMAT_DAY_SHORT), DATE_FORMAT_DAY_SHORT);
		return (endDate1.getTime() - startDate1.getTime()) / (1000 * 24 * 60 * 60);

	}

	/**
	 * 获得当前时间月份有多少天
	 * */
	public static int getDays(Date enddate) {
		Calendar lastDate = Calendar.getInstance();
		lastDate.setTime(enddate);
		lastDate.set(Calendar.DATE, 1);// 设为当前月的1号
		lastDate.add(Calendar.MONTH, 1);// 加一个月，变为下月的1号
		lastDate.add(Calendar.DATE, -1);// 减去一天，变为当月最后一天
		return lastDate.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 判断是否是闰年
	 * */
	public static boolean isLeap(int year) {
		if (((year % 100 == 0) && year % 400 == 0) || ((year % 100 != 0) && year % 4 == 0))
			return true;
		else
			return false;
	}

	public static String thisMonth() {
		int x; // 日期属性：年
		int y; // 日期属性：月
		Calendar localTime = Calendar.getInstance();//当前日期
		String strY = null;
		x = localTime.get(Calendar.YEAR);
		y = localTime.get(Calendar.MONTH) + 1;
		strY = y >= 10 ? String.valueOf(y) : ("0" + y);
		return x + "-" + strY + "-01";
	}
	
	/**
	 * 获得当月1号日期
	 * */
	public static Date firstDayOfMonth(){
		Calendar firstDay = Calendar.getInstance();
		firstDay.set(Calendar.DATE, 1);// 设为当前月的1号
		String temp = DateUtils.date2String(firstDay.getTime(), DateUtils.DATE_FORMAT_DAY);
		return DateUtils.string2Date(temp, DateUtils.DATE_FORMAT_DAY);
	}
	/**
	 * 获得当月最后一天
	 * */
	public static Date lastDayOfMonth(){
		Calendar lastDate = Calendar.getInstance();
		lastDate.set(Calendar.DATE, 1);// 设为当前月的1号
		lastDate.add(Calendar.MONTH, 1);// 加一个月，变为下月的1号
		lastDate.add(Calendar.DATE, -1);// 减去一天，变为当月最后一天
		String temp = DateUtils.date2String(lastDate.getTime(), DateUtils.DATE_FORMAT_DAY);
		return DateUtils.string2Date(temp, DateUtils.DATE_FORMAT_DAY);
	}
	/**
	 * 获得下月1号
	 * */
	public static Date nextMonthFirstDay(){
		Calendar lastDate = Calendar.getInstance();
		lastDate.set(Calendar.DATE, 1);// 设为当前月的1号
		lastDate.add(Calendar.MONTH, 1);// 加一个月，变为下月的1号
		String temp = DateUtils.date2String(lastDate.getTime(), DateUtils.DATE_FORMAT_DAY);
		return DateUtils.string2Date(temp, DateUtils.DATE_FORMAT_DAY);
	}

	/**
	  * 功能：得到当前月份月底 格式为：xxxx-yy-zz (eg: 2007-12-31)
	  * @return String
	  * @author Mignet
	  **/
	public static String thisMonthEnd() {
		int x; // 日期属性：年
		int y; // 日期属性：月
		Calendar localTime = Calendar.getInstance();//当前日期
		String strY = null;
		String strZ = null;
		boolean leap = false;
		x = localTime.get(Calendar.YEAR);
		y = localTime.get(Calendar.MONTH) + 1;
		if (y == 1 || y == 3 || y == 5 || y == 7 || y == 8 || y == 10 || y == 12) {
			strZ = "31";
		}
		if (y == 4 || y == 6 || y == 9 || y == 11) {
			strZ = "30";
		}
		if (y == 2) {
			leap = isLeap(x);
			if (leap) {
				strZ = "29";
			} else {
				strZ = "28";
			}
		}
		strY = y >= 10 ? String.valueOf(y) : ("0" + y);
		return x + "-" + strY + "-" + strZ;
	}

	/**
	 * 计算两个日期的大小
	 * 
	 * @param begindate
	 * @param enddate
	 * @return flag 1 表示begindate >= enddate -1 表示begindate < enddate
	 * */
	public static int comparaDate(Date begindate, Date enddate) {
		int flag = 0;
		if(begindate==null||enddate==null)return flag;
		
		if (begindate.getTime() >= enddate.getTime())
			flag = 1;
		if (begindate.getTime() < enddate.getTime())
			flag = -1;
		return flag;
	}
	
	public static boolean datecompara(Date begindate, Date enddate){
		if (begindate.getTime() >= enddate.getTime()){
			return true;
		}else{
			return false;
		}
	}
	/**
	 * 得到日期的下一天
	 * @param thisDay 指定日期
	 * */
	public static Date getNextDay(Date thisDay) {
		Date nextDate = new Date(thisDay.getTime() + (1000 * 60 * 60 * 24));
		return nextDate;
	}
	
	/**
	 * 得到日期的前一天
	 * @param thisDay 指定日期
	 * */
	public static Date getPreviousDay(Date thisDay) {
		return getSpecifyDate(thisDay, Calendar.DATE, -1);
	}
	/**
	 * 
	 * 当前日期+ hours 后的日期
	 * @param date
	 * @param hours
	 * @return
	 */
	public static Date dayAddHours(Date date,int hours){
		Calendar calendartemp = Calendar.getInstance();
		calendartemp.setTime(date);
		calendartemp.add(Calendar.HOUR_OF_DAY, hours);
		return calendartemp.getTime();
	}
	
	/**
	 * 获取原时间的特定偏差时间
	 * @param srcDate 原时间
	 * @param field 偏差的类型,类是为 Calendar.MONTH
	 * @param moveNum 偏差的大小,可为负数
	 * @return date
	 * Create By Mignet
	 */
	public static Date getSpecifyDate(Date srcDate , int field , int moveNum){
		Calendar tempCalendar = Calendar.getInstance();
		tempCalendar.setTime(srcDate);
		tempCalendar.add(field , moveNum);
		return tempCalendar.getTime();
	}
	public static List<String> getOneCys(String startTime, String endTime) {
		List<String> yyyymm = new ArrayList<String>();
		int syear = Integer.valueOf((startTime.substring(0, 4)));
		int smonth = Integer.valueOf(startTime.substring(4, 6));
		int eyear = Integer.valueOf(endTime.substring(0, 4));
		int emonth = Integer.valueOf(endTime.substring(4, 6));
		if (eyear == syear) {
			if (emonth > smonth) {
				for (int i = smonth; i <= emonth; i++) {
					String ti = "" + i;
					if (ti.length() < 2) {
						ti = "0" + ti;
					}
					yyyymm.add(eyear + ti);
				}
			} else {
				yyyymm.add((eyear + "") + emonth);
			}
		} else if (eyear > syear) {
			for (int yy = syear; yy <= eyear; yy++) {
				if (yy < eyear) {
					if (yy == syear) {
						for (int i = smonth; i <= 12; i++) {
							String ti = "" + i;
							if (ti.length() < 2) {
								ti = "0" + ti;
							}
							yyyymm.add((yy + "") + ti);
						}
					} else {
						for (int i = 1; i <= 12; i++) {
							String ti = "" + i;
							if (ti.length() < 2) {
								ti = "0" + ti;
							}
							yyyymm.add((yy + "") + ti);
						}
					}
				}
				if (yy == eyear) {
					for (int i = 1; i <= emonth; i++) {
						String ti = "" + i;
						if (ti.length() < 2) {
							ti = "0" + ti;
						}
						yyyymm.add((yy + "") + ti);
					}
				}
			}
		} else {

		}
		return yyyymm;
	}
	
	/**
	 * 判断两个时间间隔（一个月默认取30天）
	 * @param startDate
	 * @param endDate
	 * @return 整型数组，第一个为月，第二个为天
	 * @throws Exception
	 */
	public static long[] distanceDate(Date startDate,Date endDate) throws Exception{
		long[] result = new long[]{0,0};
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		startDate = format.parse((format.format(startDate)));
		endDate = format.parse((format.format(endDate)));
		
		long lTime = endDate.getTime() - startDate.getTime();
		if(lTime <= 0){
			result[0] = 0;
			result[1] = 0;
		}else{
			result[0] = lTime/(1000*60*60*24)/30;
			result[1] = lTime/(1000*60*60*24)%30;
		}

		return result;
	}	
	
	/**
	 * 
	 * findDates:(获取从开始日期到结束日期内所有date).
	 *
	 * @author Joyon
	 * @param dBegin
	 * @param dEnd
	 * @return
	 * @since JDK 1.6
	 */
	public static List<Date> findDates(Date dBegin, Date dEnd)
	 {
	  List lDate = new ArrayList();
	  lDate.add(dBegin);
	  Calendar calBegin = Calendar.getInstance();
	  // 使用给定的 Date 设置此 Calendar 的时间
	  calBegin.setTime(dBegin);
	  Calendar calEnd = Calendar.getInstance();
	  // 使用给定的 Date 设置此 Calendar 的时间
	  calEnd.setTime(dEnd);
	  // 测试此日期是否在指定日期之后
	  while (dEnd.after(calBegin.getTime()))
	  {
	   // 根据日历的规则，为给定的日历字段添加或减去指定的时间量
	   calBegin.add(Calendar.DAY_OF_MONTH, 1);
	   lDate.add(calBegin.getTime());
	  }
	  return lDate;
	 }
	
	/**
	 * 
	 * getAllTheDateOftheMonth:(获取日期所在当前月所有日期).
	 *
	 * @author Joyon
	 * @param date
	 * @return
	 * @since JDK 1.6
	 */
	public static List<Date> getAllTheDateOftheMonth(Date date) {
		List<Date> list = new ArrayList<Date>();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DATE, 1);

		int month = cal.get(Calendar.MONTH);
		while(cal.get(Calendar.MONTH) == month){
			list.add(cal.getTime());
			cal.add(Calendar.DATE, 1);
		}
		return list;
	}
	
	/**
	  * 得到某年某月的第一天
	  *
	  * @param year
	  * @param month
	  * @return
	  */
	 public static Date getFirstDayOfMonth(int year, int month) {
	  Calendar cal = Calendar.getInstance();
	  cal.set(Calendar.YEAR, year);
	  cal.set(Calendar.MONTH, month-1);
	  cal.set(Calendar.DAY_OF_MONTH, cal.getMinimum(Calendar.DATE));
	  return cal.getTime();
	 }
	 
	 /**
	  * 得到某年某月的第一天字符串
	  *
	  * @param year
	  * @param month
	  * @return
	  */
	 public static String getFirstDayStrOfMonth(int year, int month) {
	  Calendar cal = Calendar.getInstance();
	  cal.set(Calendar.YEAR, year);
	  cal.set(Calendar.MONTH, month-1);
	  cal.set(Calendar.DAY_OF_MONTH, cal.getMinimum(Calendar.DATE));
	  return new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
	 }

	
}

