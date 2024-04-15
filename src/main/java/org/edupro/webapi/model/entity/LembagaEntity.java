/**
 * 
 */
package org.edupro.webapi.model.entity;

import java.time.LocalDate;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.TableGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author Awiyanto Ajisasongko
 *
 * Oct 7, 2023
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "T_LEMBAGA")
@AttributeOverrides({
	@AttributeOverride(name = "createdAt", column = @Column(name="LBGCRD")),
	@AttributeOverride(name = "createdBy", column = @Column(name="LBGCRUID")),
	@AttributeOverride(name = "updatedAt", column = @Column(name="LBGUPD")),
	@AttributeOverride(name = "updatedBy", column = @Column(name="LBGUPUID")),
	@AttributeOverride(name = "status", column = @Column(name="LBSTAT"))
})
public class LembagaEntity extends BaseEntity {
	
	@Id
	@Column(name = "LBGID")
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "LEMBAGA")
	@TableGenerator(name = "LEMBAGA", table = "T_SEQUENCE", pkColumnName = "SEQ_NAME", pkColumnValue = "LEMBAGA", valueColumnName = "SEQ_VAL", allocationSize = 1, initialValue = 1)
	private Integer id;
	
	@Column(name = "LBGNM", length = 100, nullable = false)
	private String nama;
	
	@Column(name = "LBGNMSHORT", length = 50)
	private String namaSingkat;
	
	/**
	 * NIS/NSS/NDS
	 */
	@Column(name = "LBGNOINDUK", length = 100)
	private String nomorInduk;
	
	/**
	 * Kode Sekolah / NPSN
	 */
	@Column(name = "LBGKD", length = 100)
	private String kode;
	
	@Column(name = "LBGKDEXPD")
	private LocalDate tanggalAkhirMasaBerlaku;
	
	@Column(name = "LBGJENJANGKD", length = 20)
	private String kodeJenjangKategori;
	
	@Column(name = "LBGPIMP", length = 100)
	private String kepala;
	
	@Column(name = "LBGNOUNIK", length = 100)
	private String nomorUnik;
	
	@Column(name = "LBGADMIN", length = 100)
	private String admin;
	
	@Column(name = "LBGMAXEXAMUSER")
	private int maxUserUjian;
	
	@Column(name = "LBGMAXLMSUSER")
	private int maxUserLMS;
	
	@Column(name = "LBGSELISIHJAM")
	private int selisihJamDenganServer;
	
	@Column(name = "LBGHARISEKOLAH")
	private int hariEfektifSekolah;
	
	@Column(name = "LBGMASUKAWAL", length = 5)
	private String masukAwal;
	@Column(name = "LBGMASUKAKHIR", length = 5)
	private String masukAkhir;
	@Column(name = "LBGPULANGAWAL", length = 5)
	private String pulangAwal;
	@Column(name = "LBGPULANGAKHIR", length = 5)
	private String pulahgAkhir;
	
	@Column(name = "LBGPROVKD", length = 20)
	private String kodeProvinsi;
	@Column(name = "LBGKOTAKD", length = 20)
	private String kodeKota;
	@Column(name = "LBGKECKD", length = 20)
	private String kodeKecamatan;
	@Column(name = "LBGKELKD", length = 20)
	private String kodeKelurahan;
	
	
	@Column(name = "LBGADDR", length = 255)
	private String alamat;
	@Column(name = "LBGKDPOS", length = 6)
	private String kodePos;
	@Column(name = "LBGPHONE", length = 50)
	private String telpon;
	@Column(name = "LBGFAX", length = 20)
	private String fax;
	@Column(name = "LBGWEBSITE", length = 100)
	private String website;
	@Column(name = "LBGEMAIL", length = 100)
	private String email;
	
	@Column(name = "LBGATTIDKOP")
	private Long kop;
	@Column(name = "LBGATTIDTTDPIMP")
	private Long ttdPimpinan;
	@Column(name = "LBGATTIDLOGODINAS")
	private Long logoDinas;
	@Column(name = "LBGATTIDLOGOLEMBAGA")
	private Long logoLembaga;
	@Column(name = "LBGATTIDSTEMPEL")
	private Long stempel;
}
