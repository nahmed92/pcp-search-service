package com.deltadental.pcp.search.controller;

import com.deltadental.pcp.search.domain.Facility;
import com.deltadental.pcp.search.service.PCPSearchService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
public class FacilitySearchControllerTest {

    @InjectMocks
    FacilitySearchController controller;

    @Mock
    PCPSearchService mockPCPSearchService;

    @Test
    public void testFacilitySearch_success(){
        String facilityId = "F001";
        Facility response = Facility.builder().build();
        Mockito.when(mockPCPSearchService.searchFacility(facilityId)).thenReturn(response);
        ResponseEntity expectedResponse = controller.facilitySearch(facilityId);

        Assertions.assertEquals(expectedResponse.getStatusCode(), HttpStatus.OK);

    }


}
