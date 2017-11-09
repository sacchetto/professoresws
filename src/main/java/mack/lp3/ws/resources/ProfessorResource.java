package mack.lp3.ws.resources;

import java.util.Date;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import io.dropwizard.jersey.params.LongParam;
import mack.lp3.dao.*;
import javax.annotation.security.RolesAllowed;
import javax.annotation.security.PermitAll;

@Path("professores")
@Produces(MediaType.APPLICATION_JSON)
public class ProfessorResource {
    private Dao<Professor> dao = null;
    
    public ProfessorResource() {
        try {
            ConexaoDb conexao;
            conexao = new ConexaoDb("jdbc:derby://127.0.0.1:1527/faculdade", "app", "app");
            dao = new ProfessoresDao(conexao);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @GET
    @RolesAllowed("USER")
    public List<Professor> listar() {
        List<Professor> professores = null;
        try {
            professores = dao.ler();
        } catch(Exception e) {
            e.printStackTrace();
        }
        return professores;
    }
    
    @GET
    @Path("{id}")
    public Professor buscar(@PathParam("id") LongParam id) {
        Professor p = null;
        try {
            p = dao.lerPeloId(id.get());
        } catch(Exception e) {
            e.printStackTrace();
        }
        if (p == null) {
            throw new WebApplicationException("Professor não encontrado", 404);
        }
            return p;
    }
    
    @POST
    @RolesAllowed("ADMIN")
    public Professor criar(Professor professor) {
        try {
            long id = dao.criar(professor);
            professor.setId(id);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return professor;
    }

    @PUT
    @Path("{id}")
    public Professor atualizar(@PathParam("id") LongParam id, Professor professor) {
        try {
            int n = dao.atualizar(professor);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return professor;
    }

    @DELETE
    @Path("{id}")
    public Response remover(@PathParam("id") LongParam id) {
        int n = 1;
        try {
            n = dao.apagar(id.get());
        } catch(Exception e) {
            e.printStackTrace();
            throw new WebApplicationException("Erro ao tentar apagar!", 500);
        }
        if (n <= 0) {
            throw new WebApplicationException("Professor com id=" + id.get()
            + " não encontrado!", 404);
        }
        return Response.ok().build();
    }
    

  
}
