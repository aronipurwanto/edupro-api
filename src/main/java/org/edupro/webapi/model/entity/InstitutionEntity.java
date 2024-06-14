/**
 * 
 */
package org.edupro.webapi.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

/**
 * @author Awiyanto Ajisasongko
 *
 * Oct 7, 2023
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_institution")
public class InstitutionEntity extends BaseIdEntity {
	@Column(name = "name", length = 100, nullable = false)
	private String name;
	
	@Column(name = "short_name", length = 50)
	private String shortName;
	
	/**
	 * NIS/NSS/NDS
	 */
	@Column(name = "reg_number", length = 100)
	private String regNumber;
	
	/**
	 * Kode Sekolah / NPSN
	 */
	@Column(name = "code", length = 100)
	private String code;
	
	@Column(name = "expired_date")
	private LocalDate expiredDate;
	
	@Column(name = "level_category", length = 20)
	private String levelCategory;
	
	@Column(name = "head_master", length = 100)
	private String headmaster;
}
