/**
 * 
 */
package org.edupro.webapi.model.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
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
public class SesiAkademikMapelLevelPesertaId implements Serializable {
	private static final long serialVersionUID = 6633650732514656422L;
	
	@Column(name = "TAID", nullable = false)
	private Integer tahunPelajaran;
	
	@Column(name = "SAURUT", nullable = false)
	private Integer urut; // 1 = ganjil, 2 = genap

	@Column(name = "LVLKD", length = 10, nullable = false)
	private String kodeLevel;

	@Column(name = "MAPELKD", length=10, nullable = false)
	private String kodeMapel;

	@Default
	@Column(name = "PERSONID", nullable = false)
	private Integer pengampu = 0;
	
	@Default
	@Column(name = "SWID", nullable = false)
	private Long idSiswa = 0L;
}
