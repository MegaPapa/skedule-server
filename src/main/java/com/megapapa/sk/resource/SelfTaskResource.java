package com.megapapa.sk.resource;

import com.google.inject.Inject;
import com.megapapa.sk.auth.annotation.SecureApi;
import com.megapapa.sk.auth.service.ISystemUserService;
import com.megapapa.sk.cayenne.MongoUser;
import com.megapapa.sk.cayenne.SelfTask;
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

@Path("self_task")
@Produces(MediaType.APPLICATION_JSON)
public class SelfTaskResource {

    @Context
    private Configuration config;

    @Inject
    private ISystemUserService systemUserService;

    @GET
    @Path("{taskId}")
    @SecureApi(permission = "selfTask:read")
    public DataResponse<SelfTask> get(@PathParam("taskId") int id, @Context UriInfo uriInfo) {
        return Ag.select(SelfTask.class, config)
                .toManyParent(MongoUser.class, systemUserService.getSystemUser().getId(), MongoUser.SELF_TASKS)
                .byId(id)
                .uri(uriInfo)
                .get();
    }

    @POST
    @SecureApi(permission = "selfTask:create")
    public SimpleResponse post(String data) {
        return Ag.create(SelfTask.class, config)
                .toManyParent(MongoUser.class, systemUserService.getSystemUser().getId(), MongoUser.SELF_TASKS)
                .sync(data);
    }

    @PUT
    @SecureApi(permission = "selfTask:update")
    public SimpleResponse put(String data) {
        return Ag.createOrUpdate(SelfTask.class, config)
                .toManyParent(MongoUser.class, systemUserService.getSystemUser().getId(), MongoUser.SELF_TASKS)
                .sync(data);
    }

    @DELETE
    @Path("{taskId}")
    @SecureApi(permission = "selfTask:delete")
    public SimpleResponse delete(@PathParam("taskId") int id) {
        return Ag.delete(SelfTask.class, config)
                .toManyParent(MongoUser.class, systemUserService.getSystemUser().getId(), MongoUser.SELF_TASKS)
                .id(id)
                .delete();
    }

    @GET
    @SecureApi(permission = "selfTask:list")
    public DataResponse<SelfTask> list(@Context UriInfo uriInfo) {
        return Ag.select(SelfTask.class, config)
                .toManyParent(MongoUser.class, systemUserService.getSystemUser().getId(), MongoUser.SELF_TASKS)
                .uri(uriInfo)
                .get();
    }
}
