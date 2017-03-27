package org.hill.jpa.service.rest;

import org.hill.jpa.entity.User;
import org.hill.jpa.service.UserService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Hillawi on 25-03-17.
 */
@Path("users")
public class UserResource {
    @Inject
    UserService userService;

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> listUsers() {
        System.out.println("Served at: " + LocalDateTime.now());
        return userService.getUsers();
    }

    @GET
    @Path("/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public User getUser(@PathParam("userId") long id) {
        return userService.getUser(id);
    }

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public User addUser(User u) {
        return userService.createUser(u);
    }
}
