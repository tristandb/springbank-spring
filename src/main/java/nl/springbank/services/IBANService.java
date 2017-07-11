package nl.springbank.services;

import nl.springbank.bean.IbanBean;
import nl.springbank.dao.IbanDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles all iBAN requests.
 *
 * @author Tristan de Boer.
 */
@Service
public class IBANService {
    private final IbanDao ibanDao;

    @Autowired
    public IBANService(IbanDao ibanDao) {
        this.ibanDao = ibanDao;
    }

    public List<String> getAllIBAN() {
        List<String> result = new ArrayList<>();
        for (IbanBean ibanBean: ibanDao.findAll()){
            result.add(ibanBean.getIban());
        }
        return result;
    }

    public IbanBean saveIbanBean(IbanBean ibanBean) {
        return ibanDao.save(ibanBean);
    }

    public IbanBean getIbanBean(String iBAN){
        return ibanDao.findByIban(iBAN);
    }
}
