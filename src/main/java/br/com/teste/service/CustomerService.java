package br.com.teste.service;

import br.com.teste.api.request.CustomerRequest;
import br.com.teste.api.response.CustomerResponse;
import br.com.teste.domain.Customer;
import br.com.teste.filter.CustomerFilters;
import br.com.teste.mapper.CustomerMapper;
import br.com.teste.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository repository;

    private final CustomerMapper mapper;

    public CustomerResponse create(final CustomerRequest request) {
        log.info("Create customer: {}", request);
        Customer customer = repository.save(mapper.toCustomer(request));
        return mapper.toResponse(customer);
    }

    public Page<CustomerResponse> findAll(final CustomerFilters filters, final Pageable pageable) {
        return repository.findAll(filters.toPredicate(), pageable)
               .map(mapper::toResponse);
    }

    public CustomerResponse update(final Long id, final CustomerRequest request) {
        log.info("Update customer: {} by id: {}", request, id);
        repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found."));
        Customer customer = repository.save(mapper.toCustomer(id, request));
        return mapper.toResponse(customer);
    }
}
