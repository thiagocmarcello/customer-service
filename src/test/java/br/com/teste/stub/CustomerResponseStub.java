package br.com.teste.stub;

import br.com.teste.api.response.CustomerResponse;

public class CustomerResponseStub {

    private CustomerResponseStub() {
    }

    public static CustomerResponse get() {
        return CustomerResponse.builder()
                .name("nome")
                .build();
    }
}
