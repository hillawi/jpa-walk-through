package org.hill.jpa.service.rest;

import org.hill.jpa.entity.Customer;
import org.hill.jpa.entity.PaginatedListWrapper;
import org.hill.jpa.service.CustomerService;

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
@Path("customers")
public class CustomerResource {
    @Inject
    CustomerService customerService;

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Customer> list() {
        System.out.println("Served at: " + LocalDateTime.now());
        return customerService.getAll();
    }

    @POST
    @Path("/pagination")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public PaginatedListWrapper<Customer> list(@DefaultValue("1") Integer page) {
        PaginatedListWrapper<Customer> listWrapper = new PaginatedListWrapper<>();
        listWrapper.setCurrentPage(page);
        listWrapper.setPageSize(10);
        return customerService.get(listWrapper);
    }

    @GET
    @Path("/customer/find/{customerId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Customer get(@PathParam("customerId") long id) {
        return customerService.get(id);
    }

    @POST
    @Path("/customer/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Customer add(Customer u) {
        return customerService.create(u);
    }

    @POST
    @Path("/customers/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<Customer> addAll(List<Customer> customers) {
        return customerService.create(customers);
    }
}
