package com.kosmos.consultorio.presentation.dto;

import com.kosmos.consultorio.persistence.entity.ConsultorioEntity;
import com.kosmos.consultorio.persistence.entity.DoctorEntity;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CitaDTO {
    private Long id;
    private Long consultorio;
    private Long doctor;
    private LocalDateTime horarioConsulta;
    private String nombrePaciente;
}
