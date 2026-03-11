package Mattiazerbini.backend.controller;

import Mattiazerbini.backend.entities.Campo;
import Mattiazerbini.backend.entities.Recensione;
import Mattiazerbini.backend.entities.Utente;
import Mattiazerbini.backend.payloads.CampoPayload;
import Mattiazerbini.backend.payloads.RecensionePayload;
import Mattiazerbini.backend.services.RecensioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recensioni")
public class RecensioneController {


    @Autowired
    private RecensioneService recensioneService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Recensione salvaRecensione(@RequestBody RecensionePayload payload, @AuthenticationPrincipal Utente utente) {
        return this.recensioneService.salvaRecensione(payload, utente);
    }

    @GetMapping
    public List<Recensione> findAll(){
        return recensioneService.findAll();
    }

}
