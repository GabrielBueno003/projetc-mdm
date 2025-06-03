package br.edu.pucrs.dem_service.etl;

import java.util.List;

public interface EtlProcessor<T> {
    List<T> extract();

    Object transform(T raw);

    void load(Object transformed);
}
