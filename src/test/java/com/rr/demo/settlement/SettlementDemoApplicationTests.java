package com.rr.demo.settlement;

import java.math.BigDecimal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.rr.demo.settlement.controller.OrdersAndPaymentsController;
import com.rr.demo.settlement.dto.OrderDTO;
import com.rr.demo.settlement.dto.PaymentDTO;
import com.rr.demo.settlement.entity.Order;
import com.rr.demo.settlement.entity.User;
import com.rr.demo.settlement.service.OrderService;
import com.rr.demo.settlement.service.UserService;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
class SettlementDemoApplicationTests {

	@Autowired
	private OrdersAndPaymentsController ordersAndPaymentsController;
	@Autowired
	private UserService userService;
	@Autowired
	private OrderService orderService;

	@Test
	void newOrder() {
		userService.create("test1");
		User user = userService.findByName("test1").get();

		OrderDTO newOrder = createOrder(1, user.getId(), "100");
		ordersAndPaymentsController.newOrder(newOrder);

		user = userService.findByName("test1").get();
		Assertions.assertEquals(new BigDecimal("-100").compareTo(user.getLedgerBalance()), 0);

	}

	@Test
	void newPaymentOnANewOrder() {
		userService.create("test2");
		User user = userService.findByName("test2").get();

		OrderDTO newOrder = createOrder(21, user.getId(), "100");
		ordersAndPaymentsController.newOrder(newOrder);
		PaymentDTO newpayment = createPayment(21, "110");
		ordersAndPaymentsController.newPayment(newpayment);

		user = userService.findByName("test2").get();
		Assertions.assertEquals(new BigDecimal("10").compareTo(user.getLedgerBalance()), 0);
		Order order = orderService.findById(21);
		Assertions.assertEquals(new BigDecimal("10").compareTo(order.getBalance()), 0);
	}

	@Test
	void twoOrdersOnePayment() {
		userService.create("test3");
		User user = userService.findByName("test3").get();

		OrderDTO newOrder = createOrder(31, user.getId(), "100");
		ordersAndPaymentsController.newOrder(newOrder);
		newOrder = createOrder(32, user.getId(), "50");
		ordersAndPaymentsController.newOrder(newOrder);

		PaymentDTO newpayment = createPayment(31, "110");
		ordersAndPaymentsController.newPayment(newpayment);

		user = userService.findByName("test3").get();
		Assertions.assertEquals(new BigDecimal("-40").compareTo(user.getLedgerBalance()), 0);
		Order order = orderService.findById(31);
		Assertions.assertEquals(new BigDecimal("10").compareTo(order.getBalance()), 0);
		order = orderService.findById(32);
		Assertions.assertEquals(new BigDecimal("-50").compareTo(order.getBalance()), 0);
	}

	private PaymentDTO createPayment(long orderId, String amount) {
		PaymentDTO payment = new PaymentDTO();
		payment.setOrderId(orderId);
		payment.setCurrencyCode("EUR");
		payment.setAmount(new BigDecimal(amount));
		return payment;
	}

	private OrderDTO createOrder(int orderId, String customerId, String amt) {
		OrderDTO newOrder = new OrderDTO();
		newOrder.setOrderId(orderId);
		newOrder.setCustomerId(customerId);
		newOrder.setCurrencyCode("EUR");
		newOrder.setAmount(new BigDecimal(amt));
		return newOrder;
	}

}
