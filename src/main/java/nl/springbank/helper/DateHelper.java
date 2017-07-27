package nl.springbank.helper;

import sun.util.calendar.CalendarSystem;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Converts dates.
 *
 * @author Tristan de Boer.
 */
public class DateHelper {
    public static Date getDateFromString(String dateString){
        String pattern = "yyyy-MM-dd";
        Date date = null;
        try {
            date = new SimpleDateFormat(pattern).parse(dateString);
        } catch (ParseException e) {

        }
        return date;
    }

    public static String getCalenderDatefromTimestamp(Timestamp timestamp) {
        return ((Object) CalendarSystem.getGregorianCalendar().getCalendarDate(timestamp.getTime(), TimeZone.getDefault())).toString();
    }
}
