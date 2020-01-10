package app;

import app.asynctwo.KafkaModule;
import core.framework.module.App;

/**
 * @author richard
 */
public class AsyncSubscribeTwoApp extends App {
    @Override
    protected void initialize() {
        http().httpPort(8086);
        load(new KafkaModule());
    }
}
