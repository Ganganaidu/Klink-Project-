package com.klink.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DateFormates {

	/** Time zone difference (or)offset */
	public static int getTimezoneOffset() {

		TimeZone timezone = TimeZone.getDefault();
		// String TimeZoneName = timezone.getDisplayName();
		int TIMEZONE_OOFSET = timezone.getRawOffset() / (1000);
		return TIMEZONE_OOFSET;
	}

	/**Current GMT time */
	public static long getCurrentGmtTimeStamp(){
		Date date = null;

		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
		Date currentLocalTime = cal.getTime();
		DateFormat dateformat = new SimpleDateFormat("yyy-MM-dd HH:mm:ss.SSS"); 
		dateformat.setTimeZone(TimeZone.getTimeZone("GMT")); 
		String currentTimeStamp = dateformat.format(currentLocalTime);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

		try {
			date = sdf.parse(currentTimeStamp);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		long currentTimeStampMills = date.getTime();
		return currentTimeStampMills;
	}

	/** converting current date and time into time stamp(long value)*/
	public static long getCurrentDateTimestamp(String currentDateAndTime){

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date date = null;
		try {
			date = sdf.parse(currentDateAndTime);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		long currentTimeStampMills = date.getTime();
		return currentTimeStampMills;
	}

	//
	public static String getCurrentDateandTime(){

		String targetDateTime = "";
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		//get current date time with Date()
		Date date = new Date();
		targetDateTime = dateFormat.format(date);
		//System.out.println(dateFormat.format(date));
		return targetDateTime;
	}

	/**
	 * Convert GMT to local time
	 * 
	 * @throws ParseException
	 */
	public static String convertGmtToLocalTime(String datetime) {

		DateFormat formatter = new SimpleDateFormat("yyy-MM-dd HH:mm");
		Date date;
		long localTimeStamp = 0;

		try {
			date = (Date) formatter.parse(datetime);
			localTimeStamp = (date.getTime() / (1000L) + getTimezoneOffset());// NEED

		} catch (ParseException e) {
			e.printStackTrace();
		}
		return new SimpleDateFormat("yyy-MM-dd HH:mm aa")
		.format(localTimeStamp * 1000);
	}

	/* Convert Date Format according to Open Activity View
	 * */
	public static String convertDateTimeFormat(String datetime) {

		DateFormat formatter = new SimpleDateFormat("yyy-MM-dd HH:mm");
		Date dateObj;String newDateStr = null ;
		try {
			dateObj = formatter.parse(datetime);
			SimpleDateFormat postFormater = new SimpleDateFormat("dd MMM yyyy, hh:mm aa"); 

			newDateStr = postFormater.format(dateObj); 

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return newDateStr; 
	}


	public static String getNextMonthdate(){

		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH,1);//one months from now
		Date currentLocalTime = cal.getTime();
		DateFormat date = new SimpleDateFormat("yyy-MM-dd");

		return date.format(currentLocalTime);
	}

	//Getting LogDateTme, this time is also used for gps date time
	public static String getlogDateTime(){
		String log_datetime = "";

		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
		Date currentLocalTime = cal.getTime();
		DateFormat date = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");   
		date.setTimeZone(TimeZone.getTimeZone("GMT"));
		log_datetime = date.format(currentLocalTime);

		return log_datetime;
	}

	// convert Date and Time format according to Work force ListView
	public static String convertDateTime(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date testDate = null;
		try {
			testDate = sdf.parse(date);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy");
		String newFormat = formatter.format(testDate);
		//System.out.println(".....Date..."+newFormat);
		return newFormat;
	}

	// convert Time format according to comments ListView
	public static String convertTime(String time) {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		Date testDate = null;
		try {
			testDate = sdf.parse(time);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		SimpleDateFormat formatter = new SimpleDateFormat("hh:mm aa");
		String newFormat = formatter.format(testDate);
		//System.out.println(".....Date..."+newFormat);
		return newFormat;
	}

	/** Converting local time mNotificationModel.o GMT time */
	public static String getGmtDateTime(String mTime) {

		String time = "";
		DateFormat formatter = new SimpleDateFormat("yyy-MM-dd HH:mm");
		try {
			Date dateObj = formatter.parse(mTime);
			DateFormat date = new SimpleDateFormat("yyy-MM-dd HH:mm");
			date.setTimeZone(TimeZone.getTimeZone("GMT"));
			time = date.format(dateObj);
			// Log.d(TAG,"mDataSendModel.mEntity3------->"+time);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return time;
	}

	/** @return TRUE if any date less than present date */
	public static boolean checkLessThanDateTime(String startDate, String endDate) {
		//SimpleDateFormat graterThan = new SimpleDateFormat("dd MMM yyyy HH:mm");
		SimpleDateFormat dateFromate = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		boolean b = false; //2014-5-31 12:14

		try {
			if (dateFromate.parse(startDate).before(dateFromate.parse(endDate))) {
				b = true;// If start date is before end date
			}
			else if(dateFromate.parse(startDate).equals(dateFromate.parse(endDate)))
			{
				b = true;// If start date is before end date
			}
			else {
				b = false; // If start date is after the end date
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return b;
	}

	public static String getCurrentDate() {
		Calendar c = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		String formattedDate = df.format(c.getTime());
		return formattedDate;
	}

	/*
	 * Show date and time Today, Yesterday or normal date format 
	 */
	public static String convertTodayDate(String date)  {

		Date dateFormat;
		String newDateStr = "";

		try {

			Date dateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(date);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(dateTime);
			Calendar today = Calendar.getInstance();
			Calendar yesterday = Calendar.getInstance();
			yesterday.add(Calendar.DATE, -1);

			DateFormat timeFormatter = new SimpleDateFormat("hh:mm aa");
			DateFormat formatter = new SimpleDateFormat("yyy-MM-dd HH:mm");

			if (calendar.get(Calendar.YEAR) == today.get(Calendar.YEAR) && calendar.get(Calendar.DAY_OF_YEAR) == today.get(Calendar.DAY_OF_YEAR)) {

				return "Today " + timeFormatter.format(dateTime);
			} else if (calendar.get(Calendar.YEAR) == yesterday.get(Calendar.YEAR) && calendar.get(Calendar.DAY_OF_YEAR) == yesterday.get(Calendar.DAY_OF_YEAR)) {

				return "Yesterday " + timeFormatter.format(dateTime);
			} else {

				try {
					dateFormat = formatter.parse(date);
					SimpleDateFormat postFormater = new SimpleDateFormat("dd MMM yyyy, hh:mm aa"); 
					newDateStr = postFormater.format(dateFormat); 
				} catch (ParseException e) {
					e.printStackTrace();
				}
				return newDateStr;
			}
		} catch (Exception e){
			e.printStackTrace();
		}
		return newDateStr;
	}


	/**
	 * Convert GMT to local time
	 * 
	 * @throws ParseException
	 */
	public static String convertGmtToLocalTimeWithMinutes(String datetime) {

		DateFormat formatter = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
		Date date;
		long localTimeStamp = 0;

		try {
			date = (Date) formatter.parse(datetime);
			localTimeStamp = (date.getTime() / (1000L) + getTimezoneOffset());// NEED

		} catch (ParseException e) {
			e.printStackTrace();
		}
		return new SimpleDateFormat("yyy-MM-dd HH:mm:ss")
		.format(localTimeStamp * 1000);
	}

	// this method converts the given date into milliseconds
	public static long alarmDateToMillis(String dateString) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-dd HH:mm");
		Date date = sdf.parse(dateString);
		long dateInMillis = date.getTime();
		return dateInMillis;
	}
}
