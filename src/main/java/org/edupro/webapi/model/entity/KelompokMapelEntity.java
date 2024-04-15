/**
 * 
 */
package org.edupro.webapi.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
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
@Table(name = "T_MAPELKEL")
public class KelompokMapelEntity extends BlankBaseEntity {
	private static final long serialVersionUID = 6664606117330350877L;

	@EmbeddedId
	private KelompokMapelId id;
	
	@Column(name = "MKELNM", length = 100, nullable = false)
	private String nama;
	
	@Default
	@Column(name = "MKELSTAT", length = 20, nullable = false)
	@Enumerated(EnumType.STRING)
	private DataStatus status = DataStatus.AKTIF;
}
