package com.megapapa.sk.resource;

import com.google.inject.Inject;
import com.megapapa.sk.auth.service.IPermissionService;
import com.megapapa.sk.auth.service.ISystemUserService;
import com.megapapa.sk.cayenne.Company;
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

@Path("company")
@Produces(MediaType.APPLICATION_JSON)
public class CompanyResource {

    @Context
    private Configuration config;

    @Inject
    private ISystemUserService systemUserService;

    @Inject
    private IPermissionService permissionService;

    @GET
    @Path("{companyId}")
    public DataResponse<Company> get(@PathParam("companyId") int id, @Context UriInfo uriInfo) {
        permissionService.hasAccess(Company.READ);
        return Ag.select(Company.class, config)
                .toManyParent(MongoUser.class, systemUserService.getSystemUser().getId(), MongoUser.COMPANIES)
                .byId(id)
                .uri(uriInfo)
                .get();
    }

    @POST
    public SimpleResponse post(String data) {
        permissionService.hasAccess(Company.CREATE);
        return Ag.create(Company.class, config)
                .toManyParent(MongoUser.class, systemUserService.getSystemUser().getId(), MongoUser.COMPANIES)
                .sync(data);
    }

    @PUT
    public SimpleResponse put(String data) {
        permissionService.hasAccess(Company.UPDATE);
        return Ag.createOrUpdate(Company.class, config)
                .toManyParent(MongoUser.class, systemUserService.getSystemUser().getId(), MongoUser.COMPANIES)
                .sync(data);
    }

    @DELETE
    @Path("{companyId}")
    public SimpleResponse delete(@PathParam("companyId") int id) {
        permissionService.hasAccess(Company.DELETE);
        return Ag.delete(Company.class, config)
                .toManyParent(MongoUser.class, systemUserService.getSystemUser().getId(), MongoUser.COMPANIES)
                .id(id)
                .delete();
    }

    @GET
    public DataResponse<Company> list(@Context UriInfo uriInfo) {
        permissionService.hasAccess(Company.LIST);
        return Ag.select(Company.class, config)
                .uri(uriInfo)
                .get();
    }
}
