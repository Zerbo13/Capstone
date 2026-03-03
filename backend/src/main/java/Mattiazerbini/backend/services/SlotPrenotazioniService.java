package Mattiazerbini.backend.services;

import Mattiazerbini.backend.entities.Prenotazione;
import Mattiazerbini.backend.payloads.SlotPrenotazioni;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class SlotPrenotazioniService {

    //VENGONO GENERATI GLI SLOT POSSIBILI CONTROLLANDO ORARIO APERTURA E CHIUSURA,
    // LA DURATA DI UNO SLOT CHE SARA' DI 120 MINUTI E LO STEP CHE TRA OGNI SLOT DI 30 MINUTI
    public List<SlotPrenotazioni> slotOra(LocalTime apertura, LocalTime chiusura, int durata, int step){
        List<SlotPrenotazioni> slot = new ArrayList<>();
        LocalTime inizio = apertura;
        //QUI SI CONTINUA A GENENRARE FINCHE LO SLOT NON RIENTRA NELL'ORA DI APERTURA
        while (inizio.plusMinutes(durata).isBefore(chiusura.minusSeconds(1))){
            LocalTime fine = inizio.plusMinutes(durata);
            slot.add(new SlotPrenotazioni(inizio, fine));
            inizio = inizio.plusMinutes(step);
        }
        return slot;
    }

    //CONTROLLA CHE NON SI SOVRAPPONGONO DUE INTERVALLI
    private boolean isSovrapposto(LocalTime inizio1, LocalTime fine1, LocalTime inizio2, LocalTime fine2){
        //INIZIO DI UNO PRIMA DELLA FINE DELL'ALTRO
        return inizio1.isBefore(fine2) && inizio1.isBefore(fine1);
    }

    //CONTROLLA TUTTI GLI SLOT ED ELIMINA QUELLI CHE SI SOVRAPPONGONO CON LE PRENOTAZIONI
    public List<SlotPrenotazioni> filtroSlotDisponibili(List<SlotPrenotazioni> slots, List<Prenotazione> prenotazione){
        return slots.stream()
                .filter(slot -> prenotazione.stream().noneMatch(p -> isSovrapposto
                        (slot.getInizio(), slot.getFine(), p.getOraInizio(), p.getOraFine()))).toList();
    }
}
