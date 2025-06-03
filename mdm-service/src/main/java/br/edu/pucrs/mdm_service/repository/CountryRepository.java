package br.edu.pucrs.mdm_service.repository;

import br.edu.pucrs.mdm_service.model.CountryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<CountryEntity, String> {
}