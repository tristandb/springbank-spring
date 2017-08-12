package nl.springbank.dao;

import nl.springbank.bean.BankAccountBean;
import nl.springbank.bean.CardBean;
import nl.springbank.bean.UserBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

    /**
     * Increment invalid login_errors by a user by 1.
     *
     * @param cardId The cardId of the user.
     */
    @Modifying
    @Query(value = "UPDATE card c set c.login_errors = c.login_errors + 1 WHERE c.card_id = :cardId", nativeQuery = true)
    void incrementInvalidErrors(@Param("cardId") Long cardId);

    /**
     * Reset invalid login_errors of a card.
     *
     * @param cardId The cardId to reset.
     */
    @Modifying
    @Query(value = "Update card c SET c.login_errors = 0 WHERE c.card_id = :cardId", nativeQuery = true)
    void resetInvalidLoginErrors(@Param("cardId") Long cardId);
}
