package org.ejercicio.api;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.ejercicio.model.Alumno;
import org.ejercicio.model.Curso;
import org.ejercicio.repository.CursoRepository;

import java.util.List;

import static com.arjuna.common.logging.commonLogger.logger;

@Path("/curso")
public class ApiCurso {
    @Inject
    CursoRepository cr;

    @GET
    public List<Curso> list() {
        return cr.lisCurso();
    }

    @GET
    @Path("/{Id}")
    public Curso getById(@QueryParam("Id") Long Id) {
        return cr.findCurso(Id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response add(Curso c) {
        cr.crearCurso(c);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{Id}")
    public Response delete(@QueryParam("Id") Long Id) {
        cr.deleteCurso( cr.findCurso(Id));
        return Response.ok().build();
    }
}
