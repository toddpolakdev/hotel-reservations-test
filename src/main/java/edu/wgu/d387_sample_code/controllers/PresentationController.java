package edu.wgu.d387_sample_code.controllers;

import edu.wgu.d387_sample_code.model.response.PresentationResponse;
import edu.wgu.d387_sample_code.service.PresentationService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/presentation")
@CrossOrigin(origins = {"http://localhost:4200"})
public class PresentationController {

    private final PresentationService service;

    public PresentationController(PresentationService service) {
        this.service = service;
    }

    @GetMapping(value = "/time", produces = MediaType.APPLICATION_JSON_VALUE)
    public PresentationResponse getTimes(
            @RequestParam("etStart")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime etStart
    ) {
        return service.formatEtMtUtc(etStart);
    }
}
