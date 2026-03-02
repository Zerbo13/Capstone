package Mattiazerbini.backend.services;

import Mattiazerbini.backend.Excenptions.NotFoundException;
import Mattiazerbini.backend.entities.Campo;
import Mattiazerbini.backend.entities.Servizio;
import Mattiazerbini.backend.entities.Utente;
import Mattiazerbini.backend.payloads.CampoPayload;
import Mattiazerbini.backend.payloads.ServizioPayload;
import Mattiazerbini.backend.payloads.UtentePayload;
import Mattiazerbini.backend.repositories.CampoRepository;
import Mattiazerbini.backend.repositories.ServizioRepository;
import Mattiazerbini.backend.repositories.UtenteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ServizioService {

    public final ServizioRepository servizioRepository;

    @Autowired

    public ServizioService(ServizioRepository servizioRepository) {
        this.servizioRepository = servizioRepository;
    }

    public Servizio salvaServizio(ServizioPayload payload){

        Servizio newServizio = new Servizio(
                payload.getNome(),
                payload.getDescrizione(),
                payload.getPrezzo(),
                payload.getDurata(),
                payload.isAttivo()
        );
        Servizio servizioSalvato = this.servizioRepository.save(newServizio);
        log.info("Il servizio "+newServizio.getNome()+ " è stato inserito con successo!");
        return servizioSalvato;
    }

    //FIND ALL
    public Page<Servizio> findAll(int page, int size, String sortBy) {
        if (page <= 0) page = 0;
        if (size < 0 || size > 150) size = 10;
        if (sortBy == null) sortBy = "nome";
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return servizioRepository.findAll(pageable);
    }

    //FIND BY ID
    public Servizio findById(long idServizio) {
        return servizioRepository.findById(idServizio)
                .orElseThrow(() -> new NotFoundException("Il servizio con id " + idServizio + " non è stato trovato"));
    }

    //ELIMINA UTENTE
    public void findByIdAndDelete(long idServizio) {
        Servizio found = this.findById(idServizio);
        this.servizioRepository.delete(found);
        log.info("Il servizio con id "+found.getId()+ " è stato eliminata con successo!");
    }

    //MODIFICA UTENTE
    public Servizio findByIdAndUpdate(long idServizio, ServizioPayload payload) {

        Servizio found = this.findById(idServizio);
        found.setNome(payload.getNome());
        found.setDescrizone(payload.getDescrizione());
        found.setPrezzo(payload.getPrezzo());
        found.setDurata(payload.getDurata());
        found.setAttivo(payload.isAttivo());

        Servizio servizioModificato = this.servizioRepository.save(found);
        log.info("Il servizio con id " + servizioModificato.getId() + " è stato modificato correttamente");
        return servizioModificato;
    }
}
