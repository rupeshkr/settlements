/**
 * 
 */
package com.rr.demo.settlement.service;

import static com.rr.demo.settlement.utils.GenericUtilities.findCurrencyFor;

import java.math.BigDecimal;
import java.util.Currency;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.rr.demo.settlement.entity.Order;
import com.rr.demo.settlement.entity.Payment;

/**
 * @author RR
 *
 */
@Service
public class CurrencyService {

	private final Currency defaultCurrency;

	CurrencyService(@Value("${defaultCurrency}") String defaultCurrency) {
		this.defaultCurrency = findCurrencyFor(defaultCurrency);
	}

	public BigDecimal convertedAmount(Payment payment) {
		return convertedAmount(payment.getAmount(), payment.getCurrency());
	}

	public BigDecimal convertedAmount(Order order) {
		return convertedAmount(order.getAmount(), order.getCurrency());
	}

	private BigDecimal convertedAmount(BigDecimal amount, Currency currency) {
		return convertedAmount(amount, currency, defaultCurrency);
	}

	public BigDecimal convertedAmount(BigDecimal amount, Currency fromCurrency, Currency toCurrency) {
		BigDecimal result = amount;
		if (!fromCurrency.equals(toCurrency)) {
			// do some currency conversion
			// maybe even trigger another flow for adding currency conversion charges, if required
		}
		return result;
	}
}
