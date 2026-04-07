package me.farciarz.toolkit;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ScanLogger implements Closeable {

    private final PrintWriter writer;
    private static final DateTimeFormatter FMT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public ScanLogger(String filename) throws IOException {
        this.writer = new PrintWriter(new BufferedWriter(new FileWriter(filename, true)));
    }

    public synchronized void log(int port, String service) {
        String line = String.format("[%s] PORT: %d | %s", LocalDateTime.now().format(FMT), port, service);
        writer.println(line);
        writer.flush();
    }

    @Override
    public void close() {
        writer.close();
    }
}