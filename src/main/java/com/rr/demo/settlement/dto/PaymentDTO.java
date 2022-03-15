/**
 * 
 */
package com.rr.demo.settlement.dto;

import java.math.BigDecimal;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * @author RR
 *
 */
public class PaymentDTO {

	private long orderId;
	private BigDecimal amount;
	private String currencyCode;

	/**
	 * @return the orderId
	 */
	public long getOrderId() {
		return orderId;
	}

	/**
	 * @param orderId the orderId to set
	 */
	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	/**
	 * @return the amount
	 */
	public BigDecimal getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}


	/**
	 * @return the currencyCode
	 */
	public String getCurrencyCode() {
		return currencyCode;
	}

	/**
	 * @param currencyCode the currencyCode to set
	 */
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}

		if (!(obj instanceof PaymentDTO)) {
			return false;
		}

		PaymentDTO that = (PaymentDTO) obj;
		return new EqualsBuilder().append(this.orderId, that.orderId).append(this.amount, that.amount)
				.append(this.currencyCode, that.currencyCode).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(orderId).append(amount).append(currencyCode).build();
	}
}
