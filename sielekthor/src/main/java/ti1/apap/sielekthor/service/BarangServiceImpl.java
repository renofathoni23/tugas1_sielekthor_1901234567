package ti1.apap.sielekthor.service;

import ti1.apap.sielekthor.model.BarangModel;
import ti1.apap.sielekthor.model.TipeModel;
import ti1.apap.sielekthor.repository.BarangDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional


public class BarangServiceImpl implements BarangService {

    @Autowired
    BarangDb barangDb;

    @Override
    public List<BarangModel> getBarangList(){
        return barangDb.findAll();
    }

    @Override
    public void addBarang(BarangModel barang){
        barangDb.save(barang);
    }

    @Override
    public BarangModel getBarangByIdBarang(Long idBarang){
        Optional <BarangModel> barang = barangDb.findByIdBarang(idBarang);
        if(barang.isPresent()){
            return barang.get();
        }
        return null;
    }

    @Override
    public void updateBarang(BarangModel barang){
        barangDb.save(barang);
    }

    @Override
    public List<BarangModel> getListBarangByTipe(TipeModel tipe){
        return barangDb.findBarangByTipe(tipe);
    }
}
