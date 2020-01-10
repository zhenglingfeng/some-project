package app.async.test.kafka;

import app.async.kafka.OrderCreatedMessage;
import app.async.test.IntegrationTest;
import core.framework.inject.Inject;
import core.framework.kafka.MessagePublisher;
import org.junit.jupiter.api.Test;

/**
 * @author richard
 */
class KafkaIntegrationTest extends IntegrationTest {
    @Inject
    MessagePublisher<OrderCreatedMessage> publisher;

    @Test
    void publish() {
        OrderCreatedMessage message = new OrderCreatedMessage();
        message.remark = "remark";
        publisher.publish("key", message);
        publisher.publish("key", message);
    }
}
