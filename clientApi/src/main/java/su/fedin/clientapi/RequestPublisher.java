package su.fedin.clientapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

@Component
public class RequestPublisher {
    @Autowired
    ServerProperties server;


    @Autowired
    RestTemplate restTemplate;

    public ResponseEntity<String> hello(String name){
        return restTemplate.getForEntity(String.format("%s/hello/%s", server.uri(), name), String.class);
    }
}
