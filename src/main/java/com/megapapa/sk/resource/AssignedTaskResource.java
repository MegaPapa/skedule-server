package com.megapapa.sk.resource;

import com.google.inject.Inject;
import com.megapapa.sk.auth.exception.InvalidAuthTokenException;
import com.megapapa.sk.auth.service.IPermissionService;
import com.megapapa.sk.auth.service.ISystemUserService;
import com.megapapa.sk.cayenne.AssignedTask;
import com.megapapa.sk.cayenne.MongoUser;
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

@Path("assigned_task")
@Produces(MediaType.APPLICATION_JSON)
public class AssignedTaskResource {

    @Context
    private Configuration config;

    @Inject
    private ISystemUserService systemUserService;

    @Inject
    private IPermissionService permissionService;

    @GET
    @Path("{taskId}")
    public DataResponse<AssignedTask> get(@PathParam("taskId") int id, @Context UriInfo uriInfo) {
        permissionService.hasAccess(AssignedTask.READ);
        return Ag.select(AssignedTask.class, config)
                .toManyParent(MongoUser.class, systemUserService.getSystemUser().getId(), MongoUser.ASSIGNED_TASKS)
                .byId(id)
                .uri(uriInfo)
                .get();
    }

    @POST
    public SimpleResponse post(String data) {
        permissionService.hasAccess(AssignedTask.CREATE);
        return Ag.create(AssignedTask.class, config)
                .toManyParent(MongoUser.class, systemUserService.getSystemUser().getId(), MongoUser.ASSIGNED_TASKS)
                .sync(data);
    }

    @PUT
    public SimpleResponse put(String data) {
        permissionService.hasAccess(AssignedTask.UPDATE);
        return Ag.createOrUpdate(AssignedTask.class, config)
                .toManyParent(MongoUser.class, systemUserService.getSystemUser().getId(), MongoUser.ASSIGNED_TASKS)
                .sync(data);
    }

    @DELETE
    @Path("{taskId}")
    public SimpleResponse delete(@PathParam("taskId") int id) {
        permissionService.hasAccess(AssignedTask.DELETE);
        return Ag.delete(AssignedTask.class, config)
                .toManyParent(MongoUser.class, systemUserService.getSystemUser().getId(), MongoUser.ASSIGNED_TASKS)
                .id(id)
                .delete();
    }

    @GET
    public DataResponse<AssignedTask> list(@Context UriInfo uriInfo) {
        permissionService.hasAccess(AssignedTask.LIST);
        return Ag.select(AssignedTask.class, config)
                .toManyParent(MongoUser.class, systemUserService.getSystemUser().getId(), MongoUser.ASSIGNED_TASKS)
                .uri(uriInfo)
                .get();
    }
}
