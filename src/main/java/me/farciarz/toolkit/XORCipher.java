package me.farciarz.toolkit;

import java.nio.charset.StandardCharsets;

public class XORCipher {

    public static String encrypt(String text, String key){
        byte[] input = text.getBytes(StandardCharsets.UTF_8);
        byte[] keyBytes = key.getBytes(StandardCharsets.UTF_8);
        byte[] result = new byte[input.length];

        for (int i = 0; i < input.length; i++) {
            result[i] = (byte)(input[i] ^ keyBytes[i % keyBytes.length]);
        }

        return new String(result, StandardCharsets.UTF_8);
    }

    public static String decrypt(byte[] data, String key){
        byte[] result = new byte[data.length];
        byte[] keyBytes = key.getBytes(StandardCharsets.UTF_8);

        for (int i = 0; i < data.length; i++) {
            result[i] = (byte)(data[i] ^ keyBytes[i % keyBytes.length]);
        }

        return new String(result);
    }

    public static byte[] hexToBytes(String hex) {
        String[] parts = hex.split(" ");
        byte[] result = new byte[parts.length];

        for (int i = 0; i < parts.length; i++) {
            result[i] = (byte) Integer.parseInt(parts[i], 16);
        }

        return result;
    }

    public static void showInHex(byte[] encrypted){
        for (byte b : encrypted) {
            System.out.printf("%02X ", b);
        }
    }

    public static void main(String[] args) {
        String text = "Test the encryption";
        String key = "Kocham Wszystkich";
        byte[] encrypted = encrypt(text, key).getBytes(StandardCharsets.UTF_8);
        showInHex(encrypted);
    }
}
