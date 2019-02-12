package com.megapapa.sk.resource;

import com.google.inject.Inject;
import com.megapapa.sk.auth.service.IPermissionService;
import com.megapapa.sk.auth.service.ISystemUserService;
import com.megapapa.sk.cayenne.Country;
import io.agrest.Ag;
import io.agrest.DataResponse;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Configuration;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

@Path("country")
@Produces(MediaType.APPLICATION_JSON)
public class CountryResource {
    
    @Context
    private Configuration config;

    @Inject
    private ISystemUserService systemUserService;

    @Inject
    private IPermissionService permissionService;

    @GET
    @Path("{countryId}")
    public DataResponse<Country> get(@PathParam("countryId") int id, @Context UriInfo uriInfo) {
        permissionService.hasAccess(Country.READ);
        return Ag.select(Country.class, config)
                .byId(id)
                .uri(uriInfo)
                .get();
    }

    @GET
    public DataResponse<Country> list(@Context UriInfo uriInfo) {
        permissionService.hasAccess(Country.LIST);
        return Ag.select(Country.class, config)
                .uri(uriInfo)
                .get();
    }
}
