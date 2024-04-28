/**
 * 
 */
package org.edupro.webapi.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

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
@Table(name = "T_KURIKULUM")
@AttributeOverrides({
	@AttributeOverride(name = "id", column = @Column(name="KURID")),
	@AttributeOverride(name = "createdAt", column = @Column(name="KURCRD")),
	@AttributeOverride(name = "createdBy", column = @Column(name="KURCRUID")),
	@AttributeOverride(name = "updatedAt", column = @Column(name="KURUPD")),
	@AttributeOverride(name = "updatedBy", column = @Column(name="KURUPUID"))
})
public class KurikulumEntity extends BaseIdEntity {
	@Column(name = "KURKD", length = 20)
	private String kode;
	
	@Column(name = "KURNM", length = 100)
	private String nama;

	@Column(name = "NOURUT")
	private Integer noUrut;

	@OneToMany(mappedBy = "kurikulum")
	private List<SesiAkademikEntity> sesiAkademikList = new ArrayList<>();

	public KurikulumEntity(String kode, String nama, Integer noUrut) {
		this.kode = kode;
		this.nama = nama;
		this.noUrut = noUrut;
	}
}
