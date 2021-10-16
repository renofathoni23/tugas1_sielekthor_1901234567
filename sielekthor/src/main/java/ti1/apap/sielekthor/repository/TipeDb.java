package ti1.apap.sielekthor.repository;

import ti1.apap.sielekthor.model.BarangModel;
import ti1.apap.sielekthor.model.TipeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface TipeDb extends JpaRepository<TipeModel, Long>{
    Optional<TipeModel> findByIdTipe(Long idTipe);
}
