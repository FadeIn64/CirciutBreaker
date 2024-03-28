package su.fedin.clientapi;

import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@Controller
public class PostController {
    @Autowired
    StringStorage storage;

    @PostMapping("/add/{s}")
    ResponseEntity add(@PathVariable String s, @RequestHeader("I-Key") String key){
        storage.add(s, key);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/list")
    ResponseEntity<String> get(){
        return new ResponseEntity<String>(storage.storage.toString(), HttpStatus.OK);
    }
}
