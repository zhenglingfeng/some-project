package app.backoffice.order.web;

import app.backoffice.api.OrderAJAXWebService;
import app.backoffice.api.order.CreateOrderAJAXRequest;
import app.backoffice.api.order.OrderAJAXView;
import app.backoffice.api.order.SearchOrderAJAXRequest;
import app.backoffice.api.order.SearchOrderAJAXResponse;
import app.backoffice.api.order.UpdateOrderAJAXRequest;
import app.backoffice.order.service.OrderService;
import core.framework.inject.Inject;

public class OrderAJAXWebServiceImpl implements OrderAJAXWebService {
    @Inject
    private OrderService orderService;

    @Override
    public OrderAJAXView get(Long id) {
        return orderService.get(id);
    }

    @Override
    public void create(CreateOrderAJAXRequest request) {
        orderService.create(request);
    }

    @Override
    public void update(Long id, UpdateOrderAJAXRequest request) {
        orderService.update(id, request);
    }

    @Override
    public SearchOrderAJAXResponse search(SearchOrderAJAXRequest request) {
        return orderService.search(request);
    }
}
