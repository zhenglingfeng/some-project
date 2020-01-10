package app;

import app.async.ExecutorModule;
import app.async.KafkaModule;
import app.async.SchedulerModule;
import core.framework.module.App;

/**
 * @author richard
 */
public class SomeAsyncApp extends App {
    @Override
    protected void initialize() {
        http().httpPort(8085);
        load(new ExecutorModule());
        load(new SchedulerModule());
        load(new KafkaModule());
    }
}
