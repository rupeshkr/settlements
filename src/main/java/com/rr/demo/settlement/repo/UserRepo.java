/**
 * 
 */
package com.rr.demo.settlement.repo;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.rr.demo.settlement.entity.User;

/**
 * @author RR
 *
 */
public interface UserRepo extends CrudRepository<User, String> {
	Optional<User> findByName(String name);
}
