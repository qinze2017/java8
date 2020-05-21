package com.ze.java8;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalField;
import java.time.temporal.TemporalUnit;
import java.util.Calendar;
import java.util.Date;

/**
 * #author:qinze
 *
 * @date:2020-05-20
 * @description:
 **/

public class DateTime {

    public static void main(String[] args) throws ParseException, InterruptedException {
        /*Date date = new Date(114, 2, 18);
        System.out.println(date);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        // not thread-safe
        for(int i = 0; i < 30; i++) {
            new Thread(() -> {
                for(int x = 0; x < 100; x++) {
                    Date parseDate = null;
                    try {
                        parseDate = sdf.parse("20160520");
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    System.out.println(parseDate);
                }
            }).start();
        }

        Date parseDate = sdf.parse("20200520");
        System.out.println(parseDate);*/
        testLocalDate();
        testLocalTime();
        combineLocalDateAndTime();
        testInstant();
        testDuration();
        testPeriod();
        testDateFormat();
        testDateParse();
    }

    private static void testLocalDate(){
        //Thread-safe
        LocalDate localDate = LocalDate.of(2020, 05, 20);
        System.out.println(localDate.getYear());
        System.out.println(localDate.getMonth());
        System.out.println(localDate.getDayOfYear());
        System.out.println(localDate.getDayOfMonth());
        System.out.println(localDate.getDayOfWeek());

        System.out.println(localDate.now());
        System.out.println(localDate.get(ChronoField.DAY_OF_MONTH));
    }

    private static void testLocalTime(){
        LocalTime time = LocalTime.now();
        System.out.println(time.getHour());
        System.out.println(time.getMinute());
        System.out.println(time.getSecond());
    }

    private static void combineLocalDateAndTime(){
        LocalDate localDate = LocalDate.now();
        LocalTime time = LocalTime.now();

        LocalDateTime localDateTime = LocalDateTime.of(localDate, time);
        System.out.println(localDateTime.toString());

        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);
        /*Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.get(Calendar.DAY_OF_MONTH);*/
    }

    private static void testInstant() throws InterruptedException {
        Instant start = Instant.now();
        Thread.sleep(1000L);
        Instant end = Instant.now();
        Duration duration = Duration.between(start, end);
        System.out.println(duration.toMillis());
    }

    private static void testDuration(){
        LocalTime time = LocalTime.now();
        LocalTime beforeTime = time.minusHours(1);
        Duration duration = Duration.between(time, beforeTime);
        System.out.println(duration.toHours());
    }

    private static void testPeriod(){
        Period period = Period.between(LocalDate.of(2020, 1, 10), LocalDate.of(2022, 1, 10));
        System.out.println(period.getDays());
        System.out.println(period.getMonths());
        System.out.println(period.getYears());
    }

    private static void testDateFormat(){
        LocalDate localDate = LocalDate.now();
        String format = localDate.format(DateTimeFormatter.BASIC_ISO_DATE);
        String format1 = localDate.format(DateTimeFormatter.ISO_ORDINAL_DATE);
        System.out.println(format);
        System.out.println(format1);

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyy-MM-dd");
        String format2 = localDate.format(dateTimeFormatter);
        System.out.println(format2);
    }

    private static void testDateParse(){
        String date = "20200520";
        LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.BASIC_ISO_DATE);
        System.out.println(localDate);

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyy-MM-dd");
        String date1 = "2020-05-20";
        LocalDate localDate2 = LocalDate.parse(date1, dateTimeFormatter);
        System.out.println(localDate2);
    }
}
