package edu.wgu.d387_sample_code.utils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public final class Timezone {

    public static final ZoneId ET  = ZoneId.of("America/New_York");
    public static final ZoneId MT  = ZoneId.of("America/Denver");
    public static final ZoneId UTC = ZoneId.of("UTC");

    private Timezone() { }

    public static ZonedDateTime convert(LocalDateTime localDateTime,
                                        ZoneId fromZone,
                                        ZoneId toZone) {
        if (localDateTime == null || fromZone == null || toZone == null) {
            throw new IllegalArgumentException("localDateTime, fromZone, and toZone are required");
        }
        ZonedDateTime fromZdt = localDateTime.atZone(fromZone);
        return fromZdt.withZoneSameInstant(toZone);
    }
}
