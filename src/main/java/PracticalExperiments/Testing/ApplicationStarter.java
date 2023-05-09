package PracticalExperiments.Testing;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
public class ApplicationStarter{
    public static void main(String[] args) {
        SpringApplication.run(ApplicationStarter.class,args);
    }
}
