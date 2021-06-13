package br.com.teste.service;

import br.com.teste.api.response.CustomerResponse;
import br.com.teste.domain.Customer;
import br.com.teste.mapper.CustomerMapper;
import br.com.teste.mapper.CustomerMapperImpl;
import br.com.teste.repository.CustomerRepository;
import br.com.teste.stub.CustomerRequestStub;
import br.com.teste.stub.CustomerStub;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@ExtendWith(SpringExtension.class)
@Import(CustomerMapperImpl.class)
public class CustomerServiceTest {

    private CustomerService service;

    @Autowired
    private CustomerMapper mapper;

    @Mock
    private CustomerRepository repository;

    @BeforeEach
    void setUp() {
        service = new CustomerService(repository, mapper);
    }

    @Test
    public void shouldCreateReturnWithAge() {
        given(repository.save(any(Customer.class))).willReturn(CustomerStub.get());

        CustomerResponse customerResponse = service.create(CustomerRequestStub.get());

        then(repository).should(times(1)).save(any(Customer.class));

        assertNotNull(customerResponse.getAge());
        assertEquals(customerResponse.getEmail(), CustomerStub.get().getEmail());
    }

    @Test
    public void shouldUpdateCustomer() {
        given(repository.findById(anyLong())).willReturn(Optional.of(CustomerStub.get()));
        given(repository.save(any(Customer.class))).willReturn(CustomerStub.get());

        CustomerResponse customerResponse = service.update(1L, CustomerRequestStub.get());

        then(repository).should(times(1)).findById(anyLong());
        then(repository).should(times(1)).save(any(Customer.class));

        assertNotNull(customerResponse.getAge());
        assertEquals(customerResponse.getEmail(), CustomerStub.get().getEmail());
    }

    @Test
    public void shouldThrowWhenUpdateCustomerNotFound() {
        given(repository.findById(anyLong())).willReturn(Optional.empty());

        assertThrows(ResponseStatusException.class,
                () -> service.update(1L, CustomerRequestStub.get()));

        then(repository).should(times(1)).findById(anyLong());
        then(repository).should(times(0)).save(any(Customer.class));
    }
}
