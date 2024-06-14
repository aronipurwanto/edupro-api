/**
 * 
 */
package org.edupro.webapi.model.entity;

import jakarta.persistence.*;
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
@Table(name = "t_room")
public class RoomEntity extends BaseIdEntity {
	@Column(name = "code", length = 20, nullable = false)
	private String code;
	
	@Column(name = "name", length = 50, nullable = false)
	private String name;
	
	@Column(name = "capacity")
	private Integer capacity;

	@Column(name = "building_id", length = 36, nullable = false)
	private String buildingId;

	@ManyToOne
	@JoinColumn(name = "building_id", insertable = false, updatable = false)
	private BuildingEntity buildingEntity;
}
