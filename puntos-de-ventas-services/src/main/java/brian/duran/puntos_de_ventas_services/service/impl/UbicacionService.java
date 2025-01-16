package brian.duran.puntos_de_ventas_services.service.impl;

import brian.duran.commons_service.dto.UbicacionDto;
import brian.duran.commons_service.entity.Ubicacion;
import brian.duran.commons_service.enums.Provincia;
import brian.duran.commons_service.exception.DuplicateResourceException;
import brian.duran.commons_service.exception.NullOrEmptyFieldException;
import brian.duran.commons_service.exception.ResourceNotFoundException;
import brian.duran.puntos_de_ventas_services.repository.UbicacionRepository;
import brian.duran.puntos_de_ventas_services.service.IUbicacionService;
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

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UbicacionService implements IUbicacionService {
    @Autowired
    UbicacionRepository ubicacionRepository;
    @Autowired
    ObjectMapper mapper;

    @Override
    @Cacheable("cache1")
    public List<UbicacionDto> findAll() {
        List<Ubicacion> ubicacionList = ubicacionRepository.findAll();


        return ubicacionList.stream().map( ubicacion ->
            mapper.convertValue(ubicacion, UbicacionDto.class)
        ).collect(Collectors.toList());
    }

    @Override
    public UbicacionDto findByNombre(Provincia provincia) {
        Optional<Ubicacion> ubicacion = ubicacionRepository.findByNombre(provincia.getNombre());

        if(ubicacion.isEmpty()) {
            throw new ResourceNotFoundException("Ubicación '" + provincia.getNombre()+ "' no se encuentra registrado.");
        }

        return mapper.convertValue(ubicacion, UbicacionDto.class);
    }

    @Transactional
    @Override
    @CachePut(cacheNames="cache1", key="#ubicacion.id")
    public UbicacionDto save(Ubicacion ubicacion) {
        if (ubicacion.getId() != null && ubicacionRepository.existsById(ubicacion.getId())) {
                throw new DuplicateResourceException("Punto de venta '" + ubicacion.getNombre() + "' ya se encuentra registrado.");
        }

        return mapper.convertValue(ubicacionRepository.save(ubicacion), UbicacionDto.class);
    }

    @Override
    public UbicacionDto update(Ubicacion ubicacion) {
        Optional<Ubicacion> ubicacionById = ubicacionRepository.findById(ubicacion.getId());

        if (ubicacionById.isEmpty()){
            throw new NullOrEmptyFieldException("Punto de venta '" + ubicacion.getNombre() + "' no se encuentra registrado en la base de datos.");
        }


        return mapper.convertValue(ubicacionRepository.save(ubicacion), UbicacionDto.class);
    }

    @Override
    public void deleteById(Long id) {
        Ubicacion ubicacionById = ubicacionRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Punto de venta con id = '" + id + "' no se encuentra registrado en la base de datos.")
        );

        ubicacionRepository.deleteById(ubicacionById.getId());
    }
}
