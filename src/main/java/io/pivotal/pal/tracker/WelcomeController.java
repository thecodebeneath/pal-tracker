package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
public class WelcomeController {

    private String message;

    public WelcomeController(@Value("${WELCOME_MESSAGE}")String envMessage) {
        message = envMessage;
    }


    @GetMapping("/")
    public String sayHello() {
        return message;
    }
}
