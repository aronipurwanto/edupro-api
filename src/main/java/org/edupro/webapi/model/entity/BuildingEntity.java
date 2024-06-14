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

import java.util.ArrayList;
import java.util.List;

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
@Table(name = "t_building")
public class BuildingEntity extends BaseIdEntity {
	@Column(name = "code", length = 20, nullable = false)
	private String code;
	
	@Column(name = "name", length = 50, nullable = false)
	private String name;

	@OneToMany(mappedBy = "gedungEntity")
	private List<RoomEntity> ruanganList = new ArrayList<>();
}
