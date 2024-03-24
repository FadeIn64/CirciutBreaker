package su.fedin.clientapi;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
public class ServerProperties {

    @Value("${backend.server.domain}")
    String domain;
    @Value("${backend.server.port}")
    String port;
    @Value("${backend.server.protocol}")
    String protocol;

    public String uri(){
        return String.format("%s://%s:%s", protocol, domain, port);
    }
}
