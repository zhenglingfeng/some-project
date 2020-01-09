package app;

import core.framework.module.App;
import core.framework.module.SystemModule;

/**
 * @author richard
 */
public class WebSiteApp extends App {
    @Override
    protected void initialize() {
        load(new SystemModule("sys.properties"));
        http().gzip();
        http().httpsPort(8444);
        site().security();
//        log().maskFields("password");
        load(new WebModule());
    }
}
