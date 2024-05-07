package com.gtvt.backendcustomermanagement.utils;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * @author quanNA
 */
public class DateUtil {
    //Using an approximation of the length of a solar year, about 365.2425 24-hour days. This is the amount of time it takes the earth to orbit the sun.
    private final static double AVERAGE_DAY_IN_YEAR = 365.2425;
    private final static int AVERAGE_MILLIS_PER_DAY = 24 * 60 * 60 * 1000;
    private final static double AVERAGE_MILLIS_PER_MONTH = AVERAGE_DAY_IN_YEAR * 24 * 60 * 60 * 1000 / 12;

    /**
     * Hang so chuyen doi tu gio sang giay
     */
    public static final long CONST_HOUR_2_SECOND = 60l * 60l;

    /**
     * Hang so chuyen doi tu phut sang giay
     */
    public static final long CONST_MINUTE_2_SECOND = 60l;

    /**
     * Hang so chuyen doi tu gio sang mili giay
     */
    public static final long CONST_HOUR_2_MILISECOND = 60l * 60l * 1000l;

    /**
     * Hang so chuyen doi tu phut sang mili giay
     */
    public static final long CONST_MINUTE_2_MILISECOND = 60l * 1000l;

    /**
     * Hang so chuyen doi tu giay sang mili giay
     */
    public static final long CONST_SECOND_2_MILISECOND = 1000l;

    /**
     * Dinh dang ngay thang: <b>dd/MM/yyyy hh:mm:ss</b>
     */
    public static final String DATE_FORMAT = "dd/MM/yyyy hh:mm:ss";

    public static final String DATE_FORMAT_2 = "yyyy-MM-dd hh:mm:ss";

    public static final String DATE_FORMAT_3 = "dd-MMM-YYYY";

    /**
     * Kiem tra 1 string co ep duoc sang date theo dinh dang
     * {@linkplain #DATE_FORMAT}
     *
     * @param value
     * @return boolean
     * @author haind6
     */
    public static boolean isString2Date(String value) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
            sdf.parse(value);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Kiem tra 1 string co ep duoc sang date theo dinh dang
     * {@linkplain #DATE_FORMAT}
     *
     * @param value
     * @return boolean
     * @author haind6
     */
    public static boolean isString2DateUS(String value) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT, new Locale("en_US"));
            sdf.parse(value);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Ep kieu string sang date theo dinh dang {@linkplain #DATE_FORMAT}
     *
     * @param value
     * @return Date
     * @author haind6
     */
    public static Date parseString2DateMMM(String value) {
        try {
            Date date = new SimpleDateFormat("dd-MMM-yy").parse(value);
//            String dateFormat  = DateFormatUtils.format(date, "dd-MM-yyyy");
            return date;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Ep kieu string sang date theo dinh dang {@linkplain #DATE_FORMAT_2}
     *
     * @param value
     * @return Date
     * @author haind6
     */
    public static Date parseString2Date2(String value) {
        if (isString2Date(value)) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_2);
                return sdf.parse(value);
            } catch (ParseException e) {
                return null;
            }
        } else {
            return null;
        }
    }

    public static Date addToDate(Date currentDate, int field, int amountToAdd) {
        Calendar c = Calendar.getInstance();
        c.setTime(currentDate);
        c.add(field, amountToAdd);
        return c.getTime();
    }

    /**
     * Ep kieu 1 string sang date theo dinh dang truyen vao
     *
     * @param value
     * @param pattern
     * @return null neu khong ep kieu duoc
     * date  neu ep kieu duoc
     * @author haind6
     */
    public static Date string2Date(String value, String pattern) {
        Date result = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            result = sdf.parse(value);
        } catch (Exception e) {
            result = null;
        }
        return result;
    }

