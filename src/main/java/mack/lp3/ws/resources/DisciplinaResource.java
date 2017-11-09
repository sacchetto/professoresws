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

@Path("disciplinas")
@Produces(MediaType.APPLICATION_JSON)
public class DisciplinaResource {
    private Dao<Disciplina> dao = null;
    
    public DisciplinaResource() {
        try {
            ConexaoDb conexao;
            conexao = new ConexaoDb("jdbc:derby://127.0.0.1:1527/faculdade", "app", "app");
            dao = new DisciplinasDao(conexao);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @GET
    @RolesAllowed("USER")
    public List<Disciplina> listar() {
        List<Disciplina> disciplinas = null;
        try {
            disciplinas = dao.ler();
        } catch(Exception e) {
            e.printStackTrace();
        }
        return disciplinas;
    }
    
    @GET
    @Path("{id}")
    public Disciplina buscar(@PathParam("id") LongParam id) {
        Disciplina d = null;
        try {
            d = dao.lerPeloId(id.get());
        } catch(Exception e) {
            e.printStackTrace();
        }
        if (d == null) {
            throw new WebApplicationException("Disciplina não encontrado", 404);
        }
            return d;
    }
    
    @POST
    @RolesAllowed("ADMIN")
    public Disciplina criar(Disciplina disciplina) {
        try {
            long id = dao.criar(disciplina);
            disciplina.setId(id);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return disciplina;
    }

    @PUT
    @Path("{id}")
    public Disciplina atualizar(@PathParam("id") LongParam id, Disciplina disciplina) {
        try {
            int n = dao.atualizar(disciplina);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return disciplina;
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
            throw new WebApplicationException("Disciplina com id=" + id.get()
            + " não encontrada!", 404);
        }
        return Response.ok().build();
    }
    

  
}
