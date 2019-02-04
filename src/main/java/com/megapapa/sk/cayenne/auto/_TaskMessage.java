package com.megapapa.sk.cayenne.auto;

import org.apache.cayenne.CayenneDataObject;
import org.apache.cayenne.exp.Property;

import com.megapapa.sk.cayenne.CompanyTask;

/**
 * Class _TaskMessage was generated by Cayenne.
 * It is probably a good idea to avoid changing this class manually,
 * since it may be overwritten next time code is regenerated.
 * If you need to make any customizations, please use subclass.
 */
public abstract class _TaskMessage extends CayenneDataObject {

    private static final long serialVersionUID = 1L; 

    public static final String ID_PK_COLUMN = "id";

    public static final Property<String> MESSAGE = Property.create("message", String.class);
    public static final Property<CompanyTask> COMPANY_TASK = Property.create("companyTask", CompanyTask.class);

    public void setMessage(String message) {
        writeProperty("message", message);
    }
    public String getMessage() {
        return (String)readProperty("message");
    }

    public void setCompanyTask(CompanyTask companyTask) {
        setToOneTarget("companyTask", companyTask, true);
    }

    public CompanyTask getCompanyTask() {
        return (CompanyTask)readProperty("companyTask");
    }


}