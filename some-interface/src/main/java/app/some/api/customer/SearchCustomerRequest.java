package app.some.api.customer;

import core.framework.api.validate.NotBlank;
import core.framework.api.validate.NotNull;
import core.framework.api.web.service.QueryParam;

public class SearchCustomerRequest {
    @NotBlank
    @NotNull
    @QueryParam(name = "remark")
    public String remark;
}
