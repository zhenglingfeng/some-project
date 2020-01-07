package app.some.order.service;

import app.some.api.order.OrderView;
import app.some.order.demo.Painter;
import core.framework.inject.Inject;

public class OrderService {
    @Inject
    Painter painter;

    public OrderView get(Long id) {
        painter.draw();
        OrderView result = new OrderView();
        result.id = 1L;
        return result;
    }
}
