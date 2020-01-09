package app.backoffice.order.service;

import app.backoffice.api.order.CreateOrderAJAXRequest;
import app.backoffice.api.order.OrderAJAXView;
import app.backoffice.api.order.SearchOrderAJAXRequest;
import app.backoffice.api.order.SearchOrderAJAXResponse;
import app.backoffice.api.order.UpdateOrderAJAXRequest;
import app.some.api.BOOrderWebService;
import app.some.api.order.BOCreateOrderRequest;
import app.some.api.order.BOOrderView;
import app.some.api.order.BOSearchOrderRequest;
import app.some.api.order.BOSearchOrderResponse;
import app.some.api.order.BOUpdateOrderRequest;
import core.framework.inject.Inject;

import java.util.stream.Collectors;

public class OrderAJAXService {
    @Inject
    private BOOrderWebService orderWebService;

    public OrderAJAXView get(Long id) {
        BOOrderView orderView = orderWebService.get(id);
        return view(orderView);
    }

    public void create(CreateOrderAJAXRequest request) {
        BOCreateOrderRequest orderRequest = new BOCreateOrderRequest();
        orderRequest.remark = request.remark;
        orderWebService.create(orderRequest);
    }

    public void update(Long id, UpdateOrderAJAXRequest request) {
        BOUpdateOrderRequest orderRequest = new BOUpdateOrderRequest();
        orderRequest.remark = request.remark;
        orderWebService.update(id, orderRequest);
    }

    public SearchOrderAJAXResponse search(SearchOrderAJAXRequest request) {
        BOSearchOrderRequest searchOrderRequest = new BOSearchOrderRequest();
        searchOrderRequest.skip = request.skip;
        searchOrderRequest.limit = request.limit;
        searchOrderRequest.remark = request.remark;
        BOSearchOrderResponse searchOrderResponse = orderWebService.search(searchOrderRequest);
        SearchOrderAJAXResponse result = new SearchOrderAJAXResponse();
        result.orders = searchOrderResponse.orders.stream().map(this::view).collect(Collectors.toList());
        result.total = searchOrderResponse.total;
        return result;
    }

    private OrderAJAXView view(BOOrderView orderView) {
        OrderAJAXView orderAJAXView = new OrderAJAXView();
        orderAJAXView.id = orderView.id;
        orderAJAXView.remark = orderView.remark;
        return orderAJAXView;
    }
}
