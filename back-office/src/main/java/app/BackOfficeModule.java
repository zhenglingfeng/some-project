package app;

import app.backoffice.api.BackOfficeWebService;
import app.backoffice.order.service.BackOfficeService;
import app.backoffice.order.web.BackOfficeWebServiceImpl;
import app.some.api.OrderWebService;
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
