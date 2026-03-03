package Mattiazerbini.backend.controller;

import Mattiazerbini.backend.entities.Servizio;
import Mattiazerbini.backend.payloads.ServizioPayload;
import Mattiazerbini.backend.services.ServizioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/servizi")
public class ServizioController {

    private ServizioService servizioService;

    @Autowired
    public ServizioController(ServizioService servizioService) {
        this.servizioService = servizioService;
    }

    //POST
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Servizio salvaServizio(@RequestBody ServizioPayload payload) {
        return this.servizioService.salvaServizio(payload);
    }

    //DELETE
    @DeleteMapping("/{idServizio}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable long idServizio) {
        this.servizioService.findByIdAndDelete(idServizio);
    }


    //GET
    @GetMapping("/{idServizio}")
    public Servizio findById(@PathVariable long idServizio) {
        return this.servizioService.findById(idServizio);
    }


    //GET
    @GetMapping
    public Page<Servizio> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "nome") String orderBy) {
        return servizioService.findAll(page, size, orderBy);
    }

    //PUT
    @PutMapping("/{idServizio}")
    public Servizio findByIdAndUpdate(@PathVariable long idServizio, @RequestBody ServizioPayload payload) {
        return this.servizioService.findByIdAndUpdate(idServizio, payload);
    }

}
