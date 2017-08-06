package nl.springbank.dao;

import nl.springbank.bean.IbanBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * IbanDao. Communicates with the database and returns objects of type {@link IbanBean}.
 *
 * @author Tristan de Boer
 * @author Sven Konings
 */
@Transactional
public interface IbanDao extends JpaRepository<IbanBean, Long> {
    /**
     * Get the bankaccount-iban combination of the given iban.
     *
     * @param iban the given iban
     * @return the bankaccount-iban combination
     */
    IbanBean findByIban(String iban);
}
