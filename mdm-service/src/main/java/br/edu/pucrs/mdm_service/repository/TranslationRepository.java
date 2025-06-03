package br.edu.pucrs.mdm_service.repository;

import br.edu.pucrs.mdm_service.model.TranslationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TranslationRepository extends JpaRepository<TranslationEntity, String> {
}
