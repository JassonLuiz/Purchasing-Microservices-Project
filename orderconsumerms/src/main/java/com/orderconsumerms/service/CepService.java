package com.orderconsumerms.service;

import com.orderconsumerms.model.Address;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class CepService {

    private Logger log = LoggerFactory.getLogger(CepService.class);

    private final WebClient webClient;

    public CepService(WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<Address> searchByCep(String cep){
        if (cep.isBlank()) {
            return Mono.error(new IllegalArgumentException("CEP cannot be null or empty"));
        }

        long startTime = System.currentTimeMillis();
        log.info("Starting search by zip code: {}", cep);

        Mono<Address> addressMono = webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/{cep}/json").build(cep))
                .retrieve()
                .bodyToMono(Address.class)
                .doOnNext(address -> log.info("Address found: {}", address))
                .doOnError(error -> log.error("Error when searching for address for zip code: {}", cep, error))
                .doOnTerminate(() -> {
                    long endTime = System.currentTimeMillis();
                    log.info("Search by zip code {} completed in {} ms", cep, (endTime - startTime));
                });

        return addressMono;
    }
}
