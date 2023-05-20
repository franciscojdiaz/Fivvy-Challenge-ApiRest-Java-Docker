package com.challenge.fivvy.service;

import com.challenge.fivvy.dominio.Disclaimer;

import java.util.List;

public interface DisclaimerService {

    Disclaimer createDisclaimer(String id_user, Disclaimer disclaimer);
    Disclaimer updateDisclaimer(Disclaimer disclaimer);
    List<Disclaimer> getDisclaimers(String text);
    Disclaimer getDisclaimerById(int id);
    void deleteDisclaimer(int id);

}
