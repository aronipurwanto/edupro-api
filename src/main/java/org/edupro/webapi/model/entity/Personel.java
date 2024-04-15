/**
 * 
 */
package org.edupro.webapi.model.entity;

import java.time.LocalDate;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.TableGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

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
@Table(name = "T_PERSONEL")
@AttributeOverrides({
	@AttributeOverride(name = "createdAt", column = @Column(name="PERSONCRD")),
	@AttributeOverride(name = "createdBy", column = @Column(name="PERSONCRUID")),
	@AttributeOverride(name = "updatedAt", column = @Column(name="PERSONUPD")),
	@AttributeOverride(name = "updatedBy", column = @Column(name="PERSONUPUID"))
})
public class Personel extends BaseEntity {

	@Id
	@Column(name = "PERSONID", length = 36, nullable = false)
	private String id;

	@Column(name = "PERSONUID", length = 100)
	private String userId;

	@Column(name = "PERSONNO", length = 50, nullable = false)
	private String nomor;
	
	@Column(name = "PERSONNM", length = 100, nullable = false)
	private String nama;
	
	@Column(name = "PERSONADDR", length = 255)
	private String alamatTinggal;
	
	@Column(name = "PERSONNIK", length = 50)
	private String nik; // NIK KTP
	
	@Column(name = "PERSONDOB")
	private LocalDate tanggalLahir;
}
