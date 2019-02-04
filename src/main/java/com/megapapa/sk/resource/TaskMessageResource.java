package com.megapapa.sk.resource;

import com.google.inject.Inject;
import com.megapapa.sk.auth.annotation.SecureApi;
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

    @GET
    @Path("{messageId}")
    @SecureApi(permission = "taskMessage:read")
    public DataResponse<TaskMessage> get(@PathParam("messageId") int id, @Context UriInfo uriInfo) {
        return Ag.select(TaskMessage.class, config)
                .toManyParent(MongoUser.class, systemUserService.getSystemUser().getId(), MongoUser.COMPANIES.dot(Company.COMPANY_TASKS.dot(CompanyTask.TASK_MESSAGES)))
                .byId(id)
                .uri(uriInfo)
                .get();
    }

    @POST
    @SecureApi(permission = "taskMessage:create")
    public SimpleResponse post(String data) {
        return Ag.create(TaskMessage.class, config)
                .toManyParent(MongoUser.class, systemUserService.getSystemUser().getId(), MongoUser.COMPANIES.dot(Company.COMPANY_TASKS.dot(CompanyTask.TASK_MESSAGES)))
                .sync(data);
    }

    @PUT
    @SecureApi(permission = "taskMessage:update")
    public SimpleResponse put(String data) {
        return Ag.createOrUpdate(TaskMessage.class, config)
                .toManyParent(MongoUser.class, systemUserService.getSystemUser().getId(), MongoUser.COMPANIES.dot(Company.COMPANY_TASKS.dot(CompanyTask.TASK_MESSAGES)))
                .sync(data);
    }

    @DELETE
    @Path("{messageId}")
    @SecureApi(permission = "taskMessage:delete")
    public SimpleResponse delete(@PathParam("messageId") int id) {
        return Ag.delete(TaskMessage.class, config)
                .toManyParent(MongoUser.class, systemUserService.getSystemUser().getId(), MongoUser.COMPANIES.dot(Company.COMPANY_TASKS.dot(CompanyTask.TASK_MESSAGES)))
                .id(id)
                .delete();
    }

    @GET
    @SecureApi(permission = "taskMessage:list")
    public DataResponse<TaskMessage> list(@Context UriInfo uriInfo) {
        return Ag.select(TaskMessage.class, config)
                .toManyParent(MongoUser.class, systemUserService.getSystemUser().getId(), MongoUser.COMPANIES.dot(Company.COMPANY_TASKS.dot(CompanyTask.TASK_MESSAGES)))
                .uri(uriInfo)
                .get();
    }
}
