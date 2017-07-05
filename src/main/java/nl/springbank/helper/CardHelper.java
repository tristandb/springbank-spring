package nl.springbank.helper;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

/**
 * Description
 *
 * @author Tristan de Boer).
 */
public class CardHelper {
    private static int cardNumberLowerBound = 0;
    private static int cardNumberUpperBound = 10000;

    private static int pinLowerBound = 0;
    private static int pinUpperBound = 10000;

    public static int getRandomCardNumber(List<Integer> existingCardId) {
        Random random = new Random();
        Integer result = null;
        while (result == null || existingCardId.contains(result)) {
            result = random.nextInt(cardNumberUpperBound - cardNumberLowerBound) + cardNumberLowerBound;
        }
        return result;
    }

    public static int getRandomPin() {
        Random random = new Random();
        return random.nextInt(pinUpperBound - pinLowerBound) + pinLowerBound;
    }

    public static Date getExpirationDate() {
        Date sqlDate = new Date(new java.util.Date().getTime());
        Calendar cal = Calendar.getInstance();
        cal.setTime(sqlDate);
        cal.add(Calendar.YEAR, 5);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return new Date(cal.getTimeInMillis());
    }

    public static String convertToString(int number){
        return String.format("%04d", number);
    }
}
