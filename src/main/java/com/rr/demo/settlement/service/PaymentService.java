/**
 * 
 */
package com.rr.demo.settlement.service;

import static com.rr.demo.settlement.utils.GenericUtilities.findCurrencyFor;

import org.springframework.stereotype.Service;

import com.rr.demo.settlement.dto.PaymentDTO;
import com.rr.demo.settlement.entity.Payment;
import com.rr.demo.settlement.repo.PaymentRepo;

/**
 * @author RR
 *
 */
@Service
public class PaymentService {

	private final OrderService orderService;
	private final PaymentRepo repo;

	PaymentService(PaymentRepo repo, OrderService orderService) {
		this.repo = repo;
		this.orderService = orderService;
	}

	public PaymentDTO create(PaymentDTO newpayment) {
		Payment payment = convert(newpayment);
		payment = repo.save(payment);
		orderService.updateBalance(payment);

		return convert(payment);
	}

	private Payment convert(PaymentDTO newPayment) {
		Payment payment = new Payment();
		payment.setBaseOrder(orderService.findById(newPayment.getOrderId()));
		payment.setAmount(newPayment.getAmount());
		payment.setCurrency(findCurrencyFor(newPayment.getCurrencyCode()));
		return payment;
	}

	private PaymentDTO convert(Payment payment) {
		PaymentDTO result = new PaymentDTO();
		result.setOrderId(payment.getBaseOrder().getId());
		result.setAmount(payment.getAmount());
		result.setCurrencyCode(payment.getCurrency().getCurrencyCode());
		return result;
	}

}
