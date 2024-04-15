/**
 * 
 */
package org.edupro.webapi.model.entity;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Awiyanto Ajisasongko
 *
 * 12 Mar 2024
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "T_NONMAPEL")
@AttributeOverrides({
		@AttributeOverride(name = "createdAt", column = @Column(name="NMAPELCRD")),
		@AttributeOverride(name = "createdBy", column = @Column(name="NMAPELCRUID")),
		@AttributeOverride(name = "updatedAt", column = @Column(name="NMAPELUPD")),
		@AttributeOverride(name = "updatedBy", column = @Column(name="NMAPELUPUID")),
		@AttributeOverride(name = "status", column = @Column(name="NMAPELSTAT"))
})
public class KegiatanNonMapel extends BaseEntity {
	@Id
	@Column(name = "NMAPELKD", length = 20, nullable = false)
	private String kode;

	@Column(name = "NMAPELNM", length = 100, nullable = false)
	private String nama;
	
	@Column(name = "NMAPELEKSKUL")
	private boolean eksKul;
}
