package app.async.test.kafka;

import app.async.kafka.OrderCreatedMessage;
import app.async.test.IntegrationTest;
import core.framework.inject.Inject;
import core.framework.kafka.MessagePublisher;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

/**
 * @author richard
 */
class KafkaIntegrationTest extends IntegrationTest {
    @Inject
    MessagePublisher<OrderCreatedMessage> publisher;

    @Test
    void publish() throws InterruptedException {
        OrderCreatedMessage message = new OrderCreatedMessage();
        message.remark = "test";
        publisher.publish("topic", "key", message);

        verify(publisher).publish(eq("topic"), eq("key"), argThat(arg -> "test".equals(arg.remark)));
    }
}
