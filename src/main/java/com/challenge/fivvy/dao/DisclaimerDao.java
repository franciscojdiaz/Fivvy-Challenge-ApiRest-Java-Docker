package com.challenge.fivvy.dao;


import com.challenge.fivvy.dominio.Disclaimer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DisclaimerDao extends JpaRepository<Disclaimer, Integer> {

    List<Disclaimer> getDisclaimerByText(String text);
    Disclaimer getDisclaimerById(int id);
}
