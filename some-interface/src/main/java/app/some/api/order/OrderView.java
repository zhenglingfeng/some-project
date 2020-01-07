package app.some.api.order;

import core.framework.api.json.Property;
import core.framework.api.validate.NotNull;

public class OrderView {
    @NotNull
    @Property(name = "id")
    public Long id;
}
