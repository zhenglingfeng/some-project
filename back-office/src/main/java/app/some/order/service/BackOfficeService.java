package app.some.order.service;

import app.some.api.OrderWebService;
import app.some.api.backoffice.BOCreateOrderRequest;
import app.some.api.backoffice.BOOrderView;
import app.some.api.backoffice.BOSearchOrderRequest;
import app.some.api.backoffice.BOSearchOrderResponse;
import app.some.api.backoffice.BOUpdateOrderRequest;
import app.some.api.order.CreateOrderRequest;
import app.some.api.order.OrderView;
import app.some.api.order.SearchOrderRequest;
import app.some.api.order.SearchOrderResponse;
import app.some.api.order.UpdateOrderRequest;
import core.framework.inject.Inject;

import java.util.stream.Collectors;

public class BackOfficeService {
    @Inject
    OrderWebService orderWebService;

    public BOOrderView get(Long id) {
        OrderView orderView = orderWebService.get(id);
        return view(orderView);
    }

    public void create(BOCreateOrderRequest request) {
        CreateOrderRequest orderRequest = new CreateOrderRequest();
        orderRequest.remark = request.remark;
        orderWebService.create(orderRequest);
    }

    public void update(Long id, BOUpdateOrderRequest request) {
        UpdateOrderRequest orderRequest = new UpdateOrderRequest();
        orderRequest.remark = request.remark;
        orderWebService.update(id, orderRequest);
    }

    public BOSearchOrderResponse search(BOSearchOrderRequest request) {
        SearchOrderRequest searchOrderRequest = new SearchOrderRequest();
        searchOrderRequest.skip = request.skip;
        searchOrderRequest.limit = request.limit;
        searchOrderRequest.remark = request.remark;
        SearchOrderResponse searchOrderResponse = orderWebService.search(searchOrderRequest);
        BOSearchOrderResponse result = new BOSearchOrderResponse();
        result.orders = searchOrderResponse.orders.stream().map(this::view).collect(Collectors.toList());
        result.total = searchOrderResponse.total;
        return result;
    }

    public BOOrderView view(OrderView orderView) {
        BOOrderView boOrderView = new BOOrderView();
        boOrderView.id = orderView.id;
        boOrderView.remark = orderView.remark;
        return boOrderView;
    }
}
