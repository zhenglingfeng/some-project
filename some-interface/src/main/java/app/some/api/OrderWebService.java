package app.some.api;

import app.some.api.order.OrderView;
import core.framework.api.web.service.GET;
import core.framework.api.web.service.Path;
import core.framework.api.web.service.PathParam;

public interface OrderWebService {
    @GET
    @Path("/order/:id")
    OrderView get(@PathParam("id") Long id);
}
