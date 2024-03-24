package su.fedin.clientapi;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpServerErrorException;

@RestController
public class ApiController {
    @Autowired
    RequestPublisher requestPublisher;

    @Autowired
    CircuitBreaker circuitBreaker;

    @GetMapping("/hello/{name}")
    ResponseEntity<String> hello(@PathVariable String name){
        if (circuitBreaker.serverIsAvailable())
            return requestPublisher.hello(name);
        return new ResponseEntity<String>("Server is Unavailable", HttpStatus.SERVICE_UNAVAILABLE);
    }

    @ExceptionHandler(HttpServerErrorException.class)
    public ResponseEntity<Object>
    circuitBreaker(HttpServerErrorException e){
        circuitBreaker.handleException(e);
        return new ResponseEntity<Object>(e.getResponseBodyAs(String.class),e.getStatusCode());
    }
}
