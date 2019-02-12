package com.megapapa.sk.cayenne.auto;

import java.util.List;

import org.apache.cayenne.CayenneDataObject;
import org.apache.cayenne.exp.Property;

import com.megapapa.sk.cayenne.AssignedTask;
import com.megapapa.sk.cayenne.BookReadTask;
import com.megapapa.sk.cayenne.Company;
import com.megapapa.sk.cayenne.CompanyInvite;
import com.megapapa.sk.cayenne.Currency;
import com.megapapa.sk.cayenne.Language;
import com.megapapa.sk.cayenne.MovieWatchTask;
import com.megapapa.sk.cayenne.MultistepTask;
import com.megapapa.sk.cayenne.Reminder;
import com.megapapa.sk.cayenne.RepeatableTask;
import com.megapapa.sk.cayenne.SelfTask;

/**
 * Class _MongoUser was generated by Cayenne.
 * It is probably a good idea to avoid changing this class manually,
 * since it may be overwritten next time code is regenerated.
 * If you need to make any customizations, please use subclass.
 */
public abstract class _MongoUser extends CayenneDataObject {

    private static final long serialVersionUID = 1L; 

    public static final String ID_PK_COLUMN = "id";

    public static final Property<String> MONGO_ID = Property.create("mongoId", String.class);
    public static final Property<List<AssignedTask>> ASSIGNED_TASKS = Property.create("assignedTasks", List.class);
    public static final Property<List<BookReadTask>> BOOK_READ_TASKS = Property.create("bookReadTasks", List.class);
    public static final Property<List<Company>> COMPANIES = Property.create("companies", List.class);
    public static final Property<List<CompanyInvite>> COMPANY_INVITES = Property.create("companyInvites", List.class);
    public static final Property<Currency> CURRENCY = Property.create("currency", Currency.class);
    public static final Property<Language> LANGUAGE = Property.create("language", Language.class);
    public static final Property<List<MovieWatchTask>> MOVIE_WATCH_TASKS = Property.create("movieWatchTasks", List.class);
    public static final Property<List<MultistepTask>> MULTISTEP_TASKS = Property.create("multistepTasks", List.class);
    public static final Property<List<Reminder>> REMINDERS = Property.create("reminders", List.class);
    public static final Property<List<RepeatableTask>> REPEATABLE_TASKS = Property.create("repeatableTasks", List.class);
    public static final Property<List<SelfTask>> SELF_TASKS = Property.create("selfTasks", List.class);

    public void setMongoId(String mongoId) {
        writeProperty("mongoId", mongoId);
    }
    public String getMongoId() {
        return (String)readProperty("mongoId");
    }

    public void addToAssignedTasks(AssignedTask obj) {
        addToManyTarget("assignedTasks", obj, true);
    }
    public void removeFromAssignedTasks(AssignedTask obj) {
        removeToManyTarget("assignedTasks", obj, true);
    }
    @SuppressWarnings("unchecked")
    public List<AssignedTask> getAssignedTasks() {
        return (List<AssignedTask>)readProperty("assignedTasks");
    }


    public void addToBookReadTasks(BookReadTask obj) {
        addToManyTarget("bookReadTasks", obj, true);
    }
    public void removeFromBookReadTasks(BookReadTask obj) {
        removeToManyTarget("bookReadTasks", obj, true);
    }
    @SuppressWarnings("unchecked")
    public List<BookReadTask> getBookReadTasks() {
        return (List<BookReadTask>)readProperty("bookReadTasks");
    }


    public void addToCompanies(Company obj) {
        addToManyTarget("companies", obj, true);
    }
    public void removeFromCompanies(Company obj) {
        removeToManyTarget("companies", obj, true);
    }
    @SuppressWarnings("unchecked")
    public List<Company> getCompanies() {
        return (List<Company>)readProperty("companies");
    }


    public void addToCompanyInvites(CompanyInvite obj) {
        addToManyTarget("companyInvites", obj, true);
    }
    public void removeFromCompanyInvites(CompanyInvite obj) {
        removeToManyTarget("companyInvites", obj, true);
    }
    @SuppressWarnings("unchecked")
    public List<CompanyInvite> getCompanyInvites() {
        return (List<CompanyInvite>)readProperty("companyInvites");
    }


    public void setCurrency(Currency currency) {
        setToOneTarget("currency", currency, true);
    }

    public Currency getCurrency() {
        return (Currency)readProperty("currency");
    }


    public void setLanguage(Language language) {
        setToOneTarget("language", language, true);
    }

    public Language getLanguage() {
        return (Language)readProperty("language");
    }


    public void addToMovieWatchTasks(MovieWatchTask obj) {
        addToManyTarget("movieWatchTasks", obj, true);
    }
    public void removeFromMovieWatchTasks(MovieWatchTask obj) {
        removeToManyTarget("movieWatchTasks", obj, true);
    }
    @SuppressWarnings("unchecked")
    public List<MovieWatchTask> getMovieWatchTasks() {
        return (List<MovieWatchTask>)readProperty("movieWatchTasks");
    }


    public void addToMultistepTasks(MultistepTask obj) {
        addToManyTarget("multistepTasks", obj, true);
    }
    public void removeFromMultistepTasks(MultistepTask obj) {
        removeToManyTarget("multistepTasks", obj, true);
    }
    @SuppressWarnings("unchecked")
    public List<MultistepTask> getMultistepTasks() {
        return (List<MultistepTask>)readProperty("multistepTasks");
    }


    public void addToReminders(Reminder obj) {
        addToManyTarget("reminders", obj, true);
    }
    public void removeFromReminders(Reminder obj) {
        removeToManyTarget("reminders", obj, true);
    }
    @SuppressWarnings("unchecked")
    public List<Reminder> getReminders() {
        return (List<Reminder>)readProperty("reminders");
    }


    public void addToRepeatableTasks(RepeatableTask obj) {
        addToManyTarget("repeatableTasks", obj, true);
    }
    public void removeFromRepeatableTasks(RepeatableTask obj) {
        removeToManyTarget("repeatableTasks", obj, true);
    }
    @SuppressWarnings("unchecked")
    public List<RepeatableTask> getRepeatableTasks() {
        return (List<RepeatableTask>)readProperty("repeatableTasks");
    }


    public void addToSelfTasks(SelfTask obj) {
        addToManyTarget("selfTasks", obj, true);
    }
    public void removeFromSelfTasks(SelfTask obj) {
        removeToManyTarget("selfTasks", obj, true);
    }
    @SuppressWarnings("unchecked")
    public List<SelfTask> getSelfTasks() {
        return (List<SelfTask>)readProperty("selfTasks");
    }


}
