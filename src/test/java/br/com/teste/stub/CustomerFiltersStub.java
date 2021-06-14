package br.com.teste.stub;

import br.com.teste.filter.CustomerFilters;

public class CustomerFiltersStub {

    private CustomerFiltersStub() {
    }

    public static CustomerFilters get(final String name) {
        CustomerFilters filters = new CustomerFilters();
        filters.setName(name);
        return filters;
    }
}
