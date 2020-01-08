package com.nbst.comnutil;

import lombok.extern.slf4j.Slf4j;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author XH
 * @version 创建时间：2017年9月26日
 * 
 */
@Slf4j
public class Times {

	public static int HOUR_LONG = 3600000; // 一小时 3600000毫秒
	public static int DAY_LONG = 3600000 * 24;// 一天 3600000*24毫秒
	public static int MINUTES_IN_ONE_DAY = 24 * 60; // 一天有24 * 60 = 1440分钟
	public static int MINUTE_LONG = 1000 * 60; // 一分钟的毫秒数

	public static Date getDateFromYear(int year) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, Calendar.JANUARY);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	/**
	 * 
	 * @param year
	 * @param month
	 *            月份从0开始
	 * @return
	 */
	public static Date getDateFromYearMonth(int year, int month) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	public static long getTimeOffsetFromTodayZeroMsTime() {
		// 获取现在的毫秒数
		long currentMsTime = System.currentTimeMillis();

		// 获取今天凌晨的毫秒数
		long todayZeroMsTime = Times.getDayStrat(currentMsTime);

		// 返回现在到今天凌晨的毫秒数
		return currentMsTime - todayZeroMsTime;
	}

	// 将时间戳转换为格式"M.d"
	public static String timeToMonthDay(Long time) {
		SimpleDateFormat sdf = new SimpleDateFormat("M.d");
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(time);
		Date date = c.getTime();
		return sdf.format(date);
	}

	// 将时间戳转换为 格式"yyyy-MM-dd HH:mm:ss"
	public static String TimeToString(Long time) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(time);
		Date date = c.getTime();
		return sdf.format(date);
	}

	// 将时间戳转换为 格式"HH:mm"
	public static String TimeToStringHourMinute(Long time) {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(time);
		Date date = c.getTime();
		return sdf.format(date);
	}

	// 将时间戳转换为 格式"yyyy-MM"
	public static String TimeToStringByMonth(Long time) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(time);
		Date date = c.getTime();
		return sdf.format(date);
	}

	// 将时间戳转换为 格式"yyyy-MM-dd"
	public static String TimeToStringByDay(Long time) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(time);
		Date date = c.getTime();
		return sdf.format(date);
	}

	// 将时间格式"yyyy-MM-dd"转化为时间戳 (获取该日开始时间 yyyy-MM-dd 00:00:00的时间戳)
	public static long StringToTime(String timeString) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date date = sdf.parse(timeString);
			return date.getTime();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	// 时间格式"yyyy-MM" 获取该月开始时间 yyyy-MM-01 00:00:00
	public static long MonthStringToStartTime(String timeString) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		try {
			Date date = sdf.parse(timeString);
			return date.getTime();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	// 时间格式"yyyy-MM" 获取该月最后一天23时59分59秒999毫秒 yyyy-MM-dd-23:59:59 999
	public static long MonthStringToEndTime(String timeString) {
		Calendar ca = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		try {
			Date date = sdf.parse(timeString);
			ca.setTime(date);
			ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));// 获取该月最后一天
			ca.set(Calendar.HOUR_OF_DAY, 23);
			ca.set(Calendar.MINUTE, 59);
			ca.set(Calendar.SECOND, 59);
			ca.set(Calendar.MILLISECOND, 999);
			return ca.getTime().getTime();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	// 获取当前时间戳
	public static long getNow() {
		return new Date().getTime();
	}

	// 获取两天之间相差的天数
	public static int getDateOffset(long time1, long time2) {
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		c1.setTimeInMillis(time1);
		c2.setTimeInMillis(time2);
		return c1.get(Calendar.DAY_OF_YEAR) - c2.get(Calendar.DAY_OF_YEAR);
	}

	// 获取当月开始时间戳 yyyy-MM-01 00:00:00 000
	public static long getStartTimeOfMonth() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		// calendar.set(Calendar.HOUR, 0); 这种是12小时制的修改 ，HOUR_OF_DAY才是24小时制的内容
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTimeInMillis();
	}

	// 获取当月结束时间戳 yyyy-MM-30/31 23:59:59 999
	public static long getEndTimeOfMonth() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, 1); // 下月
		calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTimeInMillis() - 1;// 下月起始时间-1
	}

	// 获取某月的开始时间yyyy-MM-01 00:00:00 000
	public static long getOneMonthStart(int month) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.MONTH, month);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTimeInMillis();
	}

	// 获取某月的结束时间yyyy-MM-01 00:00:00 000
	public static long getOneMonthEnd(int month) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.MONTH, month);
		calendar.add(Calendar.MONTH, 1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTimeInMillis() - 1;
	}

	// 获取当前小时开始的时间戳
	public static long getStartTimeOfHour() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTimeInMillis();
	}

	// 获取当天0点的时间戳
	public static long getStartTimeOfDay() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTimeInMillis();
	}

	// 获取当天结束的时间戳
	public static long getEndTimeOfDay() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 999);
		return calendar.getTimeInMillis();
	}

	// 获取当前周七天的开始时间戳 /周day,0点（day从1-7）
	public static long getDayStartTimeOfWeek(int day) {
		Calendar calendar = Calendar.getInstance();
		if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
			// 周日在Calendar里面属于新的一周，所以我只能先获取上一周
			calendar.set(Calendar.WEEK_OF_MONTH, calendar.get(Calendar.WEEK_OF_MONTH) - 1);
		}
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTimeInMillis() + DAY_LONG * (day - 1);
	}

	// 获取当前周七天的结束时间戳 /周day, 23:59:59 999（day从1-7）
	public static long getDayEndTimeOfWeek(int day) {
		Calendar calendar = Calendar.getInstance();
		if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
			// 周日在Calendar里面属于新的一周，所以我只能先获取上一周
			calendar.set(Calendar.WEEK_OF_MONTH, calendar.get(Calendar.WEEK_OF_MONTH) - 1);
		}
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 999);
		return calendar.getTimeInMillis() + DAY_LONG * (day - 1);
	}

	// 获取那个月的每一周的开始和结束日期
	public static Map<String, Object> getfWeeksOfMonth(String date) throws Exception {
		// date参数如：String date = "2013-09";
		String[] weekStartDay = new String[10];// 保存date月5周的每周的开始日期,格式如"2017-11-04"
		String[] weekEndDay = new String[10];// 保存date月5周的每周的结束日期
		Map<String, Object> resmap = new HashMap<String, Object>();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
		Date date1 = dateFormat.parse(date);
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date1);
		int days = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		log.debug("days:" + days);
		int count = 0;
		for (int i = 1; i <= days; i++) {
			DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
			Date date2 = dateFormat1.parse(date + "-" + i);
			calendar.clear();
			calendar.setTime(date2);
			int k = new Integer(calendar.get(Calendar.DAY_OF_WEEK));
			if (k == 1) {// 若当天是周日
				count++;
				if (i - 6 <= 1) {
					weekStartDay[count] = date + "-01";
				} else {
					if ((i - 6) < 10) {
						weekStartDay[count] = date + "-0" + (i - 6);
					} else {
						weekStartDay[count] = date + "-" + (i - 6);
					}
				}
				if (i < 10) {
					weekEndDay[count] = date + "-0" + i;
				} else {
					weekEndDay[count] = date + "-" + i;
				}
			}
			if (k != 1 && i == days) {// 若是本月最后一天，且不是周日
				count++;
				if ((i - k + 2) < 10) {
					weekStartDay[count] = date + "-0" + (i - k + 2);
				} else {
					weekStartDay[count] = date + "-" + (i - k + 2);
				}
				if (i < 10) {
					weekEndDay[count] = date + "-0" + i;
				} else {
					weekEndDay[count] = date + "-" + i;
				}
			}
		}
		log.debug("weekStartDay={} weekEndDay={} count={} days={}", Arrays.toString(weekStartDay),
				Arrays.toString(weekEndDay), count, days);
		resmap.put("weekStartDay", weekStartDay);// 保存date月5周的每周的开始日期,格式如"2017-11-04"
		resmap.put("weekEndDay", weekEndDay);// 保存date月5周的每周的结束日期
		resmap.put("count", count);// 这个月的周数
		resmap.put("days", days);
		return resmap;
	}

	// 将日期转化为当天的开始的时间戳，"2017-12-28"--> 2017-12-28 00:00:00 000的时间戳
	public static long getDayStrat(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date day = sdf.parse(date);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(day);
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			calendar.set(Calendar.MILLISECOND, 0);
			return calendar.getTimeInMillis();
		} catch (ParseException e) {
			e.printStackTrace();
			return -1;
		}
	}

	// 将日期转化为当天的结束的时间戳，"2017-12-28"--> 2017-12-28 23:59:59 999的时间戳
	public static long getDayEnd(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date day = sdf.parse(date);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(day);
			calendar.set(Calendar.HOUR_OF_DAY, 23);
			calendar.set(Calendar.MINUTE, 59);
			calendar.set(Calendar.SECOND, 59);
			calendar.set(Calendar.MILLISECOND, 999);
			return calendar.getTimeInMillis();
		} catch (ParseException e) {
			e.printStackTrace();
			return -1;
		}
	}

	// 将日期转化为当天的开始的时间戳，"2017-12-28"--> 2017-12-28 00:00:00 000的时间戳
	public static long getDayStrat(long msTime) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(msTime);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTimeInMillis();
	}

	// 将日期转化为当天的结束的时间戳，"2017-12-28"--> 2017-12-28 23:59:59 999的时间戳
	public static long getDayEnd(long msTime) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(msTime);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 999);
		return calendar.getTimeInMillis();
	}

	// 获取当前周的开始时间戳 /周一0点
	public static long getStartTimeOfWeek() {
		Calendar calendar = Calendar.getInstance();
		if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
			// 周日在Calendar里面属于新的一周，所以我只能先获取上一周
			calendar.set(Calendar.WEEK_OF_MONTH, calendar.get(Calendar.WEEK_OF_MONTH) - 1);
		}
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTimeInMillis();
	}

	// 获取当前周的周二开始时间戳 /周二0点
	public static long getStartTimeOfTues() {
		return getStartTimeOfWeek() + DAY_LONG;
	}

	// 获取当前周的周三开始时间戳 /周三0点
	public static long getStartTimeOfWedn() {
		return getStartTimeOfWeek() + DAY_LONG * 2;
	}

	// 获取当前周的周四开始时间戳/周四0点
	public static long getStartTimeOfThur() {
		return getStartTimeOfWeek() + DAY_LONG * 3;
	}

	// 获取当前周的周五开始时间戳/周五0点
	public static long getStartTimeOfFri() {
		return getStartTimeOfWeek() + DAY_LONG * 4;
	}

	// 获取当前周的周六开始时间戳
	public static long getStartTimeOfSatur() {
		return getStartTimeOfWeek() + DAY_LONG * 5;
	}

	// 获取当前周的周日开始时间戳
	public static long getStartTimeOfSun() {
		return getStartTimeOfWeek() + DAY_LONG * 6;
	}

	public static int getHOUR_LONG() {
		return HOUR_LONG;
	}

	public static void setHOUR_LONG(int hOUR_LONG) {
		HOUR_LONG = hOUR_LONG;
	}

	public static int getDAY_LONG() {
		return DAY_LONG;
	}

	public static void setDAY_LONG(int dAY_LONG) {
		DAY_LONG = dAY_LONG;
	}

	public static boolean isSameDay(long msTime1, long msTime2) {
		long msStartTime1 = getDayStrat(msTime1);
		long msStartTime2 = getDayStrat(msTime2);
		return msStartTime1 == msStartTime2;
	}

	public static int changeMsToMinute(long msTime) {
		double floatMinute = msTime / 1000.0 / 60;
		return LongUtils.changeLongToInteger(Math.round(floatMinute));
	}

	public static double changeMsToFloatMinute(long msTime) {
		double floatMinute = msTime / 1000.0 / 60;
		return floatMinute;
	}

	public static double changeMsToDoubleMinute(long msTime) {
		double doubleMinute = msTime / 1000.0 / 60;
		return doubleMinute;
	}

	/**
	 * 周一到周日，依次返回1-7
	 * 
	 * @return
	 */
	public static int getDayOfWeek() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		int day = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		if (day == 0) {
			day = 7;
		}
		return day;
	}

	/**
	 * 1-7返回1，8-14返回2，15-21返回3，22-28返回4，剩下返回5
	 */
	public static int getWeekOfMonth() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		int week = calendar.get(Calendar.DAY_OF_MONTH);
		if (1 <= week && week <= 7) {
			return 1;
		} else if (8 <= week && week <= 14) {
			return 2;
		} else if (15 <= week && week <= 21) {
			return 3;
		} else if (22 <= week && week <= 28) {
			return 4;
		} else {
			return 5;
		}
	}

	/**
	 * 一周以周一为边界，有一个周一就是新的一周
	 * 
	 * @return
	 */
	public static int getWeekOfMonthByMonday() {
		// Calendar today = Calendar.getInstance();
		// Calendar monday = Calendar.getInstance();
		// if (monday.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
		// // 因为Calendar中，周日是一周的第一天，所以要减一周
		// monday.set(Calendar.WEEK_OF_MONTH, -1);
		// }
		// monday.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		// int mondayDayOfMonth = monday.get(Calendar.DAY_OF_MONTH);
		//
		// if (monday.get(Calendar.MONTH) != today.get(Calendar.MONTH) ||
		// mondayDayOfMonth == 1) {
		// // 第一周，周一在1号或者上个月
		// return 1;
		// } else if (2 <= mondayDayOfMonth && mondayDayOfMonth <= 8) {
		// return 2;
		// } else if (9 <= mondayDayOfMonth && mondayDayOfMonth <= 15) {
		// return 3;
		// } else if (16 <= mondayDayOfMonth && mondayDayOfMonth <= 22) {
		// return 4;
		// } else if (23 <= mondayDayOfMonth && mondayDayOfMonth <= 29) {
		// return 5;
		// } else {
		// return 6;
		// }
		Calendar calendar = Calendar.getInstance();
		calendar.setFirstDayOfWeek(Calendar.MONDAY);
		int weekOfMonth = calendar.get(Calendar.WEEK_OF_MONTH);
		return weekOfMonth;
	}

	/**
	 * 一月返回1，二月返回2……
	 * 
	 * @return
	 */
	public static int getMonthOfYear() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		int month = calendar.get(Calendar.MONTH) + 1;
		return month;
	}

	/**
	 * 获取某天为当年第几周
	 * 
	 * @param date="2017-12-28"
	 * @return
	 * @throws ParseException
	 */
	public static int getWeekOfYear(String day) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = format.parse(day);
		Calendar calendar = Calendar.getInstance();
		// 看Calendar类时，看到了WEEK_OF_YEAR，很实用。但是用时又出现了点小问题，比如2010-01-03，
		// 返回的结果是2（即2010年的第二个星期），原因是美国是以周日为每周的第一天。所以”常量字段值”如下。
		// SUNDAY ：1 MONDYA ：2 TUESDAY ：3 WEDNESDAY ：4 THURSDAY ： 5 FRIDAY ： 6
		// SATURDAY：7
		calendar.setFirstDayOfWeek(Calendar.MONDAY);
		calendar.setTime(date);
		return calendar.get(Calendar.WEEK_OF_YEAR);
	}

	/**
	 * 获取当年的第一天
	 * 
	 * @param year
	 * @return
	 */
	public static long getCurrYearFirst() {
		Calendar currCal = Calendar.getInstance();
		int currentYear = currCal.get(Calendar.YEAR);
		return getYearFirst(currentYear);
	}

	/**
	 * 获取某年第一天日期
	 * 
	 * @param year
	 *            年份
	 * @return Date
	 */
	public static long getYearFirst(int year) {
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(Calendar.YEAR, year);
		return calendar.getTimeInMillis();
	}

	// 判断选择的日期是否是今天
	public static boolean isToday(long time) {
		return isThisTime(time, "yyyy-MM-dd");
	}

	private static boolean isThisTime(long time, String pattern) {
		Date date = new Date(time);
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		String param = sdf.format(date);// 参数时间
		String now = sdf.format(new Date());// 当前时间
		if (param.equals(now)) {
			return true;
		}
		return false;
	}
}
