package com.megapapa.sk.resource;

import com.google.inject.Inject;
import com.megapapa.sk.auth.service.IPermissionService;
import com.megapapa.sk.auth.service.ISystemUserService;
import com.megapapa.sk.cayenne.MongoUser;
import com.megapapa.sk.cayenne.MovieWatchTask;
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

@Path("movie_watch_task")
@Produces(MediaType.APPLICATION_JSON)
public class MovieWatchTaskResource {

    @Context
    private Configuration config;

    @Inject
    private ISystemUserService systemUserService;

    @Inject
    private IPermissionService permissionService;

    @GET
    @Path("{taskId}")
    public DataResponse<MovieWatchTask> get(@PathParam("taskId") int id, @Context UriInfo uriInfo) {
        permissionService.hasAccess(MovieWatchTask.READ);
        return Ag.select(MovieWatchTask.class, config)
                .toManyParent(MongoUser.class, systemUserService.getSystemUser().getId(), MongoUser.MOVIE_WATCH_TASKS)
                .byId(id)
                .uri(uriInfo)
                .get();
    }

    @POST
    public SimpleResponse post(String data) {
        permissionService.hasAccess(MovieWatchTask.CREATE);
        return Ag.create(MovieWatchTask.class, config)
                .toManyParent(MongoUser.class, systemUserService.getSystemUser().getId(), MongoUser.MOVIE_WATCH_TASKS)
                .sync(data);
    }

    @PUT
    public SimpleResponse put(String data) {
        permissionService.hasAccess(MovieWatchTask.UPDATE);
        return Ag.createOrUpdate(MovieWatchTask.class, config)
                .toManyParent(MongoUser.class, systemUserService.getSystemUser().getId(), MongoUser.MOVIE_WATCH_TASKS)
                .sync(data);
    }

    @DELETE
    @Path("{taskId}")
    public SimpleResponse delete(@PathParam("taskId") int id) {
        permissionService.hasAccess(MovieWatchTask.DELETE);
        return Ag.delete(MovieWatchTask.class, config)
                .toManyParent(MongoUser.class, systemUserService.getSystemUser().getId(), MongoUser.MOVIE_WATCH_TASKS)
                .id(id)
                .delete();
    }

    @GET
    public DataResponse<MovieWatchTask> list(@Context UriInfo uriInfo) {
        permissionService.hasAccess(MovieWatchTask.LIST);
        return Ag.select(MovieWatchTask.class, config)
                .toManyParent(MongoUser.class, systemUserService.getSystemUser().getId(), MongoUser.MOVIE_WATCH_TASKS)
                .uri(uriInfo)
                .get();
    }
}
