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
@Table(name = "T_GEDUNG")
@AttributeOverrides({
	@AttributeOverride(name = "createdAt", column = @Column(name="GDID")),
	@AttributeOverride(name = "createdBy", column = @Column(name="GDCRUID")),
	@AttributeOverride(name = "updatedAt", column = @Column(name="GDUPD")),
	@AttributeOverride(name = "updatedBy", column = @Column(name="GDUPUID")),
	@AttributeOverride(name = "status", column = @Column(name="GDSTAT"))
})
public class GedungEntity extends BaseIdEntity {
	@Column(name = "GDKD", length = 20, nullable = false)
	private String kode;
	
	@Column(name = "GDNM", length = 50, nullable = false)
	private String nama;

	@OneToMany(mappedBy = "gedungEntity")
	private List<RuanganEntity> ruanganList = new ArrayList<>();
}
