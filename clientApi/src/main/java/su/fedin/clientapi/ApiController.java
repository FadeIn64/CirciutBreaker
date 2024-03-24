package su.fedin.clientapi;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {
    @Autowired
    RequestPublisher requestPublisher;

    @GetMapping("/hello/{name}")
    ResponseEntity<String> hello(@PathVariable String name){
        return requestPublisher.hello(name);
    }
}
