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
@Table(name = "T_PERSONEL_INOUT")
@AttributeOverrides({
	@AttributeOverride(name = "createdAt", column = @Column(name="PINOCRD")),
	@AttributeOverride(name = "createdBy", column = @Column(name="PINOCRUID")),
	@AttributeOverride(name = "updatedAt", column = @Column(name="PINOUPD")),
	@AttributeOverride(name = "updatedBy", column = @Column(name="PINOUPUID"))
})
public class PersonelInOut extends BaseEntity {

	@EmbeddedId
	private PersonelInOutId id;
	
	@Column(name = "PINOTPE", nullable = false)
	private String inOutType;
}
