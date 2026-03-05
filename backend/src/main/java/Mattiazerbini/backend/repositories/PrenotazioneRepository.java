package Mattiazerbini.backend.repositories;

import Mattiazerbini.backend.entities.Campo;
import Mattiazerbini.backend.entities.Prenotazione;
import Mattiazerbini.backend.entities.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface PrenotazioneRepository extends JpaRepository<Prenotazione, Long> {
    List<Prenotazione> findByUtenteId(Long utenteId);
    List<Prenotazione> findByCampo(Campo campo);
    List<Prenotazione> findByData(LocalDate data);

    //VERIFICA SE ESISTE GIA UNA PRENOTAZIONE PER QUEL CAMPO IN QUELLA DATA
    // CHE SI SOVRAPPONE ALL'ORA CHE L'UTENTE VUOLE PRENOTARE
    //Se ritorna true allora il campo non è disponibile
    boolean existsByCampoAndDataAndOraInizioLessThanAndOraFineGreaterThan
            (Campo campo, LocalDate data, LocalTime oraFine, LocalTime oraInizio);

    public List<Prenotazione> findByCampoIdAndData(Long campoId, LocalDate data);

}
