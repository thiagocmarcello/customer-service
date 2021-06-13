package br.com.teste.api;

import br.com.teste.api.request.CustomerRequest;
import br.com.teste.api.response.CustomerResponse;
import br.com.teste.filter.CustomerFilters;
import br.com.teste.service.CustomerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/customers")
public class CustomerApi {

    private final CustomerService service;

    @PostMapping
    public CustomerResponse create(@RequestBody @Validated final CustomerRequest request) {
        log.info("Iniciando criacao de cliente: {}", request);
        return service.create(request);
    }

    @GetMapping
    public Page<CustomerResponse> findAll(final CustomerFilters filters, final Pageable pageable) {
        log.info("Buscando clientes paginados: {}", pageable);
        return service.findAll(filters, pageable);
    }

    @PutMapping("/{id}")
    public CustomerResponse update(@PathVariable Long id, @RequestBody final CustomerRequest request) {
        log.info("Iniciando atualizacao de cliente id: {} para: {}", id, request);
        return service.update(id, request);
    }
}
