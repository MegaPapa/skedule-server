package com.megapapa.sk.resource;

import com.google.inject.Inject;
import com.megapapa.sk.auth.service.IPermissionService;
import com.megapapa.sk.auth.service.ISystemUserService;
import com.megapapa.sk.cayenne.Company;
import com.megapapa.sk.cayenne.MongoUser;
import com.megapapa.sk.resource.error.HasNoAccessResponse;
import io.agrest.Ag;
import io.agrest.AgResponse;
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
    public AgResponse get(@PathParam("companyId") int id, @Context UriInfo uriInfo) {
        if (!permissionService.hasAccess(Company.READ)) {
            return new HasNoAccessResponse(Company.READ);
        }
        permissionService.hasAccess(Company.READ);
        return Ag.select(Company.class, config)
                .toManyParent(MongoUser.class, systemUserService.getSystemUser().getId(), MongoUser.COMPANIES)
                .byId(id)
                .uri(uriInfo)
                .get();
    }

    @POST
    public AgResponse post(String data) {
        if (!permissionService.hasAccess(Company.CREATE)) {
            return new HasNoAccessResponse(Company.CREATE);
        }
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
        if (!permissionService.hasAccess(Company.DELETE)) {
            return new HasNoAccessResponse(Company.DELETE);
        }
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
