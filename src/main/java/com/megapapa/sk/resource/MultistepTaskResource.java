package com.megapapa.sk.resource;

import com.google.inject.Inject;
import com.megapapa.sk.auth.service.IPermissionService;
import com.megapapa.sk.auth.service.ISystemUserService;
import com.megapapa.sk.cayenne.MongoUser;
import com.megapapa.sk.cayenne.MultistepTask;
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

@Path("multistep_task")
@Produces(MediaType.APPLICATION_JSON)
public class MultistepTaskResource {

    @Context
    private Configuration config;

    @Inject
    private ISystemUserService systemUserService;

    @Inject
    private IPermissionService permissionService;

    @GET
    @Path("{taskId}")
    public DataResponse<MultistepTask> get(@PathParam("taskId") int id, @Context UriInfo uriInfo) {
        permissionService.hasAccess(MultistepTask.READ);
        return Ag.select(MultistepTask.class, config)
                .toManyParent(MongoUser.class, systemUserService.getSystemUser().getId(), MongoUser.MULTISTEP_TASKS)
                .byId(id)
                .uri(uriInfo)
                .get();
    }

    @POST
    public SimpleResponse post(String data) {
        permissionService.hasAccess(MultistepTask.CREATE);
        return Ag.create(MultistepTask.class, config)
                .toManyParent(MongoUser.class, systemUserService.getSystemUser().getId(), MongoUser.MULTISTEP_TASKS)
                .sync(data);
    }

    @PUT
    public SimpleResponse put(String data) {
        permissionService.hasAccess(MultistepTask.UPDATE);
        return Ag.createOrUpdate(MultistepTask.class, config)
                .toManyParent(MongoUser.class, systemUserService.getSystemUser().getId(), MongoUser.MULTISTEP_TASKS)
                .sync(data);
    }

    @DELETE
    @Path("{taskId}")
    public SimpleResponse delete(@PathParam("taskId") int id) {
        permissionService.hasAccess(MultistepTask.DELETE);
        return Ag.delete(MultistepTask.class, config)
                .toManyParent(MongoUser.class, systemUserService.getSystemUser().getId(), MongoUser.MULTISTEP_TASKS)
                .id(id)
                .delete();
    }

    @GET
    public DataResponse<MultistepTask> list(@Context UriInfo uriInfo) {
        permissionService.hasAccess(MultistepTask.LIST);
        return Ag.select(MultistepTask.class, config)
                .toManyParent(MongoUser.class, systemUserService.getSystemUser().getId(), MongoUser.MULTISTEP_TASKS)
                .uri(uriInfo)
                .get();
    }
}
