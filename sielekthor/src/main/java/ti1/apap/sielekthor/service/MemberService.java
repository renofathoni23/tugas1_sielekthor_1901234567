package ti1.apap.sielekthor.service;

import ti1.apap.sielekthor.model.BarangModel;
import ti1.apap.sielekthor.model.MemberModel;

import java.util.List;

public interface MemberService {
    void addMember(MemberModel member);
    void updateMember(MemberModel member);
    List<MemberModel> getMemberList();
    MemberModel getMemberByIdMember(Long idMember);
    List<MemberModel> getMemberListByPembelian();
}
