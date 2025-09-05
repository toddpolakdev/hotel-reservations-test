package edu.wgu.d387_sample_code.model.response;

public record PresentationResponse(
        String et,
        String mt,
        String utc,
        String message,
        String instantUtcIso
) {}
