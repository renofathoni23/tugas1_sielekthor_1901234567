package ti1.apap.sielekthor.service;

import ti1.apap.sielekthor.model.MemberModel;
import ti1.apap.sielekthor.model.PembelianModel;
import ti1.apap.sielekthor.repository.PembelianDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.Date;
import java.util.Random;

@Service
@Transactional

public class PembelianServiceImpl implements PembelianService{

    public static final int NUM = 31;
    @Autowired
    PembelianDb pembelianDb;

    @Override
    public List<PembelianModel> getPembelianList(){
        return pembelianDb.findAll();
    }

    @Override
    public PembelianModel getPembelianByIdPembelian(Long idPembelian){
        Optional <PembelianModel> pembelian = pembelianDb.findByIdPembelian(idPembelian);
        if(pembelian.isPresent()){
            return pembelian.get();
        }
        return null;
    }

    @Override
    public String generateInvoice (PembelianModel pembelian){

        String no_invoice="";

        String namaDepanMember = String.valueOf(pembelian.getMember().getNama_member().charAt(0) & NUM);
        int urutan = Integer.parseInt(namaDepanMember);
        if(urutan > 9){
            String urutanLebih = String.valueOf(urutan);
            String nomorPertama = String.valueOf(urutanLebih.charAt(0));
            no_invoice+= nomorPertama;
        }
        else{
            String urutanPertama = String.valueOf(urutan);
            no_invoice+=urutanPertama;
        }


        String namaAdmin = String.valueOf(pembelian.getNama_admin().charAt(pembelian.getNama_admin().length()-1));
        no_invoice+=namaAdmin;

        Date tanggal_pembelian = pembelian.getTanggal_pembelian();
        DateFormat dateFormat = new SimpleDateFormat("ddmm");
        String date_pembelian = dateFormat.format(tanggal_pembelian);
        no_invoice+=date_pembelian;

        boolean pembayaran = pembelian.getIs_cash();
        if(pembayaran){
            no_invoice+= "02";
        }
        else{
            no_invoice+="01";
        }

        DateFormat hari = new SimpleDateFormat("dd");
        DateFormat bulan = new SimpleDateFormat("mm");
        String hariString = dateFormat.format(hari);
        String bulanString = dateFormat.format(bulan);
        int hariint = Integer.parseInt(hariString);
        int bulanint = Integer.parseInt(bulanString);
        int hitung = (hariint + bulanint) * 5;
        if(hitung < 100){
            String inputharibulan = "0"+String.valueOf(hitung);
            no_invoice+=inputharibulan;
        }
        else{
            String inputbulanhari = String.valueOf(hitung);
            no_invoice+=inputbulanhari;
        }

        Random rnd = new Random();
        char c1 = (char) ('a' + rnd.nextInt(26));
        char c2 = (char) ('a' + rnd.nextInt(26));

        String char1 = String.valueOf(c1);
        String char2 = String.valueOf(c2);

        no_invoice+= c1;
        no_invoice+= c1;

        return no_invoice;

    }

    @Override
    public void hapusPembelian (PembelianModel pembelian){
        pembelianDb.delete(pembelian);
    }

    @Override
    public List <PembelianModel> getPembelianByMember (MemberModel member){
        return pembelianDb.findPembelianByMember(member);
    }

}
