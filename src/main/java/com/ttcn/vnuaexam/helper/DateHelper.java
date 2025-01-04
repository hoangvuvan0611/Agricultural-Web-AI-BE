package com.ttcn.vnuaexam.helper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Optional;
import java.util.function.Function;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DateHelper {
    // Constants for date-time patterns
    public static final String DATE_SLASH = "dd/MM/yyyy";
    public static final String DATE_TIME = "yyyyMMdd HH:mm:ss";
    public static final String DB_DATE = "yyyyMMdd";
    public static final String DB_DATE_TIME = "yyyyMMddHHmmss";
    public static final String GLOBAL_DATE = "yyyy-MM-dd";
    public static final String GLOBAL_DATE_TIME = "yyyy-MM-dd HH:mm:ss";

    // Cache các formatter để tái sử dụng (thread-safe)
    private static final DateTimeFormatter DATE_SLASH_FORMATTER = DateTimeFormatter.ofPattern(DATE_SLASH);
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DATE_TIME);
    private static final DateTimeFormatter DB_DATE_FORMATTER = DateTimeFormatter.ofPattern(DB_DATE);
    private static final DateTimeFormatter DB_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DB_DATE_TIME);
    private static final DateTimeFormatter GLOBAL_DATE_FORMATTER = DateTimeFormatter.ofPattern(GLOBAL_DATE);
    private static final DateTimeFormatter GLOBAL_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(GLOBAL_DATE_TIME);

    private static final ZoneId DEFAULT_ZONE = ZoneId.systemDefault();

    /**
     * Convert LocalDate/LocalDateTime to formatted string
     */

    public static String toDateSlash(LocalDate date) {
        return formatOrNull(date, DATE_SLASH_FORMATTER);
    }

    public static String toDateTime(LocalDateTime dateTime) {
        return formatOrNull(dateTime, DATE_TIME_FORMATTER);
    }

    public static String toDbDate(LocalDate date) {
        return formatOrNull(date, DB_DATE_FORMATTER);
    }

    public static String toDbDateTime(LocalDateTime dateTime) {
        return formatOrNull(dateTime, DB_DATE_TIME_FORMATTER);
    }

    public static String toGlobalDate(LocalDate date) {
        return formatOrNull(date, GLOBAL_DATE_FORMATTER);
    }

    public static String toGlobalDateTime(LocalDateTime dateTime) {
        return formatOrNull(dateTime, GLOBAL_DATE_TIME_FORMATTER);
    }

    /**
     * Parse string to LocalDate/LocalDateTime
     */
    public static LocalDate fromDateSlash(String dateStr) {
        return parseOrNull(dateStr, DATE_SLASH_FORMATTER, LocalDate::from);
    }

    public static LocalDateTime fromDateTime(String dateTimeStr) {
        return parseOrNull(dateTimeStr, DATE_TIME_FORMATTER, LocalDateTime::from);
    }

    public static LocalDate fromDbDate(String dateStr) {
        return parseOrNull(dateStr, DB_DATE_FORMATTER, LocalDate::from);
    }

    public static LocalDateTime fromDbDateTime(String dateTimeStr) {
        return parseOrNull(dateTimeStr, DB_DATE_TIME_FORMATTER, LocalDateTime::from);
    }

    public static LocalDate fromGlobalDate(String dateStr) {
        return parseOrNull(dateStr, GLOBAL_DATE_FORMATTER, LocalDate::from);
    }

    public static LocalDateTime fromGlobalDateTime(String dateTimeStr) {
        return parseOrNull(dateTimeStr, GLOBAL_DATE_TIME_FORMATTER, LocalDateTime::from);
    }

    /**
     * Convert legacy Date to modern types
     */
    /*
    public static LocalDate toLocalDate(Date date) {
        return Optional.ofNullable(date)
                .map(Date::toInstant)
                .map(instant -> instant.atZone(DEFAULT_ZONE))
                .map(ZonedDateTime::toLocalDate)
                .orElse(null);
    }

    public static LocalDateTime toLocalDateTime(Date date) {
        return Optional.ofNullable(date)
                .map(Date::toInstant)
                .map(instant -> instant.atZone(DEFAULT_ZONE))
                .map(ZonedDateTime::toLocalDateTime)
                .orElse(null);
    }

     */

    /**
     * Legacy support for Date type (consider migrating away from java.util.Date)
     */
    /*
    @Deprecated(since = "Java 8", forRemoval = true)
    public static String toDateSlash(Date date) {
        return formatOrNull(toLocalDate(date), DATE_SLASH_FORMATTER);
    }

    @Deprecated(since = "Java 8", forRemoval = true)
    public static String toDateTime(Date date) {
        return formatOrNull(toLocalDateTime(date), DATE_TIME_FORMATTER);
    }

    @Deprecated(since = "Java 8", forRemoval = true)
    public static String toDbDate(Date date) {
        return formatOrNull(toLocalDate(date), DB_DATE_FORMATTER);
    }

    @Deprecated(since = "Java 8", forRemoval = true)
    public static String toDbDateTime(Date date) {
        return formatOrNull(toLocalDateTime(date), DB_DATE_TIME_FORMATTER);
    }

    @Deprecated(since = "Java 8", forRemoval = true)
    public static String toGlobalDate(Date date) {
        return formatOrNull(toLocalDate(date), GLOBAL_DATE_FORMATTER);
    }

    @Deprecated(since = "Java 8", forRemoval = true)
    public static String toGlobalDateTime(Date date) {
        return formatOrNull(toLocalDateTime(date), GLOBAL_DATE_TIME_FORMATTER);
    }

     */


    /**
     * Helper methods for null-safe formatting and parsing
     */
    private static <T> String formatOrNull(T temporal, DateTimeFormatter formatter) {
        return Optional.ofNullable(temporal)
                .map(t -> formatter.format((TemporalAccessor) t))
                .orElse(null);
    }

    private static <T> T parseOrNull(String text, DateTimeFormatter formatter,
                                     Function<TemporalAccessor, T> converter) {
        return Optional.ofNullable(text)
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .map(formatter::parse)
                .map(converter)
                .orElse(null);
    }

    /*
    public static String toDateSlash(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_SLASH);
        return simpleDateFormat.format(date.getTime());
    }

    public static String toDateSlash(LocalDate localDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_SLASH);
        return localDate.format(formatter);
    }

    public static String toDateTime(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_TIME);
        return simpleDateFormat.format(date.getTime());
    }

    public static String toDateTime(LocalDateTime localDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_TIME);
        return localDateTime.format(formatter);
    }

    public static String toDbDate(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DB_DATE);
        return simpleDateFormat.format(date.getTime());
    }

    public static String toDbDate(LocalDate localDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DB_DATE);
        return localDate.format(formatter);
    }

    public static String toDbDateTime(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DB_DATE_TIME);
        return simpleDateFormat.format(date.getTime());
    }

    public static String toDbDateTime(LocalDateTime localDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DB_DATE_TIME);
        return localDateTime.format(formatter);
    }

    public static String toGlobalDate(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(GLOBAL_DATE);
        return simpleDateFormat.format(date.getTime());
    }

    public static String toGlobalDate(LocalDate localDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(GLOBAL_DATE);
        return localDate.format(formatter);
    }

    public static String toGlobalDateTime(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(GLOBAL_DATE_TIME);
        return simpleDateFormat.format(date.getTime());
    }

    public static String toGlobalDateTime(LocalDateTime localDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(GLOBAL_DATE_TIME);
        return localDateTime.format(formatter);
    }

    public static LocalDate toLocalDate(Date date) {
        return Instant.ofEpochMilli(date.getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    public static LocalDateTime toLocalDateTime(Date date) {
        return Instant.ofEpochMilli(date.getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }

    public static LocalDate fromDateSlash(String dateSlash) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_SLASH);
        return LocalDate.parse(dateSlash, formatter);
    }

    public static LocalDateTime fromDateTime(String dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_TIME);
        return LocalDateTime.parse(dateTime, formatter);
    }

    public static LocalDate fromDbDate(String dbDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DB_DATE);
        return LocalDate.parse(dbDate, formatter);
    }

    public static LocalDateTime fromDbDateTime(String dbDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DB_DATE_TIME);
        return LocalDateTime.parse(dbDateTime, formatter);
    }

    public static LocalDate fromGlobalDate(String globalDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(GLOBAL_DATE);
        return LocalDate.parse(globalDate, formatter);
    }

    public static LocalDateTime fromGlobalDateTime(String globalDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(GLOBAL_DATE_TIME);
        return LocalDateTime.parse(globalDateTime, formatter);
    }

     */
}
