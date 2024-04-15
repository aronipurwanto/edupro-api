/**
 * 
 */
package org.edupro.webapi.model.entity;

import java.io.Serializable;
import java.time.LocalDate;

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
public class PersonelInOutId implements Serializable {
	private static final long serialVersionUID = -5351040750696256831L;
	
	@Column(name = "PERSONID", nullable = false)
	private Integer idPersonel;
	
	@Column(name = "PINOTGL", nullable = false)
	private LocalDate tanggal;
}
