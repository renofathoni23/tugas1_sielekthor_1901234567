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
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name="pembelianbarang")

public class PembelianBarangModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPembelianBarang;

    @NotNull
    @Column(nullable = false)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate tanggalGaransi;

    @NotNull
    @Column(name="quantity", nullable = false)
    private int quantity;

//    //Relasi dengan Barang
//    @OneToMany(mappedBy = "pembelianbarang", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    List<BarangModel> listBarang;
//
//    //Relasi dengan Pembelian
//    @OneToMany(mappedBy = "pembelianbarang", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    List<PembelianModel> listPembelian;

    //Relasi dengan Barang
    @ManyToOne (fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "idBarang", referencedColumnName = "idBarang", nullable = false)
    @OnDelete( action = OnDeleteAction.CASCADE)
    private BarangModel barang;

    //Relasi dengan Pembelian
    @ManyToOne (fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "idPembelian", referencedColumnName = "idPembelian", nullable = false)
    @OnDelete( action = OnDeleteAction.CASCADE)
    private PembelianModel pembelian;



}
