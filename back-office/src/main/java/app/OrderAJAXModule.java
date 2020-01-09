package app;

import app.backoffice.api.OrderAJAXWebService;
import app.backoffice.order.service.OrderAJAXService;
import app.backoffice.order.web.OrderAJAXWebServiceImpl;
import app.some.api.BOOrderWebService;
import core.framework.module.Module;

public class OrderAJAXModule extends Module {
    @Override
    protected void initialize() {
        loadProperties("app.properties");
        api().client(BOOrderWebService.class, requiredProperty("app.some.order.uri"));
        bind(OrderAJAXService.class);
        api().service(OrderAJAXWebService.class, bind(OrderAJAXWebServiceImpl.class));

    }
}
