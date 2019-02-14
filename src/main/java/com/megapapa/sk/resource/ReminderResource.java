package com.megapapa.sk.resource;

import com.google.inject.Inject;
import com.megapapa.sk.auth.service.IPermissionService;
import com.megapapa.sk.auth.service.ISystemUserService;
import com.megapapa.sk.cayenne.MongoUser;
import com.megapapa.sk.cayenne.Reminder;
import com.megapapa.sk.resource.error.HasNoAccessResponse;
import io.agrest.Ag;
import io.agrest.AgResponse;
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
    public AgResponse get(@PathParam("reminderId") int id, @Context UriInfo uriInfo) {
        if (permissionService.hasAccess(Reminder.READ)) {
            return new HasNoAccessResponse(Reminder.READ);
        }
        return Ag.select(Reminder.class, config)
                .toManyParent(MongoUser.class, systemUserService.getSystemUser().getId(), MongoUser.REMINDERS)
                .byId(id)
                .uri(uriInfo)
                .get();
    }

    @POST
    public SimpleResponse post(String data) {
        if (permissionService.hasAccess(Reminder.CREATE)) {
            return new HasNoAccessResponse(Reminder.CREATE);
        }
        return Ag.create(Reminder.class, config)
                .toManyParent(MongoUser.class, systemUserService.getSystemUser().getId(), MongoUser.REMINDERS)
                .sync(data);
    }

    @PUT
    public SimpleResponse put(String data) {
        if (permissionService.hasAccess(Reminder.UPDATE)) {
            return new HasNoAccessResponse(Reminder.UPDATE);
        }
        return Ag.createOrUpdate(Reminder.class, config)
                .toManyParent(MongoUser.class, systemUserService.getSystemUser().getId(), MongoUser.REMINDERS)
                .sync(data);
    }

    @DELETE
    @Path("{reminderId}")
    public SimpleResponse delete(@PathParam("reminderId") int id) {
        if (permissionService.hasAccess(Reminder.DELETE)) {
            return new HasNoAccessResponse(Reminder.DELETE);
        }
        return Ag.delete(Reminder.class, config)
                .toManyParent(MongoUser.class, systemUserService.getSystemUser().getId(), MongoUser.REMINDERS)
                .id(id)
                .delete();
    }

    @GET
    public AgResponse list(@Context UriInfo uriInfo) {
        if (permissionService.hasAccess(Reminder.LIST)) {
            return new HasNoAccessResponse(Reminder.LIST);
        }
        return Ag.select(Reminder.class, config)
                .toManyParent(MongoUser.class, systemUserService.getSystemUser().getId(), MongoUser.REMINDERS)
                .uri(uriInfo)
                .get();
    }
}
