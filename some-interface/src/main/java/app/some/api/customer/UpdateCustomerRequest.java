package app.some.api.customer;

import core.framework.api.json.Property;
import core.framework.api.validate.NotBlank;
import core.framework.api.validate.NotNull;

public class UpdateCustomerRequest {
    @NotNull
    @NotBlank
    @Property(name = "remark")
    public String remark;
}
