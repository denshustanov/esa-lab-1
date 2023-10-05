package org.example.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.dto.OrderCreationDTO;
import org.example.dto.OrderDTO;
import org.example.dto.OrderUpdateDTO;
import org.example.service.OrderService;

import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.UUID;

@WebServlet
@Path("/orders")
public class OrderController {
    private final OrderService orderService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Inject
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GET
    @Path("/")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getOrders(@QueryParam("page") int page,
                              @QueryParam("size") int size) {
        return Response.status(Response.Status.OK)
                .entity(orderService.getAll(page, size))
                .build();
    }

    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getOrder(@PathParam("id") UUID id) {
        OrderDTO dto = orderService.getOrder(id);
        return Response.status(Response.Status.OK)
                .entity(dto)
                .build();
    }

    @PUT
    @Path("/{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response updateOrder(@PathParam("id") UUID id,
                                OrderUpdateDTO orderUpdateDto) throws JsonProcessingException {
        OrderDTO orderDTO = orderService.update(id, orderUpdateDto);
        return Response.status(Response.Status.OK)
                .entity(objectMapper.writeValueAsString(orderDTO))
                .build();
    }

    @POST
    @Path("/")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response createOrder(OrderCreationDTO creationDTO) {
        OrderDTO dto = orderService.save(creationDTO);
        return Response.status(Response.Status.OK)
                .entity(dto)
                .build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteOrder(@PathParam("id") UUID orderId) {
        orderService.delete(orderId);
        return Response.status(Response.Status.OK)
                .build();
    }
}
