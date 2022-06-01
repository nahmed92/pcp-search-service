package com.deltadental.pcp.search.controller;

import com.deltadental.pcp.search.domain.PCPAssignmentResponse;
import com.deltadental.pcp.search.domain.ProvidersRequest;
import com.deltadental.pcp.search.domain.ProvidersResponse;
import com.deltadental.pcp.search.service.PCPSearchService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class SearchProvidersControllerTest {

    @InjectMocks
    SearchProvidersController controller;

    @Mock
    PCPSearchService mockPCPSearchService;

    @Mock
    ProvidersRequestValidator mockRequestValidator;

    @Test
    public void testSearchProviders_success(){
        ProvidersRequest providersRequest = ProvidersRequest.builder().build();//buildProviderRequest();
        Mockito.doNothing().when(mockRequestValidator).validateProvidersRequest(providersRequest);

        ProvidersResponse providersResponse = buildProviderResponse();
        Mockito.when(mockPCPSearchService.searchProviders(providersRequest)).thenReturn(providersResponse);
        ResponseEntity<ProvidersResponse> expectedResponse = controller.searchProviders(providersRequest);
        Mockito.verify(mockRequestValidator, times(1)).validateProvidersRequest(providersRequest);
        Assertions.assertEquals(expectedResponse.getStatusCode(), HttpStatus.OK);

    }

    @Test
    public void testSearchProviders_BADREQUEST(){
        ProvidersRequest providersRequest = ProvidersRequest.builder().build();//buildProviderRequest();
        Mockito.doNothing().when(mockRequestValidator).validateProvidersRequest(providersRequest);

        ProvidersResponse providersResponse = buildProviderResponseBasRequest();
        Mockito.when(mockPCPSearchService.searchProviders(providersRequest)).thenReturn(providersResponse);
        ResponseEntity<ProvidersResponse> expectedResponse = controller.searchProviders(providersRequest);
        Mockito.verify(mockRequestValidator, times(1)).validateProvidersRequest(providersRequest);
        Assertions.assertEquals(expectedResponse.getStatusCode(), HttpStatus.BAD_REQUEST);

    }

    @Test
    public void testSearchProviders_PcpAssignmentResponseNull(){
        ProvidersRequest providersRequest = ProvidersRequest.builder().build();//buildProviderRequest();
        Mockito.doNothing().when(mockRequestValidator).validateProvidersRequest(providersRequest);

        ProvidersResponse providersResponse = ProvidersResponse.builder().build();
        Mockito.when(mockPCPSearchService.searchProviders(providersRequest)).thenReturn(providersResponse);
        ResponseEntity<ProvidersResponse> expectedResponse = controller.searchProviders(providersRequest);
        Mockito.verify(mockRequestValidator, times(1)).validateProvidersRequest(providersRequest);
        Assertions.assertEquals(expectedResponse.getStatusCode(), HttpStatus.NOT_FOUND);

    }

    @Test
    public void testSearchProviders_ProvidersResponseNull(){
        ProvidersRequest providersRequest = ProvidersRequest.builder().build();//buildProviderRequest();
        Mockito.doNothing().when(mockRequestValidator).validateProvidersRequest(providersRequest);

        Mockito.when(mockPCPSearchService.searchProviders(providersRequest)).thenReturn(null);
        ResponseEntity<ProvidersResponse> expectedResponse = controller.searchProviders(providersRequest);
        Mockito.verify(mockRequestValidator, times(1)).validateProvidersRequest(providersRequest);
        Assertions.assertEquals(expectedResponse.getStatusCode(), HttpStatus.NOT_FOUND);

    }

    private ProvidersResponse buildProviderResponseBasRequest() {
        PCPAssignmentResponse response = PCPAssignmentResponse.builder().processStatusCode("Failed").build();
        return ProvidersResponse.builder()
                .pcpAssignmentResponse(response)
                .build();
    }

    private ProvidersResponse buildProviderResponse() {
        PCPAssignmentResponse response = PCPAssignmentResponse.builder().processStatusCode("Success").build();
        return ProvidersResponse.builder()
                .pcpAssignmentResponse(response)
                .build();
    }


}
