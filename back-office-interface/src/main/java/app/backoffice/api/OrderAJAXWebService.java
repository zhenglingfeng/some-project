package app.backoffice.api;

import app.backoffice.api.order.CreateOrderAJAXRequest;
import app.backoffice.api.order.OrderAJAXView;
import app.backoffice.api.order.SearchOrderAJAXRequest;
import app.backoffice.api.order.SearchOrderAJAXResponse;
import app.backoffice.api.order.UpdateOrderAJAXRequest;
import core.framework.api.http.HTTPStatus;
import core.framework.api.web.service.GET;
import core.framework.api.web.service.POST;
import core.framework.api.web.service.PUT;
import core.framework.api.web.service.Path;
import core.framework.api.web.service.PathParam;
import core.framework.api.web.service.ResponseStatus;

public interface OrderAJAXWebService {
    @GET
    @Path("/ajax/order/:id")
    OrderAJAXView get(@PathParam("id") Long id);

    @POST
    @Path("/ajax/order")
    @ResponseStatus(HTTPStatus.CREATED)
    void create(CreateOrderAJAXRequest request);

    @PUT
    @Path("/ajax/order/:id")
    void update(@PathParam("id") Long id, UpdateOrderAJAXRequest request);

    @GET
    @Path("/ajax/order")
    SearchOrderAJAXResponse search(SearchOrderAJAXRequest request);
}
