package Mattiazerbini.backend.security;

import Mattiazerbini.backend.Excenptions.UnauthorizedException;
import Mattiazerbini.backend.entities.Ruolo;
import Mattiazerbini.backend.entities.Utente;
import Mattiazerbini.backend.services.UtenteService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JWTCheckedFilter extends OncePerRequestFilter {
    private final JWTTools jwtTools;
    private final UtenteService utenteService;

    public JWTCheckedFilter(JWTTools jwtTools, UtenteService utenteService) {
        this.jwtTools = jwtTools;
        this.utenteService = utenteService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        ------------------------ AUTENTICAZIONE ------------------------

        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer "))
            throw new UnauthorizedException("Inserire il token nell'Authorization header nel formato corretto");
        String accessToken = authHeader.replace("Bearer ", "");
        jwtTools.verifyToken(accessToken);

//        ------------------------ AUTORIZZAZIONE ------------------------

        long userId = jwtTools.extractIdFromToken(accessToken);
        Utente utenteLoggato = this.utenteService.findById(userId);
        Authentication authentication = new UsernamePasswordAuthenticationToken(utenteLoggato, null, utenteLoggato.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        filterChain.doFilter(request, response);


    }
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return new AntPathMatcher().match("/auth/**", request.getServletPath());
    }


}
