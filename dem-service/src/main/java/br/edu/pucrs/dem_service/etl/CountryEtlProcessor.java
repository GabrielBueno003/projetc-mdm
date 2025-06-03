package br.edu.pucrs.dem_service.etl;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import br.edu.pucrs.dem_service.dto.CountryDTO;
import br.edu.pucrs.dem_service.mapper.CountryMapper;
import br.edu.pucrs.dem_service.model.CountryToSendDTO;
import lombok.extern.slf4j.Slf4j;

@Slf4j // Lombok notation to use log
@Component("country")
public class CountryEtlProcessor implements EtlProcessor<CountryDTO> {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String API_URL = "https://restcountries.com/v3.1/all";
    private final String MDM_COUNTRIES_API = "http://localhost:8080/countries";

    @Override
    public List<CountryDTO> extract() {
        log.info("Extraindo países da API REST Countries...");
        CountryDTO[] countries = restTemplate.getForObject(API_URL, CountryDTO[].class);
        return Arrays.asList(countries);
    }

    @Override
    public Object transform(CountryDTO dto) {
        log.debug("Transformando país: ", dto.getCca3());
        return CountryMapper.toSendDto(dto);
    }

    @Override
    public void load(Object transformed) {
        CountryToSendDTO dto = (CountryToSendDTO) transformed;
        String url = MDM_COUNTRIES_API + "/" + dto.getIsoCode();

        try {
            // Verify Country arrady exist
            restTemplate.getForEntity(url, Void.class);

            log.info("Atualizando país existente: ", dto.getIsoCode());
            restTemplate.put(url, dto);

        } catch (HttpClientErrorException.NotFound e) {
            log.info("Criando novo país: ", dto.getIsoCode());
            restTemplate.postForEntity(MDM_COUNTRIES_API, dto, Void.class);

        } catch (Exception ex) {
            log.error("Erro ao carregar país: ", dto.getIsoCode(), ex.getMessage());
        }

    }
}
