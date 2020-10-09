package tn.msvcs.cnbeerorderservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CnBeerOrderServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CnBeerOrderServiceApplication.class, args);
    }

}
