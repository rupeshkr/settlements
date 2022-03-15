/**
 * 
 */
package com.rr.demo.settlement.repo;

import org.springframework.data.repository.CrudRepository;

import com.rr.demo.settlement.entity.Order;

/**
 * @author RR
 *
 */
public interface OrderRepo extends CrudRepository<Order, Long> {

}
