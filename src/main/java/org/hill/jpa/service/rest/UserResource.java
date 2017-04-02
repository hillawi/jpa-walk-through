package org.hill.jpa.service.rest;

import org.hill.jpa.entity.PaginatedListWrapper;
import org.hill.jpa.entity.User;
import org.hill.jpa.service.UserService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Hillawi on 25-03-17.
 */
@Stateless
@Path("users")
public class UserResource {
    @Inject
    UserService userService;

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> listUsers() {
        System.out.println("Served at: " + LocalDateTime.now());
        return userService.getAllUsers();
    }

    @POST
    @Path("/pagination")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public PaginatedListWrapper<User> listUsers(@DefaultValue("1") Integer page) {
        PaginatedListWrapper<User> listWrapper = new PaginatedListWrapper<>();
        listWrapper.setCurrentPage(page);
        listWrapper.setPageSize(10);
        return userService.getUsers(listWrapper);
    }

    @GET
    @Path("/user/find/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public User getUser(@PathParam("userId") long id) {
        return userService.getUser(id);
    }

    @POST
    @Path("/user/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public User addUser(User u) {
        return userService.createUser(u);
    }

    @POST
    @Path("/users/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> addUser(List<User> users) {
        return userService.createUsers(users);
    }
}
