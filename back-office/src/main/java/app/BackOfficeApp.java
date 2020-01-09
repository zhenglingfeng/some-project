package app;

import core.framework.module.App;
import core.framework.module.SystemModule;

/**
 * @author richard
 */
public class BackOfficeApp extends App {
    @Override
    protected void initialize() {
        http().httpsPort(8443);
        load(new SystemModule("sys.properties"));
        http().httpPort(8083);
        load(new OrderModule());
    }
}
