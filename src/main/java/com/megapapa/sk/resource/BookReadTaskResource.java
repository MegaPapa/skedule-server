package com.megapapa.sk.resource;

import com.google.inject.Inject;
import com.megapapa.sk.auth.annotation.SecureApi;
import com.megapapa.sk.auth.service.ISystemUserService;
import com.megapapa.sk.cayenne.BookReadTask;
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

@Path("book_read_task")
@Produces(MediaType.APPLICATION_JSON)
public class BookReadTaskResource {

    @Context
    private Configuration config;
    
    @Inject
    private ISystemUserService systemUserService;

    @GET
    @Path("{taskId}")
    @SecureApi(permission = "bookReadTask:read")
    public DataResponse<BookReadTask> get(@PathParam("taskId") int id, @Context UriInfo uriInfo) {
        return Ag.select(BookReadTask.class, config)
                .toManyParent(MongoUser.class, systemUserService.getSystemUser().getId(), MongoUser.BOOK_READ_TASKS)
                .byId(id)
                .uri(uriInfo)
                .get();
    }

    @POST
    @SecureApi(permission = "bookReadTask:create")
    public SimpleResponse post(String data) {
        return Ag.create(BookReadTask.class, config)
                .toManyParent(MongoUser.class, systemUserService.getSystemUser().getId(), MongoUser.BOOK_READ_TASKS)
                .sync(data);
    }

    @PUT
    @SecureApi(permission = "bookReadTask:update")
    public SimpleResponse put(String data) {
        return Ag.createOrUpdate(BookReadTask.class, config)
                .toManyParent(MongoUser.class, systemUserService.getSystemUser().getId(), MongoUser.BOOK_READ_TASKS)
                .sync(data);
    }

    @DELETE
    @Path("{taskId}")
    @SecureApi(permission = "bookReadTask:delete")
    public SimpleResponse delete(@PathParam("taskId") int id) {
        return Ag.delete(BookReadTask.class, config)
                .toManyParent(MongoUser.class, systemUserService.getSystemUser().getId(), MongoUser.BOOK_READ_TASKS)
                .id(id)
                .delete();
    }

    @GET
    @SecureApi(permission = "bookReadTask:list")
    public DataResponse<BookReadTask> list(@Context UriInfo uriInfo) {
        return Ag.select(BookReadTask.class, config)
                .toManyParent(MongoUser.class, systemUserService.getSystemUser().getId(), MongoUser.BOOK_READ_TASKS)
                .uri(uriInfo)
                .get();
    }
}
