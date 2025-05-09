package com.kosmos.consultorio.persistence.entity.dao.implementation;

import com.kosmos.consultorio.persistence.entity.CitaEntity;
import com.kosmos.consultorio.persistence.entity.ConsultorioEntity;
import com.kosmos.consultorio.persistence.entity.DoctorEntity;
import com.kosmos.consultorio.persistence.entity.dao.interfaces.ICitaDao;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class CitaDaoImp implements ICitaDao {
    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional(readOnly = true)
    public List<CitaEntity> findAll() {
        return this.em.createQuery("Select c from CitaEntity c").getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CitaEntity> findById(Long id) {
        return Optional.ofNullable(this.em.find(CitaEntity.class, id));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<CitaEntity>> findByValidateConsultorioFecha(Long idConsultorio,LocalDateTime horarioConsulta) {
        String jpql = "SELECT * FROM Citas c WHERE c.horario_consulta = :fecha AND c.consultorio_id = :idConsultorio";
        return Optional.ofNullable(em.createQuery(jpql, CitaEntity.class)
                .setParameter("fecha", horarioConsulta)
                .setParameter("idConsultorio", idConsultorio)
                .getResultList());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<CitaEntity>> findByFecha(LocalDateTime horarioConsulta) {
        String jpql = "SELECT * FROM Citas c WHERE c.horario_consulta BETWEEN :inicio AND :fin";
        LocalDateTime inicio = horarioConsulta.withHour(0).withMinute(0).withSecond(0).withNano(0);
        LocalDateTime fin = horarioConsulta.withHour(23).withMinute(59).withSecond(0).withNano(0);
        return Optional.ofNullable(em.createQuery(jpql, CitaEntity.class)
                .setParameter("inicio", inicio)
                .setParameter("fin", fin)
                .getResultList());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ConsultorioEntity> findByConsultorio(Long consultorioId) {
        return Optional.ofNullable(this.em.find(ConsultorioEntity.class, consultorioId));
//        String jpql = "SELECT c FROM Cita c WHERE c.consultorio_id = :consultorioId";
//        return Optional.ofNullable(this.em.createQuery(jpql, CitaEntity.class)
//                .setParameter("consultorioId", consultorioId)
//                .getResultList());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<DoctorEntity> findByDoctor(Long doctorId) {
        return Optional.ofNullable(this.em.find(DoctorEntity.class, doctorId));
//        String jpql = "SELECT c FROM Doctores d WHERE d.doctor_id = :doctorId";
//        return Optional.ofNullable(this.em.createQuery(jpql, DoctorEntity.class)
//                .setParameter("doctorId", doctorId)
//                .getResultList());
    }

    @Override
    @Transactional
    public void saveCita(CitaEntity citaEntity) {
        this.em.persist(citaEntity);
        this.em.flush();
    }

    @Override
    @Transactional
    public void updateCita(CitaEntity citaEntity) {
        this.em.merge(citaEntity);
        this.em.flush();
    }

    @Override
    @Transactional
    public void deleteCita(CitaEntity citaEntity) {
        this.em.remove(citaEntity);
        this.em.flush();
    }
}
