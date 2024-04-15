/**
 * 
 */
package org.edupro.webapi.model.entity;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Detail mata pelajaran per kurikulum
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
@Table(name = "T_KURIKULUM_MAPEL")
@AttributeOverrides({
		@AttributeOverride(name = "createdAt", column = @Column(name="KMAPELCRD")),
		@AttributeOverride(name = "createdBy", column = @Column(name="KMAPELCRUID")),
		@AttributeOverride(name = "updatedAt", column = @Column(name="KMAPELUPD")),
		@AttributeOverride(name = "updatedBy", column = @Column(name="KMAPELUPUID")),
		@AttributeOverride(name = "status", column = @Column(name="KMAPELSTAT"))
})
public class KurikulumMapel extends BaseEntity {
	
	@EmbeddedId
	private KurikulumMapelId id;

	@Column(name = "KMAPELKD", length = 10)
	private String kodeKelompokMapel;
}
