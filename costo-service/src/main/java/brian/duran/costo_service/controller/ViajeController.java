package brian.duran.costo_service.controller;

import brian.duran.commons_service.entity.Ubicacion;
import brian.duran.commons_service.entity.Viaje;
import brian.duran.costo_service.service.IViajeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/viajes")
public class ViajeController {
    @Autowired
    IViajeService viajeService;

    @GetMapping
    public ResponseEntity<?> findAll(){
        if (viajeService.findAll().isEmpty()) {
            return new ResponseEntity<>(viajeService.findAll(), HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(viajeService.findAll(), HttpStatus.ACCEPTED);
        }
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody Viaje viaje) {
        return new ResponseEntity<>(viajeService.save(viaje), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        viajeService.deleteById(id);
        return new ResponseEntity<>("El elemento ha sido eliminado correctamente.", HttpStatus.OK);
    }
}
