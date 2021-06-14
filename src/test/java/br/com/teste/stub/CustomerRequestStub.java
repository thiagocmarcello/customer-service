package br.com.teste.stub;

import br.com.teste.api.request.CustomerRequest;

import java.time.LocalDate;

public class CustomerRequestStub {

    private CustomerRequestStub() {
    }

    public static CustomerRequest get() {
        return CustomerRequest.builder()
                .name("nome")
                .email("emailstub@email.com")
                .birth(LocalDate.now())
                .build();
    }

    public static CustomerRequest get(final String name) {
        return CustomerRequest.builder()
                .name(name)
                .email("emailstub@email.com")
                .birth(LocalDate.now())
                .build();
    }
}
