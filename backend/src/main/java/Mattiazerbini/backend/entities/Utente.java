package Mattiazerbini.backend.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "utenti")
public class Utente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String cognome;
    private LocalDate dataNascita;
    private String email;
    private String password;
    private String telefono;
    private LocalDate dataRegistrazione;
    private boolean attivo;

    public Utente(String nome, String cognome, LocalDate dataNascita, String email, String password, String telefono, LocalDate dataRegistrazione, boolean attivo) {
        this.nome = nome;
        this.cognome = cognome;
        this.dataNascita = dataNascita;
        this.email = email;
        this.password = password;
        this.telefono = telefono;
        this.dataRegistrazione = dataRegistrazione;
        this.attivo = attivo;
    }

    public Utente() {
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

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public LocalDate getDataNascita() {
        return dataNascita;
    }

    public void setDataNascita(LocalDate dataNascita) {
        this.dataNascita = dataNascita;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDataRegistrazione(LocalDate dataRegistrazione) {
        this.dataRegistrazione = dataRegistrazione;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public LocalDate getdataRegistrazione() {
        return dataRegistrazione;
    }

    public void setdataRegistrazione(LocalDate dataRegistrazione) {
        this.dataRegistrazione = dataRegistrazione;
    }

    public boolean isAttivo() {
        return attivo;
    }

    public void setAttivo(boolean attivo) {
        this.attivo = attivo;
    }

}
