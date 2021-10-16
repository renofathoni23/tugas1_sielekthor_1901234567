package ti1.apap.sielekthor.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name="pembelian")

public class PembelianModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPembelian;

    @NotNull
    @Column(nullable = false)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date tanggal_pembelian;

    @NotNull
    @Column(name="total_pembelian", nullable = false)
    private int total;

    @NotNull
    @Size(max=255)
    @Column(name="nama_admin_pengelola", nullable = false)
    private String nama_admin;

    @NotNull
    @Size(max=255)
    @Column(name="no_invoice_pembelian", nullable = false, unique = true)
    private String no_invoice;

    @NotNull
    @Column(name="metode_pembayaran", nullable = false)
    private Boolean is_cash;

    //Relasi dengan Member
    @ManyToOne (fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "idMember", referencedColumnName = "idMember", nullable = false)
    @OnDelete( action = OnDeleteAction.CASCADE)
    private MemberModel member;

    //Relasi dengan PembelianBarang
    @OneToMany(mappedBy = "pembelian", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<PembelianBarangModel> listPembelianBarang;

//    //Relasi dengan Barang
//    @ManyToOne (fetch = FetchType.EAGER, optional = false)
//    @JoinColumn(name = "idPembelianBarang", referencedColumnName = "idPembelianBarang", nullable = false)
//    @OnDelete( action = OnDeleteAction.CASCADE)
//    private PembelianBarangModel pembelianbarang;

}
