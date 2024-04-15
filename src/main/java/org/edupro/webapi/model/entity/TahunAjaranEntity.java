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

/**
 * @author Awiyanto Ajisasongko
 *
 * Oct 7, 2023
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "T_TAHUNAJARAN")
@AttributeOverrides({
		@AttributeOverride(name = "createdAt", column = @Column(name="TACRD")),
		@AttributeOverride(name = "createdBy", column = @Column(name="TACRUID")),
		@AttributeOverride(name = "updatedAt", column = @Column(name="TAUPD")),
		@AttributeOverride(name = "updatedBy", column = @Column(name="TAUPUID")),
		@AttributeOverride(name = "status", column = @Column(name="TASTAT"))
})
public class TahunAjaranEntity extends BaseEntity {
	private static final long serialVersionUID = 6154593684680418364L;
	
	@Id
	@Column(name = "TAID", nullable = false)
	private String id;
	
	@Column(name = "TANM", length = 50, nullable = false)
	private String nama;
	
	@Column(name = "KURKD", length = 20, nullable = false)
	private String kodeKurikulum;
}