    public static String getStringCurrentDateByFormat(String format) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(format);
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }

    public static String getStringDateByFormat(Date date, String format) {
        if (date == null)
            return null;
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        return formatter.format(date);
    }

    public static Timestamp getDateNow() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Timestamp now = new Timestamp(calendar.getTime().getTime());
        return now;
    }

    public static Timestamp getDateTimeNow() {
        return new Timestamp(Calendar.getInstance().getTime().getTime());
    }

    public static Timestamp getTimestampFromDate(Date date) {
        return new Timestamp(date.getTime());
    }

    public static Date addHour(Date date, int h, int m) {
        if (date == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY, h);
        c.set(Calendar.MINUTE, m);
        return c.getTime();
    }

    /**
     * So sanh Timestamp1 voi Timestamp2
     *
     * @param tm1
     * @param tm2
     * @return 0  neu Timestamp tm1 bang Timestamp tm2
     * 1  neu Timestamp tm1 lớn hơn Timestamp tm2
     * -1 neu Timestamp tm1 nhỏ hơn Timestamp tm2 hoặc tm1 bằng null or
     * tm2 bằng null
     * @author haind6
     */
    public static int compareTimestam(Timestamp tm1, Timestamp tm2) {
        if (tm1 != null && tm2 != null) {
            if (tm1.getTime() - tm2.getTime() == 0) {
                return 0;
            } else if (tm1.getTime() - tm2.getTime() > 0) {
                return 1;
            } else {
                return -1;
            }
        } else {
            return -1;
        }
    }

    public static boolean dateAfterMiniture(Timestamp date, int minute) {
        if (date == null)
            return false;
        return System.currentTimeMillis() + minute * 60 * 1000l > date.getTime();
    }

    public static double periodOfTimeInMillis(Date date1, Date date2) {
        return (date2.getTime() - date1.getTime());
    }

    public static double periodOfTimeInSec(Date date1, Date date2) {
        return (date2.getTime() - date1.getTime()) / 1000D;
    }

    public static double periodOfTimeInMin(Date date1, Date date2) {
        return (date2.getTime() - date1.getTime()) / (60D * 1000D);
    }

    public static double periodOfTimeInHours(Date date1, Date date2) {
        return (date2.getTime() - date1.getTime()) / (60D * 60D * 1000D);
    }

    public static double periodOfTimeInDays(Date date1, Date date2) {
        return (date2.getTime() - date1.getTime()) / (24D * 60D * 60D * 1000D);
    }

    /**
     * Caculator day of month
     *
     * @return
     */
    public static int dayOfMonth(int month, int year) {
        int temp = 0;
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                temp = 31;
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                temp = 30;
                break;
            case 2:
                if (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0))
                    temp = 29;
                else
                    temp = 28;
                break;
            default:
                temp = 1;
                break;
        }
        return temp;
    }

    /**
     * add Date
     *
     * @return
     */
    @SuppressWarnings("static-access")
    public static Date addDate(Date date, int number) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(c.DATE, number);
        return c.getTime();
    }

    @SuppressWarnings("static-access")
    public static Date addDate(Date date, int number, boolean earliestTime) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(c.DATE, number);
        if (earliestTime) {
            c.set(Calendar.HOUR_OF_DAY, 0);
            c.set(Calendar.MINUTE, 0);
            c.set(Calendar.SECOND, 0);
            c.set(Calendar.MILLISECOND, 0);
        }
        return c.getTime();
    }

    @SuppressWarnings("static-access")
    public static Date addMonth(Date date, int number, boolean earliestTime) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(c.MONTH, number);
        if (earliestTime) {
            c.set(Calendar.HOUR_OF_DAY, 0);
            c.set(Calendar.MINUTE, 0);
            c.set(Calendar.SECOND, 0);
            c.set(Calendar.MILLISECOND, 0);
        }
        return c.getTime();
    }

    public static int calculateAge(Date birthDate) {
        if (birthDate == null) {
            return 0;
        }
        LocalDate currentDate = convertToLocalDate(new Date());
        LocalDate birthday = convertToLocalDate(birthDate);
        return Period.between(birthday, currentDate).getYears();
    }

    public static LocalDate convertToLocalDate(Date dateToConvert) {
        return dateToConvert.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public static int getYear(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        return cal.get(Calendar.YEAR);
    }

    public static int getMonth(Date date) {
        LocalDate localDate = convertToLocalDate(date);
        return localDate.getMonthValue();
    }

    /**
     * Get earliest time of a day (truncate)
     *
     * @param time day and time
     * @return day with 00:00
     */
    static public Date getDay(long time) {
        if (time == 0)
            time = System.currentTimeMillis();
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTimeInMillis(time);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return new Date(cal.getTimeInMillis());
    }

    /**
     * Get earliest time of a day (truncate)
     *
     * @param dayTime day and time
     * @return day with 00:00
     */
    static public Date getDay(Date dayTime) {
        if (dayTime == null)
            return getDay(System.currentTimeMillis());
        return getDay(dayTime.getTime());
    }

    /**
     * Is it the same day
     * DungVT
     *
     * @param one day
     * @param two compared day
     * @return true if the same day
     */
    public static boolean isSameDay(Date one, Date two) {
        GregorianCalendar calOne = new GregorianCalendar();
        if (one != null)
            calOne.setTimeInMillis(one.getTime());
        GregorianCalendar calTwo = new GregorianCalendar();
        if (two != null)
            calTwo.setTimeInMillis(two.getTime());
        return calOne.get(Calendar.YEAR) == calTwo.get(Calendar.YEAR)
                && calOne.get(Calendar.MONTH) == calTwo.get(Calendar.MONTH)
                && calOne.get(Calendar.DAY_OF_MONTH) == calTwo.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * Check có nằm trong khoảng 2 ngày min, max hay không
     * * DungVT
     *
     * @param start ngày min
     * @param end   ngày max
     * @param date  ngày so sánh
     * @return true, false
     */
    public static boolean isDayBetween(Date start, Date end, Date date) {
        return date.after(start) && date.before(end);
    }

    /**
     * Lấy ra số ngày giữa 2 ngày
     * DungVT
     *
     * @param start Ngày đầu
     * @param end   Ngày cuối
     * @return Số ngày giữa 2 ngày
     */
    public static int getDaysBetween(Date start, Date end) {
        return (int) (end.getTime() - start.getTime()) / AVERAGE_MILLIS_PER_DAY;
    }

    /**
     * Lấy ra số tháng giữa 2 ngày
     * DungVT
     *
     * @param start Ngày đầu
     * @param end   Ngày cuối
     * @return Số tháng giữa 2 ngày
     */
    public static double getMonthsBetween(Date start, Date end) {
        BigDecimal bd = BigDecimal.valueOf((end.getTime() - start.getTime()) / AVERAGE_MILLIS_PER_MONTH).setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    /**
     * Lấy năm hiện tại
     * DungVT
     *
     * @return Năm hiện tại
     */
    public static int getCurrentYear() {
        return Calendar.getInstance().get(Calendar.YEAR);
    }

    /**
     * DungVT
     * Chuyển ngày input thành ngày cuối cùng của tháng
     *
     * @param date date input
     * @return Ngày cuối cùng của tháng
     */
    public static Date convertLastDayOfMonth(Date date) {
        LocalDate localDate = convertToLocalDate(date);
        int currentDay = localDate.getDayOfMonth();
        int lastDayOfMonth = dayOfMonth(localDate.getMonthValue(), localDate.getYear());
        return addDate(date, lastDayOfMonth - currentDay, true);
    }

    /**
     * BANGNT
     * Chuyển ngày input thành ngày đầu tiên của tháng
     *
     * @param date date input
     * @return Ngày đầu tiên của tháng
     */
    public static Date convertFirstDayOfMonth(Date date) {
        LocalDate localDate = convertToLocalDate(date);
        return asDate(localDate.withDayOfMonth(1));
    }

    /*
     * Converts XMLGregorianCalendar to java.util.Date in Java
     */
    public static Timestamp toTimestamp(XMLGregorianCalendar calendar) {
        if (calendar == null) {
            return null;
        }
        return new Timestamp(calendar.toGregorianCalendar().getTimeInMillis());
    }

    /*
     * Converts XMLGregorianCalendar to java.sql.Date in Java
     */
    public static java.sql.Date toDate(XMLGregorianCalendar calendar) {
        if (calendar == null) {
            return null;
        }
        return new java.sql.Date(calendar.toGregorianCalendar().getTime().getTime());
    }

    public static LocalDate timestampToLocalDate(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime().toLocalDate();
    }

    public static Date timestampToDate(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return new Date(timestamp.getTime());
    }

    /**
     * Kiểm tra khoảng thời gian có giao nhau không
     *
     * @param startA thời gian bắt đầu A
     * @param endA   thời gian kết thúc A
     * @param startB thời gian bắt đầu B
     * @param endB   thời gian kết thúc B
     * @return true/false
     */
    public static Boolean checkOverlap(Timestamp startA, Timestamp endA,
                                       Timestamp startB, Timestamp endB) {
        return (endB == null || startA == null || !startA.after(endB))
                && (endA == null || startB == null || !endA.before(startB));
    }

    public static Date asDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }

    public static Date asDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static LocalDate asLocalDate(Date date) {
        return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public static LocalDateTime asLocalDateTime(Date date) {
        return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    public static XMLGregorianCalendar messageTimestampXml(GregorianCalendar gregorianCalendar) throws DatatypeConfigurationException {
        gregorianCalendar.setTime(new Date());

        return DatatypeFactory.newInstance().newXMLGregorianCalendar(gregorianCalendar);
    }

    public static String getDateToday (){
        Date date = new Date(); // Your Date object
        // Define the format you want
        SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
        // Convert Date to String
        String dateString = dateFormat.format(date);

       return dateString;
    }
}
