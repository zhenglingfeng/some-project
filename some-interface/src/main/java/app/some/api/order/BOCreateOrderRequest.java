package app.some.api.order;

import core.framework.api.json.Property;
import core.framework.api.validate.NotBlank;
import core.framework.api.validate.NotNull;

public class BOCreateOrderRequest {
    @NotNull
    @NotBlank
    @Property(name = "remark")
    public String remark;
}
