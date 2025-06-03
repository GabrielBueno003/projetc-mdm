package br.edu.pucrs.mdm_service.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class TranslationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String languageCode; // ex: "por", "fra"
    private String officialName;
    private String commonName;

    @ManyToOne
    @JoinColumn(name = "country_iso_code")
    private CountryEntity country;
}