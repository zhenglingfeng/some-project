package app;

import app.some.api.OrderWebService;
import app.backoffice.order.demo.Brush;
import app.backoffice.order.demo.Painter;
import app.backoffice.order.domain.Order;
import app.backoffice.order.service.OrderService;
import app.backoffice.order.web.OrderWebServiceImpl;
import core.framework.module.Module;

public class OrderModule extends Module {
    @Override
    protected void initialize() {
        db().repository(Order.class);
        bind(Brush.class);
        bind(Painter.class);
        bind(OrderService.class);
        api().service(OrderWebService.class, bind(OrderWebServiceImpl.class));
    }
}
