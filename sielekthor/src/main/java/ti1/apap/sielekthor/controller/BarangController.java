package ti1.apap.sielekthor.controller;

import org.aspectj.weaver.tools.cache.CacheBacking;
import org.springframework.data.repository.query.Param;
import ti1.apap.sielekthor.model.BarangModel;
import ti1.apap.sielekthor.model.TipeModel;
import ti1.apap.sielekthor.service.BarangService;


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
public class BarangController {

    @Qualifier("barangServiceImpl")
    @Autowired
    private BarangService barangService;

    @Qualifier("tipeServiceImpl")
    @Autowired
    private TipeService tipeService;

    @GetMapping("/barang/tambah")
    public String tambahBarangForm(Model model){
        List<TipeModel> listTipe = tipeService.getListTipe();
        model.addAttribute("cabang", new BarangModel());
        model.addAttribute("listTipe", listTipe);
        return "form-add-barang";
    }

    @PostMapping("/barang/tambah")
    public String tambahBarangSubmit(
            @ModelAttribute BarangModel barang,
            Model model
    ){
        barangService.addBarang(barang);
        model.addAttribute("kode_barang", barang.getKode_barang());
        return "add-barang";
    }

    @GetMapping("/barang")
    public String listBarang(Model model){
        List<BarangModel> listBarang = barangService.getBarangList();
        model.addAttribute("listBarang",listBarang);
        return "viewall-barang";
    }

    @GetMapping("/barang/{idBarang}")
    public String lihatDetailBarang(
            @PathVariable Long idBarang,
            Model model
    ){
        BarangModel barang = barangService.getBarangByIdBarang(idBarang);
        model.addAttribute("barang", barang);

        return "view-barang";
    }

    @GetMapping("/barang/ubah/{idBarang}")
    public String ubahBarangForm(
            @PathVariable Long idBarang,
            Model model
    ){
        BarangModel barang = barangService.getBarangByIdBarang(idBarang);
        model.addAttribute("barang",barang);
        model.addAttribute("id_tipe_barang",barang.getTipe().getIdTipe());
        model.addAttribute("tipe_barang",barang.getTipe().getNama());
        return "form-update-barang";
    }

    @PostMapping("/barang/ubah")
    public String ubahBarangSubmit(
            @ModelAttribute BarangModel barang,
            Model model
    ){
        barangService.updateBarang(barang);
        model.addAttribute("kode_barang", barang.getKode_barang());
        return "update-barang";
    }

    @GetMapping("/barang/cari")
    public String cariBarangForm(
            @RequestParam (value = "idTipe" , required = false) Long idTipe,
            @RequestParam (value = "stok", required = false) Long stok,
            Model model
    ){
        List<TipeModel> listTipe = tipeService.getListTipe();
        List<BarangModel> listBarang = new ArrayList<>();
        if(idTipe != null){
            TipeModel tipe = tipeService.getTipeByIdTipe(idTipe);
            listBarang = barangService.getListBarangByTipe(tipe);
        }
        if(stok != null){
            if(stok != 0){
                for(int i=0; i<listBarang.size();i++){
                    if(listBarang.get(i).getStok() == 0){
                        listBarang.remove(listBarang.get(i));
                    }else{

                    }
                }
            } else{
                for(int i=0;i<listBarang.size();i++){
                    if(listBarang.get(i).getStok() !=0){
                        listBarang.remove(listBarang.get(i));
                    }
                    else{

                    }
                }
            }

        }

        model.addAttribute("listTipe", listTipe);
        model.addAttribute("listBarang",listBarang);
        return "cari-barang";
    }
}
