package com.ii.illumio;

import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class App {
    private static final Integer IANA_ARG = 0;
    private static final Integer LOG_ARG = 1;
    private static final Integer TAG_ARG = 2;
    private static final Integer COUNTS_ARG = 3;
    private static final Logger logger = Logger.getLogger(App.class.getName());

    public static void run(String iana, String log, String tags, String counts) {
        try {
            IANAService ianaService = new IANAService(iana);
            LogProvider lp = new LogProvider(ianaService, log);
            DataStore ds = new DataStore(ianaService, tags);
            String line = lp.next();
            while (line != null) {
                ds.inc(line);
                line = lp.next();
            }
            ds.export(counts);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error while accessing a file.");
        }
    }

    public static void main(String[] args) {
        if (args.length <= COUNTS_ARG)
            System.out.println("Please provide four file names as parameters: " + Arrays.toString(args));
        else run(args[IANA_ARG], args[LOG_ARG], args[TAG_ARG], args[COUNTS_ARG]);
    }
}
