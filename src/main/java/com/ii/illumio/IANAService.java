package com.ii.illumio;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class IANAService {
    private final Map<String, String> ids = new HashMap<>();
    private final Set<String> names = new HashSet<>();

    public IANAService(String iana) throws IOException {
        BufferedReader protocolNumbersReader = new BufferedReader(new FileReader(iana));
        String protocol = protocolNumbersReader.readLine();
        while (protocol != null) {
            String[] p = protocol.split(",");
            p[1] = p[1].toLowerCase();
            ids.put(p[0], p[1]);
            names.add(p[1]);
            protocol = protocolNumbersReader.readLine();
        }
    }

    /**
     * Returns null if id doesn't exist
     */
    public String decode(String id) {
        return ids.get(id);
    }

    /**
     * Verify if name is supported protocol name
     *
     * @param name valid iana protocol name
     * @return name if exists otherwise null
     */
    public String getName(String name) {
        name = name.toLowerCase();
        return names.contains(name) ? name : null;
    }
}
