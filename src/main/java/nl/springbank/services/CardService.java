package nl.springbank.services;

import nl.springbank.bean.BankAccountBean;
import nl.springbank.bean.CardBean;
import nl.springbank.bean.UserBean;
import nl.springbank.dao.CardDao;
import nl.springbank.exceptions.InvalidParamValueError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

/**
 * Service that does all operations regarding Cards.
 *
 * @author Tristan de Boer
 * @author Sven Konings
 */
@Service
public class CardService {

    private final CardDao cardDao;

    @Autowired
    public CardService(CardDao cardDao) {
        this.cardDao = cardDao;
    }

    /**
     * Get the card with the given card id.
     *
     * @param cardId the given card id
     * @return the card
     * @throws InvalidParamValueError if an error occurred or the card doesn't exist
     */
    public CardBean getCard(long cardId) throws InvalidParamValueError {
        CardBean card;
        try {
            card = cardDao.findOne(cardId);
            Assert.notNull(card, "Card not found");
        } catch (IllegalArgumentException e) {
            throw new InvalidParamValueError(e);
        }
        return card;
    }

    /**
     * Get the card with the given bank account and card number.
     *
     * @param bankAccount the given bank account
     * @param cardNumber  the given card number
     * @return the card
     * @throws InvalidParamValueError if an error occurred or the card doesn't exist
     */
    public CardBean getCard(BankAccountBean bankAccount, String cardNumber) throws InvalidParamValueError {
        CardBean card;
        try {
            card = cardDao.findByBankAccountAndCardNumber(bankAccount, cardNumber);
            Assert.notNull(card, "Card not found");
        } catch (IllegalArgumentException e) {
            throw new InvalidParamValueError(e);
        }
        return card;
    }

    /**
     * Get all cards
     *
     * @return a list of all cards
     */
    public List<CardBean> getCards() {
        return cardDao.findAll();
    }

    /**
     * Save the given card in the database.
     *
     * @param card the given card
     * @return the saved card
     */
    public CardBean saveCard(CardBean card) {
        return cardDao.save(card);
    }

    /**
     * Save the given cards in the database.
     *
     * @param cards the given cards
     * @return the list of saved cards
     */
    public List<CardBean> saveCards(Iterable<CardBean> cards) {
        return cardDao.save(cards);
    }

    /**
     * Delete the card with the given id.
     *
     * @param cardId the given id
     */
    public void deleteCard(long cardId) {
        cardDao.delete(cardId);
    }

    /**
     * Delete the given card.
     *
     * @param card the given card
     */
    public void deleteCard(CardBean card) {
        cardDao.delete(card);
    }

    /**
     * Delete the card belonging to the given bank account and user.
     *
     * @param bankAccount the given bank account
     * @param user        the given user
     */
    public void deleteCard(BankAccountBean bankAccount, UserBean user) {
        cardDao.deleteByBankAccountAndUser(bankAccount, user);
    }

    /**
     * Delete the given cards
     *
     * @param cards the given cards
     */
    public void deleteCards(Iterable<CardBean> cards) {
        cardDao.delete(cards);
    }
}
