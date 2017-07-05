package nl.springbank.helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
}
