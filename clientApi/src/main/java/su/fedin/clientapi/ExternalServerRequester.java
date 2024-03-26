package su.fedin.clientapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Component
public class ExternalServerRequester {
    @Autowired
    ExternalServerProperties externalServerProperties;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    CircuitBreaker circuitBreaker;

    public ResponseEntity<String> hello(String name){
        ResponseEntity<String> response;

        if (!circuitBreaker.serverIsAvailable())
            return new ResponseEntity<String>("Server is unavailable", HttpStatus.SERVICE_UNAVAILABLE);
        try {
            response = restTemplate.getForEntity(String.format("%s/hello/%s", externalServerProperties.uri(), name), String.class);
        }
        catch (HttpClientErrorException e){
            System.out.println("Crash");
            circuitBreaker.handleException(e);
            response = new ResponseEntity<String>(e.getStatusCode());
            return response;
        }
        return response;
    }
}
