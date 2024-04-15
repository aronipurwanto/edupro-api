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
public class KelasId implements Serializable {
	private static final long serialVersionUID = -6253672174128396247L;

	@Column(name = "LBGID", nullable = false)
	private Integer idLembaga;
	
	@Column(name = "TAID", nullable = false)
	private Integer tahunAjaranId;
	
	@Column(name = "KLSKD", length = 10, nullable = false)
	private String kode;
}
