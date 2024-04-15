/**
 * 
 */
package org.edupro.webapi.model.entity;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
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
@Table(name = "T_RUANGAN")
@AttributeOverrides({
	@AttributeOverride(name = "createdAt", column = @Column(name="RUANGCRD")),
	@AttributeOverride(name = "createdBy", column = @Column(name="RUANGCRUID")),
	@AttributeOverride(name = "updatedAt", column = @Column(name="RUANGUPD")),
	@AttributeOverride(name = "updatedBy", column = @Column(name="RUANGUPUID")),
	@AttributeOverride(name = "status", column = @Column(name="RUANGSTAT"))
})
public class RuanganEntity extends BaseEntity {
	
	@Id
	@Column(name = "RUANGID", length = 36, nullable = false)
	private String id;

	@Column(name = "RUANGKD", length = 20, nullable = false)
	private String kode;
	
	@Column(name = "RUANGNM", length = 50, nullable = false)
	private String nama;
	
	@Column(name = "RUANGKAP")
	private Integer kapasitas;

	@Column(name = "GDKD", length = 20, nullable = false)
	private String kodeGedung;

	@ManyToOne
	@JoinColumn(name = "GDKD", insertable = false, updatable = false)
	private GedungEntity gedungEntity;
}
