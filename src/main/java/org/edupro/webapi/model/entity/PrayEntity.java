/**
 * 
 */
package org.edupro.webapi.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

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
@Table(name = "t_pray")
public class PrayEntity extends BaseIdEntity {
	@Column(name = "code", length = 10, nullable = false)
	private String code;
	
	@Column(name = "name", length = 100, nullable = false)
	private String name;

	@Column(name = "types", length = 36, nullable = false)
	private String types;
}
