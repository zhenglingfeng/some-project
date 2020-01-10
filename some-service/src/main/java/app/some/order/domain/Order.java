package app.some.order.domain;

import core.framework.api.validate.NotBlank;
import core.framework.api.validate.NotNull;
import core.framework.db.Column;
import core.framework.db.PrimaryKey;
import core.framework.db.Table;

@Table(name = "orders")
public class Order {
    @PrimaryKey(autoIncrement = true)
    @Column(name = "id")
    public Long id;

    @NotNull
    @NotBlank
    @Column(name = "remark")
    public String remark;
}
