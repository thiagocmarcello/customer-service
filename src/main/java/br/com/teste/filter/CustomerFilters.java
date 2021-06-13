package br.com.teste.filter;

import com.querydsl.core.BooleanBuilder;
import lombok.Setter;

import java.time.LocalDate;

@Setter
public class CustomerFilters {

    private String name;

    private String email;

    private LocalDate birth;

    public BooleanBuilder toPredicate() {
        return new CustomerPredicate()
                .withName(name)
                .withEmail(email)
                .withBirth(birth)
                .toBuild();
    }
}
