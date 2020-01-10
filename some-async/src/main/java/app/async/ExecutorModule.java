package app.async;

import core.framework.module.Module;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;

/**
 * @author richard
 */
public class ExecutorModule extends Module {
    private final Logger logger = LoggerFactory.getLogger(ExecutorModule.class);

    @Override
    protected void initialize() {
        executor().add().submit("new-executor", () -> {
            logger.info(LocalDateTime.now().toString());
        });
    }
}
