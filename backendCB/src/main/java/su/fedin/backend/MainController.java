package su.fedin.backend;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class MainController {
    int status = 200;
    @GetMapping("/status")
    ResponseEntity<String> status(){
        String response = "Status code: " + status;
        return new ResponseEntity<String>(response, HttpStatusCode.valueOf(status));
    }

    @GetMapping("/hello/{name}")
    ResponseEntity<Object> hello(@PathVariable String name){
        if (status <= 400)
            return new ResponseEntity<Object>(new HelloDTO(name), HttpStatusCode.valueOf(status));

        return new ResponseEntity<Object>("Status code: " + status, HttpStatus.valueOf(status));

    }

    @PostMapping("/changestatus/{status}")
    ResponseEntity changeStatus(@PathVariable int status){
        if (status < 100 || status >= 600)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        this.status = status;
        return new ResponseEntity<String>("Status changed on: " + status,HttpStatus.OK);
    }

    @Data
    static class HelloDTO{
        String header = "Hello world!";
        String body = "My name is ";

        public HelloDTO(String name) {
            this.body += name;
        }
    }
}
