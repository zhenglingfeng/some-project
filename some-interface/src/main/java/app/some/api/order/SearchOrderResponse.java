package app.some.api.order;

import core.framework.api.json.Property;

import java.util.List;

public class SearchOrderResponse {
    @Property(name = "total")
    public Integer total;

    @Property(name = "orders")
    public List<OrderView> orders;
}
