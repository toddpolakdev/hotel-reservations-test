package edu.wgu.d387_sample_code;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class WelcomeRunner implements ApplicationRunner {
    private static final Logger log = LoggerFactory.getLogger(WelcomeRunner.class);
    private final MessageSource messageSource;

    public WelcomeRunner(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Runnable enTask = () -> {
            String msg = messageSource.getMessage("welcome", null, Locale.US);
            log.info("[{}] {}", Thread.currentThread().getName(), msg);
        };

        Runnable frTask = () -> {
            String msg = messageSource.getMessage("welcome", null, Locale.CANADA_FRENCH);
            log.info("[{}] {}", Thread.currentThread().getName(), msg);
        };

        Thread tEn = new Thread(enTask, "welcome-en");
        Thread tFr = new Thread(frTask, "welcome-fr");

        tEn.start();
        tFr.start();

        tEn.join();
        tFr.join();
    }
}
