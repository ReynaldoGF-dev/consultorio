package com.kosmos.consultorio.service.implementation;

import com.kosmos.consultorio.persistence.entity.CitaEntity;
import com.kosmos.consultorio.persistence.entity.ConsultorioEntity;
import com.kosmos.consultorio.persistence.entity.DoctorEntity;
import com.kosmos.consultorio.persistence.entity.dao.interfaces.ICitaDao;
import com.kosmos.consultorio.presentation.dto.CitaDTO;
import com.kosmos.consultorio.presentation.dto.CitaDataDTO;
import com.kosmos.consultorio.service.interfaces.ICitaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CitaServiceImp implements ICitaService {
    @Autowired
    private ICitaDao citaDao;

    @Override
    public List<CitaDataDTO> findAll() {
        ModelMapper modelMapper = new ModelMapper();
        List<CitaEntity> citaEntities = this.citaDao.findAll();
        if (citaEntities.isEmpty()) {
            return Collections.emptyList();
        }
//        return citaEntities.stream()
//                .map(entity -> {
//                    CitaDTO dto = new CitaDTO();
//                    dto.setId(entity.getId());
//                    dto.setNombrePaciente(entity.getNombrePaciente());
//                    dto.setHorarioConsulta(entity.getHorarioConsulta());
//                    dto.setConsultorio(entity.getConsultorio() != null ? entity.getConsultorio().getId() : null);
//                    dto.setDoctor(entity.getDoctor() != null ? entity.getDoctor().getId() : null);
//                    return dto;
//                })
//                .collect(Collectors.toList());

        return this.citaDao.findAll()
                .stream()
                .map(entity -> modelMapper.map(entity, CitaDataDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public CitaDataDTO findById(Long id) {
        Optional<CitaEntity> citaEntity =this.citaDao.findById(id);
        if(citaEntity.isPresent()){
//            CitaDTO dto = new CitaDTO();
//            dto.setId(citaEntity.get().getId());
//            dto.setNombrePaciente(citaEntity.get().getNombrePaciente());
//            dto.setHorarioConsulta(citaEntity.get().getHorarioConsulta());
//            dto.setConsultorio(citaEntity.get().getConsultorio() != null ? citaEntity.get().getConsultorio().getId() : null);
//            dto.setDoctor(citaEntity.get().getDoctor() != null ? citaEntity.get().getDoctor().getId() : null);
//            return dto;
            ModelMapper modelMapper = new ModelMapper();
            CitaEntity currentcita = citaEntity.get();
            return modelMapper.map(currentcita, CitaDataDTO.class);
        } else {
            return new CitaDataDTO();
        }
    }

    @Override
    public Optional<List<CitaDTO>> findByFecha(LocalDateTime horarioConsulta) {
        Optional<List<CitaEntity>> listCitaEntity =this.citaDao.findByFecha(horarioConsulta);
        if(listCitaEntity.isPresent()){
            ModelMapper modelMapper = new ModelMapper();
            List<CitaDTO> citaDTOList = listCitaEntity.get()
                    .stream()
                    .map(cita -> modelMapper.map(cita, CitaDTO.class))
                    .collect(Collectors.toList());

            return Optional.of(citaDTOList);
        } else {
            return Optional.of(Collections.emptyList());
        }
    }

    @Override
    public Optional<List<CitaDTO>> findByConsultorio(Long id) {
//        Optional<List<CitaEntity>> listCitaEntity =this.citaDao.findByConsultorio(id);
//        if(listCitaEntity.isPresent()){
//            ModelMapper modelMapper = new ModelMapper();
//            List<CitaDTO> citaDTOList = listCitaEntity.get()
//                    .stream()
//                    .map(cita -> modelMapper.map(cita, CitaDTO.class))
//                    .collect(Collectors.toList());
//
//            return Optional.of(citaDTOList);
//        } else {
            return Optional.of(Collections.emptyList());
//        }
    }

    @Override
    public Optional<List<CitaDTO>> findByDoctor(Long id) {
//        Optional<List<CitaEntity>> listCitaEntity =this.citaDao.findByDoctor(id);
//        if(listCitaEntity.isPresent()){
//            ModelMapper modelMapper = new ModelMapper();
//            List<CitaDTO> citaDTOList = listCitaEntity.get()
//                    .stream()
//                    .map(cita -> modelMapper.map(cita, CitaDTO.class))
//                    .collect(Collectors.toList());
//
//            return Optional.of(citaDTOList);
//        } else {
            return Optional.of(Collections.emptyList());
//        }
    }

    @Override
    public CitaDTO saveCita(CitaDTO citaDTO) {
        try{
            if(this.validarCita(citaDTO)){
                ModelMapper modelMapper = new ModelMapper();
                CitaEntity citaEntity = modelMapper.map(citaDTO, CitaEntity.class);

                Optional<DoctorEntity> doctor = this.citaDao.findByDoctor(citaDTO.getDoctor());
                if(doctor.isPresent()){
                    citaEntity.setDoctor(doctor.get());
                }
                Optional<ConsultorioEntity> consultorio = this.citaDao.findByConsultorio(citaDTO.getConsultorio());
                if(consultorio.isPresent()){
                    citaEntity.setConsultorio(consultorio.get());
                }
                this.citaDao.saveCita(citaEntity);
                return citaDTO;
            }else{
                throw new UnsupportedOperationException("La cita no es valida");
            }
        } catch (Exception e) {
            throw new UnsupportedOperationException("Fallo al guardar la cita");
        }
    }

    public boolean validarCita(CitaDTO citaDTO) {
        LocalDateTime horaCita = citaDTO.getHorarioConsulta();

//        if (existsByConsultorioAndHorarioConsulta(citaDTO.getConsultorio(), horaCita)) {
//            return false;
//        }

//        if (existsByDoctorAndHorarioConsulta(citaDTO.getDoctor(), horaCita)) {
//            return false;
//        }
//
//        LocalDateTime minHoraPermitida = horaCita.minusHours(2);
//        LocalDateTime maxHoraPermitida = horaCita.plusHours(2);
//        if (existsByNombrePacienteAndHorarioConsultaBetween(citaDTO.getNombrePaciente(), minHoraPermitida, maxHoraPermitida)) {
//            return false;
//        }

        return true;
    }

    public boolean existsByConsultorioAndHorarioConsulta(Long id,LocalDateTime date) {
        Optional<List<CitaEntity>> citaEntity =this.citaDao.findByValidateConsultorioFecha(id,date);
        return citaEntity.isPresent();
    }

    @Override
    public CitaDTO updateCita(CitaDTO citaDTO) {
        Optional<CitaEntity> citaEntity =this.citaDao.findById(citaDTO.getId());
        if(citaEntity.isPresent()){
            CitaEntity currentcita = citaEntity.get();
            currentcita.setHorarioConsulta(citaDTO.getHorarioConsulta());
            currentcita.setNombrePaciente(citaDTO.getNombrePaciente());
//            currentcita.setConsultorio(citaDTO.getConsultorio());
//            currentcita.setDoctor(citaDTO.getDoctor());

            this.citaDao.updateCita(currentcita);

            ModelMapper modelMapper = new ModelMapper();
            return modelMapper.map(currentcita, CitaDTO.class);
        }else{
            throw new IllegalArgumentException("No existe la cita");
        }
    }

    @Override
    public String deleteCita(Long id) {
        Optional<CitaEntity> citaEntity =this.citaDao.findById(id);
        if(citaEntity.isPresent()){
            CitaEntity currentcita = citaEntity.get();
            this.citaDao.deleteCita(currentcita);
            return "La cita ha con id "+id+" sido eliminado";
        }else{
            return ("No existe el cita con id "+id+" ");
        }
    }
}
