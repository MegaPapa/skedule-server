package com.megapapa.sk.resource;

import com.google.inject.Inject;
import com.megapapa.sk.auth.annotation.SecureApi;
import com.megapapa.sk.auth.service.ISystemUserService;
import com.megapapa.sk.cayenne.MongoUser;
import com.megapapa.sk.cayenne.Reminder;
import io.agrest.Ag;
import io.agrest.DataResponse;
import io.agrest.SimpleResponse;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Configuration;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

@Path("reminder")
@Produces(MediaType.APPLICATION_JSON)
public class ReminderResource {

    @Context
    private Configuration config;

    @Inject
    private ISystemUserService systemUserService;

    @GET
    @Path("{reminderId}")
    @SecureApi(permission = "reminder:read")
    public DataResponse<Reminder> get(@PathParam("reminderId") int id, @Context UriInfo uriInfo) {
        return Ag.select(Reminder.class, config)
                .toManyParent(MongoUser.class, systemUserService.getSystemUser().getId(), MongoUser.REMINDERS)
                .byId(id)
                .uri(uriInfo)
                .get();
    }

    @POST
    @SecureApi(permission = "reminder:create")
    public SimpleResponse post(String data) {
        return Ag.create(Reminder.class, config)
                .toManyParent(MongoUser.class, systemUserService.getSystemUser().getId(), MongoUser.REMINDERS)
                .sync(data);
    }

    @PUT
    @SecureApi(permission = "reminder:update")
    public SimpleResponse put(String data) {
        return Ag.createOrUpdate(Reminder.class, config)
                .toManyParent(MongoUser.class, systemUserService.getSystemUser().getId(), MongoUser.REMINDERS)
                .sync(data);
    }

    @DELETE
    @Path("{reminderId}")
    @SecureApi(permission = "reminder:delete")
    public SimpleResponse delete(@PathParam("reminderId") int id) {
        return Ag.delete(Reminder.class, config)
                .toManyParent(MongoUser.class, systemUserService.getSystemUser().getId(), MongoUser.REMINDERS)
                .id(id)
                .delete();
    }

    @GET
    @SecureApi(permission = "reminder:list")
    public DataResponse<Reminder> list(@Context UriInfo uriInfo) {
        return Ag.select(Reminder.class, config)
                .toManyParent(MongoUser.class, systemUserService.getSystemUser().getId(), MongoUser.REMINDERS)
                .uri(uriInfo)
                .get();
    }
}
