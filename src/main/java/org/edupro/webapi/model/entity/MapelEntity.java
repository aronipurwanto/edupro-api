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
@Table(name = "T_MAPEL")
@AttributeOverrides({
		@AttributeOverride(name = "id", column = @Column(name="MAPELID")),
		@AttributeOverride(name = "createdAt", column = @Column(name="MAPELCRD")),
		@AttributeOverride(name = "createdBy", column = @Column(name="MAPELCRUID")),
		@AttributeOverride(name = "updatedAt", column = @Column(name="MAPELUPD")),
		@AttributeOverride(name = "updatedBy", column = @Column(name="MAPELUPUID")),
		@AttributeOverride(name = "status", column = @Column(name="MAPELSTAT"))
})
public class MapelEntity extends BaseIdEntity {
	@Column(name = "MAPELKD", length = 10, nullable = false)
	private String kode;
	
	@Column(name = "MAPELNM", length = 100, nullable = false)
	private String nama;

	@Builder.Default
	@Column(name = "KURSTAT", length = 20)
	@Enumerated(EnumType.STRING)
	private DataStatus status = DataStatus.AKTIF;
}
