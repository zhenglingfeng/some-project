package app.some.customer.web;

import app.some.api.CustomerWebService;
import app.some.api.customer.CreateCustomerRequest;
import app.some.api.customer.SearchCustomerRequest;
import app.some.api.customer.SearchCustomerResponse;
import app.some.api.customer.UpdateCustomerRequest;
import app.some.customer.service.CustomerService;
import core.framework.inject.Inject;

public class CustomerWebServiceImpl implements CustomerWebService {
    @Inject
    CustomerService customerService;

    @Override
    public void create(CreateCustomerRequest request) {
        customerService.create(request);
    }

    @Override
    public void update(UpdateCustomerRequest request) {
        customerService.update(request);
    }

    @Override
    public SearchCustomerResponse search(SearchCustomerRequest request) {
        return customerService.search(request);
    }
}
