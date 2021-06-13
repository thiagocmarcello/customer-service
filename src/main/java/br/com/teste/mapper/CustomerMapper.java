package br.com.teste.mapper;

import br.com.teste.api.request.CustomerRequest;
import br.com.teste.api.response.CustomerResponse;
import br.com.teste.domain.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CustomerMapper {

    Customer toCustomer(final CustomerRequest request);

    @Mapping(source = "id", target = "id")
    Customer toCustomer(final Long id, final CustomerRequest request);

    @Mapping(expression = "java(formatAge(customer))", target = "age")
    CustomerResponse toResponse(final Customer customer);

    default Long formatAge(final Customer customer){
        return ChronoUnit.YEARS.between(customer.getBirth(), LocalDate.now());
    }
}
