package Mattiazerbini.backend.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "utenti")
public class Utente implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String cognome;
    private String dataNascita;
    private String email;
    private String password;
    private String telefono;
    private LocalDate dataRegistrazione;
    private boolean attivo;
    private String avatar;
    @Enumerated(EnumType.STRING)
    private Ruolo ruolo;

    public Utente(String nome, String cognome, String dataNascita, String email, String password, String telefono, LocalDate dataRegistrazione, boolean attivo, String avatar) {
        this.nome = nome;
        this.cognome = cognome;
        this.dataNascita = dataNascita;
        this.email = email;
        this.password = password;
        this.telefono = telefono;
        this.dataRegistrazione = dataRegistrazione;
        this.attivo = attivo;
        this.avatar = avatar;
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

    public String getDataNascita() {
        return dataNascita;
    }

    public void setDataNascita(String dataNascita) {
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

    public Ruolo getRuolo() {
        return ruolo;
    }

    public LocalDate getDataRegistrazione() {
        return dataRegistrazione;
    }

    public void setRuolo(Ruolo ruolo) {
        this.ruolo = ruolo;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.ruolo.name()));
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    //UTENTE SCADUTO
    @Override
    public boolean isAccountNonExpired(){
        return true;
    }

    //UTENTE BLOCCATO
    @Override
    public boolean isAccountNonLocked(){
        return true;
    }

    //UTENTE CON CREDENZIALI SCADUTE
    @Override
    public boolean isCredentialsNonExpired(){
        return true;
    }

    //UTENTE ATTIVO
    @Override
    public boolean isEnabled(){
        return true;
    }


}
