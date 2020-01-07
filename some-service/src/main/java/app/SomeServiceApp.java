package app;

import core.framework.module.App;
import core.framework.module.SystemModule;

/**
 * @author neo
 */
public class SomeServiceApp extends App {
    @Override
    protected void initialize() {
        http().httpsPort(8443);
        load(new SystemModule("sys.properties"));
        http().httpPort(8082);
        load(new OrderModule());
    }
}
