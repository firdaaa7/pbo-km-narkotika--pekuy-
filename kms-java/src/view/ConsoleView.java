package view;

import controller.KnowledgeController;
import model.Putusan;
import model.StatistikPutusan;
import util.InputHandler;
import util.DataTidakValidException;
import java.util.ArrayList;


public class ConsoleView {

    private KnowledgeController controller;
    private InputHandler input;

    public ConsoleView(KnowledgeController controller) {
        this.controller = controller;
        this.input = new InputHandler();
    }


    public void mulai() {
        boolean lanjut = true;
        while (lanjut) {
            tampilkanMenu();
            int pilihan = input.bacaPilihanMenu("Pilih menu", 1, 7);
            switch (pilihan) {
                case 1: prosesTambahData(); break;
                case 2: prosesLihatSemua(); break;
                case 3: prosesCariData(); break;
                case 4: prosesFilterData(); break;
                case 5: prosesHapusData(); break;
                case 6: prosesStatistik(); break;
                case 7: lanjut = false; System.out.println("Sampai jumpa!"); break;
            }
        }
    }



    private void tampilkanMenu() {
        System.out.println("\n===== KMS PUTUSAN NARKOTIKA =====");
        System.out.println("1. Tambah Data");
        System.out.println("2. Lihat Semua Data");
        System.out.println("3. Cari Data");
        System.out.println("4. Filter Data");
        System.out.println("5. Hapus Data");
        System.out.println("6. Lihat Statistik");
        System.out.println("7. Keluar");
    }

    private void tampilkanDaftar(ArrayList<Putusan> daftar) {
        if (daftar.isEmpty()) {
            System.out.println("Tidak ada data.");
            return;
        }
        for (Putusan p : daftar) {
            p.tampilkan();
        }
    }


    private void prosesTambahData() {
        String nomorPerkara = input.bacaTeks("Nomor Perkara");
        String pengadilan = input.bacaTeks("Pengadilan");
        String tanggalPutusan = input.bacaTeks("Tanggal Putusan");
        String namaTerdakwa = input.bacaTeks("Nama Terdakwa");
        int umurTerdakwa = input.bacaAngka("Umur Terdakwa");
        String jenisNarkotika = input.bacaTeks("Jenis Narkotika");
        double beratBarangBukti = input.bacaDesimal("Berat Barang Bukti (gram)");
        String pasalDilanggar = input.bacaTeks("Pasal Dilanggar");
        String peranTerdakwa = input.bacaTeks("Peran Terdakwa");
        int vonisHukuman = input.bacaAngka("Vonis Hukuman (bulan)");
        double vonisDenda = input.bacaDesimal("Vonis Denda (rupiah)");
        String namaHakim = input.bacaTeks("Nama Hakim");

        Putusan p = new Putusan(nomorPerkara, pengadilan, tanggalPutusan, namaTerdakwa,
                umurTerdakwa, jenisNarkotika, beratBarangBukti, pasalDilanggar,
                peranTerdakwa, vonisHukuman, vonisDenda, namaHakim);

        try {
            controller.tambahData(p);
            System.out.println("Data berhasil ditambahkan.");
        } catch (DataTidakValidException e) {
            System.out.println("Gagal menambah data: " + e.getMessage());
        }
    }

    private void prosesLihatSemua() {
        tampilkanDaftar(controller.getSemuaData());
    }

    private void prosesCariData() {
        System.out.println("Cari berdasarkan: 1) Nomor Perkara  2) Nama Terdakwa");
        int pilihan = input.bacaPilihanMenu("Pilih", 1, 2);
        if (pilihan == 1) {
            String nomor = input.bacaTeks("Nomor Perkara");
            Putusan hasil = controller.cariData(nomor);
            if (hasil != null) {
                hasil.tampilkan(true);
            } else {
                System.out.println("Data tidak ditemukan.");
            }
        } else {
            String nama = input.bacaTeks("Nama Terdakwa");
            tampilkanDaftar(controller.cariByNama(nama));
        }
    }

    private void prosesFilterData() {
        System.out.println("Filter berdasarkan: 1) Jenis Narkotika  2) Pengadilan");
        int pilihan = input.bacaPilihanMenu("Pilih", 1, 2);
        if (pilihan == 1) {
            String jenis = input.bacaTeks("Jenis Narkotika");
            tampilkanDaftar(controller.filterByJenis(jenis));
        } else {
            String pengadilan = input.bacaTeks("Nama Pengadilan");
            tampilkanDaftar(controller.filterByPengadilan(pengadilan));
        }
    }

    private void prosesHapusData() {
        String nomor = input.bacaTeks("Nomor Perkara yang dihapus");
        boolean berhasil = controller.hapusData(nomor);
        System.out.println(berhasil ? "Data berhasil dihapus." : "Data tidak ditemukan.");
    }

    private void prosesStatistik() {
        StatistikPutusan stat = controller.getStatistik();
        stat.tampilkanLaporan();
    }
}