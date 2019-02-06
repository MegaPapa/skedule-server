package com.megapapa.sk.resource;

import com.google.inject.Inject;
import com.megapapa.sk.auth.service.IPermissionService;
import com.megapapa.sk.auth.service.ISystemUserService;
import com.megapapa.sk.cayenne.Company;
import com.megapapa.sk.cayenne.CompanyTask;
import com.megapapa.sk.cayenne.MongoUser;
import com.megapapa.sk.cayenne.TaskMessage;
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

@Path("task_message")
@Produces(MediaType.APPLICATION_JSON)
public class TaskMessageResource {

    @Context
    private Configuration config;

    @Inject
    private ISystemUserService systemUserService;

    @Inject
    private IPermissionService permissionService;

    @GET
    @Path("{messageId}")
    public DataResponse<TaskMessage> get(@PathParam("messageId") int id, @Context UriInfo uriInfo) {
        permissionService.hasAccess(TaskMessage.READ);
        return Ag.select(TaskMessage.class, config)
                .toManyParent(MongoUser.class, systemUserService.getSystemUser().getId(), MongoUser.COMPANIES.dot(Company.COMPANY_TASKS.dot(CompanyTask.TASK_MESSAGES)))
                .byId(id)
                .uri(uriInfo)
                .get();
    }

    @POST
    public SimpleResponse post(String data) {
        permissionService.hasAccess(TaskMessage.CREATE);
        return Ag.create(TaskMessage.class, config)
                .toManyParent(MongoUser.class, systemUserService.getSystemUser().getId(), MongoUser.COMPANIES.dot(Company.COMPANY_TASKS.dot(CompanyTask.TASK_MESSAGES)))
                .sync(data);
    }

    @PUT
    public SimpleResponse put(String data) {
        permissionService.hasAccess(TaskMessage.UPDATE);
        return Ag.createOrUpdate(TaskMessage.class, config)
                .toManyParent(MongoUser.class, systemUserService.getSystemUser().getId(), MongoUser.COMPANIES.dot(Company.COMPANY_TASKS.dot(CompanyTask.TASK_MESSAGES)))
                .sync(data);
    }

    @DELETE
    @Path("{messageId}")
    public SimpleResponse delete(@PathParam("messageId") int id) {
        permissionService.hasAccess(TaskMessage.DELETE);
        return Ag.delete(TaskMessage.class, config)
                .toManyParent(MongoUser.class, systemUserService.getSystemUser().getId(), MongoUser.COMPANIES.dot(Company.COMPANY_TASKS.dot(CompanyTask.TASK_MESSAGES)))
                .id(id)
                .delete();
    }

    @GET
    public DataResponse<TaskMessage> list(@Context UriInfo uriInfo) {
        permissionService.hasAccess(TaskMessage.LIST);
        return Ag.select(TaskMessage.class, config)
                .toManyParent(MongoUser.class, systemUserService.getSystemUser().getId(), MongoUser.COMPANIES.dot(Company.COMPANY_TASKS.dot(CompanyTask.TASK_MESSAGES)))
                .uri(uriInfo)
                .get();
    }
}
