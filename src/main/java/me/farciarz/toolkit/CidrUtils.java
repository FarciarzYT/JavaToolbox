package me.farciarz.toolkit;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class CidrUtils {

    /**
     * Konwertuje zapis CIDR (np. "192.168.1.0/24") na listę wszystkich adresów IP w podsieci.
     */
    public static List<String> getIpsFromCidr(String cidr) throws UnknownHostException {
        String[] parts = cidr.split("/");
        if (parts.length != 2) throw new IllegalArgumentException("Invalid CIDR format");

        String ip = parts[0];
        int prefixLength = Integer.parseInt(parts[1]);

        int mask = 0xffffffff << (32 - prefixLength);
        int ipInt = ipToInt(ip);

        int networkAddress = ipInt & mask;
        int broadcastAddress = networkAddress | ~mask;

        List<String> ips = new ArrayList<>();
        for (int i = networkAddress + 1; i < broadcastAddress; i++) {
            ips.add(intToIp(i));
        }

        return ips;
    }

    private static int ipToInt(String ipAddress) throws UnknownHostException {
        byte[] bytes = InetAddress.getByName(ipAddress).getAddress();
        int val = 0;
        for (int i = 0; i < bytes.length; i++) {
            val <<= 8;
            val |= bytes[i] & 0xff;
        }
        return val;
    }

    private static String intToIp(int ip) {
        return ((ip >> 24) & 0xFF) + "." +
                ((ip >> 16) & 0xFF) + "." +
                ((ip >> 8) & 0xFF) + "." +
                (ip & 0xFF);
    }
}