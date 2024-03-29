package com.megapapa.sk.cayenne.auto;

import org.apache.cayenne.CayenneDataObject;
import org.apache.cayenne.exp.Property;

import com.megapapa.sk.cayenne.CompanyTask;
import com.megapapa.sk.cayenne.MongoUser;

/**
 * Class _AssignedTask was generated by Cayenne.
 * It is probably a good idea to avoid changing this class manually,
 * since it may be overwritten next time code is regenerated.
 * If you need to make any customizations, please use subclass.
 */
public abstract class _AssignedTask extends CayenneDataObject {

    private static final long serialVersionUID = 1L; 

    public static final String COMPANY_TASK_ID_PK_COLUMN = "company_task_id";
    public static final String MONGO_USER_ID_PK_COLUMN = "mongo_user_id";

    public static final Property<String> ASSIGNED_TIME = Property.create("assignedTime", String.class);
    public static final Property<CompanyTask> COMPANY_TASK = Property.create("companyTask", CompanyTask.class);
    public static final Property<MongoUser> MONGO_USER = Property.create("mongoUser", MongoUser.class);

    public void setAssignedTime(String assignedTime) {
        writeProperty("assignedTime", assignedTime);
    }
    public String getAssignedTime() {
        return (String)readProperty("assignedTime");
    }

    public void setCompanyTask(CompanyTask companyTask) {
        setToOneTarget("companyTask", companyTask, true);
    }

    public CompanyTask getCompanyTask() {
        return (CompanyTask)readProperty("companyTask");
    }


    public void setMongoUser(MongoUser mongoUser) {
        setToOneTarget("mongoUser", mongoUser, true);
    }

    public MongoUser getMongoUser() {
        return (MongoUser)readProperty("mongoUser");
    }


}
