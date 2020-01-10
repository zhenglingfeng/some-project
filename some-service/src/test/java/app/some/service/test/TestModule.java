package app.some.service.test;

import app.some.service.test.db.TestDBEntity;
import app.some.service.test.mongo.TestMongoEntity;
import core.framework.module.SystemModule;
import core.framework.mongo.module.MongoConfig;
import core.framework.test.module.AbstractTestModule;

/**
 * @author richard
 */
public class TestModule extends AbstractTestModule {
    @Override
    protected void initialize() {
        load(new SystemModule("sys.properties"));
        configureDB();
        configureMongo();
    }

    private void configureDB() {
        db().repository(TestDBEntity.class);
        initDB().createSchema();
    }

    private void configureMongo() {
        MongoConfig mongo = config(MongoConfig.class);
        mongo.uri(requiredProperty("sys.mongo.uri"));
        mongo.collection(TestMongoEntity.class);
    }
}
