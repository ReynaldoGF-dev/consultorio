package com.kosmos.consultorio.presentation.dto;

import com.kosmos.consultorio.persistence.entity.ConsultorioEntity;
import com.kosmos.consultorio.persistence.entity.DoctorEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CitaDataDTO {
    private Long id;
    private ConsultorioEntity consultorio;
    private DoctorEntity doctor;
    private LocalDateTime horarioConsulta;
    private String nombrePaciente;
}