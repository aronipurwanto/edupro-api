/**
 * 
 */
package org.edupro.webapi.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.edupro.webapi.constant.DataStatus;

import java.time.LocalDate;

/**
 * Data semester
 * 
 * @author Awiyanto Ajisasongko
 *
 * Nov 30, 2023
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "T_SESI_AKADEMIK")
@AttributeOverrides({
		@AttributeOverride(name = "id", column = @Column(name="SAID")),
		@AttributeOverride(name = "createdAt", column = @Column(name="SACRD")),
		@AttributeOverride(name = "createdBy", column = @Column(name="SACRUID")),
		@AttributeOverride(name = "updatedAt", column = @Column(name="SAUPD")),
		@AttributeOverride(name = "updatedBy", column = @Column(name="SAUPUID")),
		@AttributeOverride(name = "status", column = @Column(name="SASTAT"))
})
public class SesiAkademikEntity extends BaseIdEntity {
	private static final long serialVersionUID = -9042433341420102754L;
	@Id
	@Column(name = "SAID", length = 36, nullable = false)
	private String id;

	@Column(name = "TAID", nullable = false)
	private String tahunAjaranId;

	@ManyToOne
	@JoinColumn(name = "TAID",insertable = false, updatable = false)
	private TahunAjaranEntity tahunAjaran;

	@Column(name = "KURID", length = 35, nullable = false)
	private String kurikulumId;
	
	@Column(name = "KURKD", length = 20)
	private String kodeKurikulum;

	@ManyToOne
	@JoinColumn(name = "KURID", insertable = false, updatable = false)
	private KurikulumEntity kurikulum;

	@Column(name = "SAURUT", nullable = false)
	private Integer urut; // 1 = ganjil, 2 = genap

	@Column(name = "SASTARTDATE")
	private LocalDate startDate;

	@Column(name = "SAENDDATE")
	private LocalDate endDate;
}
