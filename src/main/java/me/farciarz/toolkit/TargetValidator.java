package me.farciarz.toolkit;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class TargetValidator {

    // Stara metoda dla skanowania 1 portu z "gadatliwymi" logami
    public static boolean validate(String ip) {
        // 1. Format
        try {
            InetAddress addr = InetAddress.getByName(ip);
            if (addr.isLoopbackAddress()) {
                System.out.println("[*] Loopback – skip.");
                return true;
            }
        } catch (UnknownHostException e) {
            System.out.println("[!] invalid hostname: " + ip);
            return false;
        }

        // 2. TCP probe
        for (int probePort : new int[]{80, 443, 22}) {
            try (Socket s = new Socket()) {
                s.connect(new InetSocketAddress(ip, probePort), 1500);
                System.out.printf("[+] Host %s respond on port %d.%n", ip, probePort);
                return true;
            } catch (IOException ignored) {}
        }

        // 3. ICMP fallback
        try {
            if (InetAddress.getByName(ip).isReachable(2000)) {
                System.out.println("[+] Host responds on ICMP ping.");
                return true;
            }
        } catch (IOException ignored) {}

        System.out.println("[!] Host " + ip + " doesn't respond.");
        return false;
    }

    public static boolean validateQuiet(String ip) {
        try {
            InetAddress addr = InetAddress.getByName(ip);
            if (addr.isLoopbackAddress()) return true;
        } catch (UnknownHostException e) {
            return false;
        }

        try {
            if (InetAddress.getByName(ip).isReachable(1000)) {
                return true;
            }
        } catch (IOException ignored) {}

        int[] probePorts = {80, 443, 22, 135, 445};
        for (int probePort : probePorts) {
            try (Socket s = new Socket()) {
                s.connect(new InetSocketAddress(ip, probePort), 300);
                return true;
            } catch (IOException ignored) {}
        }

        return false;
    }
}