package app;

import app.backoffice.api.BOOrderWebService;
import app.backoffice.order.service.BOOrderService;
import app.backoffice.order.web.BOOrderWebServiceImpl;
import app.some.api.OrderWebService;
import core.framework.module.Module;

public class BOOrderModule extends Module {
    @Override
    protected void initialize() {
        loadProperties("app.properties");
        api().client(OrderWebService.class, requiredProperty("app.some.order.uri"));
        bind(BOOrderService.class);
        api().service(BOOrderWebService.class, bind(BOOrderWebServiceImpl.class));
    }
}
