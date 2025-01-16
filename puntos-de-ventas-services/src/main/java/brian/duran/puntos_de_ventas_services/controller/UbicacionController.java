package brian.duran.puntos_de_ventas_services.controller;

import brian.duran.commons_service.dto.UbicacionDto;
import brian.duran.commons_service.entity.Ubicacion;
import brian.duran.commons_service.enums.Provincia;
import brian.duran.puntos_de_ventas_services.service.IUbicacionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/ubicacion")
public class UbicacionController {
    @Autowired
    private IUbicacionService ubicacionService;

    @GetMapping
    public ResponseEntity<?> findAll(){

        if (ubicacionService.findAll().isEmpty()) {
            return new ResponseEntity<>(ubicacionService.findAll(), HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(ubicacionService.findAll(), HttpStatus.ACCEPTED);
        }
    }

    @GetMapping("/nombre")
    public ResponseEntity<UbicacionDto> findByName(@RequestParam @Valid Provincia nombre){
        return new ResponseEntity<>(ubicacionService.findByNombre(nombre), HttpStatus.ACCEPTED);
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody Ubicacion ubicacion) {
        return new ResponseEntity<>(ubicacionService.save(ubicacion), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody Ubicacion ubicacion){
        return new ResponseEntity<>(ubicacionService.save(ubicacion), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        ubicacionService.deleteById(id);
        return new ResponseEntity<>("El elemento ha sido eliminado correctamente.", HttpStatus.OK);
    }
}
