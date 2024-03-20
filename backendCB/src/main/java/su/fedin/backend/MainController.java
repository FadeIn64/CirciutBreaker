package su.fedin.backend;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
    @GetMapping("/status")
    ResponseEntity<String> status(){
        String status = System.getenv("HTTP_STATUS");
        System.out.println(status);
        return new ResponseEntity<String>(status, HttpStatus.OK);
    }
}
