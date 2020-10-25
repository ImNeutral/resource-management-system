package com.monstarlab.rms;

import static org.junit.Assert.assertEquals;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.monstarlab.rms.util.DateUtil;

import ch.qos.logback.classic.Logger;

@RunWith(SpringRunner.class)
@SpringBootTest 
public class DateUtilTest {
	static final Logger LOGGER  = (Logger) LoggerFactory.getLogger(DateUtilTest.class);
	
	@Test
	public void convertStartDateTest() throws ParseException {
		LocalDate date = DateUtil.convertStartDate(4, 2019);
		
		assertEquals( "2019-04-01", date.toString() );
	}
	
	@Test
	public void convertEndDateTest() throws ParseException {
		LocalDate date = DateUtil.convertEndDate(4, 2019);
		
		assertEquals( "2019-04-30", date.toString() );
	}
	
	
	@Test
	public void workingDaysTest() throws ParseException {
		String startString = "2019-04-12";
		String endString = "2019-04-23";
		DateFormat format = new SimpleDateFormat("yyyy-MM-d", Locale.ENGLISH);
		Date start = format.parse(startString);
		Date end = format.parse(endString);
		
		Long days = DateUtil.workingDays(start, end);
		assertEquals( "7", days.toString() );
	}
}
