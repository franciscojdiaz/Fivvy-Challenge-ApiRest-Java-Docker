package com.challenge.fivvy.service;


import com.challenge.fivvy.dominio.Acceptance;

import java.util.List;

public interface AcceptanceService {

    List<Acceptance> getAcceptances(String use_id);
    List<Acceptance> getAllAcceptances();

    Acceptance createAcceptance(Acceptance acceptance);
}
