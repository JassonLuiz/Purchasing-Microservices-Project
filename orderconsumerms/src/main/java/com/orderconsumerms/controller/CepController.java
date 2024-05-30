package com.orderconsumerms.controller;

import com.orderconsumerms.model.Address;
import com.orderconsumerms.service.CepService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class CepController {

    private CepService cepService;

    public CepController(CepService cepService) {
        this.cepService = cepService;
    }

    @GetMapping("/cep/{cep}")
    public Mono<Address> searchByCep(@PathVariable("cep") String cep){
        return cepService.searchByCep(cep);
    }
}
