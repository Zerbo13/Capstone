package Mattiazerbini.backend.payloads;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;
import java.time.LocalTime;

public class ServizioPayload {
    @NotBlank(message = "Il nome è obbligatorio")
    private String nome;
    @NotBlank(message = "Il campo descrizione è obbligatorio")
    private String descrizione;
    @NotBlank(message = "Il prezzo è obbligatorio")
    @Positive(message = "Deve essere maggiore di zero")
    private Double prezzo;
    @NotBlank(message = "La durata è obbligatoria")
    private LocalTime durata;
    private boolean attivo;

    public ServizioPayload(String nome, String descrizione, Double prezzo, LocalTime durata, boolean attivo) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.prezzo = prezzo;
        this.durata = durata;
        this.attivo = attivo;
    }
}
