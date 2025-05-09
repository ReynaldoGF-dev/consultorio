package com.kosmos.consultorio.service.interfaces;

import com.kosmos.consultorio.presentation.dto.CitaDTO;
import com.kosmos.consultorio.presentation.dto.CitaDataDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ICitaService {
    List<CitaDataDTO> findAll();
    CitaDataDTO findById(Long id);
    Optional<List<CitaDTO>> findByFecha(LocalDateTime horarioConsulta);
    Optional<List<CitaDTO>> findByConsultorio(Long id);
    Optional<List<CitaDTO>> findByDoctor(Long id);
    CitaDTO saveCita(CitaDTO citaDTO);
    CitaDTO updateCita(CitaDTO citaDTO);
    String deleteCita(Long id);
}
