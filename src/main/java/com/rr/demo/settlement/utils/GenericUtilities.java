/**
 * 
 */
package com.rr.demo.settlement.utils;

import java.util.Currency;

/**
 * @author RR
 *
 */
public class GenericUtilities {

	private GenericUtilities() {
		// util class, do not instantiate
	}

	public static Currency findCurrencyFor(String currencyCode) {
		return Currency.getInstance(currencyCode);
	}
}
