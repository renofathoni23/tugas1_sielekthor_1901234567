package ti1.apap.sielekthor.service;

import ti1.apap.sielekthor.model.BarangModel;
import ti1.apap.sielekthor.model.TipeModel;

import java.util.List;

public interface BarangService {
    void addBarang(BarangModel barang);
    void updateBarang(BarangModel barang);
    List<BarangModel> getBarangList();
    BarangModel getBarangByIdBarang(Long idBarang);
    List<BarangModel> getListBarangByTipe(TipeModel tipe);
}
