package Mattiazerbini.backend.payloads;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public class CampoPayload {
    @NotBlank(message = "Il nome è obbligatorio")
    private String nome;
    @NotBlank(message = "Il campo descrizione è obbligatorio")
    private String descrizione;
    private boolean coperto;
    @NotBlank(message = "Il prezzo è obbligatorio")
    @Positive(message = "Deve essere maggiore di zero")
    private Double prezzo;
    private boolean attivo;
    @NotBlank(message = "Il tipo è obbligatorio")
    private String tipo;

    public CampoPayload(String nome, String descrizione, boolean coperto, Double prezzo, boolean attivo, String tipo) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.coperto = coperto;
        this.prezzo = prezzo;
        this.attivo = attivo;
        this.tipo = tipo;
    }
}
