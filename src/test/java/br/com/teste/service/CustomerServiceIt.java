package br.com.teste.service;

import br.com.teste.api.response.CustomerResponse;
import br.com.teste.repository.CustomerRepository;
import br.com.teste.stub.CustomerFiltersStub;
import br.com.teste.stub.CustomerRequestStub;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest
@Sql("/sql/test.sql")
@ActiveProfiles("test")
public class CustomerServiceIt {

    @Autowired
    CustomerRepository repository;

    @Autowired
    CustomerService service;

    @AfterEach
    public void setUp() {
        repository.deleteAll();
    }

    @Test
    public void shouldFindDatabaseByName() {
        Page<CustomerResponse> page = service.findAll(CustomerFiltersStub.get("NOME TESTE"), Pageable.unpaged());
        Assertions.assertEquals( 1, page.getTotalElements());
    }

    @Test
    public void shouldSaveANewCustomer() {
        service.create(CustomerRequestStub.get());
        Page<CustomerResponse> page = service.findAll(CustomerFiltersStub.get(CustomerRequestStub.get().getName()),
                Pageable.unpaged());

        Assertions.assertEquals(1, page.getTotalElements());
    }

    @Test
    public void shouldUpdateCustomer() {
        service.create(CustomerRequestStub.get());
        Page<CustomerResponse> page = service.findAll(CustomerFiltersStub.get(CustomerRequestStub.get().getName()),
                Pageable.unpaged());

        Assertions.assertEquals(1, page.getTotalElements());
        Assertions.assertEquals(CustomerRequestStub.get().getName(), page.getContent().get(0).getName());

        final String alterName = "NOME ALTERADO";

        CustomerResponse alterCustomer = service.update(page.getContent().get(0).getId(),
                CustomerRequestStub.get(alterName));

        Assertions.assertEquals(alterName, alterCustomer.getName());
    }
}
