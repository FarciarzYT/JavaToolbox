package me.farciarz.toolkit;

import java.net.*;
import java.util.concurrent.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        String target = "192.168.56.1";
        ExecutorService pool = Executors.newFixedThreadPool(10000);

        for (int port = 1; port <= 65535; port++) {
            final int p = port;
            pool.execute(() -> {
                try {
                    Socket s = new Socket();
                    InetSocketAddress isa = new InetSocketAddress(target, p);
                    s.connect(isa, 200);
                    System.out.println("OTWARTY: " + isa.getAddress() +" "+  isa.getPort());
                    s.close();
                } catch (Exception e) {
                    //closed
                }
            });
        }
        pool.shutdown();
        pool.awaitTermination(60, TimeUnit.SECONDS);
    }
}