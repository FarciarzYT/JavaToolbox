package me.farciarz.toolkit;

import java.util.Scanner;

import static me.farciarz.toolkit.CliHelper.*;

public class Main {

    private static final Scanner input = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        showAscii();
        while(true) {
            showMenu();
            System.out.print("Select Option: ");
            int choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1 -> startPortScanner();
                case 2 -> startSubnetScan();
                case 3 -> startBannerGrab();
                case 4 -> startFileEncryption();
                case 5 -> {
                    System.out.println("Closing...");
                    return;
                }
                default -> System.out.println("[!] Select Correct Option.");
            }
        }
    }


    private static void startPortScanner() throws Exception {
        System.out.print("[?] input target (127.0.0.1): ");
        String target = input.nextLine();

        System.out.println("[*] Target: " + target);
        System.out.println("[*] Validating Host...");

        if (!TargetValidator.validate(target)) {
            System.out.println("[!] PANIC – target not found or doesnt respond.");
            return;
        }

        try (ScanLogger logger = new ScanLogger("scan_results.txt")) {
            PortScanner scanner = new PortScanner(target, logger);
            scanner.run();
        }
    }

    private static void startSubnetScan() throws Exception {
        System.out.print("[?] input in CIDR format (192.168.1.0/24): ");
        String subnet = input.nextLine();

        System.out.println("[*] Starting to scan subnet: " + subnet);

        long startTime = System.currentTimeMillis();
        try (ScanLogger logger = new ScanLogger("subnet_results.txt")) {
            SubnetScanner scanner = new SubnetScanner(subnet, logger);
            scanner.run();

            long elapsed = System.currentTimeMillis() - startTime;
            System.out.printf("[*] Scan ended in %d ms. Selected %d active hosts.%n", elapsed, scanner.getActiveHostsCount());
        }
    }

    private static void startBannerGrab() {
        System.out.println("start banner");
    }

    private static void startFileEncryption() {
        System.out.println("start encryption");
    }
}