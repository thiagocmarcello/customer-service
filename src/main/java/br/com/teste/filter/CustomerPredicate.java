package br.com.teste.filter;

import com.querydsl.core.BooleanBuilder;

import java.time.LocalDate;

import static br.com.teste.domain.QCustomer.customer;
import static java.util.Objects.nonNull;

public class CustomerPredicate {

    private final BooleanBuilder booleanBuilder = new BooleanBuilder();

    public CustomerPredicate withName(final String name) {
        if (nonNull(name)) {
            booleanBuilder.and(customer.name.eq(name));
        }
        return this;
    }

    public CustomerPredicate withEmail(final String email) {
        if (nonNull(email)) {
            booleanBuilder.and(customer.email.eq(email));
        }
        return this;
    }

    public CustomerPredicate withBirth(final LocalDate birth) {
        if (nonNull(birth)) {
            booleanBuilder.and(customer.birth.eq(birth));
        }
        return this;
    }

    public BooleanBuilder toBuild() {
        return this.booleanBuilder;
    }
}
