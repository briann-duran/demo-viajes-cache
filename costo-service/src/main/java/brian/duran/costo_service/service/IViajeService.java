package brian.duran.costo_service.service;

import brian.duran.commons_service.dto.ViajeDto;
import brian.duran.commons_service.entity.Viaje;

import java.util.List;

public interface IViajeService {
    List<ViajeDto> findAll();
    ViajeDto save(Viaje viaje);
    ViajeDto update(Viaje viaje);
    void deleteById(Long id);
}
