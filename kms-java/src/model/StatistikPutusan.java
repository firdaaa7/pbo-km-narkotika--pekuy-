package model;

import java.util.ArrayList;

public class StatistikPutusan {

    private static final String[] KATEGORI_JENIS = {"Sabu-sabu", "Ganja", "Ekstasi", "Heroin", "Lainnya"};
    private static final String[] KATEGORI_PERAN = {"Bandar", "Kurir", "Pengguna", "Penyimpan", "Lainnya"};

    private int totalPutusan;
    private double rataRataVonis;
    private double rataRataDenda;
    private String jenisNarkotikaTerbanyak;
    private String[] distribusiPeran;

    public StatistikPutusan(ArrayList<Putusan> daftar) {
        hitungSemua(daftar);
    }

    public void hitungSemua(ArrayList<Putusan> daftar) {
        totalPutusan = daftar.size();

        if (totalPutusan == 0) {
            rataRataVonis = 0;
            rataRataDenda = 0;
            jenisNarkotikaTerbanyak = "-";
            distribusiPeran = new String[0];
            return;
        }

        int totalVonis = 0;
        double totalDenda = 0;
        for (Putusan p : daftar) {
            totalVonis += p.getVonisHukuman();
            totalDenda += p.getVonisDenda();
        }
        rataRataVonis = (double) totalVonis / totalPutusan;
        rataRataDenda = totalDenda / totalPutusan;

        int[] jumlahPerJenis = new int[KATEGORI_JENIS.length];
        for (Putusan p : daftar) {
            for (int i = 0; i < KATEGORI_JENIS.length; i++) {
                if (p.getJenisNarkotika().equalsIgnoreCase(KATEGORI_JENIS[i])) {
                    jumlahPerJenis[i]++;
                    break;
                }
            }
        }
        int indexTerbanyak = 0;
        for (int i = 1; i < jumlahPerJenis.length; i++) {
            if (jumlahPerJenis[i] > jumlahPerJenis[indexTerbanyak]) {
                indexTerbanyak = i;
            }
        }
        jenisNarkotikaTerbanyak = KATEGORI_JENIS[indexTerbanyak] + " (" + jumlahPerJenis[indexTerbanyak] + " kasus)";

        int[] jumlahPerPeran = new int[KATEGORI_PERAN.length];
        for (Putusan p : daftar) {
            for (int i = 0; i < KATEGORI_PERAN.length; i++) {
                if (p.getPeranTerdakwa().equalsIgnoreCase(KATEGORI_PERAN[i])) {
                    jumlahPerPeran[i]++;
                    break;
                }
            }
        }
        distribusiPeran = new String[KATEGORI_PERAN.length];
        for (int i = 0; i < KATEGORI_PERAN.length; i++) {
            distribusiPeran[i] = KATEGORI_PERAN[i] + ": " + jumlahPerPeran[i] + " kasus";
        }
    }

    public void tampilkanLaporan() {
        System.out.println("===== STATISTIK PUTUSAN =====");
        System.out.println("Total data       : " + totalPutusan);
        System.out.println("Rata-rata vonis   : " + rataRataVonis + " bulan");
        System.out.println("Rata-rata denda   : Rp" + rataRataDenda);
        System.out.println("Jenis terbanyak   : " + jenisNarkotikaTerbanyak);
        System.out.println("Distribusi peran  :");
        for (String baris : distribusiPeran) {
            System.out.println("  - " + baris);
        }
    }

    public int getTotalPutusan() { return totalPutusan; }
    public double getRataRataVonis() { return rataRataVonis; }
    public double getRataRataDenda() { return rataRataDenda; }
    public String getJenisNarkotikaTerbanyak() { return jenisNarkotikaTerbanyak; }
    public String[] getDistribusiPeran() { return distribusiPeran; }
}