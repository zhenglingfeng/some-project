package app.some.order.web;

import app.some.api.OrderWebService;
import app.some.api.order.OrderView;
import app.some.order.service.OrderService;
import core.framework.inject.Inject;

public class OrderWebServiceImpl implements OrderWebService {
    @Inject
    OrderService orderService;

    @Override
    public OrderView get(Long id) {
        return orderService.get(id);
    }
}
