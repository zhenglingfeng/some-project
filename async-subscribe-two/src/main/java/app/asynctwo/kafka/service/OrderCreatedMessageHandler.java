package app.asynctwo.kafka.service;

import app.async.kafka.OrderCreatedMessage;
import core.framework.kafka.MessageHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author richard
 */
public class OrderCreatedMessageHandler implements MessageHandler<OrderCreatedMessage> {
    private final Logger logger = LoggerFactory.getLogger(OrderCreatedMessageHandler.class);

    @Override
    public void handle(String key, OrderCreatedMessage messages) {
        logger.info("{}", messages.remark);
    }
}
