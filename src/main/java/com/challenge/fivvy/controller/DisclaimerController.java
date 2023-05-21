package com.challenge.fivvy.controller;


import com.challenge.fivvy.dominio.Acceptance;
import com.challenge.fivvy.dominio.Disclaimer;
import com.challenge.fivvy.service.AcceptanceService;
import com.challenge.fivvy.service.DisclaimerService;
import com.challenge.fivvy.utils.JSONUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;


@RestController
@RequestMapping(path = "/api/v1/disclaimer")
public class DisclaimerController {

    private final Logger logger = Logger.getLogger(DisclaimerController.class.getName());

    @Autowired
    private DisclaimerService disclaimerService;


    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Disclaimer> createDisclaimer(@RequestParam(value = "name") String name,
                                                       @RequestParam(value = "text") String text,
                                                       @RequestParam(value = "varsion") String version) {

        try {

            Disclaimer disclaimer = new Disclaimer();
            disclaimer.setName(name);
            disclaimer.setText(text);
            disclaimer.setVersion(version);

            // allowing generate unique id for user
            var id_user = UUID.randomUUID().toString();

            return new ResponseEntity<>(disclaimerService.createDisclaimer(id_user, disclaimer), HttpStatus.OK);
        } catch (Exception ex) {
            var safeErrorMessage = MessageFormat.format("Error creating disclaimer - {0}", name);
            logger.log(Level.SEVERE, safeErrorMessage + " " + ex);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping(value = "disclaimerBytext/")
    public ResponseEntity<List<Disclaimer>> geSeveraltDisclaimers(@RequestParam(value = "text", required = false) String text) {
        try {
            List<Disclaimer> disclaimers = disclaimerService.getDisclaimers(text);
            if (disclaimers.size() >= 1) {
                return new ResponseEntity<>(disclaimers, HttpStatus.OK);
            }
            var safeErrorMessage = MessageFormat.format("Data not found  - {0}", text);
            logger.log(Level.SEVERE, safeErrorMessage);
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            var safeErrorMessage = MessageFormat.format("Error seeking text - {0}", text);
            logger.log(Level.SEVERE, safeErrorMessage + " " + ex);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping(value = "disclaimerById/{id}")
    public ResponseEntity<Disclaimer> getDisclaimerById(@PathVariable("id") int id) {
        try {
            Disclaimer disclaimer = disclaimerService.getDisclaimerById(id);
            if (Objects.isNull(disclaimer)) {
                var safeErrorMessage = MessageFormat.format("Error seeking by  id - {0}", id);
                logger.log(Level.SEVERE, safeErrorMessage);
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(disclaimer, HttpStatus.OK);
        } catch (Exception ex) {

            var safeErrorMessage = MessageFormat.format("Error seeking by id - {0}", id);
            logger.log(Level.SEVERE, safeErrorMessage + " " + ex);
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

        }

    }


    @PutMapping(value = "update", produces = "application/json", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Disclaimer> actualizarEquipoPorId(@RequestBody String jsonData) throws IOException {
        //aqui se recibe la categoria ya actualizada desde el font end
        Disclaimer disclaimer = JSONUtils.covertFromJsonToObject(jsonData, Disclaimer.class);

        if (Objects.isNull(disclaimer)) {
            var safeErrorMessage = MessageFormat.format("Disclamer not exists - {0}", disclaimer.getName());
            logger.log(Level.SEVERE, safeErrorMessage);
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(disclaimerService.updateDisclaimer(disclaimer), HttpStatus.OK);
    }


    @DeleteMapping(value = "deleteById/{id}")
    @ResponseStatus(HttpStatus.OK)
    private String eliminarEquipoPorId(@PathVariable("id") int id) {
        Disclaimer disclaimer = disclaimerService.getDisclaimerById(id);
        if (Objects.isNull(disclaimer)) {
            var safeErrorMessage = MessageFormat.format("Data no found - {0}", id);
            logger.log(Level.SEVERE, safeErrorMessage);

            return "Row not exists : " + id;
        }
        disclaimerService.deleteDisclaimer(id);

        return "The row  : " + disclaimer.getName() + " hes been deleted success!!";
    }


}
