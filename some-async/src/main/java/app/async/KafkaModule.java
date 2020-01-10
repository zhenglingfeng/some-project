package app.async;

import app.async.kafka.OrderCreatedMessage;
import core.framework.kafka.MessagePublisher;
import core.framework.module.Module;

import java.time.LocalDateTime;

/**
 * @author richard
 */
public class KafkaModule extends Module {
    @Override
    protected void initialize() {
        loadProperties("sys.properties");
        kafka().uri(requiredProperty("sys.kafka.uri"));
//        kafka().maxProcessTime(Duration.ofMinutes(30));
//        kafka().longConsumerLagThreshold(Duration.ofSeconds(60));
//        kafka().groupId("my-group");
        OrderCreatedMessage message = new OrderCreatedMessage();
        MessagePublisher<OrderCreatedMessage> publisher = kafka().publish("topic", OrderCreatedMessage.class);
        for (int i = 0; i < 10; i++) {
            message.remark = "test" + LocalDateTime.now();
            publisher.publish("key" + i, message);
        }
    }
}
