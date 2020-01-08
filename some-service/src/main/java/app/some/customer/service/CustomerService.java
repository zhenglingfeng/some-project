package app.some.customer.service;

import app.some.api.customer.CreateCustomerRequest;
import app.some.api.customer.CustomerView;
import app.some.api.customer.SearchCustomerRequest;
import app.some.api.customer.SearchCustomerResponse;
import app.some.api.customer.UpdateCustomerRequest;
import app.some.customer.domain.Customer;
import com.mongodb.client.model.Filters;
import core.framework.inject.Inject;
import core.framework.mongo.MongoCollection;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class CustomerService {
    @Inject
    MongoCollection<Customer> customerCollection;

    public void create(CreateCustomerRequest request) {
        Customer customer = new Customer();
        customer.remark = request.remark;
        customerCollection.insert(customer);
    }

    public void update(UpdateCustomerRequest request) {
        Customer customer = customerCollection.find(Filters.eq("remark", request.remark)).get(0);
        customer.remark = "test" + LocalDateTime.now();
        customerCollection.replace(customer);
    }

    public SearchCustomerResponse search(SearchCustomerRequest request) {
        SearchCustomerResponse result = new SearchCustomerResponse();
        List<Customer> customers = customerCollection.find(Filters.eq("remark", request.remark));
        result.customers = customers.stream().map(this::view).collect(Collectors.toList());
        return result;
    }

    private CustomerView view(Customer customer) {
        CustomerView result = new CustomerView();
        result.id = customer.id.toString();
        result.remark = customer.remark;
        return result;
    }
}
