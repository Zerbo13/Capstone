package Mattiazerbini.backend.services;

import Mattiazerbini.backend.Excenptions.NotFoundException;
import Mattiazerbini.backend.Excenptions.UnauthorizedException;
import Mattiazerbini.backend.entities.Utente;
import Mattiazerbini.backend.payloads.LoginDTO;
import Mattiazerbini.backend.repositories.UtenteRepository;
import Mattiazerbini.backend.security.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UtenteService usersService;
    private final JWTTools jwtTools;
    private final PasswordEncoder bcrypt;
    private final UtenteRepository utenteRepository;

    @Autowired
    public AuthService(UtenteService usersService, JWTTools jwtTools, PasswordEncoder bcrypt, UtenteRepository utenteRepository) {
        this.usersService = usersService;
        this.jwtTools = jwtTools;
        this.bcrypt = bcrypt;
        this.utenteRepository = utenteRepository;
    }

    public String checkCredentialsAndGenerateToken(LoginDTO body) {
        Utente found = this.usersService.findByEmail(body.email());
        if (bcrypt.matches(body.password(), found.getPassword())) {
            String accessToken = jwtTools.generateToken(found);
            return accessToken;

        } else {
            throw new UnauthorizedException("Credenziali errate!");
        }
    }

    public Utente getUserByEmail(String email){
        return utenteRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("Utente non trovato"));
    }
}
