package de.c24.finacc.klt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Spring boot application start point
 */
@EnableCaching
@EnableFeignClients
@SpringBootApplication
public class KltApplication {

    /**
     * start point
     *
     * @param args system arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(KltApplication.class, args);
    }
}
