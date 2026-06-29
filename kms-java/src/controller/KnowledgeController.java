package controller;

import model.Putusan;
import model.KnowledgeRepository;
import model.StatistikPutusan;
import util.DataTidakValidException;
import java.util.ArrayList;

public class KnowledgeController {

    private KnowledgeRepository repository;

    public KnowledgeController() {
        this.repository = new KnowledgeRepository();
    }

    public boolean tambahData(Putusan p) throws DataTidakValidException {
        if (p == null || p.getNomorPerkara().isEmpty()) {
            throw new DataTidakValidException("Nomor perkara tidak boleh kosong.");
        }
        if (repository.cariByNomor(p.getNomorPerkara()) != null) {
            throw new DataTidakValidException("Nomor perkara '" + p.getNomorPerkara() + "' sudah ada (duplikat).");
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

    public void muatDataAwal() {
        repository.muatDataSampel();
    }
}
