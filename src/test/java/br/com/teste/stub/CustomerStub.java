package br.com.teste.stub;

import br.com.teste.domain.Customer;

import java.time.LocalDate;

public class CustomerStub {

    public CustomerStub() {
    }

    public static Customer get() {
        return Customer.builder()
                .name("nome")
                .email("email@email.com")
                .birth(LocalDate.now())
                .build();
    }
}
