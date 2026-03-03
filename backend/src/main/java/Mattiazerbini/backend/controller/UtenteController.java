package Mattiazerbini.backend.controller;

import Mattiazerbini.backend.entities.Campo;
import Mattiazerbini.backend.entities.Utente;
import Mattiazerbini.backend.payloads.CampoPayload;
import Mattiazerbini.backend.payloads.UtentePayload;
import Mattiazerbini.backend.services.CampoService;
import Mattiazerbini.backend.services.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/utenti")
public class UtenteController {
    private UtenteService utenteService;

    @Autowired
    public UtenteController(UtenteService utenteService) {
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
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/{idUtente}")
    public Utente findById(@PathVariable long idUtente) {
        return this.utenteService.findById(idUtente);
    }


    //GET
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public Page<Utente> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "nome") String orderBy) {
        return utenteService.findAll(page, size, orderBy);
    }

    //PUT
    @PutMapping("/{idUtente}")
    public Utente findByIdAndUpdate(@PathVariable long idUtente, @RequestBody UtentePayload payload) {
        return this.utenteService.findByIdAndUpdate(idUtente, payload);
    }
}
