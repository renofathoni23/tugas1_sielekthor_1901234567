package ti1.apap.sielekthor.repository;

import org.springframework.data.jpa.repository.Query;
import ti1.apap.sielekthor.model.BarangModel;
import ti1.apap.sielekthor.model.MemberModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface MemberDb extends JpaRepository<MemberModel, Long> {
    Optional<MemberModel> findByIdMember(Long idMember);

}
