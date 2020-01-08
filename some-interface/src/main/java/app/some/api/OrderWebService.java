package app.some.api;

import app.some.api.order.CreateOrderRequest;
import app.some.api.order.OrderView;
import app.some.api.order.SearchOrderRequest;
import app.some.api.order.SearchOrderResponse;
import app.some.api.order.UpdateOrderRequest;
import core.framework.api.http.HTTPStatus;
import core.framework.api.web.service.GET;
import core.framework.api.web.service.POST;
import core.framework.api.web.service.PUT;
import core.framework.api.web.service.Path;
import core.framework.api.web.service.PathParam;
import core.framework.api.web.service.ResponseStatus;

public interface OrderWebService {
    @GET
    @Path("/order/:id")
    OrderView get(@PathParam("id") Long id);

    @POST
    @Path("/order")
    @ResponseStatus(HTTPStatus.CREATED)
    void create(CreateOrderRequest request);

    @PUT
    @Path("/order/:id")
    void update(@PathParam("id") Long id, UpdateOrderRequest request);

    @GET
    @Path("/order")
    SearchOrderResponse search(SearchOrderRequest request);
}
