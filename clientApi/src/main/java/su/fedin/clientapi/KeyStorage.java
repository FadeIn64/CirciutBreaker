package su.fedin.clientapi;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class KeyStorage {
    List<String> storage = new ArrayList<>();

    public boolean checkKey(String key){
        return storage.contains(key);
    }

    public void add(String key){
        storage.add(key);
    }
}
