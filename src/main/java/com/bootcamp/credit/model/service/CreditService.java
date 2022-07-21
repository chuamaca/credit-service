package com.bootcamp.credit.model.service;

import com.bootcamp.credit.model.document.Credit;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CreditService  {

    Flux<Credit> getAll();
    Mono<Credit> save(Credit credit);

    Mono<Credit> findById(String documentNumber);
    Mono<Boolean> existById(String documentNumber);

    Mono<Void> delete(String documentNumber);

    Mono<Credit> update(String documentNumber, Credit credit);
    
}
