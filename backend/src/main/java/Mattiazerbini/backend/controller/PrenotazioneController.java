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
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/prenotazioni")
public class PrenotazioneController {
    private PrenotazioneService prenotazioneService;

    @Autowired
    public void PrenotazioneController(PrenotazioneService prenotazioneService) {
        this.prenotazioneService = prenotazioneService;
    }

    //POST
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Prenotazione salvaPrenotaziona(@RequestBody PrenotazionePayload payload) {
        return this.prenotazioneService.salvaPrenotazione(payload);
    }

    //DELETE
    @DeleteMapping("/{idUtente}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
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
    public Page<Prenotazione> findAllC(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "nome") String orderBy) {
        return prenotazioneService.findAll(page, size, orderBy);
    }

    //PUT
    @PutMapping("/{idPrenotazione}")
    public Prenotazione findByIdAndUpdate(@PathVariable long idPrenotazione, @RequestBody PrenotazionePayload payload) {
        return this.prenotazioneService.findByIdAndUpdate(idPrenotazione, payload);
    }
}
