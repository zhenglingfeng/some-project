package app.async;

import core.framework.module.Module;
import core.framework.scheduler.Job;
import core.framework.scheduler.JobContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * @author richard
 */
public class SchedulerModule extends Module {
    private final Logger logger = LoggerFactory.getLogger(SchedulerModule.class);
    @Override
    protected void initialize() {
        Duration rate = Duration.ofSeconds(1);
        schedule().fixedRate("schedule-job", new SchedulerJob(), rate);
    }

    private class SchedulerJob implements Job {
        @Override
        public void execute(JobContext context) {
            logger.info(LocalDateTime.now().toString());
        }
    }
}
