package Mattiazerbini.backend.config;

import Mattiazerbini.backend.entities.Ruolo;
import Mattiazerbini.backend.entities.Utente;
import Mattiazerbini.backend.repositories.CampoRepository;
import Mattiazerbini.backend.repositories.ServizioRepository;
import Mattiazerbini.backend.repositories.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;

@Configuration
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UtenteRepository utenteRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        // Crea admin se non esiste
        if (!utenteRepository.existsByEmail("admin@padel.it")) {
            Utente admin = new Utente("Admin", "Sistema", "2006-08-14", "admin@padel.it", passwordEncoder.encode("admin123"), "3334567891", LocalDate.now(), true, null);
            admin.setRuolo(Ruolo.ADMIN);
            utenteRepository.save(admin);
        }
    }
}
