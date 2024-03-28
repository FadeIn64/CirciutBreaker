package su.fedin.clientapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StringStorage {

    @Autowired
    KeyStorage keyStorage;

    List<String> storage = new ArrayList<>();

    public void add(String s, String key){
        if (keyStorage.checkKey(key)) return;
        keyStorage.add(key);
        storage.add(s);
    }
}
