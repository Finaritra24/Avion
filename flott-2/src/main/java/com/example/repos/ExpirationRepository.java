package com.example.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.Expiration;
import com.example.model.Log;

public interface ExpirationRepository extends JpaRepository<Expiration, Long> {
	/**
	 * @param ray
	 * @return
	 */
	
}
