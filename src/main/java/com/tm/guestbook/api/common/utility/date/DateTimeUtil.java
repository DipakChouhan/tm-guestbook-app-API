package com.tm.guestbook.api.common.utility.date;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Date;
import java.util.Locale;

public class DateTimeUtil {
    private DateTimeUtil(){}

    public static LocalDate convertStringToLocalDate(String date) {
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.getDefault());
        DateTimeFormatter df = new DateTimeFormatterBuilder()
                // case insensitive to parse JAN and FEB
                .parseCaseInsensitive()
                // add pattern
                .appendPattern("yyyy-MM-dd")
                // create formatter (use English Locale to parse month names)
                .toFormatter(Locale.ENGLISH);
        return LocalDate.parse(date, df);
    }
    
    public static String convertLocalDateToString(LocalDate localDate) {
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter df = new DateTimeFormatterBuilder()
                // case insensitive to parse JAN and FEB
                .parseCaseInsensitive()
                // add pattern
                .appendPattern("yyyy-MM-dd")
                // create formatter (use English Locale to parse month names)
                .toFormatter(Locale.ENGLISH);
        return localDate.format(df);
    }

    public static String convertLocalTimeToString(LocalTime localTime) {
        DateTimeFormatter df = new DateTimeFormatterBuilder()
                // case insensitive to parse JAN and FEB
                .parseCaseInsensitive()
                // add pattern
                .appendPattern("HH:mm")
                // create formatter (use English Locale to parse month names)
                .toFormatter(Locale.ENGLISH);
        return localTime.format(df);
    }

    public static LocalTime convertStringToLocalTime(String time) {
        DateTimeFormatter df = new DateTimeFormatterBuilder()
                // case insensitive to parse JAN and FEB
                .parseCaseInsensitive()
                // add pattern
                .appendPattern("HH:mm")
                // create formatter (use English Locale to parse month names)
                .toFormatter(Locale.ENGLISH);
        return LocalTime.parse(time, df);
    }

    public static Date convertDateTimeStringToDate(String date) {
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.getDefault());
        DateTimeFormatter df = new DateTimeFormatterBuilder()
                // case insensitive to parse JAN and FEB
                .parseCaseInsensitive()
                // add pattern
                .appendPattern("yyyy-MM-dd HH:mm")
                // create formatter (use English Locale to parse month names)
                .toFormatter(Locale.ENGLISH);
        return convertDateTimeToDate(LocalDateTime.parse(date, df));
    }

    public static LocalDateTime convertDateTimeStringToLocalDateTime(String date) {
        DateTimeFormatter df = new DateTimeFormatterBuilder()
                // case insensitive to parse JAN and FEB
                .parseCaseInsensitive()
                // add pattern
                .appendPattern("yyyy-MM-dd HH:mm")
                // create formatter (use English Locale to parse month names)
                .toFormatter(Locale.ENGLISH);
        return LocalDateTime.parse(date, df);
    }

    public static Date convertDateTimeToDate(LocalDateTime localDateTime) {
        ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.systemDefault());
        return Date.from(zonedDateTime.toInstant());
    }

    public static String convertLocalDateTimeToString(LocalDateTime localDateTime) {
        DateTimeFormatter df = new DateTimeFormatterBuilder()
                // case insensitive to parse JAN and FEB
                .parseCaseInsensitive()
                // add pattern
                .appendPattern("yyyy-MM-dd HH:mm")
                // create formatter (use English Locale to parse month names)
                .toFormatter(Locale.ENGLISH);
        return localDateTime.format(df);
    }

    public static String getDateStringFromLocalDate(LocalDateTime localDateTime) {
        DateTimeFormatter df = new DateTimeFormatterBuilder()
                // case insensitive to parse JAN and FEB
                .parseCaseInsensitive()
                // add pattern
                .appendPattern("yyyy-MM-dd")
                // create formatter (use English Locale to parse month names)
                .toFormatter(Locale.ENGLISH);
        return localDateTime.format(df);
    }

    public static String getTimeStringFromLocalDate(LocalDateTime localDateTime) {
        DateTimeFormatter df = new DateTimeFormatterBuilder()
                // case insensitive to parse JAN and FEB
                .parseCaseInsensitive()
                // add pattern
                .appendPattern("HH:mm")
                // create formatter (use English Locale to parse month names)
                .toFormatter(Locale.ENGLISH);
        return localDateTime.format(df);
    }
}
