package Mattiazerbini.backend.controller;

import Mattiazerbini.backend.entities.Campo;
import Mattiazerbini.backend.entities.Utente;
import Mattiazerbini.backend.payloads.CampoPayload;
import Mattiazerbini.backend.payloads.UtentePayload;
import Mattiazerbini.backend.services.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/prenotazioni")
public class PrenotazioneController {
    private UtenteService utenteService;

    @Autowired
    public void UtenteController(UtenteService utenteService) {
        this.utenteService = utenteService;
    }

    //POST
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Utente salvaUtente(@RequestBody UtentePayload payload) {
        return this.utenteService.salvaUtente(payload);
    }

    //DELETE
    @DeleteMapping("/{idUtente}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable long idUtente) {
        this.utenteService.findByIdAndDelete(idUtente);
    }


    //GET
    @GetMapping("/{idUtente}")
    public Utente findById(@PathVariable long idUtente) {
        return this.utenteService.findById(idUtente);
    }


    //GET
    @GetMapping
    public Page<Utente> findAllClientes(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "data") LocalDate orderBy) {
        return utenteService.findAll(page, size, String.valueOf(orderBy));
    }

    //PUT
    @PutMapping("/{idUtente}")
    public Utente findByIdAndUpdate(@PathVariable long idUtente, @RequestBody UtentePayload payload) {
        return this.utenteService.findByIdAndUpdate(idUtente, payload);
    }
}
