package app;

import app.asyncone.KafkaModule;
import core.framework.module.App;

/**
 * @author richard
 */
public class AsyncSubscribeOneApp extends App {
    @Override
    protected void initialize() {
        http().httpPort(8087);
        load(new KafkaModule());
    }
}
