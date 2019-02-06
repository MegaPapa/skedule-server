package com.megapapa.sk.resource;

import com.google.inject.Inject;
import com.megapapa.sk.auth.service.IPermissionService;
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

    @Inject
    private IPermissionService permissionService;

    @GET
    @Path("{reminderId}")
    public DataResponse<Reminder> get(@PathParam("reminderId") int id, @Context UriInfo uriInfo) {
        permissionService.hasAccess(Reminder.READ);
        return Ag.select(Reminder.class, config)
                .toManyParent(MongoUser.class, 1L, MongoUser.REMINDERS)
                .byId(id)
                .uri(uriInfo)
                .get();
    }

    @POST
    public SimpleResponse post(String data) {
        permissionService.hasAccess(Reminder.CREATE);
        return Ag.create(Reminder.class, config)
                .toManyParent(MongoUser.class, systemUserService.getSystemUser().getId(), MongoUser.REMINDERS)
                .sync(data);
    }

    @PUT
    public SimpleResponse put(String data) {
        permissionService.hasAccess(Reminder.UPDATE);
        return Ag.createOrUpdate(Reminder.class, config)
                .toManyParent(MongoUser.class, systemUserService.getSystemUser().getId(), MongoUser.REMINDERS)
                .sync(data);
    }

    @DELETE
    @Path("{reminderId}")
    public SimpleResponse delete(@PathParam("reminderId") int id) {
        permissionService.hasAccess(Reminder.DELETE);
        return Ag.delete(Reminder.class, config)
                .toManyParent(MongoUser.class, systemUserService.getSystemUser().getId(), MongoUser.REMINDERS)
                .id(id)
                .delete();
    }

    @GET
    public DataResponse<Reminder> list(@Context UriInfo uriInfo) {
        permissionService.hasAccess(Reminder.LIST);
        return Ag.select(Reminder.class, config)
                .toManyParent(MongoUser.class, systemUserService.getSystemUser().getId(), MongoUser.REMINDERS)
                .uri(uriInfo)
                .get();
    }
}
