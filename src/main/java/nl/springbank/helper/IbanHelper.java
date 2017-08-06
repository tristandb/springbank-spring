package nl.springbank.helper;

import org.iban4j.CountryCode;
import org.iban4j.Iban;

import java.util.List;

/**
 * Generates iBAN keys.
 *
 * @author Tristan de Boer
 * @author Sven Konings
 */
public class IbanHelper {
    private static final String BANK_CODE = "INGB";

    /**
     * Generate an iban string that is not in the given list of existing ibans.
     *
     * @param existingIbans the given list of existing ibans
     * @return the generated iban
     */
    public static String generateIban(List<String> existingIbans) {
        String iban;
        do {
            iban = new Iban.Builder()
                    .countryCode(CountryCode.NL)
                    .bankCode(BANK_CODE)
                    .buildRandom().toString();
        } while (existingIbans.contains(iban));
        return iban;
    }
}
