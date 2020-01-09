package app.web.interceptor;

import core.framework.web.Interceptor;
import core.framework.web.Invocation;
import core.framework.web.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author richard
 */
public class WebInterceptor implements Interceptor {
    private final Logger logger = LoggerFactory.getLogger(WebInterceptor.class);

    @Override
    public Response intercept(Invocation invocation) throws Exception {
        Protected annotation = invocation.annotation(Protected.class);
        if (annotation != null) {
            logger.debug("detected @Protected");
        }
        return invocation.proceed();
    }
}
