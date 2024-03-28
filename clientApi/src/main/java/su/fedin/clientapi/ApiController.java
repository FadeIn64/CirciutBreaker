package su.fedin.clientapi;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {
    @Autowired
    ExternalServerRequester requestPublisher;

    @Autowired
    CircuitBreaker circuitBreaker;

    @GetMapping("/hello/{name}")
    ResponseEntity<String> hello(@PathVariable String name){
        if (circuitBreaker.serverIsAvailable())
            return requestPublisher.hello(name);
        return new ResponseEntity<String>("Server is Unavailable", HttpStatus.SERVICE_UNAVAILABLE);
    }
}
