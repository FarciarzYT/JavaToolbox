package me.farciarz.toolkit;

import java.io.IOException;
import java.net.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class PortScanner {

    private final String target;
    private final ScanLogger logger;
    private final AtomicInteger scanned  = new AtomicInteger(0);
    private final AtomicInteger openCount = new AtomicInteger(0);

    public PortScanner(String target, ScanLogger logger) {
        this.target = target;
        this.logger = logger;
    }

    public void run() throws InterruptedException {
        Semaphore semaphore = new Semaphore(1000);

        try (ExecutorService pool = Executors.newVirtualThreadPerTaskExecutor()) {
            for (int port = 1; port <= 65535; port++) {
                semaphore.acquire();
                final int p = port;

                pool.execute(() -> {
                    try (Socket s = new Socket()) {
                        s.connect(new InetSocketAddress(target, p), 200);

                        String service = ServiceResolver.getName(p);
                        openCount.incrementAndGet();
                        System.out.printf("[OPEN] %-6d | %s%n", p, service);
                        logger.log(p, service);

                    } catch (IOException ignored) {
                    } finally {
                        scanned.incrementAndGet();
                        semaphore.release();
                    }
                });
            }

            pool.shutdown();
            pool.awaitTermination(5, TimeUnit.MINUTES);
        }
    }
}