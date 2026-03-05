package Mattiazerbini.backend.controller;

import Mattiazerbini.backend.entities.Utente;
import Mattiazerbini.backend.payloads.LoginDTO;
import Mattiazerbini.backend.payloads.ResponseDTO;
import Mattiazerbini.backend.payloads.UtentePayload;
import Mattiazerbini.backend.services.AuthService;
import Mattiazerbini.backend.services.UtenteService;
import Mattiazerbini.backend.Excenptions.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;
    private final UtenteService utenteService;

    public AuthController(AuthService authService, UtenteService utenteService) {
        this.authService = authService;
        this.utenteService = utenteService;

    }

    @PostMapping("/login")
    public ResponseDTO login(@RequestBody LoginDTO body) {
        String token = this.authService.checkCredentialsAndGenerateToken(body);
        Utente utente = this.authService.getUserByEmail(body.email());
        return new ResponseDTO(token, utente.getRuolo().name());
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public Utente createUser(@RequestBody @Validated UtentePayload payload, BindingResult validationResult) {

        if (validationResult.hasErrors()) {
            List<String> errorsList = validationResult.getFieldErrors()
                    .stream()
                    .map(fieldError -> fieldError.getDefaultMessage())
                    .toList();

            throw new ValidationException(errorsList);
        } else {
            return this.utenteService.salvaUtente(payload);
        }

    }
}