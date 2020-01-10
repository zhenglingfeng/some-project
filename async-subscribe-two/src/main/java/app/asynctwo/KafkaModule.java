package app.asynctwo;

import app.async.kafka.OrderCreatedMessage;
import app.asynctwo.kafka.service.OrderCreatedMessageHandler;
import core.framework.module.Module;

/**
 * @author richard
 */
public class KafkaModule extends Module {
    @Override
    protected void initialize() {
        kafka().uri("kafka://localhost:9092");
//        kafka().maxProcessTime(Duration.ofMinutes(30));
//        kafka().longConsumerLagThreshold(Duration.ofSeconds(60));
//        kafka().groupId("my-group");
        kafka().subscribe("topic", OrderCreatedMessage.class, bind(OrderCreatedMessageHandler.class));
    }
}
