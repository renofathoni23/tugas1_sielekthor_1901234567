package ti1.apap.sielekthor.service;

import ti1.apap.sielekthor.model.MemberModel;
import ti1.apap.sielekthor.repository.MemberDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.lang.reflect.Member;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional

public class MemberServiceImpl implements MemberService {

    @Autowired
    MemberDb memberDb;

    @Override
    public List<MemberModel> getMemberList(){
        return memberDb.findAll();
    }

    @Override
    public void addMember(MemberModel member){
        memberDb.save(member);
    }

    @Override
    public MemberModel getMemberByIdMember(Long idMember){
        Optional <MemberModel> member = memberDb.findByIdMember(idMember);
        if(member.isPresent()){
            return member.get();
        }
        return null;
    }

    @Override
    public void updateMember(MemberModel member){
        memberDb.save(member);
    }

    @Override
    public List<MemberModel> getMemberListByPembelian(){
        List<MemberModel> listMemberDesc = memberDb.findAll();
        for (int i = 0; i < listMemberDesc.size(); i++) {
            for (int j = i+1; j < listMemberDesc.size() ; j++) {
                if(listMemberDesc.get(i).getListPembelian().size() < listMemberDesc.get(j).getListPembelian().size()){
                    Collections.swap(listMemberDesc,j,i);
                }
            }
        }

        return listMemberDesc;
    }
}
