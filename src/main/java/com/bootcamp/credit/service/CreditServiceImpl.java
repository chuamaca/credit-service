package com.bootcamp.credit.service;




import com.bootcamp.credit.dto.CustomerDto;
import com.bootcamp.credit.model.document.Credit;
import com.bootcamp.credit.model.repository.CreditRepository;
import com.bootcamp.credit.model.service.CreditService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import utils.WebClientUtils;

@Service
@Slf4j
@Validated
public class CreditServiceImpl implements CreditService {

    @Autowired
    private CreditRepository creditRepository;
    
   // private  WebClient webClient;
    
    //WebClient webClient = WebClient.builder().baseUrl("http://localhost:8082").build();
    
    @Override
    public Flux<Credit> getAll() {
    	
        return this.creditRepository.findAll();
    }

    @Override
    public Mono<Credit> save(Credit credit) {
        return this.creditRepository.save(credit);
    }

    /*
    @Override
    public Mono<Credit> findById(String documentNumber) {
        return this.creditRepository.findById(documentNumber);
    }
    */
    
    
    
    @Override
    public Mono<Credit> findById(String documentNumber)  {
    	
    	///Mono<CustomerDto> findCustomerByDocumentNumber
    	WebClient webClient = WebClient.builder().baseUrl("http://localhost:8202/customers/"+documentNumber).build();
    	Mono<CustomerDto> customerResponse = webClient.get()
		.accept(MediaType.APPLICATION_JSON).retrieve()
		.bodyToMono(CustomerDto.class);
    	
    	
		return this.creditRepository.findById(documentNumber);		
    
    }

    @Override
    public Mono<Boolean> existById(String documentNumber) {
        return this.creditRepository.existsById(documentNumber);
    }

    @Override
    public Mono<Void> delete(String documentNumber) {
        return this.creditRepository.findById(documentNumber)
                .flatMap(existingCredit -> creditRepository.delete(existingCredit));
    }

    @Override
    public Mono<Credit> update(String documentNumber, Credit credit) {
        return this.creditRepository.findById(documentNumber)
                .flatMap(existingCredit-> creditRepository.save(credit));
    }

	
}
