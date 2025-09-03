package edu.wgu.d387_sample_code.controllers;

import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.*;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.*;

@RestController
@RequestMapping("/api/welcome")
@CrossOrigin(origins = "http://localhost:4200")
public class WelcomeThreadController {

    @GetMapping(value = "/threaded", produces = "application/json;charset=UTF-8")
    public Map<String, Object> threaded() throws ExecutionException, InterruptedException {
        ExecutorService exec = Executors.newFixedThreadPool(2);

        Callable<Map<String, String>> enTask = () ->
                loadBundle("translations_en_US.properties", "en-US");

        Callable<Map<String, String>> frTask = () ->
                loadBundle("translations_fr_CA.properties", "fr-CA");

        Future<Map<String, String>> enF = exec.submit(enTask);
        Future<Map<String, String>> frF = exec.submit(frTask);

        try {
            Map<String, String> en = enF.get();
            Map<String, String> fr = frF.get();

            System.out.printf(
                    Thread.currentThread().getName(), en.get("message"), en.get("locale"), en.get("thread"));
            System.out.printf(
                    Thread.currentThread().getName(), fr.get("message"), fr.get("locale"), fr.get("thread"));

            return Map.of("en", en, "fr", fr);
        } finally {
            exec.shutdown();
        }
    }

    private Map<String, String> loadBundle(String classpathFile, String langTag) {
        Properties p = new Properties();
        try (InputStream in = new ClassPathResource(classpathFile).getInputStream();
             InputStreamReader r = new InputStreamReader(in, StandardCharsets.UTF_8)) {
            p.load(r);
            String msg = p.getProperty("welcome");
            String thread = Thread.currentThread().getName();
            return Map.of("message", msg, "locale", langTag, "thread", thread);
        } catch (java.io.IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
