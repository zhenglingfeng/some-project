package app.some.api.order;

import core.framework.api.validate.NotNull;
import core.framework.api.web.service.QueryParam;

public class SearchOrderRequest {
    @NotNull
    @QueryParam(name = "skip")
    public Integer skip = 0;

    @NotNull
    @QueryParam(name = "limit")
    public Integer limit = 1000;

    @QueryParam(name = "remark")
    public String remark;
}
