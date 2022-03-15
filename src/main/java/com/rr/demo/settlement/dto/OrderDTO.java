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
public class OrderDTO {

	private long orderId;
	private String customerId;
	private BigDecimal amount;
	private BigDecimal balance;
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
	 * @return the customerId
	 */
	public String getCustomerId() {
		return customerId;
	}

	/**
	 * @param customerId the customerId to set
	 */
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
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
	 * @return the balance
	 */
	public BigDecimal getBalance() {
		return balance;
	}

	/**
	 * @param balance the balance to set
	 */
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
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

		if (!(obj instanceof OrderDTO)) {
			return false;
		}

		OrderDTO that = (OrderDTO) obj;
		return new EqualsBuilder().append(this.orderId, that.orderId).append(this.customerId, that.customerId)
				.append(this.amount, that.amount).append(this.balance, that.balance)
				.append(this.currencyCode, that.currencyCode).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(orderId).append(customerId).append(amount).append(balance)
				.append(currencyCode).build();
	}
}
