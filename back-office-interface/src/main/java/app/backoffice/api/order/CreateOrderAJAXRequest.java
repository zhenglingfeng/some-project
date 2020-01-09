package app.backoffice.api.order;

import core.framework.api.json.Property;
import core.framework.api.validate.NotBlank;
import core.framework.api.validate.NotNull;

public class CreateOrderAJAXRequest {
    @NotNull
    @NotBlank
    @Property(name = "remark")
    public String remark;
}
