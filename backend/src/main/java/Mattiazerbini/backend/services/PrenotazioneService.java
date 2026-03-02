package Mattiazerbini.backend.services;

import Mattiazerbini.backend.Excenptions.NotFoundException;
import Mattiazerbini.backend.entities.*;
import Mattiazerbini.backend.payloads.PrenotazionePayload;
import Mattiazerbini.backend.payloads.UtentePayload;
import Mattiazerbini.backend.repositories.CampoRepository;
import Mattiazerbini.backend.repositories.PrenotazioneRepository;
import Mattiazerbini.backend.repositories.ServizioRepository;
import Mattiazerbini.backend.repositories.UtenteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@Slf4j
public class PrenotazioneService {

    public final PrenotazioneRepository prenotazioneRepository;
    public final CampoRepository campoRepository;
    public final UtenteRepository utenteRepository;
    public final ServizioRepository servizioRepository;

    @Autowired

    public PrenotazioneService(PrenotazioneRepository prenotazioneRepository, CampoRepository campoRepository, UtenteRepository utenteRepository, ServizioRepository servizioRepository) {
        this.prenotazioneRepository = prenotazioneRepository;
        this.campoRepository = campoRepository;
        this.utenteRepository = utenteRepository;
        this.servizioRepository = servizioRepository;
    }

    public Prenotazione salvaPrenotazione(PrenotazionePayload payload){
        //TROVO L'UENTE
        Utente utente = utenteRepository.findById(payload.getUtenteId().getId())
                .orElseThrow(() -> new RuntimeException("Utente non trovato!"));
        //TROVO IL CAMPO E VEDO SE E' ATTIVO
        Campo campo = campoRepository.findById(payload.getCampoId().getId())
                .orElseThrow(() -> new RuntimeException("Campo non trovato!"));
        if (!campo.isAttivo()){
            throw new RuntimeException("Il campo non è attivo");
        }
        //TROVO IL SERVIZIO E VEDO SE E' ATTIVO
        Servizio servizio = servizioRepository.findById(payload.getServizioId().getId())
                .orElseThrow(() -> new RuntimeException("Servizio non trovato!"));
        if (!servizio.isAttivo()){
            throw new RuntimeException("Il servizio non è attivo");
        }
        //VEDO SE è DISPONIBILE IL CAMPO PER QUELL'ORARIO
        if (payload.getOraFine().isAfter(payload.getOraInizio())){
            throw new RuntimeException("L'orario di fine servizio deve essere dopo quello di inizio servizio!");
        }
        boolean prenotato = prenotazioneRepository
                .existsByCampoAndDataAndOrOraInizioLessThanAndOraFineGreaterThan(
                        campo,
                        payload.getData(),
                        payload.getOraInizio(),
                        payload.getOraFine()
                );
        if (prenotato){
            throw new RuntimeException("Il campo è già prenotato per quest'orario");
        }
        Prenotazione newPrenotazione = new Prenotazione(
                payload.getData(),
                payload.getOraInizio(),
                payload.getOraFine(),
                payload.getNote(),
                LocalDate.now(),
                Stato.CONFERMATA,
                utente,
                campo,
                servizio
        );
        Prenotazione prenotazioneSalvato = this.prenotazioneRepository.save(newPrenotazione);
        log.info("la prenotazione "+newPrenotazione.getId()+" dell'utente" +newPrenotazione.getUtente()+ " è stata inserita con successo!");
        return prenotazioneSalvato;
    }

    //FIND ALL
    public Page<Prenotazione> findAll(int page, int size, String sortBy) {
        if (page <= 0) page = 0;
        if (size < 0 || size > 150) size = 10;
        if (sortBy == null) sortBy = "nome";
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return prenotazioneRepository.findAll(pageable);
    }

    //FIND BY ID
    public Prenotazione findById(long idPrenotazione) {
        return prenotazioneRepository.findById(idPrenotazione)
                .orElseThrow(() -> new NotFoundException("La prenotazione con id " + idPrenotazione + " non è stata trovata"));
    }

    //ELIMINA UTENTE
    public void findByIdAndDelete(long idPrenotazione) {
        Prenotazione found = this.findById(idPrenotazione);
        this.prenotazioneRepository.delete(found);
        log.info("La prenotazione con id "+found.getId()+ " è stata eliminata con successo!");
    }

    //MODIFICA UTENTE
    public Prenotazione findByIdAndUpdate(long idPrenotazione, PrenotazionePayload payload) {

        Prenotazione found = this.findById(idPrenotazione);
        found.setData(payload.getData());
        found.setOraInizio(payload.getOraFine());
        found.setNote(payload.getNote());
        found.setUtente(payload.getUtenteId());
        found.setCampo(payload.getCampoId());
        found.setServizio(payload.getServizioId());

        Prenotazione prenotazioenModificata = this.prenotazioneRepository.save(found);
        log.info("La prenotazione con id " + prenotazioenModificata.getId() + " è stata modificata correttamente");
        return prenotazioenModificata;
    }
}
