package com.deltadental.pcp.search.pd.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.deltadental.pcp.search.pd.entities.FacilitySearchEntity;

@Repository
public interface FacilitySearchRepo extends JpaRepository<FacilitySearchEntity, Integer>{
	
	static String CASE_CONSTANT = "CASE";
	static String END_CONSTANT = "END";
	
	final String FACILITY_SEARCH_QUERY = "SELECT DISTINCT "
			+ "    ROWNUM row_number, "
			+ "    facility_id, "
			+ "    address, "			
			+ "    phone_number, "
			+ "    provider_specialty_desc, "
			+ "    contracted, "
			+ "    group_practice_name, "
			+ "    provider_specialty, "
			+ "    effective_date, "
			+ "    facility_status, "
			+ "    enrollstatus, "
			+ "    provider_languages "
			+ "FROM  (  SELECT DISTINCT "
			+ "            a.master_contract_id AS facility_id, "
			+ "            nvl(a.rendering_address1,'')"
			+ "            || nvl(a.rendering_address2,'')"
			+ "            || nvl(a.rendering_address3,'')"
			+ "            || ', '"
			+ "            || a.rendering_city"
			+ "            || ', '"
			+ "            || a.rendering_state"
			+ "            || ' '"
			+ "            || a.rendering_zip address, "
			+ "            DECODE(length(a.practice_location_phone),10,'(' "
			+ "            || substr(a.practice_location_phone,1,3) "
			+ "            || ')' "
			+ "            || substr(a.practice_location_phone,4,3) "
			+ "            || '-' "
			+ "            || substr(a.practice_location_phone,7,4),7,substr(a.practice_location_phone,1,3) "
			+ "            || '-' "
			+ "            || substr(a.practice_location_phone,4,4) ) phone_number, "
			+ CASE_CONSTANT               
			+ "                    WHEN provider_type = 'D' THEN DECODE(provider_specialty,'00','General Dentist','15','Endodontist','40','Periodontist','10','Oral Surgeon' "
			+ ",'20','Orthodontist','30','Pedodontist','Specialist') "
			+ "                    WHEN provider_type = 'V' THEN 'Vision' "
			+ END_CONSTANT
			+ "            AS provider_specialty_desc, "
			+ "            DECODE(a.par_status,'P','Yes','No') contracted, "
			+ "            a.provider_lastname group_practice_name, "
			+ "            a.provider_specialty, "
			+ "            'NA' AS effective_date, "
			+ CASE_CONSTANT
			+ "                    WHEN a.future_facility = 'Y' THEN 'INACTIVE' "
			+ "                    ELSE 'ACTIVE' "
			+ END_CONSTANT
			+ "            AS facility_status, "
			+ CASE_CONSTANT
			+ "                    WHEN a.network_association = '30' THEN '' "
			+ "                    ELSE DECODE(nvl(a.accepts_new_patients,'Y'),'Y','OPEN','N','CLOSED','Unknown') "
			+ END_CONSTANT
			+ "            AS enrollstatus, "
			+ "            lng.provider_languages "
			+ "        FROM "
			+ "            ops$pd.mtv_providers a, "
			+ "            ops$pd.pcp_prov_languages lng "
			+ "        WHERE a.provider_key = lng.provider_key (+) "
			+ "            AND   a.master_contract_id LIKE 'DC%' "
			+ "            AND   upper(a.master_contract_id) LIKE upper(:facilityId) "
			+ "            || '%' "
			+ "            AND   a.provider_type = 'D' "
			+ "            AND   a.provider_specialty IN ( "
			+ "                '00' "
			+ "            ) "
			+ "            AND   ( "
			+ "                a.network_association IN ( "
			+ "                    '2DELTACARE' "
			+ "                ) "
			+ "                OR    a.network_association LIKE '2DC%' "
			+ "            ) "
			+ "    )";
	
	@Query(value = FACILITY_SEARCH_QUERY , nativeQuery = true)
	FacilitySearchEntity searchFacilityByFacilityId(@Param("facilityId") String facilityId);

}