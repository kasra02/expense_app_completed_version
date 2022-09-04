package com.example.demo.util;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateTimeUtil {


	/**
	 * 
	 * @param date
	 */
	public static String convertDateToString(Date date) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return simpleDateFormat.format(date);
	}


	/**
	 * @param stingDate date in string format
	 * @return sql Date
	 */
	public static Date convertStringToDate(String stingDate) throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date parse = simpleDateFormat.parse(stingDate);
		return new Date(parse.getTime());
	}

}