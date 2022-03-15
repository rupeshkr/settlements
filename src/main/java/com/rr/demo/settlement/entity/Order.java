/**
 * 
 */
package com.rr.demo.settlement.entity;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * @author RR
 *
 */
@Entity
@Table(name = "t_orders")
public class Order {

	@Id
	private long id;

	@ManyToOne
	private User customer;

	private BigDecimal amount;
	private BigDecimal balance;
	private Currency currency;

	private boolean fullyPaid;

	@OneToMany(mappedBy = "baseOrder", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Payment> payments;

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
	 * @return the customer
	 */
	public User getCustomer() {
		return customer;
	}

	/**
	 * @param customer the customer to set
	 */
	public void setCustomer(User customer) {
		this.customer = customer;
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

	/**
	 * @return the fullyPaid
	 */
	public boolean isFullyPaid() {
		return fullyPaid;
	}

	/**
	 * @param fullyPaid the fullyPaid to set
	 */
	public void setFullyPaid(boolean fullyPaid) {
		this.fullyPaid = fullyPaid;
	}

	/**
	 * @return the payments
	 */
	public Set<Payment> getPayments() {
		return payments;
	}

	/**
	 * @param payments the payments to set
	 */
	public void setPayments(Set<Payment> payments) {
		this.payments = payments;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}

		if (!(obj instanceof Order)) {
			return false;
		}

		Order that = (Order) obj;
		return new EqualsBuilder().append(this.id, that.id).append(this.customer, that.customer)
				.append(this.amount, that.amount).append(this.balance, that.balance)
				.append(this.currency, that.currency).append(this.fullyPaid, that.fullyPaid).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(id).append(customer).append(amount).append(balance).append(currency)
				.append(fullyPaid).build();
	}
}
