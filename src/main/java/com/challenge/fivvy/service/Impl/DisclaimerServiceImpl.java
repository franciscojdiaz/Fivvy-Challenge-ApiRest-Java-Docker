package com.challenge.fivvy.service.Impl;

import com.challenge.fivvy.dao.AcceptanceDao;
import com.challenge.fivvy.dao.DisclaimerDao;
import com.challenge.fivvy.dominio.Acceptance;
import com.challenge.fivvy.dominio.Disclaimer;
import com.challenge.fivvy.service.DisclaimerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Transient;
import java.util.List;
import java.util.Objects;

@Service
public class DisclaimerServiceImpl implements DisclaimerService {

    @Autowired
    private DisclaimerDao disclaimerDao;

    @Autowired
    private AcceptanceDao acceptanceDao;

    @Override
    @Transactional
    public Disclaimer createDisclaimer(String id_user, Disclaimer disclaimer) {

        Acceptance acceptance = new Acceptance(id_user, disclaimer);
        Acceptance acceptance1 = acceptanceDao.save(acceptance);

        return disclaimerDao.save(disclaimer);
    }

    @Override
    public Disclaimer updateDisclaimer(Disclaimer disclaimer) {

        Disclaimer newDisclaimer = disclaimerDao.getDisclaimerById(disclaimer.getId());
        newDisclaimer.setName(disclaimer.getName());
        newDisclaimer.setText(disclaimer.getText());
        newDisclaimer.setVersion(disclaimer.getVersion());
        return disclaimerDao.save(newDisclaimer);
    }

    @Override
    public List<Disclaimer> getDisclaimers(String text) {

        if (Objects.isNull(text) || text.length() == 0) {
            return disclaimerDao.findAll();
        }
        return disclaimerDao.getDisclaimerByText(text);
    }

    @Override
    public Disclaimer getDisclaimerById(int id) {
        return disclaimerDao.getDisclaimerById(id);
    }

    @Override
    public void deleteDisclaimer(int id) {
        disclaimerDao.deleteById(id);
    }
}
