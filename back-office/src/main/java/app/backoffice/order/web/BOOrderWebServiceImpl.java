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
    BOOrderService BOOrderService;

    @Override
    public BOOrderView get(Long id) {
        return BOOrderService.get(id);
    }

    @Override
    public void create(BOCreateOrderRequest request) {
        BOOrderService.create(request);
    }

    @Override
    public void update(Long id, BOUpdateOrderRequest request) {
        BOOrderService.update(id, request);
    }

    @Override
    public BOSearchOrderResponse search(BOSearchOrderRequest request) {
        return BOOrderService.search(request);
    }
}
