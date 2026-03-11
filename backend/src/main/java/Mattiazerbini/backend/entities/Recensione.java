package Mattiazerbini.backend.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name= "recensioni")
public class Recensione {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String testo;
    private int stelle;
    private LocalDate data;

    @ManyToOne
    @JoinColumn(name = "id_utente")
    private Utente utente;

    public Recensione(String testo, int stelle, LocalDate data, Utente utente) {
        this.testo = testo;
        this.stelle = stelle;
        this.data = data;
        this.utente = utente;
    }

    public Recensione() {
    }



    public Long getId() {
        return id;
    }

    public String getTesto() {
        return testo;
    }

    public void setTesto(String testo) {
        this.testo = testo;
    }

    public int getStelle() {
        return stelle;
    }

    public void setStelle(int stelle) {
        this.stelle = stelle;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }
}
