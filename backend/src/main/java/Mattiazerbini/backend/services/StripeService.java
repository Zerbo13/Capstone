package Mattiazerbini.backend.services;

import com.stripe.Stripe;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
@Service
@Slf4j
public class StripeService {

    @Value("${stripe.secret.key}")
    private String secretKey;

    public String sessionePagamento(String nomeServizio, long prezzoInCentesimi) throws Exception{
        Stripe.apiKey = secretKey;

        SessionCreateParams parametri = SessionCreateParams.builder()
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setSuccessUrl("http://localhost:5173/pagamentoRiuscito")
                .setCancelUrl("http://localhost:5173/pagamentoAnnullato")
                .addLineItem(SessionCreateParams.LineItem.builder()
                        .setQuantity(1L)
                        .setPriceData(SessionCreateParams.LineItem.PriceData.builder()
                                .setCurrency("eur")
                                .setUnitAmount(prezzoInCentesimi)
                                .setProductData(SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                        .setName(nomeServizio)
                                        .build()
                                )
                                .build()
                        )
                        .build()
                )
                .build();
                Session session = Session.create(parametri);
        return session.getUrl();
    }
}
