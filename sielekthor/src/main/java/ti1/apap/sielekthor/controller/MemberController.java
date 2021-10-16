package ti1.apap.sielekthor.controller;
import ti1.apap.sielekthor.model.BarangModel;
import ti1.apap.sielekthor.model.TipeModel;
import ti1.apap.sielekthor.model.MemberModel;
import ti1.apap.sielekthor.service.BarangService;
import ti1.apap.sielekthor.service.MemberService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import ti1.apap.sielekthor.service.TipeService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MemberController {

    @Qualifier("memberServiceImpl")
    @Autowired
    private MemberService memberService;

    @GetMapping("/member/tambah")
    public String tambahMemberForm(Model model){
        model.addAttribute("member", new MemberModel());
        return "form-add-member";
    }

    @PostMapping("/member/tambah")
    public String tambahMemberSubmit(
            @ModelAttribute MemberModel member,
            Model model
    ){
        memberService.addMember(member);
        model.addAttribute("nama_member", member.getNama_member());
        return "add-member";
    }

    @GetMapping("/member")
    public String listMember(Model model){
        List<MemberModel> listMember = memberService.getMemberList();
        model.addAttribute("listMember",listMember);
        return "viewall-member";
    }

    @GetMapping("/member/ubah/{idMember}")
    public String ubahMemberForm(
            @PathVariable Long idMember,
            Model model
    ){
        MemberModel member = memberService.getMemberByIdMember(idMember);
        model.addAttribute("member",member);
        return "form-update-member";
    }

    @PostMapping("/member/ubah")
    public String ubahMemberSubmit(
            @ModelAttribute MemberModel member,
            Model model
    ){
        memberService.updateMember(member);
        model.addAttribute("nama_member", member.getNama_member());
        return "update-member";
    }

//    @GetMapping("/test")
//    public @ResponseBody List<Object> list(){
//        System.out.println(memberService.getMemberListDescByJumlahPembelian().toString());
//        return memberService.getMemberListDescByJumlahPembelian();
//    }

    @GetMapping("/bonus/cari/member/paling-banyak")
    public String listMemberCari(Model model){
        List<MemberModel> listMember = memberService.getMemberListByPembelian();
        model.addAttribute("listMember", listMember);
        return "bonus";
    }
}
