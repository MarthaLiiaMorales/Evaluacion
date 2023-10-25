package org.ejercicio.repository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.ejercicio.model.Alumno;

import java.util.List;

@ApplicationScoped
public class AlumnoRepository {

    @Inject
    EntityManager em;

    //Crear
    @Transactional
    public void crearAlumno(Alumno a) {
        em.persist(a);
    }

    //Eliminar
    @Transactional
    public void deleteAlumno(Alumno a) {
        em.remove(a);
    }

    //Consulta
    @Transactional
    public List<Alumno> lisAlumno() {
        List<Alumno> alumnos = em.createQuery("select a from Alumno a").getResultList();
        return alumnos;
    }

    //Buscar
    @Transactional
    public Alumno findAlumno(Long Id) {

        return em.find(Alumno.class, Id);
    }

    //Actualizar
    @Transactional
    public void updateAlumno(Alumno a) {
        em.merge(a);
    }

}
