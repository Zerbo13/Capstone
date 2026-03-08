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
    private String immagine;

    public ServizioPayload(String nome, String descrizione, Double prezzo, LocalTime durata, boolean attivo, String immagine) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.prezzo = prezzo;
        this.durata = durata;
        this.attivo = attivo;
        this.immagine = immagine;
    }

    public String getNome() {
        return nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public Double getPrezzo() {
        return prezzo;
    }

    public LocalTime getDurata() {
        return durata;
    }

    public boolean isAttivo() {
        return attivo;
    }

    public String getImmagine() {
        return immagine;
    }
}
