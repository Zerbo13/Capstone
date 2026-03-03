package Mattiazerbini.backend.controller;

import Mattiazerbini.backend.entities.Campo;
import Mattiazerbini.backend.entities.Servizio;
import Mattiazerbini.backend.payloads.CampoPayload;
import Mattiazerbini.backend.payloads.ServizioPayload;
import Mattiazerbini.backend.services.CampoService;
import Mattiazerbini.backend.services.ServizioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/campi")
public class CampoController {

    private CampoService campoService;

    @Autowired
    public CampoController(CampoService campoService) {
        this.campoService = campoService;
    }

    //POST
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('ADMIN')")
    public Campo salvaCampo(@RequestBody CampoPayload payload) {
        return this.campoService.salvaCampo(payload);
    }

    //DELETE
    @DeleteMapping("/{idCampo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('ADMIN')")
    public void findByIdAndDelete(@PathVariable long idCampo) {
        this.campoService.findByIdAndDelete(idCampo);
    }


    //GET
    @GetMapping("/{idCampo}")
    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    public Campo findById(@PathVariable long idCampo) {
        return this.campoService.findById(idCampo);
    }


    //GET
    @GetMapping
    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    public Page<Campo> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "nome") String orderBy) {
        return campoService.findAll(page, size, orderBy);
    }

    //PUT
    @PutMapping("/{idCampo}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Campo findByIdAndUpdate(@PathVariable long idCampo, @RequestBody CampoPayload payload) {
        return this.campoService.findByIdAndUpdate(idCampo, payload);
    }
}
