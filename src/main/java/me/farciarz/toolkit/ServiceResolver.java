package me.farciarz.toolkit;

public class ServiceResolver {

    public static String getName(int port) {
        return switch (port) {
            case 21    -> "FTP";
            case 22    -> "SSH";
            case 23    -> "Telnet";
            case 25    -> "SMTP";
            case 53    -> "DNS";
            case 80    -> "HTTP";
            case 110   -> "POP3";
            case 135   -> "RPC";
            case 139   -> "NetBIOS";
            case 143   -> "IMAP";
            case 443   -> "HTTPS";
            case 445   -> "SMB";
            case 3306  -> "MySQL";
            case 3389  -> "RDP";
            case 5432  -> "PostgreSQL";
            case 6379  -> "Redis";
            case 8080  -> "HTTP-ALT";
            case 8443  -> "HTTPS-ALT";
            case 27017 -> "MongoDB";
            default    -> "UNKNOWN";
        };
    }
}