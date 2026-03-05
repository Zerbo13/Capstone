package Mattiazerbini.backend.payloads;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class UtentePayload {
    @NotBlank(message = "Il nome è obbligatorio")
    private String nome;
    @NotBlank(message = "Il cognome è obbligatorio")
    private String cognome;
    private String dataNascita;
    @NotBlank(message = "La mail è obbligatoria")
    private String email;
    @NotBlank(message = "La password è obbligatoria")
    @Size(min = 6, max = 100)
    private String password;
    @NotBlank(message = "Il telfono è obbligatorio")
    private String telefono;

    public UtentePayload(String nome, String cognome, String dataNascita, String email, String password, String telefono) {
        this.nome = nome;
        this.cognome = cognome;
        this.dataNascita = dataNascita;
        this.email = email;
        this.password = password;
        this.telefono = telefono;
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public String getDataNascita() {
        return dataNascita;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getTelefono() {
        return telefono;
    }
}
