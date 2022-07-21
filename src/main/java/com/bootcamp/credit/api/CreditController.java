package com.bootcamp.credit.api;

import com.bootcamp.credit.model.document.Credit;
import com.bootcamp.credit.model.service.CreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/credits")
public class CreditController {

    @Autowired
    private CreditService creditService;

    @PostMapping
    public Mono<Credit> register(@RequestBody Credit credit){
        return creditService.save(credit);
    }

    @GetMapping
    public Flux<Credit> getAllCredits(){
        return creditService.getAll();
    }

    @GetMapping("{documentNumber}")
    public Mono<Credit> getCreditById(@PathVariable String documentNumber){
        return creditService.findById(documentNumber);
    }

    @DeleteMapping("{documentNumber}")
    public Mono<Void> deleteCustomer(@PathVariable String documentNumber){
        return creditService.delete(documentNumber);
    }

    @PutMapping("{documentNumber}")
    public Mono<Credit> updateCredit(@PathVariable String documentNumber, @RequestBody Credit credit){
        return  creditService.update(documentNumber,credit);
    }

}
