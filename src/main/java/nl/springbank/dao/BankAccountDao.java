package nl.springbank.dao;

import nl.springbank.bean.BankAccountBean;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.transaction.annotation.Transactional;

/**
 * BankAccountDao. Communicates with the database and returns objects of type <code>nl.springbank.bean.UserBean</code>.
 *
 * @author Tristan de Boer.
 */
@Transactional
public interface BankAccountDao extends JpaRepository<BankAccountBean, Long> {
    /**
     * Returns a user based on given IBAN.
     * @param iban The IBAN.
     * @return
     */
     BankAccountBean findByIbanBean_Iban(String iban);
}
