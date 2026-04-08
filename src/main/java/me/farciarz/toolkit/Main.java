package me.farciarz.toolkit;
import org.w3c.dom.ls.LSOutput;

import java.nio.charset.StandardCharsets;
import java.sql.SQLOutput;
import java.util.Scanner;

import static me.farciarz.toolkit.CliHelper.*;
import static me.farciarz.toolkit.XORCipher.*;

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
        if (target.isEmpty()){
            target = "127.0.0.1";
        };
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
        if (subnet.isEmpty()){
            subnet = "192.168.1.0/24";
        };
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
        showCipherMenu();
        int choice = input.nextInt();
        switch (choice) {
            case 1 -> startXOREncryption();
            case 2 -> startXOREncryption();
            case 5 -> {
                System.out.println("Closing...");
            }
        }
    }

    private static void startXOREncryption(){
        showXOROptionsMenu();
        int choice = input.nextInt();
        switch (choice) {
            case 1 -> {
                showCipherOptionsMenu();
                choice = input.nextInt();
                input.nextLine();
                switch (choice){
                    case 1 -> {
                        System.out.println("========================");
                        System.out.println("[!] Starting Encryption");
                        System.out.println("========================");
                        System.out.println();
                        System.out.println("[?] Insert Text You Want to be encrypted");
                        String text = input.nextLine();
                        System.out.println("[?] Insert Key For your Encryption");
                        String key = input.nextLine();
                        System.out.println("[*] Encrypted Hex:");
                        byte[] encrypted = encrypt(text, key).getBytes(StandardCharsets.UTF_8);
                        showInHex(encrypted);
                        System.out.println();
                    }
                    case 2 ->{
                        System.out.println("File support incoming");
                    }
                    case 5 ->{
                        System.out.println("[!] exiting");
                    }
                    default -> throw new IllegalStateException("Unexpected value: " + choice);
                }
            }
            case 2 ->{
                showCipherOptionsMenu();
                choice = input.nextInt();
                input.nextLine();
                switch (choice){
                    case 1 -> {
                        System.out.println("========================");
                        System.out.println("[!] Starting Decryption");
                        System.out.println("========================");
                        System.out.println();
                        System.out.println("[?] Insert Hex You Want to decrypt");
                        String hex = input.nextLine();
                        System.out.println("[?] Insert Key For your Encryption");
                        String key = input.nextLine();
                        byte[] bytes = hexToBytes(hex);
                        System.out.println("[*] Decrypted Hex");
                        System.out.println(decrypt(bytes ,key));
                    }
                    case 2 ->{
                        System.out.println("File support incoming");
                    }
                    case 5 ->{
                        System.out.println("[!] exiting");
                    }
                    default -> throw new IllegalStateException("Unexpected value: " + choice);
                }

            }
            case 5 -> {
                System.out.println("cd ..");
            }
        }
    }
}