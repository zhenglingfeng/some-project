package app.some.order.web;

import app.some.api.BackOfficeWebService;
import app.some.api.backoffice.BOCreateOrderRequest;
import app.some.api.backoffice.BOOrderView;
import app.some.api.backoffice.BOSearchOrderRequest;
import app.some.api.backoffice.BOSearchOrderResponse;
import app.some.api.backoffice.BOUpdateOrderRequest;
import app.some.order.service.BackOfficeService;
import core.framework.inject.Inject;

public class BackOfficeWebServiceImpl implements BackOfficeWebService {
    @Inject
    BackOfficeService backOfficeService;

    @Override
    public BOOrderView get(Long id) {
        return backOfficeService.get(id);
    }

    @Override
    public void create(BOCreateOrderRequest request) {
        backOfficeService.create(request);
    }

    @Override
    public void update(Long id, BOUpdateOrderRequest request) {
        backOfficeService.update(id, request);
    }

    @Override
    public BOSearchOrderResponse search(BOSearchOrderRequest request) {
        return backOfficeService.search(request);
    }
}
