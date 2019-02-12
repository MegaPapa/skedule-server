package com.megapapa.sk.resource;

import com.google.inject.Inject;
import com.megapapa.sk.auth.service.IPermissionService;
import com.megapapa.sk.auth.service.ISystemUserService;
import com.megapapa.sk.cayenne.Language;
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

@Path("language")
@Produces(MediaType.APPLICATION_JSON)
public class LanguageResource {

    @Context
    private Configuration config;

    @Inject
    private ISystemUserService systemUserService;

    @Inject
    private IPermissionService permissionService;

    @GET
    @Path("{languageId}")
    public DataResponse<Language> get(@PathParam("languageId") int id, @Context UriInfo uriInfo) {
        permissionService.hasAccess(Language.READ);
        return Ag.select(Language.class, config)
                .byId(id)
                .uri(uriInfo)
                .get();
    }

    @GET
    public DataResponse<Language> list(@Context UriInfo uriInfo) {
        permissionService.hasAccess(Language.LIST);
        return Ag.select(Language.class, config)
                .uri(uriInfo)
                .get();
    }
}
