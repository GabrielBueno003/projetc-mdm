package br.edu.pucrs.mdm_service.controller;

import br.edu.pucrs.mdm_service.model.CountryEntity;
import br.edu.pucrs.mdm_service.repository.CountryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/countries")
@RequiredArgsConstructor
public class CountryController {

    private final CountryRepository repository;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody CountryEntity entity) {
        if (repository.existsById(entity.getIsoCode())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        repository.save(entity);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable String id, @RequestBody CountryEntity entity) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        entity.setIsoCode(id);
        repository.save(entity);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public List<CountryEntity> list() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CountryEntity> findById(@PathVariable String id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/AUTH-GSB")
    public ResponseEntity<Void> deleteAll() {
        long count = repository.count();
        if (count > 0) {
            repository.deleteAll();
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.OK).build();
        }
    }
}
