package ti1.apap.sielekthor.service;

import ti1.apap.sielekthor.model.BarangModel;
import ti1.apap.sielekthor.model.TipeModel;
import ti1.apap.sielekthor.repository.TipeDb;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TipeServiceImpl implements TipeService {

    @Autowired
    TipeDb tipeDb;

    @Override
    public List<TipeModel> getListTipe() {
        return tipeDb.findAll();
    }

    @Override
    public TipeModel getTipeByIdTipe (Long idTipe){
        Optional<TipeModel> tipe = tipeDb.findByIdTipe(idTipe);
        if(tipe.isPresent()){
            return tipe.get();
        }
        return null;
    }
}
