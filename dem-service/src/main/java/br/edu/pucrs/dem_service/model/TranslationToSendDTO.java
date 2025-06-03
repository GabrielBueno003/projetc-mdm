package br.edu.pucrs.dem_service.model;

import lombok.Data;

@Data
public class TranslationToSendDTO {
    private String languageCode;
    private String officialName;
    private String commonName;
}
