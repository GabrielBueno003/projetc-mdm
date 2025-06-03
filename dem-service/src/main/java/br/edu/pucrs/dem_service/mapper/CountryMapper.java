package br.edu.pucrs.dem_service.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import br.edu.pucrs.dem_service.dto.CountryDTO;
import br.edu.pucrs.dem_service.model.CountryToSendDTO;
import br.edu.pucrs.dem_service.model.CurrencyToSendDTO;
import br.edu.pucrs.dem_service.model.GeoLocationDTO;
import br.edu.pucrs.dem_service.model.TranslationToSendDTO;

public class CountryMapper {

    public static CountryToSendDTO toSendDto(CountryDTO dto) {
        CountryToSendDTO target = new CountryToSendDTO();

        target.setIsoCode(dto.getCca3());
        if (dto.getName() != null) {
            target.setCommonName(dto.getName().getCommon());
            target.setOfficialName(dto.getName().getOfficial());
        }

        target.setRegion(dto.getRegion());
        target.setSubregion(dto.getSubregion());
        target.setArea(dto.getArea());
        target.setPopulation(dto.getPopulation());

        target.setCapitals(dto.getCapital());

        if (dto.getLatlng() != null && dto.getLatlng().size() == 2) {
            GeoLocationDTO geo = new GeoLocationDTO();
            geo.setLatitude(dto.getLatlng().get(0));
            geo.setLongitude(dto.getLatlng().get(1));
            target.setGeoLocation(geo);
        }

        if (dto.getTranslations() != null) {
            List<TranslationToSendDTO> translations = new ArrayList<>();
            for (Map.Entry<String, CountryDTO.Translation> entry : dto.getTranslations().entrySet()) {
                TranslationToSendDTO t = new TranslationToSendDTO();
                t.setLanguageCode(entry.getKey());
                t.setCommonName(entry.getValue().getCommon());
                t.setOfficialName(entry.getValue().getOfficial());
                translations.add(t);
            }
            target.setTranslations(translations);
        }

        if (dto.getCurrencies() != null) {
            List<CurrencyToSendDTO> currencies = new ArrayList<>();
            for (Map.Entry<String, CountryDTO.Currency> entry : dto.getCurrencies().entrySet()) {
                CurrencyToSendDTO c = new CurrencyToSendDTO();
                c.setCode(entry.getKey());
                c.setName(entry.getValue().getName());
                c.setSymbol(entry.getValue().getSymbol());
                currencies.add(c);
            }
            target.setCurrencies(currencies);
        }

        return target;
    }
}
