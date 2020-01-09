package app.backoffice.order.web;

import app.backoffice.api.BackOfficeWebService;
import app.backoffice.api.order.BOCreateOrderRequest;
import app.backoffice.api.order.BOOrderView;
import app.backoffice.api.order.BOSearchOrderRequest;
import app.backoffice.api.order.BOSearchOrderResponse;
import app.backoffice.api.order.BOUpdateOrderRequest;
import app.backoffice.order.service.BackOfficeService;
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
