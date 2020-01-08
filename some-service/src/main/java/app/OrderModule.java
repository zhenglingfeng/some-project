package app;

import app.some.api.OrderWebService;
import app.some.order.demo.Brush;
import app.some.order.demo.Painter;
import app.some.order.domain.Order;
import app.some.order.service.OrderService;
import app.some.order.web.OrderWebServiceImpl;
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
