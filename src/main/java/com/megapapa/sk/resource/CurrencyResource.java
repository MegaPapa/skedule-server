package com.megapapa.sk.resource;

import com.google.inject.Inject;
import com.megapapa.sk.auth.service.IPermissionService;
import com.megapapa.sk.auth.service.ISystemUserService;
import com.megapapa.sk.cayenne.Currency;
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

@Path("currency")
@Produces(MediaType.APPLICATION_JSON)
public class CurrencyResource {

    @Context
    private Configuration config;

    @Inject
    private ISystemUserService systemUserService;

    @Inject
    private IPermissionService permissionService;

    @GET
    @Path("{currencyId}")
    public DataResponse<Currency> get(@PathParam("currencyId") int id, @Context UriInfo uriInfo) {
        permissionService.hasAccess(Currency.READ);
        return Ag.select(Currency.class, config)
                .byId(id)
                .uri(uriInfo)
                .get();
    }

    @GET
    public DataResponse<Currency> list(@Context UriInfo uriInfo) {
        permissionService.hasAccess(Currency.LIST);
        return Ag.select(Currency.class, config)
                .uri(uriInfo)
                .get();
    }
}
