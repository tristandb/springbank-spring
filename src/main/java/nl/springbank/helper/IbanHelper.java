package nl.springbank.helper;

import org.iban4j.CountryCode;
import org.iban4j.Iban;

import java.util.List;

/**
 * Generates iBAN keys.
 *
 * @author Tristan de Boer).
 */
public class IbanHelper {
    private static String BANK_CODE = "INGB";

    public static String generateIban(List<String> existingIbans) {
        Iban iban = null;
        while (iban == null || existingIbans.contains(iban.toString())) {
            iban = new Iban.Builder()
                    .countryCode(CountryCode.NL)
                    .bankCode(BANK_CODE)
                    .buildRandom();
        }
        return iban.toString();
    }
}
