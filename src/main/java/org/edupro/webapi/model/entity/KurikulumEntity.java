/**
 * 
 */
package org.edupro.webapi.model.entity;

import jakarta.persistence.*;
import org.edupro.webapi.constant.DataStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

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
	@AttributeOverride(name = "createdAt", column = @Column(name="KURCRD")),
	@AttributeOverride(name = "createdBy", column = @Column(name="KURCRUID")),
	@AttributeOverride(name = "updatedAt", column = @Column(name="KURUPD")),
	@AttributeOverride(name = "updatedBy", column = @Column(name="KURUPUID"))
})
public class KurikulumEntity extends BaseEntity {
	
	@Id
	@Column(name = "KURID", length = 36, nullable = false)
	private String id;

	@Column(name = "KURKD", length = 20)
	private String kode;
	
	@Column(name = "KURNM", length = 100)
	private String nama;

	@OneToMany(mappedBy = "kurikulum")
	private List<SesiAkademikEntity> sesiAkademikList = new ArrayList<>();
	
	@Default
	@Column(name = "KURSTAT", length = 20)
	@Enumerated(EnumType.STRING)
	private DataStatus status = DataStatus.AKTIF;
}
