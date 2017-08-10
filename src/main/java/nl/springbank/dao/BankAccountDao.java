package nl.springbank.dao;

import nl.springbank.bean.BankAccountBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * BankAccountDao. Communicates with the database and returns objects of type {@link BankAccountBean}.
 *
 * @author Tristan de Boer
 * @author Sven Konings
 */
@Transactional
public interface BankAccountDao extends JpaRepository<BankAccountBean, Long> {
    /**
     * Get the bank account belonging to the given iban.
     *
     * @param iban the given iban
     * @return the bank account, or {@code null} if it doesn't exist
     */
    BankAccountBean findByIban_Iban(String iban);
}
