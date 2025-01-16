package brian.duran.costo_service.service.impl;

import brian.duran.commons_service.dto.ViajeDto;
import brian.duran.commons_service.entity.Viaje;
import brian.duran.commons_service.exception.ResourceNotFoundException;
import brian.duran.costo_service.api.client.IUbicacionClientFeign;
import brian.duran.costo_service.repository.ViajeRepository;
import brian.duran.costo_service.service.IViajeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class ViajeService implements IViajeService{
    @Autowired
    ViajeRepository viajeRepository;
    @Autowired
    IUbicacionClientFeign ubicacionClientFeign;
    @Autowired
    ObjectMapper mapper;

    @Cacheable("cache2")
    @Override
    public List<ViajeDto> findAll() {
        List<Viaje> viajeEntityList = viajeRepository.findAll();
        List<ViajeDto> viajeDtoList = new ArrayList<>();

        viajeEntityList.forEach(viaje -> {
            viajeDtoList.add(mapper.convertValue(viaje, ViajeDto.class));
        });

        return viajeDtoList;
    }

    @CachePut(cacheNames="cache2", key="#viaje.id")
    @Transactional
    @Override
    public ViajeDto save(Viaje viaje) {
        boolean exist = viajeRepository.existsById(viaje.getId());

        if (exist) {
            throw new ResourceNotFoundException("Ya se ha calculado un costo para el viaje con id = " + viaje.getId() + ". " +
                    "Viaje de '" + viaje.getProvinciaPartida()+ "' a '" + viaje.getProvinciaLlegada()+ "' tiene un costo de $" + viaje.getCosto());

        }

        ubicacionClientFeign.findByName(viaje.getProvinciaPartida());
        ubicacionClientFeign.findByName(viaje.getProvinciaLlegada());

        return mapper.convertValue(viajeRepository.save(viaje), ViajeDto.class);
    }

    @Override
    public ViajeDto update(Viaje viaje) {
        return null;
    }

    @Override
    public void deleteById(Long id) {
        Viaje viaje = viajeRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("No se encuentra registrado el viaje con id = " + id)
        );

        viajeRepository.deleteById(id);
    }
}
