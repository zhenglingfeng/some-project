package app.async.test.scheduler;

import core.framework.scheduler.Job;
import core.framework.scheduler.JobContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;

/**
 * @author richard
 */
public class TestJob implements Job {
    private final Logger logger = LoggerFactory.getLogger(TestJob.class);
    @Override
    public void execute(JobContext context) {
        logger.info(LocalDateTime.now().toString());
    }
}
