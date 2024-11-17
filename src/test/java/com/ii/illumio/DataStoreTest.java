package com.ii.illumio;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class DataStoreTest {
    private static final String IANA = "data/iana.csv";
    private static final String TAGS = "data/tags.csv";

    @Test
    public void returnNullWhenNoDataAvailable() {
        assertDoesNotThrow(() -> new DataStore(new IANAService(IANA), TAGS));
    }
}
