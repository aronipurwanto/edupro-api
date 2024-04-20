/**
 * 
 */
package org.edupro.webapi.model.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * @author Awiyanto Ajisasongko
 *
 * 6 Feb 2024
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "T_ATTCH_RESOURCE")
public class AssignmentResourceEntity extends BlankBaseEntity {
	@Id
	@Column(name = "ATTCHRSCID", nullable = false, length = 36)
	private String id;

	@Column(name = "ATTCHID", nullable = false, length = 36)
	private String attachmentId;

	@ManyToOne
	@JoinColumn(name = "ATTCHID", insertable = false, updatable = false)
	private AttachmentEntity attachment;

	@Column(name = "INSTRUCTION")
	private String instruction;
}
