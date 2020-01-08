package app.some.order.web;

import app.some.api.OrderWebService;
import app.some.api.order.CreateOrderRequest;
import app.some.api.order.OrderView;
import app.some.api.order.SearchOrderRequest;
import app.some.api.order.SearchOrderResponse;
import app.some.api.order.UpdateOrderRequest;
import app.some.order.service.OrderService;
import core.framework.inject.Inject;

public class OrderWebServiceImpl implements OrderWebService {
    @Inject
    OrderService orderService;

    @Override
    public OrderView get(Long id) {
        return orderService.get(id);
    }

    @Override
    public void create(CreateOrderRequest request) {
        orderService.create(request);
    }

    @Override
    public void update(Long id, UpdateOrderRequest request) {
        orderService.update(id, request);
    }

    @Override
    public SearchOrderResponse search(SearchOrderRequest request) {
        return orderService.search(request);
    }
}
