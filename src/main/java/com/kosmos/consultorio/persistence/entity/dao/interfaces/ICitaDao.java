package com.kosmos.consultorio.persistence.entity.dao.interfaces;

import com.kosmos.consultorio.persistence.entity.CitaEntity;
import com.kosmos.consultorio.persistence.entity.ConsultorioEntity;
import com.kosmos.consultorio.persistence.entity.DoctorEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ICitaDao {
    List<CitaEntity> findAll();
    Optional<CitaEntity> findById(Long id);
    Optional<List<CitaEntity>> findByFecha(LocalDateTime horarioConsulta);
    Optional<ConsultorioEntity> findByConsultorio(Long id);
    Optional<DoctorEntity> findByDoctor(Long id);
    Optional<List<CitaEntity>> findByValidateConsultorioFecha(Long id,LocalDateTime horarioConsulta);
    void saveCita(CitaEntity citaEntity);
    void updateCita(CitaEntity citaEntity);
    void deleteCita(CitaEntity citaEntity);
}
