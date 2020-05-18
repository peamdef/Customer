package com.digitalacademy.customer.support;

import com.digital.academy.customer.model.response.GetLoanInfoResponse;

public class LoanSupportTest {
    public static GetLoanInfoResponse getLoanInfo(){
        GetLoanInfoResponse loanInfoResponse = new GetLoanInfoResponse();
        loanInfoResponse.setId(1L);
        loanInfoResponse.setStatus("OK");
        loanInfoResponse.setAccountPayable("102-222-2200");
        loanInfoResponse.setAccountReceivable("102-333-2020");
        loanInfoResponse.setPrincipalAmount(3400.0);
        return  loanInfoResponse;
    }
}
