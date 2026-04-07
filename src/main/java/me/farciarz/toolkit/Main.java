package me.farciarz.toolkit;

public class Main {

    public static void main(String[] args) throws Exception {
        showAscii();
        String target = args.length > 0 ? args[0] : "localhost"; //change Target <----------

        System.out.println("[*] Target: " + target);
        System.out.println("[*] Validating Host...");
        if (!TargetValidator.validate(target)) {
            System.out.println("[!] PANIC – target not found.");
            return;
        }
        long startTime = System.currentTimeMillis();
        try (ScanLogger logger = new ScanLogger("scan_results.txt")) {
            PortScanner scanner = new PortScanner(target, logger);
            scanner.run();
            long elapsed = System.currentTimeMillis() - startTime;
            printSummary(target, scanner.getScanned(), scanner.getOpenCount(), elapsed);
        }
    }

    static void printSummary(String target, int scanned, int open, long ms) {
        long s = (ms / 1000) % 60;
        long m = (ms / 60_000) % 60;
        String duration = String.format("%dm %02ds (%dms)", m, s, ms);

        System.out.println();
        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║             Scan Results             ║");
        System.out.println("╠══════════════════════════════════════╣");
        System.out.printf( "║  Target:           %-18s║%n", target);
        System.out.printf( "║  Ports Checked:     %-17d║%n", scanned);
        System.out.printf( "║  Opened Ports:      %-17d║%n", open);
        System.out.printf( "║  scan time:         %-17s║%n", duration);
        System.out.println("║  results: scan_results.txt           ║");
        System.out.println("╚══════════════════════════════════════╝");
    }

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
}