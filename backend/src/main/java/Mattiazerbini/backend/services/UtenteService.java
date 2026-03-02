package Mattiazerbini.backend.services;

import Mattiazerbini.backend.Excenptions.NotFoundException;
import Mattiazerbini.backend.entities.Utente;
import Mattiazerbini.backend.payloads.UtentePayload;
import Mattiazerbini.backend.repositories.UtenteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UtenteService {

    private final UtenteRepository utenteRepository;

    @Autowired

    public UtenteService(UtenteRepository utenteRepository) {
        this.utenteRepository = utenteRepository;
    }

    public Utente salvaUtente(UtentePayload payload){
        if(utenteRepository.existsByEmail(payload.getEmail())){
            throw new RuntimeException("Email gia in uso!");
        }
        Utente newUtente = new Utente(
                payload.getNome(),
                payload.getCognome(),
                payload.getDataNascita(),
                payload.getEmail(),
                payload.getPassword(),
                payload.getTelefono(),
                java.time.LocalDate.now(),
                true
        );
        Utente utenteSalvato = this.utenteRepository.save(newUtente);
        log.info("L'utente "+newUtente.getNome()+" " +newUtente.getCognome()+ " è stato inserito con successo!");
        return utenteSalvato;
    }

    //FIND ALL
    public Page<Utente> findAll(int page, int size, String sortBy) {
        if (page <= 0) page = 0;
        if (size < 0 || size > 150) size = 10;
        if (sortBy == null) sortBy = "nome";
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return utenteRepository.findAll(pageable);
    }

    //FIND BY ID
    public Utente findById(long idUtente) {
        return utenteRepository.findById(idUtente)
                .orElseThrow(() -> new NotFoundException("L'utente con id " + idUtente + " non è stato trovato"));
    }

    //ELIMINA UTENTE
    public void findByIdAndDelete(long idUtente) {
        Utente found = this.findById(idUtente);
        this.utenteRepository.delete(found);
        log.info("L'utente con id "+found.getId()+ " è stato eliminata con successo!");
    }

    //MODIFICA UTENTE
    public Utente findByIdAndUpdate(long idUtente, UtentePayload payload) {

        Utente found = this.findById(idUtente);
        found.setNome(payload.getNome());
        found.setCognome(payload.getCognome());
        found.setDataNascita(payload.getDataNascita());
        found.setEmail(payload.getEmail());
        found.setPassword(payload.getPassword());
        found.setTelefono(payload.getTelefono());

        Utente utenteModificato = this.utenteRepository.save(found);
        log.info("L'utente con id " + utenteModificato.getId() + " è stato modificato correttamente");
        return utenteModificato;
    }

}
