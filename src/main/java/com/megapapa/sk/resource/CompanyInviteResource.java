package com.megapapa.sk.resource;

import com.google.inject.Inject;
import com.megapapa.sk.auth.service.IPermissionService;
import com.megapapa.sk.auth.service.ISystemUserService;
import com.megapapa.sk.cayenne.Company;
import com.megapapa.sk.cayenne.CompanyInvite;
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

@Path("company_invite")
@Produces(MediaType.APPLICATION_JSON)
public class CompanyInviteResource {

    @Context
    private Configuration config;

    @Inject
    private ISystemUserService systemUserService;

    @Inject
    private IPermissionService permissionService;

    @GET
    @Path("{inviteId}")
    public DataResponse<CompanyInvite> get(@PathParam("inviteId") int id, @Context UriInfo uriInfo) {
        permissionService.hasAccess(CompanyInvite.READ);
        return Ag.select(CompanyInvite.class, config)
                .toManyParent(MongoUser.class, systemUserService.getSystemUser().getId(), MongoUser.COMPANY_INVITES)
                .byId(id)
                .uri(uriInfo)
                .get();
    }

    @POST
    public SimpleResponse post(String data) {
        permissionService.hasAccess(CompanyInvite.CREATE);
        return Ag.create(CompanyInvite.class, config)
                .toManyParent(MongoUser.class, systemUserService.getSystemUser().getId(), MongoUser.COMPANIES.dot(Company.COMPANY_INVITES))
                .sync(data);
    }

    @PUT
    public SimpleResponse put(String data) {
        permissionService.hasAccess(CompanyInvite.UPDATE);
        return Ag.createOrUpdate(CompanyInvite.class, config)
                .toManyParent(MongoUser.class, systemUserService.getSystemUser().getId(), MongoUser.COMPANIES.dot(Company.COMPANY_INVITES))
                .sync(data);
    }

    @DELETE
    @Path("{inviteId}")
    public SimpleResponse delete(@PathParam("inviteId") int id) {
        permissionService.hasAccess(CompanyInvite.DELETE);
        return Ag.delete(CompanyInvite.class, config)
                .toManyParent(MongoUser.class, systemUserService.getSystemUser().getId(), MongoUser.COMPANIES.dot(Company.COMPANY_INVITES))
                .id(id)
                .delete();
    }

    @GET
    public DataResponse<CompanyInvite> list(@Context UriInfo uriInfo) {
        permissionService.hasAccess(CompanyInvite.LIST);
        return Ag.select(CompanyInvite.class, config)
                .toManyParent(MongoUser.class, systemUserService.getSystemUser().getId(), MongoUser.COMPANY_INVITES)
                .uri(uriInfo)
                .get();
    }
}
