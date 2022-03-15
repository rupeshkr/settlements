/**
 * 
 */
package com.rr.demo.settlement.service;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.rr.demo.settlement.dto.UserDTO;
import com.rr.demo.settlement.entity.Order;
import com.rr.demo.settlement.entity.Payment;
import com.rr.demo.settlement.entity.User;
import com.rr.demo.settlement.repo.UserRepo;

/**
 * @author RR
 *
 */
@Service
public class UserService {

	private final UserRepo repo;
	private final CurrencyService currencyService;

	UserService(UserRepo repo, CurrencyService currencyService) {
		this.repo = repo;
		this.currencyService = currencyService;
	}

	public void updateBalance(@NonNull Order order) {
		BigDecimal orderAmount = currencyService.convertedAmount(order);
		User customer = order.getCustomer();
		updateBalance(customer, orderAmount.negate());
	}

	public void updateBalance(Payment payment) {
		BigDecimal paymentAmount = currencyService.convertedAmount(payment);
		User customer = payment.getBaseOrder().getCustomer();
		updateBalance(customer, paymentAmount);
	}
	
	private void updateBalance(User customer, BigDecimal amount) {
		BigDecimal existingBalance = customer.getLedgerBalance();
		BigDecimal updatedBalance = amount;
		if(existingBalance != null) {
			updatedBalance = amount.add(existingBalance);
		}
		customer.setLedgerBalance(updatedBalance);
		repo.save(customer); // update ledger balance in DB
	}

	public UserDTO create(String newUser) {
		User user = convert(newUser);
		user = repo.save(user);
		return convert(user);
	}

	public List<UserDTO> all() {
		Iterable<User> allUsers = repo.findAll();
		List<UserDTO> result = new LinkedList<>();

		for (User user : allUsers) {
			result.add(convert(user));
		}
		return result;
	}

	public User findById(String customerId) {
		return repo.findById(customerId).orElseThrow(() -> new IllegalArgumentException("Unknown user"));
	}
	
	public Optional<User> findByName(String userName) {
		return repo.findByName(userName);
	}

	private UserDTO convert(User user) {
		UserDTO dto = new UserDTO();
		dto.setId(user.getId());
		dto.setName(user.getName());
		return dto;
	}

	private User convert(String newUser) {
		User user = new User();
		user.setName(newUser);
		return user;
	}

}
