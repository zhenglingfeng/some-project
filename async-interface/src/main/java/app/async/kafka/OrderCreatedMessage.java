package app.async.kafka;

import core.framework.api.json.Property;

public class OrderCreatedMessage {
    @Property(name = "remark")
    public String remark;
}