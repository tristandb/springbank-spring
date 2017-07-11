package nl.springbank.services;

import nl.springbank.bean.CardBean;
import nl.springbank.dao.CardDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Tristan de Boer.
 */
@Service
public class CardService {
    private final CardDao cardDao;

    @Autowired
    public CardService(CardDao cardDao) {
        this.cardDao = cardDao;
    }

    public List<Integer> getCardNumbers(long bankAccountId){
        List<Integer> result = new ArrayList<>();
        for (CardBean cardBean: cardDao.findByBankAccountId(bankAccountId)){
            result.add(cardBean.getCardNumber());
        }
        return result;
    }

    public CardBean saveCardBean(CardBean cardBean){
        return this.cardDao.save(cardBean);
    }

    public void deleteCardByUserIdAndBankAccount(long userId, long bankAccountId) {
        cardDao.deleteByUserIdAndBankAccountId(userId, bankAccountId);
    }
}
