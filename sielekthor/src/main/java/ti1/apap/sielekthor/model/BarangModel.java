package ti1.apap.sielekthor.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name="barang")

public class BarangModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idBarang;

    @NotNull
    @Size(max=255)
    @Column(name="nama_barang", nullable = false)
    private String nama_barang;

    @NotNull
    @Column(name="stok_barang", nullable = false)
    private int stok;

    @NotNull
    @Column(name="jumlah_garansi", nullable = false)
    private int jumlah_garansi;

    @NotNull
    @Size(max=255)
    @Column(name="deskripsi_barang",nullable = false)
    private String deskripsi_barang;

    @NotNull
    @Size(max=255)
    @Column(name="kode_barang",nullable = false)
    private String kode_barang;

    @NotNull
    @Size(max=255)
    @Column(name="merk_barang",nullable = false)
    private String merk_barang;

    @NotNull
    @Column(name="harga_barang", nullable = false)
    private int harga_barang;


    //Relasi dengan Tipe
    @ManyToOne (fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "idTipe", referencedColumnName = "idTipe", nullable = false)
    @OnDelete( action = OnDeleteAction.CASCADE)
    private TipeModel tipe;

    //Relasi dengan PembelianBarang
    @OneToMany(mappedBy = "barang", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<PembelianBarangModel> listPembelianBarang;

//    //Relasi dengan Pembelian Barang
//    @ManyToOne (fetch = FetchType.EAGER, optional = false)
//    @JoinColumn(name = "idPembelianBarang", referencedColumnName = "idPembelianBarang", nullable = false)
//    @OnDelete( action = OnDeleteAction.CASCADE)
//    private PembelianBarangModel pembelianbarang;

}
