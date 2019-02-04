package com.megapapa.sk.resource;

import com.google.inject.Inject;
import com.megapapa.sk.auth.annotation.SecureApi;
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

    @GET
    @Path("{taskId}")
    @SecureApi(permission = "movieWatchTask:read")
    public DataResponse<MovieWatchTask> get(@PathParam("taskId") int id, @Context UriInfo uriInfo) {
        return Ag.select(MovieWatchTask.class, config)
                .toManyParent(MongoUser.class, systemUserService.getSystemUser().getId(), MongoUser.MOVIE_WATCH_TASKS)
                .byId(id)
                .uri(uriInfo)
                .get();
    }

    @POST
    @SecureApi(permission = "movieWatchTask:create")
    public SimpleResponse post(String data) {
        return Ag.create(MovieWatchTask.class, config)
                .toManyParent(MongoUser.class, systemUserService.getSystemUser().getId(), MongoUser.MOVIE_WATCH_TASKS)
                .sync(data);
    }

    @PUT
    @SecureApi(permission = "movieWatchTask:update")
    public SimpleResponse put(String data) {
        return Ag.createOrUpdate(MovieWatchTask.class, config)
                .toManyParent(MongoUser.class, systemUserService.getSystemUser().getId(), MongoUser.MOVIE_WATCH_TASKS)
                .sync(data);
    }

    @DELETE
    @Path("{taskId}")
    @SecureApi(permission = "movieWatchTask:delete")
    public SimpleResponse delete(@PathParam("taskId") int id) {
        return Ag.delete(MovieWatchTask.class, config)
                .toManyParent(MongoUser.class, systemUserService.getSystemUser().getId(), MongoUser.MOVIE_WATCH_TASKS)
                .id(id)
                .delete();
    }

    @GET
    @SecureApi(permission = "movieWatchTask:list")
    public DataResponse<MovieWatchTask> list(@Context UriInfo uriInfo) {
        return Ag.select(MovieWatchTask.class, config)
                .toManyParent(MongoUser.class, systemUserService.getSystemUser().getId(), MongoUser.MOVIE_WATCH_TASKS)
                .uri(uriInfo)
                .get();
    }
}
