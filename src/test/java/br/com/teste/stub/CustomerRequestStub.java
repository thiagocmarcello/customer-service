package br.com.teste.stub;

import br.com.teste.api.request.CustomerRequest;

import java.time.LocalDate;

public class CustomerRequestStub {

    private CustomerRequestStub() {
    }

    public static CustomerRequest get() {
        return CustomerRequest.builder()
                .name("nome")
                .email("email@email.com")
                .birth(LocalDate.now())
                .build();
    }
}
