package com.hbe.ms.common.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

/**
 * Date utility for MicroService. Mostly copied from HBEFramework DateUtils
 * 
 * @author mrahaman
 *
 */
public class DateUtils {

	private static final String DATE_FORMAT = "MM/dd/yyyy";
	public static final String YYYYMMDDHHMMSS = "yyyy-MM-dd hh:mm:ss";
	private static final int ZERO = 0;
	public static final String CENTURY_DATE_FORMAT_WITH_DASHES = "yyyy-MM-dd";

	private DateUtils() {
		// Don't allow to create DateUtils object
	}

	
	
    
    
	/**
	 * Returns the current date in a Timestamp format.
	 * 
	 * @return Timestamp
	 */
	public static Timestamp getCurrentDate(Timestamp systemDate) {
		if (systemDate != null) {
			return systemDate;
		}
		// Production
		return new Timestamp(Calendar.getInstance().getTimeInMillis());
	}

	/**
	 * Calculates 9 months in milliseconds and subtracts it from input Timestamp
	 * 
	 * @param stamp Timestamp to compare against.
	 * @return New Timestamp object for stamp minus 9 months.
	 */
	public static Timestamp getNineMonthsPriorToDate(Timestamp stamp) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(stamp.getTime());
		calendar.add(Calendar.MONTH, -9);

		return new Timestamp(calendar.getTimeInMillis());
	}

	/**
	 * Determines if a Timestamp object is within the given range, inclusively,
	 * while ignoring time.
	 * 
	 * @param stamp      Timestamp object to be tested.
	 * @param lowerBound Lower boundary to compare against.
	 * @param upperBound Upper boundary to compare against.
	 * @return True if stamp equals either boundary or is between them. Returns
	 *         false by default if any input stamps are null.
	 */
	public static boolean isTimestampInRangeIgnoringTime(Timestamp stamp, Timestamp lowerBound, Timestamp upperBound) {
		boolean inRange = false;

		if (null == stamp || null == lowerBound || null == upperBound) {
			return inRange;
		} else {
			if (compareTimeStampIgnoringTime(stamp, lowerBound) || compareTimeStampIgnoringTime(stamp, upperBound)
					|| (stamp.after(lowerBound) && stamp.before(upperBound))) {
				inRange = true;
			}
		}

		return inRange;
	}

	/**
	 * This method is used to compare the two timestamp objects provided as input.
	 * The timestamp are converted to string objects using dateformatters and
	 * compared.
	 * 
	 * The time is ignored for the timestamps being compared.
	 * 
	 * @param ts1
	 * @param ts2
	 * @return
	 */
	public static boolean compareTimeStampIgnoringTime(Timestamp ts1, Timestamp ts2) {
		boolean flag = false;
		if (ts1 != null && ts2 != null) {
			String ts1Str = formatTimeStampToString(ts1);
			String ts2Str = formatTimeStampToString(ts2);
			if (ts1Str != null && ts2Str != null) {
				flag = ts1Str.equalsIgnoreCase(ts2Str);
			}
		}
		return flag;
	}

	/**
	 * This is a public utility method which returns a String representation of
	 * TimeStamp object. The returned String is formatted as per the application
	 * wide date format.
	 * 
	 * @param ts
	 * @return String - formatted string representation of date
	 */
	public static String formatTimeStampToString(Timestamp ts) {
		Date date = new Date(ts.getTime());
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
		return sdf.format(date);
	}

	/**
	 * returns true if the difference in months between given date and current date
	 * is within range
	 * 
	 * @param date
	 * @param startRange
	 * @param endRange
	 * @return
	 */
	public static boolean isDateDifferenceWithinRange(Timestamp date, int startRange, int endRange,
			Timestamp currentDate) {
		boolean isWithinRange = false;
		Calendar startDate = Calendar.getInstance();
		Calendar endDate = Calendar.getInstance();
		startDate.setTime(date);
		startDate.add(Calendar.MONTH, startRange);
		// Adding 1 day to exclude the last date of the starting month
		startDate.add(Calendar.DATE, 1);
		endDate.setTime(date);
		endDate.add(Calendar.MONTH, endRange);
		isWithinRange = DateUtils.isTimestampInRangeIgnoringTime(currentDate,
				new Timestamp(startDate.getTime().getTime()), new Timestamp(endDate.getTime().getTime()));
		return isWithinRange;
	}

	
	public static Timestamp convertStringToTimestampWithTime(String strDate) {
		if(null!=strDate) {
			try {
				DateFormat formatter;
				formatter = new SimpleDateFormat(YYYYMMDDHHMMSS);
				Date date = formatter.parse(strDate);
				return new Timestamp(date.getTime());
			} catch (ParseException e) {
				e.printStackTrace();
				return null;
			}
		}else {
			return null;
		}
	}
	/**
	 * Gets the min time with that year, month and day. and also Strips off the
	 * hour, min, sec and millis part of timestamp and returns it.
	 * 
	 * @param dateOfBirth
	 * @return
	 */
	public static Timestamp getLowerBound(Timestamp dateOfBirth) {

		Calendar cal = Calendar.getInstance();

		cal.setTimeInMillis(dateOfBirth.getTime());

		cal.set(Calendar.HOUR_OF_DAY, ZERO);
		cal.set(Calendar.MINUTE, ZERO);
		cal.set(Calendar.SECOND, ZERO);
		cal.set(Calendar.MILLISECOND, ZERO);

		return new Timestamp(cal.getTimeInMillis());

	}

	/**
	 * gets difference in days for the given dates.
	 * 
	 */
	public static long getDifferenceInDaysForGivenDates(Timestamp date1, Timestamp date2) {

		Calendar firstDate = null;
		Calendar secondDate = null;

		firstDate = Calendar.getInstance();
		secondDate = Calendar.getInstance();
		firstDate.setTime(date1);
		secondDate.setTime(date2);
		// Get the difference between two dates in milliseconds
		long diffInMillisec  = secondDate.getTimeInMillis() - firstDate.getTimeInMillis();
		
		// Get difference between two dates in days
		return diffInMillisec / (24 * 60 * 60 * 1000);
	}

    public static Timestamp formatStringToTimestamp(String date, String stringInFormat)
            throws ParseException {
        Timestamp resp = null;
        if (date != null && !StringUtils.isBlank(date)) {
            SimpleDateFormat sdf = new SimpleDateFormat(stringInFormat);
            sdf.setLenient(false);
            Date dateObj = sdf.parse(date);
            resp = new Timestamp(dateObj.getTime());
        }
        return resp;
    }


    /**
	 * format date from long to String
	 * @param longDate
	 * @return String
	 */
	public static String formatLongTimestampToString(long longDate) {
		Timestamp ts = new Timestamp(longDate);
		Date date = new Date(ts.getTime());

		DateFormat dateFormat = new SimpleDateFormat(YYYYMMDDHHMMSS);

		return dateFormat.format(date);
	}
}