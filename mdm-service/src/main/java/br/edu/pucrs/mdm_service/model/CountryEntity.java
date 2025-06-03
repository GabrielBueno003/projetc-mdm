package br.edu.pucrs.mdm_service.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CountryEntity {

    @Id
    @EqualsAndHashCode.Include
    private String isoCode;

    private String commonName;
    private String officialName;
    private String region;
    private String subregion;
    private double area;
    private long population;

    @OneToMany(mappedBy = "country", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TranslationEntity> translations;

    @Embedded
    private GeoLocation geoLocation;

    @ElementCollection
    @CollectionTable(name = "country_capitals", joinColumns = @JoinColumn(name = "country_id"))
    @Column(name = "capital")
    private List<String> capitals;

    @ElementCollection
    @CollectionTable(name = "country_currencies", joinColumns = @JoinColumn(name = "country_id"))
    private List<Currency> currencies;
}

@Embeddable
@Data
class GeoLocation {
    private Double latitude;
    private Double longitude;
}

@Embeddable
@Data
class Currency {
    private String code;
    private String name;
    private String symbol;
}