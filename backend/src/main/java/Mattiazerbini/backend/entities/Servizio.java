package Mattiazerbini.backend.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "servizi")
public class Servizio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String descrizione;
    private Double prezzo;
    private LocalTime durata;
    private boolean attivo;
    private String immagine;

    public Servizio( String nome, String descrizione, Double prezzo, LocalTime durata, boolean attivo, String immagine) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.prezzo = prezzo;
        this.durata = durata;
        this.attivo = attivo;
        this.immagine = immagine;
    }

    public Servizio() {
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizone(String descrizione) {
        this.descrizione = descrizione;
    }

    public Double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(Double prezzo) {
        this.prezzo = prezzo;
    }

    public LocalTime getDurata() {
        return durata;
    }

    public void setDurata(LocalTime durata) {
        this.durata = durata;
    }

    public boolean isAttivo() {
        return attivo;
    }

    public void setAttivo(boolean attivo) {
        this.attivo = attivo;
    }

    public String getImmagine() {
        return immagine;
    }

    public void setImmagine(String immagine) {
        this.immagine = immagine;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }
}
