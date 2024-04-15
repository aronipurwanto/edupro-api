/**
 * 
 */
package org.edupro.webapi.model.entity;

import jakarta.persistence.EmbeddedId;
import lombok.Builder;
import lombok.Data;

/**
 * @author Awiyanto Ajisasongko
 *
 * 6 Feb 2024
 */
@Data
@Builder
//@Entity
public class ResourceFile {
	@EmbeddedId
	private ResourceFileId id;
}
