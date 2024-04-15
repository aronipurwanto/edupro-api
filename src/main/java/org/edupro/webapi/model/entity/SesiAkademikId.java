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
public class SesiAkademikId implements Serializable {
	private static final long serialVersionUID = 6633650732514656422L;
	
	@Column(name = "TAID", nullable = false)
	private Integer tahunPelajaran;
	
	@Column(name = "SAURUT", nullable = false)
	private Integer urut; // 1 = ganjil, 2 = genap
}
