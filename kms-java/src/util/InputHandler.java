package util;

import java.util.Scanner;

/**
 * InputHandler bertugas membaca & memvalidasi input dari pengguna lewat console.
 * Layer: UTIL — dipakai oleh View maupun Controller, supaya logika validasi
 * tidak ditulis berulang-ulang di banyak tempat.
 *
 * Konsep OOP yang diterapkan:
 * - Exception handling (try-catch) supaya program tidak crash kalau user salah input
 */
public class InputHandler {

    private Scanner scanner;

    public InputHandler() {
        this.scanner = new Scanner(System.in);
    }

    public String bacaTeks(String label) {
        String input;
        do {
            System.out.print(label + ": ");
            input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("Input tidak boleh kosong, coba lagi.");
            }
        } while (input.isEmpty());
        return input;
    }

    public int bacaAngka(String label) {
        while (true) {
            System.out.print(label + ": ");
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Itu bukan angka yang valid, coba lagi.");
            }
        }
    }

    public double bacaDesimal(String label) {
        while (true) {
            System.out.print(label + ": ");
            String input = scanner.nextLine().trim();
            try {
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println("Itu bukan angka desimal yang valid, coba lagi.");
            }
        }
    }

    public int bacaPilihanMenu(String label, int min, int max) {
        while (true) {
            int pilihan = bacaAngka(label);
            if (pilihan >= min && pilihan <= max) {
                return pilihan;
            }
            System.out.println("Pilihan harus antara " + min + " - " + max + ".");
        }
    }
}