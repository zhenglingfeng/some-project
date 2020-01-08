package app.some.api;

import app.some.api.customer.CreateCustomerRequest;
import app.some.api.customer.SearchCustomerRequest;
import app.some.api.customer.SearchCustomerResponse;
import app.some.api.customer.UpdateCustomerRequest;
import core.framework.api.http.HTTPStatus;
import core.framework.api.web.service.GET;
import core.framework.api.web.service.POST;
import core.framework.api.web.service.PUT;
import core.framework.api.web.service.Path;
import core.framework.api.web.service.ResponseStatus;

public interface CustomerWebService {

    @POST
    @Path("/customer")
    @ResponseStatus(HTTPStatus.CREATED)
    void create(CreateCustomerRequest request);

    @PUT
    @Path("/customer")
    void update(UpdateCustomerRequest request);

    @GET
    @Path("/customer")
    SearchCustomerResponse search(SearchCustomerRequest request);
}
