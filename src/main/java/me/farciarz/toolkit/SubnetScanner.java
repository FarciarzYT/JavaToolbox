package me.farciarz.toolkit;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class SubnetScanner {

    private final String subnetCidr;
    private final ScanLogger logger;
    private final AtomicInteger activeHosts = new AtomicInteger(0);

    public SubnetScanner(String subnetCidr, ScanLogger logger) {
        this.subnetCidr = subnetCidr;
        this.logger = logger;
    }

    public void run() {
        List<String> ipsToScan;
        try {
            ipsToScan = CidrUtils.getIpsFromCidr(subnetCidr);
        } catch (Exception e) {
            System.out.println("[!] error parsing CIDR. use format like 192.168.1.0/24");
            return;
        }

        System.out.println("[*] addresses to search: " + ipsToScan.size());

        try (ExecutorService pool = Executors.newVirtualThreadPerTaskExecutor()) {
            for (String ip : ipsToScan) {
                pool.execute(() -> {
                    if (TargetValidator.validateQuiet(ip)) {
                        activeHosts.incrementAndGet();
                        System.out.println("[+] Active Host: " + ip);
                        logger.log(0, "HOST-UP (" + ip + ")");
                    }
                });
            }

            pool.shutdown();
            pool.awaitTermination(2, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            System.out.println("[!] Scan failure.");
        }
    }

    public int getActiveHostsCount() {
        return activeHosts.get();
    }
}