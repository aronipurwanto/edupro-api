/**
 * 
 */
package org.edupro.webapi.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.edupro.webapi.constant.DataStatus;
import org.edupro.webapi.util.CommonUtil;
import org.hibernate.annotations.SoftDelete;
import org.hibernate.annotations.SoftDeleteType;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

/**
 * @author Awiyanto Ajisasongko
 *
 * Aug 24, 2021
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
@SoftDelete(strategy = SoftDeleteType.DELETED)
public abstract class BaseIdEntity {
	@Id
	@Column(name = "ID", nullable = false, length = 36)
	private String id;

	@Column(name = "CRUID", length = 100)
	@CreatedBy
	private String createdBy;
	
	@Column(name = "UPUID", length = 100)
	@LastModifiedBy
	private String updatedBy;
	
	@Column(name = "DELUID", length = 100)
	private String deletedBy;
	
	@Column(name = "CRD")
	@CreatedDate
	private LocalDateTime createdAt;
	
	@Column(name = "UPD")
	@LastModifiedDate
	private LocalDateTime updatedAt;
	
	@Column(name = "DELD")
	private LocalDateTime deletedAt;

	@Column(name = "STAT", length = 20, nullable = false)
	@Enumerated(EnumType.STRING)
	private DataStatus status = DataStatus.AKTIF;

	@PrePersist
	public void onCreate(){
		this.createdAt = LocalDateTime.now();
		this.updatedAt = LocalDateTime.now();
		this.id = CommonUtil.getUUID();
		this.status = DataStatus.AKTIF;
	}

	@PreUpdate
	public void onUpdate(){
		this.updatedAt = LocalDateTime.now();
	}

	@PreRemove
	public void onRemove(){
		this.deletedAt = LocalDateTime.now();
		this.status = DataStatus.DIHAPUS;
	}
}
