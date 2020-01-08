package app.some.api.customer;

import core.framework.api.json.Property;
import core.framework.api.validate.NotBlank;
import core.framework.api.validate.NotNull;

public class CustomerView {
    @NotNull
    @NotBlank
    @Property(name = "id")
    public String id;

    @NotNull
    @NotBlank
    @Property(name = "remark")
    public String remark;
}
