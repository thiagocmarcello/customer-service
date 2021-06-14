package br.com.teste.mapper;

import br.com.teste.api.response.CustomerResponse;
import br.com.teste.stub.CustomerStub;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@Import(CustomerMapperImpl.class)
public class CustomerMapperTest {

    @Autowired
    private CustomerMapper mapper;

    @Test
    public void shouldReturnCustomerAge() {
        CustomerResponse response = mapper.toResponse(CustomerStub.get(1986));

        Assertions.assertNotNull(response.getAge());
    }
}
