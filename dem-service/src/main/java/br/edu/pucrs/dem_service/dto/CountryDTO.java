package br.edu.pucrs.dem_service.dto;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class CountryDTO {
    private Name name;
    private String cca3;
    private String region;
    private String subregion;
    private double area;
    private long population;
    private List<String> capital;
    private List<Double> latlng;
    private Map<String, Translation> translations;
    private Map<String, Currency> currencies;

    @Data
    public static class Name {
        private String common;
        private String official;
    }

    @Data
    public static class Translation {
        private String common;
        private String official;
    }

    @Data
    public static class Currency {
        private String name;
        private String symbol;
    }
}
