package microservicescourse.currencyexchangeservice;

import microservicescourse.currencyexchangeservice.CurrencyExchange.CurrencyExchange;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyExchangeRepository
    extends JpaRepository<CurrencyExchange, Long> {

  CurrencyExchange findByFromAndTo(String from, String to);
}
