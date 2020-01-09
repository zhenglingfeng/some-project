package app.some.order.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Painter {
    private final Logger logger = LoggerFactory.getLogger(Painter.class);

    public void draw() {
        logger.warn("DEMO_WARN", "drawing picture...");
    }
}