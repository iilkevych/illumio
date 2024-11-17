package com.ii.illumio;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class LogProviderTest
{
    private static final String IANA = "data/iana.csv";
    private static final String LOG = "data/log";
    private static final String EMPTY_LOG = "data/empty.log";
    private static final String SMALL_LOG = "data/small.log";

    @Test
    public void throwExceptionWhenFileNotFound() {
        assertThrows(IOException.class, () -> new LogProvider(new IANAService(IANA), LOG));
    }

    @Test
    public void returnNullWhenEmptyInput()
    {
        assertDoesNotThrow(() -> assertNull(new LogProvider(new IANAService(IANA), EMPTY_LOG).next()));
    }

    @Test
    public void returnNullWhenNoDataAvailable()
    {
        assertDoesNotThrow(() -> assertNotNull(new LogProvider(new IANAService(IANA), SMALL_LOG).next()));
    }
}
