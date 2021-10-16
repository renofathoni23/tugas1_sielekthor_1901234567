package ti1.apap.sielekthor.controller;

import ti1.apap.sielekthor.model.*;
import ti1.apap.sielekthor.service.BarangService;
import ti1.apap.sielekthor.service.MemberService;
import ti1.apap.sielekthor.service.PembelianService;

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
public class PembelianController {

    @Qualifier("pembelianServiceImpl")
    @Autowired
    private PembelianService pembelianService;

    @Qualifier("memberServiceImpl")
    @Autowired
    private MemberService memberService;

    @Qualifier("barangServiceImpl")
    @Autowired
    private BarangService barangService;

    @GetMapping("/pembelian")
    public String listPembelian(Model model){
        List<PembelianModel> listPembelian = pembelianService.getPembelianList();
        List<Integer> listTotal = new ArrayList<>();
        for (int i = 0; i < listPembelian.size(); i++) {
            int totalBarang = 0;
            List <PembelianBarangModel> pembelianBarang = listPembelian.get(i).getListPembelianBarang();
            for (int j = 0; j < pembelianBarang.size(); j++) {
                totalBarang+= pembelianBarang.get(j).getQuantity();
            }
            listTotal.add(totalBarang);
            totalBarang = 0;
        }
        model.addAttribute("listPembelian",listPembelian);
        model.addAttribute("listTotal", listTotal);
        return "viewall-pembelian";
    }

    @GetMapping("/pembelian/{idPembelian}")
    public String lihatDetailPembelian(
            @PathVariable Long idPembelian,
            Model model
    ){
        PembelianModel pembelian = pembelianService.getPembelianByIdPembelian(idPembelian);
        List<PembelianBarangModel> listPembelianBarang = pembelian.getListPembelianBarang();
        int jumlahBarang = 0;
        for (int i = 0; i < pembelian.getListPembelianBarang().size(); i++) {
            jumlahBarang += pembelian.getListPembelianBarang().get(i).getQuantity();
        }
        model.addAttribute("jumlahBarang", jumlahBarang);
        model.addAttribute("pembelian", pembelian);
        model.addAttribute("listPembelianBarang", listPembelianBarang);

        return "view-pembelian";
    }

    @RequestMapping("/pembelian/hapus/{idPembelian}")
    public String hapusPembelian(
            @PathVariable long idPembelian,
            Model model
    ){
        PembelianModel pembelian = pembelianService.getPembelianByIdPembelian(idPembelian);

        List<PembelianBarangModel> listPembelianBarang = pembelian.getListPembelianBarang();
        List<BarangModel> listBarang = barangService.getBarangList();
        List<BarangModel> listBarangConfirm = new ArrayList<>();

        for(int i=0;i<listPembelianBarang.size();i++){
            for(int j=0;j<listBarang.size();j++){
                if(listBarang.get(j).getKode_barang().equals(listPembelianBarang.get(i).getBarang().getKode_barang())){
                    listBarang.get(j).setStok(listBarang.get(j).getStok() + listPembelianBarang.get(i).getQuantity());
                    listBarangConfirm.add(listBarang.get(j));
                }
            }
        }
        String hapusConfirm ="dan stok barang ";
        for(int i=0;i<listBarangConfirm.size();i++){
            if(i == listBarangConfirm.size()-1){
                hapusConfirm+= "dan "+ listBarangConfirm.get(i).getNama_barang()+ " bertambah menjadi " + listBarangConfirm.get(i).getStok() + ".";
            }
            else{
                hapusConfirm+= listBarangConfirm.get(i).getNama_barang() + " bertambah menjadi " + listBarang.get(i).getStok() + ",";
            }
        }

        if(listBarangConfirm.size()!=0){
            model.addAttribute("hapusConfirm", hapusConfirm);
        }
        model.addAttribute("no_invoice",pembelian.getNo_invoice());
        pembelianService.hapusPembelian(pembelian);
        return "delete-pembelian";
    }

    @GetMapping("/cari/pembelian")
    public String cariBarangForm(
            @RequestParam (value = "idMember" , required = false) Long idMember,
            @RequestParam (value = "isCicilan", required = false) Long isCicilan,
            Model model
    ){
        List<MemberModel> listMember = memberService.getMemberList();
        List<PembelianModel> listPembelian = new ArrayList<>();
        if(idMember != null){
            MemberModel member = memberService.getMemberByIdMember(idMember);
            listPembelian = pembelianService.getPembelianByMember(member);
        }
        if(isCicilan != null){
            if(isCicilan == 0){
                for(int i=0; i<listPembelian.size();i++){
                    if(listPembelian.get(i).getIs_cash() == Boolean.FALSE){
                        listPembelian.remove(listPembelian.get(i));
                    }else{

                    }
                }
            } else{
                for(int i=0;i<listPembelian.size();i++){
                    if(listPembelian.get(i).getIs_cash()){
                        listPembelian.remove(listPembelian.get(i));
                    }
                    else{

                    }
                }
            }

        }

        model.addAttribute("listMember", listMember);
        model.addAttribute("listPembelian",listPembelian);
        return "cari-pembelian";
    }

//    @GetMapping("/pembelian/tambah")
//    public String tambahPembelianForm(Model model){
//        PembelianModel pembelian = new PembelianModel();
//
//        List<MemberModel> listMember = memberService.getMemberList();
//        List<BarangModel> listBarangAvb = new ArrayList<BarangModel>();
//        List<BarangModel> listBarangSemua = barangService.getBarangList();
//        for(int i=0;i<listBarangSemua.size();i++){
//            if(listBarangSemua.get(i).getStok()>0){
//                listBarangAvb.add(listBarangSemua.get(i));
//            }
//        }
//
//        model.addAttribute("listMember", listMember);
//        model.addAttribute("listBarangAvb", listBarangAvb);
//        return "form-add-pembelian";
//    }
//
//    @PostMapping("/barang/tambah")
//    public String tambahBarangSubmit(
//            @ModelAttribute BarangModel barang,
//            Model model
//    ){
//        barangService.addBarang(barang);
//        model.addAttribute("kode_barang", barang.getKode_barang());
//        return "add-barang";
//    }
}
