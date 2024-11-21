package br.com.fiap.controller;

import br.com.fiap.dto.ClienteDto;
import br.com.fiap.exceptions.ClienteNotFoundException;
import br.com.fiap.exceptions.ClienteNotSavedException;
import br.com.fiap.exceptions.UnsupportedServiceOperationException;
import br.com.fiap.model.Cliente;
import br.com.fiap.service.ClienteService;
import br.com.fiap.service.ClienteServiceFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.Map;

@Path("/rest/cliente")
public class ClienteController {
    private final ClienteService clienteService = ClienteServiceFactory.create();

    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response add(ClienteDto input) throws UnsupportedServiceOperationException {
        if (input.getEmail() != null) {
            try {
                Cliente c1 = this.clienteService.create(new Cliente(input.getEmail(), input.getSenha()));
                return Response
                        .status(Response.Status.CREATED)
                        .entity(c1)
                        .build();
            } catch (SQLException e) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                        .entity(Map.of("mensagem", "erro inesperado ao tentar inserir os dados"))
                        .build();
            } catch (ClienteNotSavedException e) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                        .entity(Map.of("mensagem", "não foi possível salvar os dados"))
                        .build();
            } catch (UnsupportedServiceOperationException e) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(Map.of("mensagem", "operação não suportada"))
                        .build();
            }
        } else {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(Map.of("mensagem", "esse método só permite a criação de novas pessoas"))
                    .build();
        }
    }

    @GET
    @Path("/{email}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByEmail(@PathParam("email") String email) {
        return Response.status(Response.Status.OK)
                .entity(this.clienteService.findByEmail(email)).build();
    }

    @PUT
    @Path("/{email}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("email") String email, ClienteDto input){
        try {
            Cliente updated = this.clienteService.update(new Cliente(email, input.getSenha()));
            return Response.status(Response.Status.OK).entity(updated).build();
        } catch (ClienteNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (SQLException s) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Map.of("mensagem","erro inesperado ao tentar atualizar dados.")).build();
        }
    }

    @DELETE
    @Path("/{email}")
    public Response delete(@PathParam("email")String email){
        try {
            this.clienteService.deleteByEmail(email);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (ClienteNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (SQLException s) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Map.of("mensagem","erro inesperado ao tentar deletar dados")).build();
        }
    }

}

