package app.async.test;

import app.async.kafka.OrderCreatedMessage;
import app.async.test.scheduler.TestJob;
import core.framework.scheduler.Job;
import core.framework.test.module.AbstractTestModule;

import java.time.Duration;
import java.time.ZoneId;

/**
 * @author richard
 */
public class TestModule extends AbstractTestModule {
    @Override
    protected void initialize() {
        configureKafka();
    }

    private void configureExecutor() {
        executor().add();
        executor().add("name", 1);
    }

    private void configureHTTP() {
        http().httpPort(8085);
        http().httpsPort(8445);
    }

    private void configureKafka() {
        loadProperties("sys.properties");
        kafka().uri(requiredProperty("sys.kafka.uri"));
        OrderCreatedMessage message = new OrderCreatedMessage();
        message.remark = "value";
        kafka().publish("topic", OrderCreatedMessage.class).publish("key", message);
    }

    private void configureJob() {
        schedule().timeZone(ZoneId.of("UTC"));
        Job job = new TestJob();
        schedule().fixedRate("my-job", job, Duration.ofSeconds(1));
    }
}
