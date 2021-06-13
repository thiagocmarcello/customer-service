package br.com.teste.api;

import br.com.teste.api.request.CustomerRequest;
import br.com.teste.api.response.CustomerResponse;
import br.com.teste.filter.CustomerFilters;
import br.com.teste.service.CustomerService;
import br.com.teste.stub.CustomerRequestStub;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class CustomerApiTest {

    private final ObjectMapper mapper = new ObjectMapper();

    private MockMvc mvc;

    @Mock
    private CustomerService service;

    @InjectMocks
    private CustomerApi api;

    @BeforeEach
    public void setUp() {
        mapper.registerModule(new JavaTimeModule());
        mvc = MockMvcBuilders.standaloneSetup(api).build();
    }

    @Test
    public void shouldCreateCustomer() throws Exception {
        final CustomerRequest createRequest = CustomerRequestStub.get();
        when(service.create(any(CustomerRequest.class)))
                .thenReturn(CustomerResponse.builder().name("nome retorno").build());

        this.mvc.perform(post("/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(createRequest)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("nome retorno"));
    }

    @Test
    public void shouldGetPaginationCustomer() throws Exception {
        List<CustomerResponse> customers = Arrays.asList(
                CustomerResponse.builder().name("A").build(),
                CustomerResponse.builder().name("B").build(),
                CustomerResponse.builder().name("C").build()
        );

        Page<CustomerResponse> pagedResponse = new PageImpl<>(customers);
        when(service.findAll(any(CustomerFilters.class), any(Pageable.class))).thenReturn(pagedResponse);

        this.mvc = MockMvcBuilders.standaloneSetup(api)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .setViewResolvers((viewName, locale) -> new MappingJackson2JsonView())
                .build();

        this.mvc.perform(get("/customers?page=0")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].name").value("A"))
                .andExpect(jsonPath("$.content[1].name").value("B"))
                .andExpect(jsonPath("$.content[2].name").value("C"))
                .andExpect(jsonPath("$.numberOfElements").value("3"));
    }

    @Test
    public void shouldUpdateCustomer() throws Exception {
        final CustomerRequest createRequest = CustomerRequestStub.get();
        when(service.update(anyLong(), any(CustomerRequest.class)))
                .thenReturn(CustomerResponse.builder().name("nome retorno").build());

        this.mvc.perform(put("/customers/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(createRequest)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("nome retorno"));
    }
}
