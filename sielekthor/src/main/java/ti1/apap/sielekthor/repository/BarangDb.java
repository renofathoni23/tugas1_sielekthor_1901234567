package ti1.apap.sielekthor.repository;

import ti1.apap.sielekthor.model.BarangModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ti1.apap.sielekthor.model.TipeModel;

import java.util.List;
import java.util.Optional;

public interface BarangDb extends JpaRepository<BarangModel, Long> {
    Optional<BarangModel> findByIdBarang(Long idBarang);
    List<BarangModel> findBarangByTipe(TipeModel tipe);
}
