package app;

import app.backoffice.api.OrderAJAXWebService;
import app.backoffice.order.service.OrderService;
import app.backoffice.order.web.OrderAJAXWebServiceImpl;
import app.some.api.BOOrderWebService;
import core.framework.module.Module;

public class OrderModule extends Module {
    @Override
    protected void initialize() {
        loadProperties("app.properties");
        api().client(BOOrderWebService.class, requiredProperty("app.some.order.uri"));
        bind(OrderService.class);
        api().service(OrderAJAXWebService.class, bind(OrderAJAXWebServiceImpl.class));
    }
}
