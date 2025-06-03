package br.edu.pucrs.dem_service.model;

import lombok.Data;

import java.util.List;

@Data
public class CountryToSendDTO {
    private String isoCode;
    private String commonName;
    private String officialName;
    private String region;
    private String subregion;
    private double area;
    private long population;

    private List<String> capitals;

    private List<CurrencyToSendDTO> currencies;

    private GeoLocationDTO geoLocation;

    private List<TranslationToSendDTO> translations;
}
