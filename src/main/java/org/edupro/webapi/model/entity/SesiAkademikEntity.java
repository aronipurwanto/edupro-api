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
 * Data semester
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
@Table(name = "T_SESI_AKADEMIK")
public class SesiAkademikEntity extends BlankBaseEntity {
	private static final long serialVersionUID = -9042433341420102754L;
	@EmbeddedId
	private SesiAkademikId id;
	
	@Column(name = "KURKD", length = 20)
	private String kodeKurikulum;

	@Builder.Default
	@Column(name = "KURSTAT", length = 20, nullable = false)
	@Enumerated(EnumType.STRING)
	private DataStatus status = DataStatus.AKTIF;
}
