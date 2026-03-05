package Mattiazerbini.backend.controller;

import Mattiazerbini.backend.entities.Campo;
import Mattiazerbini.backend.entities.Prenotazione;
import Mattiazerbini.backend.entities.Servizio;
import Mattiazerbini.backend.entities.Utente;
import Mattiazerbini.backend.payloads.CampoPayload;
import Mattiazerbini.backend.payloads.PrenotazionePayload;
import Mattiazerbini.backend.payloads.UtentePayload;
import Mattiazerbini.backend.services.PrenotazioneService;
import Mattiazerbini.backend.services.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/prenotazioni")
public class PrenotazioneController {
    private final PrenotazioneService prenotazioneService;

    @Autowired
    public PrenotazioneController(PrenotazioneService prenotazioneService) {
        this.prenotazioneService = prenotazioneService;
    }

    //POST

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    public Prenotazione salvaPrenotaziona(@RequestBody PrenotazionePayload payload) {
        return this.prenotazioneService.salvaPrenotazione(payload);
    }

    //DELETE
    @DeleteMapping("/{idPrenotazione}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    public void findByIdAndDelete(@PathVariable long idPrenotazione) {
        this.prenotazioneService.findByIdAndDelete(idPrenotazione);
    }


    //GET
    @GetMapping("/{idPrenotazione}")
    public Prenotazione findById(@PathVariable long idPrenotazione) {
        return this.prenotazioneService.findById(idPrenotazione);
    }


    //GET
    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public Page<Prenotazione> findAllC(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "data") String orderBy) {
        return prenotazioneService.findAll(page, size, orderBy);
    }

    //PUT
    @PutMapping("/{idPrenotazione}")
    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    public Prenotazione findByIdAndUpdate(@PathVariable long idPrenotazione, @RequestBody PrenotazionePayload payload) {
        return this.prenotazioneService.findByIdAndUpdate(idPrenotazione, payload);
    }

    //GET
    @GetMapping("/utente")
    @PreAuthorize("hasAnyAuthority('USER')")
    public List<Prenotazione> getPrenotazioniUtente(){
        return prenotazioneService.findPrenotazioniUtente();
    }
}


