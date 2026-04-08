package me.farciarz.toolkit;

import java.util.Scanner;

public class CliHelper {
    public static void showAscii() {
        String banner = """
                  ▄▄▄██▀▀▀▄▄▄    ██▒   █▓ ▄▄▄         ▄▄▄█████▓ ▒█████   ▒█████   ██▓     ▄▄▄▄    ▒█████  ▒██   ██▒
                    ▒██  ▒████▄ ▓██░   █▒▒████▄       ▓  ██▒ ▓▒▒██▒  ██▒▒██▒  ██▒▓██▒    ▓█████▄ ▒██▒  ██▒▒▒ █ █ ▒░
                    ░██  ▒██  ▀█▄▓██  █▒░▒██  ▀█▄     ▒ ▓██░ ▒░▒██░  ██▒▒██░  ██▒▒██░    ▒██▒ ▄██▒██░  ██▒░░  █   ░
                 ▓██▄██▓ ░██▄▄▄▄██▒██ █░░░██▄▄▄▄██    ░ ▓██▓ ░ ▒██   ██░▒██   ██░▒██░    ▒██░█▀  ▒██   ██░ ░ █ █ ▒\s
                  ▓███▒   ▓█   ▓██▒▒▀█░   ▓█   ▓██▒     ▒██▒ ░ ░ ████▓▒░░ ████▓▒░░██████▒░▓█  ▀█▓░ ████▓▒░▒██▒ ▒██▒
                  ▒▓▒▒░   ▒▒   ▓▒█░░ ▐░   ▒▒   ▓▒█░     ▒ ░░   ░ ▒░▒░▒░ ░ ▒░▒░▒░ ░ ▒░▓  ░░▒▓███▀▒░ ▒░▒░▒░ ▒▒ ░ ░▓ ░
                  ▒ ░▒░    ▒   ▒▒ ░░ ░░    ▒   ▒▒ ░       ░      ░ ▒ ▒░   ░ ▒ ▒░ ░ ░ ▒  ░▒░▒   ░   ░ ▒ ▒░ ░░   ░▒ ░
                  ░ ░ ░    ░   ▒     ░░    ░   ▒        ░      ░ ░ ░ ▒  ░ ░ ░ ▒    ░ ░    ░    ░ ░ ░ ░ ▒   ░    ░ \s
                  ░   ░        ░  ░   ░        ░  ░                ░ ░      ░ ░      ░  ░ ░          ░ ░   ░    ░ \s
                                     ░                                                         ░                  \s
                """;
        System.out.print(banner);
    }

    public static void showMenu(){
        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║          Java-toolkit v0.1           ║");
        System.out.println("╠══════════════════════════════════════╣");
        System.out.println("║  1. TCP Port Scanner                 ║");
        System.out.println("║  2. Subnet Discovery                 ║");
        System.out.println("║  3. Banner Grabbing                  ║");
        System.out.println("║  4. File Encryption                  ║");
        System.out.println("║  5. Exit                             ║");
        System.out.println("╚══════════════════════════════════════╝");

    }
    public static void showCipherMenu(){
        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║         Choose Cipher Methode        ║");
        System.out.println("╠══════════════════════════════════════╣");
        System.out.println("║  1. AES                              ║");
        System.out.println("║  2. XOR                              ║");
        System.out.println("║  5. Exit                             ║");
        System.out.println("╚══════════════════════════════════════╝");
    }

    public static void showXOROptionsMenu(){
            System.out.println("╔══════════════════════════════════════╗");
            System.out.println("║          XOR Cipher Options          ║");
            System.out.println("╠══════════════════════════════════════╣");
            System.out.println("║  1. Encrypt                          ║");
            System.out.println("║  2. Decrypt                          ║");
            System.out.println("║  5. Exit                             ║");
            System.out.println("╚══════════════════════════════════════╝");
    }

    public static void showAESOptionsMenu(){
        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║          AES Cipher Options          ║");
        System.out.println("╠══════════════════════════════════════╣");
        System.out.println("║  1. Encrypt                          ║");
        System.out.println("║  2. Decrypt                          ║");
        System.out.println("║  5. Exit                             ║");
        System.out.println("╚══════════════════════════════════════╝");
      }

    public static void showCipherOptionsMenu() {
        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║            Cipher Options            ║");
        System.out.println("╠══════════════════════════════════════╣");
        System.out.println("║  1. Text                             ║");
        System.out.println("║  2. File                             ║");
        System.out.println("║  5. Exit                             ║");
        System.out.println("╚══════════════════════════════════════╝");
    }
}
