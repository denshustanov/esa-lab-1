package org.example.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.dto.RequestError;
import org.example.exception.ResourceNotFoundException;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ControllerExceptionHandler implements ExceptionMapper<ResourceNotFoundException> {
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    @Produces(MediaType.APPLICATION_JSON)
    public Response toResponse(ResourceNotFoundException e) {
        RequestError error = new RequestError(RequestError.NOT_FOUND, e.getMessage());
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(error)
                    .build();
    }
}
