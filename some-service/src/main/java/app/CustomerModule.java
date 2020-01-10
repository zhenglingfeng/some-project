package app;

import app.some.api.CustomerWebService;
import app.some.customer.domain.Customer;
import app.some.customer.service.CustomerService;
import app.some.customer.web.CustomerWebServiceImpl;
import core.framework.module.Module;
import core.framework.mongo.module.MongoConfig;

public class CustomerModule extends Module {
    @Override
    protected void initialize() {
        MongoConfig mongo = config(MongoConfig.class);
        loadProperties("sys.properties");
        mongo.uri(requiredProperty("sys.mongo.uri"));
        mongo.collection(Customer.class);
        bind(CustomerService.class);
        api().service(CustomerWebService.class, bind(CustomerWebServiceImpl.class));
    }
}
