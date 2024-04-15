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
@Table(name = "T_KELAS")
@AttributeOverrides({
		@AttributeOverride(name = "createdAt", column = @Column(name="KLSCRD")),
		@AttributeOverride(name = "createdBy", column = @Column(name="KLSCRUID")),
		@AttributeOverride(name = "updatedAt", column = @Column(name="KLSUPD")),
		@AttributeOverride(name = "updatedBy", column = @Column(name="KLSUPUID")),
		@AttributeOverride(name = "status", column = @Column(name="KLSSTAT"))
})
public class KelasEntity extends BaseEntity {

	@EmbeddedId
	private KelasId id;
	
	@Column(name = "KLSNM", length = 20, nullable = false)
	private String nama;
	
	@Column(name = "RUANGKD", length = 20)
	private String kodeRuangan;
}
