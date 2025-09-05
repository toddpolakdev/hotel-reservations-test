package edu.wgu.d387_sample_code.service;

import edu.wgu.d387_sample_code.model.response.PresentationResponse;
import edu.wgu.d387_sample_code.utils.Timezone;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Service
public class PresentationService {

    private static final DateTimeFormatter HM_12H =
            DateTimeFormatter.ofPattern("h:mm a", Locale.US);

    public PresentationResponse formatEtMtUtc(LocalDateTime etStart) {
        ZonedDateTime et  = Timezone.convert(etStart, Timezone.ET,  Timezone.ET);
        ZonedDateTime mt  = Timezone.convert(etStart, Timezone.ET,  Timezone.MT);
        ZonedDateTime utc = Timezone.convert(etStart, Timezone.ET,  Timezone.UTC);

        String etStr  = HM_12H.format(et);
        String mtStr  = HM_12H.format(mt);
        String utcStr = HM_12H.format(utc);

        String message = "Online Live Presentation held at the Landon Hotel: "
                + etStr + " ET | " + mtStr + " MT | " + utcStr + " UTC";

        return new PresentationResponse(
                etStr,
                mtStr,
                utcStr,
                message,
                utc.toInstant().toString()
        );
    }
}