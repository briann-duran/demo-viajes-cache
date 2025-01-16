package brian.duran.puntos_de_ventas_services.service;

import brian.duran.commons_service.dto.UbicacionDto;
import brian.duran.commons_service.entity.Ubicacion;
import brian.duran.commons_service.enums.Provincia;

import java.util.List;

public interface IUbicacionService {
    List<UbicacionDto> findAll();
    UbicacionDto findByNombre(Provincia provincia);
    UbicacionDto save(Ubicacion ubicacion);
    UbicacionDto update(Ubicacion ubicacion);
    void deleteById(Long id);
}
