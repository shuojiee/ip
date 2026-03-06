package gin.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtil {

    private static final DateTimeFormatter INPUT_FORMAT =
            DateTimeFormatter.ofPattern("dd MM yyyy");

    private static final DateTimeFormatter OUTPUT_FORMAT =
            DateTimeFormatter.ofPattern("MMM d yyyy");

    public static LocalDate parseDate(String date) {
        return LocalDate.parse(date.trim(), INPUT_FORMAT);
    }

    public static String formatDate(LocalDate date) {
        return date.format(OUTPUT_FORMAT);
    }
}