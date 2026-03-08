package Mattiazerbini.backend.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "campi")
public class Campo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String descrizione;
    private boolean coperto;
    private Double prezzoOra;
    private boolean attivo;
    private String tipo;
    private LocalTime oraApertura;
    private LocalTime oraChiusura;
    private String immagine;


    public Campo(String nome, String descrizione, boolean coperto, double prezzoOra, boolean attivo, String tipo, String immagine) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.coperto = coperto;
        this.prezzoOra = prezzoOra;
        this.attivo = attivo;
        this.tipo = tipo;
        this.oraApertura = LocalTime.of(8, 0);
        this.oraChiusura = LocalTime.of(22, 0);
        this.immagine = immagine;
    }

    public Campo() {
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

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public boolean isCoperto() {
        return coperto;
    }

    public void setCoperto(boolean coperto) {
        this.coperto = coperto;
    }

    public double getPrezzoOra() {
        return prezzoOra;
    }

    public void setPrezzoOra(double prezzoOra) {
        this.prezzoOra = prezzoOra;
    }

    public boolean isAttivo() {
        return attivo;
    }

    public void setAttivo(boolean attivo) {
        this.attivo = attivo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setPrezzoOra(Double prezzoOra) {
        this.prezzoOra = prezzoOra;
    }

    public LocalTime getOraApertura() {
        return oraApertura;
    }

    public void setOraApertura(LocalTime oraApertura) {
        this.oraApertura = oraApertura;
    }

    public LocalTime getOraChiusura() {
        return oraChiusura;
    }

    public void setOraChiusura(LocalTime oraChiusura) {
        this.oraChiusura = oraChiusura;
    }

    public String getImmagine() {
        return immagine;
    }

    public void setImmagine(String immagine) {
        this.immagine = immagine;
    }
}
