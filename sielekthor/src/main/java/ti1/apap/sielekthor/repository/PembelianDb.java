package ti1.apap.sielekthor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ti1.apap.sielekthor.model.BarangModel;
import ti1.apap.sielekthor.model.MemberModel;
import ti1.apap.sielekthor.model.PembelianModel;
import ti1.apap.sielekthor.model.TipeModel;

import java.util.List;
import java.util.Optional;

public interface PembelianDb extends JpaRepository<PembelianModel,Long> {
    Optional<PembelianModel> findByIdPembelian(Long idPembalian);
    List<PembelianModel> findPembelianByMember(MemberModel member);
}
