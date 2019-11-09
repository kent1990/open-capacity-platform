package com.owen.common.util;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *  @author zcl
 *  @date 2019/8/12
 */
public class DateUtil {
	public static String getCurrentTime() {
		return System.currentTimeMillis() + "";
	}


	/**
	 *  获取6个月后后面一天的 02:00：00  时间点
	 * @param createTime
	 * @return
	 */
	public static Date getExpireTime(Date createTime){

		Calendar calendar = new GregorianCalendar();
		calendar.setTime(createTime);
		calendar.add(Calendar.MONTH, 6);
		//
		calendar.add(Calendar.DAY_OF_MONTH,1);
		// 时
		calendar.set(Calendar.HOUR_OF_DAY, 2);
		// 分
		calendar.set(Calendar.MINUTE, 0);
		// 秒
		calendar.set(Calendar.SECOND, 0);
		return calendar.getTime();
	}


	public static List<Integer> getLastNDay(int n) throws Exception {

		List<Integer> list = new ArrayList<Integer>();

		while (n > 0) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());
			calendar.add(Calendar.DATE, -1);
			Date resultDate = calendar.getTime();

			SimpleDateFormat formatDate = new SimpleDateFormat("yyyyMMdd");
			list.add(Integer.parseInt(formatDate.format(resultDate)));

