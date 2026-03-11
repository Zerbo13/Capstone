package Mattiazerbini.backend.services;

import Mattiazerbini.backend.entities.Recensione;
import Mattiazerbini.backend.entities.Utente;
import Mattiazerbini.backend.payloads.RecensionePayload;
import Mattiazerbini.backend.repositories.RecensioneRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
public class RecensioneService {

    @Autowired
    private RecensioneRepository recensioneRepository;

    public Recensione salvaRecensione(RecensionePayload payload, Utente utente){
        Recensione recensioneSalvata = new Recensione(payload.getTesto(), payload.getStella(), LocalDate.now(), utente);
        return recensioneRepository.save(recensioneSalvata);
    }

    public List<Recensione> findAll(){
        return recensioneRepository.findAll();
    }
}
