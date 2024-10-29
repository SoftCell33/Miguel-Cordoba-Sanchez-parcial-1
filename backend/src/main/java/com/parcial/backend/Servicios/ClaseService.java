package com.parcial.backend.Servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parcial.backend.Entidades.Clase;
import com.parcial.backend.Repository.ClaseRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ClaseService {
    @Autowired
    private ClaseRepository claseRepository;

    public Clase crearClase(Clase clase) {
        return claseRepository.save(clase);
    }

    public Optional<Clase> obtenerClasePorId(Long id) {
        return claseRepository.findById(id);
    }

    public List<Clase> obtenerTodasLasClases() {
        return claseRepository.findAll();
    }

    public Clase actualizarClase(Long id, Clase claseActualizada) {
        return claseRepository.findById(id).map(clase -> {
            clase.setNombreAsignatura(claseActualizada.getNombreAsignatura());
            clase.setDescripcion(claseActualizada.getDescripcion());
            clase.setSalon(claseActualizada.getSalon());
            clase.setHoraInicio(claseActualizada.getHoraInicio());
            clase.setHoraFin(claseActualizada.getHoraFin());
            clase.setDocenteEncargado(claseActualizada.getDocenteEncargado());
            return claseRepository.save(clase);
        }).orElseThrow(() -> new RuntimeException("Clase no encontrada"));
    }

    public void eliminarClase(Long id) {
        claseRepository.deleteById(id);
    }
}
