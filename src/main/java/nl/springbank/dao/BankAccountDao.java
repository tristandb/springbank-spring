package nl.springbank.dao;

import nl.springbank.bean.BankAccountBean;
import nl.springbank.bean.UserBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * BankAccountDao. Communicates with the database and returns objects of type {@link BankAccountBean}.
 *
 * @author Tristan de Boer
 * @author Sven Konings
 */
@Transactional
public interface BankAccountDao extends JpaRepository<BankAccountBean, Long> {
    /**
     * Get the bank accounts which the given user is the holder of.
     *
     * @param holder the given user
     * @return the list of bank accounts
     */
    List<BankAccountBean> findByHolder(UserBean holder);
}
