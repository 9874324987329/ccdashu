package com.wx.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * --------------------------------------------------<br/>
 * 功能：日期工具类<br/>
 * 作者：陈成<br/>
 * 日期：2012-7-3<br/>
 * --------------------------------------------------<br/>
 */
public class DateUtils {
	public static final String datePattern = "yyyy-MM-dd";

    public static final String timePattern = "HH:mm:ss";

    public static final String datetimePattern = "yyyy-MM-dd HH:mm:ss";
    public static final String datemmPatterm="yyyy-MM-dd HH:mm";

    private static final DateFormat FORMATTER = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * 获取当前日期（yyyy-MM-dd）
     *
     * @return 当前日期
     */
    public static String getCurrentDate() {
        return new SimpleDateFormat(datePattern).format(new Date());
    }

    public static String getCurrentYear() {
        return new SimpleDateFormat( "yyyy" ).format(new Date());
    }

    /**
     * 获取当前时间（HH:mm:dd）
     *
     * @return 当前时间
     */
    public static String getCurrentTime() {
        return new SimpleDateFormat(timePattern).format(new Date());
    }

    /**
     * 获取当前日期和时间（yyyy-MM-dd HH:mm:ss）
     *
     * @return 当前日期和时间
     */
    public static String getCurrentDateTime() {
        return new SimpleDateFormat(datetimePattern).format(new Date());
    }

    /**
     * 格式化日期为字符串
     *
     * @param date 日期
     * @return 日期字符串
     */
    public static String formatDate(Date date) {
        return new SimpleDateFormat(datePattern).format(date);
    }

    /**
     * 根据模式格式化日期为字符串
     *
     * @param date    日期
     * @param pattern 模式
     * @return 日期字符串
     */
    public static String formatDate(Date date, String pattern) {
        return new SimpleDateFormat(pattern).format(date);
    }
    /**
     * String toString
     * @param date
     * @param pattern
     * @return
     */
    public static String formatStringToString(String date, String pattern) {
    	if(StringUtil.isEmpty(date))return "";
    	 Date d=new Date();
    	 try {
		   d=parse(date,pattern);
		} catch (ParseException e) {
 			e.printStackTrace();
		}
        return new SimpleDateFormat(pattern).format(d);
    }


    /**
     * 解析字符串为日期
     *
     * @param dateString 日期字符串
     * @return 日期
     * @throws ParseException 解析异常
     */
    public static Date parseDate(String dateString) throws ParseException {
        return new SimpleDateFormat(datePattern).parse(dateString);
    }

    /**
     * 解析字符串为日期
     *
     * @param dateString 日期字符串
     * @param pattern    模式
     * @return 日期
     * @throws ParseException 解析异常
     */
    public static Date parse(String dateString, String pattern) throws ParseException {
        return new SimpleDateFormat(pattern).parse(dateString);
    }

