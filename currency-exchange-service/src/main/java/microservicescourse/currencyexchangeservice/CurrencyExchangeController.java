package microservicescourse.currencyexchangeservice;

import microservicescourse.currencyexchangeservice.CurrencyExchange.CurrencyExchange;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class CurrencyExchangeController {
    private CurrencyExchangeRepository currencyExchangeRepository;
    private Environment environment;

    public CurrencyExchangeController(CurrencyExchangeRepository currencyExchangeRepository, Environment environment) {
        this.currencyExchangeRepository = currencyExchangeRepository;
        this.environment = environment;
    }

    @GetMapping("currency-exchange/from/{from}/to/{to}")
    public CurrencyExchange retrieveExchangeValue(@PathVariable String from, @PathVariable String to) {
        CurrencyExchange currencyExchange = currencyExchangeRepository.findByFromAndTo(from, to);
        if (currencyExchange == null) {
            throw new RuntimeException("Unable to Find dat from " + from + " to " + to);
        }
        String port = environment.getProperty("local.server.port");
        currencyExchange.setEnvironment(port);
        return currencyExchange;
    }
}
