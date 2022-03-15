/**
 * 
 */
package com.rr.demo.settlement.service;

import static com.rr.demo.settlement.utils.GenericUtilities.findCurrencyFor;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.rr.demo.settlement.dto.OrderDTO;
import com.rr.demo.settlement.entity.Order;
import com.rr.demo.settlement.entity.Payment;
import com.rr.demo.settlement.repo.OrderRepo;

/**
 * @author RR
 *
 */
@Service
public class OrderService {

	private static final Logger log = LoggerFactory.getLogger(OrderService.class);

	private final OrderRepo repo;
	private final UserService userService;
	private final CurrencyService currencyService;

	OrderService(OrderRepo repo, UserService userService, CurrencyService currencyService) {
		this.repo = repo;
		this.userService = userService;
		this.currencyService = currencyService;
	}

	public OrderDTO create(OrderDTO newOrder) {
		if (newOrder.getBalance() != null) {
			log.warn("Ignoring 'balance' value as its internally calculated value.");
		}

		if (repo.findById(newOrder.getOrderId()).isPresent()) {
			throw new IllegalArgumentException("Order already present!");
		}

		Order order = convert(newOrder);
		order.setBalance(order.getAmount().negate()); // negative amount as balance for new orders

		order = repo.save(order);
		userService.updateBalance(order);

		return convert(order);
	}

	public Order findById(long orderId) {
		return repo.findById(orderId).orElseThrow(() -> new IllegalArgumentException("Unknown order id"));
	}

	private OrderDTO convert(Order order) {
		OrderDTO result = new OrderDTO();
		result.setOrderId(order.getId());
		result.setCustomerId(order.getCustomer().getId());
		result.setAmount(order.getAmount());
		result.setCurrencyCode(order.getCurrency().getCurrencyCode());
		return result;
	}

	private Order convert(OrderDTO newOrder) {
		Order order = new Order();
		order.setId(newOrder.getOrderId());
		order.setCustomer(userService.findById(newOrder.getCustomerId()));
		order.setAmount(newOrder.getAmount());
		order.setCurrency(findCurrencyFor(newOrder.getCurrencyCode()));
		return order;
	}

	public void updateBalance(Payment payment) {
		Order baseOrder = payment.getBaseOrder();
		BigDecimal paidAmount = currencyService.convertedAmount(payment.getAmount(), payment.getCurrency(), baseOrder.getCurrency());
		baseOrder.setBalance(baseOrder.getBalance().add(paidAmount));
		if (BigDecimal.ZERO.compareTo(baseOrder.getBalance()) > 0) {
			// order amount fully paid
			baseOrder.setFullyPaid(true);
		} else {
			// order amount NOT fully paid
			baseOrder.setFullyPaid(false);
		}
		repo.save(baseOrder);
		userService.updateBalance(payment); // also update user ledger balance
	}

}
