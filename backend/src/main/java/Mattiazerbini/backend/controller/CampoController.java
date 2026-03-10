package Mattiazerbini.backend.controller;

import Mattiazerbini.backend.Excenptions.DataNoValidationException;
import Mattiazerbini.backend.entities.Campo;
import Mattiazerbini.backend.entities.Prenotazione;
import Mattiazerbini.backend.entities.Servizio;
import Mattiazerbini.backend.payloads.CampoPayload;
import Mattiazerbini.backend.payloads.ServizioPayload;
import Mattiazerbini.backend.payloads.SlotPrenotazioni;
import Mattiazerbini.backend.services.CampoService;
import Mattiazerbini.backend.services.PrenotazioneService;
import Mattiazerbini.backend.services.ServizioService;
import Mattiazerbini.backend.services.SlotPrenotazioniService;
import jdk.dynalink.linker.LinkerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/campi")
public class CampoController {

    private CampoService campoService;
    @Autowired
    private PrenotazioneService prenotazioneService;
    @Autowired
    private SlotPrenotazioniService slotPrenotazioniService;

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

    //SLOT DISPONIBILI
    @GetMapping("/{idCampo}/orari")
    public List<SlotPrenotazioni> getOrariDisponibili(
            @PathVariable Long idCampo,
            @RequestParam LocalDate data
            ){
        //CONTROLLA SE LA DATA E' OGGI O IN  FUTURO NO IN PASSATO
        if (data.isBefore(LocalDate.now())){
            throw new DataNoValidationException("Non è possibile visualizzare gli orari per i giorni passati");
        }
        //TROVA IL CAMPO
        Campo campo = campoService.findById(idCampo);
        //ORARI APERTURA E CHIUSURA DEL CAMPO
        LocalTime oraApertura = campo.getOraApertura();
        LocalTime oraChiusura = campo.getOraChiusura();
        int durata = 90; //DURATA SLOT
        int step = 30; //INTERVALLO TRA UNO SLOT E L'ALTRO
       List<SlotPrenotazioni> slots = slotPrenotazioniService.slotOra(oraApertura, oraChiusura, durata, step);
       List<Prenotazione> prenotazioni = prenotazioneService.findByCampoAndData(idCampo, data);
        return slotPrenotazioniService.filtroSlotDisponibili(slots, prenotazioni);
    }

    //PATH
    @PatchMapping("/{idCampo}/immagine")
    public Campo uploadImmagine(@PathVariable Long idCampo, @RequestParam("immagine") MultipartFile file)
            throws IOException {
        return campoService.uploadImmagine(idCampo, file);
    }
}
