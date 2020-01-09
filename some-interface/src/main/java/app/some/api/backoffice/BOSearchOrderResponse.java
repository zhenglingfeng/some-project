package app.some.api.backoffice;

import core.framework.api.json.Property;

import java.util.List;

public class BOSearchOrderResponse {
    @Property(name = "total")
    public Integer total;

    @Property(name = "orders")
    public List<BOOrderView> orders;
}
