package utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.bootcamp.credit.dto.CustomerDto;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_JSON;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class WebClientUtils {
	
	//private WebClient typeCustomerServiceClient = WebClient.builder().baseUrl("http://localhost:8082").build();
	 private final WebClient webClient;
	 
	 public WebClientUtils(WebClient.Builder builder) {
	        webClient = builder.baseUrl("http://localhost:8082").build();
	 }
	 
	  public Mono<CustomerDto> findCustomerByDocumentNumber(String documentNumber) {
	        log.info("Buscando personagem com o id [{}]", documentNumber);
	        return webClient
	                .get()
	                .uri("/customers/" + documentNumber)
	                .accept(APPLICATION_JSON)
	                .retrieve()
	                .onStatus(HttpStatus::is4xxClientError,
	                        error -> Mono.error(new RuntimeException("verifique os parâmetros informados")))
	                .bodyToMono(CustomerDto.class);
	  }
	
}
