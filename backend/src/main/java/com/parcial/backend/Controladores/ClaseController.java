package com.parcial.backend.Controladores;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import com.parcial.backend.Entidades.Clase;
import com.parcial.backend.Servicios.ClaseService;

@RestController
@RequestMapping("/api/clases")
public class ClaseController {
    
    @Autowired
    private ClaseService claseService;

    
    @PreAuthorize("hasAuthority('CREAR_CLASE')")
    @PostMapping
    public ResponseEntity<Clase> crearClase(@RequestBody Clase clase) {
        Clase nuevaClase = claseService.crearClase(clase);
        return ResponseEntity.ok(nuevaClase);
    }

   
    @PreAuthorize("hasAuthority('VER_CLASE')")
    @GetMapping("/{id}")
    public ResponseEntity<Clase> obtenerClasePorId(@PathVariable Long id) {
        return claseService.obtenerClasePorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasAuthority('VER_CLASE')")
    @GetMapping
    public List<Clase> obtenerTodasLasClases() {
        return claseService.obtenerTodasLasClases();
    }

    
    @PreAuthorize("hasAuthority('ACTUALIZAR_CLASE') or hasAuthority('ACTUALIZAR_HORARIO')")
    @PutMapping("/{id}")
    public ResponseEntity<Clase> actualizarClase(@PathVariable Long id, @RequestBody Clase clase) {
        Clase claseActualizada = claseService.actualizarClase(id, clase);
        return ResponseEntity.ok(claseActualizada);
    }

  
    @PreAuthorize("hasAuthority('ELIMINAR_CLASE')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarClase(@PathVariable Long id) {
        claseService.eliminarClase(id);
        return ResponseEntity.noContent().build();
    }


}
