package com.challenge.fivvy.service.Impl;

import com.challenge.fivvy.dao.AcceptanceDao;
import com.challenge.fivvy.dominio.Acceptance;
import com.challenge.fivvy.service.AcceptanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class AcceptanceServiceImpl implements AcceptanceService {

    @Autowired
    private AcceptanceDao acceptanceDao;

    public Acceptance createAcceptance(Acceptance acceptance) {
        return acceptanceDao.save(acceptance);
    }

    @Override
    public List<Acceptance> getAcceptances(String user_id) {

        if (user_id.isBlank()) {
            return acceptanceDao.findAll();
        }
        return acceptanceDao.getAcceptancesByUserId(user_id);
    }

    @Override
    public List<Acceptance> getAllAcceptances() {
        return acceptanceDao.findAll();
    }
}
