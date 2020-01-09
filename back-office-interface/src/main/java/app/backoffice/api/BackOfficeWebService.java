package app.backoffice.api;

import app.backoffice.api.order.BOCreateOrderRequest;
import app.backoffice.api.order.BOOrderView;
import app.backoffice.api.order.BOSearchOrderRequest;
import app.backoffice.api.order.BOSearchOrderResponse;
import app.backoffice.api.order.BOUpdateOrderRequest;
import core.framework.api.http.HTTPStatus;
import core.framework.api.web.service.GET;
import core.framework.api.web.service.POST;
import core.framework.api.web.service.PUT;
import core.framework.api.web.service.Path;
import core.framework.api.web.service.PathParam;
import core.framework.api.web.service.ResponseStatus;

public interface BackOfficeWebService {
    @GET
    @Path("/back-office/order/:id")
    BOOrderView get(@PathParam("id") Long id);

    @POST
    @Path("/back-office/order")
    @ResponseStatus(HTTPStatus.CREATED)
    void create(BOCreateOrderRequest request);

    @PUT
    @Path("/back-office/order/:id")
    void update(@PathParam("id") Long id, BOUpdateOrderRequest request);

    @GET
    @Path("/back-office/order")
    BOSearchOrderResponse search(BOSearchOrderRequest request);
}
