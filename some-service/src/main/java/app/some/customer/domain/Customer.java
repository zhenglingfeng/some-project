package app.some.customer.domain;

import core.framework.mongo.Collection;
import core.framework.mongo.Field;
import core.framework.mongo.Id;
import org.bson.types.ObjectId;

@Collection(name = "col_customers")
public class Customer {
    @Id
    public ObjectId id;

    @Field(name = "remark")
    public String remark;
}
