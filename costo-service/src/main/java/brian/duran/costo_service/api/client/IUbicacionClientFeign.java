package brian.duran.costo_service.api.client;

import brian.duran.commons_service.dto.UbicacionDto;
import brian.duran.commons_service.entity.Ubicacion;
import brian.duran.commons_service.enums.Provincia;
import jakarta.validation.Valid;
import jakarta.ws.rs.Path;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "ubicacion-service", url = "localhost:8080")
public interface IUbicacionClientFeign {
    @GetMapping("/api/v1/ubicacion")
    public ResponseEntity<List<UbicacionDto>> findAllUbicacionDto();

    @GetMapping("/api/v1/ubicacion/nombre")
    public ResponseEntity<UbicacionDto> findByName(@RequestParam @Valid Provincia nombre);

    @PostMapping("/api/v1/ubicacion")
    public ResponseEntity<UbicacionDto> save(@Valid @RequestBody Ubicacion ubicacion);

    @PutMapping("/api/v1/ubicacion")
    public ResponseEntity<UbicacionDto> update(@Valid @RequestBody Ubicacion ubicacion);

    @DeleteMapping("/api/v1/ubicacion/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id);
}
