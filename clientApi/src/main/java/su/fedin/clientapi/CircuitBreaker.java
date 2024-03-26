package su.fedin.clientapi;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

@Component
@Scope("prototype")
public class CircuitBreaker {
    @Value("${circuitbreaker.milescheck}")
    private Long milesCheck;

    @Value("${circuitbreaker.maxfailures}")
    int maxFailures;

    private int failures = 0;
    long errorTimestamp;


    public void handleException(HttpClientErrorException e){
        if (!e.getStatusCode().is5xxServerError()) return;

        failures++;
        System.out.println(failures);
        if (failures < maxFailures) return;

        if (errorTimestamp == 0) {
            errorTimestamp = System.currentTimeMillis();
        }

    }

    public boolean serverIsAvailable(){

        long timestamp = System.currentTimeMillis();

        if (errorTimestamp > 0 && Math.abs(timestamp - errorTimestamp) > milesCheck) {
            errorTimestamp = 0;
            failures = 0;
        }

        return errorTimestamp == 0;
    }

}
