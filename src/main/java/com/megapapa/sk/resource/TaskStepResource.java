package com.megapapa.sk.resource;

import com.google.inject.Inject;
import com.megapapa.sk.auth.service.IPermissionService;
import com.megapapa.sk.auth.service.ISystemUserService;
import com.megapapa.sk.cayenne.MongoUser;
import com.megapapa.sk.cayenne.MultistepTask;
import com.megapapa.sk.cayenne.TaskStep;
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

@Path("task_step")
@Produces(MediaType.APPLICATION_JSON)
public class TaskStepResource {


    @Context
    private Configuration config;

    @Inject
    private ISystemUserService systemUserService;

    @Inject
    private IPermissionService permissionService;

    @GET
    @Path("{stepId}")
    public DataResponse<TaskStep> get(@PathParam("stepId") int id, @Context UriInfo uriInfo) {
        permissionService.hasAccess(TaskStep.READ);
        return Ag.select(TaskStep.class, config)
                .toManyParent(MongoUser.class, systemUserService.getSystemUser().getId(), MongoUser.MULTISTEP_TASKS.dot(MultistepTask.TASK_STEPS))
                .byId(id)
                .uri(uriInfo)
                .get();
    }

    @POST
    public SimpleResponse post(String data) {
        permissionService.hasAccess(TaskStep.CREATE);
        return Ag.create(TaskStep.class, config)
                .toManyParent(MongoUser.class, systemUserService.getSystemUser().getId(), MongoUser.MULTISTEP_TASKS.dot(MultistepTask.TASK_STEPS))
                .sync(data);
    }

    @PUT
    public SimpleResponse put(String data) {
        permissionService.hasAccess(TaskStep.UPDATE);
        return Ag.createOrUpdate(TaskStep.class, config)
                .toManyParent(MongoUser.class, systemUserService.getSystemUser().getId(), MongoUser.MULTISTEP_TASKS.dot(MultistepTask.TASK_STEPS))
                .sync(data);
    }

    @DELETE
    @Path("{stepId}")
    public SimpleResponse delete(@PathParam("stepId") int id) {
        permissionService.hasAccess(TaskStep.DELETE);
        return Ag.delete(TaskStep.class, config)
                .toManyParent(MongoUser.class, systemUserService.getSystemUser().getId(), MongoUser.MULTISTEP_TASKS.dot(MultistepTask.TASK_STEPS))
                .id(id)
                .delete();
    }

    @GET
    public DataResponse<TaskStep> list(@Context UriInfo uriInfo) {
        permissionService.hasAccess(TaskStep.LIST);
        return Ag.select(TaskStep.class, config)
                .toManyParent(MongoUser.class, systemUserService.getSystemUser().getId(), MongoUser.MULTISTEP_TASKS.dot(MultistepTask.TASK_STEPS))
                .uri(uriInfo)
                .get();
    }
}
