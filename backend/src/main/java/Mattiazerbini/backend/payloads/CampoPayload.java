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
    private Double prezzoOra;
    private boolean attivo;
    @NotBlank(message = "Il tipo è obbligatorio")
    private String tipo;

    public CampoPayload(String nome, String descrizione, boolean coperto, Double prezzoOra, boolean attivo, String tipo) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.coperto = coperto;
        this.prezzoOra = prezzoOra;
        this.attivo = attivo;
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public boolean isCoperto() {
        return coperto;
    }

    public Double getPrezzoOra() {
        return prezzoOra;
    }

    public boolean isAttivo() {
        return attivo;
    }

    public String getTipo() {
        return tipo;
    }


}
