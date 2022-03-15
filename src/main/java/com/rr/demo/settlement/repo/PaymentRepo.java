/**
 * 
 */
package com.rr.demo.settlement.repo;

import org.springframework.data.repository.CrudRepository;

import com.rr.demo.settlement.entity.Payment;

/**
 * @author RR
 *
 */
public interface PaymentRepo extends CrudRepository<Payment, Long> {

}
