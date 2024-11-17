package com.ii.illumio;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LogProvider {
    private static final Integer PORT_IDX = 6;
    private static final Integer PROTO_IDX = 7;
    private final BufferedReader lineReader;
    private final IANAService iana;

    public LogProvider(IANAService iana, String path) throws IOException {
        this.iana = iana;
        lineReader = new BufferedReader(new FileReader(path));
    }

    public String next() throws IOException {
        String line = lineReader.readLine();
        while (line != null) {
            String[] lines = line.split(" ");
            lines[PROTO_IDX] = iana.decode(lines[PROTO_IDX]);
            if (lines[PROTO_IDX] != null) {
                return lines[PORT_IDX] + "," + lines[PROTO_IDX];
            } else line = lineReader.readLine();
        }
        return line;
    }
}