package com.megapapa.sk.cayenne.auto;

import java.time.LocalDateTime;

import org.apache.cayenne.CayenneDataObject;
import org.apache.cayenne.exp.Property;

import com.megapapa.sk.cayenne.MongoUser;

/**
 * Class _RepeatableTask was generated by Cayenne.
 * It is probably a good idea to avoid changing this class manually,
 * since it may be overwritten next time code is regenerated.
 * If you need to make any customizations, please use subclass.
 */
public abstract class _RepeatableTask extends CayenneDataObject {

    private static final long serialVersionUID = 1L; 

    public static final String ID_PK_COLUMN = "id";

    public static final Property<LocalDateTime> CREATED_TIME = Property.create("createdTime", LocalDateTime.class);
    public static final Property<String> CRON = Property.create("cron", String.class);
    public static final Property<String> DESCRIPTION = Property.create("description", String.class);
    public static final Property<LocalDateTime> END_TIME = Property.create("endTime", LocalDateTime.class);
    public static final Property<Boolean> IS_COMPLETED = Property.create("isCompleted", Boolean.class);
    public static final Property<String> TITLE = Property.create("title", String.class);
    public static final Property<MongoUser> MONGO_USER = Property.create("mongoUser", MongoUser.class);

    public void setCreatedTime(LocalDateTime createdTime) {
        writeProperty("createdTime", createdTime);
    }
    public LocalDateTime getCreatedTime() {
        return (LocalDateTime)readProperty("createdTime");
    }

    public void setCron(String cron) {
        writeProperty("cron", cron);
    }
    public String getCron() {
        return (String)readProperty("cron");
    }

    public void setDescription(String description) {
        writeProperty("description", description);
    }
    public String getDescription() {
        return (String)readProperty("description");
    }

    public void setEndTime(LocalDateTime endTime) {
        writeProperty("endTime", endTime);
    }
    public LocalDateTime getEndTime() {
        return (LocalDateTime)readProperty("endTime");
    }

    public void setIsCompleted(boolean isCompleted) {
        writeProperty("isCompleted", isCompleted);
    }
	public boolean isIsCompleted() {
        Boolean value = (Boolean)readProperty("isCompleted");
        return (value != null) ? value.booleanValue() : false;
    }

    public void setTitle(String title) {
        writeProperty("title", title);
    }
    public String getTitle() {
        return (String)readProperty("title");
    }

    public void setMongoUser(MongoUser mongoUser) {
        setToOneTarget("mongoUser", mongoUser, true);
    }

    public MongoUser getMongoUser() {
        return (MongoUser)readProperty("mongoUser");
    }


}
