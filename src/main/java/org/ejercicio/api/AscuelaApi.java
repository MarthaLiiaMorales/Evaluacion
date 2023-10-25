package org.ejercicio.api;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.ejercicio.model.Alumno;
import org.ejercicio.model.Curso;
import org.ejercicio.repository.AlumnoRepository;
import org.ejercicio.repository.CursoRepository;

import java.util.List;

@Path("/escuela")
public class AscuelaApi {

    @Inject
    AlumnoRepository ar;
    @Inject
    CursoRepository cr;


    @GET
    @Path("/Alumnos")
    public List<Alumno> list() {
        return ar.lisAlumno();
    }

    @GET
    @Path("/{Id}/Alumnos")
    public Alumno getById(@QueryParam("Id") Long Id) {
        return ar.findAlumno(Id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response add(Alumno a) {
        String status = verificarAprobacion(a);
        a.setStatus(status);
        ar.crearAlumno(a);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{Id}")
    public Response delete(@QueryParam("Id") Long Id) {
        ar.deleteAlumno( ar.findAlumno(Id));
        return Response.ok().build();
    }

    @PUT
    public Response update(Alumno a) {
        Alumno alumno = ar.findAlumno(a.getId());
        alumno.setAsistencia(a.getAsistencia());
        alumno.setCalificacion(a.getCalificacion());
        alumno.setId_curso(a.getId_curso());
        String status = verificarAprobacion(a);
        alumno.setStatus(status);
        ar.updateAlumno(alumno);
        return Response.ok().build();
    }


    public String verificarAprobacion(Alumno a) {
        Long Idcurso = a.getId_curso().longValue();
        Curso curso = cr.findCurso(Idcurso);
        if (a.getCalificacion() >= 7.0 && a.getAsistencia() >= 80 && curso.getTipo().equals("basico")) {
            return "Aprobado";
        }
        return "No Aprobado";
    }
}
