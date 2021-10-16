package ti1.apap.sielekthor.service;

import ti1.apap.sielekthor.model.TipeModel;

import java.util.List;

public interface TipeService {

    List<TipeModel> getListTipe();
    TipeModel getTipeByIdTipe (Long idTipe);
}
