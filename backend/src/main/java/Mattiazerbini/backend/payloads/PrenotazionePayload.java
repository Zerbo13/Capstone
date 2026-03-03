package Mattiazerbini.backend.payloads;

import Mattiazerbini.backend.entities.Campo;
import Mattiazerbini.backend.entities.Servizio;
import Mattiazerbini.backend.entities.Utente;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.time.LocalTime;

public class PrenotazionePayload {


    @NotNull(message = "La data è obbligatoria")
    @FutureOrPresent(message = "La data non può essere nel passato")
    private LocalDate data;

    @NotNull(message = "L'ora di inizio è obbligatoria")
    private LocalTime oraInizio;

    @NotNull(message = "L'ora di fine è obbligatoria")
    private LocalTime oraFine;

    @Size(max= 255)
    private String note;


    @NotNull(message = "Il campo è obbligatorio")
    private Long campoId;

    @NotNull(message = "Il servizio è obbligatorio")
    private Long servizioId;

    public PrenotazionePayload(LocalDate data, LocalTime oraInizio, LocalTime oraFine, String note, Long campoId, Long servizioId) {
        this.data = data;
        this.oraInizio = oraInizio;
        this.oraFine = oraFine;
        this.note = note;
        this.campoId = campoId;
        this.servizioId = servizioId;
    }

    public LocalDate getData() {
        return data;
    }

    public LocalTime getOraInizio() {
        return oraInizio;
    }

    public LocalTime getOraFine() {
        return oraFine;
    }

    public String getNote() {
        return note;
    }

    public Long getCampoId() {
        return campoId;
    }

    public Long getServizioId() {
        return servizioId;
    }
}
