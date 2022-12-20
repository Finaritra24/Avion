package com.example.repos;

import com.example.model.Avion;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AvionRepository extends JpaRepository<Avion, Long> {

	Avion findByIdAvion(Long id);

	void deleteByIdAvion(Long id);
	
}
