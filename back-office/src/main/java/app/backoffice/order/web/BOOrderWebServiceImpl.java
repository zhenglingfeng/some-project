package app.backoffice.order.web;

import app.backoffice.api.BOOrderWebService;
import app.backoffice.api.order.BOCreateOrderRequest;
import app.backoffice.api.order.BOOrderView;
import app.backoffice.api.order.BOSearchOrderRequest;
import app.backoffice.api.order.BOSearchOrderResponse;
import app.backoffice.api.order.BOUpdateOrderRequest;
import app.backoffice.order.service.BOOrderService;
import core.framework.inject.Inject;

public class BOOrderWebServiceImpl implements BOOrderWebService {
    @Inject
    private BOOrderService orderService;

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
