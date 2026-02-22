package arkanian.userprompts;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import arkanian.arkanianexceptions.InvalidParameterException;

/**
 * Converts strings of dates and/or times into LocalDateTime objects.
 * <p>
 * Supports multiple date and time formats.
 */
public class DateTimeParser {

    private static final String[] datePatterns = {
        "d-MM-yyyy",
        "d/MM/yyyy",
        "d MMM yyyy",
        "d MMMM yyyy",
        "yyyy-MM-dd",
        "ddMMyyyy"
    };

    private static final String[] timePatterns = {
        "H:mm",
        "HHmm",
        "hma",
        "hm a",
        "h.ma",
        "h.m a"
    };

    /**
     * Converts a string input to a LocalDateTime object.
     *
     * @param input string version of the date-time
     * @return LocalDateTime object, or null if parsing fails
     */
    public static LocalDateTime convert(String input) {
        LocalDateTime dateTime = parseDateTimeWithPatterns(input);

        if (dateTime == null) {
            dateTime = parseDateOnly(input);
        }

        if (dateTime == null) {
            throw new InvalidParameterException(
                    "Arf arf! This date/time smells weird ;_; Try something like '2026-03-15 1800' hooman!"
            );
        }

        return dateTime;
    }

    private static LocalDateTime parseDateTimeWithPatterns(String input) {
        for (String dp : datePatterns) {
            for (String tp : timePatterns) {
                LocalDateTime dt = tryParseDateTime(input, dp, tp);
                if (dt != null) {
                    return dt;
                }
            }
        }
        return null;
    }

    private static LocalDateTime tryParseDateTime(String input, String datePattern, String timePattern) {
        String pattern = (datePattern + " " + timePattern).trim();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        try {
            return LocalDateTime.parse(input, formatter);
        } catch (DateTimeParseException ignore) {
            return null;
        }
    }

    private static LocalDateTime parseDateOnly(String input) {
        for (String dp : datePatterns) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dp);
            try {
                LocalDate date = LocalDate.parse(input, formatter);
                return date.atStartOfDay();
            } catch (DateTimeParseException ignore) {
                // continue
            }
        }
        return null;
    }

    public static String getDateString(String dt) {
        return dt.substring(0, 10);
    }

    public static String getTimeString(String dt) {
        return dt.substring(11);
    }
}
