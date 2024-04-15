/**
 * 
 */
package org.edupro.webapi.model.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Awiyanto Ajisasongko
 *
 * Nov 30, 2023
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Embeddable
public class KurikulumMapelId implements Serializable {
	private static final long serialVersionUID = -7109210833664328268L;

	@Column(name = "LBGID", nullable = false)
	private Integer idLembaga;

	@Column(name = "KURKD", length = 20)
	private String kodeKurikulum;

	@Column(name = "MAPELKD", length = 10, nullable = false)
	private String kode;
}
