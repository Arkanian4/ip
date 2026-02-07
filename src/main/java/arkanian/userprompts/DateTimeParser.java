package arkanian.userprompts;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Converts strings of dates and/or times into LocalDateTime objects
 */
public class DateTimeParser {

    private static String[] datePatterns = {
            "d-MM-yyyy",
            "d/MM/yyyy",
            "d MMM yyyy",
            "d MMMM yyyy",
            "yyyy-MM-dd",
            "ddMMyyyy"
    };

    private static String[] timePatterns = {
            "H:mm",
            "HHmm",
            "hma",
            "hm a",
            "h.ma",
            "h.m a"
    };

    /**
     *
     * @param input string version of the date-time
     * @return LocalDateTime object version of the date-time
     */
    public static LocalDateTime convert(String input) {
        LocalDateTime dateTime = null;
        for (String dp : datePatterns) {
            for (String tp : timePatterns) {
                String pattern = dp + " " + tp;
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern.trim());
                try {
                    dateTime = LocalDateTime.parse(input, formatter);
                } catch (DateTimeParseException ignore) {
                }
            }
        }

        for (String dp : datePatterns) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dp);
            try {
                LocalDate date = LocalDate.parse(input, formatter);
                dateTime = date.atStartOfDay();
            } catch (DateTimeParseException ignore) {
            }
        }

        return dateTime;

    }

    public static String getDateString(String dt) {
        return dt.substring(0, 10);
    }

    public static String getTimeString(String dt) {
        return dt.substring(11);
    }

}