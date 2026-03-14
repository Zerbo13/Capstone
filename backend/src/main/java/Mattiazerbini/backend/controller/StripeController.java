package Mattiazerbini.backend.controller;

import Mattiazerbini.backend.services.StripeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/pagamenti")
public class StripeController {

    @Autowired
    private StripeService stripeService;

    @PostMapping("/checkout")
    public Map<String, String> creaCheckout(@RequestBody Map<String, Object> body) throws Exception {
        String nomeServizio = (String) body.get("nomeServizio");
        long prezzo = Long.parseLong(body.get("prezzo").toString());
        String url = stripeService.sessionePagamento(nomeServizio, prezzo * 100);
        return Map.of("url", url);
    }
}
