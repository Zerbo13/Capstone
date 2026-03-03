package Mattiazerbini.backend.entities;

import jakarta.persistence.*;
import org.hibernate.tool.schema.internal.exec.ScriptTargetOutputToStdout;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "prenotazioni")
public class Prenotazione {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate data;
    private LocalTime oraInizio;
    private LocalTime oraFine;
    private String note;
    private LocalDate dataCreazione;
    @Enumerated(EnumType.STRING)
    private Stato stato;

    @ManyToOne
    @JoinColumn(name = "id_utente")
    private Utente utente;

    @ManyToOne
    @JoinColumn(name = "id_campo")
    private Campo campo;

    @ManyToOne
    @JoinColumn(name = "id_servizio")
    private Servizio servizio;


    public Prenotazione(LocalDate data, LocalTime oraInizio, LocalTime oraFine, String note, LocalDate dataCreazione, Stato stato, Utente utente, Campo campo, Servizio servizio) {
        this.data = data;
        this.oraInizio = oraInizio;
        this.oraFine = oraFine;
        this.note = note;
        this.dataCreazione = dataCreazione;
        this.stato = stato;
        this.utente = utente;
        this.campo = campo;
        this.servizio = servizio;
    }

    public Prenotazione() {
    }



    public Long getId() {
        return id;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public LocalTime getOraInizio() {
        return oraInizio;
    }

    public void setOraInizio(LocalTime oraInizio) {
        this.oraInizio = oraInizio;
    }

    public LocalTime getOraFine() {
        return oraFine;
    }

    public void setOraFine(LocalTime oraFine) {
        this.oraFine = oraFine;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public LocalDate getDataCreazione() {
        return dataCreazione;
    }

    public void setDataCreazione(LocalDate dataCreazione) {
        this.dataCreazione = dataCreazione;
    }

    public Stato getStato() {
        return stato;
    }

    public void setStato(Stato stato) {
        this.stato = stato;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    public Campo getCampo() {
        return campo;
    }

    public void setCampo(Campo campo) {
        this.campo = campo;
    }

    public Servizio getServizio() {
        return servizio;
    }

    public void setServizio(Servizio servizio) {
        this.servizio = servizio;
    }
}
