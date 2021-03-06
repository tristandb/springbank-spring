package nl.springbank.dao;

import nl.springbank.bean.BankAccountBean;
import nl.springbank.bean.CardBean;
import nl.springbank.bean.UserBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * CardDao. Communicates with the database and returns objects of type {@link CardBean}.
 *
 * @author Tristan de Boer
 * @author Sven Konings
 */
@Transactional
public interface CardDao extends JpaRepository<CardBean, Long> {
    /**
     * Get the card with the given bank account and card number.
     *
     * @param bankAccount the given bank account
     * @param cardNumber  the given card number
     * @return the card, or {@code null} if it doesn't exist
     */
    CardBean findByBankAccountAndCardNumber(BankAccountBean bankAccount, String cardNumber);

    /**
     * Delete the cards with the given bank account and user.
     *
     * @param bankAccount the given bank account
     * @param user        the given user
     */
    void deleteByBankAccountAndUser(BankAccountBean bankAccount, UserBean user);
}
