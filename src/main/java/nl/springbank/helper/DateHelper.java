package nl.springbank.helper;

import nl.springbank.exceptions.InvalidParamValueError;
import sun.util.calendar.CalendarSystem;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

/**
 * Converts dates.
 *
 * @author Tristan de Boer
 * @author Sven Konings
 */
public class DateHelper {
    private static final String DATE_PATTERN = "yyyy-MM-dd";

    /**
     * Parse the given string to a date.
     *
     * @param dateString the given string
     * @return the resulting date
     * @throws InvalidParamValueError if the string couldn't be parsed
     */
    public static Date getDateFromString(String dateString) throws InvalidParamValueError {
        Date date;
        try {
            date = new Date(new SimpleDateFormat(DATE_PATTERN).parse(dateString).getTime());
        } catch (ParseException e) {
            throw new InvalidParamValueError(e);
        }
        return date;
    }

    /**
     * Get the calendar string representation of the given timestamp.
     *
     * @param timestamp the given timestamp
     * @return the calendar string representation
     */
    public static String getCalendarDatefromTimestamp(Timestamp timestamp) {
        return ((Object) CalendarSystem.getGregorianCalendar().getCalendarDate(timestamp.getTime(), TimeZone.getDefault())).toString();
    }
}
