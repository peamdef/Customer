package com.digitalacademy.customer.api;

import com.digital.academy.customer.api.LoanApi;
import com.digital.academy.customer.model.response.GetLoanInfoResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@SpringBootConfiguration
public class LoanServiceApiTest {

    @Mock
    RestTemplate restTemplate;

    @InjectMocks
    LoanApi loanApi;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        loanApi = new LoanApi(restTemplate);
    }

    public static ResponseEntity<String> prepareResponseSuccess(){
        return ResponseEntity.ok("{\n" +
                "    \"status\": {\n" +
                "        \"code\": \"0\",\n" +
                "        \"message\": \"success\"\n" +
                "    },\n" +
                "    \"data\": {\n" +
                "        \"status\": \"OK\",\n" +
                "        \"account_payable\": \"102-222-2200\",\n" +
                "        \"account_receivable\": \"102-333-2020\",\n" +
                "        \"principal_amount\": 3400.0,\n" +
                "        \"id\": 1\n" +
                "    }\n" +
                "}");
    }

    @DisplayName("Test get loan info should return loan information")
    @Test
    void testGetLoanInfo() throws Exception{
        Long reqId = 1L;

        when(restTemplate.exchange(
                ArgumentMatchers.<RequestEntity<String>>any(),
                ArgumentMatchers.<Class<String>>any()))
                .thenReturn(this.prepareResponseSuccess());
        GetLoanInfoResponse resp = loanApi.getLoanInfo(reqId);
        assertEquals("1",resp.getId().toString());
        assertEquals("OK",resp.getStatus());
        assertEquals("102-222-2200",resp.getAccountPayable());
        assertEquals("102-333-2020",resp.getAccountReceivable());
        assertEquals(3400.0,resp.getPrincipalAmount());
        verify(restTemplate,times(1)).exchange(ArgumentMatchers.<RequestEntity<String>>any(),ArgumentMatchers.<Class<String>>any());
    }
    public  static ResponseEntity<String> prepareResponseLOAN4002(){
        return ResponseEntity.ok("{\n" +
                "    \"status\": {\n" +
                "        \"code\": \"LOAN4002\",\n" +
                "        \"message\": \"Loan information not found\"\n" +
                "    }\n" +
                "}");
    }

    @DisplayName("Fail return LOAN4002")
    @Test
    void testGetLoanInfoInternalServerError() throws Exception{
        Long reqId = 2L;

        when(restTemplate.exchange(
                ArgumentMatchers.<RequestEntity<String>>any(),
                ArgumentMatchers.<Class<String>>any()))
                .thenReturn(this.prepareResponseLOAN4002());
        GetLoanInfoResponse resp = loanApi.getLoanInfo(reqId);
        assertEquals(null,resp.getId());
        assertEquals(null,resp.getStatus());
        assertEquals(null,resp.getAccountPayable());
        assertEquals(null,resp.getAccountReceivable());
        assertEquals(0.0,resp.getPrincipalAmount());
    }
    public  static ResponseEntity<String> prepareResponseLOAN4001(){
        return ResponseEntity.ok("{\n" +
                "    \"status\": {\n" +
                "        \"code\": \"LOAN4001\",\n" +
                "        \"message\": \"Cannot get loan information\"\n" +
                "    },\n" +
                "    \"data\": \"Cannot get loan information\"\n" +
                "}");
    }
    @DisplayName("Fail return LOAN4001")
    @Test
    void testGetLoanInfoLOAN4001() throws Exception{
        Long reqId = 3L;

        when(restTemplate.exchange(
                ArgumentMatchers.<RequestEntity<String>>any(),
                ArgumentMatchers.<Class<String>>any()))
                .thenReturn(this.prepareResponseLOAN4001());
        GetLoanInfoResponse resp = loanApi.getLoanInfo(reqId);
        assertEquals(null,resp.getId());
        assertEquals(null,resp.getStatus());
        assertEquals(null,resp.getAccountPayable());
        assertEquals(null,resp.getAccountReceivable());
        assertEquals(0.0,resp.getPrincipalAmount());
    }

    @DisplayName("Test get loan info should return client exception")
    @Test
    void testGetLoanInfoReturnException()throws  Exception{
        final Long reqId = 3L;
        when(restTemplate.exchange(
                ArgumentMatchers.<RequestEntity<String>>any(),
                ArgumentMatchers.<Class<String>>any()))
                .thenThrow(HttpClientErrorException.class);

        Exception thrown = assertThrows(Exception.class,
                () -> loanApi.getLoanInfo(reqId),
                "Expected getLoanInfo(reqId) to throw, but it didn't");
        assertEquals("httpClientErrorException", thrown.getMessage());

        verify(restTemplate,times(1)).exchange(ArgumentMatchers.<RequestEntity<String>>any(),ArgumentMatchers.<Class<String>>any());
    }

    @DisplayName("Test get loan info should return server exception")
    @Test
    void testGetLoanInfoReturnServerException()throws  Exception{
        final Long reqId = 3L;
        when(restTemplate.exchange(
                ArgumentMatchers.<RequestEntity<String>>any(),
                ArgumentMatchers.<Class<String>>any()))
                .thenThrow(HttpServerErrorException.class);

        Exception thrown = assertThrows(Exception.class,
                () -> loanApi.getLoanInfo(reqId),
                "Expected getLoanInfo(reqId) to throw, but it didn't");
        assertEquals("httpServerErrorException", thrown.getMessage());

        verify(restTemplate,times(1)).exchange(ArgumentMatchers.<RequestEntity<String>>any(),ArgumentMatchers.<Class<String>>any());
    }
    @DisplayName("Test get loan info by id equals 3 should throw Exception: Test throw new exception")
    @Test
    void testGetLoanInfoByIdEquals3()throws Exception {
        Long reqId = 3L;
        Exception thrown = assertThrows(Exception.class,
                () -> loanApi.getLoanInfo(reqId),
                "Expected loanInfoById(reqParam) to throw, but it didn't");
        assertEquals(null, thrown.getMessage());
    }
}
