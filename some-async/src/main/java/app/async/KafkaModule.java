package app.async;

import app.async.kafka.OrderCreatedMessage;
import core.framework.module.Module;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * @author richard
 */
public class KafkaModule extends Module {
    @Override
    protected void initialize() {
        kafka().uri("kafka://localhost:9092");
        kafka().maxProcessTime(Duration.ofMinutes(30));
        kafka().longConsumerLagThreshold(Duration.ofSeconds(60));
        kafka().groupId("my-group");
        OrderCreatedMessage message = new OrderCreatedMessage();
        message.remark = "test" + LocalDateTime.now();
        kafka().publish("topic", OrderCreatedMessage.class);
    }
}
