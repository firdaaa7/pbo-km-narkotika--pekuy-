package controller;

import model.Putusan;
import model.KnowledgeRepository;
import model.StatistikPutusan;
import java.util.ArrayList;

public class KnowledgeController {

    private KnowledgeRepository repository;

    public KnowledgeController() {
        this.repository = new KnowledgeRepository();
    }

    public boolean tambahData(Putusan p) {
        if (p == null || p.getNomorPerkara().isEmpty()) {
            return false;
        }
        if (repository.cariByNomor(p.getNomorPerkara()) != null) {
            return false;
        }
        repository.simpan(p);
        return true;
    }

    public ArrayList<Putusan> getSemuaData() {
        return repository.getDaftarSemua();
    }

    public Putusan cariData(String nomor) {
        return repository.cariByNomor(nomor);
    }

    public ArrayList<Putusan> cariByNama(String nama) {
        return repository.cariByNama(nama);
    }

    public ArrayList<Putusan> filterByJenis(String jenis) {
        return repository.filterByJenis(jenis);
    }

    public ArrayList<Putusan> filterByPengadilan(String pengadilan) {
        return repository.filterByPengadilan(pengadilan);
    }

    public boolean hapusData(String nomor) {
        return repository.hapus(nomor);
    }

    public StatistikPutusan getStatistik() {
        return new StatistikPutusan(repository.getDaftarSemua());
    }


}
