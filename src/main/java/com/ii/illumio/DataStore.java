package com.ii.illumio;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class DataStore {
    private static final String UNTAGGED = "Untagged";
    private final Map<String, String> mapping = new HashMap<>();
    private final Map<String, Integer> tags = new HashMap<>();
    private final Map<String, Integer> logs = new HashMap<>();

    public DataStore(IANAService iana, String tags) throws IOException {
        BufferedReader tagsReader = new BufferedReader(new FileReader(tags));
        String tag = tagsReader.readLine();
        while (tag != null) {
            String[] t = tag.split(",");
            t[1] = iana.getName(t[1]);
            if (t[1] != null && t.length > 2 && !t[2].isEmpty()) {
                mapping.put(t[0] + "," + t[1], t[2]);
            }
            tag = tagsReader.readLine();
        }
    }

    public void inc(String line) {
        logs.compute(line, (k, v) -> v == null ? 1 : v + 1);
        if (mapping.containsKey(line)) tags.compute(mapping.get(line), (k, v) -> v == null ? 1 : v + 1);
        else tags.compute(UNTAGGED, (k, v) -> v == null ? 1 : v + 1);
    }

    public void export(String path) throws IOException {
        BufferedWriter outFile = new BufferedWriter(new FileWriter(path, false));
        outFile.write("Tag Counts:");
        outFile.newLine();
        outFile.write("Tag,Count");
        outFile.newLine();
        for (Map.Entry<String, Integer> entry : tags.entrySet()) {
            outFile.write(entry.getKey() + "," + entry.getValue());
            outFile.newLine();
        }
        outFile.write("Port/Protocol Combination Counts:");
        outFile.newLine();
        outFile.write("Port,Protocol,Count");
        outFile.newLine();
        for (Map.Entry<String, Integer> entry : logs.entrySet()) {
            outFile.write(entry.getKey() + "," + entry.getValue());
            outFile.newLine();
        }
        outFile.close();
    }
}
