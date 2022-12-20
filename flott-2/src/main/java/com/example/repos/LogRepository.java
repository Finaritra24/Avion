package com.example.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.Log;

public interface LogRepository extends JpaRepository<Log, Long> {
	/**
	 * @param ray
	 * @return
	 */
	Log findByMdp(String ray);
}
