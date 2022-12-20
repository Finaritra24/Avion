package com.example.repos;

import java.util.List;

import com.example.model.Avion;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.Kilometrage;

public interface KilometrageRepository extends JpaRepository<Kilometrage, Long> {

	List<Kilometrage> findByAvion(Avion vec);




	void deleteByAvion(Avion vec);

}
