package com.deltadental.pcp.search.controller;

import com.deltadental.pcp.search.domain.PCPAssignmentResponse;
import com.deltadental.pcp.search.domain.PCPValidateRequest;
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
public class PCPValidateControllerTest {

    @InjectMocks
    PCPValidateController controller;

    @Mock
    PCPSearchService mockPCPSearchService;

    @Test
    public void testValidate_success(){
        PCPValidateRequest pcpValidateRequest = PCPValidateRequest.builder().build();
        PCPAssignmentResponse pcpAssignmentResponse = PCPAssignmentResponse.builder()
                .processStatusCode("Success")
                        .build();
        Mockito.when(mockPCPSearchService.validate(pcpValidateRequest)).thenReturn(pcpAssignmentResponse);
        ResponseEntity expectedResponse = controller.validate(pcpValidateRequest);
        Assertions.assertEquals(expectedResponse.getStatusCode(), HttpStatus.OK);

    }
}
