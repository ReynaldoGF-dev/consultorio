package com.kosmos.consultorio.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "citas")
public class CitaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private ConsultorioEntity consultorio;

    @ManyToOne
    private DoctorEntity doctor;

    @Column(name = "horario_consulta")
    private LocalDateTime horarioConsulta;
    @Column(name = "nombre_paciente")
    private String nombrePaciente;
}
