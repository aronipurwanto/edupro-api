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
@AttributeOverrides({
		@AttributeOverride(name = "id", column = @Column(name="RSCID")),
		@AttributeOverride(name = "createdAt", column = @Column(name="RSCCRD")),
		@AttributeOverride(name = "createdBy", column = @Column(name="RSCCRUID")),
		@AttributeOverride(name = "updatedAt", column = @Column(name="RSCUPD")),
		@AttributeOverride(name = "updatedBy", column = @Column(name="RSCUPUID")),
		@AttributeOverride(name = "status", column = @Column(name="RSCSTAT"))
})
public class ResourceEntity extends BaseIdEntity {
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
