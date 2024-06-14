/**
 * 
 */
package org.edupro.webapi.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

/**
 * @author Awiyanto Ajisasongko
 *
 * Oct 7, 2023
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_institution_config")
public class InstitutionConfigEntity extends BaseIdEntity {
	
	@Column(name = "unique_number", length = 100)
	private String uniqueNumber;
	
	@Column(name = "admin_name", length = 100)
	private String adminName;
	
	@Column(name = "exam_quota")
	private int maxUserUjian;
	
	@Column(name = "max_lms_user")
	private int maxUserLMS;
	
	@Column(name = "diff_server_time")
	private int diffServerTime;
	
	@Column(name = "effective_days")
	private int effectiveDays;
	
	@Column(name = "started_day", length = 5)
	private String startedDay;

	@Column(name = "end_day", length = 5)
	private String endDay;

	@Column(name = "end_early", length = 5)
	private String endEarly;

	@Column(name = "endOfDay", length = 5)
	private String endOfDay;
	
	@Column(name = "province_id", length = 20)
	private String proviceId;

	@Column(name = "city_id", length = 20)
	private String cityId;

	@Column(name = "district_id", length = 20)
	private String districtId;

	@Column(name = "sub_district_id", length = 20)
	private String subDistrictId;
	
	@Column(name = "address", length = 255)
	private String address;

	@Column(name = "postal_code", length = 6)
	private String postalCode;

	@Column(name = "no_telp", length = 50)
	private String noTelp;

	@Column(name = "no_fax", length = 20)
	private String noFax;

	@Column(name = "website", length = 100)
	private String website;

	@Column(name = "email", length = 100)
	private String email;
	
	@Column(name = "letter_head")
	private Long letterHead;

	@Column(name = "head_signature")
	private Long headSignature;

	@Column(name = "service_logo")
	private Long serviceLogo;

	@Column(name = "institution_logo")
	private Long institutionLogo;

	@Column(name = "LBGATTIDSTEMPEL")
	private Long stamp;
}
