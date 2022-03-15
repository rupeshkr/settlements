/**
 * 
 */
package com.rr.demo.settlement.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rr.demo.settlement.dto.OrderDTO;
import com.rr.demo.settlement.dto.PaymentDTO;
import com.rr.demo.settlement.dto.UserDTO;
import com.rr.demo.settlement.service.OrderService;
import com.rr.demo.settlement.service.PaymentService;
import com.rr.demo.settlement.service.UserService;

/**
 * @author RR
 *
 */
@RestController
public class OrdersAndPaymentsController {

	private final UserService userService;
	private final OrderService orderService;
	private final PaymentService paymentService;

	OrdersAndPaymentsController(UserService userService, OrderService orderService, PaymentService paymentService) {
		this.userService = userService;
		this.orderService = orderService;
		this.paymentService = paymentService;
	}

	@GetMapping("/users")
	public List<UserDTO> all() {
		return userService.all();
	}

	@PostMapping("/users")
	public UserDTO newEmployee(@RequestBody String newUser) {
		return userService.create(newUser);
	}

	@PostMapping("/orders")
	public OrderDTO newOrder(@RequestBody OrderDTO newOrder) {
		return orderService.create(newOrder);
	}

	@PostMapping("/payments")
	public PaymentDTO newPayment(@RequestBody PaymentDTO newpayment) {
		return paymentService.create(newpayment);
	}

	@GetMapping("/orderBalance/{orderId}")
	public BigDecimal orderBalance(@PathVariable("orderId") long orderId) {
		return orderService.findById(orderId).getBalance();
	}
	
	@GetMapping("/userLedgerBalance/{customerId}")
	public BigDecimal userLedgerBalance(@PathVariable("customerId") String customerId) {
		return userService.findById(customerId).getLedgerBalance();
	}
}
