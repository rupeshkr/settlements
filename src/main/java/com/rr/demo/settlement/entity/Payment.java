/**
 * 
 */
package com.rr.demo.settlement.entity;

import java.math.BigDecimal;
import java.util.Currency;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * @author RR
 *
 */
@Entity
@Table(name = "t_payments")
public class Payment {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "order_id", nullable = false)
	private Order baseOrder;

	private BigDecimal amount;
	private Currency currency;

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the baseOrder
	 */
	public Order getBaseOrder() {
		return baseOrder;
	}

	/**
	 * @param baseOrder the baseOrder to set
	 */
	public void setBaseOrder(Order baseOrder) {
		this.baseOrder = baseOrder;
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
	 * @return the currency
	 */
	public Currency getCurrency() {
		return currency;
	}

	/**
	 * @param currency the currency to set
	 */
	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}

		if (!(obj instanceof Payment)) {
			return false;
		}

		Payment that = (Payment) obj;
		return new EqualsBuilder().append(this.id, that.id).append(this.baseOrder, that.baseOrder)
				.append(this.amount, that.amount).append(this.currency, that.currency).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(id).append(baseOrder).append(amount).append(currency).build();
	}
}
