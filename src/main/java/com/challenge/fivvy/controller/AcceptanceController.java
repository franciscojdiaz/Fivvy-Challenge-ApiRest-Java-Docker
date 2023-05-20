package com.challenge.fivvy.controller;

import com.challenge.fivvy.dominio.Acceptance;
import com.challenge.fivvy.dominio.Disclaimer;
import com.challenge.fivvy.service.AcceptanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.MessageFormat;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping(path = "/api/v1/acceptance")
public class AcceptanceController {

    private final Logger logger = Logger.getLogger(DisclaimerController.class.getName());

    @Autowired
    private AcceptanceService acceptanceService;

    @GetMapping(value = "acceptanceByUserId/{userId}")
    public ResponseEntity<List<Acceptance>> geSeveraltAcceptance(@PathVariable("userId") String userId) {


        try {

            List<Acceptance> acceptanceList = acceptanceService.getAcceptances(userId);
            if (acceptanceList.size() >= 1) {
                return new ResponseEntity<>(acceptanceList, HttpStatus.OK);
            }
            var safeErrorMessage = MessageFormat.format("Data not found  - {0}", userId);
            logger.log(Level.SEVERE, safeErrorMessage);
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            var safeErrorMessage = MessageFormat.format("Error seeking text - {0}", userId);
            logger.log(Level.SEVERE, safeErrorMessage + " " + ex);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "acceptanceall/")
    public ResponseEntity<List<Acceptance>> getAllAcceptance() {

        try {
            List<Acceptance> acceptanceList = acceptanceService.getAllAcceptances();
            if (acceptanceList.size() >= 1) {
                return new ResponseEntity<>(acceptanceList, HttpStatus.OK);
            }
            var safeErrorMessage = MessageFormat.format("Data not found  - {0}", null);
            logger.log(Level.SEVERE, safeErrorMessage);
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            var safeErrorMessage = MessageFormat.format("Error seeking text - {0}", null);
            logger.log(Level.SEVERE, safeErrorMessage + " " + ex);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
