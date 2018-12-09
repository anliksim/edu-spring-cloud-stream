package edu.cloud.stream.streamrabbitmq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.messaging.handler.annotation.SendTo;

@Configuration
@EnableAutoConfiguration
@EnableBinding(Processor.class)
@PropertySource("classpath:name-processor.properties")
public class NameProcessorApplication {

    public static void main(String[] args) {
        SpringApplication.run(NameProcessorApplication.class, args);
    }

    @StreamListener(Processor.INPUT)
    @SendTo(Processor.OUTPUT)
    public Person handle(Person person) {
        System.out.println("Received: " + person);
        person.setName(person.getName()+"new");
        return person;
    }
}
