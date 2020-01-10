package app.some.service.test.mongo;

import app.some.service.test.IntegrationTest;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Indexes;
import com.mongodb.client.model.Updates;
import core.framework.inject.Inject;
import core.framework.mongo.Mongo;
import core.framework.mongo.MongoCollection;
import core.framework.mongo.Query;
import core.framework.util.Lists;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author richard
 */
class MongoIntegrationTest extends IntegrationTest {
    @Inject
    MongoCollection<TestMongoEntity> collection;
    @Inject
    Mongo mongo;

    @AfterEach
    void cleanup() {
        mongo.dropCollection("entities");
        mongo.createIndex("entities", Indexes.ascending("string_field"));
    }

    @Test
    void runCommand() {
        Document result = mongo.runCommand(new Document("buildInfo", 1));
        assertThat(result.get("ok")).isEqualTo(1.0);
    }

    @Test
    void insert() {
        var entity = new TestMongoEntity();
        entity.stringField = "string";
        entity.zonedDateTimeField = ZonedDateTime.parse("2016-09-01T11:00:00Z");
        entity.enumMapField = Map.of(TestMongoEntity.TestEnum.VALUE2, "V2");
        collection.insert(entity);

        assertThat(entity.id).isNotNull();
        assertThat(collection.get(entity.id)).get().satisfies(loadedEntity -> {
            assertThat(loadedEntity.stringField).isEqualTo(entity.stringField);
            assertThat(loadedEntity.zonedDateTimeField).isEqualTo("2016-09-01T11:00:00Z");
            assertThat(loadedEntity.enumMapField).containsEntry(TestMongoEntity.TestEnum.VALUE2, "V2");
        });
    }

    @Test
    void replace() {
        var entity = new TestMongoEntity();
        entity.id = new ObjectId();
        entity.stringField = "value1";
        collection.replace(entity);

        assertThat(collection.get(entity.id)).get().isEqualToComparingFieldByField(entity);

        entity.stringField = "value2";
        collection.replace(entity);

        assertThat(collection.get(entity.id)).get().isEqualToComparingFieldByField(entity);
    }

    @Test
    void find() {
        TestMongoEntity entity = createEntity("value2", TestMongoEntity.TestEnum.VALUE2);

        List<TestMongoEntity> entities = collection.find(Filters.eq("string_field", "value2"));
        assertThat(entities).hasSize(1).first().isEqualToComparingFieldByField(entity);
    }

    @Test
    void findByEnum() {
        TestMongoEntity entity = createEntity("value1", TestMongoEntity.TestEnum.VALUE1);

        List<TestMongoEntity> entities = collection.find(Filters.eq("enum_field", TestMongoEntity.TestEnum.VALUE1));
        assertThat(entities).hasSize(1).first().isEqualToComparingFieldByField(entity);
    }

    @Test
    void findOne() {
        TestMongoEntity entity = createEntity("value3", TestMongoEntity.TestEnum.VALUE1);

        assertThat(collection.findOne(Filters.eq("string_field", "value3"))).get().isEqualToComparingFieldByField(entity);
    }

    @Test
    void count() {
        long count = collection.count(Filters.eq("string_field", "value"));

        assertThat(count).isZero();
    }

    @Test
    void bulkInsert() {
        List<TestMongoEntity> entities = testEntities();
        collection.bulkInsert(entities);

        for (TestMongoEntity entity : entities) {
            assertThat(entity.id).isNotNull();
            assertThat(collection.get(entity.id)).get().isEqualToComparingFieldByField(entity);
        }
    }

    @Test
    void bulkReplace() {
        List<TestMongoEntity> entities = testEntities();
        entities.forEach(entity -> entity.id = new ObjectId());
        collection.bulkReplace(entities);

        entities.forEach(entity -> assertThat(collection.get(entity.id)).get().isEqualToComparingFieldByField(entity));

        entities.get(0).stringField = "string1-updated";
        entities.get(1).stringField = "string2-updated";
        collection.bulkReplace(entities);

        entities.forEach(entity -> assertThat(collection.get(entity.id)).get().isEqualToComparingFieldByField(entity));
    }

    @Test
    void bulkDelete() {
        List<TestMongoEntity> entities = testEntities();
        collection.bulkInsert(entities);

        long deletedCount = collection.bulkDelete(entities.stream().map(entity -> entity.id).collect(Collectors.toList()));
        assertThat(deletedCount).isEqualTo(2);
        entities.forEach(entity -> assertThat(collection.get(entity.id)).isEmpty());
    }

    @Test
    void delete() {
        TestMongoEntity entity = createEntity("value1", TestMongoEntity.TestEnum.VALUE1);

        boolean deleted = collection.delete(entity.id);
        assertThat(deleted).isTrue();
        assertThat(collection.get(entity.id)).isEmpty();
    }

    @Test
    void deleteByFilter() {
        TestMongoEntity entity = createEntity("value1", TestMongoEntity.TestEnum.VALUE1);

        long deleted = collection.delete(Filters.eq("string_field", "value1"));
        assertThat(deleted).isEqualTo(1);
        assertThat(collection.get(entity.id)).isEmpty();
    }

    @Test
    void update() {
        List<TestMongoEntity> entities = testEntities();
        collection.bulkInsert(entities);

        long updatedCount = collection.update(Filters.eq("string_field", entities.get(0).stringField), Updates.set("enum_field", TestMongoEntity.TestEnum.VALUE2));
        assertThat(updatedCount).isEqualTo(1);
        assertThat(collection.get(entities.get(0).id)).get().satisfies(loadedEntity -> assertThat(loadedEntity.enumField).isEqualTo(TestMongoEntity.TestEnum.VALUE2));
    }

    @Test
    void forEach() {
        List<TestMongoEntity> entities = testEntities();
        collection.bulkInsert(entities);

        List<TestMongoEntity> returnedEntities = new ArrayList<>();
        collection.forEach(new Query(), returnedEntities::add);
        assertThat(returnedEntities).hasSize(2);
    }

    private TestMongoEntity createEntity(String stringField, TestMongoEntity.TestEnum enumField) {
        TestMongoEntity entity = new TestMongoEntity();
        entity.id = new ObjectId();
        entity.stringField = stringField;
        entity.enumField = enumField;
        collection.insert(entity);
        return entity;
    }

    private List<TestMongoEntity> testEntities() {
        List<TestMongoEntity> entities = Lists.newArrayList();
        TestMongoEntity entity1 = new TestMongoEntity();
        entity1.stringField = "string1";
        entities.add(entity1);
        TestMongoEntity entity2 = new TestMongoEntity();
        entity2.stringField = "string2";
        entities.add(entity2);
        return entities;
    }
}
