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

/**
 * Jika di google classroom, resource ini mirip seperti classwork
 * 
 * @author Awiyanto Ajisasongko
 *
 * 6 Feb 2024
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "T_RESOURCE")
public class Resource extends BaseEntity {
	@Id
	@Column(name = "RSRCID", nullable = false)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "RESOURCE")
	@TableGenerator(name = "RESOURCE", table = "T_SEQUENCE", pkColumnName = "SEQ_NAME", pkColumnValue = "RESOURCE", valueColumnName = "SEQ_VAL", allocationSize = 1, initialValue = 1)
	private Long id;
	
	/**
	 * Mengacu pada google classroom, jenis resource ini contohnya: assignment, quiz, question, material
	 */
	@Column(name = "RSRCTPE", nullable = false)
	private Integer type;
	
	@Column(name = "RSRCNM", nullable = false)
	private String name;
}
