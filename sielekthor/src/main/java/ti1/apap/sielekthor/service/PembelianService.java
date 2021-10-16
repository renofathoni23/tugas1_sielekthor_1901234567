package ti1.apap.sielekthor.service;

import ti1.apap.sielekthor.model.BarangModel;
import ti1.apap.sielekthor.model.MemberModel;
import ti1.apap.sielekthor.model.PembelianModel;

import java.util.List;

public interface PembelianService {
    List<PembelianModel> getPembelianList();
    PembelianModel getPembelianByIdPembelian (Long idPembelian);
    String generateInvoice (PembelianModel pembelian);
    void hapusPembelian (PembelianModel pembelian);
    List <PembelianModel> getPembelianByMember (MemberModel member);
}
