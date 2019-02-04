package com.megapapa.sk.resource;

import com.google.inject.Inject;
import com.megapapa.sk.auth.annotation.SecureApi;
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

    @GET
    @Path("{taskId}")
    @SecureApi(permission = "assignedTask:read")
    public DataResponse<AssignedTask> get(@PathParam("taskId") int id, @Context UriInfo uriInfo) {
        return Ag.select(AssignedTask.class, config)
                .toManyParent(MongoUser.class, systemUserService.getSystemUser().getId(), MongoUser.ASSIGNED_TASKS)
                .byId(id)
                .uri(uriInfo)
                .get();
    }

    @POST
    @SecureApi(permission = "assignedTask:create")
    public SimpleResponse post(String data) {
        return Ag.create(AssignedTask.class, config)
                .toManyParent(MongoUser.class, systemUserService.getSystemUser().getId(), MongoUser.ASSIGNED_TASKS)
                .sync(data);
    }

    @PUT
    @SecureApi(permission = "assignedTask:update")
    public SimpleResponse put(String data) {
        return Ag.createOrUpdate(AssignedTask.class, config)
                .toManyParent(MongoUser.class, systemUserService.getSystemUser().getId(), MongoUser.ASSIGNED_TASKS)
                .sync(data);
    }

    @DELETE
    @Path("{taskId}")
    @SecureApi(permission = "assignedTask:delete")
    public SimpleResponse delete(@PathParam("taskId") int id) {
        return Ag.delete(AssignedTask.class, config)
                .toManyParent(MongoUser.class, systemUserService.getSystemUser().getId(), MongoUser.ASSIGNED_TASKS)
                .id(id)
                .delete();
    }

    @GET
    @SecureApi(permission = "assignedTask:list")
    public DataResponse<AssignedTask> list(@Context UriInfo uriInfo) {
        return Ag.select(AssignedTask.class, config)
                .toManyParent(MongoUser.class, systemUserService.getSystemUser().getId(), MongoUser.ASSIGNED_TASKS)
                .uri(uriInfo)
                .get();
    }
}
