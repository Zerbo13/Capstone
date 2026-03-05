package Mattiazerbini.backend.controller;

import Mattiazerbini.backend.entities.Utente;
import Mattiazerbini.backend.payloads.LoginDTO;
import Mattiazerbini.backend.payloads.ResponseDTO;
import Mattiazerbini.backend.payloads.UtentePayload;
import Mattiazerbini.backend.services.AuthService;
import Mattiazerbini.backend.services.UtenteService;
import Mattiazerbini.backend.Excenptions.ValidationException;
import com.cloudinary.Url;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
        return new ResponseDTO(token);
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity <?> createUser(@RequestBody @Validated UtentePayload payload, BindingResult validationResult) {

        if (validationResult.hasErrors()) {
            List<String> errorsList = validationResult.getFieldErrors()
                    .stream()
                    .map(fieldError -> fieldError.getDefaultMessage())
                    .toList();
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("message: ", errorsList));
        }
        try {
            Utente newUtente = this.utenteService.salvaUtente(payload);
            return ResponseEntity.status(HttpStatus.CREATED).body(newUtente);
        }catch(RuntimeException ex){
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(Map.of("message: ", ex.getMessage()));
        }

    }
}