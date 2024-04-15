/**
 * 
 */
package org.edupro.webapi.model.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author Awiyanto Ajisasongko
 *
 * May 16, 2022
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "T_ATTCH")
public class Attachment extends BlankBaseEntity {
	private static final long serialVersionUID = 4672155540988374323L;
	
	@Id
	@Column(name = "ATTID")
	private Long id;
	
	@Column(name = "ATTCONTYPE", length = 100)
	private String contentType;
	
	@Column(name = "ATTFNAME", length = 100)
	private String filename;
	
	@Default
	@Column(name = "ATTPUBLIC")
	private boolean publiclyAccessible = true;
	
	//@Column(name = "ATTCONTENT")
	//private byte[] file;
	
	@Column(name = "ATTSIZE")
	private Long size;
	
	@Column(name = "ATTCRD")
	private LocalDateTime createdAt;
	
	@Column(name = "ATTCRUID", length = 100)
	private String createdBy;
	
	@Column(name = "ATTDESC", length = 255)
	private String description;
	
	@Column(name = "ATTOWNER", length = 100)
	private String owner;
}
