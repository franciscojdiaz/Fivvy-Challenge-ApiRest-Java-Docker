package com.challenge.fivvy.dao;


import com.challenge.fivvy.dominio.Acceptance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AcceptanceDao extends JpaRepository<Acceptance, Integer> {

    List<Acceptance> getAcceptancesByUserId(String user_id);

}
