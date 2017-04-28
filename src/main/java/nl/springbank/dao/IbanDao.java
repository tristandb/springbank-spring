package nl.springbank.dao;

import nl.springbank.bean.IbanBean;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

/**
 * IbanDao. Communicates with the database and returns objects of type <code>nl.springbank.bean.IbanBean</code>
 *
 * @author Tristan de Boer.
 */
@Transactional
public interface IbanDao extends CrudRepository<IbanBean, Long> {

    /**
     * Return the iban bean having the passed iban or null if no iban is found.
     *
     * @param iban The iban
     */
    IbanBean findByIban(String iban);
}