			n--;
		}

		return list;
	}

	public static Integer getSeconds(String time) throws Exception {
		long oldTime = stringConvetDate(time, "yyyy-MM-dd HH:mm:ss").getTime();
		long newTime = System.currentTimeMillis();
		Integer seconds = (int) ((newTime - oldTime) / 1000);
		return seconds;

	}

	public static Integer getSeconds(String time, String current) throws Exception {
		long oldTime = stringConvetDate(time, current).getTime();
		long newTime = System.currentTimeMillis();
		Integer seconds = (int) ((newTime - oldTime) / 1000);
		return seconds;

	}

	public static Integer getMilliseconds(String time, String current) throws Exception {
		long oldTime = stringConvetDate(time, current).getTime();
		long newTime = System.currentTimeMillis();
		Integer seconds = (int) ((newTime - oldTime));
		return seconds;

	}

	public static Integer getSeconds(long oldTime) throws Exception {
		long newTime = System.currentTimeMillis();
		if (oldTime > newTime) {
			return -1;
		}
		Integer seconds = (int) ((newTime - oldTime) / 1000);
		return seconds;

	}

	// 得到当前的时间
	public static String getCurrentDate() throws Exception {
		SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String str = formatDate.format(new Date());
		try {
			String date = formatDate.format(formatDate.parse(str));
			return date;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
	}

	// 得到当前的时间  年月
	public static String getCurrentMonth() throws Exception {
		SimpleDateFormat formatDate = new SimpleDateFormat("yyyyMM");
		String str = formatDate.format(new Date());
		try {
			String date = formatDate.format(formatDate.parse(str));
			return date;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
	}

	/**
	 *  获取昨天
	 * @return
	 * @throws Exception
	 */
	public static String getYesterdayTimeForDay(){
		Calendar cal=Calendar.getInstance();
		cal.add(Calendar.DATE,-1);
		Date d=cal.getTime();
		SimpleDateFormat sp=new SimpleDateFormat("yyyyMMdd");
		String yesterday=sp.format(d);//获取昨天日期
		try {
			String date = sp.format(sp.parse(yesterday));
			return date;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
	}

	/**
	 *  获取前天
	 * @return
	 * @throws Exception
	 */
	public static String getBeforYesterdayTimeForDay(){
		Calendar cal=Calendar.getInstance();
		cal.add(Calendar.DATE,-2);
		Date d=cal.getTime();
		SimpleDateFormat sp=new SimpleDateFormat("yyyy-MM-dd");
		String yesterday=sp.format(d);//获取昨天日期
		try {
			String date = sp.format(sp.parse(yesterday));
			return date;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
	}

	/**
	 *  获取后天
	 * @return
	 * @throws Exception
	 */
	public static String getAfterTomorrowTimeForDay(){
		Calendar cal=Calendar.getInstance();
		cal.add(Calendar.DATE,2);
		Date d=cal.getTime();
		SimpleDateFormat sp=new SimpleDateFormat("yyyy-MM-dd");
		String yesterday=sp.format(d);//获取昨天日期
		try {
			String date = sp.format(sp.parse(yesterday));
			return date;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
	}

	/**
	 *  获取昨天
	 * @return
	 * @throws Exception
	 */
	public static String getYesterdayTimeForMonth(){
		Calendar cal=Calendar.getInstance();
		cal.add(Calendar.DATE,-1);
		Date d=cal.getTime();
		SimpleDateFormat sp=new SimpleDateFormat("yyyyMM");
		String yesterday=sp.format(d);//获取昨天日期
		try {
			String date = sp.format(sp.parse(yesterday));
			return date;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
	}

	// 得到当前的时间  年月日
	public static String getCurrentDay() throws Exception {
		SimpleDateFormat formatDate = new SimpleDateFormat("yyyyMMdd");
		String str = formatDate.format(new Date());
		try {
			String date = formatDate.format(formatDate.parse(str));
			return date;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
	}

	public static String getDate(Date date, String format) {
		SimpleDateFormat formatDate = new SimpleDateFormat(format);
		String str = formatDate.format(date);
		try {
			String dateStr = formatDate.format(formatDate.parse(str));
			return dateStr;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
	}

	// 得到当前的时间
	public static String getCurrentDate(String current) {
		SimpleDateFormat formatDate = new SimpleDateFormat(current);
		String str = formatDate.format(new Date());
		return str;
	}

	// 字符串转化成日期
	public static Date stringConvetDate(String time, String current) {
		SimpleDateFormat formatDate = new SimpleDateFormat(current);
		Date date = null;
		try {
			if (time != null) {
				date = formatDate.parse(time);
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return date;
	}

	/**
	 * 校验日期格式
	 * @param sDate
	 * @return
	 */
	public static boolean isValidDate(String sDate) {
		String datePattern1 = "\\d{4}-\\d{2}-\\d{2}";
		String datePattern2 = "^((\\d{2}(([02468][048])|([13579][26]))" + "[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|"
				+ "(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?" + "((0?[1-9])|([1-2][0-9])))))|(\\d{2}("
				+ "([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?(" + "(((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(("
				+ "(0?[469])|(11))[\\-\\/\\s]?" + "((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))";
		if((sDate != null)) {
			Pattern pattern = Pattern.compile(datePattern1);
			Matcher match = pattern.matcher(sDate);
			if(match.matches()) {
				pattern = Pattern.compile(datePattern2);
				match = pattern.matcher(sDate);
				return match.matches();
			} else {
				return false;
			}
		}
		return false;
	}

//	public static boolean isValidDate(String str) {
//		boolean convertSuccess = true;
//		// 指定日期格式为四位年/两位月份/两位日期，注意yyyy/MM/dd区分大小写；
//		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		try {
//			// 设置lenient为false.
//			// 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
//			format.setLenient(false);
//			format.parse(str);
//		} catch (Exception e) {
//			convertSuccess = false;
//		}
//		return convertSuccess;
//	}



	/**
	 * This method generates a string representation of a date/time in the format
	 * you specify on input
	 * 
	 * @param aMask   the date pattern the string is in
	 * @param strDate a string representation of a date
	 * @return a converted Date object
	 * @throws ParseException when String doesn't match the expected format
	 * @see SimpleDateFormat
	 */
	public static Date convertStringToDate(String aMask, String strDate) throws ParseException {
		SimpleDateFormat df;
		Date date;
		df = new SimpleDateFormat(aMask);

		try {
			date = df.parse(strDate);
		} catch (ParseException pe) {
			// log.error("ParseException: " + pe);
			throw new ParseException(pe.getMessage(), pe.getErrorOffset());
		}

		return (date);
	}



	/**
	 * 对日期加减
	 *
	 * @param num 正数为加 负数为减
	 * @return
	 * @throws Exception
	 */
	public static String dayOfMouthCount(String currentDate, int num) throws Exception {
		String result = "";
		String str = getCurrentDate();
		if (currentDate != null && !"".equals(currentDate)) {
			str = currentDate;
		}
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		Date date = formatter.parse(str);
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DAY_OF_MONTH, num);
		date = c.getTime();
		result = formatter.format(date);
		return result;
	}

	/**
	 * 判定起止时间是否越界
	 *
	 * @param start 开始时间
	 * @param end   结束时间
	 * @param num   界限 单位：天
	 * @return
	 * @throws Exception
	 */
	public static boolean timeOutOfBond(String start, String end, int num) throws Exception {
		boolean result = false;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date startDate = formatter.parse(start);
		Date endDate = formatter.parse(end);
		long time = endDate.getTime() - startDate.getTime();
		long bond = num * 1000 * 86400;
		if (time > bond) {
			result = true;
		}
		return result;
	}

	/**
	 * 判定输入时间是否为当天日期
	 *
	 * @param input 结束时间
	 * @return
	 * @throws Exception
	 */
	public static boolean isCurrentDate(String currentDate, String input) throws Exception {
		boolean result = false;
		if (currentDate != null && !"".equals(currentDate)) {
			if (currentDate.equals(input.substring(0, 8))) {
				result = true;
			}
		} else {
			if (getDate(new Date(), "yyyyMMddHHmmss").substring(0, 8).equals(input.substring(0, 8))) {
				result = true;
			}
		}
		return result;
	}

	public static String getLastWeekDate() {
		Calendar cal = Calendar.getInstance(Locale.CHINA);
		int weekday = cal.get(Calendar.DAY_OF_WEEK);
		if (weekday == 1)
			cal.add(Calendar.DATE, -7);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		return new SimpleDateFormat("yyyyMMdd").format(cal.getTime());
	}

	public static List<String> getRecentWeekDate(String weekDate, int n) throws Exception {
		List<String> list = new ArrayList<>();
		for (int i = n - 1; i > 0; i--) {
			String date = dayOfMouthCount(weekDate, -7 * i);
			list.add(date);
		}
		list.add(weekDate);
		return list;
	}

	public static String getBeforeMonthDate() {
		Calendar cal = Calendar.getInstance(Locale.CHINA);
		cal.add(Calendar.MONTH, -1);
		return new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
	}

	public static String getLastYearDate() {
		Calendar cal = Calendar.getInstance(Locale.CHINA);
		cal.add(Calendar.YEAR, -1);
		return new SimpleDateFormat("yyyyMM").format(cal.getTime());
	}

	public static String getEndTime(long timestamp) {
		String format = "yyyyMMddHHmmss";
		return new SimpleDateFormat(format, Locale.CHINA).format(new Date(timestamp));
	}

	// 获得年和月份
	public static String getCurrentYearAndMonth() {
		Calendar calendar = Calendar.getInstance(Locale.CHINA);
		Date time = calendar.getTime();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMM");
		String s = format.format(time);
		return s;
	}

	/**
	 * 返回给定日期的前month[-]天或者后month[+]天的日期
	 * 
	 * @param date
	 * @param month
	 * @return
	 */
	public static Date addMonth(Date date, int month) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, month);
		return calendar.getTime();
	}

//     public static void main(String[] args) {
//     try {
//     System.out.println(getEndTime(System.currentTimeMillis()));
//     } catch (Exception e) {
//     // TODO Auto-generated catch block
//     e.printStackTrace();
//     }
//     }
	/*
	 * public static void main(String[] args) { try {
	 * System.out.println(getCurrentDate()); } catch (Exception e) { // TODO
	 * Auto-generated catch block e.printStackTrace(); } }
	 */

	//校验8位字符串是否为正确的日期格式
	public static boolean validDate(String str) {
		boolean result = true;
		//判断字符串长度是否为8位
		if(str.length() == 8){
			// 指定日期格式为四位年/两位月份/两位日期，注意yyyy/MM/dd区分大小写；
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
			try {
				// 设置lenient为false.
				// 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
				format.setLenient(false);
				format.parse(str);
			} catch (ParseException e) {
				// e.printStackTrace();
				// 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
				result = false;
			}
		}else{
			result = false;
		}
		return result;
	}

	//校验8位字符串是否为正确的日期格式
	public static boolean validDate6(String str) {
		boolean result = true;
		//判断字符串长度是否为8位
		if(str.length() == 6){
			// 指定日期格式为四位年/两位月份/两位日期，注意yyyy/MM/dd区分大小写；
			SimpleDateFormat format = new SimpleDateFormat("yyyyMM");
			try {
				// 设置lenient为false.
				// 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
				format.setLenient(false);
				format.parse(str);
			} catch (ParseException e) {
				// e.printStackTrace();
				// 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
				result = false;
			}
		}else{
			result = false;
		}
		return result;
	}
}
