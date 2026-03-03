package Mattiazerbini.backend.services;

import Mattiazerbini.backend.Excenptions.UnauthorizedException;
import Mattiazerbini.backend.entities.Utente;
import Mattiazerbini.backend.payloads.LoginDTO;
import Mattiazerbini.backend.security.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UtenteService usersService;
    private final JWTTools jwtTools;
    private final PasswordEncoder bcrypt;

    @Autowired
    public AuthService(UtenteService usersService, JWTTools jwtTools, PasswordEncoder bcrypt) {

        this.usersService = usersService;
        this.jwtTools = jwtTools;
        this.bcrypt = bcrypt;
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
}
