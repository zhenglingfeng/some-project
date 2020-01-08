package app.some.api.customer;

import core.framework.api.json.Property;

import java.util.List;

public class SearchCustomerResponse {
    @Property(name = "customers")
    public List<CustomerView> customers;
}
