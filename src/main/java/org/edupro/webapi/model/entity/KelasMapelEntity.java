/**
 * 
 */
package org.edupro.webapi.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.edupro.webapi.constant.DataStatus;

import java.io.Serializable;

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
@Table(name = "T_KELMAPEL")
public class KelasMapelEntity implements Serializable {
	private static final long serialVersionUID = 6664606117330350877L;

	@Id
	@Column(name = "KELMAPELID", nullable = false, length = 36)
	private String id;

	@Column(name = "KLSID", nullable = false, length = 36)
	private String kelasId;

	@ManyToOne
	@JoinColumn(name = "KLSID", insertable = false, updatable = false)
	private ClassEntity kelas;
	
	@Column(name = "MAPELID", nullable = false, length = 36)
	private String mapelId;

	@ManyToOne
	@JoinColumn(name = "MAPELID", insertable = false, updatable = false)
	private SubjectEntity mapel;

	@Column(name = "SAMKKM")
	private double nilaiKKM;
	
	@Default
	@Column(name = "MKELSTAT", length = 20, nullable = false)
	@Enumerated(EnumType.STRING)
	private DataStatus status = DataStatus.AKTIF;
}
