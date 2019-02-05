package com.megapapa.sk.resource;

import com.google.inject.Inject;
import com.megapapa.sk.auth.annotation.SecureApi;
import com.megapapa.sk.auth.service.ISystemUserService;
import com.megapapa.sk.cayenne.MongoUser;
import com.megapapa.sk.cayenne.RepeatableTask;
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

@Path("repeatable_task")
@Produces(MediaType.APPLICATION_JSON)
public class RepeatableTaskResource {

    @Context
    private Configuration config;

    @Inject
    private ISystemUserService systemUserService;

    @GET
    @Path("{repeatableTaskId}")
    @SecureApi(permission = RepeatableTask.READ)
    public DataResponse<RepeatableTask> get(@PathParam("repeatableTaskId") int id, @Context UriInfo uriInfo) {
        return Ag.select(RepeatableTask.class, config)
                .toManyParent(MongoUser.class, systemUserService.getSystemUser().getId(), MongoUser.REPEATABLE_TASKS)
                .byId(id)
                .uri(uriInfo)
                .get();
    }

    @POST
    @SecureApi(permission = RepeatableTask.CREATE)
    public SimpleResponse post(String data) {
        return Ag.create(RepeatableTask.class, config)
                .toManyParent(MongoUser.class, systemUserService.getSystemUser().getId(), MongoUser.REPEATABLE_TASKS)
                .sync(data);
    }

    @PUT
    @SecureApi(permission = RepeatableTask.UPDATE)
    public SimpleResponse put(String data) {
        return Ag.createOrUpdate(RepeatableTask.class, config)
                .toManyParent(MongoUser.class, systemUserService.getSystemUser().getId(), MongoUser.REPEATABLE_TASKS)
                .sync(data);
    }

    @DELETE
    @Path("{repeatableTaskId}")
    @SecureApi(permission = RepeatableTask.DELETE)
    public SimpleResponse delete(@PathParam("repeatableTaskId") int id) {
        return Ag.delete(RepeatableTask.class, config)
                .toManyParent(MongoUser.class, systemUserService.getSystemUser().getId(), MongoUser.REPEATABLE_TASKS)
                .id(id)
                .delete();
    }

    @GET
    @SecureApi(permission = RepeatableTask.LIST)
    public DataResponse<RepeatableTask> list(@Context UriInfo uriInfo) {
        return Ag.select(RepeatableTask.class, config)
                .toManyParent(MongoUser.class, systemUserService.getSystemUser().getId(), MongoUser.REPEATABLE_TASKS)
                .uri(uriInfo)
                .get();
    }
}
