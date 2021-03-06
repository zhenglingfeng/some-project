package app;

import core.framework.module.App;
import core.framework.module.SystemModule;

/**
 * @author richard
 */
public class SomeServiceApp extends App {
    @Override
    protected void initialize() {
        load(new SystemModule("sys.properties"));
        http().httpsPort(8442);
        http().httpPort(8082);
        load(new OrderModule());
        load(new CustomerModule());
    }
}
