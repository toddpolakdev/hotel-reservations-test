package edu.wgu.d387_sample_code.controllers;

import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@RestController
@RequestMapping("/api/welcome")
@CrossOrigin(origins = "http://localhost:4200")
public class WelcomeController {

    private final MessageSource messageSource;

    public WelcomeController(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @GetMapping(produces = "text/plain;charset=UTF-8")
    public String welcome(@RequestParam(defaultValue = "en-US") String lang) {
        Locale locale = Locale.forLanguageTag(lang);
        return messageSource.getMessage("welcome", null, locale);
    }
}