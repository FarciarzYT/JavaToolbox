package me.farciarz.toolkit;

import java.io.IOException;
import java.net.*;

public class TargetValidator {

    public static boolean validate(String ip) {
        // 1. Format
        try {
            InetAddress addr = InetAddress.getByName(ip);
            if (addr.isLoopbackAddress()) {
                System.out.println("[*] Loopback – skip.");
                return true;
            }
        } catch (UnknownHostException e) {
            System.out.println("[!] wrong IP / hostname: " + ip);
            return false;
        }

        // 2. TCP probe
        for (int probePort : new int[]{80, 443, 22}) {
            try (Socket s = new Socket()) {
                s.connect(new InetSocketAddress(ip, probePort), 1500);
                System.out.printf("[+] Host %s responds on port %d.%n", ip, probePort);
                return true;
            } catch (IOException ignored) {}
        }

        // 3. ICMP fallback
        try {
            if (InetAddress.getByName(ip).isReachable(2000)) {
                System.out.println("[+] Host doesn't respond to ICMP ping.");
                return true;
            }
        } catch (IOException ignored) {}

        System.out.println("[!] Host " + ip + " doesn't respond.");
        return false;
    }
}