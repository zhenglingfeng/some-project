package app.some.order.web;

import app.some.order.service.BOOrderService;
import app.some.api.BOOrderWebService;
import app.some.api.order.BOCreateOrderRequest;
import app.some.api.order.BOOrderView;
import app.some.api.order.BOSearchOrderRequest;
import app.some.api.order.BOSearchOrderResponse;
import app.some.api.order.BOUpdateOrderRequest;
import core.framework.inject.Inject;

public class BOOrderWebServiceImpl implements BOOrderWebService {
    @Inject
    BOOrderService orderService;

    @Override
    public BOOrderView get(Long id) {
        return orderService.get(id);
    }

    @Override
    public void create(BOCreateOrderRequest request) {
        orderService.create(request);
    }

    @Override
    public void update(Long id, BOUpdateOrderRequest request) {
        orderService.update(id, request);
    }

    @Override
    public BOSearchOrderResponse search(BOSearchOrderRequest request) {
        return orderService.search(request);
    }
}
