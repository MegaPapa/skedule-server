package com.megapapa.sk.resource;

import com.google.inject.Inject;
import com.megapapa.sk.auth.annotation.SecureApi;
import com.megapapa.sk.auth.service.ISystemUserService;
import com.megapapa.sk.cayenne.Company;
import com.megapapa.sk.cayenne.CompanyTask;
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

@Path("company_task")
@Produces(MediaType.APPLICATION_JSON)
public class CompanyTaskResource {

    @Context
    private Configuration config;

    @Inject
    private ISystemUserService systemUserService;

    @GET
    @Path("{taskId}")
    @SecureApi(permission = "companyTask:read")
    public DataResponse<CompanyTask> get(@PathParam("taskId") int id, @Context UriInfo uriInfo) {
        return Ag.select(CompanyTask.class, config)
                .toManyParent(MongoUser.class, systemUserService.getSystemUser().getId(), MongoUser.COMPANIES.dot(Company.COMPANY_TASKS))
                .byId(id)
                .uri(uriInfo)
                .get();
    }

    @POST
    @SecureApi(permission = "companyTask:create")
    public SimpleResponse post(String data) {
        return Ag.create(CompanyTask.class, config)
                .toManyParent(MongoUser.class, systemUserService.getSystemUser().getId(), MongoUser.COMPANIES.dot(Company.COMPANY_TASKS))
                .sync(data);
    }

    @PUT
    @SecureApi(permission = "companyTask:update")
    public SimpleResponse put(String data) {
        return Ag.createOrUpdate(CompanyTask.class, config)
                .toManyParent(MongoUser.class, systemUserService.getSystemUser().getId(), MongoUser.COMPANIES.dot(Company.COMPANY_TASKS))
                .sync(data);
    }

    @DELETE
    @Path("{taskId}")
    @SecureApi(permission = "companyTask:delete")
    public SimpleResponse delete(@PathParam("taskId") int id) {
        return Ag.delete(CompanyTask.class, config)
                .toManyParent(MongoUser.class, systemUserService.getSystemUser().getId(), MongoUser.COMPANIES.dot(Company.COMPANY_TASKS))
                .id(id)
                .delete();
    }

    @GET
    @SecureApi(permission = "companyTask:list")
    public DataResponse<CompanyTask> list(@Context UriInfo uriInfo) {
        return Ag.select(CompanyTask.class, config)
                .toManyParent(MongoUser.class, systemUserService.getSystemUser().getId(), MongoUser.COMPANIES.dot(Company.COMPANY_TASKS))
                .uri(uriInfo)
                .get();
    }
}