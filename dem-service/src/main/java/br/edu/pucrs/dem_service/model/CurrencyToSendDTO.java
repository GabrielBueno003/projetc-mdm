package br.edu.pucrs.dem_service.model;

import lombok.Data;

@Data
public class CurrencyToSendDTO {
    private String code;
    private String name;
    private String symbol;
}
