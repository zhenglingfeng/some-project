package app;

import app.some.api.BackOfficeWebService;
import app.some.api.OrderWebService;
import app.some.order.service.BackOfficeService;
import app.some.order.web.BackOfficeWebServiceImpl;
import core.framework.module.Module;

public class BackOfficeModule extends Module {
    @Override
    protected void initialize() {
        loadProperties("app.properties");
        api().client(OrderWebService.class, requiredProperty("app.some.order.uri"));
        bind(BackOfficeService.class);
        api().service(BackOfficeWebService.class, bind(BackOfficeWebServiceImpl.class));
    }
}
