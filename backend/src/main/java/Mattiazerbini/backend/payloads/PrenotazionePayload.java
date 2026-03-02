package Mattiazerbini.backend.payloads;

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

    @NotNull(message = "L'utente è obbligatorio")
    private Long utenteId;

    @NotNull(message = "Il campo è obbligatorio")
    private Long campoId;

    private Long servizioId;

}
