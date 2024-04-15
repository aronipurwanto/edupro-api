/**
 * 
 */
package org.edupro.webapi.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

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
@Table(name = "T_AGAMA")
public class Agama extends BlankBaseEntity {
	private static final long serialVersionUID = -4553528758918446978L;
	
	@Id
	@Column(name = "AGID", nullable = false)
	private Integer id;
	
	@Column(name = "AGNM", length = 50)
	private String nama;
}
