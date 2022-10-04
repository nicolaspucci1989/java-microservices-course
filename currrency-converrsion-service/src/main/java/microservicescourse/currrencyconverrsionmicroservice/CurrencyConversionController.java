package microservicescourse.currrencyconverrsionmicroservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class CurrencyConversionController {
  private final CurrencyExchangeProxy currencyExchangeProxy;

  public CurrencyConversionController(CurrencyExchangeProxy currencyExchangeProxy) {
    this.currencyExchangeProxy = currencyExchangeProxy;
  }

  @GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
  public CurrencyConversion calculateCurrencyConversion(
      @PathVariable String from,
      @PathVariable String to,
      @PathVariable BigDecimal quantity
  ) {
    CurrencyConversion currencyConversion = currencyExchangeProxy.retrieveExchangeValue(from, to);

    return new CurrencyConversion(
        currencyConversion.getId(),
        from, to, quantity,
        currencyConversion.getConversionMultiple(),
        quantity.multiply(currencyConversion.getConversionMultiple()),
        currencyConversion.getEnvironment()
    );
  }
}
