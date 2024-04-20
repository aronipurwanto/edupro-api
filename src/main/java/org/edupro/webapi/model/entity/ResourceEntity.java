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
public class ResourceEntity extends BaseEntity {
	@Id
	@Column(name = "RSRCID", nullable = false, length = 36)
	private String id;
	
	/**
	 * Mengacu pada google classroom, jenis resource ini contohnya: assignment, quiz, question, material
	 */
	@Column(name = "RSRCTPE", nullable = false)
	private Integer type;
	
	@Column(name = "RSRCNM", nullable = false)
	private String name;

	@Column(name = "ATTCHID")
	private String attachmentId;

	@ManyToOne
	@JoinColumn(name = "ATTCHID", insertable = false, updatable = false)
	private AttachmentEntity attachment;
}
