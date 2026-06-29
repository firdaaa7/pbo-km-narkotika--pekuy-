package model;

import java.util.ArrayList;


public class KnowledgeRepository {

    private ArrayList<Putusan> daftarPutusan;

    public KnowledgeRepository() {
        this.daftarPutusan = new ArrayList<>();
    }

    public void simpan(Putusan p) {
        daftarPutusan.add(p);
    }

    public Putusan cariByNomor(String nomor) {
        for (Putusan p : daftarPutusan) {
            if (p.getNomorPerkara().equalsIgnoreCase(nomor)) {
                return p;
            }
        }
        return null;
    }

    public ArrayList<Putusan> cariByNama(String nama) {
        ArrayList<Putusan> hasil = new ArrayList<>();
        for (Putusan p : daftarPutusan) {
            if (p.getNamaTerdakwa().toLowerCase().contains(nama.toLowerCase())) {
                hasil.add(p);
            }
        }
        return hasil;
    }

    public ArrayList<Putusan> filterByJenis(String jenis) {
        ArrayList<Putusan> hasil = new ArrayList<>();
        for (Putusan p : daftarPutusan) {
            if (p.getJenisNarkotika().equalsIgnoreCase(jenis)) {
                hasil.add(p);
            }
        }
        return hasil;
    }

    public ArrayList<Putusan> filterByPengadilan(String pengadilan) {
        ArrayList<Putusan> hasil = new ArrayList<>();
        for (Putusan p : daftarPutusan) {
            if (p.getPengadilan().equalsIgnoreCase(pengadilan)) {
                hasil.add(p);
            }
        }
        return hasil;
    }

    public boolean hapus(String nomor) {
        Putusan target = cariByNomor(nomor);
        if (target != null) {
            daftarPutusan.remove(target);
            return true;
        }
        return false;
    }

    public ArrayList<Putusan> getDaftarSemua() {
        return daftarPutusan;
    }

    public int getTotalData() {
        return daftarPutusan.size();
    }

    public void muatDataSampel() {
        String[] nama = {
                "Budi Santoso","Siti Aminah","Andi Wijaya","Rina Kartika","Joko Susanto",
                "Dewi Lestari","Agus Pratama","Maya Sari","Hendra Gunawan","Yuni Astuti",
                "Rudi Hartono","Lina Marlina","Tono Saputra","Wati Suryani","Bambang Setiawan",
                "Indah Permata","Eko Nugroho","Fitri Handayani","Dedi Kurniawan","Ratna Sari",
                "Slamet Riyadi","Putri Anggraini","Hadi Wibowo","Yanti Susilowati","Aris Munandar",
                "Lia Oktaviani","Wahyu Hidayat","Sri Wahyuni","Fajar Ramadhan","Nita Kurniasih",
                "Iwan Setiabudi","Diah Puspita","Yusuf Rahman","Erna Wulandari","Dani Firmansyah",
                "Wulan Sari","Bayu Aji","Tika Maharani","Anto Sucipto","Ika Nuraini",
                "Reza Pratama","Sinta Dewi","Gilang Ramadhan","Mira Yulianti","Doni Saputra",
                "Lestari Wulandari","Asep Hidayat","Ningsih Astuti","Firman Syah","Citra Permata"
        };
        String[] kotaArr = {"PN Surabaya","PN Jakarta Pusat","PN Bandung","PN Medan","PN Semarang",
                "PN Makassar","PN Denpasar","PN Yogyakarta","PN Malang","PN Palembang"};
        String[] jenisArr = {"Sabu-sabu","Ganja","Ekstasi","Heroin","Lainnya"};
        String[] peranArr = {"Bandar","Kurir","Pengguna","Penyimpan","Lainnya"};
        String[] pasalArr = {"Pasal 114 ayat (1) UU No. 35/2009","Pasal 112 ayat (1) UU No. 35/2009",
                "Pasal 127 ayat (1) UU No. 35/2009","Pasal 111 ayat (1) UU No. 35/2009"};
        String[] hakimArr = {"Hakim Andi Saputra","Hakim Maria Christina","Hakim Bambang Sutrisno",
                "Hakim Diah Permatasari","Hakim Rudi Wijaya"};

        for (int i = 0; i < nama.length; i++) {
            int umur = 19 + (i * 3) % 36;
            double berat = 1.0 + (i % 25) * 2.0;
            int vonis = 6 + (i % 20) * 6;
            double denda = 5000000 + (i % 15) * 10000000;
            int day = 1 + (i % 28);
            int month = 1 + (i % 12);
            int year = 2023 + (i % 2);
            String tanggal = String.format("%02d-%02d-%04d", day, month, year);
            String nomor = String.format("%03d/Pid.Sus/%d/PN", i + 1, year);

            String kota = kotaArr[i % kotaArr.length];
            String jenis = jenisArr[i % jenisArr.length];
            String peran = peranArr[i % peranArr.length];
            String pasal = pasalArr[i % pasalArr.length];
            String hakim = hakimArr[i % hakimArr.length];

            Putusan p = new Putusan(nomor, kota, tanggal, nama[i], umur, jenis,
                    berat, pasal, peran, vonis, denda, hakim);
            daftarPutusan.add(p);
        }
    }
}