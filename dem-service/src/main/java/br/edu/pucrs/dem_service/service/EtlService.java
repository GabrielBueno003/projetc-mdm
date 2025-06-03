package br.edu.pucrs.dem_service.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import br.edu.pucrs.dem_service.etl.EtlProcessor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class EtlService {

    private final Map<String, EtlProcessor<?>> processors;

    public String run(String domain) {
        EtlProcessor<?> processor = processors.get(domain);
        if (processor == null) {
            throw new IllegalArgumentException("Domínio não suportado: " + domain);
        }

        log.info("Iniciando ETL para domínio: '{}'", domain);
        runGeneric(processor);
        return "ETL para '" + domain + "' executado com sucesso!";
    }

    @SuppressWarnings("unchecked")
    private <T> void runGeneric(EtlProcessor<T> processor) {
        List<T> rawList = processor.extract();
        for (T raw : rawList) {
            try {
                Object transformed = processor.transform(raw);
                processor.load(transformed);
            } catch (Exception e) {
                log.error("Erro ao processar item: ", e.getMessage(), e);
            }
        }
    }
}
