package br.edu.pucrs.dem_service.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.pucrs.dem_service.service.EtlService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/etl")
public class EtlController {

    private final EtlService service;

    @GetMapping("/{domain}")
    public ResponseEntity<String> run(@PathVariable String domain) {
        String msg = service.run(domain);
        return ResponseEntity.ok(msg);
    }
}