    /**
     * 根据偏移量获取日期字符串
     *
     * @param offset 偏移量（正负整数）
     * @return 日期字符串
     * @throws ParseException 解析异常
     */
    public static Date calcDate(Integer offset) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, offset);
        return DateUtils.parseDate(new SimpleDateFormat(datePattern).format(calendar.getTime()));
    }

    /**
     * 获取当前时间
     *
     * @return 当前时间（long型）
     */
    public static long getTime() {
        return System.currentTimeMillis();
    }

    /**
     * 格式化时间为字符串
     *
     * @param time    时间
     * @param pattern 模式
     * @return 日期字符串
     */
    public static String formatTime(long time, String pattern) {
        return time != 0 ? DateUtils.formatDate(new Date(time), pattern) : "";
    }

    /**
     * 转化时间为**前形式
     *
     * @return
     */
    public static String formatTimes(String date) {

        Date tempDate = null;
        try {
            tempDate = parse(date, "yyyy-MM-dd HH:mm:ss");
        } catch (ParseException e) {
            System.out.println("时间格式错误！eg:yyyy-MM-dd HH:mm:ss" + e);
        }

        long lDate = tempDate.getTime();

        String dateStr = "";
        int days = 0;
        int hours = 0;
        int minutes = 0;
        long timesHour = 0;
        long timesMinute = 0;
        long currentTime = new Date().getTime();
        if (currentTime >= lDate) {
            long times = currentTime - lDate;
            days = (int) (times / 86400000);
            timesHour = times % 86400000;
            hours = (int) (timesHour / 3600000);
            timesMinute = timesHour % 3600000;
            minutes = (int) (timesMinute / 60000);
            if (days == 0 && hours == 0 && minutes == 0) {
                dateStr = "刚刚";
            } else if (days > 30) {
                dateStr = String.valueOf(days / 30) + "月前";
            } else if (days != 0) {
                dateStr = String.valueOf(days) + "天前";
            } else if (days == 0 && hours != 0) {
                dateStr = String.valueOf(hours) + "小时前";
            } else if (days == 0 && hours == 0 && minutes != 0) {
                dateStr = String.valueOf(minutes) + "分钟前";
            }
        }
        return dateStr;
    }

    /**
     * 计算现在时间到未来时间有长
     *
     * @param future
     * @return **天/小时/分钟/秒
     */
    public static String countDateTime(long future) {
        if (future > 0) {
            long day = future / (24 * 60 * 60 * 1000);
            long hour = (future / (60 * 60 * 1000) - day * 24);
            long minute = ((future / (60 * 1000)) - day * 24 * 60 - hour * 60);
            long second = (future / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - minute * 60);
            if (day > 0) {
                return day + "天" + hour + "小时" + minute + "分" + second + "秒";
            } else if (hour > 0) {
                return hour + "小时" + minute + "分" + second + "秒";
            } else if (minute > 0) {
                return minute + "分" + second + "秒";
            } else if (second > 0) {
                return second + "秒";
            }
        }
        return "";
    }

    public static String countDateTime(long start, long end) {
        long future = (end - start);//秒
        if (future > 0) {
            long day = future / (24 * 60 * 60 * 1000);
            long hour = (future / (60 * 60 * 1000) - day * 24);
            long minute = ((future / (60 * 1000)) - day * 24 * 60 - hour * 60);
            long second = (future / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - minute * 60);
            if (day > 0) {
                return day + "天" + hour + "小时" + minute + "分" + second + "秒";
            } else if (hour > 0) {
                return hour + "小时" + minute + "分" + second + "秒";
            } else if (minute > 0) {
                return minute + "分" + second + "秒";
            } else if (second > 0) {
                return second + "秒";
            } else if (future > 0) {
                return future + "毫秒";
            }
        }
        return "";
    }

    /**
     * 创建日期
     *
     * @param year  年
     * @param month 月
     * @param date  日
     * @return java.util.Date
     */
    public static Date createDate(int year, int month, int date) {
        Calendar c = Calendar.getInstance();
        c.set(year, month - 1, date, 0, 0, 0);
        return c.getTime();
    }

    /**
     * 创建日期时间
     *
     * @param year   年
     * @param month  月
     * @param date   日
     * @param hour   时
     * @param minute 分
     * @param second 秒
     * @return java.util.Date
     */
    public static Date createDatetime(int year, int month, int date, int hour, int minute, int second) {
        Calendar c = Calendar.getInstance();
        c.set(year, month - 1, date, hour, minute, second);
        return c.getTime();
    }

    /**
     * 重置日期的时间
     *
     * @param srcDate 源日期
     * @param hour    时
     * @param minute  分
     * @param second  秒
     * @return 重置后的日期
     */
    public static Date resetTime(Date srcDate, int hour, int minute, int second) {
        return resetTime(srcDate, hour, minute, second, 0);
    }

    public static Date resetTime(Date srcDate, int hour, int minute, int second, int millisecond) {
        Calendar c = Calendar.getInstance();
        c.setTime(srcDate);
        c.set(Calendar.HOUR_OF_DAY, hour);
        c.set(Calendar.MINUTE, minute);
        c.set(Calendar.SECOND, second);
        c.set(Calendar.MILLISECOND, millisecond);
        return c.getTime();
    }

    /**
     * 获取两日期之间的日期  (包括两个边界) ex: 2013-5-1,  2013-5-3 返回 ['2013-5-1','2013-5-2','2013-5-3']
     *
     * @param startDate 开始日期
     * @param endDate   截止日期
     * @return 日期集合
     */
    public static List<Date> getDatesBetween(Date startDate, Date endDate) {
        List<Date> dateList = new ArrayList<Date>();
        if (startDate.compareTo(endDate) > 0) {
        } else if (startDate.compareTo(endDate) == 0) {
            dateList.add(startDate);
        } else {
            Calendar startCal = Calendar.getInstance();
            Calendar endCal = Calendar.getInstance();
            startCal.setTime(startDate);
            endCal.setTime(endDate);
            dateList.add(startDate);
            startCal.add(Calendar.DAY_OF_YEAR, 1);
            while (startCal.compareTo(endCal) < 0) {
                dateList.add(startCal.getTime());
                startCal.add(Calendar.DAY_OF_YEAR, 1);
            }
            dateList.add(endDate);
        }
        return dateList;
    }

    /**
     * 强制截断某日期
     *
     * @param srcDate     源日期
     * @param destPattern 目标某格式
     * @return
     * @throws ParseException
     */
    public static Date forceConvert(Date srcDate, String destPattern) {
        DateFormat df = new SimpleDateFormat(destPattern);
        try {
            return df.parse(df.format(srcDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) throws Throwable {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

     }
    /**
     * 获取三十天之前的日期
     * @return
     */
    public static String getThirtyBeforeTime(){
    	 Calendar startCal = Calendar.getInstance();
    	 startCal.add(Calendar.DAY_OF_YEAR, -30);
    	return DateUtils.formatDate(startCal.getTime(), DateUtils.datePattern);
    }

    public static String getBeforeTime(int days){
        Calendar startCal = Calendar.getInstance();
        startCal.add(Calendar.DAY_OF_YEAR, -days);
        return DateUtils.formatDate(startCal.getTime(), DateUtils.datePattern);
    }

    /**
     * 根据calendarType在某个时间上增加时间跨度
     * @param date
     * @param calendarType
     * @param value
     * @return
     */
    public static Date addByCalendarType(Date date, int calendarType, int value) {
        Calendar c = Calendar.getInstance();
        c.add( calendarType, value);
        return c.getTime();
    }

    public static boolean equalDate(Date bigDate,Date smallDate){
        return bigDate.getTime()>smallDate.getTime();
    }

	/**
	 * String转Date
	 *
	 * @param sdate
	 *            日期字符串
	 * @param fmString
	 *            指定日期格式
	 * @return
	 */
	public static Date toDate(String sdate, String fmString) {
		DateFormat df = new SimpleDateFormat(fmString);
		try {
			return df.parse(sdate);
		} catch (ParseException e) {
			throw new RuntimeException("日期格式不正确 ");
		}
	}

	/**
	 * 如果下线时间大于当前时间，return 1，
	 * @param time
	 * @return
	 * @throws ParseException
	 */
	public int getTimeOffline(String time) throws ParseException {
		 if (StringUtil.isNotEmpty(time)) {
			Date d1 = DateUtils.parse(time, "yyyy-MM-dd hh:mm:ss");
			Date now = new Date();
			if (now.getTime() > d1.getTime()) {
				return 1;
			} else {
				return 0;
			}
		}
		return 0;

	}
	/**
	 * 判断两个时间的大小.
	 *
	 * @param startTime
	 * @param endTime
	 * @return
	 * @author Jason ni 2014/9/9
	 */
	public static boolean isCompareTime(Date startTime, Date endTime) {
		if (endTime.getTime() > startTime.getTime()) {
			return true;
		} else {
			return false;
		}
	}

	// 计算两个日期之间有多少天
	public static int getDaysBetween(Date startDate, Date endDate) {
		Calendar d1 = Calendar.getInstance();
		d1.setTime(startDate);
		Calendar d2 = Calendar.getInstance();
		d2.setTime(endDate);
		if (d1.after(d2)) { // swap dates so that d1 is start and d2 is end
			Calendar swap = d1;
			d1 = d2;
			d2 = swap;
		}
		int days = d2.get(Calendar.DAY_OF_YEAR) - d1.get(Calendar.DAY_OF_YEAR);
		int y2 = d2.get(Calendar.YEAR);
		if (d1.get(Calendar.YEAR) != y2) {
			d1 = (Calendar) d1.clone();
			do {
				days += d1.getActualMaximum(Calendar.DAY_OF_YEAR);// 得到当年的实际天数
				d1.add(Calendar.YEAR, 1);
			} while (d1.get(Calendar.YEAR) != y2);
		}
		return days;
	}

    // utc 时间戳 转换本地时间
    private static Calendar getStringToCal(String date) {
        final String year = date.substring(0, 4);
        final String month = date.substring(5, 7);
        final String day = date.substring(8, 10);
        final String hour = date.substring(11, 13);
        final String minute = date.substring(14, 16);
        final String second = date.substring(17, 19);
        final int millisecond = Integer.valueOf(date.substring(20, 23));
        Calendar result =
                new GregorianCalendar(Integer.valueOf(year),
                        Integer.valueOf(month) - 1, Integer.valueOf(day),
                        Integer.valueOf(hour), Integer.valueOf(minute),
                        Integer.valueOf(second));
        result.set(Calendar.MILLISECOND, millisecond);
        result.setTimeZone(TimeZone.getTimeZone("Etc/UTC"));
        return result;
    }

    /**
     * unix时间戳转换为dateFormat
     *
     * @param beginDate
     * @return
     */
    public static String timestampToDate(String beginDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String sd = sdf.format(new Date(Long.parseLong(beginDate)));
        return sd;
    }
    /**
     * unix时间戳转换为dateFormat
     *
     * @param beginDate
     * @return
     */
    public static String timestampToDate(long beginDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String sd = sdf.format(new Date(beginDate));
        return sd;
    }
    /**
     * 自定义格式时间戳转换
     *
     * @param beginDate
     * @return
     */
    public static String timestampToDate(long beginDate,String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        if(new Long(beginDate).toString().length()==10){
            beginDate=new Long(beginDate*1000);
        }
        String sd = sdf.format(new Date(beginDate));
        return sd;
    }

    /**
     * 指定一个时间添加 给定 小时
     *
     * @param
     * @return
     */
    public static Date addHourToTime(Date date,int hours){
        Calendar ca=Calendar.getInstance();
        ca.setTime(date);
        ca.add(Calendar.HOUR, hours);
        date=ca.getTime();
        return date;
    }

    public static List<String>  getTwoTimeDates(String begin_time, String end_time) throws ParseException {
        Calendar startDay = Calendar.getInstance();
        Calendar endDay = Calendar.getInstance();

        startDay.setTime(FORMATTER.parse(begin_time));
        endDay.setTime(FORMATTER.parse(end_time));
        List<String> dates = new ArrayList<String>();
        // 现在打印中的日期
        Calendar currentPrintDay = startDay;
        while (true) {
            // 日期加一
            currentPrintDay.add(Calendar.DATE, 1);
            // 日期加一后判断是否达到终了日，达到则终止打印
            if (currentPrintDay.compareTo(endDay) == 0) {
                break;
            }
            // 打印日期
            dates.add(FORMATTER.format(currentPrintDay.getTime()));
        }
        return dates;
    }

    public static List<String>  getTwoDateMonths(String begin_time, String end_time) throws ParseException {
        Calendar startDay = Calendar.getInstance();
        Calendar endDay = Calendar.getInstance();

        startDay.setTime(FORMATTER.parse(begin_time));
        endDay.setTime(FORMATTER.parse(end_time));
        List<String> dates = new ArrayList<String>();

        while (startDay.before(endDay)) {
            int year = startDay.get(Calendar.YEAR);
            int month = startDay.get(Calendar.MONTH) + 1;
            //System.out.println(year+"-"+month+"");
            dates.add(year+"-"+month);
            startDay.add(Calendar.MONTH, 1);
        }
        return dates;
    }
}

