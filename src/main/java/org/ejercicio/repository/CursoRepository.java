package org.ejercicio.repository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.ejercicio.model.Curso;

import java.util.List;

@ApplicationScoped
public class CursoRepository {

    @Inject
    EntityManager em;

    @Transactional
    public void crearCurso (Curso c){
        em.persist(c);
    }

    @Transactional
    public void deleteCurso (Curso c){
        em.remove(c);
    }

    @Transactional
    public List<Curso> lisCurso() {
        List<Curso> cursos = em.createQuery("select a from Curso a").getResultList();
        return cursos;
    }

    @Transactional
    public Curso findCurso(Long Id){

        return em.find(Curso.class, Id);
    }
    @Transactional
    public void updateCurso(Curso c){
        em.merge(c);
    }
}
