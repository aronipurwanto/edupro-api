/**
 * 
 */
package org.edupro.webapi.model.entity;

import java.io.Serializable;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Awiyanto Ajisasongko
 *
 * 6 Feb 2024
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "T_RESOURCE_FILE")
public class ResourceFileId implements Serializable {
	private static final long serialVersionUID = 9192379075241925570L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "RSRCID", nullable = false)
	private Long resourceId;
	
	@Column(name = "ATTCHID", nullable = false)
	private Long attachmentId;
}
