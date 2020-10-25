package com.monstarlab.rms.util;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateUtil {

	// convert month, year to start Date, ex. 04-01-2019
	public static LocalDate convertStartDate(Integer month, Integer year) throws ParseException {		
		String date = String.format("%02d", month) + "." + year.toString();
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM.yyyy");
		YearMonth ym = YearMonth.parse(date, formatter);
		
		return ym.atDay(1);
	}
	
	// convert month, year to end Date, ex. 04-30-2019
	public static LocalDate convertEndDate(Integer month, Integer year) throws ParseException {		
		String date = String.format("%02d", month) + "." + year.toString();
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM.yyyy");
		YearMonth ym = YearMonth.parse(date, formatter);
		
		return ym.atEndOfMonth();
	}
	
	// Calculate working days between 2 dates
	public static long workingDays(Date start, Date end){
	    //Ignore argument check

	    Calendar c1 = Calendar.getInstance();
	    c1.setTime(start);
	    int w1 = c1.get(Calendar.DAY_OF_WEEK);
	    c1.add(Calendar.DAY_OF_WEEK, -w1);

	    Calendar c2 = Calendar.getInstance();
	    c2.setTime(end);
	    int w2 = c2.get(Calendar.DAY_OF_WEEK);
	    c2.add(Calendar.DAY_OF_WEEK, -w2);

	    //end Saturday to start Saturday 
	    long days = (c2.getTimeInMillis()-c1.getTimeInMillis())/(1000*60*60*24);
	    long daysWithoutWeekendDays = days-(days*2/7);

	    // Adjust days to add on (w2) and days to subtract (w1) so that Saturday
	    // and Sunday are not included
	    if (w1 == Calendar.SUNDAY && w2 != Calendar.SATURDAY) {
	        w1 = Calendar.MONDAY;
	    } else if (w1 == Calendar.SATURDAY && w2 != Calendar.SUNDAY) {
	        w1 = Calendar.FRIDAY;
	    } 

	    if (w2 == Calendar.SUNDAY) {
	        w2 = Calendar.MONDAY;
	    } else if (w2 == Calendar.SATURDAY) {
	        w2 = Calendar.FRIDAY;
	    }

	    return daysWithoutWeekendDays-w1+w2;
	}
	
	// get Next 4 months
	public static List<Integer> getNextMonths(Integer numberOfMonths) {
		Calendar c = Calendar.getInstance();
		
		Integer month = null;
		
		List<Integer> months = new ArrayList<Integer>();
		
		for (int i = 1; i <= numberOfMonths; i++) {
			month = c.get(Calendar.MONTH)+1;
			months.add(month);
			
			c.add(Calendar.MONTH, 1);
		}
		
		return months;
	}
	
	public static List<String> getMonthNames() {
		List<String> monthNames = new ArrayList<String>();
		
		monthNames.add("Jan");
		monthNames.add("Feb");
		monthNames.add("Mar");
		monthNames.add("Apr");
		monthNames.add("May");
		monthNames.add("Jun");
		monthNames.add("Jul");
		monthNames.add("Aug");
		monthNames.add("Sept");
		monthNames.add("Oct");
		monthNames.add("Nov");
		monthNames.add("Dec");
		
		return monthNames;
	}
	
	// compare if date is inside the date min and max
	// min <= date <= max 
	public static boolean isInsideDates(LocalDate date, LocalDate startDate, LocalDate endDate) {
		return !startDate.isAfter(date) && !endDate.isBefore(date);
	}
	
	// convert Date to LocalDate
	public static LocalDate convertToLocalDate(Date date) {
		return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}
	
	// convert LocalDate to Date
	public static Date convertToDate(LocalDate date) {
		return Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}
}
