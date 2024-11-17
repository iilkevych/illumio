package com.ii.illumio;

import org.junit.jupiter.api.Test;

public class AppTest {
    private static final String IANA = "data/iana.csv";
    private static final String TAGS = "data/tags.csv";
    private static final String FLOW = "data/flow.log";
    private static final String OUTPUT = "data/output.csv";

    /**
     * Just run App
     */
    @Test
    public void returnNullWhenNoDataAvailable() {
        App.run(IANA, FLOW, TAGS, OUTPUT);
    }
}
